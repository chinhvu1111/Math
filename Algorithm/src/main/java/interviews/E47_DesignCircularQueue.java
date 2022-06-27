package interviews;

public class E47_DesignCircularQueue {

    int arr[];
    int rear=0;
    int front=0;
    int length=0;

    public E47_DesignCircularQueue(int k) {
        arr=new int[k+1];
        length=k;
    }

    public boolean enQueue(int value) {
        if(front-rear==1||(front==0&&rear==length)){
            return false;
        }
        arr[rear++]=value;
        if(rear>length){
            rear=0;
        }
        return true;
    }

    public boolean deQueue() {
        if(front==rear){
            return false;
        }
        front++;
        return true;
    }

    public int Front() {
        if(front!=rear){
            return arr[front];
        }
        return -1;
    }

    public int Rear() {
        if(front!=rear){
            if(rear==0){
                return arr[length];
            }
            return arr[rear-1];
        }
        return -1;
    }

    public boolean isEmpty() {
        return front==rear;
    }

    public boolean isFull() {
        return front-rear==1||(front==0&&rear==length);
    }

    public static void main(String[] args) {
//        E47_DesignCircularQueue queue=new E47_DesignCircularQueue(3);
//        queue.enQueue(1);
//        queue.enQueue(2);
//        queue.enQueue(3);
//        queue.enQueue(4);
//        System.out.println(queue.Rear());
//        System.out.println(queue.isFull());
//        queue.deQueue();
//        queue.enQueue(4);
//        System.out.println(queue.Rear());

//        E47_DesignCircularQueue queue = new E47_DesignCircularQueue(6);
//        queue.enQueue(6);
//        System.out.println(queue.Rear());
//        System.out.println(queue.Rear());
//        System.out.println(queue.deQueue());
//        queue.enQueue(5);
//        System.out.println(queue.Rear());;
//        System.out.println(queue.deQueue());
//        System.out.println(queue.Front());;
//        System.out.println(queue.deQueue());
//        System.out.println(queue.deQueue());
//        System.out.println(queue.deQueue());

        E47_DesignCircularQueue queue = new E47_DesignCircularQueue(2);
        queue.enQueue(1);
        queue.enQueue(2);
        System.out.println(queue.deQueue());
        queue.enQueue(2);
        System.out.println(queue.deQueue());
        queue.enQueue(3);
        System.out.println(queue.deQueue());
        queue.enQueue(3);
        System.out.println(queue.deQueue());
        System.out.println(queue.Front());
    }

}
