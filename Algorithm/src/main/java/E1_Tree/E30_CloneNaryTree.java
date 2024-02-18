package E1_Tree;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class E30_CloneNaryTree {

    public static class Node {
        public int val;
        public List<Node> children;


        public Node() {
            children = new ArrayList<Node>();
        }

        public Node(int _val) {
            val = _val;
            children = new ArrayList<Node>();
        }

        public Node(int _val,ArrayList<Node> _children) {
            val = _val;
            children = _children;
        }
    };

    public static Node solution(Node node, Node curNode){
        if(node==null){
            return null;
        }
        List<Node> child=node.children;
        if(curNode.children==null){
            curNode.children=new ArrayList<>(node.children.size());
        }

        for(Node next: child){
            Node cloneNode=new Node(next.val);
            Node next1=solution(next, cloneNode);
            curNode.children.add(next1);
        }
        return curNode;
    }

    public static Node solutionRefactor(Node node){
        if(node==null){
            return null;
        }
        List<Node> child=node.children;
        Node curNode=new Node(node.val);
        curNode.children=new ArrayList<>(node.children.size());

        for(Node next: child){
            Node next1=solutionRefactor(next);
            curNode.children.add(next1);
        }
        return curNode;
    }

    public static Node cloneTree(Node root) {
        if(root==null){
            return null;
        }
        return solutionRefactor(root);
    }

    public static Node cloneTreeIterative(Node root) {
        if(root==null){
            return null;
        }
        Deque<Node[]> stack=new LinkedList<>();
        Node newRoot=new Node(root.val);
        stack.add(new Node[]{root, newRoot});

        while(!stack.isEmpty()){
            //~ removeFirst()
            Node[] curNode=stack.pop();
            Node oldNode=curNode[0];
            Node newNode=curNode[1];

            for(Node next: oldNode.children){
                Node newChildNode=new Node(next.val);
                newNode.children.add(newChildNode);
                //~ addFirst
                stack.push(new Node[]{next, newChildNode});
            }
        }
        return newRoot;
    }

    public static void main(String[] args) {
        // Requirement
        //- Given a root of N-ary Tree:
        //- Definition:
        //+ Nary-Tree input serialization is represented in their level order traversal,
        // each group of children is separated by the null value (See examples).
        //Ex: root = [1,null,3,2,4,null,5,6] ==> Cái này chỉ là 1 presentation bằng array thôi ==> Input vẫn là root node
        //* Clone the input tree
        //
        // Idea
        //1.
        //1.0,
        //- Constraint:
        //The depth of the n-ary tree is less than or equal to 1000.
        //height <= 1000 : Không quá lớn
        //The total number of nodes is between [0, 10^4].
        //==> Số node này thì có thể xử lý O(N)
        //
        //- Brainstorm
        //Ex:
        //Input: root = [1,null,3,2,4,null,5,6]
        //                    1 (root)
        //               /   \    \
        //             3      2    4
        //           /  \
        //         5     6
        //- Nếu node phân kiểu mỗi node ứng với list of nodes khác thì:
        //+ Ta cần phải deep clone từng node + các con của nó một.
        //Ex:
        //- Từ 1 -> 3 -> 5 - 6 -> add (5,6) vào 3
        //=> return 3 để được add vào list của 1
        //-> Ta sẽ dùng DFS để add ==> return lại current node khi add xong.
        //
        //1.1, Optimization
        //- Phần này không cần pass cả 2 parent vào đầu method
        //==> Pass 1 + add all children là được r.
        //
        //1.2, Complexity
        //- M is the number of node in the tree
        //- Space: O(M)
        //  + We will traverse each node in the tree only once
        //- Time : O(H) -> O(M)
        //  + Theo tính toán thông thường thì space cho result sẽ không tính vào space chung
        //  + Recursion space : O(H) --> Worst case = O(M)
        //
        //2. DFS with stack (Iterative)
        //2.0, Idea
        //- Với cách này ta có thể reduce được space.
        //
        //Ex:
        //Input: root = [1,null,3,2,4,null,5,6]
        //                    1 (root)
        //               /   \    \
        //             3      2    4
        //           /  \        /   \
        //         5     6      10    8
        //                          /    \
        //                         11     12
        //- Cần tầng trên thực hiện sau
        //- Cùng level --> Add trước ==> Thực hiện trước
        //stack: first-1-last
        //stack : first-4,2,3,1-last
        //stack : first-8,10,2,3,1-last
        //stack : first-11,12,8,10,2,3,1-last
        //stack : first-11,8,10,2,3,1-last
        //stack : first-8,10,2,3,1-last
        //stack : first-10,2,3,1-last
        //stack : first-2,3,1-last
        //stack : first-3,1-last
        //stack : first-6,5,3,1-last
        //
        //- Bài này là standard stack cho kiểu duyệt từng level
        //- Clone graph nên không cần DFS left --> right ==> Vẫn có thể RIGHT => LEFT
        //- Node nào add sau ==> Xử lý trươc
        //
        //1.1, Optimization
        //1.2, Complexity
        //- Space : O(H) ==> Chiều cao của Tree
        //- Time : O(M)
        //
        //#Reference:
        //133. Clone Graph
        //1485. Clone Binary Tree With Random Pointer
    }
}
