package E1_binary_search_topic;

public class E5_InsufficientNodesInRootToLeafPaths {
    
      public class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode() {}
      TreeNode(int val) { this.val = val; }
      TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
          this.right = right;
      }
  }

    public static boolean solution(int currentSum, TreeNode prevNode, TreeNode node, int limit, int direction){
        if(node==null){
            return false;
        }
        boolean isLeft=solution(currentSum+node.val, node, node.left, limit, 0);
        boolean isRight=solution(currentSum+node.val, node, node.right, limit, 1);
        // if(prevNode!=null){
        //     System.out.printf("Sum: %s, Node: %s, left: %s, right: %s, isLeft %s, isRight %s\n", currentSum+node.val, node.val, node.left==null?null:node.val, node.right==null?null:node.val, isLeft, isRight);
        // }
        if(prevNode!=null&&currentSum+node.val<limit&&node.left==null&&node.right==null){
            if(direction==0){
                prevNode.left=null;
            }else{
                prevNode.right=null;
            }
            // System.out.printf("Removed %s, Prev %s\n", node.val, prevNode.val);
            return true;
        }else if((isLeft||isRight)&&prevNode!=null&&node.left==null&&node.right==null){
            if(direction==0){
                prevNode.left=null;
            }else{
                prevNode.right=null;
            }
            return true;
        }
        return false;
    }

    public TreeNode sufficientSubset(TreeNode root, int limit) {
        TreeNode node=root;
        if(root==null){
            return root;
        }
        solution(0, null, node, limit, -1);
        if(root.val<limit&&root.left==null&&node.right==null){
            return null;
        }
        return root;
    }

    public static void main(String[] args) {
        // Đề bài:
        //- Given root of binary tree and limit number
        //- Node is insufficient if every
        //* Delete all insufficient nodes in the tree simultaneously
        // Return root
        //
        // Tư duy
        //1.
        //1.1, Idea
        //-10^5 <= Node.val <= 10^5
        //-10^9 <= limit <= 10^9
        //
        //- Brainstorm
        //Ex:
        //                      1
        //                   /     \
        //                 2        3
        //               /   \    /   \
        //             4    -99 -99   7
        //           /   \  /   \      \
        //         8     9 -99 -99     14
        //- Xoá từ cuối trở lên đầu:
        //- Nếu current sum < limit + this node is leaf node ==> delete
        //- If the current node is not leaf node : Thôi ==> Cái này chỉ check khi loop xong left và right
        //
        //#Reference:
        //1. Two Sum
        //2265. Count Nodes Equal to Average of Subtree
    }
}
