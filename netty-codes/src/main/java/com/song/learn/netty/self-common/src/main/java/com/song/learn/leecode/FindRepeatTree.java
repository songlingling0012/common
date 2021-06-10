package com.song.learn.leecode;

import com.song.learn.leecode.bean.TreeNode;

import java.util.*;

/**
 * @Author: Song_
 * @Description:
 *      给定一棵二叉树，返回所有重复的子树。对于同一类的重复子树，你只需要返回其中任意一棵的根结点即可。
 *      两棵树重复是指它们具有相同的结构以及相同的结点值。
 *              1
 *        / \
 *       2   3
 *      /   / \
 *     4   2   4
 *        /
 *       4
 * 下面是两个重复的子树：
 *
 *       2
 *      /
 *     4
 * 和
 *
 *     4
 * @Date: 2021/6/9 11:08
 */
public class FindRepeatTree {

    /**
     * 记录子树重复次数
     */
    Map<String,Integer> repeatMap =  new HashMap<String,Integer>();

    /**
     * 记录重复的子树
     */
    List<TreeNode> resList = new ArrayList<TreeNode>();

    /**
     * 主函数
     * @param root
     * @return
     */
    List<TreeNode> findDuplicateSubtrees(TreeNode root) {

        traverse(root);
        return resList;
    }

    /**
     * 辅助函数
     * @param root
     * @return
     */
    String traverse(TreeNode root) {

        // 知道以此为根个节点长什么样，后序遍历【知道左右节点+根节点，即可确定树的形状】
        if(root == null){
            // 使用“#”代表空节点
            return "#";
        }

        String left = traverse(root.left);
        String right = traverse(root.right);

        String subTree = left+","+right+","+root.val;

        int repeat = repeatMap.getOrDefault(subTree, 0);
        // 如果多次重复，也只加入结果列表一次
        if(repeat==1){
            resList.add(root);
        }
        repeatMap.put(subTree,repeat+1);
        return subTree;


    }


}
