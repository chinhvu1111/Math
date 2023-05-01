package E1_Tree;

import java.util.*;

public class E2_NaryTreeLevelOrderTraversal {

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

    public static List<List<Integer>> levelOrder(Node root) {
        return solution(root);
    }

    public static List<List<Integer>> solution(Node node){
        List<List<Integer>> rs=new ArrayList<>();
        if(node==null){
            return rs;
        }
        Stack<Node> listNodes=new Stack<>();
        listNodes.add(node);
        HashMap<Node, Integer> nodeMapLevel=new HashMap<>();
        nodeMapLevel.put(node, 1);
        HashMap<Integer, List<Integer>> levelMapNodes=new HashMap<>();
        levelMapNodes.put(1, Collections.singletonList(node.val));
        int maxLevel=1;

        while (!listNodes.isEmpty()){
            Node currentNode=listNodes.pop();
            Integer currentLevel=nodeMapLevel.get(currentNode);

            List<Integer> listNodeNextLevel=levelMapNodes.get(currentLevel+1);

            if(listNodeNextLevel==null){
                listNodeNextLevel=new ArrayList<>();
            }
            int n=currentNode.children.size();

            for(int i=n-1;i>=0;i--){
                Node currentChildNode=currentNode.children.get(i);
                Node lastNode=currentNode.children.get(n-i-1);
                listNodeNextLevel.add(lastNode.val);
                listNodes.add(currentChildNode);
                nodeMapLevel.put(currentChildNode, currentLevel+1);
            }
            if(!listNodeNextLevel.isEmpty()){
                levelMapNodes.put(currentLevel+1, listNodeNextLevel);
                maxLevel=Math.max(maxLevel, currentLevel+1);
            }
        }
        for(int i=1;i<=maxLevel;i++){
            rs.add(levelMapNodes.get(i));
        }
        return rs;
    }

    public static void main(String[] args) {
        //** Đề bài:
        //- return level traversal order : Tức là return từng nodes trên từng độ sâu tăng dần.
        //
        //** Tư duy:
        //1.
        //1.1, Idea
        //- Vì depth không vượt quá 1000 --> Dùng fixed array cũng được.
        //- Vì số điểm không vượt quá 10000 --> Ta có thể dùng array lưu depth <=> Để common hơn thì ta sẽ dùng hashmap để lưu.
        //- Ta cần lưu depth để có thể check depth của node hiện tại ( Sau khi pop)
        //- Khi pop 1 node ra --> Depth các children nodes chính bằng depth parent +1
        //- Tạo 1 hashmap nữa để lưu thông tin các nodes tại từng level depth.
        //
        //#Reference:
        //428. Serialize and Deserialize N-ary Tree
        //102. Binary Tree Level Order Traversal
        //590. N-ary Tree Postorder Traversal
        //2039. The Time When the Network Becomes Idle
    }
}
