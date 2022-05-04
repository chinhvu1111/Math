package interviews;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Set;
import java.util.TreeSet;

class LRUCache{
//    private LinkedList<Integer> usage=null;
    private Node first=null;
    private Node last=null;
    private Node cache[]=null;
    private int capacity=0;
    private int currentLength=0;

    class Node{
        int key;
        int value;
        Node next;
        Node prev;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }

        public void setKey(int key) {
            this.key = key;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        public void setPrev(Node prev) {
            this.prev = prev;
        }

        public int getKey() {
            return key;
        }

        public int getValue() {
            return value;
        }

        public Node getNext() {
            return next;
        }

        public Node getPrev() {
            return prev;
        }
    }

    public LRUCache(int capacity) {
        cache=new Node[10000];
//        Arrays.fill(cache, -1);
        this.capacity=capacity;
    }

    public int get(Integer key) {
        Node oldNode=cache[key];
        if(key>=10000||oldNode==null){
            return -1;
        }
        Node node=oldNode;
        if(node!=null){
            removeKey(node);
            node.prev=null;
//            usage.remove(key);
        }
        addKey(node);
        return node.getValue();
    }

    public void put(Integer key, Integer value) {
        Node node=cache[key];
        if(currentLength+1>capacity&&cache[key]==null){
            if(first!=null){
                cache[first.key]=null;
                first=first.next;
            }
            if(first!=null){
                first.prev=null;
            }
        }else if(node==null){
            this.currentLength++;
        }else {
            removeKey(node);
            node.prev=null;
            cache[key]=null;
        }
        Node newNode=new Node(key, value);
        cache[key]=newNode;

        if(first==null){
            first=newNode;
        }
        addKey(newNode);
    }

    private void removeKey(Node node){
        if(node.prev!=null){
            node.prev.next=node.next;
            if(node.next!=null){
                node.next.prev=node.prev;
            }else{
                last=node.prev;
            }
        }else if(node.next!=null){
            this.first=node.next;
            node.next.prev=null;
        }
        node.next=null;
        node.prev=null;
    }

    private void addKey(Node node){
        if(this.last==null&&node!=null){
            this.last=node;
        }else if(this.last!=null){
            this.last.next=node;
            node.prev=this.last;
            this.last=node;
        }
    }

    public Node[] getCache() {
        return cache;
    }

    public int getCapacity() {
        return capacity;
    }
}

public class E1_LRUCache {
    public static void main(String[] args) {
        LRUCache lRUCache = new LRUCache(3);
        //Case 1:
        //Với cách code 2: Lỗi liên quan đến gán cache[key]=nyll đối với element bị remove
//        lRUCache.put(1, 1); // cache is {1=1}
//        lRUCache.put(2, 2); // cache is {1=1, 2=2}
//        System.out.println(lRUCache.get(1));    // return 1
//        lRUCache.put(3, 3); // LRU key was 2, evicts key 2, cache is {1=1, 3=3}
//        System.out.println(lRUCache.get(2));    // returns -1 (not found)
//        lRUCache.put(4, 4); // LRU key was 1, evicts key 1, cache is {4=4, 3=3}
//        System.out.println(lRUCache.get(1));    // return -1 (not found)
//        System.out.println(lRUCache.get(3));    // return 3
//        System.out.println(lRUCache.get(4));    // return 4

        //Case 2: Case này sai do nếu put duplicated key --> Thì không tăng length của Array nữa .
//        System.out.println(lRUCache.get(2));
//        lRUCache.put(2, 6);
//        System.out.println(lRUCache.get(1));
//        lRUCache.put(1, 5);
//        lRUCache.put(1, 2);
//        System.out.println(lRUCache.get(1));
//        System.out.println(lRUCache.get(2));
        //Case 3: Lỗi do put lại duplicated thì coi như dùng lại key đó --> Đẩy key đó lên đầu
//        lRUCache.put(2, 1);
//        lRUCache.put(2, 2);
//        lRUCache.get(2);
//        lRUCache.put(1, 1);
//        lRUCache.put(4, 1);
//        System.out.println(lRUCache.get(2));
        //Case 4: time limit
        //Case 5: Lỗi do set nhầm cache[first.value]=null --> cache[first.key]=null
//        lRUCache.put(1, 0);
//        lRUCache.put(2, 2);
//        System.out.println(lRUCache.get(1));
//        lRUCache.put(3, 3);
//        System.out.println(lRUCache.get(2));
//        lRUCache.put(4, 4);
//        System.out.println(lRUCache.get(1));
//        System.out.println(lRUCache.get(3));
//        System.out.println(lRUCache.get(4));
        //Case 6:
        lRUCache.put(1, 1); //1
        lRUCache.put(2, 2);//1,2
        lRUCache.put(3, 3);//1,2,3
        lRUCache.put(4, 4);//2,3,4
        System.out.println(lRUCache.get(4));//2,3,4
        System.out.println(lRUCache.get(3));//2,4,3
        System.out.println(lRUCache.get(2));//4,3,2
        System.out.println(lRUCache.get(1));//4,3,2
        lRUCache.put(5, 5);//3,2,5
        System.out.println(lRUCache.get(1));//-1
        System.out.println(lRUCache.get(2));//3,5,2 : 2
        System.out.println(lRUCache.get(3));//5,2,3 : 3
        System.out.println(lRUCache.get(4));//-1
        System.out.println(lRUCache.get(5));//2,3,5 : 5
    }
}
