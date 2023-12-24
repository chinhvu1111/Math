package E1_Tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class E25_BinaryTreeVerticalOrderTraversal {

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

    public static void getHeightNodes(TreeNode node, int height, HashMap<TreeNode, Integer> nodeToHeight){
        if(node==null){
            return;
        }
        nodeToHeight.put(node, height);
        getHeightNodes(node.left, height+1, nodeToHeight);
        getHeightNodes(node.right, height+1, nodeToHeight);
    }

    public static int getLeftFurthest(TreeNode node, int index){
        if(node==null){
            return index;
        }
        int left=Integer.MIN_VALUE;
        if(node.left!=null){
            left=getLeftFurthest(node.left, index+1);
        }
        int right=index;
        if(node.right!=null){
            right=getLeftFurthest(node.right, index-1);
        }
        return Math.max(left, Math.max(right, index));
    }

    public static int getRightFurthest(TreeNode node, int index){
        if(node==null){
            return index;
        }
        int left=Integer.MIN_VALUE;
        if(node.left!=null){
            left=getRightFurthest(node.left, index-1);
        }
        int right=index;
        if(node.right!=null){
            right=getRightFurthest(node.right, index+1);
        }
        return Math.max(left, Math.max(right, index));
    }

    public static void solution(TreeNode node, List<List<TreeNode>> rs, int index){
        if(node==null){
            return;
        }
        solution(node.left, rs, index-1);
        solution(node.right, rs, index+1);
        List<TreeNode> curValByIndex=null;
        // System.out.printf("Val:%s, index: %s\n", node.val, index);
        curValByIndex=rs.get(index);
        curValByIndex.add(node);
    }

    public static List<List<Integer>> verticalOrder(TreeNode root) {
        HashMap<TreeNode, Integer> nodeToHeight=new HashMap<>();
        getHeightNodes(root, 0, nodeToHeight);
        int furthestLeft=getLeftFurthest(root, 0);
        int furthestRight=getRightFurthest(root, 0);
        //2,1,0,1,2,3
        //==> Size=left+right+1
        List<List<Integer>> rs=new ArrayList<>();
        List<List<TreeNode>> rsTreeNode=new ArrayList<>();
        for(int i=0;i<furthestLeft+furthestRight+1;i++){
            rsTreeNode.add(new ArrayList<>());
        }
        // for(TreeNode key: nodeToHeight.keySet()){
        //     System.out.printf("%s %s\n", key.val, nodeToHeight.get(key));
        // }
        // System.out.printf("%s %s\n", furthestLeft, furthestRight);
        solution(root, rsTreeNode, furthestLeft);
        for(List<TreeNode> list: rsTreeNode){
            // System.out.printf("Before %s\n",list);
            list.sort((a, b) -> nodeToHeight.get(a) - nodeToHeight.get(b));
            // System.out.printf("After %s\n",list);
            List<Integer> curList=list.stream().map(a -> a.val).collect(Collectors.toList());
            if(curList.size()>0){
                rs.add(curList);
            }
        }
        return rs;
    }

    public static void main(String[] args) {
        // Requirement
        //- Given the root of a binary tree, return (the vertical order traversal) of its nodes' values.
        // (i.e., from top to bottom, column by column).
        //If two nodes are in the same row and column, the order should be from left to right.
        //Ex:
        //                  3
        //               /     \
        //             9        8
        //           /   \    /   \
        //         4      0/1      7
        //rs=[[4],[9],[3,0,1],[8],[7]]
        //
        // Idea
        //1.
        //1.0,
        //- Constraint:
        //
        //- Brainstorm
        //- Traverse ntn để scan được all nodes
        //Ex:
        //                  3
        //               /     \
        //             9        8
        //           /   \    /   \
        //         4      0/1      7
        //rs=[[4],[9],[3,0,1],[8],[7]]
        //- Mỗi node sẽ được đánh index khi so với root
        //- Rule:
        //+ Left: index++
        //+ Right: index--
        //==> Vì quy luật của left và right của root khác nhau ==> Ta cần tách ra để tính riêng.
        //==> WRONG
        //- Bài này tương tự bài E24_FindLeavesOfBinaryTree ==> Ta cũng sẽ lưu rs theo index.
        //- Vì index ta không thể biết được root ở index thứ mấy nên:
        //+ Ta cần get range (min, max)
        //
        //- Get xa nhất về left:
        //  + Left xa nhất của all nodes
        //- Get xa nhất về right:
        //  + right xa nhất của all nodes
        //==> Ta lấy max nhất của all node bao gồm cả đi right và left
        //  + Chỉ khác quy luật:
        //    + Nếu lâý left : left(index+1), right(index-1)
        //    + Nếu lâý right : right(index+1), left(index-1)
        //
        //- Sau đó ta fix size của rs
        //- Vì ở đây ta thêm 1 quy luật nữa ==> Mỗi list thì các node cần được add sort theo heigh của nodes đó
        //+ Ta cần dùng hashMap để lưu height của mỗi node
        //- Và ta cần lấy các node và sort lại lần nữa theo height là được.
        //
        //1.1, Optimization
        //
        //1.2, Complexity
        //- N is the number of nodes of tree
        //- h is the height of tree
        //- Space : O(n + h)
        //- Time : O(n*log(n))
        //
        //#Reference:
        //582. Kill Process
        //1110. Delete Nodes And Return Forest
        //2653. Sliding Subarray Beauty
    }
}
