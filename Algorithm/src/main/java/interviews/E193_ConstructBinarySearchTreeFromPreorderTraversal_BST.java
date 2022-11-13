package interviews;

public class E193_ConstructBinarySearchTreeFromPreorderTraversal_BST {

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

    public static TreeNode bstFromPreorder(int[] preorder) {
        int n=preorder.length;
        TreeNode[] treeNodes=new TreeNode[n];

        for(int i=0;i<n;i++){
            treeNodes[i]=new TreeNode(preorder[i]);
        }

        for(int i=0;i<n;i++){
            int currentValue=preorder[i];
            TreeNode nodeAssignedRight=null;
            TreeNode nodeAssignedLeft=null;
            int maxAssignedRight=-1;
            int maxAssignedLeft=-1;

            for(int j=i-1;j>=0;j--){
                if(preorder[j]<currentValue&&treeNodes[j].right==null){
                    if(maxAssignedRight<preorder[j]){
                        maxAssignedRight=preorder[j];
                        nodeAssignedRight=treeNodes[j];
                    }
                }
                if(preorder[j]>currentValue&&maxAssignedLeft==-1&&treeNodes[j].left==null){
                    nodeAssignedLeft=treeNodes[j];
                    maxAssignedLeft=preorder[j];
                }
            }
            if(nodeAssignedLeft!=null){
                nodeAssignedLeft.left=treeNodes[i];
                continue;
            }
            if(nodeAssignedRight!=null){
                nodeAssignedRight.right=treeNodes[i];
                continue;
            }
            System.out.printf("value: %s, assign to right of : %s, assign to left of: %s\n",currentValue, maxAssignedRight, maxAssignedLeft);
        }
        return treeNodes[0];
    }

    public static int index=0;

    public static TreeNode bstHelper(int[] preorder, int start, int end){
        if(index==preorder.length || preorder[index]<start || preorder[index]>end){
            return null;
        }
        TreeNode node=new TreeNode(preorder[index++]);
        node.left=bstHelper(preorder, start, node.val);
        node.right=bstHelper(preorder, node.val, end);
        return node;
    }

    public static TreeNode bstFromPreorderBottomUp(int[] preorder) {
        index=0;
        TreeNode root=bstHelper(preorder, Integer.MIN_VALUE, Integer.MAX_VALUE);
        return root;
    }

    public static TreeNode bstFromPreorderInterative(int[] preorder) {
        index=0;
        int n=preorder.length;

        TreeNode root=null;
        if(n!=0){
            root=new TreeNode(preorder[0]);
        }
        for(int i=1;i<n;i++){
            insertNode(root, preorder[i]);
        }
        return root;
    }

    public static void insertNode(TreeNode node, int currentValue){
        TreeNode temp=node;

        while (temp!=null){
            if(currentValue<temp.val){
                if(temp.left==null){
                    temp.left=new TreeNode(currentValue);
                    break;
                }
                temp=temp.left;
            }else if(currentValue>temp.val){
                if(temp.right==null){
                    temp.right=new TreeNode(currentValue);
                    break;
                }
                temp=temp.right;
            }
        }
    }

    public static void println(TreeNode node){
        if(node==null){
            return;
        }
        System.out.print(node.val+",");
        println(node.left);
        println(node.right);
    }

    public static void main(String[] args) {

        int[] arr=new int[]{8,5,1,7,10,12};
        TreeNode root=bstFromPreorder(arr);
        println(root);

//        int[] arr=new int[]{8};
//        TreeNode root=bstFromPreorder(arr);
//        println(root);

//        int[] arr=new int[]{8, 1};
//        TreeNode root=bstFromPreorder(arr);
//        println(root);

        //Test case 2:
        //            10
        //      3           19
        //        (14)  (14)
        //# 14 sẽ được đặt ở đâu bên phải 3 / bên trái 14
        //
//        int[] arr=new int[]{10,3,19,14};
//        TreeNode root=bstFromPreorder(arr);
//        println(root);
        System.out.println();

        //** Đề bài
        //- Chuyển 1 array dạng preorder trarversal --> binary tree
        //-, Return lại root
        //
        //- Inorder : Trái > Cha > Phải : DFS : ABCDEFGHI
        //- Preorder : Cha > Trái > Phải : DFS : FBADCEGIH
        //- Postorder : Trái > Phải > Cha : DFS : ACEDBHIGF
        //- Level-order : Từ trên xuống dưới, từ trái sang phải : DFS : FBGADICEH
        //
        //             8
        //         5       10
        //     1   (7)  (9)   12
        //  0   2
        //# : 8, 5, 1, 0, 2, (7), 10, (9), 12
        //[7] >5 : Tìm như thế nào
        //===> Số (max nhất - chỉ khi left) mà [7] > cho đến lúc traverse đến (7)
        //[9] <10 : Tìm như thế nào
        //===> Số (max nhất - chỉ khi right) mà [9]< cho đến lúc traverse đến 9
        //VD:
        //         8
        //       5   10
        //     1   (7:Nếu điền 7)
        //   0   2
        //        (3:Nếu điền 3)
        //==> Cần phải lưu lại thông tin các (value trung gian)
        //*** ==> Bài này chỉ cần thay đổi hướng tuy duy không dùng loop array
        //
        //** Bài này tư duy như sau:
        //Cách 1:
        //1,
        ////             8
        ////         5       10
        ////     1   (7)  (9)   12
        ////  0   2 6
        ////# : 8, 5, 1, 0, 2, (7), (6), 10, (9), 12
        //- Khi a<b ==> a chắc chắn nằm khác nhánh b
        //- Cách tìm (7) : Số lớn nhất mà 7 > (gán là right của node đó)
        //- Cách tìm (6) : Số gần nhất mà 6 < (gán là left của node đó)
        //1.1,
        //- Ta sẽ tạo 1 array các nodes của tree
        //- Loop O(n^2) : --> Gán theo các điều kiện bên trên
        //1.2, Các cases cần lưu ý:
        //- Cần phải chú ý phần log ra --> Nghĩ 1 chút về cách log
        //- Cần chú ý phần check max phần tử --> Phải lớn hơn mới lấy TreeNode ==> Tránh nhầm case
        //1.3, Case đặc biệt
        //            10
        //      3           19
        //        (14)  (14)
        //# 14 sẽ được đặt ở đâu bên phải 3 / bên trái 14
        //==> Cần ưu tiên left hơn --> Nếu (nodeAssignedLeft!=null) continue; luôn
        //
        //1.4, Complextity
        //- O(n^2)
        //Cách 2:
        //1, Cách tiếp cận
        //- Bottom up --> Xây dựng những sub-tree trước ==> Mỗi bước xây xong --> return root của (từng sub-trees)
        //- Cần phải duyệt để phù hợp với thứ tự preorder.
        //+ Duyệt từ dưới lên
        //VD: khi đến vị trí node.val=7 --> Cần bottom up để tìm vị trí điền 7 ==> Cái này chỉ cần check preorder[index (Đã được + từ trước]
        //+ Lúc tìm xong --> bottom up lên level tiếp theo ==> Để xét tiếp các node bên right.
        //+ Mỗi lần check đó --> Ta cần phải đi sâu hơn với mỗi lần check --> Nếu không thỏa mãn return null
        //1.1, Tư duy chính ở đây là tư duy dạng (start, end)
        //- Tính chất của bài này:
        //node.left : range (MIN, node.val)
        //node.right= range (node.val, MAX)
        //
        //1.2, Tính chất thêm:
        //Tính chất:
        //      a
        //    b   c
        //  d   (e)(right)
        //TC: e < a
        //VD
        ////                   8
        ////         5(MIN,8)          10
        ////     1(MIN, 5)     (7)    (9)   12
        ////  0 (MIN, 1)   2(1, 5)
        ////# : 8, 5, 1, 0, 2, (7), 10, (9), 12
        //1.3,
        //- Complexity : O(N) : Scan all nodes.
        //
        TreeNode root1= bstFromPreorderBottomUp(arr);
        println(root1);
        //Cách 3:
        //1, Tư tưởng chính
        //- Xác định root node
        //- Loop lần lượt add lần lượt new node dựa trên root node
        //1.1,
        //# Time complexity: O(n log(n))

        System.out.println();
        TreeNode root2= bstFromPreorderInterative(arr);
        println(root2);
    }
}
