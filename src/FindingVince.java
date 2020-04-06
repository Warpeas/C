import java.io.*;
import java.util.*;

public class FindingVince {
    static int n;
    static vertex[] graph;
    static HashMap<Integer, Long> dist;
    static InputStream inputStream = System.in;
    static OutputStream outputStream = System.out;
    static InputReader in = new InputReader(inputStream);
    static PrintWriter out = new PrintWriter(outputStream);
    
    static class vertex {
        int index;
        int time, pre;
        int a, b;
        HashMap<Integer, Integer> path;
        
        vertex(int i) {
            index = i;
            path = new HashMap<>();
        }
    }
    
    static int minDistance(HashMap<Integer, Long> dist, Boolean[] sptSet) {
        double min = Double.MAX_VALUE;
        int min_index = -1;
        for (int v = 0; v < n; v++)
            if (!sptSet[v] && dist.get(v) <= min) {
                min = dist.get(v);
                min_index = v;
            }
        return min_index;
    }
    
    static long printSolution() {
//        long result = 1;
//        vertex s = graph[n - 1];
//        vertex e;
//        for (int i = 1; i < n; i++) {
//            if (s.index != 0) {
//                e = graph[s.pre];
//                result *= (e.path.get(s.index) % 19260817);
//                result %= 19260817;
//                s = graph[s.pre];
//            }
//        }
//        return result;
        return dist.get(n - 1);
    }
    
    static class comparator implements Comparator<Integer> {
        Map<Integer, Long> base;
        
        public comparator(Map<Integer, Long> base) {
            this.base = base;
        }
        
        public int compare(Integer a, Integer b) {
            if (base.get(a) >= base.get(b)) {
                return -1;
            } else {
                return 1;
            } // returning 0 would merge keys
        }
    }
    
    public static <K, V extends Comparable<? super V>> HashMap<K, V> sortByValue(Map<K, V> map) {
        List<Map.Entry<K, V>> list = new ArrayList<>(map.entrySet());
        list.sort(Map.Entry.comparingByValue());
        
        HashMap<K, V> result = new LinkedHashMap<>();
        for (Map.Entry<K, V> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }
        
        return result;
    }
    
    static void dijkstra(int src) {
        graph[src].time = 0;
        comparator cmp = new comparator(dist);
        dist = new HashMap<>();
        Boolean[] sptSet = new Boolean[n];
        // Initialize all distances as INFINITE and stpSet[] as false
        for (int i = 0; i < n; i++) {
            dist.put(i, Long.MAX_VALUE);
            sptSet[i] = false;
        }
        int diff, actual;
        dist.replace(src, (long) 0);
        // Find shortest path for all vertices
        for (int cnt = 0; cnt < n - 1; cnt++) {
            dist = sortByValue(dist);
            int u = minDistance(dist, sptSet);
            sptSet[u] = true;
            vertex v = graph[u];
            
            for (int k : v.path.keySet()) {
                diff = (v.time + v.path.get(k)) % (graph[k].a + graph[k].b);
                actual = v.path.get(k);
                if (diff < graph[k].a) {
                    actual += graph[k].a - diff;
                }
                if (!sptSet[k] && dist.get(u) != Double.MAX_VALUE && dist.get(u) + actual < dist.get(k)) {
                    dist.replace(k, dist.get(u) + actual);
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
