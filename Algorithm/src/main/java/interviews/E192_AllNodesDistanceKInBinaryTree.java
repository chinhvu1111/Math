package interviews;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class E192_AllNodesDistanceKInBinaryTree {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public static List<Integer> results;

    public static void getNodesDepthK(TreeNode node, List<Integer> result, int depth){
        if(node==null){
            return;
        }
        if(depth==1){
            result.add(node.val);
            return;
        }
        getNodesDepthK(node.left, result, depth-1);
        getNodesDepthK(node.right, result, depth-1);
    }

    public static int solutionDFS(TreeNode node, TreeNode target, int k, int height){
        if(node==null){
            return -1;
        }
        if(node==target){
            return height;
        }
        int result=solutionDFS(node.left, target, k, height+1);
        if(result!=-1){
            getNodesDepthK(node.right, results, k- result+height);
        }else{
            result=solutionDFS(node.right, target, k, height+1);
            if(result!=-1){
                getNodesDepthK(node.left, results, k- result+height);
            }
        }
        return result;
    }

    public static List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        results=new ArrayList<>();
        solutionDFS(root, target, k, 1);
        getNodesDepthK(target, results, k+1);
        return results;
    }

    public static void main(String[] args) {
//        Integer[] arr =new Integer[]{3,5,1,6,2,0,8,null,null,7,4};
//        int k=2;
//        TreeNode target=null;
//        int valueTarget=5;

        Integer[] arr =new Integer[]{1};
        int k=3;
        TreeNode target=null;
        int valueTarget=1;

        TreeNode[] treeNodes=new TreeNode[arr.length];
        for(int i=0;i<treeNodes.length;i++) treeNodes[i]=new TreeNode(0);

        TreeNode root=null;

        for(int i=arr.length-1;i>=0;i--){
            if(arr[i]==null){
                continue;
            }
            TreeNode currentNode=treeNodes[i];
            currentNode.val=arr[i];
            if(i==0){
                root=currentNode;
            }
            if(arr[i]==valueTarget){
                target=currentNode;
            }

            TreeNode treeNodeNext1=null;
            if(2*i+1<arr.length&&arr[2*i+1]!=null){
                treeNodeNext1=treeNodes[2*i+1];
                treeNodeNext1.val=arr[2*i+1];
            }

            TreeNode treeNodeNext2=null;
            if(2*i+2<arr.length&&arr[2*i+2]!=null){
                treeNodeNext2=treeNodes[2*i+2];
                treeNodeNext2.val=arr[2*i+2];
            }
            currentNode.left=treeNodeNext1;
            currentNode.right=treeNodeNext2;
        }
        System.out.println(distanceK(root, target, k));
        //
        //** Đề bài
        //-
        //
    }
}
