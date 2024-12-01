package day1;

import util.DayTemplate;

import java.util.*;

public class Day1 extends DayTemplate {

    @Override
    public String solve(boolean isFirstPart, Scanner in) {
        String answer = "";
        List<String> lines = this.readFile(in);

        if (isFirstPart) answer = solveFirstPart(lines);
        else answer = solveSecondPart(lines);

        return answer;
    }

    private String solveFirstPart(List<String> lines) {
        int result = 0;

        var firstArr = new int[lines.size()];
        var secondArr = new int[lines.size()];

        for (int i = 0; i < lines.size(); i++) {
            int first = Integer.parseInt(lines.get(i).split("\s+")[0]);
            int second = Integer.parseInt(lines.get(i).split("\s+")[1]);
            firstArr[i] = first;
            secondArr[i] = second;
        }

        Arrays.sort(firstArr);
        Arrays.sort(secondArr);

        for (int i = 0; i < firstArr.length; i++)
            result += Math.abs(firstArr[i] - secondArr[i]);

        return result + "";
    }

    private String solveSecondPart(List<String> lines) {
        int result = 0;

        var map1 = new HashMap<Integer, Integer>();
        var map2 = new HashMap<Integer, Integer>();

        for (int i = 0; i < lines.size(); i++) {
            int first = Integer.parseInt(lines.get(i).split("\s+")[0]);
            int second = Integer.parseInt(lines.get(i).split("\s+")[1]);

            if (map1.containsKey(first)) map1.put(first, map1.get(first) + 1);
            else map1.put(first, 1);

            if (map2.containsKey(second)) map2.put(second, map2.get(second) + 1);
            else map2.put(second, 1);
        }

        for (int key : map1.keySet())
            if (map2.containsKey(key))
                result += key * map2.get(key) * map1.get(key);

        return result + "";
    }

}
