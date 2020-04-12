
import java.io.*;
import java.util.*;

public class TheGreatMystery_pq {
    static InputStream inputStream = System.in;
    static OutputStream outputStream = System.out;
    static InputReader in = new InputReader(inputStream);
    static PrintWriter out = new PrintWriter(outputStream);
    static int n, m;
    static int[] set;
    
    static class path implements Comparable<path> {
        int index1, index2;
        int w;
        
        public path(int x1, int y1, int x2, int y2, int w) {
            index1 = x1 * m + y1 + 1;
            index2 = x2 * m + y2 + 1;
            this.w = w;
        }
        
        @Override
        public int compareTo(path o) {
            return this.w - o.w;
        }
    }
    
    static PriorityQueue<path> paths;
    
    //  find set
    static int find(int x) {
        if (x == set[x]) {
            return x;
        } else {
            return set[x] = find(set[x]);
        }
    }
    
    //  join two set
    static void join(int s1, int s2) {
        if (s1 == s2) {
            return;
        }
        set[s1] = s2;
    }
    
    static long addToSet() {
        long result = 0;
        int cnt = 0, s_cnt = 0;
        int u, v, s1, s2, w;
//        for (int i = 0; i < paths.length; i++) {
        while (!paths.isEmpty()){
            if (cnt == n * m && s_cnt == 1) {
                break;
            }
            path p = paths.peek();
            w = p.w;
            u = p.index1;
            v = p.index2;
            s1 = find(u);
            s2 = find(v);
            if (s1 != s2 && s1 != 0 && s2 != 0) {
                s_cnt--;
                result += w;
                join(s1, s2);
            } else if (s1 == 0 && s2 != 0) {
                result += w;
                cnt++;
                set[u] = s2;
            } else if (s2 == 0 && s1 != 0) {
                result += w;
                cnt++;
                set[v] = s1;
            } else if (s1 == 0) {
                s_cnt++;
                result += w;
                cnt++;
                set[u] = u;
                cnt++;
                set[v] = u;
            }
            paths.remove();
        }
        return result;
    }
    
    public static void main(String[] args) {
//        long start = System.currentTimeMillis();
        n = in.nextInt();
        m = in.nextInt();
        set = new int[n * m + 1];
        int w;
        int[][] weight = new int[2][m];
        paths = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                w = in.nextInt();
                weight[i % 2][j] = w;
                if (i > 0) {
                    w = weight[(i - 1) % 2][j] ^ weight[i % 2][j];
                    paths.add(new path(i - 1, j, i, j, w));
                }
                if (j > 0) {
                    w = weight[i % 2][j - 1] ^ weight[i % 2][j];
                    paths.add(new path(i, j - 1, i, j, w));
                }
            }
        }
//        Arrays.sort(paths);
        out.println(addToSet());
//        long end = System.currentTimeMillis();
//        out.println(end - start);
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

