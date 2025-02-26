// B树节点类
class BTreeNode {
    int[] keys;          // 存储键的数组
    int t;               // 最小度数，决定节点的容量
    BTreeNode[] children;// 子节点数组
    int n;               // 当前节点中键的数量
    boolean leaf;        // 是否为叶子节点

    // 构造函数
    public BTreeNode(int t, boolean leaf) {
        this.t = t;
        this.leaf = leaf;
        // 键的最大数量为2t-1，子节点数量为2t
        this.keys = new int[2 * t - 1];
        this.children = new BTreeNode[2 * t];
        this.n = 0;      // 初始化时键的数量为0
    }

    // 遍历子树，中序遍历输出所有键
    public void traverse() {
        int i;
        for (i = 0; i < n; i++) {
            // 如果非叶子节点，先遍历子节点
            if (!leaf) children[i].traverse();
            System.out.print(keys[i] + " ");
        }
        // 遍历最后一个子节点
        if (!leaf) children[i].traverse();
    }

    // 在节点中搜索键k
    public BTreeNode search(int k) {
        int i = 0;
        // 找到第一个不小于k的键的位置
        while (i < n && k > keys[i]) i++;
        // 如果找到相等的键，返回当前节点
        if (i < n && keys[i] == k) return this;
        // 如果是叶子节点且未找到，返回null，否则递归搜索子节点
        return leaf ? null : children[i].search(k);
    }

    // 从节点中删除键k
    public void remove(int k) {
        int idx = findKey(k); // 找到k的位置
        // 如果键存在于当前节点
        if (idx < n && keys[idx] == k) {
            if (leaf)
                removeFromLeaf(idx);  // 从叶子节点删除
            else
                removeFromNonLeaf(idx); // 从内部节点删除
        } else {
            if (leaf) return; // 如果不在叶子节点，结束
            // 标记是否在最后一个子节点
            boolean flag = (idx == n);
            // 如果子节点键不足，需要填充
            if (children[idx].n < t)
                fill(idx);
            // 如果填充导致合并，调整索引
            if (flag && idx > n)
                children[idx - 1].remove(k);
            else
                children[idx].remove(k);
        }
    }

    // 找到第一个不小于k的键的索引
    private int findKey(int k) {
        int idx = 0;
        while (idx < n && keys[idx] < k)
            idx++;
        return idx;
    }

    // 从叶子节点删除键
    private void removeFromLeaf(int idx) {
        // 将后面的键前移，覆盖被删除的键
        for (int i = idx; i < n - 1; i++)
            keys[i] = keys[i + 1];
        n--; // 减少键数量
    }

    // 从内部节点删除键
    private void removeFromNonLeaf(int idx) {
        int k = keys[idx];
        // 如果左子节点足够，用前驱替换并递归删除
        if (children[idx].n >= t) {
            int pred = getPredecessor(idx);
            keys[idx] = pred;
            children[idx].remove(pred);
        }
        // 如果右子节点足够，用后继替换并递归删除
        else if (children[idx + 1].n >= t) {
            int succ = getSuccessor(idx);
            keys[idx] = succ;
            children[idx + 1].remove(succ);
        }
        // 左右子节点都不够，合并后递归删除
        else {
            merge(idx);
            children[idx].remove(k);
        }
    }

    // 获取前驱键（左子树的最大键）
    private int getPredecessor(int idx) {
        BTreeNode cur = children[idx];
        while (!cur.leaf)
            cur = cur.children[cur.n]; // 找到最右子节点
        return cur.keys[cur.n - 1]; // 返回最后一个键
    }

    // 获取后继键（右子树的最小键）
    private int getSuccessor(int idx) {
        BTreeNode cur = children[idx + 1];
        while (!cur.leaf)
            cur = cur.children[0]; // 找到最左子节点
        return cur.keys[0]; // 返回第一个键
    }

    // 填充不足的子节点
    private void fill(int idx) {
        // 从前一个兄弟借键
        if (idx != 0 && children[idx - 1].n >= t)
            borrowFromPrev(idx);
            // 从后一个兄弟借键
        else if (idx != n && children[idx + 1].n >= t)
            borrowFromNext(idx);
            // 合并兄弟节点
        else {
            if (idx != n)
                merge(idx);
            else
                merge(idx - 1);
        }
    }

    // 从前一个兄弟节点借键
    private void borrowFromPrev(int idx) {
        BTreeNode child = children[idx];
        BTreeNode sibling = children[idx - 1];
        // 将当前节点的键后移
        for (int i = child.n - 1; i >= 0; i--)
            child.keys[i + 1] = child.keys[i];
        // 若非叶子节点，子节点也后移
        if (!child.leaf) {
            for (int i = child.n; i >= 0; i--)
                child.children[i + 1] = child.children[i];
        }
        // 将父节点的键下移，兄弟节点的键上移
        child.keys[0] = keys[idx - 1];
        if (!child.leaf)
            child.children[0] = sibling.children[sibling.n];
        keys[idx - 1] = sibling.keys[sibling.n - 1];
        // 更新键数量
        child.n++;
        sibling.n--;
    }

    // 从后一个兄弟节点借键
    private void borrowFromNext(int idx) {
        BTreeNode child = children[idx];
        BTreeNode sibling = children[idx + 1];
        // 父节点键下移，兄弟节点键前移
        child.keys[child.n] = keys[idx];
        if (!child.leaf)
            child.children[child.n + 1] = sibling.children[0];
        keys[idx] = sibling.keys[0];
        for (int i = 1; i < sibling.n; i++)
            sibling.keys[i - 1] = sibling.keys[i];
        // 若非叶子节点，子节点前移
        if (!sibling.leaf) {
            for (int i = 1; i <= sibling.n; i++)
                sibling.children[i - 1] = sibling.children[i];
        }
        // 更新键数量
        child.n++;
        sibling.n--;
    }

    // 合并两个子节点
    private void merge(int idx) {
        BTreeNode child = children[idx];
        BTreeNode sibling = children[idx + 1];
        // 将父节点的键下移到子节点
        child.keys[t - 1] = keys[idx];
        // 复制兄弟节点的键和子节点
        System.arraycopy(sibling.keys, 0, child.keys, t, sibling.n);
        if (!child.leaf)
            System.arraycopy(sibling.children, 0, child.children, t, sibling.n + 1);
        // 调整父节点的键和子节点数组
        for (int i = idx + 1; i < n; i++)
            keys[i - 1] = keys[i];
        for (int i = idx + 2; i <= n; i++)
            children[i - 1] = children[i];
        // 更新键数量
        child.n += sibling.n + 1;
        n--;
    }
}

// B树类
class BTree {
    BTreeNode root; // 根节点
    int t;          // 最小度数

    // 构造函数，初始化根节点为叶子
    public BTree(int t) {
        this.root = new BTreeNode(t, true);
        this.t = t;
    }

    // 在非满节点插入键
    private void insertNonFull(BTreeNode node, int k) {
        int i = node.n - 1;
        if (node.leaf) { // 叶子节点直接插入
            // 找到插入位置并后移较大键
            while (i >= 0 && node.keys[i] > k) {
                node.keys[i + 1] = node.keys[i];
                i--;
            }
            node.keys[i + 1] = k;
            node.n++;
        } else { // 内部节点递归插入
            // 找到合适的子节点
            while (i >= 0 && node.keys[i] > k)
                i--;
            // 如果子节点已满，先分裂
            if (node.children[i + 1].n == 2 * t - 1) {
                splitChild(node, i + 1, node.children[i + 1]);
                // 分裂后可能需要调整插入位置
                if (k > node.keys[i + 1])
                    i++;
            }
            insertNonFull(node.children[i + 1], k);
        }
    }

    // 分裂子节点
    private void splitChild(BTreeNode parent, int i, BTreeNode child) {
        // 创建新节点，复制后半部分键和子节点
        BTreeNode newNode = new BTreeNode(child.t, child.leaf);
        newNode.n = t - 1;
        System.arraycopy(child.keys, t, newNode.keys, 0, t - 1);
        if (!child.leaf)
            System.arraycopy(child.children, t, newNode.children, 0, t);
        child.n = t - 1; // 原子节点保留前t-1个键
        // 将新节点插入父节点
        for (int j = parent.n; j > i; j--)
            parent.children[j + 1] = parent.children[j];
        parent.children[i + 1] = newNode;
        // 将中间键提升到父节点
        for (int j = parent.n - 1; j >= i; j--)
            parent.keys[j + 1] = parent.keys[j];
        parent.keys[i] = child.keys[t - 1];
        parent.n++;
    }

    // 遍历整棵树
    public void traverse() {
        if (root != null)
            root.traverse();
        System.out.println();
    }

    // 搜索键k
    public BTreeNode search(int k) {
        return (root == null) ? null : root.search(k);
    }

    // 插入键k
    public void insert(int k) {
        // 根节点已满，需要分裂
        if (root.n == 2 * t - 1) {
            BTreeNode s = new BTreeNode(t, false);
            s.children[0] = root;
            splitChild(s, 0, root);
            root = s; // 更新根节点
        }
        insertNonFull(root, k);
    }

    // 删除键k
    public void remove(int k) {
        if (root == null) return;
        root.remove(k);
        // 根节点无键后，降低树的高度
        if (root.n == 0) {
            root = root.leaf ? null : root.children[0];
        }
    }

    // 测试主函数
    public static void main(String[] args) {
        BTree tree = new BTree(3); // 最小度数t=3
        int[] keys = {10, 20, 5, 6, 12, 30, 7, 17};
        // 插入测试数据
        for (int key : keys)
            tree.insert(key);
        System.out.println("Traversal before deletion:");
        tree.traverse();
        tree.remove(6); // 删除键6
        System.out.println("Traversal after deletion of 6:");
        tree.traverse();
    }
}