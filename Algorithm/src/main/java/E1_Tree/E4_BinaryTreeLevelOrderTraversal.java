package E1_Tree;

import java.util.*;

public class E4_BinaryTreeLevelOrderTraversal {

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

    public static List<List<Integer>> levelOrderIterative(TreeNode root) {
        List<List<Integer>> rs=new ArrayList<>();
        if(root==null){
            return rs;
        }

        Queue<TreeNode> nodes=new LinkedList<>();
        nodes.add(root);
        rs.add(Collections.singletonList(root.val));

        while (!nodes.isEmpty()){
            List<TreeNode> nextNodes=new ArrayList<>();
            List<Integer> nodesAtCurrentLevel=new ArrayList<>();

            while (!nodes.isEmpty()){
                TreeNode currentNode=nodes.poll();

                if(currentNode.left!=null){
                    nextNodes.add(currentNode.left);
                    nodesAtCurrentLevel.add(currentNode.left.val);
                }
                if(currentNode.right!=null){
                    nextNodes.add(currentNode.right);
                    nodesAtCurrentLevel.add(currentNode.right.val);
                }
            }
            if(!nodesAtCurrentLevel.isEmpty()){
                rs.add(nodesAtCurrentLevel);
            }
            nodes.addAll(nextNodes);
        }
        return rs;
    }

    public static List<List<Integer>> rs=null;

    public static List<List<Integer>> levelOrderRecusion(TreeNode root) {
        rs=new ArrayList<>();
        if(root==null){
            return rs;
        }
        solution(root, 0);
        return rs;
    }

    public static void solution(TreeNode node, int level){
        if(node==null){
            return;
        }
        List<Integer> listNode=null;

        //2 --> index : 1
        if(rs.size()<=level){
            listNode=new ArrayList<>();
        }else{
            listNode=rs.get(level);
        }
        // System.out.println(listNode);
        // System.out.println(rs);
        listNode.add(node.val);
        if(rs.size()<=level){
            rs.add(listNode);
        }else{
            rs.set(level, listNode);
        }
        solution(node.left, level+1);
        solution(node.right, level+1);
    }

    public static void solutionRefactor(TreeNode node, int level){
        if(node==null){
            return;
        }
        if(rs.size()<=level){
            rs.add(new ArrayList<>());
        }
        List<Integer> listNode=rs.get(level);
        listNode.add(node.val);
        solutionRefactor(node.left, level+1);
        solutionRefactor(node.right, level+1);
    }

    public List<List<Integer>> levelOrderOtherIterativeWays(TreeNode root) {
        List<List<Integer>> levels = new ArrayList<List<Integer>>();
        if (root == null) return levels;

        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);
        int level = 0;
        while ( !queue.isEmpty() ) {
            // start the current level
            levels.add(new ArrayList<Integer>());

            // number of elements in the current level
            int level_length = queue.size();
            for(int i = 0; i < level_length; ++i) {
                TreeNode node = queue.remove();

                // fulfill the current level
                levels.get(level).add(node.val);

                // add child nodes of the current level
                // in the queue for the next level
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }
            // go to next level
            level++;
        }
        return levels;
    }

    public static void main(String[] args) {
        //** Đề bài
        //- Trả lại danh sách các nodes từng level
        //
        //** Tư duy
        //1.
        //1.0, Idea
        //VD:
        //            3
        //          /   \
        //        9      20
        //             /    \
        //           15      7
        //- Ta sẽ add nodes dựa trên queue
        //Steps:
        //+ 3
        //+ 9,20 --> level 1
        //+ Pop all ra để tính level 2
        //+ 15,7
        //+ Pop all để tính level 3
        //
        //- Nếu dùng recursion thì ta có thể lưu level variable để có thể add các nodes vào level tương ứng.
        //+ Với mỗi level ta sẽ lấy ra các nodes ở level cũ --> Dựa trên đó traverse đến các nodes ở next level
        //+ Cái tư duy trên là tư duy iterative + có init rs (root)
        //<=> Thay vì suy nghĩ như bên trên ta sẽ tư duy dạng:
        //+ (node, level) + add(node, level)
        // --> (node.left, level+1) + add(node.left, level+1) / (node.right, level+1) + add(node.right, level+1)
        //+ ==> Tư duy này là tư duy dạng có traverse all tree
        //+ Cần level bắt đầu từ 0 --> Vì ta cần các nodes mỗi level thế nên init =0/1 đều như nhau
        //
        //1.1, Kinh nghiệm:
        //Với những bài cần lấy các nodes của từng level ta có 2 cách:
        //- Dùng iterative (Dùng queue pop lần lượt current level + add lần lượt new level)
        //- Dùng recursion (Lưu level variable)
        //
        //1.2, Complexity of java collection
        //- Array list:
        //+ set(index, element) : O(1)
        //
        //1.3, Refactor
        //- Với code dạng :
        //- List<List<Integer>> list;
        //+ list.get(i) : nếu size của list không đủ thì ta sẽ check:
        //+ (size<=i) : Tức size của Array List không đủ để lấy index
        //- Nếu ta làm dạng :
        //+ Tạo local list =new Array (size<=i)
        //<> list.add(node.val)
        //--> Bên dưới ta lại phải if lần thứ 2
        //+ (size<=i) : rs.add
        //<> : rs.set(i, listNode)
        //** Ta làm ntn vì ta chưa hiểu tính chất của Java
        //- Nếu 2 list nào đó được lưu vào List<List<Integer> list
        //+ Muốn thay đổi thì ta không cần set --> Chỉ cần thay đổi list=rs.get(i) là được (add, remove...)
        //- Ở đây để đồng nhất ta xét như sau:
        //+ (size<=i) rs.add(new ArrayList());
        //+ Sau đó ta sẽ lấy listNodes=rs.get(i) ra ==> Thao tác trên đó <> thay vì cố gắng tạo local list
        //===> Không cần if 2 lần (Vì đã add sẵn vào rs rồi)
        //
        //1.4, Complexity:
        //- Time complexity : O(n), n là số nodes
        //- Space complexity : O(h), h là chiều cao của tree.
        //
        //1.5, Cách iterative khác:
        //- Ta có thể dùng level tương tự recursion để apply iterative way.
        //
        //** KINH NGHIỆM:
        //- Khi chơi với List<List<Integer>> : Add luôn new ArrayList() luôn nếu (size <= index query cần lấy)
        //==> Sau đó lấy sau <> nếu dùng local list (Sẽ phải if 2 lần)
        //
        //#Reference:
        //103. Binary Tree Zigzag Level Order Traversal
        //103. Binary Tree Zigzag Level Order Traversal
        //107. Binary Tree Level Order Traversal II
        //111. Minimum Depth of Binary Tree
    }
}
