package E1_binary_search_topic;

import java.util.ArrayList;
import java.util.List;

public class E3_RecoverBinarySearchTree {
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

    public static void inorderTraverse(TreeNode node, List<TreeNode> listNodes){
        if(node==null){
            return;
        }
        inorderTraverse(node.left, listNodes);
        listNodes.add(node);
        inorderTraverse(node.right, listNodes);
    }

    public static void recoverTree(TreeNode root) {
        List<TreeNode> listNodes =new ArrayList<>();
        inorderTraverse(root, listNodes);
        int x=-1;
        int y=-1;
        int n=listNodes.size();
        boolean isDecreFirst=false;

//        System.out.println(listNodes.get(0).val+", ");
        for(int i=0;i<n;i++){
            //
            if(!isDecreFirst&&i+1<n&&listNodes.get(i).val>listNodes.get(i+1).val){
                x=i;
                isDecreFirst=true;
            }
            if(isDecreFirst&&i-1>=0&&listNodes.get(i).val<listNodes.get(i-1).val){
                y=i;
            }
//            System.out.printf("%s, ", listNodes.get(i).val);
        }
//        System.out.println();
//        System.out.printf("%s %s\n", x, y);
        TreeNode firstNode=listNodes.get(x);
        TreeNode secondNode=listNodes.get(y);
        int tmp= firstNode.val;
        firstNode.val= secondNode.val;
        secondNode.val=tmp;
    }

    public static void buildTree(int[] nums){

    }

    public static void main(String[] args) {
        //** Đề bài
        //- Cho binary tree với các node bị swap 1 cách tự do --> Nhiệm vụ recover lại thành đúng
        //- Chỉ swap đúng 2 nodes
        //- Sao cho không thay đổi structure của nó.
        //
        //** Bài này tư duy như sau:
        //1
        //1.1, concepts
        //- Ta cần phải nhớ cách traverse của inoder traversal:
        //+ Traverse sang node bên left branch tận cùng --> Quay về root node sau đó chạy sáng right branch
        //+ Trả lại traverse path theo template như sau:
        //==========================
        //inorder_traverse(root.left);
        //list.add(root.val);
        //inorder_traverse(root.right);
        //==========================
        //https://leetcode.com/problems/delete-node-in-a-bst/editorial/
        //                  2
        //              1       33
        //                  25      40
        //                              50
        //arr={1,2,25,33,40,50}
        //+ Array là array tăng dần do traverse từ (left --> right)
        //==========================
        //- Ta có 2 loại nodes cần quan tâm:
        //+ Successor : Node có min value mà nằm bên right branch
        //+ Predecessor : Node có max value mà nằm bên left branch
        //VD:
        //                      2
        //              1               [33] current node
        //          0       (25) [predecessor]      40
        //               11      34 [sucesssor]
        //- Để tìm được node successor/ predecessor thì ta có 2 cách implement khác nhau
        //==========================
        //public TreeNode successor(TreeNode root) {
        //  root = root.right;
        //  while (root.left != null) root = root.left;
        //  return root;
        //}
        //==========================
        //
        //1.2, Tư duy
        //* Method-1:
        //- Xác định dựa trên array tăng dần:
        //+ Ta sẽ traverse all Tree --> Dựng thành array
        //+ Từ array ta sẽ xác định 2 phần tử cần swap sau đó thực hiện swap value.
        //VD:
        //		    [2]
        //	    [5]     4
        //	 1	    3
        //--->
        //		    (5)
        //	    (2)      4
        //	 1	    3
        //arr={1,(5),3,(2),4}, array này cần increase
        //+ Ta cần tìm ra swap(5,2)
        //                    10
        //              [5]       14
        //          1      [3]
        //arr={1,(5),(3),10,14}
        //- Vậy tức là :
        //+ x=i nếu nums[i] >= nums[i-1]
        //+ y=i nếu nums[i] <= nums[i-1]
        //* Giải thích:
        //- Trong array tăng dần --> Nếu swap 2 phần tử bất kỳ thì array sẽ có xu hướng:
        //VD: a < b < c < d < e
        // + swap(b,d)
        // a < d > c > b < e
        //+ Tăng đến (d) giảm 1 lần sau đó tăng
        //+ Sau đó giảm 1 lần đến (b) sau đó tăng
        //--> d là ở vị trí (tăng rồi giảm)
        //--> b là ở vị trí (giảm rồi tăng)
        //+ Tăng rồi giảm 1 lần --> x
        //VD: (3),2,1
        //VD: 1,(3),2,1
        //VD: 1,(3) --> Không xảy ra vì luôn có 2 vị trí cần swap
        //+ Giảm 2 lần --> y
        //
        //VD-2: 3,2,1
        //          1
        //       3
        //          2
        //-->
        //           (3)
        //       (1)
        //           2
        //#Reference:
        //100. Same Tree
        //1273. Delete Tree Nodes
        //1319. Number of Operations to Make Network Connected
        //536. Construct Binary Tree from String
    }
}
