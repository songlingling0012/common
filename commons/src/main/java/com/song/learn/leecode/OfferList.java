package com.song.learn.leecode;

import com.song.learn.leecode.bean.ListNode;
import com.song.learn.leecode.bean.TreeNode;

import java.util.*;


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


    /**
     * 机器人运动轨迹
     *
     * @param m
     * @param n
     * @param k
     * @return
     */
    public static int movingCount(int m, int n, int k) {

        if (k == 0) {
            return 1;
        }

        boolean[][] visited = new boolean[m][n];
        int ans = 1;
        visited[0][0] = true;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if ((i == 0 && j == 0) || get(i) + get(j) > k) {
                    continue;
                }
                if (i - 1 >= 0) {
                    visited[i][j] |= visited[i - 1][j];
                }
                if (j - 1 >= 0) {
                    visited[i][j] |= visited[i][j - 1];
                }
                ans += visited[i][j] ? 1 : 0;
            }
        }
        return ans;
    }

    private static int get(int x) {
        int res = 0;
        while (x != 0) {
            res += x % 10;
            x = x / 10;
        }
        return res;
    }


    /**
     * 剑指 Offer 15. 二进制中1的个数
     *
     * @param n
     * @return
     */
    public int hammingWeight(int n) {

        int count = 0;
        while (n != 0) {
            // 将n的二进制位中的最低位1变成0
            n &= n - 1;
            count++;
        }
        // 运算次数就是1的个数

        return count;
    }


    /**
     * 剑指 Offer 16. 数值的整数次方
     * 实现 pow(x, n) ，即计算 x 的 n 次幂函数（即，xn）。不得使用库函数，同时不需要考虑大数问题。
     *
     * @param x
     * @param n
     * @return
     */
    public static double myPow(double x, int n) {

        if (x == 0) {
            return 0;
        }
        long b = n;
        double res = 1.0;
        if (b < 0) {
            x = 1 / x;
            b = -b;
        }

        while (b > 0) {
            if ((b & 1) == 1) {
                res *= x;
            }
            x *= x;
            b >>= 1;
        }
        return res;
    }


    /**
     * 剑指 Offer 17. 打印从1到最大的n位数 ：输入数字 n，按顺序打印出从 1 到最大的 n 位十进制数。比如输入 3，则打印出 1、2、3 一直到最大的 3 位数 999
     * 不考虑大数
     *
     * @param n
     * @return
     */
    public int[] printNumbers(int n) {

        int len = (int) Math.pow(10, n) - 1;

        int[] res = new int[len];

        int index = 0;
        while (index < len) {
            res[index] = ++index;
        }

        return res;


    }

    /**
     * 剑指 Offer 17. 打印从1到最大的n位数 ：输入数字 n，按顺序打印出从 1 到最大的 n 位十进制数。比如输入 3，则打印出 1、2、3 一直到最大的 3 位数 999
     * 考虑大数
     *
     * @param n
     * @return
     */
    static StringBuilder numRes;
    static int nine = 0;
    static int count = 0;
    static int start;
    static int n;
    static char[] num;
    static char[] loop = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',};

    public static String printNumbersBiggest(int num) {
        n = num;
        numRes = new StringBuilder();
        start = n - 1;
        dfsPrintNums(0);

        numRes.deleteCharAt(res.length - 1);
        return res.toString();

    }

    public static void dfsPrintNums(int x) {
        if (x == n) {
            // 遍历结束
            String s = String.valueOf(num).substring(start);
            if (!s.equals('0')) {
                numRes.append(s + ",");
                return;
            }
        }
        for (char i : loop) {
            if (i == '9') {
                nine++;
            }
            num[x] = i;
            dfsPrintNums(x + 1);
        }
        nine--;
    }


    /**
     * 121. 买卖股票的最佳时机
     * 给定一个数组 prices ，它的第 i 个元素 prices[i] 表示一支给定股票第 i 天的价格。
     * 你只能选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子 卖出该股票。设计一个算法来计算你所能获取的最大利润。
     * 返回你可以从这笔交易中获取的最大利润。如果你不能获取任何利润，返回 0 。
     *
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {

        if (prices.length < 2) {
            return 0;
        }

        int max = prices[0];
        int min = prices[0];
        int preMaxSale = 0;

        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > max) {
                max = prices[i];
            }
            if (prices[i] < min) {
                // 在此时卖出股票
                preMaxSale = (max - min > preMaxSale) ? max - min : preMaxSale;
                min = prices[i];
                max = prices[i];
            }
        }
        return (max - min > preMaxSale) ? max - min : preMaxSale;
    }


    /**
     * 剑指 Offer 18. 删除链表的节点：
     * 给定单向链表的头指针和一个要删除的节点的值，定义一个函数删除该节点。
     * 返回删除后的链表的头节点。
     *
     * @param head
     * @param val
     * @return
     */
    public ListNode deleteNode(ListNode head, int val) {

        if (head == null) {
            return head;
        }
        if (head.val == val) {
            return head.next;
        }

        ListNode res = head;

        while (head.next != null) {

            if (head.next.val == val) {
                //删除head.next
                head.next = head.next.next;
            }
        }

        return res;

    }

    /**
     * 剑指 Offer 19. 正则表达式匹配:
     * 请实现一个函数用来匹配包含'. '和'*'的正则表达式。
     * 模式中的字符'.'表示任意一个字符，而'*'表示它前面的字符可以出现任意次（含0次）。
     * 在本题中，匹配是指字符串的所有字符匹配整个模式。例如，字符串"aaa"与模式"a.a"和"ab*ac*a"匹配，但与"aa.a"和"ab*a"均不匹配。
     *
     * @param s
     * @param p
     * @return
     */
    public static boolean isMatch(String s, String p) {

        char[] sourceArr = s.toCharArray();
        char[] patternArr = p.toCharArray();

        if (s == "" || p == "") {
            return false;
        }
        return matchCore(sourceArr, patternArr, 0, 0);
    }

    public static boolean matchCore(char[] sourceArr, char[] patternArr, int index1, int index2) {

        if (index1 < sourceArr.length && index2 < patternArr.length) {
            char curSourceChar = sourceArr[index1];
            char curPattenChar = patternArr[index2];

            if (index2 + 1 < patternArr.length && patternArr[index2 + 1] == '*') {

                //下一个字符是 *
                if (curPattenChar == curSourceChar) {
                    return matchCore(sourceArr, patternArr, index1 + 1, index2) || matchCore(sourceArr, patternArr, index1, index2 + 2) || matchCore(sourceArr, patternArr, index1 + 1, index2 + 2);
                } else {
                    // 不相等则跳过该字符串
                    return matchCore(sourceArr, patternArr, index1, index2 + 2);
                }
            }

            // 如果模式串字符是 .，匹配上直接匹配下一个字符，或者模式串和源串相等，则也是匹配上
            if (curPattenChar == '.' || curPattenChar == curSourceChar) {
                return matchCore(sourceArr, patternArr, index1 + 1, index2 + 1);
            }

        }

        return false;

    }


    /**
     * 剑指 Offer 21. 调整数组顺序使奇数位于偶数前面
     * 输入一个整数数组，实现一个函数来调整该数组中数字的顺序，使得所有奇数位于数组的前半部分，所有偶数位于数组的后半部分。
     *
     * @param nums
     * @return
     */
    public static int[] exchange(int[] nums) {

        int index1 = 0;
        int index2 = nums.length - 1;

        while (index1 < index2) {

            if (nums[index1] % 2 == 0) {
                //后面是偶数，往前移动
                while (index2 > 0 && nums[index2] % 2 == 0 && index1 < index2) {
                    index2--;
                }
                if (nums[index2] % 2 == 1) {
                    swage(nums, index1, index2);
                }
            }
            index1++;
        }

        return nums;
    }


    public static int[] swage(int[] nums, int index1, int index2) {
        int tmp = nums[index1];
        nums[index1] = nums[index2];
        nums[index2] = tmp;
        return nums;
    }


    /**
     * 剑指 Offer 26. 树的子结构:
     * 输入两棵二叉树A和B，判断B是不是A的子结构。(约定空树不是任意一个树的子结构)
     * B是A的子结构， 即 A中有出现和B相同的结构和节点值。
     *
     * @param A
     * @param B
     * @return
     */
    public boolean isSubStructure(TreeNode A, TreeNode B) {

        return (A != null && B != null) && (subStructureCore(A, B) || isSubStructure(A.left, B) || isSubStructure(A.right, B));

    }


    public static boolean subStructureCore(TreeNode nodeA, TreeNode nodeB) {
        if (nodeB == null) {
            return true;
        }
        if (nodeA == null || nodeA.val != nodeB.val) {
            return false;
        }

        return subStructureCore(nodeA.left, nodeB.left) && subStructureCore(nodeA.right, nodeB.right);
    }


    /**
     * 请完成一个函数，输入一个二叉树，该函数输出它的镜像。
     *
     * @param root
     * @return
     */
    public TreeNode mirrorTree(TreeNode root) {

        return mirrorTreeCore(root);
    }


    public static TreeNode mirrorTreeCore(TreeNode node) {

        if (node == null) {
            return node;
        }
        TreeNode right = node.right;
        TreeNode left = node.left;
        node.left = right;
        node.right = left;
        mirrorTreeCore(node.left);
        mirrorTreeCore(node.right);
        return node;

    }

    public TreeNode mirrorTreeStack(TreeNode root) {

        if (root == null) {
            return null;
        }

        Stack<TreeNode> stack = new Stack<>();
        stack.add(root);

        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if (node.left != null) {
                stack.add(node.left);
            }
            if (node.right != null) {
                stack.add(node.right);
            }
            TreeNode tmp = node.left;
            node.left = node.right;
            node.right = tmp;
        }
        return root;

    }


    /**
     * 剑指 Offer 28. 对称的二叉树
     * 请实现一个函数，用来判断一棵二叉树是不是对称的。如果一棵二叉树和它的镜像一样，那么它是对称的。
     *
     * @param root
     * @return
     */
    public boolean isSymmetric(TreeNode root) {

        if (root == null) {
            return true;
        }
        return isSymmetricCore(root.left, root.right);
    }


    public boolean isSymmetricCore(TreeNode node1, TreeNode node2) {

        if (node1 == node2 && node1.left == null && node1.right == null && node2.left == null && node2.right == null) {
            System.out.println(node1.val + "====" + node2.val + "====true");
            return true;
        } else if (node1.val != node2.val) {
            System.out.println(node1.val + "====" + node2.val + "====false");
            return false;
        } else {
            return isSymmetricCore(node1.left, node2.right) && isSymmetricCore(node1.right, node2.left);
        }
    }


    public static int[] spiralOrder(int[][] matrix) {


        int x = matrix.length;
        int y = matrix[0].length;
        int[] res = new int[x * y];
        int resIndex = 0;

        int left = 0;
        int right = y;
        int up = 0;
        int down = x;

        int xi = 0;
        int yi = 0;
        int circleCount = 0;
        int numCount = 0;
        while (numCount < (x * y)) {

            //未超过边界
            int index = circleCount % 4;
            if (index == 0) {
                // 从左往右
                while (yi < right) {
                    res[resIndex++] = matrix[xi][yi];
                    yi++;
                    numCount++;
                }
                xi++;
                yi--;
                up++;
            } else if (index == 1) {
                // 从上往下
                while (xi < down) {
                    res[resIndex++] = matrix[xi][yi];
                    xi++;
                    numCount++;
                }
                yi--;
                xi--;
                right--;
            } else if (index == 2) {
                // 从右往左
                while (yi >= left) {
                    res[resIndex++] = matrix[xi][yi];
                    yi--;
                    numCount++;
                }
                xi--;
                yi++;

                down--;
            } else if (index == 3) {
                // 从下往上
                while (xi >= up) {
                    res[resIndex++] = matrix[xi][yi];
                    xi--;
                    numCount++;
                }
                xi++;
                yi++;
                left++;
            }
            circleCount++;
        }

        return res;
    }


    /**
     * 剑指 Offer 31. 栈的压入、弹出序列
     * 输入两个整数序列，第一个序列表示栈的压入顺序，请判断第二个序列是否为该栈的弹出顺序。假设压入栈的所有数字均不相等。
     * 例如，序列 {1,2,3,4,5} 是某栈的压栈序列，序列 {4,5,3,2,1} 是该压栈序列对应的一个弹出序列，但 {4,3,5,1,2} 就不可能是该压栈序列的弹出序列。
     *
     * @param pushed
     * @param popped
     * @return
     */
    public static boolean validateStackSequences(int[] pushed, int[] popped) {

        int x = pushed.length;
        int y = popped.length;

        if (x != y) {
            return false;
        }

        Stack<Integer> stack = new Stack<>();

        int popIndex = 0;

        for (int i = 0; i < x; i++) {

            if (popped[popIndex] == pushed[i]) {
                popIndex++;
                while (!stack.isEmpty() && stack.peek() == popped[popIndex]) {
                    stack.pop();
                    popIndex++;
                }
            } else if (!stack.isEmpty() && stack.peek() == popped[popIndex]) {
                while (!stack.isEmpty() && stack.peek() == popped[popIndex]) {
                    stack.pop();
                    popIndex++;
                }
            } else {
                stack.push(pushed[i]);
            }

        }

        int count = stack.size();
        for (int i = 0; i < count; i++) {
            if (stack.peek() == popped[popIndex]) {
                stack.pop();
                popIndex++;
            }
        }

        return stack.isEmpty();

    }


    /**
     * 剑指 Offer 32 - I. 从上到下打印二叉树
     * 从上到下打印出二叉树的每个节点，同一层的节点按照从左到右的顺序打印
     *
     * @param root
     * @return
     */

    public int[] levelOrder(TreeNode root) {

        if (root == null) {
            return new int[0];
        }

        ArrayDeque<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        ArrayList<Integer> list = new ArrayList<>();
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();

            list.add(node.val);

            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }

        }

        int[] res = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }

        return res;

    }


    /**
     * 剑指 Offer 32 - II. 从上到下打印二叉树 II
     * 从上到下按层打印二叉树，同一层的节点按从左到右的顺序打印，每一层打印到一行。
     *
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder2(TreeNode root) {

        ArrayList<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }

        ArrayDeque<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);

        ArrayList<Integer> list = new ArrayList<>();
        int curLevelCount = 1;
        int nextLevelCount = 0;

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            list.add(node.val);
            curLevelCount--;

            if (node.left != null) {
                queue.offer(node.left);
                nextLevelCount++;
            }
            if (node.right != null) {
                queue.offer(node.right);
                nextLevelCount++;
            }

            if (curLevelCount == 0) {
                //一层遍历结束
                res.add(list);
                list = new ArrayList<>();
                curLevelCount = nextLevelCount;
                nextLevelCount = 0;
            }

        }

        return res;

    }


    /**
     * 剑指 Offer 32 - III. 从上到下打印二叉树 III
     * 请实现一个函数按照之字形顺序打印二叉树，即第一行按照从左到右的顺序打印，第二层按照从右到左的顺序打印，
     * 第三行再按照从左到右的顺序打印，其他行以此类推。
     *
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder3(TreeNode root) {

        ArrayList<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }

        ArrayDeque<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);

        ArrayList<Integer> list = new ArrayList<>();
        int curLevelCount = 1;
        int nextLevelCount = 0;

        int types = 1;

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            list.add(node.val);
            curLevelCount--;

            if (node.left != null) {
                queue.offer(node.left);
                nextLevelCount++;
            }
            if (node.right != null) {
                queue.offer(node.right);
                nextLevelCount++;
            }

            if (curLevelCount == 0) {
                //一层遍历结束
                if (types == 2) {
                    // 反序
                    Collections.reverse(list);
                    types = 1;
                } else {
                    types = 2;
                }
                res.add(list);
                list = new ArrayList<>();
                curLevelCount = nextLevelCount;
                nextLevelCount = 0;
            }

        }

        return res;

    }


    /**
     * 剑指 Offer 33. 二叉搜索树的后序遍历序列
     * 输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历结果。如果是则返回 true，否则返回 false。
     * 假设输入的数组的任意两个数字都互不相同。
     *
     * @param postorder
     * @return
     */
    public boolean verifyPostorder(int[] postorder) {

        int len = postorder.length;

        if (len < 1) {
            return true;
        }

        int root = postorder[len - 1];

        for (int i = len - 2; i >= 0; i--) {

            int cur = postorder[i];

            if (cur > root) {
                // 右子树节点

            } else {
                // 没有右子树


            }


        }


        return false;
    }


    /**
     * 剑指 Offer 22. 链表中倒数第k个节点
     * 输入一个链表，输出该链表中倒数第k个节点。为了符合大多数人的习惯，本题从1开始计数，即链表的尾节点是倒数第1个节点。
     * <p>
     * 例如，一个链表有 6 个节点，从头节点开始，它们的值依次是 1、2、3、4、5、6。这个链表的倒数第 3 个节点是值为 4 的节点。
     *
     * @param head
     * @param k
     * @return
     */
    public ListNode getKthFromEnd(ListNode head, int k) {

        int nodeCount = 0;
        ListNode resNode = null;
        ListNode tmp = head;
        while (head != null) {
            nodeCount++;
            if (resNode == null) {
                resNode = tmp;
            }
            if (nodeCount > k) {
                resNode = tmp;
            } else {
                tmp = tmp.next;
            }
            head = head.next;
        }
        return resNode;
    }


    public static int binarySearchFirstEqual(int[] data, int k) {

        int left = 0;
        int right = data.length - 1;

        while (left <= right) {

            int mid = left + (right - left) / 2;

            if (data[mid] > k) {
                right = mid - 1;
            } else if (data[mid] < k) {
                left = mid + 1;
            } else {
                if(mid==0 || data[mid-1]<k){
                    return mid;
                }
            }
        }
        return -1;
    }

    public static int binarySearchLastEqual(int[] data, int k) {

        int left = 0;
        int right = data.length - 1;

        while (left <= right) {

            int mid = left + (right - left) / 2;

            if (data[mid] > k) {
                right = mid - 1;
            } else if (data[mid] < k) {
                left = mid + 1;
            } else {
                if(mid==data.length-1 || data[mid+1]!=k){
                    return mid;
                }
            }
        }
        return -1;
    }

    public static int binarySearchFirstMax(int[] data, int k) {

        int left = 0;
        int right = data.length - 1;

        while (left <= right) {

            int mid = left + (right - left) / 2;

            if (data[mid] > k) {
                right = mid - 1;
            } else if (data[mid] < k) {
                left = mid + 1;
            } else if(k==0 || mid==data.length-1 || data[mid-1]<k ){
                return mid;
            }

        }

        return -1;

    }

    public static int binarySearchLastMin(int[] data, int k) {

        int left = 0;
        int right = data.length - 1;

        while (left <= right) {

            int mid = left + (right - left) / 2;

            if(data[mid] <= k){
                if(mid==data.length-1 || data[mid+1]>k){
                    return mid;
                }else{
                    left = mid+1;
                }
            }else{
                right = mid-1;
            }
        }

        return -1;

    }


    /**
     * 剑指 Offer 34. 二叉树中和为某一值的路径
     * 输入一棵二叉树和一个整数，打印出二叉树中节点值的和为输入整数的所有路径。从树的根节点开始往下一直到叶节点所经过的节点形成一条路径。
     * @param root
     * @param target
     * @return
     */
    static ArrayList<List<Integer>> pathRes = new ArrayList<>();
    static Deque<Integer> pathQueue = new ArrayDeque<Integer>();

    public List<List<Integer>> pathSum(TreeNode root, int target) {

        pathSumDFS(root,target);

        return pathRes;
    }


    /**
     * 深度优先遍历
     * @param node
     * @param target
     */
    public static void pathSumDFS(TreeNode node,int target){

        if(node == null){
            return ;
        }
        pathQueue.add(node.val);
        target-=node.val;
        if(target==0 && node.left==null && node.right==null){
            pathRes.add(new ArrayList<>(pathQueue));
        }
        pathSumDFS(node.left,target);
        pathSumDFS(node.right,target);

        pathQueue.removeLast();
    }


    /**
     * 剑指 Offer 37. 序列化二叉树
     * 请实现两个函数，分别用来序列化和反序列化二叉树。
     *
     * 你需要设计一个算法来实现二叉树的序列化与反序列化。
     * 这里不限定你的序列 / 反序列化算法执行逻辑，你只需要保证一个二叉树可以被序列化为一个字符串并且将这个字符串反序列化为原始的树结构。
     * @param
     */
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {

        return serializePre(root)+","+serializePost(root);

    }


    public static String serializePre(TreeNode root){

        StringBuilder builder = new StringBuilder();

        if(root==null){
            return builder.toString();
        }

        builder.append(root.val);
        String left = serializePre(root.left);
        String right = serializePre(root.right);

        return builder.toString()+left+right;
    }


    public static String serializePost(TreeNode root){

        StringBuilder builder = new StringBuilder();

        if(root==null){
            return builder.toString();
        }

        String left = serializePre(root.left);
        builder.append(root.val);
        String right = serializePre(root.right);

        return left + builder.toString()+ right;
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {

        String[] arr = data.split(",");
        char[] pres = arr[0].toCharArray();
        char[] post = arr[1].toCharArray();

        return deserializeCore(pres,post,0,0);
    }

    public static TreeNode deserializeCore(char[] preArr,char[] postArr,int preIndex,int postIndex){

        // 先序遍历的第一个节点就是根节点
        Integer rootVal = Integer.valueOf(String.valueOf(preArr[preIndex]));
        TreeNode rootNode = new TreeNode(rootVal);
        while(!rootVal.equals(Integer.valueOf(String.valueOf(postArr[postIndex])))){
            postIndex++;
        }
        rootNode.left = deserializeCore(preArr,postArr,preIndex++,postIndex-1);
        rootNode.right = deserializeCore(preArr,postArr,preIndex++,postIndex+1);
        return rootNode;


    }


    public static void main(String[] args) {


        System.out.println(Integer.valueOf("01233"));
        System.out.println(Integer.valueOf("1233"));

        System.exit(0);

        System.out.println(binarySearchLastEqual(new int[]{1, 3, 4, 6, 7,7,7, 8, 9, 12}, 7));


        System.exit(0);

        validateStackSequences(new int[]{1, 2, 3, 0}, new int[]{2, 1, 3, 0});

        System.exit(0);


        System.out.println(spiralOrder(new int[][]{{1, 2, 3}, {5, 6, 7}, {9, 10, 11}}));


        System.exit(0);

        System.out.println(exchange(new int[]{8, 10, 3, 20, 12, 4, 10, 8, 4, 0, 5, 17, 7, 20, 3}));

        System.exit(0);

        int res = movingCount(11, 9, 11);

        System.out.println(res);


    }


}
