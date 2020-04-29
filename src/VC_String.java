import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class VC_String {
    static int pre_power_of_two(int l) {
        int b = Integer.highestOneBit(l);
        if (l == b) {
            return l;
        }
        return b;
    }

    static int next_power_of_two(int l) {
        int b = Integer.highestOneBit(l);
        if (l == b) {
            return l;
        }
        return b << 1;
    }

    public static long findV(int l, int r) {
        //  flag origin 0, left 2, right 1
        if (l > r) {
            return 0;
        }
        long result = 0;
        int pre = pre_power_of_two(r);
        int nxt = next_power_of_two(pre);
        if (l < pre) {
            result += 1;
            result += findV(l, pre - 1);
            result += r - pre - findV(2 * pre - r, pre - 1);
        } else if (l == pre) {
            result += 1;
            if (r != l)
                result += r - l - findV(2 * pre - r, pre - 1);
        } else {
            result += r - l + 1 - findV(2 * pre - r, 2 * pre - l);
        }
        return result;
    }

    public static long[] find(int[] inputs, int n) {
        long[] output = new long[n];
        for (int i = 0; i < 2 * n; i++) {
            output[i / 2] = findV(inputs[i], inputs[++i]);
        }
        return output;
    }

    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        int n = in.nextInt();
        int[] inputs = new int[2 * n];
//        int max = 0;
        for (int i = 0; i < 2 * n; i++) {
            inputs[i] = in.nextInt();
//            max = Math.max(inputs[i], max);
            inputs[++i] = in.nextInt();
//            max = Math.max(inputs[i], max);
        }
        long[] output = find(inputs, n);
        for (int i = 0; i < n; i++) {
            out.println(output[i]);
        }
//        out.println(pre_power_of_two(n));
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
