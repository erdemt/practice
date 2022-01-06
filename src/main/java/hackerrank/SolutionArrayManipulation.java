package hackerrank;

import support.Support;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

class ResultArrayManipulation {


    public static long arrayManipulation(int n, List<List<Integer>> queries) {
       // return slowSolution(n, queries);
        return fastSolution(n,queries);
    }


    public static long fastSolution(int n, List<List<Integer>> queries) {
        long[] arr = new long[n + 1];
        for (List<Integer> query : queries) {
            int startIndex = query.get(0) - 1;
            int endIndex = query.get(1);
            int val = query.get(2);
            arr[startIndex] += val;
            arr[endIndex] -= val;
        }
        long current =0;
        long max = 0;
        for (int i = 0; i < n + 1; i++) {
            current += arr[i];

            if (current > max) {
                max = current;
            }
        }
        return max;

    }

    private static long slowSolution(int n, List<List<Integer>> queries) {
        long[] arr = new long[n];
        long max = Long.MIN_VALUE;
        for (List<Integer> query : queries) {
            int startIndex = query.get(0);
            int endIndex = query.get(1);
            int val = query.get(2);
            long currentMax = updateList(arr, startIndex, endIndex, Long.valueOf(val));
            if (currentMax > max) {
                max = currentMax;
            }
        }
        return max;
    }

    public static long updateList(long[] arr, int start, int end, Long val) {
        long currentMax = Long.MIN_VALUE;
        for (int i = start - 1; i < end; i++) {
            arr[i] = arr[i] + val;

            if (arr[i] > currentMax) {
                currentMax = arr[i];
            }
        }
        return currentMax;
    }


}

public class SolutionArrayManipulation {
    public static void main(String[] args) throws IOException {


        Support.createInOutFileIfNotExist();
        BufferedReader bufferedReader = new BufferedReader(Support.getIs());
        BufferedWriter bufferedWriter = new BufferedWriter(Support.getOs());

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int n = Integer.parseInt(firstMultipleInput[0]);

        int m = Integer.parseInt(firstMultipleInput[1]);

        List<List<Integer>> queries = new ArrayList<>();

        IntStream.range(0, m).forEach(i -> {
            try {
                queries.add(
                        Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                                .map(Integer::parseInt)
                                .collect(toList())
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        long result = ResultArrayManipulation.arrayManipulation(n, queries);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
