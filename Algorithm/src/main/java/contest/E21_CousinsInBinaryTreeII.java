package contest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class E21_CousinsInBinaryTreeII {

    //** Đề bài:
    //- Replace value của mỗi node bằng giá trị sum của tất cả các cousins của nó
    //- Cousin là các nodes không cùng parent nodes hiện tại + cùng depth.
    //
    //** Bài này tư duy như sau:
    //1.
    //1.0, Idea
    //- Dữ kiện:
    //+ 10^4 nodes
    //- Để tính được các điểm cùng depth --> Ta phải traverse tree --> tính depth cho từng nodes.
    //- Sau đó tìm traverse all depth --> Để update value của các nodes cùng depth.
    //- Bài toán quy thành:
    //(node1, v1, parent1), (node2, v2, parent1), (node3, v3, parent3), (node4, v4, parent4)
    //--> Update
    //(node1, v3+v4, parent1), (node2, v3+v4, parent1), (node3, v2+v3, parent3), (node4, v2+v3, parent4)
    //+ Value = sum_all - sum_current( sum ứng với depth + parent hiện tại)
    //1.1, Cách làm như sau
    //- Cái này sẽ cần traverse all (depth)
    //- Tính sum all value của 1 depth
    //- HashMap --> Ứng với parent hiện tại --> Sum vào
    //==> Tính value của group hiện tại = sum_all - sum_current.
    //- Update lại value của all nodes trong group
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

    public static HashMap<Integer, List<TreeNode>> depthMapNodes;
    public static HashMap<TreeNode, TreeNode> parentsNodes;

    public static TreeNode replaceValueInTree(TreeNode root) {
        depthMapNodes=new HashMap<>();
        parentsNodes=new HashMap<>();
        traverse(root, null, 1);

        for(Map.Entry<Integer, List<TreeNode>> e:depthMapNodes.entrySet()){
            long sum=0;
            HashMap<TreeNode, Integer> parentsSum=new HashMap<>();
            HashMap<TreeNode, List<TreeNode>> parentsTmpNodes=new HashMap<>();

            for(TreeNode node:e.getValue()){
                sum+= node.val;
                TreeNode currentParent = parentsNodes.get(node);
                List<TreeNode> childrenTmp=parentsTmpNodes.get(currentParent);

                if(childrenTmp==null){
                    childrenTmp=new ArrayList<>();
                }
                childrenTmp.add(node);
                parentsTmpNodes.put(currentParent, childrenTmp);

                parentsSum.put(currentParent, parentsSum.getOrDefault(currentParent, 0)+node.val);
            }
            for(Map.Entry<TreeNode, List<TreeNode>> nodes: parentsTmpNodes.entrySet()){
                for(TreeNode tmpNode: nodes.getValue()){
                    tmpNode.val= (int) (sum-parentsSum.get(nodes.getKey()));
                }
            }
        }
        return root;
    }

    public static void traverse(TreeNode node, TreeNode parent, Integer depth){
        if(parent!=null){
            parentsNodes.put(node, parent);
        }
        List<TreeNode> currentList=depthMapNodes.get(depth);
        if(currentList==null){
            currentList=new ArrayList<>();
        }
        currentList.add(node);
        depthMapNodes.put(depth, currentList);

        if(node.left!=null){
            traverse(node.left, node, depth+1);
        }
        if(node.right!=null){
            traverse(node.right, node, depth+1);
        }
    }

    public static void println(TreeNode node){
        System.out.println(node.val);
        if(node.left!=null){
            println(node.left);
        }
        if(node.right!=null){
            println(node.right);
        }
    }

    public static void main(String[] args) {
        TreeNode root=new TreeNode(5);
        TreeNode node1=new TreeNode(4);
        TreeNode node2=new TreeNode(9);
        root.left=node1;
        root.right=node2;

        TreeNode node3=new TreeNode(1);
        TreeNode node4=new TreeNode(10);
        node1.left=node3;
        node1.right=node4;

        TreeNode node5=new TreeNode(7);
        node2.right=node5;
        TreeNode root1=replaceValueInTree(root);
        println(root1);
    }
}
