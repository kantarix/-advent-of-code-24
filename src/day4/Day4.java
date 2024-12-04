package day4;

import util.DayTemplate;

import java.util.List;
import java.util.Scanner;

public class Day4 extends DayTemplate {

    @Override
    public String solve(boolean isFirstPart, Scanner in) {
        String answer = "";
        List<String> lines = this.readFile(in);

        int n = lines.size();
        int m = lines.get(0).length();
        char[][] matrix = new char[n][m];

        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                matrix[i][j] = lines.get(i).charAt(j);

        if (isFirstPart) answer = solveFirstPart(matrix, n, m);
        else answer = solveSecondPart(matrix, n, m);

        return answer;
    }

    private String solveFirstPart(char[][] matrix, int n, int m) {
        int result = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (matrix[i][j] == 'X') {
                    if (i-3 >= 0 && j-3 >= 0 && matrix[i-1][j-1] == 'M' && matrix[i-2][j-2] == 'A' && matrix[i-3][j-3] == 'S') result++;
                    if (j-3 >= 0 && matrix[i][j-1] == 'M' && matrix[i][j-2] == 'A' && matrix[i][j-3] == 'S') result++;
                    if (i+3 < n && j-3 >= 0 && matrix[i+1][j-1] == 'M' && matrix[i+2][j-2] == 'A' && matrix[i+3][j-3] == 'S') result++;

                    if (i-3 >= 0 && j+3 < m && matrix[i-1][j+1] == 'M' && matrix[i-2][j+2] == 'A' && matrix[i-3][j+3] == 'S') result++;
                    if (j+3 < m && matrix[i][j+1] == 'M' && matrix[i][j+2] == 'A' && matrix[i][j+3] == 'S') result++;
                    if (i+3 < n && j+3 < m && matrix[i+1][j+1] == 'M' && matrix[i+2][j+2] == 'A' && matrix[i+3][j+3] == 'S') result++;

                    if (i-3 >= 0 && matrix[i-1][j] == 'M' && matrix[i-2][j] == 'A' && matrix[i-3][j] == 'S') result++;
                    if (i+3 < n && matrix[i+1][j] == 'M' && matrix[i+2][j] == 'A' && matrix[i+3][j] == 'S') result++;
                }
            }
        }

        return result + "";
    }

    private String solveSecondPart(char[][] matrix, int n, int m) {
        int result = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (matrix[i][j] == 'A' && i-1 >= 0 && j-1 >=0 && i+1 < n &&  j+1 < m) {
                    if ((matrix[i-1][j-1] == 'M' && matrix[i+1][j+1] == 'S' || matrix[i-1][j-1] == 'S' && matrix[i+1][j+1] == 'M') &&
                            (matrix[i-1][j+1] == 'M' && matrix[i+1][j-1] == 'S' || matrix[i-1][j+1] == 'S' && matrix[i+1][j-1] == 'M'))
                        result++;
                }
            }
        }

        return result + "";
    }

}