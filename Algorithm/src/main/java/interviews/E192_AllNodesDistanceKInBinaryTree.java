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
            if(k- result+height==0){
                if(k- result+height==0){
                    results.add(node.val);
                }
            }
            getNodesDepthK(node.right, results, k- result+height);
        }else{
            result=solutionDFS(node.right, target, k, height+1);
            if(result!=-1){
                if(k- result+height==0){
                    results.add(node.val);
                }
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
//        TreeNode target=null;
//        int valueTarget=5;
//        int k=2;

//        Integer[] arr =new Integer[]{1};
//        TreeNode target=null;
//        int valueTarget=1;
//        int k=3;

//        Integer[] arr =new Integer[]{0,1,null,3,2};
//        TreeNode target=null;
//        int valueTarget=2;
//        int k=1;

        Integer[] arr =new Integer[]{0,1,null,3,2,6,null,5,4};
        TreeNode target=null;
        int valueTarget=3;
        int k=1;

        TreeNode[] treeNodes=new TreeNode[arr.length];
        for(int i=0;i<treeNodes.length;i++) treeNodes[i]=new TreeNode(0);

        TreeNode root=null;
        int j=1;

        for(int i=0;i<arr.length;i++){
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
            if(j<arr.length&&arr[j]!=null){
                treeNodeNext1=treeNodes[j];
                treeNodeNext1.val=arr[j];
            }

            TreeNode treeNodeNext2=null;
            if(j+1<arr.length&&arr[j+1]!=null){
                treeNodeNext2=treeNodes[j+1];
                treeNodeNext2.val=arr[j+1];
            }
            j+=2;
            currentNode.left=treeNodeNext1;
            currentNode.right=treeNodeNext2;
        }
        System.out.println(distanceK(root, target, k));
        //
        //** Đề bài
        //- Tìm các điểm cách điểm đã cho 1 khoảng k
        //
        //** Bài này tư duy như sau:
        //-
        //
        //# Reference:
        //- Amount of Time for Binary Tree to Be Infected
    }
}
