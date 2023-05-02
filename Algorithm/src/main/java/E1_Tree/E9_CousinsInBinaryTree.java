package E1_Tree;

import java.util.*;

public class E9_CousinsInBinaryTree {

    public class TreeNode {
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

    public static HashMap<Integer, Integer> nodeMapLevel=null;
    public static HashMap<Integer, Integer> nodeMapParent=null;

    public static boolean isCousinsTraverseAllTree(TreeNode root, int x, int y) {
        if(root==null){
            return false;
        }
        nodeMapLevel=new HashMap<>();
        nodeMapParent=new HashMap<>();
        solution(root, 0);

        int levelx=nodeMapLevel.get(x);
        int levely=nodeMapLevel.get(y);
        if(levelx!=levely){
            return false;
        }

        int parentX=nodeMapParent.get(x);
        int parentY=nodeMapParent.get(y);
        return parentX != parentY;
    }

    public static void solution(TreeNode node, int level){
        nodeMapLevel.put(node.val, level);
        if(node.left!=null){
            nodeMapParent.put(node.left.val, node.val);
            solution(node.left, level+1);
        }
        if(node.right!=null){
            nodeMapParent.put(node.right.val, node.val);
            solution(node.right, level+1);
        }
    }

    public static int depthX, depthY;
    public static int x1, y1;
    public static boolean isCousins(TreeNode root, int x, int y) {
        if(root==null){
            return false;
        }
        x1=x;
        y1=y;
        depthX=-1;
        depthY=-1;
        return solutionTraversPart(root, 1);
    }

    public static boolean solutionTraversPart(TreeNode node, int level){
        boolean isFind=false;

        if(node.left!=null){
            if(node.left.val==x1){
                depthX=level+1;
            }
            if(node.left.val==y1){
                depthY=level+1;
            }
            if(node.left.val==x1&&depthY!=-1){
                //    System.out.printf("%s %s %s\n", depthX, depthY, node.right);
                return (node.right == null || node.right.val != y1)&&depthY==depthX;
            }
            if(node.left.val==y1&&depthX!=-1){
                //    System.out.printf("%s %s %s\n", depthX, depthY, node.right);
                return (node.right==null|| node.right.val!=x1)&&depthY==depthX;
            }
            isFind=solutionTraversPart(node.left, level+1);
        }
        if(isFind){
            return true;
        }
        if(node.right!=null){
            if(node.right.val==x1){
                depthX=level+1;
            }
            if(node.right.val==y1){
                depthY=level+1;
            }
            if(node.right.val==x1&&depthY!=-1){
                //    System.out.printf("%s %s %s %s\n", depthX, depthY, node.left==null, (node.left==null||node.left.val!=y1)&&depthY==depthX);
                return (node.left==null||node.left.val!=y1)&&depthY==depthX;
            }
            if(node.right.val==y1&&depthX!=-1){
                //    System.out.printf("%s %s %s %s\n", depthX, depthY, node.left==null, (node.left==null||node.left.val!=x1)&&depthY==depthX);
                return (node.left==null||node.left.val!=x1)&&depthY==depthX;
            }
            isFind=solutionTraversPart(node.right, level+1);
        }
        return isFind;
    }

    public static boolean isCousinsRecursionOptimization(TreeNode root, int x, int y) {
        if(root==null){
            return false;
        }
        x1=x;
        y1=y;
        depthX=-1;
        depthY=-1;
        return solutionTraversPartRecursion(root, 1);
    }

    public static boolean solutionTraversPartRecursion(TreeNode node, int level){
        boolean isFind=false;

        if(node.left!=null){
            if(node.left.val==x1){
                depthX=level+1;
            }
            if(node.left.val==y1){
                depthY=level+1;
            }
            if(node.left.val==x1&&depthY!=-1){
                //    System.out.printf("%s %s %s\n", depthX, depthY, node.right);
                return (node.right == null || node.right.val != y1)&&depthY==depthX;
            }
            if(node.left.val==y1&&depthX!=-1){
                //    System.out.printf("%s %s %s\n", depthX, depthY, node.right);
                return (node.right==null|| node.right.val!=x1)&&depthY==depthX;
            }
            isFind= solutionTraversPartRecursion(node.left, level+1);
        }
        if(isFind){
            return true;
        }
        if(node.right!=null){
            if(node.right.val==x1){
                depthX=level+1;
            }
            if(node.right.val==y1){
                depthY=level+1;
            }
            if(node.right.val==x1&&depthY!=-1){
                //    System.out.printf("%s %s %s %s\n", depthX, depthY, node.left==null, (node.left==null||node.left.val!=y1)&&depthY==depthX);
                return (node.left==null||node.left.val!=y1)&&depthY==depthX;
            }
            if(node.right.val==y1&&depthX!=-1){
                //    System.out.printf("%s %s %s %s\n", depthX, depthY, node.left==null, (node.left==null||node.left.val!=x1)&&depthY==depthX);
                return (node.left==null||node.left.val!=x1)&&depthY==depthX;
            }
            isFind= solutionTraversPartRecursion(node.right, level+1);
        }
        return isFind;
    }

    public static boolean isCousinsRecursionIterative(TreeNode root, int x, int y) {
        if(root==null){
            return false;
        }
        x1=x;
        y1=y;
        Queue<TreeNode> listNodes=new LinkedList<>();
        listNodes.add(root);
        int level=1;

        while (!listNodes.isEmpty()){
            boolean isNotSibling=false;
            boolean isExists=false;
            List<TreeNode> nodesNextLevel=new ArrayList<>();

            while (!listNodes.isEmpty()){
                TreeNode currentNode=listNodes.poll();

                if(currentNode==null){
                    if(isExists) {
                        isNotSibling = true;
                    }
                    continue;
                }
                if(currentNode.val==x1 || currentNode.val==y1){
                    if(isExists){
                        if(isNotSibling){
                            return true;
                        }
                    }
                    isExists=true;
                }

                if(currentNode.left!=null){
                    nodesNextLevel.add(currentNode.left);
                }
//                else{
//                    nodesNextLevel.add(null);
//                }
                if(currentNode.right!=null){
                    nodesNextLevel.add(currentNode.right);
                }
//                else{
//                    nodesNextLevel.add(null);
//                }
                nodesNextLevel.add(null);
            }
            // System.out.println(nodesNextLevel);
            if(isExists){
                 System.out.println(level);
                return false;
            }
            level++;
            listNodes.addAll(nodesNextLevel);
        }

        return false;
    }

    public static void main(String[] args) {
        //** Đề bài
        //- Cho 1 binary tree
        //- Check 2 nodes có phải cousin hay không
        //- 2 nodes là cousins khi:
        //+ Cùng level
        //+ Không chung parent (Tức là parent ngay trước nó)
        //
        //** Tư duy
        //1.
        //1.0, Idea
        //- Điều kiện cho :
        //+ x!=y
        //+ Số lượng nodes <=100
        //+ Value của các nodes khác nhau
        //
        //- Ta sẽ traverse tree
        //- Vì value của các nodes khác nhau --> ta sẽ lưu level của từng nodes vào hashmap.
        //- Ta cũng sẽ lưu parent của từng node vào hashmap.
        //
        //- Ta có thể travese bằng recursion
        //
        //- Nếu giải theo cách bên trên --> Lúc nào cũng có xu hướng cache cả tree ==> Không cần thiết
        //--> Ta chỉ cần traverse đến vị trí (x, y) là được (không cần đi thêm)
        //- Ta chỉ cần tìm depth cho x trước --> sau đó tìm depth cho y ==> So sánh là được.
        //+ Traverse cho đến x trước
        //+ Sau đó sẽ check nodes cùng parent với x
        //--> nếu node đó = y --> return false
        //+ Điều kiện thêm x,y luôn thuộc tree
        //<> thì check depth của y tiếp ==> Nếu depth đó =x ==> return true, vì y thuộc vào (nhánh khác/ giống)
        //cùng depth với x
        //
        //* Câu hỏi:
        //- Làm ntn để chỉ traverse 1 lần mà trả được kết quả:
        //- Ta sẽ khởi tạo depthX, depthY=-1 : Nếu tìm được kết quả được là x/ y ta sẽ check với depth của node còn lại tương ứng
        //+ Nếu depthX=-1/ depthY=-1 : Tức là node còn lại chưa tìm được --> Ta sẽ traverse tiếp
        //+ Nếp depthX tìm được check depthY !=0 (Tức là cũng đã tìm được) : return result (Hoặc là depthY check depthX)
        //
        //- Nếu dùng bfs --> Thì nếu trong cùng 1 level ta tìm được x ==> nếu y không đứng cạnh x + tìm được y trong level đó thì
        //return true <> return false.
        //==> Cần 1 kỹ thuật để xử lý trong bài toán tìm 2 điểm không cùng parent.
        //
        //1.1, Kỹ thuật đánh dấu siblings
        //- Trong binary tree có 1 kỹ thuật đánh dấu 2 children có phải siblings với nhau hay không <=> Có chung 1 parent hay không
        //+ Ta sẽ add null node vào để thể hiện ngăn cách nhau
        //- Ta có 1 node1 đã được duyệt qua rồi trong quá trình ta scan từng nodes của từng level một:
        //+ Trong quá trình scan nodes tiếp của 1 level nếu gặp node=null --> Tức là nodes sau đó chắc chắn không là sibling với node1
        //- Nếu ta không dùng phương pháp add null node như trên thì sẽ bị case:
        //VD:
        //                        10
        //                  /           \
        //                1               2
        //              /   \          /     \
        //            3      (4)      (5)     6
        //--> Với case trên xét x=4, y=5 : 2 nodes đứng cạnh nhau nhưng vẫn không là siblings với nhau
        //==> Việc xét các nodes đứng cạnh nhau là sai : Ta cần ngăn cách nó ra
        //VD: 3,4,null,5,6 : Như thế này thì sẽ xử lý được.
        //#Reference:
        //994. Rotting Oranges
        //156. Binary Tree Upside Down
        //341. Flatten Nested List Iterator
        //431. Encode N-ary Tree to Binary Tree
    }
}
