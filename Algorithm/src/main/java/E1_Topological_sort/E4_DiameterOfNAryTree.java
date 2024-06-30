package E1_Topological_sort;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class E4_DiameterOfNAryTree {
    // Definition for a Node.
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

        public Node(int _val, ArrayList<Node> _children) {
            val = _val;
            children = _children;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "val=" + val +
                    '}';
        }
    }

    public static int result;

    public static int solution1(Node currentNode){
        List<Node> adjNodes=currentNode.children;

        if(adjNodes==null||adjNodes.size()==0){
            return 1;
        }
        int greatestFirst=0;
        int greatestSecond=0;
//        List<Integer> rs=new ArrayList<>();

        for(Node node: adjNodes){
            int currentHeight= solution1(node);
//            rs.add(currentHeight);
//            System.out.printf("%s,", node.val);
            if(greatestFirst<currentHeight){
                greatestSecond=greatestFirst;
                greatestFirst=currentHeight;
            }else if(greatestFirst>greatestSecond&&greatestSecond<=currentHeight){
                greatestSecond=currentHeight;
            }
        }
//        System.out.println();
//        System.out.println(adjNodes);
//        System.out.println(rs);
//        System.out.printf("%s first %s, second %s, max: %s\n", currentNode, greatestFirst, greatestSecond, greatestFirst+greatestSecond);
//        int currentRs=0;
//        if(greatestFirst==-1){
//
//        }
        result=Math.max(result, greatestFirst+greatestSecond);
        return greatestFirst+1;
    }

    public static int solution2(Node currentNode, int currentDepth){
        List<Node> adjNodes=currentNode.children;

        if(adjNodes==null||adjNodes.size()==0){
            return currentDepth;
        }
        int greatestFirst=0;
        int greatestSecond=0;
//        List<Integer> rs=new ArrayList<>();

        for(Node node: adjNodes){
            int nextDepth= solution2(node, currentDepth+1);
//            rs.add(currentHeight);
//            System.out.printf("%s,", node.val);
            if(greatestFirst<nextDepth){
                greatestSecond=greatestFirst;
                greatestFirst=nextDepth;
            }else if(greatestFirst>greatestSecond&&greatestSecond<=nextDepth){
                greatestSecond=nextDepth;
            }
        }
//        System.out.println();
//        System.out.println(adjNodes);
//        System.out.println(rs);
        System.out.printf("%s first %s, second %s, current depth %s, max: %s\n", currentNode, greatestFirst, greatestSecond,
                currentDepth, greatestFirst+greatestSecond-2*currentDepth);
//        int currentRs=0;
//        if(greatestFirst==-1){
//
//        }
        result=Math.max(result, Math.max(greatestFirst-1, greatestFirst+greatestSecond-2*currentDepth));
        return greatestFirst;
    }

    public static int diameter(Node root) {
        if(root==null){
            return 0;
        }
        if(root.children==null||root.children.size()==0){
            return 0;
        }
        result=1;
        solution1(root);
        return result;
    }
    public static int diameter1(Node root) {
        if(root==null){
            return 0;
        }
        if(root.children==null||root.children.size()==0){
            return 0;
        }
        result=1;
        solution2(root, 1);
        return result;
    }

    public static void println(Node root){
        System.out.printf("%s %s\n", root.val, root.children);
        for(Node node: root.children){
            println(node);
        }
    }

    public static void main(String[] args) {
        //** Requirement
        //- Cho 1 root của 1 tree
        //* Return longest path giữa bất kỳ  2 nodes trong tree.
        //
        //** Idea
        //* Method-1:
        //1.
        //1.0,
        //- Constraint
        //The depth of the n-ary tree is less than or equal to 1000.
        //The total number of nodes is between [1, 10^4].
        //--> Depth <= 1000 ==> Vẫn ok
        //
        //- Brainstorm
        //- Tính min height của 1 pair nodes + các edges không cần đi qua root node
        //+ Ta sẽ chia các nodes trung gian thành các root node trung gian
        //VD:
        //                1
        //             /
        //           2
        //         /   \
        //       3      4
        //     /         \
        //    5           6
        //+ Longest path : 5 - 3 - 2 - 4 - 6 = 4 edges
        //- Ta thấy ở 1 kết nối với được với 5 và 6 ==> Với trường hợp này đối với 1 ta sẽ chỉ lấy node xa nhất (6/5)
        //  + Vì giữa 5 và 6 ta sẽ có subnodes là 1 trong những child nodes dưới của root nó path sẽ đi qua.
        //
        //- Bài toán thành
        //+ Tính chiều dài max nhất của subtree ==> return của từng method recursively
        //+ Tính pair path lớn nhất của từng subtree ==> Update result liên tục.
        //--> Ở đây ta sẽ dùng DFS
        //
        //** Công thức:
        //- Cái này dùng để tính max heigh của tree hiện tại (Unit là số lượng node):
        //+ min height = min (all subtree) + 1
        //+ Result ta sẽ tính trong nội bộ method:
        //+ rs=Max(rs, left + right)
        // ==> Tổng số node của left sub tree và right sub tree (Không tính node hiện tại) ==> Chính là số lượng edges.
        //
        //- Special testcases:
        //- Bên dưới là ta tính sai value cho greater second value
        //+ Khi gán lại firstGreaterValue --> Ta cần gán secondGreaterValue cho firstGreaterValue nữa.
        //+ Predicate còn lại thì ta chọn bình thường.
        //
        //- Kỹ năng code tạo tree:
        //- Với dạng build tree mà có các node phân cách nhau bởi null ==> Ta có thể dùng queue để xử lý
        //+ Poll từng parent trước đó thành currenNode -> add node to children of current node.
        //
        //1.1, Optimization
        //+ greatestFirst>greatestSecond&&greatestSecond<=currentHeight
        //==> greatestSecond<=currentHeight (Vì cái thằng 1 không dùng (Tức là đã < greaterfirstValue rồi) thì để thằng 2 dùng)
        //
        //1.2, Complexity
        //+ E is number of edges
        //+ N is number of nodes
        //+ H is max height of the tree
        //- Time complexity : O(N) ==> Traverse all nodes
        //- Space complexity : O(H) ==> O(N) in worst case.
        //
        //* Method-2:
        //2.
        //2.0, Idea
        //* Cách 1 thì ta tính dựa trên khoảng cách từ leaf nodes --> root nodes
        //- Cách này thì ta sẽ tính dựa trên khoảng cách từ root node --> node hiện tại
        //
        //- Recursion:
        //+ return depth
        //+ result=Math.max(result, Math.max(greatestFirst-1, greatestFirst+greatestSecond-2*currentDepth));
        //  + sum (first_dep1 + first_dep2) - 2 * current depth
        //  + So sánh max(dep1, dep2) [first_dep1] với result nữa ==> Vì có thể nó chỉ có 1 child node (Tính sum sẽ bị sai)
        //
        //VD:
        //                1 (1)
        //             /
        //           2 (2)
        //         /   \
        //       3      4
        //     /         \
        //    5           6
//        Integer[] nodes = {1,null,2,null,3,4,null,5,null,6};
//        Integer[] nodes = {35,null,52,95,null,null,38,22,null,70,72,null,54,2,null,null,null,42,null,98,null,73,63};
        Integer[] nodes = {3,null,1,null,5};
        //1 --> 2
        //2 --> 3,4
        //3 --> 5
        //                          35
        //                      /       \
        //                   52         95
        //                           /      \
        //                         38       22
        //                      /    \     /   \
        //                     70    72   54    2
        //                               /       \
        //                             42        98
        //                            /  \
        //                          73   63
        //
        //              3
        //              |
        //              1 (2)
        //              |
        //              5
        Queue<Node> queue=new LinkedList<>();
        Node node=new Node(nodes[0]);
        Node root=node;
        int n=nodes.length;

        for(int i=2;i<n;i++){
            if(nodes[i]==null){
                node=queue.poll();
            }else{
                Node newNode=new Node(nodes[i]);
                queue.add(newNode);
                node.children.add(newNode);
            }
        }
        System.out.println(diameter(root));
        System.out.println(diameter1(root));
//        println(root);
        //#Reference:
        //1038. Binary Search Tree to Greater Sum Tree
        //1902. Depth of BST Given Insertion Order
        //2445. Number of Nodes With Value One
    }
}
