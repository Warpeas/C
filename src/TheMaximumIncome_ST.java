
import java.io.*;
import java.util.*;

public class TheMaximumIncome_ST {
    static InputStream inputStream = System.in;
    static OutputStream outputStream = System.out;
    static InputReader in = new InputReader(inputStream);
    static PrintWriter out = new PrintWriter(outputStream);
    static int n;
    static int[] set;
    static int maxEndTime;
    static int[] pay;
    static int[] time;

    static class path implements Comparable<path> {
        int index1, index2;
        int w;

        public path(int s, int e, int w) {
            index1 = s;
            index2 = e;
            this.w = w;
        }

        @Override
        public int compareTo(path o) {
            return o.w - this.w;
        }
    }

    static path[] paths;

    static long getPay() {
        time = new int[maxEndTime];
        pay = new int[maxEndTime];
        long result = 0;
        int flag;
        /*
        i is current task
        j is the time of current task
        k is the time of the task to move
        */
        for (int i = 0; i < paths.length; i++) {
            path a = paths[i];
            for (int j = a.index1; j < a.index2; j++) {
                if (time[j] == 0) {
                    result += a.w;
                    pay[j] = a.w;
                    time[j] = i + 1;
                    a.index1 = j;
                    break;
                } else if (time[j] > 0) {
                    if (a.index2 < paths[time[j] - 1].index2) {
                        path b = paths[time[j] - 1];
                        flag = 0;
                        for (int k = b.index1 + 1; k < b.index2; k++) {
                            if (time[k] == 0) {
                                result += a.w;
                                pay[k] = pay[j];
                                pay[j] = a.w;
                                b.index1 = k;
                                a.index1 = j;
                                time[k] = time[j];
                                time[j] = i + 1;
                                flag = 1;
                                break;
                            }
                        }
                        if (flag == 1) {
                            break;
                        }
                    }
                }
            }
        }
        return result;
    }

    static void swap(path a, path b) {

    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        n = in.nextInt();
        int s, e, w;
        paths = new path[n];
        for (int i = 0; i < n; i++) {
            s = in.nextInt();
            e = in.nextInt();
            w = in.nextInt();
            paths[i] = (new path(s - 1, e, w));
            maxEndTime = Math.max(e + 1, maxEndTime);
        }

//        set = new int[maxEndTime + 1];
        Arrays.sort(paths);
//        out.println(addToSet());
        out.println(getPay());
        long end = System.currentTimeMillis();
        out.println(end - start);
        out.close();
    }

    static class InputReader {
        public BufferedReader reader;
        public StringTokenizer tokenizer;

        public InputReader(InputStream stream) {
            reader = new BufferedReader(new InputStreamReader(stream), 32768);
            tokenizer = null;
        }

        public String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken();
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

        public long nextLong() {
            return Long.parseLong(next());
        }

        public double nextDouble() {
            return Double.parseDouble(next());
        }

        public char[] nextCharArray() {
            return next().toCharArray();
        }
    }
}

