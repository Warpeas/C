import javafx.util.Pair;

import java.io.*;
import java.util.*;

public class TheGreatMystery {
    static InputStream inputStream = System.in;
    static OutputStream outputStream = System.out;
    static InputReader in = new InputReader(inputStream);
    static PrintWriter out = new PrintWriter(outputStream);
    static int n, m;
    
    static class path implements Comparable<path> {
        int x1, y1;
        int x2, y2;
        int w;
        
        public path(int x1, int y1, int x2, int y2, int w) {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
            this.w = w;
        }
        
        @Override
        public int compareTo(path o) {
            return this.w - o.w;
        }
    }
    
    static int[][] weight;
    static int[][] s;
    static Map<Integer, Set<Integer>> setMap = new HashMap<>();
    static path[] paths;
    
    static long addToSet() {
        long result = 0;
        int s1, s2, cnt = 1;
        int x1, x2, y1, y2;
        for (int i = 0; i < paths.length && setMap.entrySet().size() != n * m; i++) {
            x1 = paths[i].x1;
            y1 = paths[i].y1;
            x2 = paths[i].x2;
            y2 = paths[i].y2;
            s1 = s[x1][y1];
            s2 = s[x2][y2];
            if (s1 != s2 && s1 != 0 && s2 != 0) {
                result += paths[i].w;
                if (s1 > s2) {
                    for (int p :
                            setMap.get(s1)) {
                        s[p / m][p % m] = s2;
                    }
                    setMap.get(s2).addAll(setMap.get(s1));
                    setMap.remove(s1);
                } else {
                    for (int p :
                            setMap.get(s2)) {
                        s[p / m][p % m] = s1;
                    }
                    setMap.get(s1).addAll(setMap.get(s2));
                    setMap.remove(s2);
                }
            } else if (s1 == 0 && s2 != 0) {
                result += paths[i].w;
                setMap.get(s2).add(x1 * m + y1);
                s[x1][y1] = s2;
            } else if (s1 != 0 && s2 == 0) {
                result += paths[i].w;
                setMap.get(s1).add(x2 * m + y2);
                s[x2][y2] = s1;
            } else if (s1 == 0) {
                result += paths[i].w;
                setMap.put(cnt, new HashSet<>());
                setMap.get(cnt).add(x1 * m + y1);
                setMap.get(cnt).add(x2 * m + y2);
                s[x1][y1] = cnt;
                s[x2][y2] = cnt++;
            }
        }
        return result;
    }
    
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        n = in.nextInt();
        m = in.nextInt();
        weight = new int[n][m];
        s = new int[n][m];
        int w;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                w = in.nextInt();
                weight[i][j] = w;
            }
        }
        paths = new path[n * (m - 1) + m * (n - 1)];
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (i + 1 < n) {
                    w = weight[i][j] ^ weight[i + 1][j];
                    paths[cnt++] = new path(i, j, i + 1, j, w);
                }
                if (j + 1 < m) {
                    w = weight[i][j] ^ weight[i][j + 1];
                    paths[cnt++] = new path(i, j, i, j + 1, w);
                }
            }
        }
        Arrays.sort(paths);
        out.println(addToSet());
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

