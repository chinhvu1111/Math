package interviews;

import java.util.Stack;

public class E46_ImplementQueueUsingStacks {

    Stack<Integer> stack1;
    Stack<Integer> stack2;

    public E46_ImplementQueueUsingStacks() {
        stack1=new Stack<>();
        stack2=new Stack<>();
    }

    public void push(int x) {
        stack1.push(x);
    }

    public int pop() {
        if(!stack2.isEmpty()){
            return stack2.pop();
        }else{
            while(!stack1.isEmpty()){
                stack2.push(stack1.pop());
            }
            return stack2.pop();
        }
    }

    public int peek() {
        if(!stack2.isEmpty()){
            return stack2.peek();
        }else{
            while(!stack1.isEmpty()){
                stack2.push(stack1.pop());
            }
            return stack2.peek();
        }
    }

    public boolean empty() {
        return stack1.isEmpty()&&stack2.isEmpty();
    }

    public static void main(String[] args) {
        E46_ImplementQueueUsingStacks obj = new E46_ImplementQueueUsingStacks();
        obj.push(1);
        obj.push(2);
        System.out.println(obj.peek());
        System.out.println(obj.pop());
        boolean param_4 = obj.empty();
        //Bài này giúp ta ôn lại kiến thức về queue + Phân biệt rõ rệt giữa stack và queue
        //+ Queue: peek chính là element ta insert cũ nhật
        //+ Stack : peek chính là element vừa insert xong.
        //QUY TẮC QUEUE:
        //enqueue --> tail --> QUEUE --> head (peek)--> dequeue
        //Ta tư duy như sau:
        //Stack về cơ bản cơ chế khác queue:
        //- Không có cách nào pop được đầu của 1 stack
        //==> Ngoài việc copy stack đó ra 1 stack khác (Reverse lại) ===> Sau đó pop để cut + return.
        //==> Sử dụng stack2
        //- Stack1 sẽ được push bình thường cho đến khi pop/ peek nó sẽ được copy sang stack2
        //--> Còn không thì tốc độ y hệt.

    }
}
