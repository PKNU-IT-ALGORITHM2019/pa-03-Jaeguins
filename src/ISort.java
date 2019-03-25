import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

@FunctionalInterface
interface Quick {
    void Sort(int[] input, int mod);
}

interface Sorting {
    void Sort(int[] input);
}

interface Pivot {
    int getPivot(int[] field,int left,int right);
}

public class ISort {
    public static void swap(int[] field, int i, int j) {
        int t = field[i];
        field[i] = field[j];
        field[j] = t;
    }
    public static LinkedList<int[]>queue=new LinkedList<>();
    public static Sorting bubbleSorting = (int[] array) -> {
        for (int i = array.length - 1; i > 0; --i)
            for (int j = 0; j < i; ++j)
                if (array[j] > array[j + 1])
                    swap(array, j, j + 1);
    };
    public static Sorting STLSorting = (int[] array) -> {
        Arrays.sort(array);
    };
    public static Sorting selectionSorting = (int[] array) -> {
        for (int i = 0; i < array.length - 1; ++i) {
            int imin = i;
            for (int j = i + 1; j <= array.length - 1; ++j)
                if (array[imin] > array[j]) imin = j;
            if (imin != i) swap(array, i, imin);
        }
    };
    public static Sorting insertionSorting = (int[] array) -> {
        for (int i = 0 + 1; i <= array.length - 1; i++) {
            int j, v = array[i];
            for (j = i; j > 0 && array[j - 1] > v; j--)
                array[j] = array[j - 1];
            if (i != j) array[j] = v;
        }
    };
    public static Pivot GetLastPivot = (int[] array,int left,int right) -> right;
    public static Pivot GetMiddlePivot = (int[] array,int left,int right) -> {
        if (array[left] > array[right])
            if (array[right] > array[right / 2]) return right;
            else if (array[left] > array[right / 2]) return right / 2;
            else return left;
        else if (array[left] > array[right / 2]) return left;
        else if (array[right] > array[right / 2]) return right / 2;
        else return right;
    };
    public static Pivot GetRandomPivot = (int[] array,int left,int right) -> MainClass.random.nextInt(right-left)+left;
    public static Sorting mergeSorting = (int[] array) -> {
        if (array.length < 2) return;
        int div = array.length / 2;
        int[] firstHalf = Arrays.copyOfRange(array, 0, div);
        int[] secondHalf = Arrays.copyOfRange(array, div, array.length);
        ISort.mergeSorting.Sort(firstHalf);
        ISort.mergeSorting.Sort(secondHalf);
        int j = 0, k = 0;
        for (int i = 0; i < array.length; i++) {
            array[i] =
                    j == firstHalf.length ? secondHalf[k++] :
                            k == secondHalf.length ? firstHalf[j++] :
                                    firstHalf[j] <= secondHalf[k] ? firstHalf[j++] : secondHalf[k++];
        }
    };
    public static Quick QuickSort = (int[] array, int mod) -> {
        while(!queue.isEmpty()) {
            int[] pair = queue.poll();
            int left = pair[0], right = pair[1];
            int pivot = array[
                mod == left ? GetLastPivot.getPivot(array,left,right) :
                    mod == 1 ? GetMiddlePivot.getPivot(array,left,right) :
                        GetRandomPivot.getPivot(array,left,right)];
            int leftP = left, rightP = right;
            do {
                while (array[leftP] < pivot) leftP++;
                while (array[rightP] > pivot) rightP--;
                if (leftP <= rightP) {
                    swap(array, leftP, rightP);
                    leftP++;
                    rightP--;
                }
            } while (leftP <= rightP);
            if (left < rightP){
                int[] leftPart={left,rightP};
                queue.offer(leftPart);
            }
            if (leftP < right){
                int[] rightPart={leftP,right};
                queue.offer(rightPart);
            }
        }
    };

    public static Sorting heapSorting = (int[] array) -> {
        int size = array.length - 1;
        for (int i = size / 2 - 1; i >= 0; i--)
            HeapInternal(array, i, size);
        while (--size >= 1) {
            swap(array, 0, size);
            HeapInternal(array, 0, size);
        }
    };

    public static void HeapInternal(int[] array, int index, int size) {
        for (int ch = (index << 1) | 1; ch < size; index = ch, ch = ch << 1 | 1) {
            if (ch + 1 < size && array[ch + 1] > array[ch]) ++ch;
            if (array[ch] <= array[index]) return;
            swap(array, ch, index);
        }
    }


    public static double printTime(Quick sort, int mod, int[] field) {
        long start = 0;
        start = System.currentTimeMillis();
        queue.clear();
        int[] pair={0,field.length-1};
        queue.offer(pair);
        sort.Sort(field, mod);
        return (double)(System.currentTimeMillis() - start)/1000;
    }

    public static double printTime(Sorting sorting, int[] field) {
        long start = 0;
        start = System.currentTimeMillis();
        sorting.Sort(field);
        return (double)(System.currentTimeMillis() - start)/1000;
    }
}

