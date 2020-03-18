import java.util.Random;

public class QuickSortTest {
    private static void quickSort(long[] array, int left, int right) {
        if (left >= right) {
            return;
        }
        int pos = partition(array, left, right);
        quickSort(array, left, pos - 1);
        quickSort(array, pos + 1, right);
    }
    
    private static int partition(long[] array, int left, int right) {
        int pivot = left + (int) (Math.random() * (right - left));
        if (pivot != left) {
            swap(array, pivot, left);
        }
        long p = array[left];
        
        int i = left + 1;
        for (int j = left + 1; j <= right; j++) {
            if (array[j] < p) {
                swap(array, j, i);
                i++;
            }
        }
        swap(array, left, i - 1);
        return i - 1;
    }
    
    private static void swap(long[] a, int x, int y) {
        if (x == y) {
            return;
        }
        a[x] = a[x] ^ a[y];
        a[y] = a[x] ^ a[y];
        a[x] = a[x] ^ a[y];
    }
    
    public static void QuickSort(long[] list, int l, int h) {
        if (l < h) {
            int p = Partition(list, l, h);
            QuickSort(list, l, p - 1);
            QuickSort(list, p + 1, h);
        }
    }
    
    public static int Partition(long[] list, int l, int h) {
        int p = l + (int) (Math.random() * (h - l));
        long pivot = list[p];
        swap(list, p, l);
        int lo = l;
        for (int i = l + 1; i < h + 1; i++) {
            if (list[i] < pivot) {
                swap(list, i, ++lo);
            }
        }
        swap(list, l, lo);
        return lo;
    }
    
    public static void generateList(int n, long[] list) {
        for (int i = 0; i < n; i++) {
            list[i] = i;
        }
        // shuffle
        Random rnd = new Random();
        for (int i = list.length; i > 1; i--) {
            int j = rnd.nextInt(i);
            long temp = list[j];
            list[j] = list[i - 1];
            list[i - 1] = temp;
        }
    }
    
    public static void main(String[] args) {
        long[] test = new long[100000];
        generateList(100000, test);
        long startTime, stopTime;
        startTime = System.currentTimeMillis();
        quickSort(test, 0, test.length - 1);
        stopTime = System.currentTimeMillis();
        System.out.println(stopTime - startTime);
//        for (int i = 0; i < test.length; i++) {
//            System.out.print(test[i] + " ");
//        }
        System.out.println();
        generateList(100000, test);
        startTime = System.currentTimeMillis();
        QuickSort(test, 0, test.length - 1);
        stopTime = System.currentTimeMillis();
        System.out.println(stopTime - startTime);
//        for (int i = 0; i < test.length; i++) {
//            System.out.print(test[i] + " ");
//        }
    }
}
