package E1_Tree;

import java.util.HashMap;

public class E13_ConstructBinaryTreeFromInorderAndPostorderTraversal {
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

    public static HashMap<Integer, Integer> mapIndexInorder;
    public static HashMap<Integer, Integer> mapIndexPostorder;
    public static int[] inorderOrigin;
    public static int[] postorderOrigin;

    public static TreeNode solutionWrong(int startPostOrder, int endPostOrder, int startInorder, int endInorder){
        if(startInorder==endInorder){
            return new TreeNode(inorderOrigin[startInorder]);
        }
        int rootNodeVal=postorderOrigin[endPostOrder];
        int indexOfInOrder=mapIndexInorder.get(rootNodeVal);
        int leftNodeInorder;
        int newStartPostOrder;

        if(indexOfInOrder!=0){
            leftNodeInorder=inorderOrigin[indexOfInOrder-1];
            newStartPostOrder=mapIndexPostorder.get(leftNodeInorder);
        }else{
//            leftNodeInorder=startInorder;
            newStartPostOrder=endPostOrder;
        }

        TreeNode currentRootNode=new TreeNode(rootNodeVal);
        System.out.printf("%s %s %s %s, ", startPostOrder, newStartPostOrder, startInorder, indexOfInOrder-1);
        System.out.printf("%s %s %s %s\n", startPostOrder, endPostOrder-1, indexOfInOrder+1, endInorder);
        if(startInorder<=indexOfInOrder-1){
            currentRootNode.left=solutionWrong(startPostOrder, newStartPostOrder, startInorder, indexOfInOrder-1);
        }
        if(indexOfInOrder+1<=endInorder){
            currentRootNode.right=solutionWrong(startPostOrder, endPostOrder-1, indexOfInOrder+1, endInorder);
        }

        return currentRootNode;
    }

    public static int postIndex=0;

    public static TreeNode solution(int start, int end){
        if(start>end){
            return null;
        }
        int rootNodeVal=postorderOrigin[postIndex];
        int indexOfInOrder=mapIndexInorder.get(rootNodeVal);
        postIndex--;

        TreeNode currentRootNode=new TreeNode(rootNodeVal);
//        System.out.printf("%s %s, ", start, indexOfInOrder-1);
//        System.out.printf("%s %s\n", indexOfInOrder+1, end);
        currentRootNode.right=solution(indexOfInOrder+1, end);
        currentRootNode.left=solution(start, indexOfInOrder-1);

        return currentRootNode;
    }

    public static TreeNode buildTree(int[] inorder, int[] postorder) {
        mapIndexInorder=new HashMap<>();
        int n=inorder.length;

        for(int i=0;i<n;i++){
            mapIndexInorder.put(inorder[i],i);
        }
        inorderOrigin=inorder;
        postorderOrigin=postorder;
        postIndex=n-1;
        TreeNode node=solution(0, n-1);
        println(node);

        return node;
    }

    public static void println(TreeNode node){
        if(node==null){
            return;
        }
        println(node.left);
        System.out.println(node.val);
        println(node.right);
    }

    public static void main(String[] args) {
        //** Requirement:
        //-
        //
        //** Idea
        //
        //* Method-1
        //1.0, Idea
        //- Given information:
        //+ Binary tree
        //+ Inorder and postOrder are unique values.
        //+ 1 <= length <= 3000
        //
        //-
        //Ex:
        //inorder=   9,3,15,20,7
        //postorder= 9,15,7,20,3
        //
        //inorder=   9,3,15,20,7
        //Case 1:
        //+ 9 is the right node
        //              3
        //           /
        //        8
        //          \
        //            9
        //I can not happen --> 8 must before 9
        //+ 9 is the left node --> OK
        //==> 3 is root of 9
        //(3) is root of 9
        //- Bài này thuộc dạng kinh điển về tính chất của inorder và postorder
        //VD:
        //inorder=   9,3,15,20,7
        //postorder= 9,15,7,20,3
        //
        //- postorder ta có các tính chất đặc biệt như:
        //+ Điểm cuối của postOrder luôn là root node.
        //
        //- Inorder ta có tính chất đặc biệt như
        //+ Điểm thứ 2 của dãy luôn là root node.
        //
        //Tách:
        //+ inorder : (9),3,(25,20,7)
        //+ postorder : (9,15,7,20),3
        //
        //- Mấu chốt của bài toán là chia làm sao (postOrder và inorder) thành 2 phần (left/ right) cho phù hợp
        //- Để chia nodes ta có quy luật sau đây:
        //postorder= 9,15,7,20,(3)
        //inorder=   9,(3),15,20,7
        //- Với postOrder Số sau 3 là các node là children của 3
        //- Với node sau (3) nó là những node là chilrend của (3)
        //  + 15 có thể là parent node của (3) hoặc là 1 node thuộc branch khác ==> Sẽ không cùng branch với (9)
        //  --> Ta có thể loại 15 ra khỏi các cases có thể xảy ra ở left node của (3)
        //                          postorder= 9,15,7,20,(3)
        //                          inorder=   9,(3),15,20,7
        //                  /                                   \ + ở postOder (right) tất cả các node trước (3) + các node sau (3) inorder
        //                 /                                     \+ Ở (left) của postOrder là map từ [15] --> [15] bên trên --> Các node left của [15] sẽ là node
        //                /                                        \ + của postOrder
        //          postorder= 9                           postorder= 9,[15],7,(20)
        //          inorder=9                              inorder=[15],(20),7
        //                                                 /                    \
        //                                      postorder= 9,15             postorder= 9,15,7
        //                                      inorder=15                  inorder= 7
        //VD:
        //              15
        //            /
        //          3
        //            \
        //              9
        //===> Tư duy trên bị SAI.
        //
        //VD:
        //          3
        //        /   \
        //      9      15
//        int[] inorder=new int[]{9,3,15,20,7};
//        int[] postorder=new int[]{9,15,7,20,3};
        //+ Test case 2:
//        int[] inorder=new int[]{2, 1};
//        int[] postorder=new int[]{2, 1};
        //          1
        //        /
        //      2
        //                          postorder= 2,1
        //                          inorder=   2,1 (1)
        //                          /               \
        //                postorder= 2          postorder= 2
        //                inorder= 2            inorder=...
//        int[] inorder=new int[]{1, 2};
//        int[] postorder=new int[]{2, 1};
//        buildTree(inorder, postorder);
        //+ Test case 3:
        //          1
        //        /
        //      2
        //        \
        //          3
        //                              postorder= 2,3,1
        //                              inorder=   3,2,1
        //                          /                       \
        //                postorder= 2                      postorder= 2,3
        //                inorder= 3,2                      inorder= ...
        //                   /              \                     \
        //                postorder= ...    postorder= 2          NONE
        //                inorder= 3        inorder= ...
//        int[] inorder=new int[]{2,3,1};
//        int[] postorder=new int[]{3,2,1};
//        buildTree(inorder, postorder);
        //+ Test case 4:
        int[] postorder=new int[]{1,2,3,4};
        int[] inorder=new int[]{2,1,4,3};
        //                                  postorder= 1,2,3,4
        //                                  inorder=   2,1,4,3 (start=0, end=3)
        //                          /                                   \
        //                postorder= 1,2                                postorder= 1,2,3
        //                inorder= 2,1                                  inorder= 3 (start=3, end=3) --> return 3
        //                   /              \                       /                   \
        //                postorder= ...    postorder= 1        postorder= 1,2          postorder= 1,2
        //                inorder= ...        inorder= 1          inorder= ...            inorder= ... ==> (start > end)
        //==>
        //                                4
        //                              /   \
        //                            2       3
        //                              \
        //                               1
        //- Có case: Inorder (start==end) --> Bỏ qua --> return null
        //-
        //#What diff
        //=========
        //if(start>end){
        //            return null;
        //        }
        //And
        //=========
        //==> Cái này đã giải thích bằng các cases đặc biệt phía trên.
        //
        //- Mấu chốt ở đây là:
        //+ Last node của post order traversal chắc chắn là root node của subtree
        //+ Map node đó trong inorder thì nhưng node right của root node --> Chính là (danh sách nodes) có trong (subtree) đó
        //+ Ta đi từ right --> left vì khi chọn được hết root node
        // --> Những (node còn lại của postOrder) chính là (danh sách node) phân chia của left branch.
        //
        //1.1, Complexity
        //- Space complexity : O(n)
        //+ Store all tree
        //
        //- Time complexity :
        //- Using master theorem:
        //https://www.giaithuatlaptrinh.com/?tag=master-theorem
        //+ Số node mỗi layer giống như nhau = f(n)
        //+ n là kích thước đầu vào
        //+ a là số bài toán con trong đệ quy
        //+ n/b là kích thước của mỗi bài toán con. Tất cả các bài toán con đều được giả định để có cùng kích thước.
        //+ f(n) là chi phí của công việc được thực hiện bên ngoài lời gọi đệ quy,
        // trong đó bao gồm chi phí phân chia vấn đề và chi phí hợp nhất các giải pháp
        //
        //+ Each subproblem have n/b size (a không bằng b vì b là giá trị trung bình khi chia n)
        //+ Mỗi layer sẽ là a^i children node --> a^i*f(n/b^i)
        //+ Ta có L là độ sâu của Tree : L = Log(b)(n)
        //--> Time complexity  = (xích ma) i:(1 -> L) a^i f(n/b^i)
        //+ a* f(n/b) = f(n) (layer-1) <=> a^i* f(n/b^i) = f(n) (all layer thứ i)
        // ==> T(n) = O( f(n) * L ) = O(f(n) * log(b)n) ** [ do số nodes mỗi layer giống nhau = n ]
        //+ a^i * f(n/b^i) = k^i * f(n) ==> T(n) = f(n) * i(1 --> L) k^i
        //--> Mà k < 1
        //==> T(n) = O ( f(n) ) = O(n) (Complexity)
        //CT:
        //- a so sánh với b^k
        //+ a < b^k Nếu a * f(n/b) = k * f(n) với k < 1 ta có T(n) = O(f(n))
        //+  Nếu a * f(n/b) = K * f(n) với K > 1 ta có T(n) = O(n^ log(b)a)
        //+ Nếu a * f(n/b) = f(n), ta có T(n)=O(f(n) ^ log(b)n)
        //
        //T(n) = a T(n/b) + f(n)
        //- Ta có ct:
        //+ a^(log(b)n)= n^(log(b)a) ==> Ở layer cuối cùng số nodes sẽ là [ n^(log(b)a) ]
        //Bellow code
        buildTree(inorder, postorder);
        //#Reference:
        //107. Binary Tree Level Order Traversal II
        //105. Construct Binary Tree from Preorder and Inorder Traversal
    }
}
