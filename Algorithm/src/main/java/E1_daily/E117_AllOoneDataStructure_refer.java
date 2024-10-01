package E1_daily;

import javafx.util.Pair;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class E117_AllOoneDataStructure_refer {

    public class Node{
        int freq;
        Node prev;
        Node next;
        Set<String> keys=new HashSet<>();

        Node(int freq){
            this.freq=freq;
        }
    }

    Node head;
    Node tail;
    Map<String, Node> map;

    public E117_AllOoneDataStructure_refer() {
        //Đoạn này đặc biệt ==>  Để tạo ra double linkedlist:
        //  + 2 đầu head - tail
        //  ==> Nên theo pattern này để tránh check null 2 đầu.
        head=new Node(0);
        tail=new Node(0);
        head.next=tail;
        tail.prev=head;
        map=new HashMap<>();
    }

    public void removeNode(Node node){
        Node prevNode = node.prev;
        Node nextNode = node.next;
        prevNode.next=nextNode;
        nextNode.prev=prevNode;
    }

    public void inc(String key) {
        //Key đã có rồi
        if(map.containsKey(key)){
            //Old node
            Node node = map.get(key);
            int freq = node.freq;
            node.keys.remove(key);

            Node nextNode=node.next;
            //node <-> nextNode
            //node <-> newNode <-> nextNode
            if(nextNode == tail || nextNode.freq != freq+1){
                Node newNode = new Node(freq+1);
                newNode.keys.add(key);
                newNode.prev = node;
                node.next=newNode;
                newNode.next=nextNode;
                nextNode.prev=newNode;
                map.put(key, newNode);
            }else{
                nextNode.keys.add(key);
                map.put(key, nextNode);
            }
            if(node.keys.isEmpty()){
                //remove node
                removeNode(node);
            }
        }else{
            //Chỗ này không update lại tail à?
            //- new node được add vào có (value == 1)
            //  + Do chưa exists
            Node firstNode = head.next;
            if(firstNode == tail || firstNode.freq > 1){
                //Vào đây tức là gắn vào trước firstNode
                //
                Node newNode=new Node(1);
                newNode.keys.add(key);
                newNode.prev = head;
                newNode.next = firstNode;
                head.next=newNode;
                firstNode.prev=newNode;
                map.put(key, newNode);
            }else{
                //Mới tinh ==> first node có value == 1
                //==> Ta group new node vào luôn với first node
                //
                firstNode.keys.add(key);
                map.put(key, firstNode);
            }
        }
    }

    public void dec(String key) {
        //Không có thì không remove
        if(!map.containsKey(key)){
            return;
        }
        Node node = map.get(key);
        node.keys.remove(key);
        int freq = node.freq;
        if(freq==1){
            map.remove(key);
        }else{
            Node prevNode = node.prev;
            if(prevNode == head || prevNode.freq != freq-1){
                // prevNode <-> newNode <-> node
                Node newNode = new Node(freq-1);
                newNode.keys.add(key);
                newNode.prev=prevNode;
                newNode.next=node;
                prevNode.next=newNode;
                node.prev=newNode;
                map.put(key, newNode);
            }else{
                prevNode.keys.add(key);
                map.put(key, prevNode);
            }
        }
        if(node.keys.isEmpty()){
            removeNode(node);
        }
    }

    public String getMaxKey() {
        if(tail.prev==head){
            return "";
        }
        return tail.prev.keys.iterator().next();
    }

    public String getMinKey() {
        if(head.next==tail){
            return "";
        }
        return head.next.keys.iterator().next();
    }

    public static void main(String[] args) {
        //** Requirement
        //- Design a data structure to store (the strings' count) with the ability to return the strings with (minimum and maximum) counts.
        //Implement the AllOne class:
        //  + AllOne() Initializes the object of the data structure.
        //  + inc(String key) Increments the count of the string key by 1. If key does not exist in the data structure, insert it with count 1.
        //  + dec(String key) Decrements the count of the string key by 1. If the count of key is 0 after the decrement, remove it from the data structure. It is guaranteed that key exists in the data structure before the decrement.
        //  + getMaxKey() Returns one of the keys with the maximal count. If no element exists, return an empty string "".
        //  + getMinKey() Returns one of the keys with the minimum count. If no element exists, return an empty string "".
        //- Note that each function must run in O(1) average time complexity.
        //
        // Idea
        //1.
        //1.0,
        // Method-1:
        //- Constraint
        //1 <= key.length <= 10
        //key consists of lowercase English letters.
        //It is guaranteed that for each call to dec, key is existing in the data structure.
        //At most 5 * 10^4 calls will be made to inc, dec, getMaxKey, and getMinKey.
        //  + Key có length <=10:
        //      + Không lớn
        //
        //- Brainstorm
        //- Để lấy được min count:
        //  ==> Thường sẽ mất O(log(n))
        //- Nhưng mà ở đây ==> Chỉ có thao tác (increase, decrease value):
        //  + Không phải random value
        //  ==> WRONG IDEA vì:
        //  - Hoàn toàn có thể ntn:
        //      + 1->2->(2)->4
        //      + 1->(2)->2->4
        //      + 1->2->4->4
        //- increase value:
        //  + Đẩy node về phía trước 1 unit
        //- decrease value:
        //  + Đẩy node về phía sau 1 unit
        //  ==> WRONG IDEA
        //- Đoạn này có thể increase or decrease (2) ở bất cứ đâu:
        //  + Nếu (2) ở giữa mà loop theo 2 ==> 4 thì:
        //      + Time: O(n)
        //- Đoạn này ta increase(2 -> 3) có thể search 3 or 4
        //  + Để dễ thì 2 ta sẽ search 3 được không?
        //  ==> WRONG IDEA vì:
        //+ Có thể có gap từ 2 -> 4 luôn.
        //  + 1->2->(2)->4
        //  + 1->(2)->2->4
        //  + 1->2->4->4
        //==> 2 ta cần xác định được tail để increase => 3
        //==> 2 ta cũng cần xác định được head để decrease => 1
        //- Nếu dùng map[2,(head,tail]]
        //- Increase sẽ tằng tư 1 -> 100:
        //  + Không có chuyện insert random ở giữa
        //- increase(x):
        //  + Nếu val_x+1 không có thì insert thêm vào ==> Tail
        //  <> decrease cũng thế ==> Head
        //- Cần phải có global (head, tail)
        //
        HashSet<String> set=new HashSet<>();
        set.add("cde");
        set.add("abc");
        set.add("12312");
        //Set không có order
        System.out.println(set);
        //
        E117_AllOoneDataStructure_refer allOne=new E117_AllOoneDataStructure_refer();
//        allOne.inc("hello");
//        allOne.inc("hello");
//        System.out.println(allOne.getMaxKey()); // return "hello"
//        System.out.println(allOne.getMinKey()); // return "hello"
//        allOne.inc("leet");
//        System.out.println(allOne.getMaxKey()); // return "hello"
//        System.out.println(allOne.getMinKey()); // return "leet"
        //
        //- Issue:
        //- Không có giá trị:
        //  + return ""
        //
        //[[],["a"],["b"],["b"],["c"],["c"],["c"],["b"],["b"],[],["a"],[],[]]
        //["AllOne","inc","inc","inc","inc","inc","inc","dec", "dec","getMinKey","dec","getMaxKey","getMinKey"]
        //Output: [null,null,null,null,null,null,null,null,null,"b",null,"c","b"]
        //Expected: [null,null,null,null,null,null,null,null,null,"a",null,"c","c"]
        //
//        allOne.inc("a");//a:1
//        allOne.inc("b");//a:1,b:1
//        allOne.inc("b");//a:1,b:2
//        allOne.inc("c");//a:1,b:2,c:1
//        allOne.inc("c");//a:1,b:2,c:2
//        allOne.inc("c");//a:1,b:2,c:3
//        allOne.dec("b");//a:1,b:1,c:3
//        allOne.dec("b");//a:1,c:3
//        System.out.println(allOne.getMinKey());//a
//        allOne.dec("a");//c:3
//        System.out.println(allOne.getMaxKey());//c
//        System.out.println(allOne.getMinKey());//c
        //
        //- Nếu tiếp tục làm cách trên:
        //  ===> Khó debug + k có pattern
        //
        //1.1, Optimization
        //1.2, Complexity
        //- N is the number of key
        //- Space: O(n)
        //- Time: O(1)
        //
        System.out.println(allOne.getMinKey());
        System.out.println(allOne.getMaxKey());
        //
        //#Reference:
        //822. Card Flipping Game
        //2357. Make Array Zero by Subtracting Equal Amounts
        //2856. Minimum Array Length After Pair Removals
        //2935. Maximum Strong Pair XOR II
        //1713. Minimum Operations to Make a Subsequence
        //819. Most Common Word
    }
}
