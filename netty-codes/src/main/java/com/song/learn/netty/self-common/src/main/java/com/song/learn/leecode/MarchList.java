package com.song.learn.leecode;

import java.util.*;

/**
 * @Author: Song_
 * @Description:
 * @Date: 2021/3/9 9:51
 */
public class MarchList {

    public String removeDuplicates(String S) {

        StringBuffer stack = new StringBuffer();

        int top = -1;

        for (int i = 0; i < S.length(); ++i) {

            char c = S.charAt(i);
            if (top >= 0 && stack.charAt(top) == c) {
                stack.deleteCharAt(top);
                --top;
            } else {
                stack.append(c);
                ++top;
            }
        }
        return stack.toString();
    }


    /**
     * 分割回文字符串
     *
     * @param s
     * @return
     */
    public int minCut(String s) {
        int n = s.length();
        boolean[][] g = new boolean[n][n];

        for (int i = 0; i < n; ++i) {
            Arrays.fill(g[i], true);
        }

        for (int i = n - 1; i >= 0; --i) {
            for (int j = i + 1; j < n; ++j) {
                g[i][j] = (s.charAt(i) == s.charAt(j)) && g[i + 1][j - 1];
            }
        }

        int[] f = new int[n];
        Arrays.fill(f, Integer.MAX_VALUE);

        for (int i = 0; i < n; ++i) {
            if (g[0][i]) {
                f[i] = 0;
            } else {
                for (int j = 0; j < i; ++j) {
                    if (g[j + 1][i]) {
                        f[i] = Math.min(f[i], f[j] + 1);
                    }
                }
            }
        }

        return f[n - 1];
    }


    boolean[][] f;
    List<List<String>> res;
    List<String> ans;
    int n;

    /**
     * 分割回文串
     *
     * @param s
     * @return
     */
    public List<List<String>> partition(String s) {

        n = s.length();
        f = new boolean[n][n];
        for (int i = 0; i < n; ++i) {
            Arrays.fill(f[i], true);
        }
        for (int i = n - 1; i >= 0; --i) {
            for (int j = i + 1; j < n; ++j) {
                f[i][j] = (s.charAt(i) == s.charAt(j)) && f[i + 1][j - 1];
            }
        }
        dfs(s, 0);
        return res;
    }

    public void dfs(String s, int i) {
        if (i == n) {
            res.add(new ArrayList<String>(ans));
            return;
        }
        for (int j = i; j < n; ++j) {
            if (f[i][j]) {
                ans.add(s.substring(i, j + 1));
                dfs(s, j + 1);
                ans.remove(ans.size() - 1);
            }
        }
    }

    /**
     * 零钱兑换
     *
     * @param amount
     * @param coins
     * @return
     */
    public int change(int amount, int[] coins) {

        int n = coins.length;

        // 只使用前i个物品，当背包容量为j时，有dp[i][j]种方法可以装满背包
        int[][] dp = new int[n + 1][amount + 1];

        // base case
        for (int i = 0; i <= n; i++) {
            dp[i][0] = 1;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= amount; j++) {
                if (j - coins[i - 1] >= 0) {
                    // 将这枚硬币放进背包，即使用这个面值的硬币，则dp[i][j]应该等于dp[i][j-coins[i-1])
                    dp[i][j] = dp[i - 1][j] + dp[i][j - coins[i - 1]];
                } else {
                    // 不把这个硬币放在背包里，就是之前凑硬币的方法
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        return dp[n][amount];
    }


    public static int calculate(String s) {

        LinkedList<Integer> ops = new LinkedList<>();

        ops.push(1);

        int sign = 1;

        int ret = 0;
        int n = s.length();
        int i = 0;
        while (i < n) {
            if (s.charAt(i) == ' ') {
                i++;
            } else if (s.charAt(i) == '+') {
                sign = ops.peek();
                i++;
            } else if (s.charAt(i) == '-') {
                sign = -ops.peek();
                i++;
            } else if (s.charAt(i) == '(') {
                ops.push(sign);
                i++;
            } else if (s.charAt(i) == ')') {
                ops.pop();
                i++;
            } else {
                long num = 0;
                while (i < n && Character.isDigit(s.charAt(i))) {
                    num = num * 10 + s.charAt(i) - '0';
                    i++;
                }
                ret += sign * num;
            }
        }
        return ret;
    }


    /**
     * 包括加减乘除
     *
     * @param s
     * @return
     */
    public static int calculate2(String s) {

        ArrayDeque<Integer> stack = new ArrayDeque<>();
        char preSign = '+';
        int num = 0;
        int n = s.length();
        for (int i = 0; i < n; i++) {
            if (Character.isDigit(s.charAt(i))) {
                num = num * 10 + s.charAt(i) - '0';
            }

            if (!Character.isDigit(s.charAt(i)) && s.charAt(i) != ' ' || i == n - 1) {
                switch (preSign) {
                    case '+':
                        stack.push(num);
                        break;
                    case '-':
                        stack.push(-num);
                        break;
                    case '*':
                        stack.push(stack.pop() * num);
                        break;
                    default:
                        stack.push(stack.pop() / num);
                }
                preSign = s.charAt(i);
                num = 0;
            }
        }
        int ans = 0;
        while (!stack.isEmpty()) {
            ans += stack.pop();
        }
        return ans;


    }

    public int calculateByMyThink(String s) {

        Deque<Integer> nums = new ArrayDeque<>();
        ArrayDeque<Character> signs = new ArrayDeque<>();

        char[] chars = s.toCharArray();

        for (int i = 0; i < chars.length; i++) {

            char curChar = chars[i];

            if (s.charAt(i) != ' ') {

                if ('+' == s.charAt(i) || '-' == s.charAt(i) || '(' == s.charAt(i)) {
                    signs.push(curChar);
                } else if (')' == s.charAt(i)) {
                    int curNum = 0;
                    // pop直到 （
                    while (signs.peek() != '(') {
                        Character sign = signs.pop();
                        Integer num2 = nums.pop();
                        Integer num1 = 0;
                        if (!nums.isEmpty()) {
                            num1 = nums.pop();
                        }
                        if (sign == '+') {
                            curNum = num1 + num2;
                        } else if (sign == '-') {
                            curNum = num1 - num2;
                        }
                        nums.push(curNum);
                    }
                    if (signs.peek() == '(') {
                        signs.pop();
                    }
                    if (!signs.isEmpty()) {
                        Character sign = signs.pop();
                        Integer num2 = nums.pop();
                        Integer num1 = 0;
                        if (!nums.isEmpty()) {
                            num1 = nums.pop();
                        }
                        if (sign == '+') {
                            curNum = num1 + num2;
                        } else if (sign == '-') {
                            curNum = num1 - num2;
                        }
                        nums.push(curNum);
                    }

                } else {
                    int num = 0;
                    while (i < chars.length && Character.isDigit(curChar)) {
                        num = num * 10 + s.charAt(i) - '0';
                        i++;
                        continue;
                    }
                    nums.push(num);
                    if (!signs.isEmpty() && signs.peek() != '(') {
                        // 计算
                        Integer num2 = nums.pop();
                        Integer num1 = 0;
                        if (!nums.isEmpty()) {
                            num1 = nums.pop();
                        }
                        Character sign = signs.pop();
                        int curCalc = 0;
                        if (sign == '+') {
                            curCalc = num1 + num2;
                        } else if (sign == '-') {
                            curCalc = num1 - num2;
                        }
                        nums.push(curCalc);
                    }
                }
            }
        }

        if (!signs.isEmpty()) {
            Character sign = signs.pop();
            Integer num2 = nums.pop();
            Integer num1 = nums.pop();
            if (sign == '+') {
                return num1 + num2;
            } else if (sign == '-') {
                return num1 - num2;
            }

        }

        int res = 0;
        int cell = 1;
        while (signs.isEmpty() && !nums.isEmpty()) {

            res += cell * nums.pop();
            cell = cell * 10;
        }

        return res;

    }

    public static String convert(String s, int numRows) {

        int n = s.length();

        if (numRows == 1) {
            return s;
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < numRows; i++) {

            int index = i;
            int interval = (numRows - 1 - i) * 2;
            boolean flag = true;

            while (index < n) {
                sb.append(s.charAt(index));
                if (i == 0 || i == numRows - 1) {
                    index = index + (numRows - 1) * 2;
                } else {
                    if (flag) {
                        index = index + interval;
                        flag = false;
                    } else {
                        index = index + ((numRows - 1) * 2 - interval);
                        flag = true;
                    }
                }
            }
        }

        return sb.toString();


    }


    /**
     * 9,#,92,#,#
     *
     * @param preorder
     * @return
     */
    public boolean isValidSerialization(String preorder) {

        int len = preorder.length();

        String[] trees = preorder.split(",");

        ArrayDeque<Integer> stack = new ArrayDeque<>();

        int i = 0;

        stack.push(1);

        while (i < len) {

            if (stack.isEmpty()) {
                return false;
            }

            if (preorder.charAt(i) == ',') {
                i++;
            } else if (preorder.charAt(i) == '#') {
                int top = stack.pop() - 1;
                if (top > 0) {
                    stack.push(top);
                }
                i++;
            } else {
                //是一个数字
                while (i < n && preorder.charAt(i) != ',') {
                    i++;
                }
                int top = stack.pop() - 1;
                if (top > 0) {
                    stack.push(top);
                }
                stack.push(2);
            }

        }
        return stack.isEmpty();
    }


    public static List<Integer> spiralOrder(int[][] matrix) {

        int x = matrix.length;
        int y = matrix[0].length;
        ArrayList<Integer> list = new ArrayList<>();

        // 记录扫描边界：上下左右
        int left = 0;
        int right = y;
        int up = 0;
        int down = x;

        // 当前坐标
        int xi = 0;
        int yi = 0;

        /**
         * 螺旋只有四个方向，且是按顺序的：1.从左往右；2.从上往下；3.从右往左；4.从下向上
         * 一横或者一列扫描看作一次circleCount,通过circleCount就知道当前遍历数据的方向
         */
        int circleCount = 0;

        // 记录数据个数，当返回链表中的数字个数=x*y,则螺旋结束
        int numCount = 0;


        while (numCount < (x * y)) {

            //未超过边界
            int index = circleCount % 4;
            if (index == 0) {
                // 从左往右
                while (yi < right) {
                    list.add(matrix[xi][yi]);
                    // 纵坐标右移
                    yi++;
                    numCount++;
                }
                xi++;
                yi--;
                up++;
            } else if (index == 1) {
                // 从上往下
                while (xi < down) {
                    list.add(matrix[xi][yi]);
                    xi++;
                    numCount++;
                }
                yi--;
                xi--;
                right--;
            } else if (index == 2) {
                // 从右往左
                while (yi >= left) {
                    list.add(matrix[xi][yi]);
                    yi--;
                    numCount++;
                }
                xi--;
                yi++;
                down--;
            } else if (index == 3) {
                // 从下往上
                while (xi >= up) {
                    list.add(matrix[xi][yi]);
                    xi--;
                    numCount++;
                }
                xi++;
                yi++;
                left++;
            }
            circleCount++;
        }

        return list;
    }


    public static int[][] generateMatrix(int n) {

        int[][] matrix = new int[n][n];

        // 记录扫描边界：上下左右
        int left = 0;
        int right = n;
        int up = 0;
        int down = n;

        // 当前坐标
        int xi = 0;
        int yi = 0;

        /**
         * 螺旋只有四个方向，且是按顺序的：1.从左往右；2.从上往下；3.从右往左；4.从下向上
         * 一横或者一列扫描看作一次circleCount,通过circleCount就知道当前遍历数据的方向
         */
        int circleCount = 0;

        // 记录数据个数，当返回链表中的数字个数=x*y,则螺旋结束
        int numCount = 1;

        while (numCount <= (n * n)) {

            //未超过边界
            int index = circleCount % 4;

            if (index == 0) {
                // 从左往右
                while (yi < right) {
                    matrix[xi][yi] = numCount;
                    // 纵坐标右移
                    yi++;
                    numCount++;
                }
                xi++;
                yi--;
                up++;
            } else if (index == 1) {
                // 从上往下
                while (xi < down) {
                    matrix[xi][yi] = numCount;
                    xi++;
                    numCount++;
                }
                yi--;
                xi--;
                right--;
            } else if (index == 2) {
                // 从右往左
                while (yi >= left) {
                    matrix[xi][yi] = numCount;
                    yi--;
                    numCount++;
                }
                xi--;
                yi++;
                down--;
            } else if (index == 3) {
                // 从下往上
                while (xi >= up) {
                    matrix[xi][yi] = numCount;
                    xi--;
                    numCount++;
                }
                xi++;
                yi++;
                left++;
            }
            circleCount++;
        }

        return matrix;
    }


    public int numDistinct(String s, String t) {

        int m = s.length();
        int n = t.length();

        if (n > m) {
            return 0;
        }

        int[][] dp = new int[m + 1][n + 1];

        for (int i = 0; i <= m; i++) {
            dp[i][n] = 1;
        }

        for (int i = m - 1; i >= 0; i--) {
            char sc = s.charAt(i);
            for (int j = n - 1; j >= 0; j--) {
                char tc = t.charAt(j);
                if (sc == tc) {
                    dp[i][j] = dp[i + 1][j] + dp[i + 1][j + 1];
                } else {
                    dp[i][j] = dp[i + 1][j];
                }
            }
        }
        return dp[0][0];
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public static ListNode reverseBetween(ListNode head, int left, int right) {

        int nodeCount = 0;
        if (head == null) {
            return null;
        }
        nodeCount++;

        int[] data = new int[right - left + 1];
        int i = 0;
        ListNode preHead = new ListNode();
        ListNode pre = new ListNode();
        preHead = pre;

        while (i < data.length) {

            if (nodeCount >= left && nodeCount <= right) {
                data[i++] = head.val;
            } else {
                pre.next = new ListNode(head.val);
                pre = pre.next;
            }
            head = head.next;
            nodeCount++;

        }

        ListNode preNode = new ListNode();
        ListNode resNode = preNode;
        for (int j = data.length - 1; j >= 0; j--) {
            preNode.next = new ListNode(data[j]);
            preNode = preNode.next;
        }

        preNode.next = head;

        preNode = preNode.next;
        pre.next = resNode.next;

        return preHead.next;

    }


    /**
     * 切分两个子集
     *
     * @param nums
     * @return
     */
    public boolean canPartition(int[] nums) {

        int sum = 0;
        int n = nums.length;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }
        if (sum % 2 != 0) {
            return false;
        }

        sum = sum / 2;
        // 前i个数 是否能满足取值为j
       /* boolean[][] dp = new boolean[nums.length][];

        for(int i=0;i<=n;i++){
            dp[i][0] =true;
        }

        for(int i=1;i<=n;i++){
            for( int j=1;j<=sum;j++){
                if(j - nums[i-1] < 0){
                    // 背包容量不足，不能装入第i个物品
                    dp[i][j] = dp[i-1][j];
                }else {
                    // 装入背包 或者 不装入背包
                    dp[i][j] = dp[i-1][j] || dp[i-1][j-nums[i-1]];
                }
            }
        }
        return dp[n][sum];
*/
        boolean[] singleDp = new boolean[sum + 1];
        singleDp[0] = true;
        for (int i = 0; i < n; i++) {
            for (int j = sum; j >= 0; j--) {
                if (j - nums[i] >= 0) {
                    singleDp[j] = singleDp[j] || singleDp[j - nums[i]];
                }
            }
        }
        return singleDp[sum];
    }


    public int hammingWeight(int n) {

        int count = 0;
        for (int i = 0; i < 32; i++) {
            if ((n & (1 << i)) != 0) {
                count++;
            }
        }
        return count;
    }


    public interface NestedInteger {

        // @return true if this NestedInteger holds a single integer, rather than a nested list.
        public boolean isInteger();

        // @return the single integer that this NestedInteger holds, if it holds a single integer
        // Return null if this NestedInteger holds a nested list
        public Integer getInteger();

        // @return the nested list that this NestedInteger holds, if it holds a nested list
        // Return null if this NestedInteger holds a single integer
        public List<NestedInteger> getList();

    }

    public class NestedIterator implements Iterator<Integer> {

         private List<Integer> list;
         private Iterator<Integer> cur;

        public NestedIterator(List<NestedInteger> nestedList) {

            list = new ArrayList<Integer>();
            dfs(nestedList);
            cur = list.iterator();
        }
        @Override
        public Integer next() {
            return cur.next();
        }

        @Override
        public boolean hasNext() {

            return cur.hasNext();
        }

        private void dfs(List<NestedInteger> nestedList){
            for(NestedInteger nest : nestedList){
                if(nest.isInteger()){
                    list.add(nest.getInteger());
                }else{
                    dfs(nest.getList());
                }
            }
        }



    }

    public static void main(String[] args) {

        ListNode head = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);

        head.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;


        reverseBetween(head, 4, 5);

        System.exit(0);

        int[][] data = {{1, 2, 3, 4, 5}, {6, 7, 8, 9, 10}, {11, 12, 13, 14, 15}, {16, 17, 18, 19, 20}, {21, 22, 23, 24, 25}};

        List<Integer> r = spiralOrder(data);
        System.out.println(r);

        System.exit(0);

        String res = convert("PAYPALISHIRING", 3);

        System.out.println(res);


    }

}
