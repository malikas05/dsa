package leetcode_contests.biweekly.biweekly_37;

import java.util.*;

/*
1620. Coordinate With Maximum Network Quality - https://leetcode.com/problems/coordinate-with-maximum-network-quality/
 */
public class CoordinateWithMaximumNetworkQuality {
    public static void main(String[] args) {
        int[] result = bestCoordinate(new int[][]{{1, 2, 5}, {2, 1, 7}, {3, 1, 9}}, 2);
        System.out.println(result[0] + " " + result[1]);

        result = bestCoordinate(new int[][]{{23, 11, 21}}, 9);
        System.out.println(result[0] + " " + result[1]);

        result = bestCoordinate(new int[][]{{1, 2, 13}, {2, 1, 7}, {0, 1, 9}}, 9);
        System.out.println(result[0] + " " + result[1]);

        result = bestCoordinate(new int[][]{{2, 1, 9}, {0, 1, 9}}, 9);
        System.out.println(result[0] + " " + result[1]);
    }

    public static int[] bestCoordinate(int[][] towers, int radius) {
        int minX = towers[0][0], minY = towers[0][1];
        int maxX = towers[0][0], maxY = towers[0][1];

        for (int i = 1; i < towers.length; i++) {
            minX = Math.min(towers[i][0], minX);
            minY = Math.min(towers[i][1], minY);

            maxX = Math.max(towers[i][0], maxX);
            maxY = Math.max(towers[i][1], maxY);
        }

        List<List<Integer>> possibleCoordinates = new ArrayList<>();
        for (int i = minX; i <= maxX; i++) {
            for (int j = minY; j <= maxY; j++) {
                possibleCoordinates.add(Arrays.asList(i, j));
            }
        }

        int maxQuality = 0;
        int[] maxPoint = new int[2];

        for (List<Integer> possibleCoordinate : possibleCoordinates) {
            int quality = 0;

            for (int[] towerCoordinate : towers) {
                double distance = calculateEuclideanDistane(possibleCoordinate.get(0), towerCoordinate[0], possibleCoordinate.get(1), towerCoordinate[1]);
                if (distance <= radius) {
                    quality += calculateQuality(towerCoordinate[2], distance);
                }
            }

            if (quality > maxQuality || (quality == maxQuality && (possibleCoordinate.get(0) < maxPoint[0] || possibleCoordinate.get(0) == maxPoint[0]) && possibleCoordinate.get(1) < maxPoint[1])) {
                maxQuality = quality;
                maxPoint[0] = possibleCoordinate.get(0);
                maxPoint[1] = possibleCoordinate.get(1);
            }
        }

        return maxPoint;
    }

    private static double calculateEuclideanDistane(int x1, int x2, int y1, int y2) {
        return Math.sqrt(Math.pow((x1 - x2), 2) + Math.pow((y1 - y2), 2));
    }

    private static int calculateQuality(int qualityFactor, double distance) {
        return (int) (qualityFactor / (1 + distance));
    }
}
