package interviews;

public class E32_MinStack_2_stack {

    public class ListNode
    {
        public int val;
        public int minVal;
        public ListNode next;
    }

    private ListNode node;
    public E32_MinStack_2_stack() {
        node = null;
    }

    public void push(int val) {
        ListNode curr = new ListNode();
        curr.val = val;
        if (node == null)
            curr.minVal = val;
        else
        {
            curr.minVal = Math.min(node.minVal, val);
            curr.next = node;
        }

        node = curr;

    }

    public void pop() {
        node = node.next;

    }

    public int top() {
        return node.val;

    }

    public int getMin() {
        return node.minVal;

    }

    public static void main(String[] args) {
        int num=Integer.MAX_VALUE;
//        E32_MinStack minStack = new E32_MinStack();
//        minStack.push(-2);
//        minStack.push(0);
//        minStack.push(-3);
//        System.out.println(minStack.getMin()); // return -3
//        minStack.pop();
//        System.out.println(minStack.top());    // return 0
//        System.out.println(minStack.getMin()); // return -2

//        E32_MinStack minStack = new E32_MinStack();
////        minStack.push(-2);
//        minStack.push(-12);
//        minStack.push(-20);
////        minStack.push(0);
//        minStack.push(-3);
//        minStack.pop();
//        minStack.pop();
//        minStack.pop();
//        minStack.pop();
//        System.out.println(minStack.getMin()); // return -3
////        minStack.pop();
//        System.out.println(minStack.top());    // return 0
//        minStack.push(-30);
//        minStack.pop();
//        System.out.println(minStack.getMin()); // return -2
//        System.out.println(minStack.top()); // return -2

        //Case 2: Bị lỗi pop khi top-- ==> Lúc đó top==0 Lúc đó (min)
        // --> reset về (MAX_INTEGER)
//        E32_MinStack minStack = new E32_MinStack();
////        minStack.push(-2);
//        minStack.push(2147483646);
//        minStack.push(2147483646);
//        minStack.push(2147483647);
//        System.out.println(minStack.top());
//        minStack.pop();
//        minStack.pop();
//        minStack.pop();
//        minStack.push(2147483647);
//        System.out.println(minStack.top());
//        System.out.println(minStack.getMin());

        E32_MinStack_2_stack minStack = new E32_MinStack_2_stack();
//        minStack.push(-2);
        minStack.push(-10);
        minStack.push(14);
        minStack.push(-20);
        minStack.pop();
        minStack.push(10);
        minStack.push(-7);
        minStack.push(-7);
        minStack.pop();
        System.out.println(minStack.top());
        System.out.println(minStack.getMin());
        minStack.pop();
    }
}
