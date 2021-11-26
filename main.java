import java.util.ArrayList;
import java.util.Random;

public class main {

    public static void main(String[] args) {
        for (int i = 5; i <= 100; i += 5)
            System.out.println(
                    "Média de Comparações 50 vetores de tamanho " + i + " : " + testSortMethod(generateArrays(i), "bubbleSort"));
    }

    public static ArrayList<int[]> generateArrays(int size) {
        ArrayList<int[]> arrays = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            int[] array = new int[size];
            for (int j = 0; j < size; j++) {
                array[j] = (new Random()).nextInt(49) + 1;
            }
            arrays.add(array);
        }
        return arrays;
    }

    public static int testSortMethod(ArrayList<int[]> arrays, String method) {
        int mean = 0;
        switch (method) {
            case "bubbleSort":
                for (int[] array : arrays) mean += bubbleSort(array);
                break;
            case "selectionSort":
                for (int[] array : arrays) mean += selectionSort(array);
                break;
            case "insertionSort":
                for (int[] array : arrays) mean += insertionSort(array);
                break;
            case "mergeSort":
                break;
            case "quickSort":
                break;
        }
        return mean / 50;
    }

    public static int bubbleSort(int[] array) {
        int comparisons = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < (array.length - 1); j++) {
                if (array[j] > array[j + 1]) {
                    comparisons++;
                    int aux = 0;
                    aux = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = aux;
                }
            }
        }
        return comparisons;
    }

    public static int selectionSort(int[] array) {
        int comparisons = 0;
        for (int i = 0; i < (array.length - 1); i++) {
            int index = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < array[index]) {
                    comparisons++;
                    index = j;
                }
            }
            int numberSmall = array[index];
            array[index] = array[i];
            array[i] = numberSmall;
        }
        return comparisons;
    }

    public static int insertionSort(int[] array) {
        int comparisons = 0;
        for (int i = 1; i <= (array.length - 1); i++) {
            int numberSmall = array[i];
            int j = i - 1;
            while ((j > -1) && (array[j] > numberSmall)) {
                comparisons++;
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = numberSmall;
        }
        return comparisons;
    }

    public static void heapSort(int[] array){
        int n = array.length;

        for (int i = n / 2 - 1; i >= 0; i--)
            heapify(array, n, i);

        for (int i = n - 1; i > 0; i--){
            int temp = array[0];
            array[0] = array[i];
            array[i] = temp;

            heapify(array, i, 0);
        }
    }

    public static void heapify(int array[], int n, int i){
        int largest = i;
        int l = 2 * i + 1;
        int r = 2 * i + 2;

        if (l < n && array[l] > array[largest])
            largest = l;
        if (r < n && array[r] > array[largest])
            largest = r;
        if (largest != i){
            int swap = array[i];
            array[i] = array[largest];
            array[largest] = swap;

            heapify(array, n, largest);
        }
    }

    public static void mergeSort(int[] Array, int Start, int End) {
        int mid = (Start + End) / 2;

        if (Start < (End - 1)) {
            mergeSort(Array, Start, mid);
            mergeSort(Array, mid, End);

            merge(Array, Start, mid, End);
        }
    }

    public static void merge(int[] Array, int Start, int Mid, int End) {
        int[] sortArray = new int[End - Start];
        int start = Start;
        int mid = Mid;
        int position = 0;

        while (start < Mid && mid < End) {
            if (Array[start] >= Array[mid]) {
                sortArray[position] = Array[start];
                position++;
                start++;
            } else {
                sortArray[position] = Array[mid];
                position++;
                mid++;
            }
        }
        while (start < Mid) {
            sortArray[position] = Array[start];
            position++;
            start++;
        }
        while (mid < End) {
            sortArray[position] = Array[mid];
            position++;
            mid++;
        }
        for (position = 0, start = Start; start < End; start++, position++) {
            Array[start] = sortArray[position];
        }
    }

    public static void quickSort(int[] array) {
        recursiveQuickSort(array, 0, array.length - 1);
    }

    public static void recursiveQuickSort(int[] array, int startIdx, int endIdx) {
        int idx = partition(array, startIdx, endIdx);
        if (startIdx < idx - 1) {
            recursiveQuickSort(array, startIdx, idx - 1);
        }
        if (endIdx > idx) {
            recursiveQuickSort(array, idx, endIdx);
        }
    }

    public static int partition(int[] array, int left, int right) {
        int pivot = array[left];
        while (left <= right) {
            while (array[left] > pivot) {
                left++;
            }
            while (array[right] < pivot) {
                right--;
            }
            if (left <= right) {
                int tmp = array[left];
                array[left] = array[right];
                array[right] = tmp;
                left++;
                right--;
            }
        }
        return left;
    }
}