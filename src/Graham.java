import java.io.*;
import java.util.*;

public class Graham {
    static class node {
        int x, y;
        double tan, dist;

        node(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public void calculate(node root) {
            if (x != root.x) {
                tan = ((double) y - root.y) / (x - root.x);
            } else {
                if (y > root.y) {
                    tan = Double.MAX_VALUE;
                } else if (y < root.y) {
                    tan = -(Double.MAX_VALUE - 1);
                } else {
                    tan = 0;
                }
            }
            dist = Math.pow(y - root.y, 2) + Math.pow(x - root.x, 2);
        }

        public boolean isAtTheSameSideWith_Of(node root, node a, node b) {
            double tan = ((double) a.y - b.y) / (a.x - b.y);
            double l = (double) a.y - tan * a.x;
            double l1 = (double) root.y - tan * root.x;
            double l2 = (double) y - tan * x;
            return l1 > l & l2 > l;
        }
    }

    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        int n = in.nextInt();
//        ArrayList<node> nodes = new ArrayList<>();
        node[] nodes = new node[n];
        node root = null;
        for (int i = 0; i < n; i++) {
            nodes[i] = (new node(in.nextInt(), in.nextInt()));
            if (root == null || nodes[i].x < root.x && nodes[i].y < root.y) {
                root = nodes[i];
            }
        }
        long start = System.currentTimeMillis();
        for (int i = 0; i < n; i++) {
            nodes[i].calculate(root);
        }
//        nodes.remove(root);
//        Map<Double, node> tan_to_node = new HashMap<>();
//        for (int i = 0; i < nodes.size(); i++) {
//            assert root != null;
//            nodes.get(i).calculate(root);
//            if (!tan_to_node.containsKey(nodes.get(i).tan)) {
//                tan_to_node.put(nodes.get(i).tan, nodes.get(i));
//            } else if (tan_to_node.get(nodes.get(i).tan).dist < nodes.get(i).dist) {
//                tan_to_node.replace(nodes.get(i).tan, nodes.get(i));
//            }
//        }
//        for (int i = 0; i < n; i++) {
//            nodes[i] = new node(in.nextInt(), in.nextInt());
//        }
        Arrays.sort(nodes, (node, t1) -> {
            if (node.dist > 0 && node.tan - t1.tan > 0 || (node.tan == t1.tan && node.dist > t1.dist)) {
                return 1;
            } else if (node.tan == t1.tan && node.dist == t1.dist) {
                return 0;
            }
            return -1;
        });
//        root = nodes[0];

        Stack<node> nodeStack = new Stack<>();
//        Queue<node> nodeQueue = new PriorityQueue<>((node, t1) -> {
//            if (node.tan - t1.tan > 0) {
//                return 1;
//            } else if (node.tan == t1.tan) {
//                return 0;
//            }
//            return -1;
//        });
//        nodeQueue.addAll(tan_to_node.values());
//        nodeStack.push(nodeQueue.poll());
//        nodeStack.push(nodeQueue.poll());
        nodeStack.push(nodes[0]);
        nodeStack.push(nodes[1]);
        nodeStack.push(nodes[2]);
//        while (!nodeQueue.isEmpty()) {
        for (int i = 3; i < n; i++) {
            node s = nodeStack.pop();
//            node b = nodeQueue.poll();
            node b = nodes[i];
            if (!s.isAtTheSameSideWith_Of(root, nodeStack.peek(), b)) {
                nodeStack.push(s);
            }
            nodeStack.push(b);
        }
        out.println(nodeStack.size());
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
