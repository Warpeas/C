
import java.io.*;
import java.util.*;

import static java.util.Arrays.sort;

public class MalaysianTranslator {
//    static int Huffuman(Integer[] a, int n) {
//        int sum;
//        sort(a);
//        if (n > 1) {
//            sum = a[0] + a[1];
//            a[1] = sum;
//            Integer[] b = new Integer[n - 1];
//            for (int i = 0; i < n - 1; i++) {
//                b[i] = a[i + 1];
//            }
//            return sum + Huffuman(b, n - 1);
//        }
//        return 0;
//    }
    
    static class c {
        static int index = 0;
        char c;
        int cnt;
    }
    
    static Map<Character, Integer> map;
    static PriorityQueue<Integer> priorityQueue;
    
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        int n = in.nextInt();
        char[] chars;
        for (int j = 0; j < n; j++) {
            map = new HashMap<>();
            priorityQueue = new PriorityQueue<>();
            chars = in.nextCharArray();
            for (char c : chars) {
                if (!map.containsKey(c)) {
                    map.put(c, 1);
                } else {
                    map.put(c, map.get(c) + 1);
                }
            }
            priorityQueue.addAll(map.values());
            int result = 0;
            int a, b, temp;
            while (priorityQueue.size() > 1) {
                a = priorityQueue.poll();
                b = priorityQueue.poll();
                temp = a + b;
                result += temp;
                priorityQueue.add(temp);
            }
            out.println(result);
//            out.println(Huffuman(map.values().toArray(new Integer[0]), map.values().size()));
        }
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
