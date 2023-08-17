package E1_Tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class E14_ClosestBinarySearchTreeValue {

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

    public static double subtraction;
    public static double result;
    public static void dfs(TreeNode node, double target){
        if(node==null){
            return;
        }
        if(subtraction>=Math.abs(node.val-target)){
            if(result>node.val&&subtraction==Math.abs(node.val-target)){
                result=node.val;
                subtraction=Math.abs(node.val-target);
            }else if(subtraction>Math.abs(node.val-target)){
                result=node.val;
                subtraction=Math.abs(node.val-target);
            }
        }
        // System.out.printf("%s %s\n",result, subtraction);
        if(target>node.val){
            dfs(node.right, target);
        }else {
            dfs(node.left, target);
        }
    }

    public static int closestValue(TreeNode root, double target) {
        subtraction=Integer.MAX_VALUE;
        result=Integer.MAX_VALUE;
        dfs(root, target);
        return (int) result;
    }

    public static void inorder(TreeNode node, List<Integer> sortArrs){
        if(node==null){
            return;
        }
        inorder(node.left, sortArrs);
        sortArrs.add(node.val);
        inorder(node.right, sortArrs);
    }

    public int closestValueInorderTraverse(TreeNode root, double target) {
        List<Integer> sortedArr=new ArrayList<>();
        inorder(root, sortedArr);
        double subtraction=Integer.MAX_VALUE;
        int rs=0;

        for (Integer integer : sortedArr) {
            if (Math.abs(integer - target) < subtraction) {
                subtraction = Math.abs(integer - target);
                rs = integer;
            } else {
                break;
            }
        }
        return rs;
    }

    public static int inorderIterativeWrong(TreeNode node, double target){
        Stack<TreeNode> nodesStack=new Stack<>();
        int pred = Integer.MIN_VALUE;
        System.out.println(node.val);

        while (!nodesStack.isEmpty()||node!=null){
            while (node!=null){
                nodesStack.add(node);
                node=node.left;
            }
            node=nodesStack.pop();
            System.out.printf("1: %s %s %s %s\n", pred, node.val, Math.abs(target-node.val), Math.abs(target-pred));
            if(node.val>=target&&target>=pred){
                if(Math.abs(target-node.val)>Math.abs(target-pred)){
                    return pred;
                }else if(Math.abs(target-node.val)==Math.abs(target-pred)){
                    return (node.val<target)?(int)node.val:(int)target;
                }else{
                    return node.val;
                }
            }
//            System.out.println(node.val);
            pred=node.val;
            node=node.right;
            System.out.printf("Node %s\n", node);
            if(node!=null){
                System.out.printf("2: %s %s\n", pred, node.val);
            }
            System.out.printf("Node %s, %s\n", node, node!=null&&node.val>=target&&target>=pred);
            if(node!=null){
                System.out.printf("Node %s, %s:%s, %s\n", node, node.val, pred, node!=null&&node.val>=target&&target>=pred);
            }
            if(node!=null&&node.val>=target&&target>=pred){
                if(Math.abs(target-node.val)>Math.abs(target-pred)){
                    return pred;
                }else if(Math.abs(target-node.val)==Math.abs(target-pred)){
                    return (node.val<target)?(int)node.val:(int)target;
                }else{
                    return node.val;
                }
            }
            if(node!=null){
                pred=node.val;
            }
        }
        return pred;
    }

    public static int inorderIterative(TreeNode node, double target){
        Stack<TreeNode> nodesStack=new Stack<>();
        int pred = Integer.MIN_VALUE;
        System.out.println(node.val);

        while (!nodesStack.isEmpty()||node!=null){
            while (node!=null){
                nodesStack.add(node);
                node=node.left;
            }
            node=nodesStack.pop();
            System.out.printf("Traverse %s \n", node.val);
            System.out.printf("1: %s %s %s %s\n", pred, node.val, Math.abs(target-node.val), Math.abs(target-pred));
            if(node.val>=target&&target>=pred){
                if(Math.abs(target-node.val)>Math.abs(target-pred)){
                    return pred;
                }else if(Math.abs(target-node.val)==Math.abs(target-pred)){
                    return (node.val<target)?(int)node.val:(int)target;
                }else{
                    return node.val;
                }
            }
//            System.out.println(node.val);
            pred=node.val;
            node=node.right;
            System.out.printf("Node %s\n", node);
        }
        return pred;
    }

    public int closestValueIterative(TreeNode root, double target) {
        return inorderIterative(root, target);
    }

    public static void main(String[] args) {
        //** Requirement
        //- Cho 1 binary tree + target (double) --> Tìm điểm closest to target
        //- Closest tức là số gần target nhất
        //VD: 3, 3.7, 4 ==> 3.7 cách 4 (0.3) nên nó là closest.
        //
        //** Idea
        //
        //* Method-1: Traverse bình thường
        //1.
        //1.0,
        //- Constraint
        //
        //- Brainstorm
        //- Đơn giản là ta sẽ traverse tree:
        //+ Xét hiệu của từng số
        //
        //1.1, Optimization
        //
        //1.2, Complexity:
        //+ N is the number of node
        //- Time complexity : O(N)
        //- Space complexity : O(N)
        //
        //* Method-2: Recursive inorder + Linear search O(N) time
        //2.
        //2.0,
        //- Chỉ đơn gian là tạo 1 sorted array --> Sau đó tìm result.
        //
        //* Method-3: Iterative inorder traverse
        //3.
        //3.0,
        //Ta có:
        //- nums[i] <= target < nums[i+1]
        //- Số cần tìm là 1 trong 2 số nums[i] và nums[i+1]
        //- Khi traverse tree ta khi ta tìm được range --> stop (Không cần traverse the whole tree)
        //
        //3.1, Lỗi sai:
        //- Special test case:
        //+ target = 3.285714
        //                          41
        //                        /
        //                    37
        //                  /
        //                24
        //               /
        //             1
        //           /   \
        //         0      2
        //                 \
        //                  4
        //                /
        //              3
        //+ Thứ tự chạy sẽ là left ==> 0 sau đó sẽ pop 0, 1
        //+ right -> 1 -> right -> 2 -> right -> 4 -> left -> 3
        //* Ở đây không nên viết việc check 2 lần theo kiểu sorted array vì sẽ có case:
        //+ So sánh (2 < 3.285714 < 4)
        //- Ở đây nếu node = node.right ==> Vòng sau khi sang left ta mới nên check
        //+ Nếu 1 - 2 ==> Sẽ check range (1,2) vì 2 không có left
        //+ Nếu 2 - 4 ==> Sẽ check range (3,4) vì 4 có left
        //* ==> Ta sẽ check (2 --> 3 (left cuối của 4)) + Update (pred=3)
        //--> Sau đó sẽ check tiếp sau.
        //
        //** Lỗi sai:
        //- Check 2 lần liên thiên
        //- Max function cần cast ra double.
        //
        //#Reference:
        //272. Closest Binary Search Tree Value II
        //700. Search in a Binary Search Tree
        //2476. Closest Nodes Queries in a Binary Search Tree
    }
}
