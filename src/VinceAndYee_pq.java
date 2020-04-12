
import java.io.*;
import java.util.*;

public class VinceAndYee_pq {
    static InputStream inputStream = System.in;
    static OutputStream outputStream = System.out;
    static InputReader in = new InputReader(inputStream);
    static PrintWriter out = new PrintWriter(outputStream);
    static int n;
    static int[] set;
    
    static class path implements Comparable<path> {
        int index1, index2;
        int w;
        
        public path(int i, int j, int w) {
            index1 = i;
            index2 = j;
            this.w = w;
        }
        
        @Override
        public int compareTo(path o) {
            return this.w - o.w;
        }
    }
    
    //    static path[] paths;
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
        while (!paths.isEmpty()) {
            if (cnt == n + 1 && s_cnt == 1) {
                break;
            }
            path p = paths.peek();
            w = p.w;
//            w = paths[i].w;
            u = p.index1;
//            u = paths[i].index1;
            v = p.index2;
//            v = paths[i].index2;
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
        long start = System.currentTimeMillis();
        n = in.nextInt();
        set = new int[n + 2];
        int w;
        paths = new PriorityQueue<>();
//        path[] p = new path[n * (n + 1) / 2];
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                w = in.nextInt();
//                p[cnt++] = new path(i + 1, j + 2, w);
                paths.add(new path(i + 1, j + 2, w));
            }
        }
//        Arrays.sort(paths);
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

