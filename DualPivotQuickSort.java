import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class DualPivotQuickSort {

    private static final int INSERTION_SORT_THRESHOLD = 27;
    private static final int SAMPLE_THRESHOLD = 64;
    private static final int STACK_FRAME_SIZE_BYTES = 64;

    private static long comparisons = 0;
    private static int currentDepth = 0;
    private static int maxDepth = 0;

    public static void sort(int[] arr) {
        if (arr == null || arr.length < 2) return;
        quickSort(arr, 0, arr.length - 1);
    }

    private static void quickSort(int[] arr, int low, int high) {

        while (low < high) {

            currentDepth++;
            maxDepth = Math.max(maxDepth, currentDepth);

            if (high - low < INSERTION_SORT_THRESHOLD) {
                insertionSort(arr, low, high);
                currentDepth--;
                return;
            }

            if (high - low + 1 >= SAMPLE_THRESHOLD) {
                selectPivots(arr, low, high);
            }

            if (arr[low] > arr[high]) {
                swap(arr, low, high);
            }

            int pivot1 = arr[low];
            int pivot2 = arr[high];

            int lt = low + 1;
            int gt = high - 1;
            int i = lt;

            while (i <= gt) {
                comparisons++;

                if (arr[i] < pivot1) {
                    swap(arr, i++, lt++);
                } else if (arr[i] > pivot2) {
                    swap(arr, i, gt--);
                } else {
                    i++;
                }
            }

            swap(arr, low, --lt);
            swap(arr, high, ++gt);

            int leftSize = lt - low;
            int middleSize = gt - lt - 1;
            int rightSize = high - gt;

            // recurse smaller partitions first (stack optimization)
            if (leftSize < middleSize && leftSize < rightSize) {
                quickSort(arr, low, lt - 1);
                quickSort(arr, lt + 1, gt - 1);
                low = gt + 1;
            } else if (middleSize < rightSize) {
                quickSort(arr, lt + 1, gt - 1);
                quickSort(arr, gt + 1, high);
                high = lt - 1;
            } else {
                quickSort(arr, gt + 1, high);
                quickSort(arr, low, lt - 1);
                low = lt + 1;
                high = gt - 1;
            }

            currentDepth--;
        }
    }

    private static void insertionSort(int[] arr, int low, int high) {
        for (int i = low + 1; i <= high; i++) {
            int key = arr[i];
            int j = i - 1;

            while (j >= low && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }

            arr[j + 1] = key;
        }
    }

    private static void selectPivots(int[] arr, int low, int high) {
        int length = high - low + 1;

        int q1 = low + length / 4;
        int mid = low + length / 2;
        int q3 = low + (3 * length) / 4;

        sort3(arr, q1, mid, q3);

        swap(arr, q1, low);
        swap(arr, q3, high);
    }

    private static void sort3(int[] arr, int a, int b, int c) {
        if (arr[a] > arr[b]) swap(arr, a, b);
        if (arr[b] > arr[c]) swap(arr, b, c);
        if (arr[a] > arr[b]) swap(arr, a, b);
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private static int[] generateArray(int size) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = ThreadLocalRandom.current().nextInt(size);
        }
        return arr;
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter array size: ");
        int size = scanner.nextInt();
        scanner.close();

        int[] arr = generateArray(size);

        long startTime = System.nanoTime();
        sort(arr);
        long endTime = System.nanoTime();

        long executionTimeNs = endTime - startTime;
        long estimatedStackBytes = (long) maxDepth * STACK_FRAME_SIZE_BYTES;

        System.out.println("\nResults:");
        System.out.println("Execution time (ns): " + executionTimeNs);
        System.out.println("Comparisons: " + comparisons);
        System.out.println("Max recursion depth: " + maxDepth);
        System.out.println("Estimated stack usage (bytes): " + estimatedStackBytes);
    }
}