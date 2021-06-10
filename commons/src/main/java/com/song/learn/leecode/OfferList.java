package com.song.learn.leecode;

import com.song.learn.leecode.bean.ListNode;
import com.song.learn.leecode.bean.TreeNode;


/**
 * @Author: Song_
 * @Description:
 * @Date: 2021/6/8 15:58
 */
public class OfferList {


    public static boolean findNumberIn2DArray(int[][] matrix, int target) {

        int x = matrix.length;
        if (x < 1) {
            return false;
        }
        int y = matrix[0].length;

        int i = 0;
        int j = y - 1;

        while (i < x && j >= 0) {
            int cur = matrix[i][j];
            if (cur == target) {
                return true;
            } else if (cur < target) {
                i++;
            } else if (cur > target) {
                j--;
            }
        }
        return false;
    }


    public String replaceSpace(String s) {

        //  return s.replaceAll(" ","%20");

        int len = s.length();

        char[] charArr = new char[len * 3];
        int size = 0;

        for (int i = 0; i < len; i++) {

            if (s.charAt(i) == ' ') {
                charArr[size++] = '%';
                charArr[size++] = '2';
                charArr[size++] = '0';
            } else {
                charArr[size++] = s.charAt(i);
            }

        }

        return new String(charArr, 0, size);


    }

    volatile static int size = 0;
    volatile static int i = 0;
    volatile static int[] res;

    public static void recursive(ListNode node) {
        if (node != null) {
            size++;
            recursive(node.next);
            res[i++] = node.val;
        } else {
            // 最后一个节点，开始创建数组
            res = new int[size];
        }
    }

    public int[] reversePrint(ListNode head) {

        // 使用递归

        recursive(head);

        return res;

        // 使用栈
        /*ArrayDeque<Integer> deque = new ArrayDeque<>();

        while(head!=null){
            deque.push(head.val);
            head = head.next;
        }

        int size = deque.size();

        int[] res = new int[size];
        int i=0;
        while(!deque.isEmpty()){
            res[i++] = deque.pop();
        }
        return res;*/


        // 使用数组
        /*ArrayList<Integer> list = new ArrayList<>();

        while(head != null ){
            list.add(head.val);
            head = head.next;
        }

        int size = list.size();

        int[] res = new int[size];

        for(int i=0;i<size;i++){
            res[i] = list.get(size-i-1);

        }

        return res;*/
    }


    /**
     * 重建二叉树
     *
     * @param preorder
     * @param inorder
     * @return
     */
    public TreeNode buildTreePreIn(int[] preorder, int[] inorder) {

        if (preorder.length < 1 || inorder.length < 1) {
            return null;
        }

        return buildTreeCore(preorder, 0, preorder.length, inorder, 0, inorder.length);


    }

    public static TreeNode buildTreeCore(int[] preOrder, int preStart, int preEnd, int[] inOrder, int inStart, int inEnd) {

        if (preStart > preEnd) {
            return null;
        }

        // 先序遍历的第一个节点就是根节点
        int rootVal = preOrder[preStart];
        // 中序遍历 ：查看根节点在中序遍历中的位置
        int index = 0;
        for (int i = inStart; i <= inEnd; i++) {
            if (inOrder[i] == rootVal) {
                // 找到中序遍历中根节点的位置
                index = i;
                break;
            }
        }

        int leftSize = index - inStart;

        // 构造当前根节点
        TreeNode root = new TreeNode(rootVal);

        // 递归构造左子树
        root.left = buildTreeCore(inOrder, preStart + 1, preStart + leftSize, inOrder, inStart, index - 1);

        // 递归构造右子树
        root.right = buildTreeCore(inOrder, preStart + leftSize + 1, preEnd, inOrder, index + 1, inEnd);

        return root;

    }


    public TreeNode buildTreeInEndCore(int[] inOrder, int inStart, int inEnd, int[] postOrder, int postStart, int postEnd) {

        if (inStart > inEnd) {
            return null;
        }

        // 后序遍历的最后一个节点就是根节点
        int rootVal = postOrder[postEnd];

        // 查找在中序遍历中的位置，确定左右子树的边界
        int rootIndex = 0;
        for (int i = inStart; i < inEnd; i++) {
            if (inOrder[i] == rootVal) {
                rootIndex = i;
                break;
            }
        }

        int leftSize = rootIndex - inStart;

        // 创建根节点
        TreeNode root = new TreeNode(rootVal);

        // 递归创建左子树
        root.left = buildTreeInEndCore(inOrder, inStart, rootIndex - 1, postOrder, postStart, postStart + leftSize - 1);

        // 递归创建右子树
        root.right = buildTreeInEndCore(inOrder, rootIndex + 1, inEnd, postOrder, postStart + leftSize, postEnd - 1);

        return root;

    }

    /**
     * 中序遍历 inorder = [9,3,15,20,7]
     * 后序遍历 postorder = [9,15,7,20,3]
     * 返回如下的二叉树：
     * <p>
     * 3
     * / \
     * 9  20
     * /  \
     * 15   7
     *
     * @param inorder
     * @param postorder
     * @return
     */
    public TreeNode buildTreeInEnd(int[] inorder, int[] postorder) {

        return buildTreeInEndCore(inorder, 0, inorder.length - 1, postorder, 0, postorder.length - 1);
    }


    /**
     * 写一个函数，输入 n ，求斐波那契（Fibonacci）数列的第 n 项（即 F(N)）。斐波那契数列的定义如下：
     * <p>
     * F(0) = 0,   F(1) = 1
     * F(N) = F(N - 1) + F(N - 2), 其中 N > 1.
     *
     * @param n
     * @return
     */

    static int[] fibArr;

    public static int fib(int n) {

        fibArr = new int[n];

        return fibCore(n);

    }


    public static int fibCore(int n) {

        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }

        if (fibArr[n - 1] == 0) {
            fibArr[n - 1] = fibCore(n - 1);
        }
        int pre1 = fibArr[n - 1];


        if (fibArr[n - 2] == 0) {
            fibArr[n - 2] = fibCore(n - 2);
        }
        int pre2 = fibArr[n - 2];

        return (pre1 + pre2) % 1000000007;
    }


    /**
     * 给出二叉 搜索 树的根节点，该树的节点值各不相同，请你将其转换为累加树（Greater Sum Tree），使每个节点 node 的新值等于原树中大于或等于 node.val 的值之和。
     *
     * @param root
     * @return
     */
    int sum = 0;

    public TreeNode convertBST(TreeNode root) {

        if (root != null) {
            convertBST(root.right);
            sum += root.val;
            root.val = sum;
            convertBST(root.left);
        }
        return root;

    }

    /**
     * DFS 解析：
     * 递归参数： 当前元素在矩阵 board 中的行列索引 i 和 j ，当前目标字符在 word 中的索引 k 。
     * 终止条件：
     * 返回 falsefalse ： (1) 行或列索引越界 或 (2) 当前矩阵元素与目标字符不同 或 (3) 当前矩阵元素已访问过 （ (3) 可合并至 (2) ） 。
     * 返回 truetrue ： k = len(word) - 1 ，即字符串 word 已全部匹配。
     * 递推工作：
     * 标记当前矩阵元素： 将 board[i][j] 修改为 空字符 '' ，代表此元素已访问过，防止之后搜索时重复访问。
     * 搜索下一单元格： 朝当前元素的 上、下、左、右 四个方向开启下层递归，使用 或 连接 （代表只需找到一条可行路径就直接返回，不再做后续 DFS ），并记录结果至 res 。
     * 还原当前矩阵元素： 将 board[i][j] 元素还原至初始值，即 word[k] 。
     * 返回值： 返回布尔量 res ，代表是否搜索到目标字符串。
     * 使用空字符（Python: '' , Java/C++: '\0' ）做标记是为了防止标记字符与矩阵原有字符重复。当存在重复时，此算法会将矩阵原有字符认作标记字符，从而出现错误。
     *
     * @param board
     * @param word
     */
    public boolean exist(char[][] board, String word) {

        char[] wordArr = word.toCharArray();

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (existDFS(board, wordArr, i, j, 0)) {
                    return true;
                }
            }
        }
        return false;


    }

    public static boolean existDFS(char[][] board, char[] word, int i, int j, int k) {

        // 下标越界或者字符不相等
        if (i >= board.length || i < 0 || j > board[0].length || j < 0 || board[i][j] != word[k]) {
            return false;
        }
        if (k == word.length - 1) {
            return true;
        }
        // 表示已经遍历过
        board[i][j] = '\0';

        boolean res = existDFS(board, word, i + 1, j, k + 1) ||
                existDFS(board, word, i - 1, j, k + 1) ||
                existDFS(board, word, i, j + 1, k + 1) ||
                existDFS(board, word, i, j - 1, k + 1);
        board[i][j] = word[k];
        return res;

    }

    static int count = 0;
    static int xBound = -1;
    static int yBound = -1;

    public static int movingCount(int m, int n, int k) {

        for (int i = 0; i < m; i++) {
            for (int j = 0; i < n; j++) {
                if ((xBound != -1 && yBound !=-1 ) && ((i == xBound && j > yBound) || (i > xBound && j >= yBound))){
                    break;
                }
                movingCountDfs(m, n, i, j, k);
            }
        }
        return count;
    }


    public static void movingCountDfs(int m, int n, int i, int j, int k) {
        int sum = 0;
        while (i > 10) {
            int mod = i % 10;
            sum += mod;
            i = i - mod * 10;
        }
        sum += i;
        while (j > 10) {
            int mod = j % 10;
            sum += mod;
            j = j - mod * 10;
        }
        sum += j;

        if (i >= m || i < 0 || j >= n || j < 0 || sum > k) {
            xBound = i;
            yBound = j;
            return;
        }
        count++;
        return;
    }



    public static void main(String[] args) {

        int res = movingCount(11, 9, 11);

        System.out.println(res);


    }


}
