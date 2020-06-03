import java.io.*;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Vince_BlackAndVince_White {
    static class edge {
        int start, end;
        int capacity, flow, direct, index;

        edge(int s, int e, int c, int d, int index) {
            start = s;
            end = e;
            capacity = c;
            flow = d;
            direct = d;
            this.index = index;
        }
    }

    static class node {
        int index, color;
        LinkedList<Integer> next;

        node(int index, int color) {
            this.index = index;
            this.color = color;
            next = new LinkedList<>();
//            rnext = new LinkedList<>();
        }
    }

    static void augment(LinkedList<edge> p, edge[] edges, int n) {
        for (edge e :
                p) {
            if (e.direct == 1) {
                edges[e.index - 1].flow = 0;
                e.flow += 1;
//                e.capacity -= 1;
            } else {
                e.flow += 1;
                edges[e.index + 1].flow = 0;
//                if (e.start != 0 && e.end != n + 1)
//                    edges[e.index + 1].capacity = 1;
            }
        }
    }

    static int BFS(node[] nodes, int s, int t, edge[] edges, int[] visit) {
        Queue<Integer> nodeQueue = new LinkedList<>();
        int current = s;
        nodeQueue.add(s);
        visit[0] = 1;
        while (nodeQueue.size() > 0 && !nodes[current].next.isEmpty()) {
            current = nodeQueue.poll();
            if (current == t) {
                return 1;
            }
            for (int i :
                    nodes[current].next) {
                if (edges[i].capacity - edges[i].flow > 0 && visit[edges[i].end] == 0) {
                    if (edges[i].end == t) {
                        return 1;
                    }
                    nodeQueue.add(edges[i].end);
                    visit[edges[i].end] = 1;
                }
            }
        }
        return 0;
    }

    static int DFS2(node[] nodes, int s, int t, edge[] edges, LinkedList<edge> p, int[] visit) {
        Deque<Integer> nodeQueue = new LinkedList<>();
        int current = s;
        nodeQueue.add(s);
        visit[0] = 1;
        LOOP:
        while (nodeQueue.size() > 0) {
            current = nodeQueue.getLast();
            if (current == t) {
                return 1;
            }
            for (int i :
                    nodes[current].next) {
                edge e = edges[i];
                if (visit[e.end] == 0 && e.capacity - e.flow > 0) {
                    p.add(e);
                    nodeQueue.add(e.end);
                    visit[e.end] = 1;
                    continue LOOP;
                }
            }
            nodeQueue.removeLast();
            p.removeLast();
        }
        return 0;
    }

    static int DFS(node[] nodes, int s, int t, edge[] edges, LinkedList<edge> p, int[] visit) {
        for (int i = 0; i < nodes[s].next.size(); i++) {
            edge e = edges[nodes[s].next.get(i)];
            if (e.capacity - e.flow > 0 && visit[e.end] == 0) {
                if (e.end == t) {
                    p.add(e);
                    return 1;
                } else {
                    p.add(e);
                    visit[e.end] = 1;
                    if (DFS(nodes, e.end, t, edges, p, visit) == 1) {
                        return 1;
                    }
                }
            }
        }
        if (!p.isEmpty()) {
            visit[p.peekLast().end] = 0;
            p.removeLast();
        }
        return 0;
    }

    static void Ford_Fulkerson(node[] G, int n, edge[] edges) {
        LinkedList<edge> path = new LinkedList<>();
        int[] visit = new int[n + 2];
//        while (DFS(G, 0, n + 1, edges, path, visit) == 1) {
        while (BFS(G, 0, n + 1, edges, visit) == 1) {
            visit = new int[n + 2];
            if (DFS2(G, 0, n + 1, edges, path, visit) != 1) {
                continue;
            }
            augment(path, edges, n);
            visit = new int[n + 2];
            path.clear();
        }
    }

    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        int n, m, e = 0;
        n = in.nextInt();
        m = in.nextInt();
        node[] nodes = new node[n + 2];
        edge[] edges = new edge[2 * m + n];
//        edge[] redges = new edge[m];
//        ArrayList<edge> edges = new ArrayList<>();
        nodes[0] = new node(0, 1);
        nodes[n + 1] = new node(n + 1, 0);
        for (int i = 1; i <= n; i++) {
            nodes[i] = new node(i, in.nextInt());
            if (nodes[i].color == 1) {
                edges[e] = new edge(0, i, 1, 0, e);
                nodes[0].next.add(e++);
            } else {
                edges[e] = new edge(i, n + 1, 1, 0, e);
                nodes[i].next.add(e++);
            }
        }
        int u, v;
        for (int i = 0; i < m; i++) {
            u = in.nextInt();
            v = in.nextInt();
            edges[e] = new edge(u, v, 1, 0, e);
            nodes[u].next.add(e++);
            edges[e] = new edge(v, u, 1, 1, e);
            nodes[v].next.add(e++);
        }
        Ford_Fulkerson(nodes, n, edges);
        int max_flow = 0;
        for (int i = 0; i < nodes[0].next.size(); i++) {
            max_flow += edges[nodes[0].next.get(i)].flow;
        }
        out.println(max_flow);
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
