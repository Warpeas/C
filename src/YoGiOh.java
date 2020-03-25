import java.io.*;
import java.util.HashMap;
import java.util.StringTokenizer;

public class YoGiOh {
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

    /*
    |   score   |   points  |
    |   2:0     |   3       |
    |   2:1     |   2       |
    |   1:1     |   1       |
    |   1:2     |   1       |
    |   0:2     |   0       |
    */

    /*
    hashmap<(string)number+score:(int)cnt>
    */

    /*
    a (n)*(n) matrix
    and a n array store each score
    first dfs, fill each grid
    when the left empty grid is the number n then find if the situation is calculate
    use sorted array? relate with an int
    */

    static HashMap<int[],Integer>hashMap;
    static int n;
    int dfs(int[] players, int[][] scores) {
        return 0;
    }

    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        hashMap = new HashMap<>();
        n = in.nextInt();
    }
}
