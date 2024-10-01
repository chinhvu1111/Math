package interviews.bytedance;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

class Solution{

    int[] stackArray;
    int[] plusVal;
    int top;
    public Solution(int maxSize) {
        stackArray=new int[maxSize];
        plusVal=new int[maxSize];
        top=-1;
    }

    public void push(int x) {
        if(top+1>=stackArray.length){
            return;
        }
        stackArray[top+1]=x;
        top++;
    }

    public int pop() {
        if(top<=-1){
            return -1;
        }
        int rs=stackArray[top]+plusVal[top];
        if(top-1>=0){
            plusVal[top-1]+=plusVal[top];
        }
        //Cần reset
        plusVal[top]=0;
        top--;
        return rs;
    }

    public void increment(int k, int val) {
        if(top>=0){
            int sizeIncreased = Math.min(k-1, top);
            plusVal[sizeIncreased]+=val;
        }
    }

}

public class E11_DesignAStackWithIncrementOperation {

    int[] stack;
    int first=-1;
    int last=-1;
    int maxSize;
    int currentLength;
    public E11_DesignAStackWithIncrementOperation(int maxSize) {
        stack=new int[2001];
        this.maxSize=maxSize;
        currentLength=0;
    }

    public void push(int x) {
        if(currentLength==maxSize){
            return;
        }
        last++;
        currentLength++;
        stack[last]=x;
    }

    public int pop() {
        if(currentLength==0){
            return -1;
        }
        currentLength--;
        return stack[last--];
    }

    public void increment(int k, int val) {
        int max=Math.min(k-1, last);
        for(int i=first+1;i<=max;i++){
            stack[i]+=val;
        }
    }

    public static void main(String[] args) {
        E11_DesignAStackWithIncrementOperation stk = new E11_DesignAStackWithIncrementOperation(3); // Stack is Empty []
        stk.push(1);                          // stack becomes [1]
        stk.push(2);                          // stack becomes [1, 2]
        System.out.println(stk.pop());                            // return 2 --> Return top of the stack 2, stack becomes [1]
        stk.push(2);                          // stack becomes [1, 2]
        stk.push(3);                          // stack becomes [1, 2, 3]
        stk.push(4);                          // stack still [1, 2, 3], Do not add another elements as size is 4
        stk.increment(5, 100);                // stack becomes [101, 102, 103]
        stk.increment(2, 100);                // stack becomes [201, 202, 103]
        System.out.println(stk.pop());                            // return 103 --> Return top of the stack 103, stack becomes [201, 202]
        System.out.println(stk.pop());                            // return 202 --> Return top of the stack 202, stack becomes [201]
        System.out.println(stk.pop());                            // return 201 --> Return top of the stack 201, stack becomes []
        System.out.println(stk.pop());                            // return -1 --> Stack is empty return
        //
        //** Requirement
        //- Design a stack that supports increment operations on its elements.
        //Implement the CustomStack class:
        //  + CustomStack(int maxSize) Initializes the object with maxSize which is the maximum number of elements in the stack.
        //  + void push(int x) Adds x to the top of the stack if the stack has not reached the maxSize.
        //  + int pop() Pops and returns the top of the stack or -1 if the stack is empty.
        //  + void inc(int k, int val)
        //      + Increments (the bottom k elements) of the stack by (val).
        //- If there are (less than k) elements in the stack, increment (all the elements) in the stack.
        //
        // Idea
        //1.
        //1.0,
        // Method-1:
        //- Constraint
        //1 <= maxSize, x, k <= 1000
        //0 <= val <= 100
        //At most 1000 calls will be made to each method of increment, push and pop each separately.
        //
        //- Brainstorm
        //
        //1.1, Optimization
        //- Tăng (bottom k elements) of staack lên val:
        //  + Nên gói thành 1 group của k elements
        //      + Nếu lấy value ==> Sẽ cộng vào là được.
        //  + Nhưng vấn đề là k có thể truyền vào trong method thì xử lý ntn?
        //- Khi add value của k phần tử cuối:
        //  + ==> Nó chỉ có tác dụng khi pop ra mà thôi
        //Ex:
        //arr = [4,5,2,3,1], k=3
        //+ inc(k=3, val=2)
        //  + [4,5,2] sẽ được thêm 2
        //+ inc(k=4, val=1)
        //  + [4,5,2,3] sẽ được thêm 1
        //Tăng như sau:
        //  + <=3: x+=2
        //  + <=4: x+=3
        //      + Áp dụng được vì (k=3) và (k=4) có include lẫn nhau
        //          + Số trong khoảng (1->3):
        //              + x+=3
        //          + Số trong khoảng (3,4]:
        //              + x+=2
        //- Nếu dùng linked list để lưu lại thông tin cộng value vào thành list thì cũng được
        //  ==> cái này có thể sẽ hơi phức tạp
        //  + (k=3, val=2) <-> (k=4, val=1)
        //  ** Kinh nghiệm:
        //      + Truyền k random ==> Add node để tăng dần sẽ mất O(log(n))
        //- Có thể dùng array để lưu lại thông tin add(val):
        //  + (k=3, val=2) <-> (k=4, val=1)
        //  + [0,1,2,3,4]
        //  + [0,0,2,0,1] => [3,3,3,1,1] (Cộng từ right-> left)
        //  * Đoạn này hiểu lầm tưởng pop là remove left ==> Khá khó
        //      + Remove right thì dễ rồi:
        //  + Remove index nào ==> Cộng nó vào cái left là được
        //      + plusVal[i-1]+=plusVal[i]
        //  - Chú ý khi gán xong cần reset (plusVal với top lớn hơn về 0):
        //      + plusVal[top]=0;
        //
        //1.2, Complexity
        //- Space: O(maxSize)
        //- Time: O(k) ==> O(1)
        //
        //#Reference:
        //1379. Find a Corresponding Node of a Binary Tree in a Clone of That Tree
        //1959. Minimum Total Space Wasted With K Resizing Operations
        //1298. Maximum Candies You Can Get from Boxes
        //2403. Minimum Time to Kill All Monsters
        //2996. Smallest Missing Integer Greater Than Sequential Prefix Sum
        //1632. Rank Transform of a Matrix
        //2402. Meeting Rooms III
        //
        System.out.println();
        Solution stk1 = new Solution(3); // Stack is Empty []
        stk1.push(1);                          // stack becomes [1]
        stk1.push(2);                          // stack becomes [1, 2]
        System.out.println(stk1.pop());                            // return 2 --> Return top of the stack 2, stack becomes [1]
        stk1.push(2);                          // stack becomes [1, 2]
        stk1.push(3);                          // stack becomes [1, 2, 3]
        stk1.push(4);                          // stack still [1, 2, 3], Do not add another elements as size is 4
        stk1.increment(5, 100);                // stack becomes [101, 102, 103]
        stk1.increment(2, 100);                // stack becomes [201, 202, 103]
        System.out.println(stk1.pop());                            // return 103 --> Return top of the stack 103, stack becomes [201, 202]
        System.out.println(stk1.pop());                            // return 202 --> Return top of the stack 202, stack becomes [201]
        System.out.println(stk1.pop());                            // return 201 --> Return top of the stack 201, stack becomes []
        System.out.println(stk1.pop());                            // return -1 --> Stack is empty return
    }
}
