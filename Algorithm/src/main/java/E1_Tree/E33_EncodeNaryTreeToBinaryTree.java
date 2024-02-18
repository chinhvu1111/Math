package E1_Tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class E33_EncodeNaryTreeToBinaryTree {

    public static class Node {
        public int val;
        public List<Node> children;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }

    ;

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public static class Codec {
        // Encodes an n-ary tree to a binary tree.
        public TreeNode buildBinaryTree(Node root){
            if(root==null){
                return null;
            }
            TreeNode curRoot=new TreeNode(root.val);
            if(root.children!=null){
                TreeNode curNode=null;
                for(Node child: root.children){
                    TreeNode newNode=buildBinaryTree(child);
                    if(curNode==null){
                        curRoot.left=newNode;
                    }else{
                        curNode.right=newNode;
                    }
                    curNode=newNode;
                }
            }
            return curRoot;
        }
        public TreeNode encode(Node root) {
            return buildBinaryTree(root);
        }

        public Node buildNTree(TreeNode root){
            if(root==null){
                return null;
            }
            Node newRoot=new Node(root.val);
            newRoot.children=new ArrayList<>();
            if(root.left!=null){
                TreeNode node=root.left;

                while(node!=null){
                    Node newNode=buildNTree(node);
                    newRoot.children.add(newNode);
                    node=node.right;
                }
            }
            return newRoot;
        }
        //  1
        //   \
        //    2   ->   3 -> 5 -> 6
        //     \         \
        //      3 -> 4    9
        // Decodes your binary tree to an n-ary tree.
        public Node decode(TreeNode root) {
            return buildNTree(root);
        }
    }
    public static void printlnNTree(Node node){
        System.out.println(node.val);
        if(node.children!=null){
            for(Node sub:node.children){
                printlnNTree(sub);
            }
        }
    }

    public static void printlnBiTree(TreeNode node){
        System.out.println(node.val);
        if(node.left!=null){
            printlnBiTree(node.left);
        }
        if(node.right!=null){
            printlnBiTree(node.right);
        }
    }

    public static void main(String[] args) {
        // Requirement
        //- Design an algorithm to encode (an N-ary tree) into (a binary tree) and decode (the binary tree) to get
        // (the original N-ary tree).
        // An N-ary tree is a rooted tree in which each node has (no more than N children).
        // Similarly, a binary tree is a rooted tree in which each node has no more than 2 children.
        //
        // Idea
        //1.
        //1.0,
        //- Constraint:
        //The number of nodes in the tree is in the range [0, 10^4].
        //0 <= Node.val <= 10^4
        //The height of the n-ary tree is less than or equal to 1000
        //Do not use class member/global/static variables to store states.
        // Your encode and decode algorithms should be stateless.
        //==> Số lượng node cũng khá lớn ==> Time có thể O(n), O(n*k)
        //
        //- Brainstorm
        //Ex:
        //root = [1,null,3,2,4,null,5,6]
        //          1
        //        /    \     \
        //      3       2     4
        //    /   \    /   \
        //  5      6  7     8
        //==> Mấu chốt ở đây là tìm cách recover từ binary tree -> N-ary Tree
        //          1
        //       /    \
        //     3       2
        //- 4 có thể nối vào đâu để:
        //  + Để sau có thể nối vào 1
        //- Làm thế nào để ta có thể bỏ qua N (là số lượng node ít nhất của mỗi node)
        //- Có thể đặt ra quy luật cho binary tree để:
        //+ Có thể revert được không
        //Ex:
        //          1
        //        /  \    \    \
        //      2     3    5    6
        //->
        //       1
        //        \
        //         2
        //          \
        //           3
        //            \
        //             5
        //              \
        //               6
        //==> Ta có thể revert được
        //- Đổi một chút thông tin thành:
        //Ex:
        //          1
        //        /  \    \    \
        //      2     3    5    6
        //    /  \  \
        //  3     4  9
        //==>
        //      1
        //        \
        //         2
        //      /    \
        //     3      3
        //      \      \
        //       4      5
        //        \      \
        //         9      6
        //
        //- Simple idea hint:
        //+ Level traveral + link các child nodes thành linked list
        //+ Sau đó lấy head của list --> nối với parent của các nodes trong list.
        //Ex:
        //          1
        //        /  \    \    \
        //      2     3    5    6
        //    /  \  \
        //  3     4  9
        //->
        //  1
        //   \
        //    2   ->   3 -> 5 -> 6
        //     \         \
        //      3 -> 4    9
        //
        Node node=new Node(1);
        Node node1=new Node(3);
        Node node2=new Node(2);
        Node node3=new Node(4);
        node.children=new ArrayList<>();
        node.children.add(node1);
        node.children.add(node2);
        node.children.add(node3);
        Node node4=new Node(5);
        Node node5=new Node(6);
        node1.children=new ArrayList<>();
        node1.children.add(node4);
        node1.children.add(node5);

        Codec codec=new Codec();
        TreeNode newRoot = codec.encode(node);
        Node oldRoot = codec.decode(newRoot);
        printlnNTree(oldRoot);
        //#Reference:
        //1261. Find Elements in a Contaminated Binary Tree
        //1026. Maximum Difference Between Node and Ancestor
        //606. Construct String from Binary Tree
    }
}
