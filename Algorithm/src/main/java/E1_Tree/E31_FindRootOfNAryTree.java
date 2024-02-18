package E1_Tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class E31_FindRootOfNAryTree {

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

    public static HashMap<Node, Integer> indegree;

    public static void solution(Node node){
        List<Node> child=node.children;

        if(child==null){
            return;
        }
        for(Node next: child){
            // System.out.printf("From %s to %s\n", node.val, next.val);
            indegree.put(next, indegree.getOrDefault(next, 0)+1);
        }
    }

    public static Node findRoot(List<Node> tree) {
        indegree=new HashMap<>();
        for(Node curNode: tree){
            solution(curNode);
        }
        Node root=null;
        for(Node node: tree){
            if(!indegree.containsKey(node)){
                root=node;
                break;
            }
        }
        // System.out.printf("%s\n", indegree);
        return root;
    }

    public static HashSet<Node> indegreeSet;

    public static void solutionOptimization(Node node){
        List<Node> child=node.children;

        if(child==null){
            return;
        }
        // System.out.printf("From %s to %s\n", node.val, next.val);
        indegreeSet.addAll(child);
    }

    public static Node findRootOptimization(List<Node> tree) {
        indegreeSet=new HashSet<>();
        for(Node curNode: tree){
            solutionOptimization(curNode);
        }
        Node root=null;
        for(Node node: tree){
            if(!indegreeSet.contains(node)){
                root=node;
                break;
            }
        }
        // System.out.printf("%s\n", indegree);
        return root;
    }

    public static int solutionOptimization1(Node node){
        List<Node> child=node.children;

        if(child==null){
            return 0;
        }
        int sum=0;
        for(Node next: child){
            sum+=next.val;
        }
        // System.out.printf("From %s to %s\n", node.val, next.val);
        return sum;
    }

    public static Node findRootOptimization1(List<Node> tree) {
        int sum=0, sumChildren=0;

        for(Node curNode: tree){
            sum+=curNode.val;
            sumChildren+=solutionOptimization1(curNode);
        }
        Node root=null;
        int rootVal=sum-sumChildren;

        for(Node node: tree){
            if(node.val==rootVal){
                root=node;
                break;
            }
        }
        // System.out.printf("%s\n", indegree);
        return root;
    }

    public static void main(String[] args) {
        // Requirement
        //- Given list of nodes of tree which will be passed in the random order
        //* Find the root of tree
        //
        // Idea
        //1.
        //1.0,
        //- Constraint:
        //- The total number of nodes is between [1, 5 * 10^4].
        //- Each node has a unique value.
        //+ Unique : Có thể dùng hashmap để xác định (indegree) và (outdegree)
        //
        //- Brainstorm
        //- Đỉnh mà không có node nào trỏ đến ==> Nó sẽ là root
        //- Ở đây mình traverse all nodes:
        //  + Chỉ cần traverse mỗi node 1 layer là được.
        //
        //1.1, Optimization
        //- Ở đây ta không cần dùng hashmap để lưu bậc của vertex
        //==> Ta dùng hashset add all các vertices mà có role= child của 1 node khác là được
        //
        //1.2, Complexity
        //- E is the number of edges
        //- V is the number of vertex
        //- Space : O(V)
        //- Time : O(V+E)
        //
        //- Tối ưu space --> O(1) bằng cách tận dụng value của mỗi node là unique?
        //2.
        //- (Sum của tất cả các children) + traverse = sum all - (root value)
        //
        //#Reference:
        //1516. Move Sub-Tree of N-Ary Tree
        //
    }
}
