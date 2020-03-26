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
    static int n, tie;

    static long dfs(int[] players, int a, int b, int r, int rest) {
        players[a] -= result[r][0];
        players[b] -= result[r][1];

        boolean lastRace = (b == (n - 1));
        if (players[a] < 0 || players[b] < 0 ||
                (lastRace && players[a] != 0) || (players[a] > 3 * (n - b - 1)) || (players[b] > 3 * (n - a - 1)) || rest < tie) {
            players[a] += result[r][0];
            players[b] += result[r][1];
            return 0;
        }
        if (players[n - 2] == 0 && players[n - 1] == 0 && rest == 0) {
            players[a] += result[r][0];
            players[b] += result[r][1];
            return 1;
        }

        int[] copy = Arrays.copyOfRange(players, a, n);
        Arrays.sort(copy);
        int key = Arrays.hashCode(new int[]{Arrays.hashCode(copy), rest});
        if (hashMap.containsKey(key)) {
            players[a] += result[r][0];
            players[b] += result[r][1];
            return hashMap.get(key);
        }

        int a1, b1;
        if (lastRace) {
            if (a == n - 2) {
                players[a] += result[r][0];
                players[b] += result[r][1];
                return 0;
            }
            a1 = a + 1;
            b1 = a1 + 1;
        } else {
            a1 = a;
            b1 = b + 1;
        }

        long cnt = dfs(players, a1, b1, 0, rest - 1) % 998244353;
        cnt += dfs(players, a1, b1, 1, rest - 1) % 998244353;
        tie--;
        cnt += dfs(players, a1, b1, 2, rest - 1) % 998244353;
        tie++;
        cnt += dfs(players, a1, b1, 3, rest - 1) % 998244353;
        cnt += dfs(players, a1, b1, 4, rest - 1) % 998244353;
        cnt %= 998244353;

        if (lastRace) hashMap.put(key, cnt);
        players[a] += result[r][0];
        players[b] += result[r][1];
        return cnt;
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
            int cnt = 0;
            for (int i = 0; i < n; i++) {
                players[i] = in.nextInt();
                cnt += players[i];
            }
            Arrays.sort(players);
            int rest = n * (n - 1) / 2 - 1;
            tie = 3 * (rest + 1) - cnt;
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
            tie--;
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
