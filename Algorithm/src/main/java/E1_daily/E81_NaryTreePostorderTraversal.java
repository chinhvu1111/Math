package E1_daily;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class E81_NaryTreePostorderTraversal {

    static class Node {
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
    }

    public static void dfs(List<Integer> rs, Node curNode){
        List<Node> children = curNode.children;
        if(children==null){
            rs.add(curNode.val);
            return;
        }
        for(Node node: children){
            dfs(rs, node);
        }
        rs.add(curNode.val);
    }

    public static List<Integer> postorderRecursion(Node root) {
        List<Integer> rs=new ArrayList<>();

        if(root==null){
            return rs;
        }
        dfs(rs, root);
        return rs;
    }

    public static List<Integer> postorderIterative(Node root) {
        //Space: O(n)
        List<Integer> rs=new ArrayList<>();

        if(root==null){
            return rs;
        }
        Stack<Node> stack=new Stack<>();
        stack.add(root);

        //Time: O(n)
        while (!stack.isEmpty()){
            Node curNode=stack.pop();
            rs.add(curNode.val);
            List<Node> children=curNode.children;

            stack.addAll(children);
        }
        Collections.reverse(rs);
//        System.out.println(rs);
        return rs;
    }

    public static List<Integer> postorderIterativeTwoStacks(Node root) {
        //Space: O(n)
        List<Integer> rs=new ArrayList<>();

        if(root==null){
            return rs;
        }
        Stack<Node> stack=new Stack<>();
        Stack<Node> reversalStack=new Stack<>();
        stack.add(root);

        //Time: O(n)
        while (!stack.isEmpty()){
            Node curNode=stack.pop();
            reversalStack.add(curNode);
            List<Node> children=curNode.children;
            stack.addAll(children);
        }
        while (!reversalStack.isEmpty()){
            rs.add(reversalStack.pop().val);
        }
//        System.out.println(rs);
        return rs;
    }

    // Helper class to pair a node with its visited status
    private static class NodeVisitPair {

        Node node;
        boolean isVisited;

        NodeVisitPair(Node node, boolean isVisited) {
            this.node = node;
            this.isVisited = isVisited;
        }
    }

    public static List<Integer> postorderWithoutReverse(Node root) {
        List<Integer> result = new ArrayList<>();
        // If the root is null, return the empty list
        if (root == null) return result;

        Stack<NodeVisitPair> nodeStack = new Stack<>();
        nodeStack.push(new NodeVisitPair(root, false));

        while (!nodeStack.isEmpty()) {
            NodeVisitPair currentPair = nodeStack.pop();

            if (currentPair.isVisited) {
                // If the node has been visited, add its value to the result
                result.add(currentPair.node.val);
            } else {
                // Mark the current node as visited and push it back to the stack
                currentPair.isVisited = true;
                nodeStack.push(currentPair);

                // Push all children to the stack in reverse order
                List<Node> children = currentPair.node.children;
                for (int i = children.size() - 1; i >= 0; i--) {
                    nodeStack.push(new NodeVisitPair(children.get(i), false));
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        //** Requirement
        //- Given (the root of an n-ary tree),
        //* Return (the postorder traversal) of (its nodes' values).
        //- Nary-Tree input serialization is represented in their level order traversal.
        //- Each group of children is separated by the null value (See examples)
        //- Đại loại là post order cho thằng tree này thôi
        //
        // Idea
        //1.
        //1.0,
        // Method-1:
        //- Constraint
        //The number of nodes in the tree is in the range [0, 10^4].
        //0 <= Node.val <= 10^4
        //The height of the n-ary tree is less than or equal to 1000.
        //
        //- Brainstorm
        //Cái này dùng stack là được
        //        1
        //     /   \    \
        //   3      2    4
        // /  \
        //5    6
        //- Stack: 1,3,5
        //- Stack: 1,3
        //  + 5
        //- Stack: 1
        //  + 5,3
        //- Stack: 1,6
        //  + Đi qua right của 3 ==> node = node.right
        //      + Vòng sau sẽ add left hết
        //  + 5,3
        //- Tree ở đây là danh sách children chứ không phải (left, right)
        //- Vì children có rất nhiều nodes:
        //  + Nếu muốn back lại được thì phần next --> để đi deeper cần phải được điền vào lỗi lần loop.
        //- Loop ntn?
        //Ex:
        //        1
        //     /   \    \
        //   3      2    4
        // /  \        /   \
        //5    6     7      8
        //+ 1,5,6,3,2,7,8,4
        //
        //- Traverse steps:
        //- Stack: 1
        //  + rs: []
        //- Stack: 4,2,3
        //  + rs: [1]
        //- Stack: 1,4,2,6,5
        //  + rs: [3]
        //- Stack: 1,4,2
        //  + rs: [3,5,6]
        //- Stack: 1,4
        //  + rs: [3,5,6,2]
        //- Stack: 1,8,7
        //  + rs: [3,5,6,2,4]
        //- Stack:
        //  + rs: [3,5,6,2,4,7,8]
        //* Kinh nghiệm:
        //- Preorder ==> Reverse(Post order)
        //
        //1.1, Optimization
        //- Thay vì dùng reverse list <=> dùng 2 stack để (lấy được reverse)
        //  + Ta dùng tính chất của stack (Reversal) để nhận kết quả của stack luôn
        //  - Idea thay vì reverse(rs):
        //      + Ta add vào value vào stack ==> Sau đó đến cuối pop() từ stack + add vào rs
        //      ==> return rs là được.
        //- Ta có thể add node without reverse:
        //  + Thay vì reverse ta sẽ add vào stack + reverse luôn lúc đó
        //  + Dùng visited để đánh giá node đã visited hay chưa.
        //
        //Ex:
        //        1
        //     /   \    \
        //   3      2    4
        // /  \        /   \
        //5    6     7      8
        //+ 1,5,6,3,2,7,8,4
        //- Khi traverse đến điểm node trong stack:
        //  + Mark node đó visited = true
        //      + Vẫn add vào stack
        //Ex:
        //         1
        //      /   \    \
        //    3      2    4
        // /    \
        //5      6
        //stack: 1(false)
        //stack: 1(true),3(false),2(false),4(false)
        //stack: 1(true),3(false),2(false),4(true)
        //  ==> Ta có thể thấy rõ là reverse (3,2,4) ==> (2,3,4) bằng cách sử dụng visited:
        //* Kinh nghiệm:
        //- Ta có thể reverse stack bằng cách switch visited[i] = false ==> true
        //  + add(node(false)) ==> pop(node) + mark = true
        //      + Sau đó pop ra hết ==> reverse
        //stack: 1(true),4(true),2(true),3(true),5(false),6(false)
        //stack: 1(true),4(true),2(true),3(true),5(false),6(true)
        //stack: 1(true),4(true),2(true),3(true),6(true),5(true)
        //  ==> Reverse sẵn rồi: 5,6,3,2,4,1
        //
        //** Main point:
        //  + Revers preorder các node tại mỗi (SUBTREE)
        //      ==> Ta sẽ thu được post order.
        //
        //1.2, Complexity
        //+ Let m be the number of nodes in the tree.
        //- Space: O(n)
        //- Time: O(n)
        //
        //#Reference:
        //1273. Delete Tree Nodes
        //1586. Binary Search Tree Iterator II
        //1916. Count Ways to Build Rooms in an Ant Colony
        //581. Shortest Unsorted Continuous Subarray
        //1104. Path In Zigzag Labelled Binary Tree
        //2265. Count Nodes Equal to Average of Subtree
    }
}
