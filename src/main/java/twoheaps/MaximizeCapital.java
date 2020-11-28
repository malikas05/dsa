package twoheaps;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/*
Problem Statement #
Given a set of investment projects with their respective profits, we need to find the most profitable projects.
We are given an initial capital and are allowed to invest only in a fixed number of projects.
Our goal is to choose projects that give us the maximum profit.
Write a function that returns the maximum total capital after selecting the most profitable projects.
We can start an investment project only when we have the required capital. Once a project is selected,
we can assume that its profit has become our capital.

Example 1:
Input: Project Capitals=[0,1,2], Project Profits=[1,2,3], Initial Capital=1, Number of Projects=2
Output: 6
Explanation:
With initial capital of ‘1’, we will start the second project which will give us profit of ‘2’.
Once we selected our first project, our total capital will become 3 (profit + initial capital).
With ‘3’ capital, we will select the third project, which will give us ‘3’ profit.
After the completion of the two projects, our total capital will be 6 (1+2+3).

Example 2:
Input: Project Capitals=[0,1,2,3], Project Profits=[1,2,3,5], Initial Capital=0, Number of Projects=3
Output: 8
Explanation:
With ‘0’ capital, we can only select the first project, bringing out capital to 1.
Next, we will select the second project, which will bring our capital to 3.
Next, we will select the fourth project, giving us a profit of 5.
After selecting the three projects, our total capital will be 8 (1+2+5).
 */
public class MaximizeCapital {
    /*
    - Complexity Analysis:
    Time complexity: O(N log N + K log N)
    Space complexity: O(N)
     */
    public static int findMaximumCapital(int[] capital, int[] profits, int numberOfProjects, int initialCapital) {
        PriorityQueue<Project> maxHeapProfit = new PriorityQueue<>((project1, project2) -> Integer.compare(project2.profit, project1.profit));

        for (int i = 0; i < capital.length; i++) {
            maxHeapProfit.offer(new Project(capital[i], profits[i]));
        }

        for (int i = 0; i < numberOfProjects; i++) {
            List<Project> list = new ArrayList<>();
            while (!maxHeapProfit.isEmpty() && initialCapital < maxHeapProfit.peek().capital) {
                list.add(maxHeapProfit.poll());
            }

            if (maxHeapProfit.isEmpty()) {
                return -1;
            }

            initialCapital += maxHeapProfit.poll().profit;
            maxHeapProfit.addAll(list);
        }

        return initialCapital;
    }

    public static void main(String[] args) {
        int result = MaximizeCapital.findMaximumCapital(new int[] { 0, 1, 2 }, new int[] { 1, 2, 3 }, 2, 1);
        System.out.println("Maximum capital: " + result);
        result = MaximizeCapital.findMaximumCapital(new int[] { 0, 1, 2, 3 }, new int[] { 1, 2, 3, 5 }, 3, 0);
        System.out.println("Maximum capital: " + result);
    }

    private static class Project {
        private int capital;
        private int profit;

        public Project(int capital, int profit) {
            this.capital = capital;
            this.profit = profit;
        }

        public int getCapital() {
            return capital;
        }

        public int getProfit() {
            return profit;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Project project = (Project) o;

            if (capital != project.capital) return false;
            return profit == project.profit;
        }

        @Override
        public int hashCode() {
            int result = capital;
            result = 31 * result + profit;
            return result;
        }
    }
}
