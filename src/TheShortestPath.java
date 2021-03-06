import java.io.*;
import java.lang.*;
import java.util.HashMap;
import java.util.StringTokenizer;

class TheShortestPath {
    static int n;
    static vertex[] graph;
    static double[] dist;
    static InputStream inputStream = System.in;
    static OutputStream outputStream = System.out;
    static InputReader in = new InputReader(inputStream);
    static PrintWriter out = new PrintWriter(outputStream);
    
    static class vertex {
        int index;
        int pre;
        HashMap<Integer, Integer> path;
        
        vertex(int i) {
            index = i;
            path = new HashMap<>();
        }
    }
    
    static int minDistance(double[] dist, Boolean[] sptSet) {
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
        long result = 1;
        vertex s = graph[n - 1];
        vertex e;
        for (int i = 1; i < n; i++) {
            if (s.index != 0) {
                e = graph[s.pre];
                result *= (e.path.get(s.index) % 19260817);
                result %= 19260817;
                s = graph[s.pre];
            }
        }
        return result;
//        return (int) Math.pow(10, dist[n - 1]) % 19260817;
    }
    
    static void dijkstra(int src) {
        dist = new double[n];
        Boolean[] sptSet = new Boolean[n];
        // Initialize all distances as INFINITE and stpSet[] as false
        for (int i = 0; i < n; i++) {
            dist[i] = Double.MAX_VALUE;
            sptSet[i] = false;
        }
        dist[src] = 0;
        // Find shortest path for all vertices
        for (int cnt = 0; cnt < n - 1; cnt++) {
            int u = minDistance(dist, sptSet);
            sptSet[u] = true;
            vertex v = graph[u];
            
            // Update dist value of the adjacent vertices of the picked vertex.
            for (int k : v.path.keySet()) {
                if (!sptSet[k] && dist[u] != Double.MAX_VALUE && dist[u] + Math.log10(graph[u].path.get(k)) < dist[k]) {
                    dist[k] = (dist[u] + Math.log10(graph[u].path.get(k)));
                    graph[k].pre = u;
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
