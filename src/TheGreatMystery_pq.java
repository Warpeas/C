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
                    y == node.y;
        }
        
        int x, y;
        int w, s;
        
        node(int x, int y, int w) {
            this.x = x;
            this.y = y;
            this.w = w;
            s = -1;
        }
    }
    
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
            return w - o.w;
        }
    }
    
    static Set<node> originalSet;
    static node[][] nodes;
    static Map<Integer, Set<node>> setMap = new HashMap<>();
    static PriorityQueue<path> paths = new PriorityQueue<>();
    
    static long addToSet() {
        long result = 0;
        int s1, s2, cnt = 0;
        while (setMap.entrySet().isEmpty() || !setMap.entrySet().iterator().next().getValue().containsAll(originalSet)) {
            assert paths.peek() != null;
            int x1, x2, y1, y2;
            x1 = paths.peek().x1;
            y1 = paths.peek().y1;
            x2 = paths.peek().x2;
            y2 = paths.peek().y2;
            s1 = nodes[x1][y1].s;
            s2 = nodes[x2][y2].s;
            if (s1 != s2 && s1 != -1 && s2 != -1) {
                result += paths.peek().w;
                if (s1 > s2) {
                    for (node n :
                            setMap.get(s1)) {
                        n.s = s2;
                    }
                    setMap.get(s2).addAll(setMap.get(s1));
                    setMap.remove(s1);
                } else {
                    for (node n :
                            setMap.get(s2)) {
                        n.s = s1;
                    }
                    setMap.get(s1).addAll(setMap.get(s2));
                    setMap.remove(s2);
                }
            } else if (s1 == -1 && s2 != -1) {
                result += paths.peek().w;
                setMap.get(s2).add(nodes[x1][y1]);
                nodes[x1][y1].s = s2;
            } else if (s1 != -1 && s2 == -1) {
                result += paths.peek().w;
                setMap.get(s1).add(nodes[x2][y2]);
                nodes[x2][y2].s = s1;
            } else if (s1 == -1) {
                result += paths.peek().w;
                setMap.put(cnt, new HashSet<>());
                setMap.get(cnt).add(nodes[x1][y1]);
                setMap.get(cnt).add(nodes[x2][y2]);
                nodes[x1][y1].s = cnt;
                nodes[x2][y2].s = cnt++;
            }
            paths.remove();
        }
        return result;
    }
    
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        n = in.nextInt();
        m = in.nextInt();
        nodes = new node[n][m];
        int w;
        originalSet = new HashSet<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                w = in.nextInt();
                nodes[i][j] = new node(i, j, w);
                originalSet.add(nodes[i][j]);
            }
        }
        int weight;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (i + 1 < n) {
                    weight = nodes[i][j].w ^ nodes[i + 1][j].w;
                    paths.add(new path(i, j, i + 1, j, weight));
                }
                if (j + 1 < m) {
                    weight = nodes[i][j].w ^ nodes[i][j + 1].w;
                    paths.add(new path(i, j, i, j + 1, weight));
                }
            }
        }
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

