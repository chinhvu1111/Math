package E1_binary_search_topic;

public class E3_RecoverBinarySearchTree_wrong_idea {

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

    public static TreeNode[] solutionInorderTraversal(TreeNode root){
        TreeNode[] leftNode=null;
        TreeNode[] rightNode=null;

        if(root.left!=null){
            leftNode=solutionInorderTraversal(root.left);
        }
        if(root.right!=null){
            rightNode=solutionInorderTraversal(root.right);
        }
        TreeNode leftMin=null;
        TreeNode leftMax=null;
        TreeNode rightMin=null;
        TreeNode rightMax=null;

        if(leftNode!=null){
            leftMin=leftNode[0];
            leftMax=leftNode[1];
        }
        if(rightNode!=null){
            rightMin=rightNode[0];
            rightMax=rightNode[1];
        }
        if(leftMax!=null&&leftMax.val>root.val){
            int tmp=leftMax.val;
            leftMax.val=root.val;
            root.val=tmp;
            solutionInorderTraversal(leftMax);
        }
        if(rightMin!=null&&rightMin.val>root.val){
            int tmp= root.val;
            root.val=rightMin.val;
            rightMin.val=tmp;
            solutionInorderTraversal(rightMin);
        }
        if(rightMax!=null&&rightMax.val<root.val){
            int tmp= root.val;
            root.val=rightMax.val;
            rightMax.val=tmp;
            solutionInorderTraversal(rightMax);
        }
        if(leftMin==null){
            leftMin=root;
        }
        if(rightMax==null){
            rightMax=root;
        }
//        System.out.printf("%s %s\n", leftMin.val, rightMax.val);
        return new TreeNode[]{leftMin, rightMax};
    }

    public static void recoverTree(TreeNode root) {
        solutionInorderTraversal(root);
        println(root);
    }

    public static void println(TreeNode root){
        if(root==null){
            return;
        }
        System.out.println(root.val);
        println(root.left);
        println(root.right);
    }

    public static void main(String[] args) {
        //** Đề bài
        //- Cho binary tree với các node bị swap 1 cách tự do --> Nhiệm vụ recover lại thành đúng
        //- Sao cho không thay đổi structure của nó.
        //
        //** Bài này tư duy như sau:
        //1.
        //1.1, Idea
        //- Traverse tree --> Add nodes vào list ==> Sau đó dựng lại Tree (KHÔNG ĐƯỢC).
        //- Ta sẽ dùng phương pháp (max/ min heap) để làm ==> So sánh với max của các children node
        //+ left branch : nếu node.val < max --> swap
        //+ right branch : nếu node.val > max --> swap
        //+ Traverse theo inorder traversal --> Swap node để dần dần correct đúng.
        //--> Nó chính là cách traverse bình thường khi dùng đệ quy
        //+ function(a.left)
        //+ function(a.right)
        //VD:
        //               (3)
        //          5          4
        //      2     6   (2)       5
        //- Giả sử ta swap bên left branch trước sao cho root node có value lớn nhất > all left nodes.
        //- Nếu swap right --> Nếu thay root node bằng node nào khác có value > --> nó sẽ luôn lớn hơn all left nodes.
        TreeNode root=new TreeNode(1);
        TreeNode node1=new TreeNode(3);
        root.left=node1;
        TreeNode node2=new TreeNode(2);
        node1.right=node2;
        recoverTree(root);
    }
}
