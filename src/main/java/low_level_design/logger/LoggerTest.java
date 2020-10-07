package low_level_design.logger;

import org.junit.Test;

import java.util.*;
import java.util.concurrent.*;

import static java.util.concurrent.CompletableFuture.allOf;
import static java.util.concurrent.CompletableFuture.runAsync;

public class LoggerTest {
    @Test
    public void test() {
        LogClient logClient = new LogClientImpl(10);
        logClient.start("1", System.currentTimeMillis());
        logClient.poll();
        logClient.start("3", System.currentTimeMillis());
        logClient.poll();
        logClient.end("1");
        logClient.poll();
        logClient.start("2", System.currentTimeMillis());
        logClient.poll();
        logClient.end("2");
        logClient.poll();
        logClient.end("3");
        logClient.poll();
        logClient.poll();
        logClient.poll();
    }

    /*
    This test checks the blocking logic to make sure that the thread is blocked if the task with the lowest start time has not been finished yet
     */
    @Test
    public void defaultLogging() throws InterruptedException, ExecutionException {
        final LogClient logClient = new LogClientImpl(10);
        List<CompletableFuture<Void>> tasks = new ArrayList<>();
        logClient.start("1", 1);
        logClient.start("2", 2);
        logClient.start("3", 3);
        tasks.add(runAsync(() -> logClient.end("3")));
        tasks.add(runAsync(() -> logClient.end("2")));
        tasks.add(runAsync(logClient::poll));
        tasks.add(runAsync(logClient::poll));
        tasks.add(runAsync(() -> logClient.end("1")));
        tasks.add(runAsync(logClient::poll));
        allOf(tasks.toArray(CompletableFuture[]::new)).get();
    }

    @Test
    public void concurrencyTest() throws ExecutionException, InterruptedException {
        final LogClient logClient = new LogClientImpl(10);
        final var size = 1000;
        final ExecutorService executorService = Executors.newFixedThreadPool(size);
        final Random random = new Random();
        final List<String> commands = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            commands.add("POLL");
            commands.add("END " + i);
        }
        Collections.shuffle(commands);
        Map<Integer, Integer> ends = new HashMap<>();
        for (int i = 0; i < size * 2; i++) {
            if (!commands.get(i).equals("POLL")) {
                ends.put(Integer.parseInt(commands.get(i).split(" ")[1]), i);
            }
        }
        int index = commands.size() - 1;
        while (index >= 0) {
            if (commands.get(index).startsWith("END ")) {
                final var taskId = Integer.parseInt(commands.get(index).split(" ")[1]);
                final var insertionPoint = random.nextInt(Math.min(ends.get(taskId), ends.getOrDefault(taskId + 1, commands.size() - 1)) + 1);
                commands.add(insertionPoint, "START " + taskId);
                if (insertionPoint <= index) {
                    index++;
                }
            }
            index--;
        }
        final List<CompletableFuture<Void>> tasks = new CopyOnWriteArrayList<>();
        for (final String command : commands) {
            if (command.equals("POLL")) {
                tasks.add(runAsync(logClient::poll, executorService));
            } else {
                final var id = command.split(" ")[1];
                if (command.startsWith("START ")) {
                    logClient.start(id, size - Long.parseLong(id) + 1);
                } else {
                    logClient.end(id);
                }
            }
        }
        allOf(tasks.toArray(CompletableFuture[]::new)).get();
    }
}
