
import java.io.*;
import java.util.*;

public class MalaysianTranslator {
    static Map<Character, Long> map;
    static PriorityQueue<Long> priorityQueue;
    
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        int n = in.nextInt();
        char[] chars;
        map = new HashMap<>();
        priorityQueue = new PriorityQueue<>();
        for (int j = 0; j < n; j++) {
            map.clear();
            priorityQueue.clear();
            chars = in.nextCharArray();
            for (char c : chars) {
                if (!map.containsKey(c)) {
                    map.put(c, (long) 1);
                } else {
                    map.put(c, map.get(c) + 1);
                }
            }
            priorityQueue.addAll(map.values());
            long result = 0;
            long a, b, temp;
            if (priorityQueue.size() == 1) {
                result = priorityQueue.poll();
            } else {
                while (priorityQueue.size() > 1) {
                    a = priorityQueue.poll();
                    b = priorityQueue.poll();
                    temp = a + b;
                    result += temp;
                    priorityQueue.add(temp);
                }
            }
            out.println(result);
        }
        long end = System.currentTimeMillis();
        out.println();
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
