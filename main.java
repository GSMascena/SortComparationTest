import java.util.ArrayList;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Random;

public class main {

    public static void main(String[] args) {
        for (int i = 5; i <= 100; i += 5) System.out.println("Média de Comparações 50 vetores de tamanho " + i + " : " +testBubbleSort(generateArrays(i)));
    }

    public static ArrayList<int[]> generateArrays(int size){
        ArrayList<int[]> arrays = new ArrayList<>();
        for(int i = 0; i < 50; i++){
            int[] array = new int[size];
            for(int j = 0; j < size; j++){
                array[j] = (new Random()).nextInt(49) + 1;
            }
            arrays.add(array);
        }
        return arrays;
    }

    public static int testBubbleSort(ArrayList<int[]> arrays){
        int mean = 0;
        for(int[] array : arrays) mean += bubbleSort(array);
        return mean/50;
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

    public static int bubbleSort(int[] Array){
        int comparations = 0;
        for(int i = 0; i < Array.length; i++){
            for(int j = 0; j < (Array.length - 1 ); j++){
                if(Array[j] > Array[j + 1]){
                    comparations++;
                    int aux = 0;
                    aux = Array[j];
                    Array[j] = Array[j + 1];
                    Array[j + 1] = aux;
                }
            }
        }
        return comparations;
    }

    /*
    public static void bubbleSort(int[] Array){
        for(int i = 0; i < Array.length; i++){
            for(int j = 0; j < (Array.length - 1 ); j++){
                if(Array[j] > Array[j + 1]){
                    int aux = 0;
                    aux = Array[j];
                    Array[j] = Array[j + 1];
                    Array[j + 1] = aux;
                }
            }
        }
    }
     */

    public static void selectionSort(int[] Array){
        for(int i = 0; i < (Array.length - 1); i++){
            int index = i;
            for(int j = i + 1; j < Array.length; j++){
                if(Array[j] < Array[index]){
                    index = j;
                }
            }
            int numberSmall = Array[index];
            Array[index] = Array[i];
            Array[i] = numberSmall;
        }
    }
}