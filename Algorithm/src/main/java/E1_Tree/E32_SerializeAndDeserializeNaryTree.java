package E1_Tree;

import java.util.*;

public class E32_SerializeAndDeserializeNaryTree {

    public static class Node {
        public int val;
        public List<Node> children;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "val=" + val +
                    '}';
        }
    };


    public static class Codec {
        // Encodes a tree to a single string.

        //Time : O(N)
        public void serialize(Node node, StringBuilder rs){
            if(node==null){
                rs.append("]");
                return;
            }
            rs.append(node.val);
            rs.append(",");
            List<Node> children=node.children;
            if(children!=null&&children.size()!=0){
                for(Node child: children){
                    serialize(child, rs);
                }
            }
            rs.append("]");
        }

        public String serialize(Node root) {
            if(root==null){
                return null;
            }
            //      1
            //    /  \   \
            //  3     2   4
            // /  \
            //5    6
            //[1[3[5,6],2,4]
            //1,3,5],6]],2]
            //1[2,3[6,7[11[14]],4[8[12]],5[9[13],10]
            //1,null,3,2,4,null,5,6,null,null,null,null
            StringBuilder rs=new StringBuilder();
            serialize(root, rs);
            System.out.println(rs.toString());
            return rs.toString();
        }

        public boolean isDigit(char c){
            return c>='0'&&c<='9';
        }

        // Decodes your encoded data to tree.
        public Node deserialize(String data) {
            if(data==null){
                return null;
            }
            int index=0;
            int n=data.length();
            Stack<Node> stack=new Stack<>();
            Node root=null;

            //Time : O(N)
            while(index<n){
                int curNum=0;
//                System.out.println(stack);

                while(index<n&&isDigit(data.charAt(index))){
                    curNum=curNum*10+data.charAt(index)-'0';
                    index++;
                }
                Node newNode=new Node(curNum);
                newNode.children=new ArrayList<>();
                if(root==null){
                    root=newNode;
                }
                if(stack.isEmpty()){
                    stack.add(newNode);
                }else{
                    stack.peek().children.add(newNode);
                    stack.add(newNode);
                }
                if(index<n&&data.charAt(index)==','){
                    index++;
                }
                while(index<n&&data.charAt(index)==']'){
                    stack.pop();
                    index++;
                }
            }
            //1,3,5],6]],2]
//            System.out.println(stack);
            return root;
        }
    }

    public static void solution(Node node){
        if(node==null){
            return;
        }
        System.out.println(node.val);
        List<Node> children=node.children;
        if(children!=null&&children.size()!=0){
            for(Node child: children){
                solution(child);
            }
        }
    }

    public static void main(String[] args) {
        // Requirement
        //- Serialization is the process of converting a data structure or object into a sequence of bits
        // so that it can be stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later
        // in the same or another computer environment.
        //
        //
        // Idea
        //1.
        //1.0,
        //- Constraint:
        //The number of nodes in the tree is in the range [0, 10^4].
        //0 <= Node.val <= 10^4
        //The height of the n-ary tree is less than or equal to 1000
        //Do not use class member/global/static variables to store states. Your encode and decode algorithms should be stateless.
        //
        //- Brainstorm
        //- Ở đây cần chú ý là:
        //+ An N-ary tree is a rooted tree in which each node has no more than N children.
        //--> Tức là mỗi node có thể có <=N children.
        //
        //- Có 3 ideas:
        //      1
        //    /  \   \
        //  3     2   4
        // /  \
        //5    6
        //Simple idea=[1[3[5,6],2,4]
        // + Ex:
        //  1[2,3[6,7[11[14]],4[8[12]],5[9[13],10]
        //* Idea khi kết thúc 1 child node thì điền vào ']':
        // Presentation cho ex bên trên: 1,3,5],6]],2]
        //- Nếu biểu diễn theo level traversal:
        //==> 1,null,3,2,4,null,5,6,null,null,null,null
        // + Khá là khó để biểu diễn vì:
        //  + N-Tree: Không thể biết số lượng child của mỗi node là bao nhiêu
        //  ==> Thực ra suy nghĩ thêm cũng được nhưng sẽ khó biểu diễn hơn cách trên.
        //
        //1.1, Optimization
        //1.2, Complexity
        //- N is the number of node
        //- K is the number of extra character
        //- N>>K
        //- Space : O(N+K) ~ O(N)
        //- Time : O(N+K) ~ O(N)
        //
        Node node=new Node(1);
        node.children=new ArrayList<>();
        Node node1=new Node(3);
        Node node2=new Node(2);
        Node node3=new Node(4);
        node.children.add(node1);
        node.children.add(node2);
        node.children.add(node3);
        node1.children=new ArrayList<>();
        Node node4=new Node(5);
        Node node5=new Node(6);
        node1.children.add(node4);
        node1.children.add(node5);
        Codec codec=new Codec();
        System.out.println(codec.serialize(node));
        String s=codec.serialize(node);
        Node root=codec.deserialize(s);
        solution(root);
        //#Reference:
        //297. Serialize and Deserialize Binary Tree
        //449. Serialize and Deserialize BST
        //431. Encode N-ary Tree to Binary Tree
    }
}
