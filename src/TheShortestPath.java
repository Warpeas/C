import java.io.*;
import java.lang.*;
import java.util.StringTokenizer;

class TheShortestPath {
    static int n, m;
    static InputStream inputStream = System.in;
    static OutputStream outputStream = System.out;
    static InputReader in = new InputReader(inputStream);
    static PrintWriter out = new PrintWriter(outputStream);
    
    static int minDistance(double[] dist, Boolean[] sptSet) {
        double min = Integer.MAX_VALUE;
        int min_index = -1;
        for (int v = 0; v < n; v++)
            if (!sptSet[v] && dist[v] <= min) {
                min = dist[v];
                min_index = v;
            }
        return min_index;
    }
    
    static int printSolution(double[] dist) {
        return (int) Math.pow(10, dist[n - 1]) % 19260817;
    }
    
    static void dijkstra(int[][] graph, int src) {
        double[] dist = new double[n]; // The output array. dist[i] will hold the shortest distance from src to i
        Boolean[] sptSet = new Boolean[n];
        // Initialize all distances as INFINITE and stpSet[] as false
        for (int i = 0; i < n; i++) {
            dist[i] = Integer.MAX_VALUE;
            sptSet[i] = false;
        }
        dist[src] = 0;
        
        // Find shortest path for all vertices
        for (int cnt = 0; cnt < n - 1; cnt++) {
            int u = minDistance(dist, sptSet);
            sptSet[u] = true;
            
            // Update dist value of the adjacent vertices of the picked vertex.
            for (int v = 0; v < n; v++) {
                // Update dist[v] only if is not in sptSet, there is an
                // edge from u to v, and total weight of path from src to
                // v through u is smaller than current value of dist[v]
                if (!sptSet[v] && graph[u][v] != 0 && dist[u] != Integer.MAX_VALUE && dist[u] + Math.log10(graph[u][v]) < dist[v]) {
                    dist[v] = (dist[u] + Math.log10(graph[u][v]) % 19260817) % 19260817;
                }
            }
        }
        out.println(printSolution(dist));
    }
    
    public static void main(String[] args) {
        n = in.nextInt();
        m = in.nextInt();
        int[][] graph = new int[n][n];
        int u, v, w;
        for (int i = 0; i < m; i++) {
            u = in.nextInt();
            v = in.nextInt();
            w = in.nextInt();
            graph[u - 1][v - 1] = w;
        }
        dijkstra(graph, 0);
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
