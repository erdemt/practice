package hackerrank;

import support.Support;

import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

class ResultDowntoZero2 {


    static int SQRT_OF_N;
    static Set<Integer> TRIED_BEFORE = new HashSet<>();

    public static int downToZero(int n) {
        SQRT_OF_N = (int) Math.sqrt(n);
        TRIED_BEFORE.clear();
        Node node = new Node(n, 0);
        Queue<Node> q = new ArrayDeque<>();
        q.add(node);
        return getCount(q);
    }

    private static int getCount(Queue<Node> q) {
        Node node = q.poll();
        node.fillChildren();
        if (node.val == 0) {
            return node.count;
        } else {
            for (Node child : node.children) {
                if (child.val == 0) {
                    return child.count;
                }
                q.add(child);
            }
        }
        if (q.isEmpty()) {
            return node.count;
        }
        return getCount(q);
    }


    static class Node {
        int val;
        int count;
        List<Node> children = new ArrayList<>();

        public Node(int val, int count) {
            this.val = val;
            this.count = count;
        }

        public Node fillChildren() {
            if (val == 0) {
                return this;
            }
            Set<Integer> options = getOptions(val);
            options.add(val - 1);
            options.removeAll(TRIED_BEFORE);
            for (Integer option : options) {
                TRIED_BEFORE.add(option);
                children.add(new Node(option, count + 1));
            }
            return this;
        }

        public Set<Integer> getOptions(int n) {
            Set<Integer> result = new HashSet<>();

            int inc = 1;
            int start = 2;
            if (n % 2 != 0) {
                inc = 2;
                start = 1;
            }
            int other;
            for (int i = start; i <= SQRT_OF_N; i = i + inc) {
                if (i == 1) continue;
                if (n % i == 0) {
                    other = n / i;
                    result.add(Math.max(other, i));
                }
            }
            return result;
        }

    }


}


public class SolutionDowntoZero2 {
    public static void main(String[] args) throws IOException {
        Support.createInOutFileIfNotExist();
        BufferedReader bufferedReader = new BufferedReader(Support.getIs());
        BufferedWriter bufferedWriter = new BufferedWriter(Support.getOs());

        int q = Integer.parseInt(bufferedReader.readLine().trim());
        IntStream.range(0, q).forEach(qItr -> {
            try {
                int n = Integer.parseInt(bufferedReader.readLine().trim());

                int result = ResultDowntoZero2.downToZero(n);

                bufferedWriter.write(String.valueOf(result));
                bufferedWriter.newLine();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        bufferedReader.close();
        bufferedWriter.close();
    }
}
