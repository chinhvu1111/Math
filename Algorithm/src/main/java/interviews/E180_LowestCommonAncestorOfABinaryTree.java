package interviews;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class E180_LowestCommonAncestorOfABinaryTree {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public static HashSet<TreeNode> mapExistQ;
    public static HashSet<TreeNode> mapExistP;

    public static boolean isInDescentNode(TreeNode node, TreeNode currentNode, HashSet<TreeNode> hashSet) {
        if(hashSet.contains(node)){
            return true;
        }
        if (node == currentNode) {
            return true;
        }
        if (node == null) {
            return false;
        }

        boolean isInLeft = isInDescentNode(node.left, currentNode, hashSet);
        if(isInLeft){
            hashSet.add(node);
        }
        boolean isInRight = isInDescentNode(node.right, currentNode, hashSet);
        if(isInRight){
            hashSet.add(node);
        }

        return isInLeft | isInRight;
    }

    public static TreeNode findLowestCommonAncestor(TreeNode node, TreeNode p, TreeNode q) {
        boolean isInChildq = false;
        boolean isInChildp = false;

        if (node == null) {
            return null;
        }
        //Find q in the right
        isInChildq = isInDescentNode(node.right, q, mapExistQ);

        if (!isInChildq) {
            isInChildq = isInDescentNode(node.left, q, mapExistQ);
        }
        //Find p in the right
        isInChildp = isInDescentNode(node.right, p, mapExistP);

        if (!isInChildp) {
            isInChildp = isInDescentNode(node.left, p, mapExistP);
        }

        //Case trùng node
        if (isInChildq && node == p) {
            return node;
        }
        if (isInChildp && node == q) {
            return node;
        }

        //Nếu cả left và right đều không tìm thấy
        //--> False vì nó là node có khả năng xảy ra cao nhất
        if (!isInChildq || !isInChildp) {
            return null;
        }
        TreeNode left = findLowestCommonAncestor(node.left, p, q);

        //p và q chỉ có thể nằm 1 trong 2 bên của node root
        //==> Thế nên chỉ có left, right có kết quả mà thôi
        if (left != null) {
            return left;
        } else {
            TreeNode right = findLowestCommonAncestor(node.right, p, q);

            if (right != null) {
                return right;
            }
        }
        return node;
    }

    public static TreeNode lowestCommonAncestorHashSetTopDown(TreeNode root, TreeNode p, TreeNode q) {
        mapExistQ=new HashSet<>();
        mapExistP=new HashSet<>();
        TreeNode treeNode = findLowestCommonAncestor(root, p, q);

        return treeNode;
    }

    public static Queue<TreeNode> queueQ=new LinkedList<>();
    public static Queue<TreeNode> queueP=new LinkedList<>();

    public static boolean isInDescentNodeQueue(TreeNode node, TreeNode currentNode, Queue<TreeNode> queue) {
        if (node == currentNode) {
            //Phần này cần thêm vì có thể sẽ bị case trùng số cuối
            //[3,5,1,6,2,0,8,null,null,7,4]
            //p : 5
            //q : 4
            //==> Kết quả ==5 --> Không add vào queue thì sẽ bị sai
            queue.add(node);
            return true;
        }
        if (node == null) {
            return false;
        }

        boolean isInLeft = isInDescentNodeQueue(node.left, currentNode, queue);
        if(isInLeft){
            queue.add(node);
            return true;
        }
        boolean isInRight = isInDescentNodeQueue(node.right, currentNode, queue);
        if(isInRight){
            queue.add(node);
            return true;
        }

        return false;
    }

    public static TreeNode lowestCommonAncestorQueueBottomUp(TreeNode root, TreeNode p, TreeNode q) {
        queueQ=new LinkedList<>();
        queueP=new LinkedList<>();
        boolean nodeIsInLeft = (root==q) | isInDescentNodeQueue(root.left, q, queueQ);

        if(!nodeIsInLeft){
            nodeIsInLeft=isInDescentNodeQueue(root.right, q, queueQ);
        }
        if(nodeIsInLeft){
            queueQ.add(root);
        }
        boolean nodeIsInRight= (root==p) | isInDescentNodeQueue(root.right, p, queueP);

        if(!nodeIsInRight){
            nodeIsInRight=isInDescentNodeQueue(root.left, p, queueP);
        }
        if(nodeIsInRight){
            queueP.add(root);
        }

        HashSet<TreeNode> hashSetP=new HashSet<>();

        while (!queueP.isEmpty()){
            hashSetP.add(queueP.poll());
        }
        while (!queueQ.isEmpty()){
            TreeNode currentNode=queueQ.poll();

            if(hashSetP.contains(currentNode)){
                return currentNode;
            }
        }

        return null;
    }

    public static TreeNode lowestCommonAncestorEasy(TreeNode root, TreeNode p, TreeNode q) {
        //1, Đoạn này sẽ return node sâu nhất là cha (Ancestor) của (p/q)\
        //root --> p/q
        //2, Vì tìm theo cả p/q ==> Nó sẽ chọn 1 trong 2 --> Điểm nào higher --> Chọn điểm đó
        // VD: (q trước p) ==> return root của q.
        if(root==null||root==p||root==q){
            return root;
        }
        TreeNode left=lowestCommonAncestorEasy(root.left, p, q);
        TreeNode right=lowestCommonAncestorEasy(root.right, p, q);

        //Ta có thể tìm được (p/q) trên cả (left/ right)
        //3, khi q, p nằm trên 2 nhánh tách biệt
        //VD: q in left, p in right
        //==> Thì chỉ có thể chung gốc là root được mà thôi.
        if(left!=null&&right!=null){
            return root;
        }
        return left!=null?left:right;
    }

    public static void main(String[] args) {
        //Test case 2:
        //Case trùng node : currentNode= q/p

        //Test case 3:
        //[3,5,1,6,2,0,8,null,null,7,4]
        //p : 0
        //q : 8
        //            3
        //         5     [1]
        //       6   2  (0)  (8)
        //     7   4
        //(p : 0, q : 8)
        //* ==> Sai ít logic nhỏ khi điền node.(right/ left)
        TreeNode treeNode = new TreeNode(3);
        TreeNode treeNode1 = new TreeNode(5);
        TreeNode treeNode2 = new TreeNode(1);
        TreeNode treeNode3 = new TreeNode(6);
        TreeNode treeNode4 = new TreeNode(2);
        TreeNode treeNode5 = new TreeNode(0);
        TreeNode treeNode6 = new TreeNode(8);
        TreeNode treeNode7 = new TreeNode(7);
        TreeNode treeNode8 = new TreeNode(4);

        //q (0), p (8)
        //3 : (5,1)
        treeNode.left = treeNode1;
        treeNode.right = treeNode2;
        //5 : (6,2)
        treeNode1.left = treeNode3;
        treeNode1.right = treeNode4;
        //6 : (7,4)
        treeNode3.left = treeNode7;
        treeNode3.right = treeNode8;
        //1 : (0, 8)
        treeNode2.left = treeNode5;
        treeNode2.right = treeNode6;

        System.out.println(lowestCommonAncestorHashSetTopDown(treeNode, treeNode5, treeNode6).val);
        //
        //Test case 3:
        //[-64,12,18,-4,-53,null,76,null,-51,null,null,-93,3,null,-31,47,null,-7834,53,-81,33,4,null,5657,-44,-60,11,null,null,null,null,78,null,-35,-1800,26,8805,-4992,27,60,74,null,null,8,-38,-389,1968,-24,null,-59,-49,-11,-8262,67,null,null,null,null,null,null,null,-67,null,-37,-19,10,-55,72,null,null,null,-70,17,5857,null,null,null,null,null,null,null,2931,80,44,-88,-91,null,48,-90,-30,null,null,90,-34,37,null,null,73,4304,-6955,-85,3260,-96,null,null,-18,3243,34,-6847,null,-17,-77,null,56,-65,-5715,-377,null,null,null,-33,86,null,81,-42,null,null,98,-40,70,-26,24,null,null,null,null,92,-3953,-27,null,null,null,null,null,null,5400,null,null,null,null,null,null,null,-54,-66,-36,null,-72,null,null,43,null,null,null,-92,-1,-98,null,null,null,null,null,null,null,39,-84,null,null,null,null,null,null,null,null,null,null,null,null,null,5811,null,null,null,-5185]
        //74
        //-40
        //Lý do : Sai do node bị null
        //
        //
        //** Đề bài:
        //- Tìm cây con thấp nhất của 2 nodes đã cho --> Tức là max(height)
        //
        //** Ta tư duy như sau:
        //
        //Cách 1:
        //1, Nếu lấy từ root --> Sẽ luôn cho là con chung
        //- Tư duy chính ở đây là xét từ top --> bottom (Từng node xem node đó có trỏ được đến cả p/q hay không) ==> Nếu có thì next tiếp
        //- Với cách như thê này --> Mỗi lần next Node --> Ta lại phải traverse all nodes bên dưới (ascendant nodes)
        //
        //---> Tìm left/ right --> Cho đến khi không còn là con chung của (q,p)
        //VD:
        //isIn(node.left, q,p) --> false và (node.right, q,p)==false
        //==> Trả lại node hiện tại vì node hiện tại có thể chỉ là tệ nhất là root ==> Luôn luôn return true.
        //
        //1.1, Node hiện tại phải là common --> Thì mới next tiếp
        //==> Điều kiện xảy ra recursion
        //
        //1.2, Nếu node hiện tại ==q/ p thì sao?
        //==> Phải xét riêng với từng q,p == node
        //- Nếu xảy ra trường hợp node==q --> Sẽ chỉ xét p và <>
        //
        //1.3, Với những bài dạng Tree --> nên vẽ
        //
        //1.4, Tối ưu thì cần lưu việc contains lại ==> từ việc chạy check in root ==> Thì ta có thể suy ra các node liên quan mà contains (p/q)
        //- Dùng hashset để lưu việc đó --> return true cho nhanh.
        //
        //Cách 2:
        //2,
        //2.1, Tối ưu bằng cách không xét từng điểm nữa ==> Ta sẽ traverse bottom --> top ==> Mỗi bước sẽ lưu lại
        //- Node sâu nhất gần p/q ==> Mà it could be reached to p/q
        //--> Lưu vào trong 2 queue : Mục đích là để phân điểm gần p/q nhất ==> Convert ra haset là được
        //
        System.out.println(lowestCommonAncestorQueueBottomUp(treeNode, treeNode1, treeNode8).val);
        System.out.println(lowestCommonAncestorEasy(treeNode, treeNode1, treeNode8).val);
        //
        //Cách 3:
        //3,
        // Tư duy chính liên quan đến việc phân ra làm 2 cases:
        //- Left right cùng 1 bên ==> Chỉ cần parent của p/q (Điểm cao hơn)
        //- Left right khác bên ==> Trả lại root node mà tìm được thấy (p, q thuộc cả left / right)
        //
        //3.1,
        //- 1 tính chất khá quan trọng của tree ==> Tìm điểm cha của 1 điểm
        //1, Đoạn này sẽ return node sâu nhất là cha (Ancestor) của (p/q)\
        //root --> p/q
        //2, Vì tìm theo cả p/q ==> Nó sẽ chọn 1 trong 2 --> Điểm nào higher --> Chọn điểm đó
        // VD: (q trước p) ==> return root của q.
        //##CODE:
        //if(root==null||root==p||root==q){
        //    return root;
        //}
        //##
        //
        //3.2, ** Tư duy mở rộng --> Tìm đối với cả 2 nodes (q, p)
        //Ta có thể tìm được (p/q) trên cả (left/ right)
        //- khi q, p nằm trên 2 nhánh tách biệt
        //VD: q in left, p in right
        //==> Thì chỉ có thể chung gốc là root được mà thôi.
        //###CODE:
        //TreeNode left=lowestCommonAncestorEasy(root.left, p, q);
        //TreeNode right=lowestCommonAncestorEasy(root.right, p, q);
        //###
        System.out.println(lowestCommonAncestorEasy(treeNode, treeNode1, treeNode8).val);
        //
        //## reference:
        //- Lowest Common Ancestor of a Binary Search Tree
        //- Smallest Common Region
        //- Find Players With Zero or One Losses
        //- Lowest Common Ancestor of a Binary Tree II
        //- Lowest Common Ancestor of a Binary Tree III
        //- Lowest Common Ancestor of a Binary Tree IV
        //- Step-By-Step Directions From a Binary Tree Node to Another
    }
}
