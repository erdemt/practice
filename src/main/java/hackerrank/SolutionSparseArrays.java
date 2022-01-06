package hackerrank;

import support.Support;

import java.io.*;
import java.util.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

class ResultSparseArrays {

    public static List<Integer> matchingStrings(List<String> strings, List<String> queries) {
        return queries.stream().map(s->getCountInList(strings,s)).map(Long::intValue).collect(toList());
    }

    public static long getCountInList(List<String> strings,String query){
        return strings.stream().filter(s->s.equals(query)).count();
    }

}

public class SolutionSparseArrays {
    public static void main(String[] args) throws IOException {

        Support.createInOutFileIfNotExist();
        BufferedReader bufferedReader = new BufferedReader(Support.getIs());
        BufferedWriter bufferedWriter = new BufferedWriter(Support.getOs());

        int stringsCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<String> strings = IntStream.range(0, stringsCount).mapToObj(i -> {
                    try {
                        return bufferedReader.readLine();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                })
                .collect(toList());

        int queriesCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<String> queries = IntStream.range(0, queriesCount).mapToObj(i -> {
                    try {
                        return bufferedReader.readLine();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                })
                .collect(toList());

        List<Integer> res = ResultSparseArrays.matchingStrings(strings, queries);

        bufferedWriter.write(
                res.stream()
                        .map(Object::toString)
                        .collect(joining("\n"))
                        + "\n"
        );

        bufferedReader.close();
        bufferedWriter.close();
    }
}
