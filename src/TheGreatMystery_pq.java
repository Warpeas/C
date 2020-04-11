import java.io.*;
import java.util.*;

public class TheGreatMystery_pq {
    static InputStream inputStream = System.in;
    static OutputStream outputStream = System.out;
    static InputReader in = new InputReader(inputStream);
    static PrintWriter out = new PrintWriter(outputStream);
    static int n, m;
    
    static class node {
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            node node = (node) o;
            return x == node.x &&
                    y == node.y &&
                    w == node.w;
        }
        
        int x, y;
        int w;
        
        node(int x, int y, int w) {
            this.x = x;
            this.y = y;
            this.w = w;
        }
    }
    
    static class path {
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
    }
    
    static Set<node> originalSet;
    static int[][] nodes;
    static int[][] set;
    static Map<Integer, Set<node>> setMap = new HashMap<>();
    static PriorityQueue<Map.Entry<Integer, path>> paths = new PriorityQueue<>(
            Comparator.comparingInt(Map.Entry::getKey)
    );
    
    static int addToSet() {
        int result = 0;
        while (!setMap.entrySet().containsAll(originalSet)) {
            assert paths.peek() != null;
            int x1, x2, y1, y2;
            x1 = paths.peek().getValue().x1;
            y1 = paths.peek().getValue().y1;
            x2 = paths.peek().getValue().x2;
            y2 = paths.peek().getValue().y2;
            if (set[x1][y1] != 0 && set[x1][y1] != set[x2][y2]) {
                result += paths.peek().getKey();
                
            }
        }
        return result;
    }
    
    public static void main(String[] args) {
        n = in.nextInt();
        m = in.nextInt();
        nodes = new int[n][m];
        set = new int[n][m];
        int w;
//        comparator cmp = new comparator();
        originalSet = new HashSet<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                w = in.nextInt();
                nodes[i][j] = w;
                originalSet.add(new node(i, j, w));
            }
        }
        Map<Integer, path> p = new HashMap<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (i + 1 < n) {
                    int weight = nodes[i][j] ^ nodes[i + 1][j];
                    p.put(weight, new path(i, j, i + 1, j, weight));
                }
                if (j + 1 < m) {
                    int weight = nodes[i][j] ^ nodes[i][j + 1];
                    p.put(weight, new path(i, j, i, j + 1, weight));
                }
            }
        }
        paths.addAll(p.entrySet());
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

