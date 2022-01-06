package hackerrank;

import support.Support;

import java.io.*;
import java.util.*;
import java.util.stream.*;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

class Result2DArrayDS {


    public static int hourglassSum(List<List<Integer>> arr) {
        return calculateFor(arr, 0, 0, Integer.MIN_VALUE);
    }

    public static int calculateFor(List<List<Integer>> arr, int row, int column, int max) {

        int result = 0;
        result +=  arr.get(row).get(column);
        result +=  arr.get(row).get(column + 1);
        result +=  arr.get(row).get(column + 2);
        result +=  arr.get(row + 1).get(column + 1);
        result +=  arr.get(row + 2).get(column);
        result +=  arr.get(row + 2).get(column + 1);
        result +=  arr.get(row + 2).get(column + 2);

        if (result > max) {
            max = result;
        }
        if (column + 2 >= 5) {
            column = 0;
            if (row + 2 >= 5) {
                return max;
            }
            row++;
        } else {
            column++;
        }
        return calculateFor(arr, row, column, max);
    }

}

public class Solution2DArrayDS {
    public static void main(String[] args) throws IOException {
        Support.createInOutFileIfNotExist();
        BufferedReader bufferedReader = new BufferedReader(Support.getIs());
        BufferedWriter bufferedWriter = new BufferedWriter(Support.getOs());

        List<List<Integer>> arr = new ArrayList<>();

        IntStream.range(0, 6).forEach(i -> {
            try {
                arr.add(
                        Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                                .map(Integer::parseInt)
                                .collect(toList())
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        int result = Result2DArrayDS.hourglassSum(arr);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }

}
