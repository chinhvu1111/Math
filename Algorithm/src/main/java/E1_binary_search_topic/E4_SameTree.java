package E1_binary_search_topic;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class E4_SameTree {

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

    public static void inorder(TreeNode node, List<Integer> listValues){
        if(node==null){
            return;
        }
        if(node.left!=null){
            listValues.add(node.left.val);
        }else{
            listValues.add(null);
        }
        if(node.right!=null){
            listValues.add(node.right.val);
        }else{
            listValues.add(null);
        }
        inorder(node.left, listValues);
        inorder(node.right, listValues);
    }
    public static boolean isSameTree(TreeNode p, TreeNode q) {
        List<Integer> firstVals=new ArrayList<>();
        List<Integer> secondVals=new ArrayList<>();
        inorder(p, firstVals);
        inorder(q, secondVals);
        if(p!=null){
            firstVals.add(p.val);
        }
        if(q!=null){
            secondVals.add(q.val);
        }
        System.out.println(firstVals);
        System.out.println(secondVals);
        if(firstVals.size()!=secondVals.size()){
            return false;
        }
        for(int i=0;i<firstVals.size();i++){
            if(!Objects.equals(firstVals.get(i), secondVals.get(i))){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        //** Đề bài:
        //- Kiểm tra 2 tree xem chúng có giống nhau hay không
        //- Giống khi:
        //+ Value các node giống nhau
        //+ structure của tree giống nhau
        //
        //** Tư duy
        //1.
        //1.1, Idea
        //- Ta sẽ build array dựa trên structure của tree --> Vì nếu recursive thì sẽ khó vì là 1 tree khác nhau.
        //+ Ta không thể build array tăng dần theo inorder traverse vì như thế thì sẽ bị thiếu cases.
        //- Case đặc biệt:
        //VD:
        //          1
        //       1
        //arr={1, 1, null,null,null}
        //VD:
        //          1
        //             1
        //arr={1, null, 1,null,null}
        //
        //#Reference
        //101. Symmetric Tree
        //1382. Balance a Binary Search Tree
        //1080. Insufficient Nodes in Root to Leaf Paths
        //1325. Delete Leaves With a Given Value
    }
}
