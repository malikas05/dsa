package leetcode_contests.weekly.weekly_208;

import java.util.Stack;

/*
https://leetcode.com/contest/weekly-contest-208/problems/crawler-log-folder/
 */
public class CrawlerLogFolder {
    private static final String PARENT_FOLDER = "../";
    private static final String SAME_FOLDER = "./";

    public static void main(String[] args) {
        System.out.println(minOperations(new String[]{"d1/", "d2/", "../", "d21/", "./"}));
    }

    public static int minOperations(String[] logs) {
        if (logs == null || logs.length == 0)
            return 0;

        Stack<String> stack = new Stack<>();

        for (String log : logs) {
            if (!log.equals(PARENT_FOLDER) && !log.equals(SAME_FOLDER)) {
                stack.push(log);
            } else if (log.equals(PARENT_FOLDER) && !stack.isEmpty()) {
                stack.pop();
            }
        }

        int result = 0;
        while (!stack.isEmpty()) {
            result++;
            stack.pop();
        }

        return result;
    }
}
