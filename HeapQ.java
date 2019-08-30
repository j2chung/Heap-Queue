package cs21as02;

import java.util.Arrays;
import java.io.Serializable;

class HeapQ implements Serializable{

    public HeapQ(int cap){
        capacity = cap;
        size = 0;
        A = new int[capacity];
    }

    public int[] heapsort(){
        //preserve your array and size. make a new array and copy if necessary
        HeapQ B = new HeapQ(size);
        B.A = Arrays.copyOf(A, size);
        B.size = size;
        for (int i = B.size - 1; i > 0; i--) {
            B.swap(i , 0);
            B.size--;
            B.heapify(0);
        }
        return B.A;
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        if (!isEmpty()){
            sb.append("heap size ").append(size).append(": ").append(A[0]);
            for(int i = 1; i < size; i++) {
                sb.append(", ").append(A[i]);
            }
            return sb.toString();
        }
        return sb.append("heap size 0:").toString();
    }


    public boolean heapinsert(int n){
        if (isFull()) { //capacity bounds check
            return false;
        }
        size++;
        A[size - 1] = n - 1;
        decreaseKey(size - 1, n);
        return true;
    }

    public int minimum(){
        if (isEmpty()) {
            return 0;
        }
        return A[0];
    } // A[root] or 0 if empty

    public int extractMin(){
        if (!isEmpty()) {
            int min = A[0];
            A[0] = A[size - 1];
            size--;
            heapify(0);
            return min;
        } else {
            return 0;
        }
        //ERROR
    } // A[root] or 0

    public void decreaseKey(int i, int k){
        //bounds check on i
        if (A[i] > k) {
            A[i] = k;
            while (i > 0 && A[parent(i)] > A[i]) {
                swap(i, parent(i));
                i = parent(i);
            }
        }
    }

    public boolean isEmpty(){
        if (size == 0) {
            return true;
        }
        return false;
    }

    public boolean isFull(){
        if (size >= capacity) {
            return true;
        }
        return false;
    }

    private void buildheap(){
        for (int i = (size - 2)/2; i > 0; i--)
            heapify(i);
    }

    private int minOf3(int i, int j, int k){
        if (i > size) {
            return -1;
        }
        if (i == size) {
            return i;
        }
        if (j > size - 1) {
            return i;
        }
        if (k > size - 1) {
            int x = Math.min(A[i], A[j]);
            if (A[i] == x) {
                return i;
            } else if (A[j] == x) {
                return j;
            }
        }
        int x = Math.min(A[i], Math.min(A[j], A[k]));
        if (A[i] == x) {
            return i;
        } else if (A[j] == x) {
            return j;
        } else {
            return k;
        }
    }

    private int left(int i){
        return (2 * i) + 1;
    }

    private int right(int i){
        return (2 * i) + 2;
    }

    private int parent(int i){
        return (i - 1)/2;
    }

    private void heapify(int i){
        int n = minOf3(i, left(i), right(i));
        if (n != i) {
            swap (i,n);
            heapify(n);
        }
    } // at position i

    private void swap(int fp, int sp) {
        int temp;
        temp = A[fp];
        A[fp] = A[sp];
        A[sp] = temp;
    }

    private int [] A;
    private int capacity; // size of array A
    private int size; // size of data in array A
}
