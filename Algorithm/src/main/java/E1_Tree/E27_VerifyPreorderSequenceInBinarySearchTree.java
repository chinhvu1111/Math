package E1_Tree;

import java.util.Stack;

public class E27_VerifyPreorderSequenceInBinarySearchTree {

    public static boolean verifyPreorder(int[] preorder) {
        int n=preorder.length;
        if(n<=1){
            return true;
        }
        Stack<Integer> stack=new Stack<>();
        int min=Integer.MIN_VALUE;

        for(int node: preorder){
            while(!stack.isEmpty()&&stack.peek()<node){
                min=stack.pop();
            }
            if(min>node){
                return false;
            }
            stack.add(node);
        }
        return true;
    }

    public static boolean verifyPreorderOptimization(int[] preorder) {
        int n=preorder.length;
        if(n<=1){
            return true;
        }
        int index=-1;
        int min=Integer.MIN_VALUE;

        for(int node: preorder){
            while(index!=-1&&preorder[index]<node){
                min=preorder[index];
                index--;
            }
            if(min>node){
                return false;
            }
            index++;
            preorder[index]=node;
        }
        return true;
    }

    public static boolean solution(int min, int max, int[] preorder, int[] index){
        if(index[0]==preorder.length){
            return true;
        }
        int root=preorder[index[0]];
        if(root<=min||root>=max){
            return false;
        }
        index[0]++;
        boolean left=solution(min, root, preorder, index);
        boolean right=solution(root, max, preorder, index);
        return left||right;
    }

    public static boolean verifyPreorderrecursion(int[] preorder) {
        int[] index=new int[]{0};
        return solution(Integer.MIN_VALUE, Integer.MAX_VALUE, preorder, index);
    }

    public static void main(String[] args) {
        // Requirement
        //- Given the array which made by traversing the (binary search tree) the preoder rule
        //* return true if the array is correct
        //
        // Idea
        //1.
        //1.0,
        //- Constraint:
        //+ 1 <= preorder.length <= 10^4
        //+ 1 <= preorder[i] <= 10^4
        //+ All the elements of preorder are unique.
        //--> Không quá lớn có thể xử lý O(N)/ O(N*log(N))
        //
        //- Brainstorm
        //- Preorder traverse:
        //  + Trái trước hết
        //  + Lên -> right -> back to rule above
        //- Nếu có 1 traverse --> Không thể reverse nó thành tree được.
        //  + Nhưng liệu binary ("search") tree thì có được không?
        //Ex-1:
        //5,2,1,3,6
        //Tree-1:
        //          5
        //       /    \
        //     2       6
        //   /   \
        // 1      3
        //- Rule:
        //+ Add các node từ root vào stack
        //+ Nếu node tới val < peak(): add
        //+ Nếu node tới val > peak(), val < prev peak() : pop() + add(val)
        //+ Nếu node tới val > peak(), val > prev peak() : pop()
        //- Bị case nếu node tới nhỏ hơn nhưng lớn hơn root trước thì sao?
        //Ex-2:
        //5,2,1,(3),[2],6
        //Tree-1:
        //          5
        //       /    \
        //     2       6
        //   /   \
        // 1      3
        //      /
        //   (2)
        //+ Nếu add 2 : Invalid
        //  + 2==2:
        //- Nếu add vào right của 1 value --> Ta không add vào left của nó nữa.
        //Ex-3:
        //5,2,1,(3),[2],6
        //+ Steps:
        //  + add(5,2,1), stack: 5,2,1
        //  + check(4), stack: 5,2,1
        //      + 1<3: pop(1) : stack: 5,2 ( If == return false)
        //      + 2<3: pop(2) : stack: 5
        //      + 5>3: add(3) : stack: 5,3
        //  + Check(2), stack: 5,3
        //      + 3>2 but we got the previous root having value is equal to 2:
        //      ==> Return false.
        //- Add a variable to store (the root's value)
        //+ Khi nào change (min variable):
        //  + Giữ lại root value ==> Hơi khó control code vì (check peak() và prev peek() không tốt)
        //  + Không giữ root --> Không có rule để update lại
        //  ==> Cần giữ lại root.
        //- Rule:
        //  + min= giá trị ngay trước root cũ khi pop()
        //
        //Ex-4:
        //5,2,1,4,3,6
        //+ Steps:
        //  + add(5,2,1), stack: 5,2,1
        //  + check(4), stack: 5,2,1
        //      + 1<4: pop(1) : stack: 5,2 ( If == return false)
        //      + 2<4: pop(2) : stack: 5
        //      + 5>4: add(3) : stack: 5,4
        //  + Check(3), stack: 5,4
        //      + 4>3 : add(3), stack: 5,4,3
        //  + Check(6), stack: 5,4,3
        //      + 3<6 : pop(3)
        //      + 4<6 : pop(4)
        //      + 5<6 : pop(5)
        //      + empty : add(6)
        //
        //Tree-1:
        //          5
        //       /    \
        //     2       6
        //   /   \
        // 1      3
        //      /
        //   (2)
        //
        //Ex-2:
        //preorder = [7,5,2,1,3,4,6]
        //==> False
        //              7
        //            /    \
        //         (5)       9
        //        /   \
        //      2      6
        //    /   \
        //   1    (3)
        //           \
        //            4
        //          /   \
        //        x      y
        //* Rule:
        //  - Min>= value gần nhất
        //  - Max <= root gần nhất
        //Ex: bên trên:
        //+ y<=5
        //+ x>=3
        //- Xét y:
        //  + Nếu y>5: pop all ra
        //  + Nếu y<5 && y>4 : add vào stack
        //- Xét x:
        //  + Nếu x<4 && x>3: add vào stack
        //  + Nếu x<4 && x<3 : return false
        //
        //- Tính 5 như thế nào?
        //
        //- Tính 3 như thế nào?
        //
        //+ Steps:
        //  + add(7,5,2,1), stack: 7,5,2,1
        //  + check(3), 1<3, 2<3, pop(1): stack: 5,2
        //  + check(2): 2<3, 5>3, add(3) : stack: 5,2,3
        //  + check(4): 3<4, 5>3, add(3) : stack: 5,2,3
        //
        //- Có cần thiết phải traverse add all nodes to (stack)?
        //- Follow theo hướng check x có thuộc (min, max) hay không là được.
        //- Hạ hệ quy chiếu xuống subtree
        //+ Mỗi tree sẽ có:
        //  + Root : ==> Hạ quy chiếu tiếp
        //  + Previous node of root:
        //      + root là left of the previous node: all node < prev_node.val
        //      + root là right of the previous node: all node > prev_node.val
        //- Mình sẽ đứng ở root --> Traverse để update range (min, max)
        //  + Quan trọng là node nằm ở left hay right ==> Sau đó mới update range(min, max)
        //+ val=7: max=19999, min=-1
        //+ val=5: 5>-1, 5<7 ==> add(5) (left)
        //  + Update : max=7, min=-1
        //+ val=2: 2<5, 2>-1 (left)
        //  + Update : max=5, min=-1
        //+ val=1: 1<2, 1>-1 (left)
        //  + Update : max=2, min=-1
        //+ val=3:
        //  + 3>max=2 --> pop(1)
        //  + 3<max=5, 3 > 2, right
        //    update: max=5, min=2
        //
        //Ex:
        //               7
        //            /    \
        //         (5)       9
        //        /   \
        //      2      6
        //    /   \
        //   1    (3)
        //           \
        //            4
        //          /   \
        //        x      y
        //
        //+ Để thoả mãn (x<4 && x>3)
        //- Thao tác pop xảy ra khi nào:
        //Ex:
        //               7
        //            /    \
        //         (5)       9
        //        /   \
        //      2      6
        //    /   \
        //   1    (3)
        //           \
        //            4
        //          /   \
        //        x      t
        //      /
        //    z
        //* Chốt lại (standard idea):
        //- Ta có 2 thao tác:
        //  + Add left : Ta cần so sánh với previous node
        //  + Add right : Không cần so sánh vì chỉ cần pop các node < nó ra + add vào là được.
        //- Phần update min node để so sánh khi add left ==> Ta sẽ update dựa trên việc pop bên trên
        //  ==> Nó sẽ cho ta thằng previou root node.
        //
        //1.1, Optimization
        //- Ta có thể tối ưu space bằng cách sử dụng array input
        //
        //1.2, Complexity
        //- Space : O(1)
        //- Time : O(n)
        //
        //2. Recursion
        //2.0, Idea
        //- Ta sẽ chia ra 2 branch left và right chạy --> Giống y hệt preorder traversal để giải.
        //- Mỗi branch sẽ chia (min, max) value để validate (false or true)
        //- Xét node x:
        //  + x<root --> left
        //  + x>root --> đi ra ngoài sau đó xét tiếp
        //- Init:
        //- Có 2 cách để share variable giữa các method:
        //  + Ta sẽ dùng biến nhớ dạng array[0]
        //  + Return lại index sau khi dùng xong
        //- Ta sẽ kết hợp:
        //  + Dùng array index[0]
        //  + return false/ true để giải quyết bài này
        //
        //Ex:
        //                  7
        //            /          \
        //         (5)             9
        //        /                  \
        //      2(min=-1, max=5)       6
        //    /   \
        //   1    (3) (min=2, max=5)
        //           \
        //            4 (min=3, max=5)
        //==> max sẽ được kế thừa khi qua right
        //2.1, Optimization
        //2.2, Complexity
        //- N is the length of array
        //- H is height of array
        //- Space : O(h)
        //- Time : O(n)
        //
        //#Reference:
        //124. Binary Tree Maximum Path Sum
        //1145. Binary Tree Coloring Game
        //536. Construct Binary Tree from String
        //2421. Number of Good Paths
        //545. Boundary of Binary Tree
        //2096. Step-By-Step Directions From a Binary Tree Node to Another
        int[] preorder = {5,2,1,3,6};
        System.out.println(verifyPreorder(preorder));
        System.out.println(verifyPreorderOptimization(preorder));
        System.out.println(verifyPreorderrecursion(preorder));
    }
}
