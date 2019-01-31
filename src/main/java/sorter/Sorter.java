
import java.util.Arrays;


public class Sorter {
    private int[] original;
    private int[] array;
    private int[] helper;
    private long timer;

    public Sorter(int[] array) {
        this.helper = new int[array.length];
        this.original = new int[array.length];
        this.array = array;
        timer = 0;

        for (int i = 0; i < array.length; i++) {
            original[i] = array[i]; 
        }

    }

    public void restoreArray() {
        for (int i = 0; i < array.length; i++) {
            array[i] = original[i];
        }
    }

    public void timeQuicksort(){
        timer = System.currentTimeMillis();
        quicksort(0, this.array.length -1);        
        printf("Quicksort took %s seconds", (double) (System.currentTimeMillis() - timer) / 1000);
    }

    public void timeMergeSort(){
        timer = System.currentTimeMillis();
        mergeSort();    
        printf("Merge sort took %s seconds", (double) (System.currentTimeMillis() - timer) / 1000);
    }

    public void timeArraysSort() {
        timer = System.currentTimeMillis();
        Arrays.sort(array);
        printf("Arrays.sort took %s seconds", (double) (System.currentTimeMillis() - timer) / 1000);
    }

    private void countingSort(int keys) {
        helper = new int[keys];
        for (int i = 0; i < array.length; i++) {
            helper[array[i]]++;
        }

        int pointer = 0;
        for (int i = 0; i < helper.length; i++) {
            if (helper[i] != 0) {
                for (int a = 0; a<helper[i]; a++) {
                    this.array[pointer] = i;
                    pointer++;
                }
            }
        }
    }

    private void mergeSort() {
        helper = new int[array.length];

        int partitionLength, start, leftStart, leftEnd, rightEnd;
        partitionLength = 1;

        // Käy läpi eri kokoiset osataulukot esim 8 kokoisella taulukolla käy läpi koot 1, 2, 4
        while (partitionLength < array.length) {
            start = 0;

            // Lomittaa erimitkeskitaiset osataulukot. start osoittaa vasemman puolista pituuden (1, 2, 4) mittaista pätkää mitä tutkitaan
            while (start + partitionLength < array.length) {
                leftStart = start;
                leftEnd = start + partitionLength -1;
                if (leftEnd + partitionLength < array.length -1) {
                    rightEnd = leftEnd + partitionLength;
                } else {
                    rightEnd = array.length -1;
                }

                merge(leftStart, leftEnd, leftEnd + 1, rightEnd);
                start = start + 2 * partitionLength;
            }
            partitionLength = 2 * partitionLength;
        }

    }

    private void merge(int leftStart, int leftEnd, int rightStart, int rightEnd) {
        int a = leftStart;
        int b = rightEnd;
        for (int i = a; i <=b; i++) {
            if (rightStart > rightEnd || (leftStart <= leftEnd && array[leftStart] < array[rightStart])) {
                helper[i] = array[leftStart];
                leftStart++;
            } else {
                helper[i] = array[rightStart];
                rightStart++;
            }
        }
        for (int i = a; i <=b; i++) {
           array[i] = helper[i];
        }
        

    }

    private void quicksort(int left, int right) {
        if (left >= right) {
            return;
        }
        int k = pivot(left, right);
        quicksort(left, k);
        quicksort(k+1, right);
    }
  
    private int pivot(int a, int b){
        int k = a;
        for (int i = a + 1; i < b + 1; i++) {
            if (array[i] < array[a]) {
                k++;
                if (i != k) {
                    swap(i, k);
                }
            }
        }
        swap(k, a);
        return k;
    }

    private void swap(int a, int b) {
        int tempB = array[b];
        array[b] = array[a];
        array[a] = tempB;

    }

    private static void printf(String string, Object...args) {
        System.out.println(String.format(string, args));
    }
}


