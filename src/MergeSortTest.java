import java.util.Random;

public class MergeSortTest {
    public static void Merge(long[] arr, int l, int m, int r) {
        // Find sizes of two subarrays to be merged
        int n1 = m - l + 1;
        int n2 = r - m;
        /* Create temp arrays */
        long[] L = new long[n1];
        long[] R = new long[n2];
        /*Copy data to temp arrays*/
        for (int i = 0; i < n1; ++i)
            L[i] = arr[l + i];
        for (int j = 0; j < n2; ++j)
            R[j] = arr[m + 1 + j];
        
        /* Merge the temp arrays */
        // Initial indexes of first and second subarrays
        int i = 0, j = 0;
        
        // Initial index of merged subarry array
        int k = l;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                i++;
            } else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }
        /* Copy remaining elements of L[] if any */
        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }
        /* Copy remaining elements of R[] if any */
        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }
    
    // Main function that sorts arr[l..r] using
    // merge()
    public static void MergeSort(long[] arr, int l, int r) {
        if (l < r) {
            // Find the middle point
            int m = (l + r) / 2;
            // Sort first and second halves
            MergeSort(arr, l, m);
            MergeSort(arr, m + 1, r);
            // Merge the sorted halves
            Merge(arr, l, m, r);
        }
    }
    
    public static void merge(long[] A, int l, int p, int r) {
        int n1 = p - l + 1;
        int n2 = r - p;
        long[] B = new long[n1];
        for (int i = 0; i < n1; i++) {
            B[i] = A[l + i];
        }
        long[] C = new long[n2];
        for (int i = 0; i < n2; i++) {
            C[i] = A[p + i + 1];
        }
        int n = r - l + 1;
        int i = 0, j = 0;
        for (int k = l; k < l+n; k++) {
            if (i < n1 && j < n2) {
                if (B[i] <= C[j]){
                    A[k] = B[i++];
                } else{
                    A[k] = C[j++];
                }
            }else if (i < n1){
                A[k] = B[i++];
            }else if (j < n2){
                A[k] = C[j++];
            }
        }
    }
    
    public static void mergeSort(long[] A, int l, int r) {
        if (l < r) {
            int p = (l + r) / 2;
            mergeSort(A, l, p);
            mergeSort(A, p + 1, r);
            merge(A, l, p, r);
        }
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
        int n = 1000000;
        long[] test = new long[n];
        generateList(n, test);
        long startTime, stopTime;
        startTime = System.currentTimeMillis();
        MergeSort(test, 0, n - 1);
        stopTime = System.currentTimeMillis();
        System.out.println(stopTime - startTime);
    
        for (long a :
                test) {
            System.out.print(a + " ");
        }
        System.out.println();
        generateList(n, test);
        startTime = System.currentTimeMillis();
        mergeSort(test, 0, n - 1);
        stopTime = System.currentTimeMillis();
        System.out.println(stopTime - startTime);
        for (long a :
                test) {
            System.out.print(a + " ");
        }
    }
}
