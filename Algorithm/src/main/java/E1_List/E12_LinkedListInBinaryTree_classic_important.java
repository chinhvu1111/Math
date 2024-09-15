package E1_List;

import javafx.util.Pair;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class E12_LinkedListInBinaryTree_classic_important {

    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

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

    public static boolean isSubPath(ListNode head, TreeNode root) {
        if(head==null&&root==null){
            return true;
        }
        return dfs(root, head);
    }

    public static boolean check(TreeNode node, ListNode curNode){
        if(curNode==null){
            return true;
        }
        if(node==null){
            return false;
        }
        if(node.val!=curNode.val){
            return false;
        }
        //           1
        //        /    \
        //      2       5
        //    /  \
        //   3    4
        // /       \
        //null      null
        return check(node.left, curNode.next) || check(node.right, curNode.next);
    }
    public static boolean dfs(TreeNode node, ListNode head){
        if(node==null){
            return false;
        }
        boolean curRs=false;
        if(node.val==head.val){
            curRs = check(node, head);
        }
        if(curRs){
            return curRs;
        }
        curRs = dfs(node.left, head) || dfs(node.right, head);
        return curRs;
    }

    public static boolean isSubPathIterative(ListNode head, TreeNode root) {
        if(head==null&&root==null){
            return true;
        }
        Stack<TreeNode> stack=new Stack<>();
        ListNode node = head;
        ListNode prevNode = head;

        while (node!=null){
            prevNode=node;
            node = node.next;
        }
        stack.add(root);
        while(!stack.isEmpty()){
            TreeNode curNode= stack.pop();
            if(curNode.val==head.val){
                if(isValid(curNode, head, prevNode)){
                    return true;
                }
            }
            if(curNode.left!=null){
                stack.add(curNode.left);
            }
            if(curNode.right!=null){
                stack.add(curNode.right);
            }
        }
        return false;
    }

    public static boolean isValid(TreeNode node, ListNode head, ListNode last){
        Stack<Pair<TreeNode, ListNode>> stack=new Stack<>();
        stack.add(new Pair<>(node, head));

        while(!stack.isEmpty()){
            Pair<TreeNode, ListNode> curNode = stack.pop();
            TreeNode curTreeNode=curNode.getKey();
            ListNode curListNode = curNode.getValue();
            if(curTreeNode.val!=curListNode.val){
                continue;
            }
            if(curListNode==last){
                return true;
            }
            if(curTreeNode.left!=null&&curListNode.next!=null){
                stack.add(new Pair<>(curTreeNode.left, curListNode.next));
            }
            if(curTreeNode.right!=null&&curListNode.next!=null){
                stack.add(new Pair<>(curTreeNode.right, curListNode.next));
            }
        }
        return false;
    }

    public static void main(String[] args) {
        //** Requirement
        //- Given (a binary tree root) and (a linked list with head) as (the first node).
        //* Return True
        //  + if (all the elements) in (the linked list) starting from (the head) correspond to some downward path connected in (the binary tree)
        // otherwise
        //  + return False.
        //  + Tức là có 1 path nào đó chứa tất cả các nodes trong linked list từ (head ==> last)
        //- In this context (downward path) means a path that starts at (some node) and (goes downwards).
        //
        // Idea
        //1.
        //1.0,
        // Method-1:
        //- Constraint
        //The number of nodes in the tree will be in the range [1, 2500].
        //The number of nodes in the list will be in the range [1, 100].
        //  + The number of nodes (tree) khá lớn, the number of nodes in the list cũng khá lớn
        //      + 2500*2500*100 ==> Không thể lần nào cũng traverse all tree được.
        //1 <= Node.val <= 100 for each node in the linked list and binary tree.
        //
        //- Brainstorm
        //- Node value có thể duplicated
        //- Head có thể nằm ở bất cứ đâu trong binary tree
        //- Có thể có case:
        //Ex:
        //List:
        //2 -> 2 -> 3 -> 2 -> 2
        //Tree:
        //  - Current node
        //  - Check head ==> finish thì child
        //      + Ta cần phải xét tiếp current_node.child tiếp
        //Example 1:
        //               1
        //            /     \
        //          4        4
        //            \     /
        //             2   2
        ///          /    /  \
        //         1    6     8
        //                  /   \
        //                 1     3
        //
        //Input: head = [4,2,8], root = [1,4,4,null,2,2,null,1,null,6,8,null,null,null,null,1,3]
        //Output: true
        //Explanation: Nodes in blue form a subpath in the binary Tree.
        //- Số lượng node khá nhiều:
        //  + Ta cần cut branch ???
        //  + Khá khó vì ta cần ít nhất traverse toàn bộ tree thì mới biết được kết quả cuối cùng.
        //
        //- Do đi thẳng 1 đường từ trên xuống:
        //  + Nên bài toán sẽ tương tự như việc check:
        //  * (List) contains (another list)
        //Ex:
        //list1 = [1,1,1,2,3,4,7]
        //list2 = [1,2,3,4]
        //  + check list1 include list2
        //
        //Ex:
        //list1 = [1,1,(1),2,(1),2,7]
        //list2 = [1,2,7]
        //  + Có 2 node có thể check và coi nó như head
        //      + Nhưng node thứ 2 ta mới tìm được
        //- Nếu ta lưu path:
        //  + Đến cuối mới check ==> Sẽ bị duplicate 2 lần traverse
        //  ==> Khả năng sẽ TLE
        //- Có trường hợp nào thoả mãn
        //  + x -> y -> z -> t(Không thoả mãn)
        //  Nhưng
        //  + y -> z -> t -> k(Lại thoả mãn)???
        //Ex:
        //list1 = [1,1,(1),2,(1),2,[1],2,8]
        //list2 = [1,2,1,2,8]
        //  + Đến node=1 thứ 1 --> (1) thứ 2 ta vẫn chưa biết có thoả mãn hay không
        //      + Đến [1] ta mới biết là node=1 thứ 1 không thoã mãn
        //      ==> Đến [1] thì ta không traverse về node 1 thứ 2 được
        //- Vì traverse theo tree:
        //  + Vì là top down ==> Traverse xuôi sẽ khó (Do tính subproblem nhiều lần)
        //  ==> WRONG IDEA
        //** Kinh nghiệm:
        //- Nếu traverse DFS:
        //  + Mỗi node đi thêm theo chiều dài k nodes nữa ==> Time: O(N*k)/ O((V+E)*K) mà thôi
        //  ==> Không phải là O(N^2*K)
        //      + Bản chất cũng chỉ là traverse từ mỗi node thêm k nodes nữa mà thôi.
        //
        //  + Ta sẽ đi ngược lại nếu tìm được finish ==> Check ngược lại là được
        //  + Slice window kiểu thế
        //      + Nhưng nếu ta tính ntn thì bộ nhớ sẽ có vấn đề do đi cả:
        //          + Left, right node
        //
        //- Nếu lưu kết quả check từng step thì sao?
        //  ==> Không được vì các phần tử không unique ==> Mối quan hệ có thể lẫn lộn
        //
        //- 1 node có thể là head:
        //
//        Integer[] nodes=new Integer[]{1,4,4,null,2,2,null,1,null,6,8,null,null,null,null,1,3};
//        Integer[] list=new Integer[]{1,4,2,6,8};
        Integer[] nodes=new Integer[]{1,4,4,null,2,2,null,1,null,6,8,null,null,null,null,1,3};
        Integer[] list=new Integer[]{4,2,8};

        TreeNode root=new TreeNode(nodes[0]);
        Queue<TreeNode> queueNodes=new LinkedList<>();
        queueNodes.add(root);
        int i=0;
        for(;i<nodes.length;i++){
            TreeNode currentNode=queueNodes.poll();
            TreeNode next=null;
            TreeNode next1=null;
            if(i*2+1<nodes.length&&nodes[i*2+1]!=null){
                next=new TreeNode(nodes[i*2+1]);
                queueNodes.add(next);
            }
            if(i*2+2<nodes.length&&nodes[i*2+2]!=null){
                next1=new TreeNode(nodes[i*2+2]);
                queueNodes.add(next1);
            }
            if(currentNode!=null){
                currentNode.left=next;
                currentNode.right=next1;
            }
        }
        ListNode node=null;
        ListNode head=null;
        for(int e: list){
            ListNode tmp=new ListNode(e);
            if(node!=null){
                node.next = tmp;
            }else{
                head=tmp;
            }
            node=tmp;
        }
        System.out.println(isSubPath(head, root));
        System.out.println(isSubPathIterative(head, root));
        //
        //1.1, Optimization
        //- Có thể làm iterative để giảm space cho stack
        //- Dùng stack để traverse:
        //  + Nếu traverse bình thường qua các node thì ta sẽ traverse next mà có thể quay lại
        //Example 1:
        //               1
        //            /     \
        //          4        4
        //            \     /
        //             2   2
        ///          /    /  \
        //         1    6     8
        //                  /   \
        //                 1     3
        //- Ta chạy đến 3 ==> back lại 2 ntn?
        //  + Lựa chọn duy nhất là pop hết ra
        //      + Pop đến khi gặp lại điểm cũ thôi
        //- Idea:
        //  + Traverse các node trong tree 1 method riêng
        //  + Traverse tại node đó 1 method riêng
        //- Ở đây traverse là preorder.
        //- Nếu dùng queue ==> Nó sẽ đi theo chiều đúng của việc add node
        //  <> dùng stack:
        //      + Nó sẽ đi từ (right -> left) <> với chiều dùng queue
        //- Traverse all tree by using stack that is ok
        //- Check the current case is valid that we need store node as Pair<TreeNode, ListNode>
        //  + ListNode node: Check next node
        //  + TreeNode: Check left of right (Correspond to current list node)
        //
        //** Kinh nghiệm:
        //- Việc check ListNode ở đây trên từng node đơn giản trở thành:
        //  + Check xem startPoint = Pair<Root, Head> ==> Có đến được Pair<Any, Head> được không
        //  ==> Cái này thì đi path nào cũng được
        //  ==> Dùng (Stack hay queue đều được) (Không quan trọng thứ tự)
        //
        //stack: 1,4,4
        //stack:
        //
        //1.2, Complexity
        //- Space: O(h)(Recursion) => O(n)(Iteration)
        //- Time: O(n*m)
        //
        //#Reference:
        //1443. Minimum Time to Collect All Apples in a Tree
        //133. Clone Graph
        //449. Serialize and Deserialize BST
        //
        //
    }
}
