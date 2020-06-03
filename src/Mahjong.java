import java.io.*;
import java.util.StringTokenizer;

public class Mahjong {
    public static long pick(int m, int[] card) {
        long[] result = new long[m + 1];
//        int[] card1 = card.clone();
        int a0, b0, c0, a1, b1, c1, a2, b2, c2, min1, min2, result1, result2;
        for (int i = 3; i <= m; i++) {
            result[i] = result[i - 1];
            a0 = card[i - 2];
            b0 = card[i - 1];
            c0 = card[i];
            a1 = a0 / 3;
            b1 = b0 / 3;
            c1 = c0 / 3;
            min1 = Math.min(a0 % 3, b0 % 3);
            min1 = Math.min(min1, c0 % 3);
            result1 = a1 + b1 + c1 + min1;
            min2 = Math.min(a0, b0);
            min2 = Math.min(min2, c0);
            if (result1 > min2) {
                result[i] += result1;
                card[i - 2] %= 3;
                card[i - 2] -= min1;
                card[i - 1] %= 3;
                card[i - 1] -= min1;
                card[i] %= 3;
                card[i] -= min1;
            } else if (result1 < min2) {
                result[i] += min2;
                card[i - 2] -= min2;
                card[i - 1] -= min2;
                card[i] -= min2;
            } else {
                result[i] += min2;
                card[i - 2] -= min2;
                card[i - 1] -= min2;
                card[i] -= min2;
            }
        }
        return result[m];
    }

//    public static int dp(int m, int[] card) {
//        int[][][] result = new int[m][m][m];
//        int a0, b0, c0, a1, b1, c1, a2, b2, c2, min1, min2, result1, result2;
//        for (int i = m; i >= 3; i--) {
//            a0 = card[i - 2];
//            b0 = card[i - 1];
//            c0 = card[i];
//
//            a1 = a0 / 3;
//            b1 = b0 / 3;
//            c1 = c0 / 3;
//            min1 = Math.min(a0 % 3, b0 % 3);
//            min1 = Math.min(min1, c0 % 3);
//            result1 = a1 + b1 + c1 + min1;
//
//            min2 = Math.min(a0, b0);
//            min2 = Math.min(min2, c0);
//
//        }
//    }

    public static int dp(int i, int[] card) {
        if (i <= 1) {
            return 0;
        } else if (i == 2) {
            return card[1] / 3;
        } else if (i == 3) {
            return card[1] / 3 + card[2] / 3;
        } else {
            int a0, b0, c0, a1, b1, c1, a2, b2, c2, min1, min2, result1, result2;
            a0 = card[i - 2];
            b0 = card[i - 1];
            c0 = card[i];
            a1 = a0 / 3;
            b1 = b0 / 3;
            c1 = c0 / 3;
            min1 = Math.min(a0 % 3, b0 % 3);
            min1 = Math.min(min1, c0 % 3);
            result1 = a1 + b1 + c1 + min1;
            min2 = Math.min(a0, b0);
            min2 = Math.min(min2, c0);
            result2 = Math.max(result1, min2);

            return Math.max(dp(i - 2, card) + result2, dp(i - 1, card));
        }
    }

    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        int n, m;
        n = in.nextInt();
        m = in.nextInt();
        int[] card = new int[m + 1];
        for (int i = 0; i < n; i++) {
            card[in.nextInt()]++;
        }
        out.println(pick(m, card));
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
