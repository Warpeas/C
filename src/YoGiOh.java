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

    static int[][] result = {{3, 0}, {2, 1}, {1, 1}, {1, 2}, {0, 3}};

    static HashMap<int[], Integer> hashMap;
    static int n;

    static int dfs(int[] players, int a, int b, int r) {
        players[a] -= result[r][0];
        players[b] -= result[r][1];

        int[] cp = players.clone();
        Arrays.sort(cp);
        if (cp[0] < 0) {
            return 0;
        } else if (cp[n - 1] == 0 && a == n - 2) {
            return 1;
        } else {
            if (hashMap.containsKey(cp)) {
                return hashMap.get(cp);
            } else {
                b++;
                if (b == n) {
                    a++;
                    if (a == n - 1) {
                        return 0;
                    }
                    b = a + 1;
                }
                int cnt = dfs(players.clone(), a, b, 0) +
                        dfs(players.clone(), a, b, 1) +
                        dfs(players.clone(), a, b, 2) +
                        dfs(players.clone(), a, b, 3) +
                        dfs(players.clone(), a, b, 4);
                hashMap.put(players, cnt);
                return cnt;
            }
        }
    }

    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        hashMap = new HashMap<>();
        n = in.nextInt();
        int[] players = new int[n];
        for (int i = 0; i < n; i++) {
            players[i] = in.nextInt();
        }
        System.out.println(dfs(players.clone(), 0, 1, 0) +
                dfs(players.clone(), 0, 1, 1) +
                dfs(players.clone(), 0, 1, 2) +
                dfs(players.clone(), 0, 1, 3) +
                dfs(players.clone(), 0, 1, 4));
    }
}
