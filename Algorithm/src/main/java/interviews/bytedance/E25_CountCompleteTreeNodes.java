package interviews.bytedance;

public class E25_CountCompleteTreeNodes {

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

    public static int countNodesSlow(TreeNode root) {
        return countSubNode(root);
    }

    public static int countSubNode(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int numLeft = countSubNode(node.left) + 1;
        int numRight = countSubNode(node.right) + 1;
//        System.out.println(numRight+" "+numLeft);
        return numRight + numLeft-1;
    }

    public static int countNodes(TreeNode root) {
        return countSubNode(root);
    }

    public static int countSubNodeReduceHalf(TreeNode node){
        if(node==null){
            return 0;
        }
        int h=getHeight(node);
//        System.out.printf("%s %s\n",h, node.val);
        if(h-1==getHeight(node.right)){
            int leftVal=1<<(h-1);
            return leftVal + countSubNodeReduceHalf(node.right);
        }else{
            int rightVal=1<<(h-2);
            return countSubNodeReduceHalf(node.left)+ rightVal;
        }
    }
    /*
    - Phần height nên viết thành return -1 (==null) để thể hiện số lượng edges (Độ sâu)
    return root == null ? -1 : 1 + height(root.left);
     */
    public static int getHeight(TreeNode node){
        if(node==null){
            return 0;
        }
        return getHeight(node.left)+1;
    }

    public static int countSubNodeInterative(TreeNode root){
        if(root==null){
            return 0;
        }
        TreeNode node=root;
        int h=getHeight(root);
        int numNodes=0;

        while (node!=null){
            int heightRight=getHeight(node.right);
            if(h-1==heightRight){
                numNodes+= 1<<(h -1);
                node=node.right;
            }else{
                numNodes+= 1<<(h -2);
                node=node.left;
            }
            //Đi vào sâu hơn nên h chiều cao lớn nhất của tree --> Luôn (h--)
            h--;
        }
        return numNodes;
    }

    public static int countNodesOthers(TreeNode root) {
        if (root == null)
            return 0;
        TreeNode left = root, right = root;
        int height = 0;
        while (right != null) {
            left = left.left;
            right = right.right;
            height++;
        }
        if (left == null)
            return (1 << height) - 1;
        return 1 + countNodesOthers(root.left) + countNodesOthers(root.right);
    }

    public static void main(String[] args) {
        TreeNode node = new TreeNode(1);
        TreeNode node1 = new TreeNode(2);
        TreeNode node2 = new TreeNode(3);
        node.left = node1;
        node.right = node2;
        TreeNode node3 = new TreeNode(4);
        TreeNode node4 = new TreeNode(5);
        node1.left=node3;
        node1.right=node4;
        TreeNode node5 = new TreeNode(6);
        node2.left=node5;
        //#O(n) complexity
        System.out.println(countNodesSlow(node));
        //
        //** Bài này tư duy như sau:
        //
        //1.
        //1.1, Ở đây ta có thể kiểm tra độ cao của tree bằng đệ quy Fright
        //H --> h-1 chính là phần chiều cao có thể là chưa fill hết
        //1.2,
        //- Phải tìm số x để có thể đánh giá được số note được Bill vào last rơ. ==> Cái này là tư duy toán (Ta có thể chọn tư duy đệ quy như bên dưới để tính)
        //+ Nếu cứ duyệt theo phương pháp bình thường thì sẽ vẫn sẽ phải duyệt hết
        //==> Vì ta sẽ tìm đến khi có node.left==null / right==null.
        //+ Số lượng nodes tại độ h chính là 2^edge (số lượng cạnh)
        //
        //- Node cuối cùng mà không điền:
        //+ Có thể là node có 1 left child thôi
        //+ Node không có left và right.
        //
        //1.2,
        //- Tư duy bài này thuần về kỹ thuật tối ưu để giảm số lượng vòng recursion
        //Ta có thể giảm left or right đi để không phải tính 1 trong 2:
        //==> Với dạng bài để tối ưu kiểu như này thì thường ta sẽ nghĩ đến việc tối ưu giảm run (left/ right)
        //1.3, Key tư duy:
        //- Ta có node và node.left, node.right:
        //VD-1
        //Các node không bị thiếu note ở last row có thể nằm root.left
        //
        //		.                  (18)root
        //	            15.(root.left)     30(root.right)
        //        40.           50.   100.      40
        //     8.     7.   (9)
        //
        //VD-2
        //		.                 (18)root
        //	            15.(root.left)        30(root.right)
        //        40.         50.         100      40
        //     8.     7.  9.     51.  (90)
        //
        //Các node thiếu chilren có thể nằm bên root.right.
        //
        //** Quy luật height= height(root.left)
        //==> Node bên trái cùng của root.right mà (tồn tại) ===> Số lượng note bên trái (root.left) ==> 2^height (Độ sâu của root)
        //- Node tổn tại <=> Check chiều cao của (root.right) == (height(root.left)+1 / height(root) hay không.
        //1.4, Chia ra thành từng cây con và tính số lượng note dần dần dựa trên height( 1 <<h) ==> mà không cần phải traverse all.
        //1.5,
        //- Time complexity : O(log(n))
        //- Space complexity : O(log(n))
        //1.6, Refacter interative method:
        //- Tối ưu bỏ đoạn check height của root bằng cách viết lặp.
        //- Chú ý đoạn này:
        //Đi vào sâu hơn nên h chiều cao lớn nhất của tree --> Luôn (h--)
        //+ h--;
        //+ Vẫn phải cần check height(node.right).
        //
        //1.7, Cách khác rất hay là ta sẽ check xem tree đó là
        //+ full node ở row cuối
        //+ Không có nodes ở row cuối
        //- Bằng cách left.left và right.right
        //==> Sau đó nếu rơi vào trường hợp đặc biệt thì ta return kết quả
        //<> 1 + countNodes(root.left) + countNodes(root.right);
        //==> Đi đều cả 2 sites tiếp.
        //# Time complexity : O(log(n))
        //# Space complexity : O(log(n))
        //### Refer:
        //https://leetcode.com/problems/count-complete-tree-nodes/solutions/61958/concise-java-solutions-o-log-n-2/
        //#Reference:
        //223. Rectangle Area
        //270. Closest Binary Search Tree Value
        System.out.println(getHeight(node));
        System.out.println();
        System.out.println(1<<3);
        System.out.println(countSubNodeReduceHalf(node));
        System.out.println(countSubNodeInterative(node));
        System.out.println(countNodesOthers(node));
    }
}
