package E1_daily;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class E116_DesignCircularDeque {

    public List<Integer> queue;
    public int curSize;
    public int maxSize;

    public E116_DesignCircularDeque(int k) {
        queue=new LinkedList<>();
        maxSize=k;
        curSize=0;
    }

    public boolean insertFront(int value) {
        if(curSize==maxSize){
            return false;
        }
        curSize++;
        queue.add(0, value);
        return true;
    }

    public boolean insertLast(int value) {
        if(curSize==maxSize){
            return false;
        }
        curSize++;
        queue.add(value);
        return true;
    }

    public boolean deleteFront() {
        if(curSize==0){
            return false;
        }
        curSize--;
        queue.remove(0);
        return true;
    }

    public boolean deleteLast() {
        if(curSize==0){
            return false;
        }
        curSize--;
        queue.remove(queue.size()-1);
        return true;
    }

    public int getFront() {
        if(curSize==0){
            return -1;
        }
        return queue.get(0);
    }

    public int getRear() {
        if(curSize==0){
            return -1;
        }
        return queue.get(queue.size()-1);
    }

    public boolean isEmpty() {
        return curSize==0;
    }

    public boolean isFull() {
        return curSize==maxSize;
    }

    public static void main(String[] args) {
        //#Reference:
        //622. Design Circular Queue
        //1670. Design Front Middle Back Queue
    }
}
