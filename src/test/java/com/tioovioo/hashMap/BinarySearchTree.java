package com.tioovioo.hashMap;

import java.util.zip.Inflater;

/**
 * @author: yongjia.guo
 * Create Date : 2020/4/2.
 * <p>
 * <p>
 * 二叉树（基于二分查找）
 * 中序遍历（左 根 右）
 */
@SuppressWarnings("all")
public class BinarySearchTree {

    int data; //根节点；
    BinarySearchTree left; //左子节点
    BinarySearchTree right; //右子节点

    //构造方法：
    public BinarySearchTree(int data) {
        this.data = data;
        this.right = null;
        this.left = null;
    }

    /**
     * 插入方法：
     * 新的数字插入节点前，先比较大小。
     */

    public void insertNode(BinarySearchTree root, int data) {

        //判断根节点是否为空。
        if (root == null) {
            return;
        }

        //插入数据小于根节点值，插入左边
        if (root.data > data) {
            //如果左子节点为空，直接插入：
            if (root.left == null) {
                root.left = new BinarySearchTree(data);
            } else {
                //不为空，则递归插入
                insertNode(root.left, data);
            }
        } else {
            //如果右子节点为空，直接插入：
            if (root.right == null) {
                root.right = new BinarySearchTree(data);
            } else {
                //不为空，则递归插入
                insertNode(root.right, data);
            }
        }

    }

    /**
     * 中序遍历:(左 根 右)
     *
     * @param root
     */
    public void inForeach(BinarySearchTree root) {
        if (root != null) {
            inForeach(root.left);
            System.out.println(root.data);
            inForeach(root.right);
        }

    }

    //验证插入：
    public static void main(String[] args) {
        int[] data = {0, 2, 4, 3, 19, 6, 8, 5,7};

        //创建根节点：
        BinarySearchTree root = new BinarySearchTree(data[0]);
        for (int i = 1; i < data.length; i++) {
            root.insertNode(root, data[i]);
        }
        //中序遍历输出：
        root.inForeach(root);
    }
}
