package low_level_design.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

public class LogClientImpl implements LogClient {
    private static final Logger logger = LoggerFactory.getLogger(LogClientImpl.class);

    private final Map<String, Process> processes;
    private final ConcurrentSkipListMap<Long, List<Process>> queue;
    private final BlockingQueue<CompletableFuture<String>> pendingPolls;
    private final Lock lock;
    private final ExecutorService[] taskScheduler;

    public LogClientImpl(int taskSchedulerSize) {
        this.processes = new ConcurrentHashMap<>();
        this.queue = new ConcurrentSkipListMap<>();
        this.pendingPolls = new LinkedBlockingQueue<>();
        lock = new ReentrantLock();
        this.taskScheduler = new ExecutorService[taskSchedulerSize];
        IntStream.range(0, taskSchedulerSize).forEach(i -> taskScheduler[i] = Executors.newSingleThreadExecutor());
    }

    /*
    - Complexity Analysis:
    Time complexity: O(log N)
     */
    @Override
    public void start(String taskId, long timestamp) {
        this.taskScheduler[taskId.hashCode() % taskScheduler.length].execute(() -> {
            Process process = new Process(taskId, timestamp);
            processes.put(taskId, process);
            queue.putIfAbsent(timestamp, new CopyOnWriteArrayList<>());
            queue.get(timestamp).add(process);
        });
    }

    /*
    - Complexity Analysis:
    Time complexity: O(1)
     */
    @Override
    public void end(String taskId) {
        this.taskScheduler[taskId.hashCode() % taskScheduler.length].execute(() -> {
            processes.get(taskId).setEndTime(System.currentTimeMillis());
            lock.lock();
            try {
                String result;
                while (!pendingPolls.isEmpty() && (result = pollNow()) != null) {
                    pendingPolls.take().complete(result);
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                lock.unlock();
            }
        });
    }

    /*
    - Complexity Analysis:
    Time complexity: O(log N)
     */
    @Override
    public String poll() {
        var result = new CompletableFuture<String>();
        lock.lock();
        try {
            try {
                String logStatement;
                if (!pendingPolls.isEmpty() || (logStatement = pollNow()) == null) {
                    pendingPolls.offer(result);
                } else {
                    return logStatement;
                }
            } finally {
                lock.unlock();
            }

            return result.get(3, TimeUnit.SECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            throw new RuntimeException(e);
        }
    }

    private String pollNow() {
        if (!queue.isEmpty()) {
            for (Process process : queue.firstEntry().getValue()) {
                if (process.getEndTime() != -1) {
                    final var logStatement = process.getId() + " started at " + process.getStartTime() + " and ended at " + process.getEndTime();
                    logger.info(logStatement);
                    processes.remove(process.getId());
                    queue.firstEntry().getValue().remove(process);
                    if (queue.firstEntry().getValue().isEmpty())
                        queue.pollFirstEntry();
                    return logStatement;
                }
            }
        }

        return null;
    }
}
