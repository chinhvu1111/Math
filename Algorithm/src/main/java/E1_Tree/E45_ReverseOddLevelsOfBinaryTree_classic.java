package E1_Tree;

import java.util.*;

public class E45_ReverseOddLevelsOfBinaryTree_classic {

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

    public static TreeNode solution(TreeNode node){
        Queue<TreeNode> queue=new LinkedList<>();
        queue.add(node);
        int depth=0;

        while (!queue.isEmpty()){
            int n=queue.size();
            Queue<TreeNode> curNodes=new LinkedList<>();
            List<TreeNode> nextNodes=new ArrayList<>();

            for(int i=0;i<n;i++){
                TreeNode curNode = queue.poll();
                curNodes.add(curNode);
                if(curNode.left!=null){
                    nextNodes.add(curNode.left);
                }
                if(curNode.right!=null){
                    nextNodes.add(curNode.right);
                }
            }
//            if(depth%2==0){
////                queue.addAll(nextNodes);
//            }else{
//                Collections.reverse(nextNodes);
////                for(int j=nextNodes.size()-1;j>=0;j--){
////                    queue.add(nextNodes.get(j));
////                }
//            }
            if(depth%2==1){
                Collections.reverse(nextNodes);
            }
            queue.addAll(nextNodes);
            //    3        5
            //  /   \     /   \
            // 8    13   21   34
            //    5       3
            // /   \    /   \
            //21   34  8    13
            int index=0;
            int size=nextNodes.size();

            while(!curNodes.isEmpty()){
                TreeNode curNode = curNodes.poll();
                if(!nextNodes.isEmpty()){
                    if(depth%2==0){
                        curNode.left=nextNodes.get(size-index-1);
                    }else{
                        curNode.right=nextNodes.get(index);
                    }
                }
                index++;
                if(!nextNodes.isEmpty()){
                    if(depth%2==0){
                        curNode.right=nextNodes.get(size-index-1);
                    }else{
                        curNode.left=nextNodes.get(index);
                    }
                }
                index++;
            }
            depth++;
        }
        return node;
    }

    public static TreeNode reverseOddLevels(TreeNode root) {
        return solution(root);
    }

    public static TreeNode solutionRefactor(TreeNode node){
        Queue<TreeNode> queue=new LinkedList<>();
        queue.add(node);

        while (!queue.isEmpty()){
            int n=queue.size();
            List<TreeNode> curNodes=new ArrayList<>();
            List<TreeNode> nextNodes=new ArrayList<>();

            for(int i=0;i<n;i++){
                TreeNode curNode = queue.poll();
                curNodes.add(curNode);
                if(curNode.left!=null){
                    nextNodes.add(curNode.left);
                }
                if(curNode.right!=null){
                    nextNodes.add(curNode.right);
                }
            }
//            if(depth%2==0){
////                queue.addAll(nextNodes);
//            }else{
//                Collections.reverse(nextNodes);
////                for(int j=nextNodes.size()-1;j>=0;j--){
////                    queue.add(nextNodes.get(j));
////                }
//            }
            List<TreeNode> nextNextNodes=new ArrayList<>();
            for(int i=0;i<nextNodes.size();i++){
                if(nextNodes.get(i).left!=null){
                    nextNextNodes.add(nextNodes.get(i).left);
                    queue.add(nextNodes.get(i).left);
                }
                if(nextNodes.get(i).right!=null){
                    nextNextNodes.add(nextNodes.get(i).right);
                    queue.add(nextNodes.get(i).right);
                }
            }
            Collections.reverse(nextNodes);
            //    3        5
            //  /   \     /   \
            // 8    13   21   34
            //    5       3
            // /   \    /   \
            //8    13   21   34
            //+ Keep order
            //+ Change (left and right) of the nodes at the (odd level)
            //
            int index=0;

            //Change (left and right node) of the current nodes
            for(int i=0;i<curNodes.size();i++){
                TreeNode curNode = curNodes.get(i);
                if(!nextNodes.isEmpty()){
                    curNode.left=nextNodes.get(index);
                }
                index++;
                if(!nextNodes.isEmpty()){
                    curNode.right=nextNodes.get(index);
                }
                index++;
            }
            index=0;
            for (int i = 0; i < nextNodes.size(); i++) {
                TreeNode curNode = nextNodes.get(i);
                if(!nextNextNodes.isEmpty()){
                    curNode.left=nextNextNodes.get(index);
                }
                index++;
                if(!nextNextNodes.isEmpty()){
                    curNode.right=nextNextNodes.get(index);
                }
                index++;
            }
        }
        return node;
    }

    public static TreeNode reverseOddLevelsRefactor(TreeNode root) {
        return solutionRefactor(root);
    }
    public static TreeNode reverseOddLevelsBFSRefer(TreeNode root) {
        if (root == null) {
            return null; // Return null if the tree is empty.
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root); // Start BFS with the root node.
        int level = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            List<TreeNode> currentLevelNodes = new ArrayList<>();

            // Process all nodes at the current level.
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                currentLevelNodes.add(node);

                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }

            // Reverse node values if the current level is odd.
            if (level % 2 == 1) {
                int left = 0, right = currentLevelNodes.size() - 1;
                while (left < right) {
                    int temp = currentLevelNodes.get(left).val;
                    currentLevelNodes.get(left).val = currentLevelNodes.get(
                            right
                    ).val;
                    currentLevelNodes.get(right).val = temp;
                    left++;
                    right--;
                }
            }

            level++;
        }

        return root;
    }

    public static void solutionDFS(TreeNode left, TreeNode right, int level){
        if(left==null||right==null){
            return;
        }
        if(level%2==0){
            int temp=left.val;
            left.val= right.val;
            right.val=temp;
        }
        solutionDFS(left.left, right.right, level+1);
        solutionDFS(left.right, right.left, level+1);
    }

    public static TreeNode reverseOddLevelsDFS(TreeNode root) {
        solutionDFS(root.left, root.right, 0);
        return root;
    }

    public static void main(String[] args) {
        //** Requirement
        //- Given (the root of a perfect binary tree), reverse (the node values) at each (odd level of the tree).
        //- For example, suppose the node values at level 3 are [2,1,3,4,7,11,29,18], then it should become [18,29,11,7,4,3,1,2].
        //* Return (the root of the reversed tree).
        //- A binary tree is perfect if all parent nodes have (two children) and all leaves are on the same level.
        //- The level of a node is (the number of edges) along the path between (it and the root node).
        //
        // Idea
        //1.
        //1.0,
        //- Method-1:
        //+ Constraints:
        //The number of nodes in the tree is in the range [1, 2^14].
        //0 <= Node.val <= 10^5
        //root is a perfect binary tree.
        //
        //- Brainstorm
        //Ex
        //              2
        //       /           \
        //      3             5
        //    /   \         /   \
        //  8      13     21     34
        // / \    /  \   /  \    /  \
        //1   2  3    4 5    6  7    8
        //==>
        //currentNodes[] = [3,5]
        //nextNodes[] = [21,34,8,13] ==> Keep order for (each node) of the (current nodes)
        //              2
        //       /             \
        //      5               3 ==> Reverse
        //    /   \          /   \
        //  8      13      21     34 ==> Keep order
        // / \    /  \   /  \    /  \
        //8   7  6    5 4    3  2    1 ==> Reverse
        //
        //- If we just swap left and right node for each other
        //  + It is not enough because we need to reverse all of nodes at the same level (odd depth)
        //- We use BFS approach:
        //  + For each level, we
        //
        //- The characteristic:
        //Ex
        //              2
        //       /           \
        //      3             5
        //    /   \         /   \
        //  8      13     21     34
        // / \    /  \   /  \    /  \
        //1   2  3    4 5    6  7    8
        //- If we reverse [8,13,21,34] => [34,21,13,8]
        //  + The next nodes will be changed:
        //  + [1,2,3,4,5,6,7,8]
        // => [7,8,5,6,3,4,1,2]
        // ==> We (can not get the next values) (after reversing)
        //* We want to have:
        //  + Reverse [8,13,21,34] => [34,21,13,8]
        //  + Change (left and right node) of these nodes
        //      + ==> Map to the (keeping order) next node
        //  + We can continue traverse by using [(keeping order) next nodes]
        //
        //* Solution:
        //  + Reverse order of current nodes
        //  + Add all of (keeping order next nodes) to (queue)
        //  + Change left and right node of current nodes
        //- Tuy nhiên ta sẽ bị trường hợp:
        //  + Ta đang chỉ xét 2 layers
        //      + Nếu ta add layers 2 ==> Next nodes để bfs tiếp thì cần chú ý:
        //          + Left node và right node của nó sẽ bị thay đổi
        //          ==> Để đơn giản
        //* Ta sẽ scan 3 layers luôn
        //==> Nếu chỉ scan 2 layers
        //  + Nó sẽ cần phải add lại layers 2
        //  ==> Tăng time complexity from O(n) to O(n*2)
        //
        //- If we check 3 layers:
        //  + We need to care about the second layer:
        //Ex:
        //             2
        //         /      \
        //        3        5
        //      /   \    /  \
        //     8    13 21    34
        //- If we only reverse (3,5) without reassigning the left and right node of the (3,5)
        //=> We will get
        //Ex:
        //            2
        //        /      \
        //       5        3
        //      /   \    /  \
        //     34   21 13    8 ==> WRONG
        //     Expected rs = [8,13,21,34]
        //==> Reassign the (left and right node) 2nd layer
        //
        //Ex:
        //nums = [8,13,21,34]
        //=> [34,21,13,8]
        //nums = [8,13,21,34]
        //+ swap: [13,8,21,34]
        //+ swap: [13,21,8,34]
        //+ swap: [13,21,34,8]
        //
        //1.1, Optimization
        //* KINH NGHIỆM:
        //- Thay vì cố thay đổi relationship
        //  ==> Ta sẽ chỉ ("THAY ĐỔI VALUE") bằng cách reverse
        //==> Ta không cần lo đến việc relationship giữa các layers với nhau
        //
        //- New way to traverse using DFS:
        //  + From (left++, right--)
        //
        //======== CODE
        //  solutionDFS(left.left, right.right, level+1);
        //  solutionDFS(left.right, right.left, level+1);
        //======== CODE
        //Ex:
        //                     2
        //             /              \
        //            5                3
        //      /           \         /  \
        //     34(left.left) 21     13    8 (right.right)
        //1.2, Complexity
        //- Space: O(n)
        //- Time: O(n) => O(log(n))
        //
        Integer[] nodes=new Integer[]{2,3,5,8,13,21,34};
        TreeNode root=new TreeNode(nodes[0]);
        Queue<TreeNode> queueNodes=new LinkedList<>();
        queueNodes.add(root);
        int i=0;
        for(;i<nodes.length;i++){
            TreeNode currentNode=queueNodes.poll();
            TreeNode next=null;
            TreeNode next1=null;
            if(i*2+1<nodes.length&&nodes[i*2+1]!=null){
                next=new TreeNode(nodes[i*2+1]);
                queueNodes.add(next);
            }
            if(i*2+2<nodes.length&&nodes[i*2+2]!=null){
                next1=new TreeNode(nodes[i*2+2]);
                queueNodes.add(next1);
            }
            if(currentNode!=null){
                currentNode.left=next;
                currentNode.right=next1;
            }
        }
//        TreeNode node = reverseOddLevels(root);
//        TreeNode node = reverseOddLevelsRefactor(root);
//        TreeNode node = reverseOddLevelsBFSRefer(root);
        TreeNode node = reverseOddLevelsDFS(root);
        System.out.println(node.val);
    }
}
