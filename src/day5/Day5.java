package day5;

import util.DayTemplate;

import java.util.*;

public class Day5 extends DayTemplate {

    @Override
    public String solve(boolean isFirstPart, Scanner in) {
        String answer = "";
        List<String> rules = readFileUntilEmpty(in);
        List<String> updates = readFileUntilEmpty(in);

        if (isFirstPart) answer = solveFirstPart(rules, updates);
        else answer = solveSecondPart(rules, updates);

        return answer;
    }

    private String solveFirstPart(List<String> rules, List<String> updates) {
        int result = 0;

        HashMap<Integer, Set<Integer>> next = new HashMap<>();
        fillMap(rules, next);

        for (String line : updates) {
            var update = Arrays.stream(line.split(","))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            var seen = new ArrayList<Integer>();

            for (int i = 0; i < update.length; i++) {
                if (findRulesViolation(update[i], seen, next) != -1)
                    break;
                seen.add(update[i]);

                if (i == update.length - 1)
                    result += update[update.length / 2];
            }
        }

        return result + "";
    }

    private String solveSecondPart(List<String> rules, List<String> updates) {
        int result = 0;

        HashMap<Integer, Set<Integer>> next = new HashMap<>();
        fillMap(rules, next);

        for (String line : updates) {
            var update = Arrays.stream(line.split(","))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            var seen = new ArrayList<Integer>();
            var violation = false;

            for (int i = 0; i < update.length; i++) {
                int j = findRulesViolation(update[i], seen, next);
                if (j != -1) {
                    violation = true;
                    int temp = update[i];
                    update[i] = update[j];
                    update[j] = temp;
                    i = j;
                    seen = new ArrayList<>(seen.subList(0, j));
                }
                seen.add(update[i]);
            }

            if (violation)
                result += update[update.length / 2];
        }

        return result + "";
    }

    private int findRulesViolation(Integer num, ArrayList<Integer> seen, HashMap<Integer, Set<Integer>> next) {
        for (int i = 0; i < seen.size(); i++)
            if (next.containsKey(num) && next.get(num).contains(seen.get(i))) return i;
        return -1;
    }

    private void fillMap(List<String> rules, HashMap<Integer, Set<Integer>> next) {
        for (String rule : rules) {
            int l = Integer.parseInt(rule.split("\\|")[0]);
            int r = Integer.parseInt(rule.split("\\|")[1]);

            if (!next.containsKey(l)) next.put(l, new HashSet<>());
            next.get(l).add(r);
        }
    }

    private List<String> readFileUntilEmpty(Scanner in) {
        List<String> lines = new ArrayList<>();
        while (in.hasNext()) {
            String newLine = in.nextLine();
            if (newLine.isEmpty()) {
                return lines;
            } else {
                lines.add(newLine);
            }
        }
        return lines;
    }

}