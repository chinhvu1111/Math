package interviews;

public class E32_MinStack {

    int arr[];
    int top=0;
    int min=Integer.MAX_VALUE;
    int mins[];

    public E32_MinStack() {
        arr=new int[10000];
        mins=new int[10000];
    }

    public void push(int val) {
        arr[top]=val;
        min=Math.min(min, val);
        mins[top]=Math.min(min, val);
        top++;
    }

    public void pop() {
        if(top>0){
            top--;
            if(top!=0){
                min=mins[top-1];
            }else{
                min=Integer.MAX_VALUE;
            }
        }else {
            mins[top]=Integer.MIN_VALUE;
        }
    }

    public int top() {
        if(top>0){
            return arr[top-1];
        }
        return -1;
    }

    public int getMin() {
        if(top>0){
            return mins[top-1];
        }
        return -1;
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

        E32_MinStack minStack = new E32_MinStack();
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
