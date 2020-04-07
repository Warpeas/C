import java.io.*;
import java.util.HashMap;
import java.util.StringTokenizer;

public class FindingVince {
    static int n;
    static vertex[] graph;
    static long[] dist;
    static InputStream inputStream = System.in;
    static OutputStream outputStream = System.out;
    static InputReader in = new InputReader(inputStream);
    static PrintWriter out = new PrintWriter(outputStream);
    
    static class vertex {
        int index;
        int a, b;
        HashMap<Integer, Integer> path;
        
        vertex(int i) {
            index = i;
            path = new HashMap<>();
        }
    }
    
    static int minDistance(long[] dist, Boolean[] sptSet) {
        double min = Double.MAX_VALUE;
        int min_index = -1;
        for (int v = 0; v < n; v++)
            if (!sptSet[v] && dist[v] <= min) {
                min = dist[v];
                min_index = v;
            }
        return min_index;
    }
    
    static long printSolution() {
        return dist[n - 1];
    }
    
    static void dijkstra(int src) {
        dist = new long[n];
        Boolean[] sptSet = new Boolean[n];
        // Initialize all distances as INFINITE and stpSet[] as false
        for (int i = 0; i < n; i++) {
            dist[i] = Long.MAX_VALUE;
            sptSet[i] = false;
        }
        long diff, actual;
        dist[src] = 0;
        // Find shortest path for all vertices
        for (int cnt = 0; cnt < n - 1; cnt++) {
            int u = minDistance(dist, sptSet);
            sptSet[u] = true;
            vertex v = graph[u];
            
            for (int k : v.path.keySet()) {
                diff = (dist[u] + v.path.get(k)) % (graph[k].a + graph[k].b);
                actual = v.path.get(k);
                if (diff < graph[k].a) {
                    actual += graph[k].a - diff;
                }
                if (!sptSet[k] && dist[u] != Long.MAX_VALUE && dist[u] + actual < dist[k]) {
                    dist[k] = dist[u] + actual;
                }
            }
        }
    }
    
    public static void main(String[] args) {
        n = in.nextInt();
        int m = in.nextInt();
        graph = new vertex[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new vertex(i);
        }
        int u, v, w;
        for (int i = 0; i < m; i++) {
            u = in.nextInt();
            v = in.nextInt();
            w = in.nextInt();
            graph[u - 1].path.put(v - 1, w);
        }
        for (int i = 0; i < n; i++) {
            graph[i].a = in.nextInt();
            graph[i].b = in.nextInt();
        }
        dijkstra(0);
        out.println(printSolution());
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
