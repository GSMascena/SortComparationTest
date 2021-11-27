import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Random;

public class main {

    public static void main(String[] args) {
        compareAllMethods(1000);
    }

    public static ArrayList<int[]> generateArrays(int size) {
        ArrayList<int[]> arrays = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            int[] array = new int[size];
            for (int j = 0; j < size; j++) {
                array[j] = (new Random()).nextInt(9999) + 1;
            }
            arrays.add(array);
        }
        return arrays;
    }

    public static int testSortMethod(ArrayList<int[]> arrays, int methodNumber) {
        int mean = 0;
        switch (methodNumber) {
            case 1:
                for (int[] array : arrays) mean += bubbleSort(array);
                System.out.print("BubbleSort : ");
                break;
            case 2:
                for (int[] array : arrays) mean += selectionSort(array);
                System.out.print("SelectionSort : ");
                break;
            case 3:
                for (int[] array : arrays) mean += insertionSort(array);
                System.out.print("InsertionSort : ");
                break;
            case 4:
                for (int[] array : arrays) mean += mergeSort(array, 0, array.length-1);
                System.out.print("MergeSort : ");
                break;
            case 5:
                for (int[] array : arrays) mean += quickSort(array);
                System.out.print("QuickSort : ");
                break;
            case 6:
                for (int[] array : arrays) mean += countSort(array);
                System.out.print("CountSort : ");
                break;
            case 7:
                for (int[] array : arrays) mean += radixSort(array);
                System.out.print("RadixSort : ");
                break;
            default:
                mean = -50;
                break;
        }
        return mean / 50;
    }

    public static void compareAllMethods (int arraySize){
        ArrayList<int[]> arrayList;
        int result = 0, methodNumber = 0;
        do {
            methodNumber++;
            arrayList = generateArrays(arraySize);
            result = testSortMethod(arrayList, methodNumber);
            if(result >=0) System.out.println(result);
        } while (result >= 0);
    }

    //Bubble
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

    //Selection
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

    //Insertion
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

    //Heap
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

    //Merge
    public static int mergeSort(int array[], int start, int end)
    {
        int comparison = 0;
        if (start < end) {
            int middle =start+ (end-start)/2;

            comparison += mergeSort(array, start, middle);
            comparison += mergeSort(array, middle + 1, end);

            comparison += merge(array, start, middle, end);
        }
        return comparison;
    }

    public static int merge(int array[], int start, int middle, int end)
    {
        int comparison = 0;

        int n1 = middle - start + 1;
        int n2 = end - middle;

        int Start[] = new int[n1];
        int End[] = new int[n2];

        for (int i = 0; i < n1; ++i)
            Start[i] = array[start + i];
        for (int j = 0; j < n2; ++j)
            End[j] = array[middle + 1 + j];

        int i = 0, j = 0;

        int k = start;
        while (i < n1 && j < n2) {
            if (Start[i] <= End[j]) {
                comparison++;
                array[k] = Start[i];
                i++;
            }
            else {
                array[k] = End[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            array[k] = Start[i];
            i++;
            k++;
        }

        while (j < n2) {
            array[k] = End[j];
            j++;
            k++;
        }
        return comparison;
    }

    //Quick
    public static int quickSort(int[] array) {
        return recursiveQuickSort(array, 0, array.length - 1);
    }

    public static int recursiveQuickSort(int[] array, int startIdx, int endIdx) {
        int comparisons = 0;
        int[] result = partition(array, startIdx, endIdx);
        int idx = result[0];
        comparisons += result[1];
        if (startIdx < idx - 1) {
            comparisons += recursiveQuickSort(array, startIdx, idx - 1);
        }
        if (endIdx > idx) {
            comparisons += recursiveQuickSort(array, idx, endIdx);
        }
        return comparisons;
    }

    public static int[] partition(int[] array, int left, int right) {
        int pivot = array[left];
        int comparison = 0;
        while (left <= right) {
            comparison++;
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
        int[] result = {left, comparison};
        return result;
    }

    //Count
    public static int getMax(int[] Array) {
        int max = Array[0];
        for (int i = 1; i < Array.length; i++)
            if (Array[i] > max) {
                max = Array[i];
            }

        return max;
    }

    public static int[] countElementSort(int[] Array){
        int[] newArray = new int[getMax(Array) + 1];
        Arrays.fill(newArray, 0);

        for(int i : Array){
            newArray[i] += 1;
        }

        for(int i = 1; i < newArray.length; i++){
            newArray[i] += newArray[i - 1];
        }

        return newArray;
    }

    public static int countSort(int[] Array){
        int[] newArray = countElementSort(Array);
        int[] sort = new int[Array.length];

        int comparations = 0;

        for(int i = Array.length - 1; i >= 0; i--){
            comparations++;
            int current = Array[i];
            sort[newArray[current] - 1] = current;
            newArray[current] -= 1;
        }

        return comparations;
    }

    //Radix
    public static int radixCountSort(int[] Array, int digit){
        int arrayPivo[] = new int[Array.length];
        int count[] = new int[10];
        Arrays.fill(count, 0);

        int comparison = 0;

        for(int i = 0; i < Array.length; i++){
            comparison++;
            count[(Array[i]/digit) % 10]++;
        }

        for(int i = 1; i < 10; i++)
            count[i] += count[i - 1];

        for(int i = Array.length - 1; i >= 0; i--){
            arrayPivo[count[(Array[i] / digit) % 10] - 1] = Array[i];
            count[(Array[i] / digit) % 10]--;
        }

        for(int i = 0; i < Array.length; i++){
            Array[i] = arrayPivo[i];
        }
        return comparison;
    }

    public static int radixSort(int[] Array){
        int comparison = 0;

        int max = getMax(Array);

        for(int i = 1; max / i > 0; i *= 10)
            comparison += radixCountSort(Array, i);

        return comparison;
    }

}