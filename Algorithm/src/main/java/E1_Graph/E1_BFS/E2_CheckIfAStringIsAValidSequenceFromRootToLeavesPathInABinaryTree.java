package E1_Graph.E1_BFS;

public class E2_CheckIfAStringIsAValidSequenceFromRootToLeavesPathInABinaryTree {

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

    public static boolean isValid(int index, TreeNode currentNode, int[] arr){
        if(arr.length-1==index&&currentNode!=null&&currentNode.val==arr[index]&&currentNode.left==null&&currentNode.right==null){
            return true;
        }
        if(arr.length-1==index){
            return false;
        }
        if(currentNode==null){
            return false;
        }
        if(currentNode.val!=arr[index]){
            return false;
        }
        boolean isLeftValid=isValid(index+1, currentNode.left, arr);
        boolean isRightValid=isValid(index+1, currentNode.right, arr);
//        System.out.printf("%s %s %s\n", isLeftValid|isRightValid, index, arr[index]);

        return isLeftValid|isRightValid;
    }

    public boolean isValidSequence(TreeNode root, int[] arr) {
        if(arr.length==0){
            return true;
        }
        if(root==null){
            return false;
        }
        return isValid(0, root, arr);
    }

    public static void main(String[] args) {
        //** Requirement
        //- Valid sequence là các concat của all nodes đi từ root --> leaf node.
        //- Check xem array có phải valid sequence hay không
        //
        //** Idea
        //1.
        //1.0,
        //- Constraint
        //1 <= arr.length <= 5000
        //0 <= arr[i] <= 9
        //Each node's value is between [0 - 9].
        //
        //- Idea
        //- Ta sẽ check return false khi:
        //+ Ta đến element cuối cùng của array
        //+ Và ta vẫn còn node (left, right) của node đang xét
        //+ node.val != element.value
        //
        //1.1, Complexity
        //- N is number of node of tree
        //- M is number of elements of tree
        //- Time complexity : O(min(N, 2^M*M))
        //- Space complexity : O(N)
        //
        //#Reference:
        //2307. Check for Contradictions in Equations
        //270. Closest Binary Search Tree Value
        //1028. Recover a Tree From Preorder Traversal
    }
}
