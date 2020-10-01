package consistent_hashing;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.SortedMap;
import java.util.TreeMap;
import java.util.concurrent.ThreadLocalRandom;

public class ConsistentHashing {
    private static final Logger logger = LoggerFactory.getLogger(ConsistentHashing.class);
    // Consistent Hashing with Ring having 50 buckets.
    private static final int LIMIT = 50;
    private static final int NUM_SERVERS = 10;
    private static final SortedMap<Integer, String> bucketIdToServerMap = new TreeMap<>();

    public static void main(String[] args) throws InterruptedException {
        // Hash function to calculate hashes for serverId and the userId.
        HashFunction hashFunction = new HashFunction(61, 59);

        logger.info("Creating {} servers.", NUM_SERVERS);
        StringBuilder stringBuilder = new StringBuilder().append("Server: 0");
        for (int i = 1; i <= NUM_SERVERS; i++) {
            final String server = stringBuilder.replace(stringBuilder.length() - 1, stringBuilder.length(), String.valueOf(i)).toString();
            // Can be situation of hash collision, which would override the previous server. Else again hash with some other function.
            bucketIdToServerMap.put(hashFunction.getHashValue(server), server);
        }

        Thread loadBalancerThread = new Thread(new LoadBalancerTask(bucketIdToServerMap, hashFunction));
        logger.info("Starting LoadBalancerTask.");
        loadBalancerThread.start();
        logger.info("Running LoadBalancerTask for 5 seconds.");
        Thread.sleep(5000);
        logger.info("Interrupting LoadBalancerTask.");
        loadBalancerThread.interrupt();
        loadBalancerThread.join();

        logger.info("Exiting the application.");
    }

    private static class LoadBalancerTask implements Runnable {
        private SortedMap<Integer, String> bucketIdToServerMap;
        private HashFunction hashFunction;

        public LoadBalancerTask(SortedMap<Integer, String> bucketIdToServerMap, HashFunction hashFunction) {
            this.bucketIdToServerMap = bucketIdToServerMap;
            this.hashFunction = hashFunction;
        }

        @Override
        public void run() {
            try {
                while (true) {
                    int randomUserId = ThreadLocalRandom.current().nextInt(Integer.MAX_VALUE);
                    int userBucket = hashFunction.getHashValue(String.valueOf(randomUserId));

                    SortedMap<Integer, String> mapViewWithKeyGreaterThanUserBucket = bucketIdToServerMap.tailMap(userBucket);
                    Integer bucketIdForUser = mapViewWithKeyGreaterThanUserBucket.isEmpty() ? bucketIdToServerMap.firstKey() : mapViewWithKeyGreaterThanUserBucket.firstKey();
                    String serverIdForUser = bucketIdToServerMap.get(bucketIdForUser);

                    logger.info("----------------------------------------");
                    logger.info("User ID: {} has been assigned to {}", randomUserId, serverIdForUser);

                    Thread.sleep(100);
                }
            } catch (InterruptedException e) {
                logger.info("LoadBalancerTask has been interrupted");
            }
        }
    }

    private static class HashFunction {
        private long prime;
        private long odd;

        public HashFunction(long prime, long odd) {
            this.prime = prime;
            this.odd = odd;
        }

        public int getHashValue(String word) {
            int hash = word.hashCode();
            if (hash < 0)
                hash = Math.abs(hash);
            return calculateHash(hash);
        }

        private int calculateHash(int hash) {
            return (int) ((((hash % LIMIT) * prime) % LIMIT) * odd) % LIMIT;
        }
    }
}
