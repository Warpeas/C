
import java.io.*;
import java.util.StringTokenizer;

public class likeHearthstone {
    static class card {
        int cost, value;
        
        card(int c, int v) {
            cost = c;
            value = v;
        }
    }
    
    public static int pack(card[] cards, int c) {
        int[][] bag = new int[c + 1][31];
//        int[][] num = new int[2][c + 1];
        bag[0][0] = 0;
        for (int i = 1; i <= c; i++) {
            for (int j = 1; j <= 30; j++) {
                bag[i][j] = Integer.MIN_VALUE;
            }
        }
        for (int i = 1; i <= 90; i++) {
            for (int j = cards[i].cost; j <= c; j++) {
                for (int k = 1; k <= 30; k++) {
                    bag[j][k] = Math.max(bag[j][k], cards[i].value + bag[j - cards[i].cost][k - 1]);
                }
            }
        }
        return bag[c][30];
    }
    
    public static int pack2(card[] cards, int c) {
        int[][] bag = new int[c + 1][31];
//        int[][] num = new int[2][c + 1];
        bag[0][0] = 0;
        for (int i = 1; i <= c; i++) {
            for (int j = 1; j <= 30; j++) {
                bag[i][j] = Integer.MIN_VALUE;
            }
        }
        for (int i = 1; i <= 90; i++) {
            for (int j = c; j >= cards[i].cost; j--) {
                for (int k = 30; k >= 1; k--) {
                    bag[j][k] = Math.max(bag[j][k], cards[i].value + bag[j - cards[i].cost][k - 1]);
                }
            }
        }
        return bag[c][30];
    }
    
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        int c = in.nextInt();
        card[] cards = new card[91];
        int cost, a, h;
        for (int i = 1; i < 91; i++) {
            cost = in.nextInt();
            a = in.nextInt();
            h = in.nextInt();
            cards[i] = new card(cost, a + h);
        }
        out.println(pack(cards, c));
        out.println(pack2(cards, c));
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
