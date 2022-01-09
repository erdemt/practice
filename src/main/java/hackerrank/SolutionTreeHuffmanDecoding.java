package hackerrank;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SolutionTreeHuffmanDecoding {

    void decode(String s, Node root) {
        char[] chArr = s.toCharArray();
        String result = "";
        List<Integer> selected = new ArrayList<>();
        for (int i = 0; i < chArr.length; i++) {
            int path = toInt(chArr[i]);
            selected.add(path);
            Optional<String> opt = getChar(root, selected);
            if (opt.isPresent()) {
                selected.clear();
                result += opt.get();
            }
        }
        System.out.println(result);
    }

    public static Optional<String> getChar(Node root, List<Integer> selected) {
        Node myRoot = root;
        for (Integer integer : selected) {
            myRoot = isLeft(integer) ? myRoot.left : myRoot.right;
            if (isLeaf(myRoot)) {
                return Optional.of(myRoot.data + "");
            }
        }
        return Optional.empty();
    }

    public static boolean isLeaf(Node node) {
        return node.left == null && node.right == null;
    }

    public static int toInt(char c) {
        return Integer.valueOf(c + "");
    }

    public static boolean isLeft(int i) {
        return i == 0;
    }


    //boilerplate
    abstract class Node implements Comparable<Node> {
        public  int frequency; // the frequency of this tree
        public  char data;
        public  Node left, right;
        public Node(int freq) {
            frequency = freq;
        }

        // compares on the frequency
        public int compareTo(Node tree) {
            return frequency - tree.frequency;
        }
    }

    class HuffmanLeaf extends Node {


        public HuffmanLeaf(int freq, char val) {
            super(freq);
            data = val;
        }
    }

    class HuffmanNode extends Node {

        public HuffmanNode(Node l, Node r) {
            super(l.frequency + r.frequency);
            left = l;
            right = r;
        }

    }


}
