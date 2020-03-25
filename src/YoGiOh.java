import java.io.*;
import java.util.Arrays;
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

    static InputStream inputStream = System.in;
    static OutputStream outputStream = System.out;
    static InputReader in = new InputReader(inputStream);
    static PrintWriter out = new PrintWriter(outputStream);

    /*
    |   score   |   points  |   index   |
    |   2:0     |   3       |   0       |
    |   2:1     |   2       |   1       |
    |   1:1     |   1       |   2       |
    |   1:2     |   1       |   3       |
    |   0:2     |   0       |   4       |
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

    static int[][] result = {{3, 0}, {2, 1}, {1, 1}, {1, 2}, {0, 3}};
    static HashMap<Integer, Long> hashMap;
    static int n;

    static long dfs(int[] players, int a, int b, int r, int rest) {
        players[a] -= result[r][0];
        players[b] -= result[r][1];

//        int[] cp = Arrays.copyOfRange(players, a, n);
//        Arrays.sort(cp);
//        int key = Arrays.hashCode(new int[]{Arrays.hashCode(cp), rest});
//        int key = Arrays.hashCode(new int[]{Arrays.hashCode(players), rest});
        int[] cp = Arrays.copyOfRange(players, a, n);
        Arrays.sort(cp);
        int key = Arrays.hashCode(new int[]{Arrays.hashCode(cp), rest});
        if (players[a] < 0 || (players[a] > 0 && players[b] < 0) || (b == n - 1 && players[a] != 0) || (players[a] > 3 * (n - b - 1)) || (players[b] > 3 * (n - a - 1))) {
            return 0;
        } else if (players[n - 2] == 0 && players[n - 1] == 0 && rest == 0) {
            return 1;
        } else {
            if (hashMap.containsKey(key)) {
                return hashMap.get(key);
            } else {
                b++;
                if (b == n) {
                    a++;
                    if (a == n - 1) {
                        return 0;
                    }
                    b = a + 1;
                }
                long cnt = dfs(players.clone(), a, b, 0, rest - 1) % 998244353 +
                        dfs(players.clone(), a, b, 1, rest - 1) % 998244353 +
                        dfs(players.clone(), a, b, 2, rest - 1) % 998244353 +
                        dfs(players.clone(), a, b, 3, rest - 1) % 998244353 +
                        dfs(players.clone(), a, b, 4, rest - 1) % 998244353;
                cnt %= 998244353;
                if (b == a + 1)
                    hashMap.put(key, cnt);
//                if (hashMap.containsKey(key)) {
//                    out.println(Arrays.toString(cp) + " " + hashMap.get(key));
//                }
                return cnt;
            }
        }
    }

    public static void main(String[] args) {
//        InputStream inputStream = System.in;
//        OutputStream outputStream = System.out;
//        InputReader in = new InputReader(inputStream);
//        PrintWriter out = new PrintWriter(outputStream);
        int t = in.nextInt();
        for (int j = 0; j < t; j++) {
            hashMap = new HashMap<>();
            n = in.nextInt();
            int[] players = new int[n];
            for (int i = 0; i < n; i++) {
                players[i] = in.nextInt();
            }
            int rest = n * (n - 1) / 2 - 1;
            long start = System.currentTimeMillis();
            long result = dfs(players.clone(), 0, 1, 0, rest) % 998244353;
            long end = System.currentTimeMillis();
            out.println(end - start);

            start = System.currentTimeMillis();
            result += dfs(players.clone(), 0, 1, 4, rest) % 998244353;
            result %= 998244353;
            end = System.currentTimeMillis();
            out.println(end - start);

            start = System.currentTimeMillis();
            result += dfs(players.clone(), 0, 1, 1, rest) % 998244353;
            result %= 998244353;
            end = System.currentTimeMillis();
            out.println(end - start);

            start = System.currentTimeMillis();
            result += dfs(players.clone(), 0, 1, 3, rest) % 998244353;
            result %= 998244353;
            end = System.currentTimeMillis();
            out.println(end - start);

            start = System.currentTimeMillis();
            result += dfs(players.clone(), 0, 1, 2, rest) % 998244353;
            result %= 998244353;
            end = System.currentTimeMillis();
            out.println(end - start);
            Runtime.getRuntime().freeMemory();

//        out.println((dfs(players.clone(), scores, 0, 1, 0, rest) % 998244353 +
//                dfs(players.clone(), scores, 0, 1, 1, rest) % 998244353 +
//                dfs(players.clone(), scores, 0, 1, 2, rest) % 998244353 +
//                dfs(players.clone(), scores, 0, 1, 3, rest) % 998244353 +
//                dfs(players.clone(), scores, 0, 1, 4, rest) % 998244353) % 998244353);
            out.println(result);
            out.println();
            hashMap.clear();
        }
        out.close();
    }
}
