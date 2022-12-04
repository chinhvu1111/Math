package interviews;

public class E34_ImplementQueueUsingStacks_queue {

    int front=-1;
    int rear=-1;
    int arr[]=null;

    public E34_ImplementQueueUsingStacks_queue() {
        arr=new int[100];
    }

    public void push(int x) {
        arr[++rear]=x;
    }

    public int pop() {
        if(front==-1&&rear==-1){
            return -1;
        }
        int topVal=arr[front+1];
        front++;
        return topVal;
    }

    public int peek() {
        if(front==-1&&rear==-1){
            return -1;
        }
        return arr[front+1];
    }

    public boolean empty() {
        return rear==front;
    }

    public static void main(String[] args) {
        //Cần tối ưu lại code bài này:
        //Vì nó là dạng classic --> Cần partern
        E34_ImplementQueueUsingStacks_queue usingStacks=new E34_ImplementQueueUsingStacks_queue();
        usingStacks.push(1);
        usingStacks.push(2);
        System.out.println(usingStacks.peek());
        System.out.println(usingStacks.pop());
        System.out.println(usingStacks.pop());
        System.out.println(usingStacks.empty());
    }
}
