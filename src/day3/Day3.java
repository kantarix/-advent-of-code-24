package day3;

import util.DayTemplate;

import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day3 extends DayTemplate {

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

        Pattern pattern = Pattern.compile("mul\\(\\d+,\\d+\\)");

        for (String line : lines) {
            Matcher matcher = pattern.matcher(line);

            while (matcher.find()) {
                var match = matcher.group();
                var pair = match.substring(0, match.length() - 1).substring(4);
                var i = Integer.parseInt(pair.split(",")[0]);
                var j = Integer.parseInt(pair.split(",")[1]);
                result += i * j;
            }
        }

        return result + "";
    }

    private String solveSecondPart(List<String> lines) {
        int result = 0;

        Pattern pattern = Pattern.compile("(mul\\(\\d+,\\d+\\))|(do\\(\\))|(don't\\(\\))");
        boolean enable = true;

        for (String line : lines) {
            Matcher matcher = pattern.matcher(line);

            while (matcher.find()) {
                var match = matcher.group();
                if (match.equals("do()")) enable = true;
                else if (match.equals("don't()")) enable = false;
                else if (enable) {
                    var pair = match.substring(0, match.length() - 1).substring(4);
                    var i = Integer.parseInt(pair.split(",")[0]);
                    var j = Integer.parseInt(pair.split(",")[1]);
                    result += i * j;
                }
            }
        }

        return result + "";
    }

}