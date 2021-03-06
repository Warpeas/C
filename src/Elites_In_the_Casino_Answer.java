import java.io.*;
import java.util.StringTokenizer;

public class Elites_In_the_Casino_Answer {
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
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        int n = in.nextInt();
        char[][] chars = new char[n][];
        for (int i = 0; i < n; i++) {
            chars[i] = in.next().toCharArray();
        }
        int result = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                result = (result + Shuffle(chars[i], chars[j])) % 998244353;
            }
        }
        out.println(result);
        out.close();
    }
    
    public static int Shuffle(char[] a, char[] b) {
        StringBuilder stringBuilder = new StringBuilder();
        int p = a.length;
        int q = b.length;
        if (p >= q) {
            for (int i = 0; i < p - q; i++) {
                stringBuilder.append(a[i]);
            }
            for (int i = 0; i < q; i++) {
                stringBuilder.append(a[i + p - q]);
                stringBuilder.append(b[i]);
            }
        } else {
            for (int i = 0; i < q - p; i++) {
                stringBuilder.append(b[i]);
            }
            for (int i = 0; i < p; i++) {
                stringBuilder.append(a[i]);
                stringBuilder.append(b[i + q - p]);
            }
        }
        return Integer.parseInt(stringBuilder.toString()) % 998244353;
    }
}
