import java.util.*;

import java.io.File;
import java.lang.reflect.Method;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class RunningTimeSurvey {
    // task name function name run times upper
    static String[][] taskList = {
            {"LinearTimeTest", "linearTime", "100000000"},
            {"LinearTimeTest", "linearTimeCollections", "10000000"},
            
            {"NlognTimeTest", "", ""},
            {"QuickSortTimeTest", "QuickSortTime", "100000000"},
            {"MergeSortTimeTest", "MergeSortTime", "100000000"},
            {"HeapSortTimeTest", "HeapSortTime", "10000000"},
            {"ArraysSortTimeTest", "ArraysSortTime", "100000000"},
            {"QuadraticTimeTest", "QuadraticTime", "100000"},
            {"bubbleSortTimeTest", "bubbleSortTime", "100000"},
            {"insertionSortTimeTest", "insertionSortTime", "100000"},
            {"SelectionSortTimeTest", "SelectionSortTime", "100000"},
            {"CubicTimeTest", "CubicTime", "1000"},
            {"ExponentialTimeTest", "ExponentialTime", "10"},
            {"FactorialTimeTest", "FactorialTime", "10"}
    };
    
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        String osName = System.getProperty("os.name");
        System.out.println(osName);
        try {
            File xlsFile = new File("RunningTimeSurvey.xls");
            // Create a workbook
            WritableWorkbook workbook;
            workbook = Workbook.createWorkbook(xlsFile);
            
            // Create a worksheet
            WritableSheet sheet = workbook.createSheet("RunningTime", 0);
            // the first row
            for (int j = 1, n = 1; j <= 8; j++) {
                n = 10 * n;
                sheet.addCell(new Label(j + 1, 0, "n = " + n));
            }
            for (int i = 0; i < taskList.length; i++) {
                String[] taskInfo = taskList[i];
                if (taskInfo[0].equals("NlognTimeTest")) {
                    sheet.addCell(new Label(0, i + 1, taskInfo[0]));
                    continue;
                }
                System.out.print("Processing ");
                System.out.println(taskInfo[0]);
                Class<?> me = Class.forName(Thread.currentThread().getStackTrace()[1].getClassName());
                Method method = me.getMethod(taskInfo[1], int.class);
                int upper = Integer.parseInt(taskInfo[2]);
                sheet.addCell(new Label(0, i + 1, taskInfo[0]));
                sheet.addCell(new Label(1, i + 1, taskInfo[1]));
                for (int j = 1, n = 1; Math.pow(10, j) <= upper; j++) {
                    n = 10 * n;
                    Long time = (Long) method.invoke(null, n);
                    // 向工作表中添加数据
                    sheet.addCell(new Label(j + 1, i + 1, time.toString()));
                }
            }
            workbook.write();
            workbook.close();
            
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    public static long linearTimeCollections(int n) {
        ArrayList<Long> arrayList = new ArrayList<Long>(n);
        generateArrayList(n, arrayList);
        long timeStart = System.currentTimeMillis();
        getMax(n, arrayList);
        long timeEnd = System.currentTimeMillis();
        long timeCost = timeEnd - timeStart;
        return timeCost;
    }
    
    public static long getMax(long n, ArrayList<Long> arrayList) {
        long max = arrayList.get(0);
        for (int i = 1; i < n; i++) {
            if (arrayList.get(i) > max) {
                max = arrayList.get(i);
            }
        }
        return max;
    }
    
    public static long linearTime(int n) {
        long[] list = new long[n];
        generateList(n, list);
        long timeStart = System.currentTimeMillis();
        getMax(n, list);
        long timeEnd = System.currentTimeMillis();
        long timeCost = timeEnd - timeStart;
        return timeCost;
    }
    
    public static long getMax(long n, long[] list) {
        long max = list[0];
        for (int i = 1; i < n; i++) {
            if (list[i] > max) {
                max = list[i];
            }
        }
        return max;
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
    
    public static void generatePointsList(int n, long[][] list) {
        generateList(n, list[0]);
        generateList(n, list[1]);
    }
    
    public static void generateArrayList(int n, ArrayList<Long> arrayList) {
        for (long i = 0; i < n; i++) {
            arrayList.add(i);
        }
        // shuffle
        Collections.shuffle(arrayList);
    }
    
    
    public static long MergeSortTime(int n) {
        //TODO:generate you test input data here
        long[] list = new long[n];
        generateList(n, list);
        long timeStart = System.currentTimeMillis();
        //TODO: write a algorithm
        mergeSort(list, 0, n - 1);
        long timeEnd = System.currentTimeMillis();
        long timeCost = timeEnd - timeStart;
        return timeCost;
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
        for (int k = l; k < l + n; k++) {
            if (i < n1 && j < n2) {
                if (B[i] <= C[j]) {
                    A[k] = B[i++];
                } else {
                    A[k] = C[j++];
                }
            } else if (i < n1) {
                A[k] = B[i++];
            } else if (j < n2) {
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
    
    public static long HeapSortTime(int n) {
        //TODO:generate you test input data here
        long[] list = new long[n];
        generateList(n, list);
        long timeStart = System.currentTimeMillis();
        //TODO: write a algorithm
        HeapSort(n, list);
        long timeEnd = System.currentTimeMillis();
        long timeCost = timeEnd - timeStart;
        return timeCost;
    }
    
    public static void HeapSort(int n, long[] list) {
        PriorityQueue<Long> priorityQueue = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            priorityQueue.add(list[i]);
        }
    }
    
    public static long ArraysSortTime(int n) {
        //TODO:generate you test input data here
        long[] list = new long[n];
        generateList(n, list);
        long timeStart = System.currentTimeMillis();
        //TODO: write a algorithm
        Arrays.sort(list);
        long timeEnd = System.currentTimeMillis();
        long timeCost = timeEnd - timeStart;
        return timeCost;
    }
    
    public static long QuadraticTime(int n) {
        //TODO:generate you test input data here
        long[][] list = new long[2][n];
        generatePointsList(n, list);
        long timeStart = System.currentTimeMillis();
        //TODO: write a algorithm
        closest(n, list);
        long timeEnd = System.currentTimeMillis();
        long timeCost = timeEnd - timeStart;
        return timeCost;
    }
    
    public static void closest(int n, long[][] list) {
        long x1 = list[0][0], x2 = list[0][1], y1 = list[1][0], y2 = list[1][1];
        long min = pow(x1 - x2) + pow(y1 - y2);
        long d;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                d = pow(list[0][i] - list[0][j]) + pow(list[1][i] - list[1][j]);
                if (d < min) {
                    min = d;
                }
            }
        }
    }
    
    public static long pow(long a) {
        return a * a;
    }
    
    public static long bubbleSortTime(int n) {
        //TODO:generate you test input data here
        long[] list = new long[n];
        generateList(n, list);
        long timeStart = System.currentTimeMillis();
        //TODO: write a algorithm
        bubbleSort(n, list);
        long timeEnd = System.currentTimeMillis();
        long timeCost = timeEnd - timeStart;
        return timeCost;
    }
    
    public static void bubbleSort(int n, long[] list) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (list[j] > list[j + 1]) {
                    long tmp = list[j];
                    list[j] = list[j + 1];
                    list[j + 1] = tmp;
                }
            }
        }
    }
    
    public static long insertionSortTime(int n) {
        //TODO:generate you test input data here
        long[] list = new long[n];
        generateList(n, list);
        long timeStart = System.currentTimeMillis();
        //TODO: write a algorithm
        insertionSort(n, list);
        long timeEnd = System.currentTimeMillis();
        long timeCost = timeEnd - timeStart;
        return timeCost;
    }
    
    public static void insertionSort(int n, long[] list) {
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                while (list[j] < list[j - 1]) {
                    long tmp = list[j];
                    list[j] = list[j - 1];
                    list[j - 1] = tmp;
                }
            }
            
        }
    }
    
    public static long SelectionSortTime(int n) {
        //TODO:generate you test input data here
        long[] list = new long[n];
        generateList(n, list);
        long timeStart = System.currentTimeMillis();
        //TODO: write a algorithm
        SelectionSort(n, list);
        long timeEnd = System.currentTimeMillis();
        long timeCost = timeEnd - timeStart;
        return timeCost;
    }
    
    public static void SelectionSort(int n, long[] list) {
        int min;
        for (int i = 0; i < n; i++) {
            min = i;
            for (int j = i + 1; j < n; j++) {
                if (list[j] < list[min]) {
                    min = j;
                }
            }
            long tmp = list[min];
            list[min] = list[i];
            list[i] = tmp;
        }
    }
    
    public static long QuickSortTime(int n) {
        //TODO:generate you test input data here
        long[] list = new long[n];
        generateList(n, list);
        long timeStart = System.currentTimeMillis();
        //TODO: write a algorithm
        QuickSort(list, 0, n - 1);
        long timeEnd = System.currentTimeMillis();
        long timeCost = timeEnd - timeStart;
        return timeCost;
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
    
    public static void swap(long[] list, int x, int y) {
        if (x == y) {
            return;
        }
        list[x] = list[x] ^ list[y];
        list[y] = list[x] ^ list[y];
        list[x] = list[x] ^ list[y];
    }
    
    public static long CubicTime(int n) {
        //TODO:generate you test input data here
        long[][] a = new long[n][n];
        long[][] b = new long[n][n];
        for (int i = 0; i < n; i++) {
            generateList(n, a[i]);
            generateList(n, b[i]);
        }
        long timeStart = System.currentTimeMillis();
        //TODO: write a algorithm
//        easyOn3(n);
        matrixMultiple(n,a,b);
        long timeEnd = System.currentTimeMillis();
        long timeCost = timeEnd - timeStart;
        return timeCost;
    }
    
    public static void easyOn3(int n) {
        int a;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    a = 2 * 2;
                }
            }
        }
    }
    
    public static void matrixMultiple(int n, long[][] a, long[][] b) {
        long result;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                result = 0;
                for (int k = 0; k < n; k++) {
                    result += a[i][k] * b[k][j];
                }
            }
        }
    }
    
    public static long ExponentialTime(int n) {
        //TODO:generate you test input data here
        long max;
        if (n >= 100) {
            max = Long.MAX_VALUE;
        } else {
            max = powTen(n);
        }
        long timeStart = System.currentTimeMillis();
        //TODO: write a algorithm
        enumerate(max);
        long timeEnd = System.currentTimeMillis();
        long timeCost = timeEnd - timeStart;
        return timeCost;
    }
    
    public static void enumerate(long max) {
        long a = 0;
        while (a < max) {
            a++;
        }
    }
    
    public static long powTen(int n) {
        long result = 1;
        for (int i = 0; i < n; i++) {
            result *= 10;
        }
        return result;
    }
    
    public static long FactorialTime(int n) {
        // todo generate you test input data
        long timeStart = System.currentTimeMillis();
        // todo write a algorithm
        Factorial(n);
        long timeEnd = System.currentTimeMillis();
        long timeCost = timeEnd - timeStart;
        return timeCost;
    }
    
    public static long Factorial(int n) {
        if (n == 1)
            return 1;
        else {
            long sum = 0;
            for (int i = 0; i < n; i++) {
                sum += Factorial(n - 1);
            }
            return sum;
        }
    }
}
