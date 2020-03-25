import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Elites_In_the_Casino {
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

    public static class count implements Comparable<count> {
        long length;
        int cnt;

        count(long i) {
            length = i;
            cnt = 1;
        }

        @Override
        public int compareTo(count count) {
            return (int) (length - count.length);
        }
    }

    static ArrayList<count> counts = new ArrayList<>();

    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        FastIO.InputReader in = new FastIO.InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        String[] numbers;
        TreeNode cnt = null;
        //ArrayList<count> counts = new ArrayList<>();
        int n = in.nextInt();
        numbers = new String[n];
        for (int i = 0; i < n; i++) {
            numbers[i] = in.next();
            cnt = TreeNode.insert(cnt, numbers[i].length());
        }
        while (cnt != null) {
            counts.add(cnt.cnt);
            cnt = cnt.delete(cnt, cnt.cnt.length);
        }
        //counts.sort(count::compareTo);
        long result = 0;
        //  calculation
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < numbers[i].length(); j++) {
                int b = Integer.parseInt(numbers[i].substring(j, j + 1));
                for (int k = 0; k < counts.size(); k++) {
                    result += counts.get(k).cnt * b * pow(j + counts.get(k).length);
                    if (b + 1 >= counts.get(k).length) {
                        result += counts.get(k).cnt * b * pow(j + counts.get(k).length);
                    }
                    if (b + 1 <= counts.get(k).length) {
                        result += counts.get(k).cnt * b * pow(j + counts.get(k).length - 1);
                    }
                }
            }
        }
        out.print(result);
        out.close();
    }

    public static long pow(long n) {
        long result = 1;
        for (int i = 0; i < n; i++) {
            result *= 10;
        }
        return result;
    }

    private static class TreeNode {
        count cnt;
        long size = 1;
        TreeNode left;
        TreeNode right;

        TreeNode(long index) {
            cnt = new count(index);
        }

        public static TreeNode insert(TreeNode root, long key) {
            if (root == null) {
                root = new TreeNode(key);
                //counts.add(root.cnt);
            } else {
                root.size++;
                if (key > root.cnt.length) {
                    root.right = insert(root.right, key);
                } else if (key == root.cnt.length) {
                    root.cnt.cnt++;
                } else {
                    root.left = insert(root.left, key);
                }
                root = root.maintain(root, key > root.cnt.length);
            }
            return root;
        }

        public TreeNode delete(TreeNode root, long key) {
            root.size--;
            if (key > root.cnt.length) {
                root.right = delete(root.right, key);
            } else if (key < root.cnt.length) {
                root.left = delete(root.left, key);
            } else {
                if (root.left == null && root.right == null) {
                    root = null;
                } else if (root.left == null) {
                    root = root.right;
                } else if (root.right == null) {
                    root = root.left;
                } else {
                    TreeNode tmp = root.right;
                    while (tmp.left != null) {
                        tmp = tmp.left;
                    }
                    root.cnt.length = tmp.cnt.length;
                    root.right = delete(root.right, tmp.cnt.length);
                }
            }
            return root;
        }

        TreeNode find(TreeNode root, int i) {
            if (i == root.cnt.length) {
                return root;
            } else if (i < root.cnt.length) {
                if (root.left == null) {
                    return null;
                } else {
                    return find(root.left, i);
                }
            } else {
                if (root.right == null) {
                    return null;
                } else {
                    return find(root.right, i);
                }
            }
        }

        TreeNode maintain(TreeNode node, boolean flag) {
            if (node != null) {
                if (!flag) {
                    if (node.left != null && node.right == null && node.left.left != null) {
                        node = LL(node);
                    } else if ((node.left != null && node.right != null && node.left.left != null && node.left.left.size > node.right.size)) {
                        node = LL(node);
                    } else if (node.left != null && node.right != null && node.left.right != null && node.left.right.size > node.right.size) {
                        node.left = LR(node.left);
                        node = LL(node);
                    } else
                        return node;
                } else {
                    if (node.right != null && node.left == null && node.right.right != null) {
                        node = LR(node);
                    } else if (node.right != null && node.left != null && node.right.right != null && node.right.right.size > node.left.size) {
                        node = LR(node);
                    } else if (node.right != null && node.left != null && node.right.left != null && node.right.left.size > node.left.size) {
                        node.right = LL(node.right);
                        node = LR(node);
                    } else
                        return node;
                }
                node.left = maintain(node.left, false);
                node.right = maintain(node.right, true);
            }
            return node;
        }

        TreeNode LL(TreeNode node) {
            long nls = 0, nrs = 0;
            TreeNode tmp = node.left;
            node.left = tmp.right;
            if (node.left != null)
                nls = node.left.size;
            if (node.right != null)
                nrs = node.right.size;
            tmp.right = node;
            tmp.size = node.size;
            node.size = nls + nrs + 1;
            return tmp;
        }

        TreeNode LR(TreeNode node) {
            long nls = 0, nrs = 0;
            TreeNode tmp = node.right;
            node.right = tmp.left;
            if (node.left != null)
                nls = node.left.size;
            if (node.right != null)
                nrs = node.right.size;
            tmp.left = node;
            tmp.size = node.size;
            node.size = nls + nrs + 1;
            return tmp;
        }
    }
}
