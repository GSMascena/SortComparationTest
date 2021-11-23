import java.util.Arrays;

public class main {

    public static void main(String[] args) {
        int[] array = {5, 2, 4, 3, 0, 9, 7, 8, 1, 6};
        System.out.println(Arrays.toString(array));
        SelectionSort(array);
        System.out.println(Arrays.toString(array));
    }

    public static void MergeSort(int[] Array, int Start, int End) {
        int mid = (Start + End) / 2;

        if (Start < (End - 1)) {
            MergeSort(Array, Start, mid);
            MergeSort(Array, mid, End);

            Merge(Array, Start, mid, End);
        }
    }

    public static void Merge(int[] Array, int Start, int Mid, int End) {
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

    public static void BubbleSort(int[] Array){
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

    public static void SelectionSort(int[] Array){
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