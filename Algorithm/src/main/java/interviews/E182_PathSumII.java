package interviews;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class E182_PathSumII {

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

    public static List<List<Integer>> rs;

    public static void subHasPathSum(TreeNode root, TreeNode parentNode, int targetSum, Stack<Integer> currentList) {
//        System.out.printf("Node %s, sum : %s \n", root, targetSum);

        //Test case 1:
        //1.1, Confuse trừ currentValue (trước khi duyệt / đang khi duyệt):
        //+ Nên trừ khi đang duyệt currentValue --> Để tránh việc null khi điểm cuối null --> trừ trước + truyền vào method
        //==> Có thể dẫn đến nullpointer + phải viết 1 đoạn check
        //
        //1.2, Check nốt leaf như thế nào, có 2 cách traverse:
        //- Duyệt đến điểm leaf
        //==> Lúc đó ==> Đoạn (sum - value) cần sửa lại.
        //
        //- Duyệt qua điểm leaf ==> Đến null luôn
        //==> Thì cần lưu lại parent của null --> Đến cuối rồi check (parent.left==null && parent.right)
        //
        if(targetSum==0&&root==null){
            if(parentNode.left==null&&parentNode.right==null){
                rs.add(new LinkedList<>(currentList));
                return ;
            }
            return ;
        }
        if(root==null){
            return;
        }
        currentList.add(root.val);
//        System.out.printf("%s ", root.val);
//        System.out.printf("Node : %s, sum : %s \n", root.val, targetSum);
        subHasPathSum(root.left, root,targetSum-root.val, currentList);
        currentList.pop();

        if(root.left!=null||root.right!=null){
            currentList.add(root.val);
            subHasPathSum(root.right, root, targetSum-root.val, currentList);
            currentList.pop();
        }
    }

    public static List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        rs=new LinkedList<>();
        if(root==null&&targetSum==0){
            return rs;
        }
        subHasPathSum(root,null, targetSum, new Stack<Integer>());
        return rs;
    }

    public static void findPathSumRefactor(TreeNode currentNode, int sum, List<Integer> currentPath, List<List<Integer>> allPaths){
        if(currentNode==null){
            return;
        }
        currentPath.add(currentNode.val);
        if(currentNode.val==sum&&currentNode.left==null&&currentNode.right==null){
            allPaths.add(new ArrayList<>(currentPath));
        }else{
            findPathSumRefactor(currentNode.left, sum-currentNode.val, currentPath, allPaths);
            findPathSumRefactor(currentNode.right, sum-currentNode.val, currentPath, allPaths);
        }
        currentPath.remove(currentPath.size()-1);
    }

    public static List<List<Integer>> pathSumRefactor(TreeNode root, int targetSum) {
        List<Integer> currentPath=new ArrayList<>();
        List<List<Integer>> allPaths=new ArrayList<>();

        findPathSumRefactor(root, targetSum, currentPath, allPaths);
        return allPaths;
    }

    public static void main(String[] args) {
        //
        //** Đề bài:
        //- Tìm all đường (root - leaf) có tổng bằng sum cho trước
        //
        //** Ta tư duy như sau:
        //1, 
        //1.1, Làm tương tự bài trước
        //--> Chỉ là cần bỏ các case dudplicate có thể xảy ra khi :
        //root.left ==null && root.right==null ==> Có thể dẫn đến add cùng 1 điểm ==> ra 2 dãy giống nhau
        //--> Vì cơ bản nó ở leaf thì left /right ==null ==> Sẽ bị tính 2 lần
        //
        //2, refactor code:
        //2.1,
        //- Đổi lại cách remove node --> Thay vì remove bên ngoài method () dạng:
        //======
        //add()
        //method()
        //pop()
        //add()
        //method()
        //pop()
        //======
        //- Phần remove giữa các function --> Ta sẽ remove node bên trong --> Lúc add result ta sẽ không return.
        //==> remove node ở đó
        //- Phần pop sau 2 method --> Thì ta để chung với phần remove bên trên
        //2.2, Phần sum --> Thay vì so sánh với 0 ==> Có thể xảy ra case đặc biệt
        //** ==> Ta sẽ so sánh với currentNode.val ==> return sau.
        //
        TreeNode treeNode=new TreeNode(12);
        System.out.println(pathSum(treeNode, 0));
        System.out.println(pathSumRefactor(treeNode, 0));
    }
}
