package interviews.bytedance;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

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
        //#Reference:
        //1379. Find a Corresponding Node of a Binary Tree in a Clone of That Tree
        //1959. Minimum Total Space Wasted With K Resizing Operations
        //1298. Maximum Candies You Can Get from Boxes
        //2403. Minimum Time to Kill All Monsters
    }
}
