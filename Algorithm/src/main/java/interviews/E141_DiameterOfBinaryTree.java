package interviews;

public class E141_DiameterOfBinaryTree {

    public static class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode() {}
      TreeNode(int val) { this.val = val; }
      TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
          this.right = right;
      }
  }
    
    public static int diameterOfBinaryTree(TreeNode root) {
        int[] result=calculateDiameter(root);

        return result[0]-1;
    }

    public static int[] calculateDiameter(TreeNode treeNode){
        int[] currentResult =new int[]{0,0};

        if(treeNode==null){
            return currentResult;
        }
        int[] resultLeft= calculateDiameter(treeNode.left);
        int[] resultRight= calculateDiameter(treeNode.right);

        //Đoạn này là max path của mỗi node ==> Dễ sai chỗ này
        currentResult[0]=Math.max(resultLeft[0], resultRight[0]);
        //Độ dài path lớn nhất
        currentResult[0]=Math.max(currentResult[0], resultLeft[1]+resultRight[1]+1);
        //Độ sâu lớn nhất của các children
        //          4(2,3)
        //     2(1,1)  5(1,1)
        currentResult[1]=Math.max(resultLeft[1],resultRight[1])+1;

        return currentResult;
    }

    public static int ans=0;

    public static int diameterOfBinaryTreeOptimize(TreeNode root) {
        ans=0;
        calculateDiameterOptimize(root);

        if(ans==0){
            return 0;
        }

        return ans-1;
    }

    public static int calculateDiameterOptimize(TreeNode treeNode){
        int currentResult=0;

        if(treeNode==null){
            return currentResult;
        }
        int resultLeft= calculateDiameterOptimize(treeNode.left);
        int resultRight= calculateDiameterOptimize(treeNode.right);

        //Độ dài path lớn nhất
        ans=Math.max(ans, resultLeft+resultRight+1);
        //Độ sâu lớn nhất của các children
        //          4(2,3)
        //     2(1,1)  5(1,1)
        currentResult=Math.max(resultLeft,resultRight)+1;

        return currentResult;
    }

    public static void main(String[] args) {
        //
        //** Đề bài:
        //- Tìm chiều dài dài nhất giữa 2 nodes trong tree
        //
        //** Ta tư duy như sau:
        //
        //Cách 1:
        //1, DFS + FOR LOOP
        //
        //Cách 2:
        //- Chia để trị
        //
        //2,
        //2.1,
        //Ở đây ý tưởng là ta sẽ lưu lại thông tin tại mỗi node bao gồm
        //- result[0] : chính là độ dài path lớn nhất của điểm đó có thể đến các điểm con của nó (parent node --> sub node)
        //==> Mục đích để chia bài toán ra thành nhiều (root) nums[i] là root --> Khoảng cách (path) lớn nhất của nó là bao nhiều
        //Với 2 nodes của của nums[i]
        //
        //- result[1] : là dộ sâu của (parent node --> sub node (xa nhất của nó)
        //==> nums[i] cũng đồng thời ghi lại (độ sâu đến sub-node xa nhất)
        //==> Phục vụ việc tính cho các parent node của (node nums[i])
        //
        //** KINH NGHIỆM:
        //- Tư duy này khá hay khi vừa break ra được nhiều node
        //- Vừa dùng lại được kết quả
        //===> Bài này cũng có thể dùng dynamic programming được (Ghi lại khoảng cách path với start node là nums[i] ==> Đến all node còn lại)
        //Đoạn này là max path của mỗi node ==> Dễ thiếu chỗ này
        //+ currentResult[0]=Math.max(resultLeft[0], resultRight[0]);
        //
        //3, Complexity:
        //- Time complexity: O(N) : Vì cần travers all nodes (N nodes) (left + right)
        //- Space : O(1) / O(N)
        //##Reference
        //- Diameter of N-Ary Tree
        //- Longest Path With Different Adjacent Characters
        //Case 1;
//        TreeNode treeNode=new TreeNode(1);
//        TreeNode treeNode1=new TreeNode(2);
//        TreeNode treeNode2=new TreeNode(3);
//        TreeNode treeNode3=new TreeNode(4);
//        TreeNode treeNode4=new TreeNode(5);
//        treeNode.left=treeNode1;
//        treeNode.right=treeNode2;
//        treeNode1.left=treeNode3;
//        treeNode1.right=treeNode4;
        //Case 2:
        TreeNode treeNode=new TreeNode(1);
        TreeNode treeNode1=new TreeNode(2);
        treeNode.left=treeNode1;
        System.out.println(diameterOfBinaryTree(treeNode));
        //### Đối với cách này cần: reset ans trong main
        System.out.println(diameterOfBinaryTreeOptimize(treeNode));

    }
}
