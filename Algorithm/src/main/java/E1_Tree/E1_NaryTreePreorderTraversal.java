package E1_Tree;

import java.util.*;

public class E1_NaryTreePreorderTraversal {
    class Node {
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

    public static List<Integer> preorder(Node root) {
        List<Integer> result=new ArrayList<>();
        solutionIterative(root, result);
        return result;
    }

    public static void solutionIterative(Node node, List<Integer> rs){
        if(node==null){
            return;
        }
        Stack<Node> nextNodes=new Stack<>();
        nextNodes.add(node);

        while (!nextNodes.isEmpty()){
            Node currentNode=nextNodes.pop();
            rs.add(currentNode.val);
//            System.out.println(currentNode.val);
            for(int i=currentNode.children.size()-1;i>=0;i--){
                if(currentNode.children.get(i)!=null){
                    nextNodes.add(currentNode.children.get(i));
                }
            }
        }
    }

    public static void solution(Node node, List<Integer> rs){
        if(node==null){
            return;
        }
        rs.add(node.val);
        for(Node child: node.children){
            solution(child, rs);
        }
    }

    public static void main(String[] args) {
        //** Đề bài:
        //- Dựng preorder traversal cho 1 tree
        //- Input dược list theo từng level ==> Ngăn cách nhau bằng null
        //VD:
        //root = [1,null,3,2,4,null,5,6, null,7, 8]
        //+ parent=1 :
        //      + children : 3,2,4
        //+ parent=3:
        //      + children : 5,6 (Vì ngăn cách nhau bằng null)
        //+ parent=2:
        //      + children : 7,8 (Vì ngăn cách nhau bằng null + kết thúc)
        //
        //** Tư duy:
        //1.
        //1.1, Idea
        //- Dựng tree dựa trên input trước:
        //+ Tạo 2 index
        //  + index root
        //  + index start của children
        //  + while đến khi start==n-1 là được + trong mỗi loop thì children sẽ là all các nodes cho đến khi nextNode=null
        //- Sau đó tạo list --> recursive tree để tìm preorder traversal.
        //
        //- Nếu dùng loop thì:
        //VD:
        //                  1
        //            /     \       \
        //       3          2        4
        //     /    \
        //  5       6
        //Ta sẽ dùng queue add như sau:
        //3,2,4
        //+ 3 : 5,6 --> pop(3) + add(5,6)
        //+ 5,6,2,4 ==> Làm tiếp tục ===> CÁI NÀY ĐANG ADD VÀO FIRST --> Remove ra dùng ==> Giống stack
        //--> Chuyển qua stack
        //==> Ta sẽ add từ cuối trở đi
        //
        //4,2,3
        //+ 3 : 5,6 ==> add(5,6) vào cuối
        //+ 4,2,5,6
        //
        //1.2, Complexity:
        //- Time complexity: O (n) : n là số nodes.
        //- space complexity : O(n) : n là số nodes.
        //
        //#Reference:
        //590. N-ary Tree Postorder Traversal
        //429. N-ary Tree Level Order Traversal
        //590. N-ary Tree Postorder Traversal
    }
}
