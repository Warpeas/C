import java.io.*;
import java.util.StringTokenizer;

import static java.lang.Math.max;

public class WellPlayed {
    static class card {
        int cost, value;

        card(int c, int v) {
            cost = c;
            value = v;
        }
    }

    public static int pack(int[] w, int[] v, int c) {
        int n = w.length;
        int[][] bag = new int[2][c + 1];
//        for (int j = 0; j <= c; j++) {
//            bag[0][j] = (j >= w[0] ? v[0] : 0);
//        }

        for (int i = 0; i < c + 1; i++) {
            bag[0][i] = 0;
        }
        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= c; j++) {
                bag[i % 2][j] = bag[(i - 1) % 2][j];
                if (j >= w[i]) {
                    bag[i % 2][j] = max(bag[i % 2][j], v[i] + bag[(i - 1) % 2][j - w[i]]);
                }
            }
        }
        return bag[(n - 1) % 2][c];
    }

    public static int pack(card[] cards, int n, int k, int c) {
//        int len = cards.length;
//        int[][] bag = new int[2][c + 1];
//        int[][] num = new int[2][c + 1];
////        for (int j = 0; j <= c; j++) {
////            bag[0][j] = (j >= w[0] ? v[0] : 0);
////        }
//
////        for (int i = 0; i < c + 1; i++) {
////            bag[0][i] = 0;
////        }
//        int get;
//        loop:
//        for (int i = 1; i < len; i++) {
//            for (int j = 0; j <= c; j++) {
//                bag[i % 2][j] = bag[(i - 1) % 2][j];
//                num[i % 2][j] = num[(i - 1) % 2][j];
//                if (j >= cards[i].cost) {
//                    get = cards[i].value + bag[(i - 1) % 2][j - cards[i].cost];
//                    if (bag[i % 2][j] < get) {
//                        bag[i % 2][j] = get;
//                        num[i % 2][j] = num[(i - 1) % 2][j - cards[i].cost] + 1;
//                    }
//                    if (num[i % 2][j] == 30) {
//                        for (int l = j + 1; l < c; l++) {
//                            bag[i % 2][k] = bag[i % 2][j];
//                            num[i % 2][k] = num[i % 2][j];
//                        }
////                        i++;
////                        j = 0;
//                        continue loop;
//                    }
//                }
//            }
//        }
//        return bag[(n - 1) % 2][c];
        int[][] bag = new int[n + 1][c + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= c; j++) {
                bag[i][j] = bag[i - 1][j];
                for (int l = 1; l <= k; l++) {
                    if (j >= cards[(i - 1) * k + l].cost)
                        bag[i][j] = max(bag[i][j], bag[i - 1][j - cards[(i - 1) * k + l].cost] + cards[(i - 1) * k + l].value);
                }
            }
        }
        return bag[n][c];
    }

    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        int n = in.nextInt();
        int k = in.nextInt();
        int c = in.nextInt();
        card[] cards = new card[n * k + 1];
//        int[] w = new int[91];
//        int[] v = new int[91];
        for (int i = 1; i <= n * k; i++) {
            cards[i] = new card(in.nextInt(), in.nextInt() + in.nextInt());
//            w[i] = in.nextInt();
//            v[i] = in.nextInt() + in.nextInt();
        }
//        Arrays.sort(cards, Comparator.comparingInt(card -> card.value));
        out.println(pack(cards, n, k, c));
//        out.println(pack(w, v, c));
//        w = new int[]{0, 1, 2, 5, 6, 7};
//        v = new int[]{0, 1, 6, 18, 22, 28};
//        out.println(pack(w,v,11));
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
