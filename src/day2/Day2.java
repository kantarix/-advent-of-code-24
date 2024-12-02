package day2;

import util.DayTemplate;

import java.util.*;

public class Day2 extends DayTemplate {

    private static final int RANGE_MIN = 1;
    private static final int RANGE_MAX = 3;

    @Override
    public String solve(boolean isFirstPart, Scanner in) {
        String answer = "";
        List<String> lines = this.readFile(in);

        var reports = new ArrayList<List<Integer>>();

        for (String line : lines) {
            List<Integer> levels = Arrays.stream(line.split("\s+"))
                    .mapToInt(Integer::parseInt)
                    .boxed().toList();

            reports.add(levels);
        }

        answer = solveAnyPart(reports, isFirstPart);

        return answer;
    }

    private String solveAnyPart(List<List<Integer>> reports, boolean isFirstPart) {
        int result = 0;

        if (isFirstPart) {
            for (List<Integer> levels : reports)
                if (checkIncreasingAndSafe(levels, true)
                        || checkDecreasingAndSafe(levels, true))
                    result++;
        } else {
            for (List<Integer> levels : reports)
                if (checkIncreasingAndSafe(new ArrayList<>(levels), false)
                        || checkDecreasingAndSafe(new ArrayList<>(levels), false))
                    result++;
        }


        return result + "";
    }

    private boolean checkIncreasingAndSafe(List<Integer> levels, boolean isFirstPart) {
        for (int i = 1; i < levels.size(); i++)
            if (!isIncrease(levels, i - 1, i) || !isDifferInRange(levels, i - 1, i)) {
                if (isFirstPart) return false;
                var first = new ArrayList<>(levels);
                var second = new ArrayList<>(levels);
                first.remove(i);
                second.remove(i - 1);
                return checkIncreasingAndSafe(first, true) || checkIncreasingAndSafe(second, true);
            }

        return true;
    }

    private boolean checkDecreasingAndSafe(List<Integer> levels, boolean isFirstPart) {
        for (int i = 1; i < levels.size(); i++)
            if (!isDecrease(levels, i - 1, i) || !isDifferInRange(levels, i - 1, i)) {
                if (isFirstPart) return false;
                var first = new ArrayList<>(levels);
                var second = new ArrayList<>(levels);
                first.remove(i);
                second.remove(i - 1);
                return checkDecreasingAndSafe(first, true) || checkDecreasingAndSafe(second, true);
            }

        return true;
    }

    private boolean isIncrease(List<Integer> levels, int i, int j) {
        return levels.get(i) < levels.get(j);
    }

    private boolean isDecrease(List<Integer> levels, int i, int j) {
        return levels.get(i) > levels.get(j);
    }

    private boolean isDifferInRange(List<Integer> levels, int i, int j) {
        return Math.abs(levels.get(i) - levels.get(j)) >= RANGE_MIN && Math.abs(levels.get(i) - levels.get(j)) <= RANGE_MAX;
    }

}