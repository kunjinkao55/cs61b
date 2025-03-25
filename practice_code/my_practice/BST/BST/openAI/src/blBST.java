import java.util.ArrayDeque;

public class blBST {
    private int key;
    private blBST left;
    private blBST right;
    private int balance = 0;

    public blBST(int key) {
        this.key = key;
        this.left = null;
        this.right = null;
    }

    public static blBST insert(blBST root, int key) {
        if (root == null) {
            return new blBST(key);
        }

        if (key < root.key) {
            root.left = insert(root.left, key);
        } else {
            root.right = insert(root.right, key);
        }

        return balance(root);
    }

    public static blBST delete(blBST root, int key) {
        if (root == null) {
            return null;
        }

        if (key < root.key) {
            root.left = delete(root.left, key);
        } else if (key > root.key) {
            root.right = delete(root.right, key);
        } else {
            // 找到待删除节点
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            } else {
                // 找到右子树的最小值替换当前节点
                blBST minNode = findMin(root.right);
                root.key = minNode.key;
                root.right = delete(root.right, minNode.key);
            }
        }
        return balance(root);
    }

    private static blBST findMin(blBST node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    private static int height(blBST node) {
        return (node == null) ? -1 : Math.max(height(node.left), height(node.right)) + 1;
    }

    private static int getBalanceFactor(blBST node) {
        return (node == null) ? 0 : height(node.left) - height(node.right);
    }

    private static blBST balance(blBST root) {
        if (root == null) {
            return null;
        }

        root.balance = getBalanceFactor(root);

        if (root.balance > 1) {
            if (getBalanceFactor(root.left) < 0) {
                root.left = leftRotate(root.left);
            }
            return rightRotate(root);
        }

        if (root.balance < -1) {
            if (getBalanceFactor(root.right) > 0) {
                root.right = rightRotate(root.right);
            }
            return leftRotate(root);
        }

        return root;
    }

    private static blBST leftRotate(blBST root) {
        blBST newRoot = root.right;
        root.right = newRoot.left;
        newRoot.left = root;
        return newRoot;
    }

    private static blBST rightRotate(blBST root) {
        blBST newRoot = root.left;
        root.left = newRoot.right;
        newRoot.right = root;
        return newRoot;
    }

    public void inOrderTraversal() {
        if (left != null) {
            left.inOrderTraversal();
        }
        System.out.print(key + " ");
        if (right != null) {
            right.inOrderTraversal();
        }
    }

    public void levelOrderPrint() {
        System.out.println();
        ArrayDeque<blBST> queue = new ArrayDeque<>();
        queue.add(this);
        while (!queue.isEmpty()) {
            blBST temp = queue.poll();
            System.out.print("  " + temp.key + "  ");
            if (temp.left != null) queue.add(temp.left);
            if (temp.right != null) queue.add(temp.right);
        }
        System.out.println();
    }

    public static void main(String[] args) {
        blBST root = null;
        root = insert(root, 1);
        root = insert(root, 2);
        root = insert(root, 3);
        root = insert(root, 4);
        root = insert(root, 5);
        root = insert(root, 6);
        root = insert(root, 7);
        root = insert(root, 8);
        root = insert(root, 9);

        System.out.print("中序遍历: ");
        root.inOrderTraversal();

        System.out.print("\n层序遍历: ");
        root.levelOrderPrint();

        root = delete(root, 5);
        System.out.print("\n删除5后的层序遍历: ");
        root.levelOrderPrint();
    }
}
