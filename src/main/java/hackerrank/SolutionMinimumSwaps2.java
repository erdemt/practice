package hackerrank;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class SolutionMinimumSwaps2 {

    static int minimumSwaps(int[] arr) {
        int count = 0;
        for(int i=0;i<arr.length;i++){
            if(arr[i]==i+1) continue;
            count++;
            int tmp = arr[i];
            arr[i] = arr[tmp-1];
            arr[tmp-1] = tmp;
            i--;
        }
        return count;
    }

}
