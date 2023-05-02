package E1_Tree;

import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

public class E3_SymmetricTree {

    public class TreeNode {
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

    public static boolean isSymmetric(TreeNode root) {
        if(root==null){
            return true;
        }
        return solution(root.left, root.right);
    }

    public static boolean solution(TreeNode node1, TreeNode node2){
        if(node1==null&&node2==null){
            return true;
        }
        if(node1 == null || node2 == null){
            return false;
        }
//        System.out.printf("%s %s\n", node1.val, node2.val);
        if(node1.val!=node2.val){
            return false;
        }
        return solution(node1.left, node2.right)&&solution(node1.right, node2.left);
    }

    public static boolean isSymmetricIterative(TreeNode root) {
        if(root==null){
            return true;
        }
        Queue<Integer> listNodes=solutionIterative(root.left, true);
        Queue<Integer> listNodes1=solutionIterative(root.right, false);

        if(listNodes.size()!=listNodes1.size()) return false;

        while (!listNodes1.isEmpty()){
            if (Objects.equals(listNodes1.poll(), listNodes.poll())) {
                continue;
            }
            return false;
        }
        return true;
    }

    public static Queue<Integer> solutionIterative(TreeNode node1, boolean isLeft){
        Queue<TreeNode> nodesQueue=new LinkedList<>();
        Queue<Integer> listNodesTraverse=new LinkedList<>();
        nodesQueue.add(node1);
        if(node1==null){
            listNodesTraverse.add(null);
            return listNodesTraverse;
        }
        listNodesTraverse.add(node1.val);

        while (!nodesQueue.isEmpty()){
            TreeNode currentNode=nodesQueue.poll();
            TreeNode leftNode=null;
            TreeNode rightNode=null;

            if(currentNode!=null){
                leftNode=currentNode.left;
                rightNode=currentNode.right;
            }
            if(leftNode==null&&rightNode==null){
                listNodesTraverse.add(null);
                listNodesTraverse.add(null);
                continue;
            }
            if(isLeft){
                if(leftNode!=null){
                    nodesQueue.add(leftNode);
                    listNodesTraverse.add(leftNode.val);
                }else{
                    listNodesTraverse.add(null);
                }
                if(rightNode!=null){
                    nodesQueue.add(rightNode);
                    listNodesTraverse.add(rightNode.val);
                }else{
                    listNodesTraverse.add(null);
                }
            }else{
                if(rightNode!=null){
                    nodesQueue.add(rightNode);
                    listNodesTraverse.add(rightNode.val);
                }else{
                    listNodesTraverse.add(null);
                }
                if(leftNode!=null){
                    nodesQueue.add(leftNode);
                    listNodesTraverse.add(leftNode.val);
                }else{
                    listNodesTraverse.add(null);
                }
            }
        }
        System.out.println(listNodesTraverse);
        return listNodesTraverse;
    }

    public static void main(String[] args) {
        //** Đề bài:
        //- Check xem liệu cây hiện tại có đối xứng hay không
        //- Tức là tính từ root thì left branch <=> right branch (Nếu lộn ngược lại)
        //
        //** Tư duy:
        //1.
        //1.1, Idea
        //- Dùng phương pháp recursive
        //                1
        //          /          \
        //        2               2
        //    /      \         /     \
        //  (3)        4     (3)       4
        //+ Ta cần so sánh 2 nodes ở 2 level khác nhau.
        //- Tư tưởng là pass 2 nodes đầu vào method --> Sau đó traverse song song
        //+ Xét nếu có duy nhất 2 nodes nào khác nhau --> return false.
        //- Clear idea:
        //+ solution(node1, node2)
        //+ node1.val != node2.val --> return false
        //<> solution(node1.left, node2.left) --> phải cùng là true
        //+ đến lúc right : solution(node1.right, node2.right)
        //* left, true là như nhau --> Ta sẽ check node1!= node2 lúc vừa vào method.
        //
        //- Dùng phương pháp iterative:
        //                 1
        //           /          \
        //         2               2
        //     /      \         /     \
        //   (3)        4     (3)       4
        // /    \
        //1      5
        //- Để thể hiện được cấu trúc của tree ta cần:
        //+ 1 cấu trúc danh sách ndes theo thứ tự hợp lý:
        //+ Thể hiện được left, right branch
        //VD:
        //+ left branch: 2, 3, 1, 5, 4 ==> Kiểu preorder này không thể hiện rõ ràng left, right branch của 1 node
        //+ Ta có thể hiểu sai thành:
        //                      2
        //                    /
        //                  1
        //                /
        //              5
        //            /
        //          4
        //--> Sai
        //- Ở đây ta sẽ dùng 1 cấu trúc add left, right vào liên tục
        //VD:
        //queue=2,3,4,1,5,null,null
        //--> Khi đó sang nhánh right --> Ta sẽ pop dần dần ra
        //+ Nếu queue is empty trước khi travese xong : return false
        //+ Nếu queue is not empty sau khi travese xong : return false
        //<> return true
        //
        //- Traverse dùng loop --> BFS
        //                 1
        //           /          \
        //         2               2
        //     /      \         /     \
        //   (3)        4     (3)       4
        // /    \         \
        //1      5          6
        //BFS:
        //+ 2
        //+ 3,4
        //+ 4,1,5
        //+ 1,5,6 : 2,3,4,1,5,6 ==> Cần add cả null để thể hiện left, right
        //==> 2,3,4,1,5, null, 6
        //
        //#Reference:
        //102. Binary Tree Level Order Traversal
        //572. Subtree of Another Tree
        //513. Find Bottom Left Tree Value
        //1430. Check If a String Is a Valid Sequence from Root to Leaves Path in a Binary Tree
        //
    }
}
