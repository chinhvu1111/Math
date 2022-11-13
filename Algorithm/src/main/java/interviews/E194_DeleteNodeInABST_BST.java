package interviews;

import jdk.nashorn.internal.objects.annotations.Where;

public class E194_DeleteNodeInABST_BST {

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

    public static TreeNode[] findNode(TreeNode root, int key){
        TreeNode temp=root;
        TreeNode beforeNode=null;

        while (temp!=null){
            TreeNode prevNode=temp;
            if(key<temp.val){
                temp=temp.left;
            }else if(key>temp.val){
                temp=temp.right;
            }else{
                return new TreeNode[]{temp, beforeNode};
            }
            beforeNode=prevNode;
        }
        return new TreeNode[]{temp, beforeNode};
    }

//    public static TreeNode deletedNode(TreeNode node){
//        if(node==null){
//            return null;
//        }
//        if(node.left==null&&node.right==null){
//            return null;
//        }
//        if(node.left!=null&&node.right==null){
//            return node.left;
//        }
//        if(node.right!=null&&node.left==null){
//            return node.right;
//        }
//        if(node.right!=null&&node.left!=null&&node.left.right==null
//                &&node.left.left==null&&node.right.left==null&&node.right.right==null){
//            node.left.right=node.right;
//            return node.left;
//        }
//        if(node.left!=null){
//            node.left=deletedNode(node.left);
//        }else{
//            node.right=deletedNode(node.right);
//        }
//        return node;
//    }

    public static TreeNode getRelacementNodeRight(TreeNode node, TreeNode prev){
        TreeNode temp=node;
        TreeNode replacedNode=node;
        TreeNode prevNode=prev;

        while (temp!=null){
            prevNode=replacedNode;
            replacedNode=temp;
            temp=temp.left;
        }
        //Có xảy ra rẽ nhánh right
        if(prevNode!=prev){
            prevNode.left=null;
        }
        //Không xảy ra rẽ nhánh right
        else{
            prev.right=null;
        }
        return replacedNode;
    }

    public static TreeNode getRelacementNodeLeft(TreeNode node){
        TreeNode temp=node;
        TreeNode replacedNode=null;
        TreeNode prevNode=node;

        while (temp!=null){
            if(replacedNode!=null){
                prevNode=replacedNode;
            }
            replacedNode=temp;
            temp=temp.right;
        }
        if(prevNode!=null){
            prevNode.right=null;
        }
        return replacedNode;
    }

    public static TreeNode deleteNode(TreeNode root, int key) {
        if(root==null){
            return null;
        }
        TreeNode[] nodes=findNode(root, key);
        TreeNode prevNode=nodes[1];
        TreeNode deteledNode=nodes[0];
        TreeNode relacedNode=null;

//        if(deteledNode!=null&&deteledNode.left!=null){
//            if(prevNode!=null){
//                prevNode.left=null;
//            }
//            relacedNode=getRelacementNodeRight(deteledNode.left);
//        }
        if(deteledNode!=null&&deteledNode.right!=null){
            relacedNode=getRelacementNodeRight(deteledNode.right, deteledNode);
        }
        //Nếu case mà chỉ có 2 node
        else if(prevNode!=null&&deteledNode!=null&&deteledNode.left==null&&deteledNode.right==null){
            if(deteledNode.equals(prevNode.right)){
                prevNode.right=null;
            }
            if(deteledNode.equals(prevNode.left)){
                prevNode.left=null;
            }
        }else if(deteledNode!=null&&deteledNode.left!=null){
            relacedNode=deteledNode.left;
        }
        else if(deteledNode!=null){
            return null;
        }

        if(relacedNode!=null){
            if(prevNode==null){
                if(relacedNode!=root.right){
                    TreeNode tmp=relacedNode;
                    while (tmp.right!=null){
                        tmp=tmp.right;
                    }
                    tmp.right=root.right;
                }
                if(relacedNode!=root.left){
                    TreeNode tmp=relacedNode;
                    while (tmp.left!=null){
                        tmp=tmp.left;
                    }
                    tmp.left=root.left;
                }
                root=relacedNode;
            }else if(deteledNode.equals(prevNode.left)){
                if(relacedNode!=deteledNode.right){
                    TreeNode tmp=relacedNode;
                    while (tmp.right!=null){
                        tmp=tmp.right;
                    }
                    tmp.right=deteledNode.right;
                }
                if(relacedNode!=deteledNode.left){
                    relacedNode.left=deteledNode.left;
                }
                prevNode.left=relacedNode;
            }else{
                if(relacedNode!=deteledNode.right){
                    TreeNode tmp=relacedNode;
                    while (tmp.right!=null){
                        tmp=tmp.right;
                    }
                    tmp.right=deteledNode.right;
                }
                if(relacedNode!=deteledNode.left){
                    relacedNode.left=deteledNode.left;
                }
                prevNode.right=relacedNode;
            }
        }
        return root;
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
//        Integer[] arr=new Integer[]{5,3,6,2,4,null,7};
//        Integer[] arr=new Integer[]{50,30,70,null,40,60,80};
//        Integer[] arr=new Integer[]{4,null,7,6,8,5,null,null,9};
//        Integer[] arr=new Integer[]{2,1};
//        Integer[] arr=new Integer[]{2,0,34,null,1,25,40,null,null,11,31,null,45};
//        Integer[] arr=new Integer[]{2,0,33,null,1,25,40,null,null,11,31,34,45,10,18,29,32,null,36,43,46};
//        Integer[] arr=new Integer[]{3,2,5,null,null,4,10,null,null,8,15,7};
        Integer[] arr=new Integer[]{10,5,null,null,7};
        //## Init binary tree

        TreeNode[] treeNodes=new TreeNode[arr.length];
        for(int i=0;i<treeNodes.length;i++) treeNodes[i]=new TreeNode(0);

        TreeNode root=null;
        int j=1;

        for(int i=0;i<arr.length;i++){
            if(arr[i]==null){
                continue;
            }
            TreeNode currentNode=treeNodes[i];
            currentNode.val=arr[i];
            if(i==0){
                root=currentNode;
            }

            TreeNode treeNodeNext1=null;
            if(j<arr.length&&arr[j]!=null){
                treeNodeNext1=treeNodes[j];
                treeNodeNext1.val=arr[j];
            }

            TreeNode treeNodeNext2=null;
            if(j+1<arr.length&&arr[j+1]!=null){
                treeNodeNext2=treeNodes[j+1];
                treeNodeNext2.val=arr[j+1];
            }
            j+=2;
            currentNode.left=treeNodeNext1;
            currentNode.right=treeNodeNext2;
        }
//        int key=3;
//        deleteNode(root, key);

//        int key=6;
//        deleteNode(root, key);
//        println(root);

//        int key=-1;
//        root=deleteNode(root, key);
//        println(root);

        //Case : Không tồn tại node tìm kiếm
//        int key=-1;
//        deleteNode(null, key);
//        println(root);

        //Case : xóa root nhưng chỉ có 1 node
//        int key=0;
//        root=new TreeNode(0);
//        root=deleteNode(root, key);
//        println(root);

//        Case : Xóa root nhưng có nhiều node
//        int key=root.val;
//        root=deleteNode(root, key);
//        println(root);

        //           4
        //              (7)
        //            6    8
        //          5        9
        //Case : Xóa 7 đi cần phải check root.right==replacedNode hay không?
//        int key=7;
//        root=deleteNode(root, key);
//        println(root);
//        int key=4;
//        root=deleteNode(root, key);
//        println(root);

        //50,30,70,null,40,60,80
        //
        //          (50)
        //      30       70
        //         40  60   80
        //Case : node xóa chính là root
//        int key=50;
//        root=deleteNode(root, key);
//        println(root);

//        int key=2;
//        root=deleteNode(root, key);
//        println(root);

        //                                                                            2
        //														   0                                   (33)
        //													          1                   25                         40
        //															                11         31            34             45
        //															           10    18   29    32        a      36      43    46
        //																	                              b     c
        //Case : liên quan node dùng để thay thế có tồn tại right
//        int key=33;
//        root=deleteNode(root, key);
//        println(root);

//        int key=5;
//        root=deleteNode(root, key);
//        println(root);

        //                   (10)
        //				5
        //		           7
        //case : Liên quan đến việc delete node == root
        //--> Nhưng relaced node có tồn tại right
        //===> Cần phải while(node.right!=null) : Để gán value
        int key=10;
        root=deleteNode(root, key);
        println(root);
        //
        //** Đề bài
        //- Bài này nói về việc xóa 1 node trong binary search tree
        //
        //** Tư duy như sau:
        //1,
        //1.1,
        //Các bước như sau:
        //- Ta cần tìm deleted node : Tức là node cần phải xóa đi
        //- Ta cần tìm node để thay thế cho deleted node : replacementNode
        //- Ta cần cách remove các mỗi quan hệ liên quan và gán lại --> Tương ứng với sự thay thế
        //
        //1.2, Tiêu chi là điểm thay thế
        //Tư duy như sau:
        //        b
        //     (a)          c
        //   d       e  f   d
        //       (m)     n
        //
        //VD: Ở đây ta cần thay thế (a) <-> (m)
        //- Ở đây replacementNode phải thỏa mãn --> right of a và phải nhỏ nhất trong các right nodes của (a)
        //--> Ta sẽ right 1 lần sau đó left ===> Đến khi (node.right==null)
        //+ Vì right --> Value sẽ tăng lên --> Hạn chế right ==> Chỉ right 1 lần + left (lấy số min nhất)
        //
        //1.3, Cách loại bỏ sự liên hệ
        //- replacementNode có thể có các node right (left đã ==null)
        //- Loại bỏ previous node của replacementNode
        //--> Cần phải check nó là left/ right ==> prevNode.left/right=null
        //- Sau đó cần phải lưu previousDeletedNode --> Tức là điểm trước deletedNode
        //+ child of right nhất của replacementNode=previousDeletedNode.right
        //+ child of left nhất của replacementNode=previousDeletedNode.left
        //
        //** Chú ý case:
        //          (50) (deleted node)
        //      30       70 (replacementNode)
        //         40       80
        //==> Cần phải check xem (deletedNode.right == replacementNode)
        //--> Thì mới gán vì nếu gán case (tmp.right=deletedNode.right) này sẽ bị lặp vô tận
        //VD: 70.right=50.right
        //70 --> right (70) ==> Cycle

    }
}
