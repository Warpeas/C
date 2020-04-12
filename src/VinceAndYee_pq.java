import java.io.*;
import java.util.*;

public class VinceAndYee_pq {
    static InputStream inputStream = System.in;
    static OutputStream outputStream = System.out;
    static InputReader in = new InputReader(inputStream);
    static PrintWriter out = new PrintWriter(outputStream);
    static int n;
    
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
            return w - o.w;
        }
    }
    
    static int[] nodes;
    static Set<Integer> originalSet;
    static Map<Integer, Set<Integer>> setMap = new HashMap<>();
    static PriorityQueue<path> paths = new PriorityQueue<>();
    
    static long addToSet() {
        long result = 0;
        int s1, s2, cnt = 1;
        while (setMap.entrySet().isEmpty() || !setMap.entrySet().iterator().next().getValue().containsAll(originalSet)) {
            assert paths.peek() != null;
            int n1, n2;
            n1 = paths.peek().index1;
            n2 = paths.peek().index2;
            s1 = nodes[n1];
            s2 = nodes[n2];
            if (s1 != s2 && s1 != 0 && s2 != 0) {
                result += paths.peek().w;
                if (s1 > s2) {
                    for (int n :
                            setMap.get(s1)) {
                        nodes[n] = s2;
                    }
                    setMap.get(s2).addAll(setMap.get(s1));
                    setMap.remove(s1);
                } else {
                    for (int n :
                            setMap.get(s2)) {
                        nodes[n] = s1;
                    }
                    setMap.get(s1).addAll(setMap.get(s2));
                    setMap.remove(s2);
                }
            } else if (s1 == 0 && s2 != 0) {
                result += paths.peek().w;
                setMap.get(s2).add(n1);
                nodes[n1] = s2;
            } else if (s1 != 0 && s2 == 0) {
                result += paths.peek().w;
                setMap.get(s1).add(n2);
                nodes[n2] = s1;
            } else if (s1 == 0) {
                result += paths.peek().w;
                setMap.put(cnt, new HashSet<>());
                setMap.get(cnt).add(n1);
                setMap.get(cnt).add(n2);
                nodes[n1] = cnt;
                nodes[n2] = cnt++;
            }
            paths.remove();
        }
        return result;
    }
    
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        n = in.nextInt();
        originalSet = new HashSet<>();
        for (int i = 0; i < n + 1; i++) {
            originalSet.add(i);
        }
        int weight;
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                weight = in.nextInt();
                paths.add(new path(i, j + 1, weight));
            }
        }
        nodes = new int[n + 1];
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

