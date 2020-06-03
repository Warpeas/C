import java.io.*;
import java.util.StringTokenizer;

import static java.lang.Math.max;

public class SleepyVince {
//    public static int pack(int[] a, int n, int c) {
//        long opt[][][] = new long[n + 1][c][2]
//        for (int i = 1; i <= n; i++) {
//            for (int j = 1; j <= 200; j++) {
//                if (a[i] != -1) {
//                    opt[i][a[i]][a[i] <= a[i - 1] ? 1 : 0] = opt[i - 1][a[i - 1]][a[i - 1] <= a[i - 2] ? 1 : 0];
//                }
//            }
//        }
//    }

    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        int n = in.nextInt();
        int[] a = new int[n + 2];
        int max = 0;
        for (int i = 1; i <= n; i++) {
            a[i] = in.nextInt();
            max = max(max, a[i]);
        }
//        out.println(pack(a, n, max));
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
