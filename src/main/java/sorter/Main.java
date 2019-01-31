

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        int n = 10000000;
        int[] array = new int[n];
        for (int i = 0; i<n; i++) {
            array[i] = new Random().nextInt(n) + 1;
        }

        Sorter j = new Sorter(array);

        j.timeMergeSort();
        j.restoreArray();
        j.timeQuicksort();
        j.restoreArray();
        j.timeArraysSort();
    }

    public static void printf(String string, Object...args) {
        System.out.println(String.format(string, args));
    }
}