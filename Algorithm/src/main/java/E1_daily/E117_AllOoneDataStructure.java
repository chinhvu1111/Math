package E1_daily;

import javafx.util.Pair;

import java.util.HashMap;
import java.util.HashSet;

public class E117_AllOoneDataStructure {

    public class Node{
        int value;
        String key;
        Node prev;
        Node next;

        @Override
        public int hashCode() {
            return this.key.hashCode();
        }

        public Node(String key, int value){
            this.key=key;
            this.value=value;
        }

    }

    public HashMap<String, Node> mapCount;
    public Node head;
    public Node tail;
    public HashMap<Integer, Pair<Node, Node>> valPointers;
    //Để remove được value frequency
    //--> Cần phải lưu set string:
    //  + Frequency chỉ delete ==> set is empty
    public HashMap<Integer, HashSet<String>> valStrs;

    public E117_AllOoneDataStructure() {
        //1->2->(3)->4
        mapCount=new HashMap<>();
        valPointers=new HashMap<>();
        valStrs=new HashMap<>();
    }

    public void inc(String key) {
        if(head==null){
            head=new Node(key, 1);
            tail=head;
            mapCount.put(head.key, head);
            valPointers.put(1, new Pair<>(head, tail));
            HashSet<String> newStrs=new HashSet<>();
            newStrs.add(head.key);
            valStrs.put(1, newStrs);
        }else {
            Node curNode = mapCount.getOrDefault(key, new Node(key, 0));
            curNode.value++;
//            if(valPointers.containsKey(curNode.value)){
            if(valPointers.containsKey(curNode.value)){
                Pair<Node, Node> headTail=valPointers.get(curNode.value);
                Pair<Node, Node> curHeadTail = valPointers.get(curNode.value-1);
                //Remove out of the current stage
                if(curHeadTail!=null&&curNode==curHeadTail.getKey()){
                    valPointers.put(curNode.value, new Pair<>(curNode.next, curHeadTail.getValue()));
                }else if(curHeadTail!=null&&curNode==curHeadTail.getValue()){
                    valPointers.put(curNode.value, new Pair<>(curHeadTail.getKey(), curHeadTail.getValue().prev));
                }
                //Remove curNode
                //prev <-> curNode <-> nextNode
                Node prevCurNode = curNode.prev;
                Node nextCurNode = curNode.next;
                if(prevCurNode!=null){
                    prevCurNode.next=nextCurNode;
                }
                if(nextCurNode!=null){
                    nextCurNode.prev = prevCurNode;
                }
                // prevPrevHead <-> prevHead <-> head
                // curNode <-> tail
                Node curHead = headTail.getKey();//head
                Node prevCurHead=curHead.prev;
                Node prevPrevCurNode = null;
                if(prevCurHead!=null){
                    prevPrevCurNode = prevCurHead.prev;
                }
                curHead.prev=curNode;
                curNode.prev=prevPrevCurNode;
                curNode.next=curHead;
                if(prevPrevCurNode!=null){
                    prevPrevCurNode.next=curNode;
                }
                if(curHead==head){
                    head=curNode;
                }
                valPointers.put(curNode.value, new Pair<>(curNode, headTail.getValue()));
            }else{
                if(tail!=curNode&&curNode.value>=tail.value){
                    //tail -> curNode
                    tail.next=curNode;
                    curNode.prev=tail;
                    curNode.next=null;
                    tail=curNode;
                }else if(head!=curNode&&curNode.value<=head.value){
                    //curNode -> head
                    head.prev=curNode;
                    curNode.next=head;
                    curNode.prev=null;
                    head=curNode;
                }
                valPointers.put(curNode.value, new Pair<>(curNode, curNode));
            }
            mapCount.put(curNode.key, curNode);
            //Add
            HashSet<String> newStrs = valStrs.getOrDefault(curNode.value, new HashSet<>());
            newStrs.add(curNode.key);
            valStrs.put(curNode.value, newStrs);
            //Remove
            HashSet<String> oldStrs = valStrs.get(curNode.value-1);
            if(oldStrs!=null){
                oldStrs.remove(curNode.key);
//                valStrs.put(curNode.value-1, oldStrs);
                if(oldStrs.isEmpty()){
                   valStrs.remove(curNode.value-1);
                   valPointers.remove(curNode.value-1);
                }
            }
        }
    }

    public void dec(String key) {
        if(head==null){
            head=new Node(key, -1);
            tail=head;
            mapCount.put(head.key, head);
            valPointers.put(-1, new Pair<>(head, tail));
            HashSet<String> newStrs=new HashSet<>();
            newStrs.add(head.key);
            valStrs.put(-1, newStrs);
        }else {
            Node curNode = mapCount.getOrDefault(key, new Node(key, 0));
            curNode.value--;
//            if(valPointers.containsKey(curNode.value)){
            if(valPointers.containsKey(curNode.value)){
                Pair<Node, Node> headTail=valPointers.get(curNode.value);
                Pair<Node, Node> curHeadTail = valPointers.get(curNode.value);
                //Remove out of the current stage
                if(curNode==curHeadTail.getKey()){
                    valPointers.put(curNode.value, new Pair<>(curNode.next, curHeadTail.getValue()));
                }else if(curNode==curHeadTail.getValue()){
                    valPointers.put(curNode.value, new Pair<>(curHeadTail.getKey(), curHeadTail.getValue().prev));
                }
                //Remove curNode
                //prev <-> curNode <-> nextNode
                Node prevCurNode = curNode.prev;
                Node nextCurNode = curNode.next;
                if(prevCurNode!=null){
                    prevCurNode.next=nextCurNode;
                }
                if(nextCurNode!=null){
                    nextCurNode.prev = prevCurNode;
                }
                // prevPrevTail <-> prevTail <-> tail
                // curNode <-> tail
                Node curHead = headTail.getKey();//head
                Node prevCurHead=curHead.prev;
                Node prevPrevCurNode = null;
                if(prevCurHead!=null){
                    prevPrevCurNode = prevCurHead.prev;
                }
                curHead.prev=curNode;
                curNode.prev=prevPrevCurNode;
                curNode.next=curHead;
                if(prevPrevCurNode!=null){
                    prevPrevCurNode.next=curNode;
                }
                valPointers.put(curNode.value, new Pair<>(curNode, headTail.getValue()));
            }else{
                if(tail!=curNode&&curNode.value>=tail.value){
                    tail.next=curNode;
                    curNode.prev=tail;
                    curNode.next=null;
                    tail=curNode;
                }else if(head!=curNode&&curNode.value<=head.value){
                    head.prev=curNode;
                    curNode.next=head;
                    curNode.prev=null;
                    head=curNode;
                }
                valPointers.put(curNode.value, new Pair<>(curNode, curNode));
            }
            mapCount.put(curNode.key, curNode);
            //Add
            HashSet<String> newStrs = valStrs.getOrDefault(curNode.value, new HashSet<>());
            newStrs.add(curNode.key);
            valStrs.put(curNode.value, newStrs);
            //Remove
            HashSet<String> oldStrs = valStrs.get(curNode.value+1);
            if(oldStrs!=null){
                oldStrs.remove(curNode.key);
//                valStrs.put(curNode.value-1, oldStrs);
                if(oldStrs.isEmpty()){
                    valStrs.remove(curNode.value+1);
                    valPointers.remove(curNode.value+1);
                }
            }
        }
    }

    public String getMaxKey() {
        if(tail==null){
            return null;
        }
        return tail.key;
    }

    public String getMinKey() {
        if(head==null){
            return null;
        }
        return head.key;
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
        E117_AllOoneDataStructure allOne=new E117_AllOoneDataStructure();
        /*allOne.inc("hello");
        allOne.inc("hello");
        System.out.println(allOne.getMaxKey()); // return "hello"
        System.out.println(allOne.getMinKey()); // return "hello"
        allOne.inc("leet");
        System.out.println(allOne.getMaxKey()); // return "hello"
        System.out.println(allOne.getMinKey()); // return "leet"*/
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
        allOne.inc("a");//a:1
        allOne.inc("b");//a:1,b:1
        allOne.inc("b");//a:1,b:2
        allOne.inc("c");//a:1,b:2,c:1
        allOne.inc("c");//a:1,b:2,c:2
        allOne.inc("c");//a:1,b:2,c:3
        allOne.dec("b");//a:1,b:1,c:3
        allOne.dec("b");//a:1,c:3
        System.out.println(allOne.getMinKey());//a
        allOne.dec("a");//c:3
        System.out.println(allOne.getMaxKey());//c
        System.out.println(allOne.getMinKey());//c
        //
        //- Nếu tiếp tục làm cách trên:
        //  ===> Khó debug + k có pattern
        //
        //
        //
        //#Reference:
        //822. Card Flipping Game
        //2357. Make Array Zero by Subtracting Equal Amounts
        //2856. Minimum Array Length After Pair Removals
        //
    }
}
