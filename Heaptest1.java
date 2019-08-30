package cs21as02;

import java.util.Scanner;
import java.util.Arrays;

class Heaptest1 {
    public static void main(String[] args) {
        HeapQ test = new HeapQ(2400000);
        Scanner stdin = new Scanner(System.in);
        while(stdin.hasNextInt() && stdin.hasNextLine()) {
            int x = stdin.nextInt();
            if (x == -1) {
                System.out.println("extract min: " + test.extractMin());
            } else if (x == -2) {
                int[] temp = test.heapsort();
                System.out.println("sorted array: " + Arrays.toString(temp));
            } else if (x == 0) {
                System.out.println(test.toString());
            } else if (x > 0) {
                System.out.println("insert: " + x);
                test.heapinsert(x);
            }

        }
    }

}
