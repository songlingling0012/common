package com.song.learn.leecode;

import com.song.learn.leecode.bean.Node;
import com.song.learn.leecode.bean.TreeNode;
import com.song.learn.leecode.bean.TreeNodeNext;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @Author: Song_
 * @Description:
 * @Date: 2021/7/5 16:27
 */
public class TreeList {

    /**
     * 1373. 二叉搜索子树的最大键值和
     * 给你一棵以 root 为根的 二叉树 ，请你返回 任意 二叉搜索子树的最大键值和。
     * 二叉搜索树的定义如下：
     * 任意节点的左子树中的键值都 小于 此节点的键值。
     * 任意节点的右子树中的键值都 大于 此节点的键值。
     * 任意节点的左子树和右子树都是二叉搜索树。
     *
     * @param root
     * @return
     */
    public int maxSumBST(TreeNode root) {

        /**
         * 1. 判断左右子树是否是BST
         * 2. 左子树的最大值和右子树的最大值
         * 3. 左右字数的节点值之和
         */
        int[] res = {0};
        maxSumBST(res, root);
        return res[0];
    }

    /**
     * 判断以Node为根节点的树是否是二叉搜索树
     *
     * @param node
     * @param min
     * @param max
     * @return
     */
    public static boolean isBST(TreeNode node, int min, int max) {
        if (node == null) {
            return true;
        }

        return min < node.val && max > node.val && isBST(node.left, min, node.val) && isBST(node.right, node.val, max);
    }


    /**
     * 求节点的最大值
     *
     * @param res
     * @param node
     */
    public static void maxSumBST(int[] res, TreeNode node) {

        // 该节点是BST，直接求和
        if (isBST(node, Integer.MIN_VALUE, Integer.MAX_VALUE)) {
            // 是二叉树，求节点和，节点和的最优解，肯定在子节点的求和过程中
            sumNodeValue(res, node);
            return;
        }

        // 不是BST，递归进入左右子树
        maxSumBST(res, node.left);
        maxSumBST(res, node.right);
    }

    /**
     * 以Node为根节点求和
     *
     * @param res
     * @param node
     * @return
     */
    public static int sumNodeValue(int[] res, TreeNode node) {

        if (node == null) {
            return 0;
        }

        // 二叉搜索树节点和的最大值，肯定在子节点中会出现
        int sum = node.val + sumNodeValue(res, node.left) + sumNodeValue(res, node.right);
        res[0] = Math.max(sum, res[0]);
        return sum;
    }


    /**
     * 判断是否为有效的二叉搜索树
     *
     * @param root
     * @return
     */
    public boolean isValidBST(TreeNode root) {
        return isValidBSTCore(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    public boolean isValidBSTCore(TreeNode root, long min, long max) {

        if (root == null) {
            return true;
        }

        if (root.val <= min || root.val >= max) {
            return false;
        }

        return isValidBSTCore(root.left, min, root.val) && isValidBSTCore(root.right, root.val, max);
    }


    /**
     * 翻转二叉树
     *
     * @param root
     * @return
     */
    public TreeNode invertTree(TreeNode root) {
        // 先序遍历,交换二叉树的左右子节点
        if (root == null) {
            return null;
        }

        TreeNode tmp = root.left;
        root.left = root.right;
        root.right = tmp;

        invertTree(root.left);
        invertTree(root.right);

        return root;

    }


    /**
     * 翻转二叉树
     *
     * @param root
     * @return
     */
    public TreeNode invertTree2(TreeNode root) {
        // 先序遍历,交换二叉树的左右子节点
        if (root == null) {
            return null;
        }

        ArrayDeque<TreeNode> queue = new ArrayDeque<>();
        queue.push(root);

        while (!queue.isEmpty()) {

            TreeNode node = queue.pop();

            TreeNode tmp = node.left;
            node.left = node.right;
            node.right = tmp;

            if (node.left != null) {
                queue.push(node.left);
            }
            if (node.right != null) {
                queue.push(node.right);
            }

        }

        return root;

    }


    /**
     * 填充完美二叉树每个节点的下一个右侧节点指针
     *
     * @param root
     * @return
     */
    public TreeNodeNext connect(TreeNodeNext root) {

        if (root == null) {
            return null;
        }
        connetCore(root.left, root.right);

        return root;
    }

    public void connetCore(TreeNodeNext node1, TreeNodeNext node2) {
        if (node1 == null || node2 == null) {
            return;
        }

        node1.next = node2;

        connetCore(node1.left, node1.right);
        connetCore(node2.left, node2.right);
        connetCore(node1.right, node2.left);
    }


    /**
     * 将二叉树展开为链表
     * 给你二叉树的根结点 root ，请你将它展开为一个单链表：
     *      展开后的单链表应该同样使用 TreeNode ，其中 right 子指针指向链表中下一个结点，而左子指针始终为 null 。
     *      展开后的单链表应该与二叉树 先序遍历 顺序相同。
     * @param root
     */
    public void flatten(TreeNode root){

        if(root == null){
            return;
        }

        flatten(root.left);
        flatten(root.right);

        // 后序遍历
        // 1. 左右子树已经被拉平成一条链表
        TreeNode leftSub = root.left;
        TreeNode rightSub = root.right;

        // 2. 将左子树作为右子树
        root.left = null;
        root.right = leftSub;

        // 3. 将原先的右子树接到当前的右子树末端
        TreeNode p = root;
        while(p.right != null){
            p = p.right;
        }
        p.right = rightSub;



    }


    static class Test{
        static int i;

    }

    class Test2{

    }

    public static void main(String[] args) {


        int i = Test.i;

        Test test = new Test();

    }




}
