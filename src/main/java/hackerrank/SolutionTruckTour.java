package hackerrank;

import support.Support;

import java.io.*;
import java.util.*;
import java.util.stream.*;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

class ResultTruckTour {

    public static final int DISTANCE = 0;
    public static final int AMOUNT = 1;

    public static int truckTour(List<List<Integer>> petrolPumps) {
        Node circle = createCircle(petrolPumps);
        int gas = 0;
        Node start = circle;
        Node end = circle;
        do {
            gas += start.amount - start.distance;
            if (gas < 0) {
                start = end.nextPump;
                end = end.nextPump;
                gas = 0;
            }
            start = start.nextPump;
        } while ((start.nextPump.indx != end.nextPump.indx));

        return start.nextPump.indx;
    }

    private static Node createCircle(List<List<Integer>> petrolPumps) {
        Node nextNode = null;
        Node lastNode = null;
        for (int i = petrolPumps.size() - 1; i >= 0; i--) {
            List<Integer> petrolPump = petrolPumps.get(i);
            Node n = new Node(nextNode, i, petrolPump.get(AMOUNT), petrolPump.get(DISTANCE));
            if (nextNode == null) {
                lastNode = n;
            }
            nextNode = n;
            if (i == 0) {
                lastNode.setNextPump(n);
            }
        }
        return lastNode.nextPump;
    }


    static class Node {
        Node nextPump;
        int indx;
        int distance;
        int amount;

        public Node(Node nextPump, int indx, int distance, int amount) {
            this.nextPump = nextPump;
            this.indx = indx;
            this.distance = distance;
            this.amount = amount;
        }

        public void setNextPump(Node nextPump) {
            this.nextPump = nextPump;
        }


        @Override
        public String toString() {
            return indx + "";
        }
    }

}


public class SolutionTruckTour {
    public static void main(String[] args) throws IOException {
        Support.createInOutFileIfNotExist();
        BufferedReader bufferedReader = new BufferedReader(Support.getIs());
        BufferedWriter bufferedWriter = new BufferedWriter(Support.getOs());

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        List<List<Integer>> petrolpumps = new ArrayList<>();

        IntStream.range(0, n).forEach(i -> {
            try {
                petrolpumps.add(
                        Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                                .map(Integer::parseInt)
                                .collect(toList())
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        int result = ResultTruckTour.truckTour(petrolpumps);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
