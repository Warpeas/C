#include <iostream>
#include "string"
using namespace std;

    static int n;
    
    int main() {
        int t;
        cin >> t;
        for (int i = 0; i < t; i++) {
            
        }
    }
    
    class TreeNode {
        private:
        int index;
        TreeNode *father;
        TreeNode *left;
        TreeNode *right;
        
        public:
        TreeNode(int index) {
            this->index = index;
        }
        
        static TreeNode buildBinaryHeap() {
            cin >> n;
//            TreeNode[] treeNodes = new TreeNode[n + 1];
//            treeNodes[0] = new TreeNode(0);
            int index;
            cin >> index;
            TreeNode *root = new TreeNode(index);
            for (int i = 1; i <= n; i++) {
                cin >> index;
                TreeNode *tmp = new TreeNode(index);
                root->insert(tmp, i);
            }
            return NULL;
        }
        
        void insert(TreeNode *node, int cnt) {
        string str = toBinary(cnt);
        TreeNode *tmp = this;
            for (int i = 1; i < str.length(); i++) {
                if (str[i]=='0'){
                    tmp = tmp->left;
                }else
                    tmp = tmp->right;
            }
            tmp = node;
        }
        
        string toBinary(int cnt) {
            string str = "";
            while (cnt != 0) {
                // str = str + (cnt % 2);
                cnt /= 2;
            }
            return str;
        }
    };
