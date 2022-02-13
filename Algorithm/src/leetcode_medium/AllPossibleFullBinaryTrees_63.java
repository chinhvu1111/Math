package leetcode_medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AllPossibleFullBinaryTrees_63 {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

//    public static HashMap<Integer, List<TreeNode>> mapNode=new HashMap<>();
    public static List<TreeNode>[] mapNode=new List[21];

    public static List<TreeNode> allPossibleFBT(int n) {
        List<TreeNode> prev=mapNode[n];

        if(prev!=null){
            return prev;
        }

        List<TreeNode> listTreeNode=new ArrayList<>();

        if(n==1){
            listTreeNode.add(new TreeNode());
            return listTreeNode;
        }

        //1, Đoạn này có thể tối ưu hơn nữa với (i+=2)
        for(int i=1;i<=n-2;i+=2){
            //Bỏ đi 1 root n
            int numberNodeRightode=n-i-1;
            List<TreeNode> lefts=allPossibleFBT(i);
            List<TreeNode> rights=allPossibleFBT(numberNodeRightode);

            for(TreeNode left: lefts){

                for(TreeNode right: rights){
                    TreeNode currentRootNode=new TreeNode();

                    currentRootNode.left=left;
                    currentRootNode.right=right;
                    listTreeNode.add(currentRootNode);
                }
            }
        }
        mapNode[n]=listTreeNode;
        return listTreeNode;
    }

    public static void main(String[] args) {
        int n=3;
        System.out.println(allPossibleFBT(n).size());
    }
}
