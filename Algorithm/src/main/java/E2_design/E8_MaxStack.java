package E2_design;

import javafx.util.Pair;

import java.util.*;

public class E8_MaxStack {

    public static class Node{
        int key;
        int value;
        public Node(int key, int value){
            this.key=key;
            this.value=value;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "key=" + key +
                    ", value=" + value +
                    '}';
        }
    }

    public static class MaxStack {
        TreeSet<Integer> treeSet;
        Stack<Pair<Integer, Integer>> stack;
        HashMap<Integer, Integer> mapCount;

        public MaxStack() {
            treeSet=new TreeSet<>((a,b) -> b-a);
            stack=new Stack<>();
            mapCount=new HashMap<>();
        }

        public void push(int x) {
            if(!mapCount.containsKey(x)){
                mapCount.put(x, 1);
                treeSet.add(x);
            }else{
                mapCount.put(x, mapCount.getOrDefault(x, 0)+1);
            }
            stack.add(new Pair<>(x, mapCount.get(x)));
        }

        public int pop() {
            if(stack.isEmpty()){
                return -1;
            }
            while (!stack.isEmpty()&&(!mapCount.containsKey(stack.peek().getKey())||stack.peek().getValue()>mapCount.get(stack.peek().getKey()))){
                stack.pop();
            }
            Pair<Integer, Integer> val=stack.pop();
            int curCount=mapCount.get(val.getKey());

            if(curCount==1){
                mapCount.remove(val.getKey());
                treeSet.remove(val.getKey());
            }else{
                mapCount.put(val.getKey(), curCount-1);
            }
            return val.getKey();
        }

        public int top() {
            while (!stack.isEmpty()&&(!mapCount.containsKey(stack.peek().getKey())||stack.peek().getValue()>mapCount.get(stack.peek().getKey()))){
                stack.pop();
            }
            if(!stack.isEmpty()){
                return stack.peek().getKey();
            }
            return -1;
        }

        public int peekMax() {
            if(!treeSet.isEmpty()){
                return treeSet.first();
            }
            return -1;
        }

        public int popMax() {
            int val=treeSet.first();
            int curCount=mapCount.get(val);

            if(curCount==1){
                mapCount.remove(val);
                treeSet.remove(val);
            }else{
                mapCount.put(val, curCount-1);
            }
            return val;
        }
    }

    public static class MaxStackAllTreeSet {
        TreeSet<int[]> treeSet;
        TreeSet<int[]> stack;
        int index;

        public MaxStackAllTreeSet() {
            treeSet=new TreeSet<>((a, b) -> (a[0]!=b[0]?b[0]-a[0]:b[1]-a[1]));
            stack=new TreeSet<>((a, b) -> (b[0]-a[0]));
            index=0;
        }

        public void push(int x) {
            treeSet.add(new int[]{x, index});
            stack.add(new int[]{index, x});
            index++;
        }

        public int pop() {
            int[] popVal=stack.pollFirst();
            treeSet.remove(new int[]{popVal[1], popVal[0]});
            return popVal[1];
        }

        public int top() {
            return stack.first()[1];
        }

        public int peekMax() {
            return treeSet.first()[0];
        }

        public int popMax() {
            int[] maxVal=treeSet.pollFirst();
            stack.remove(new int[]{maxVal[1], maxVal[0]});
            return maxVal[0];
        }
    }

    public static void main(String[] args) {
        // Requirement
        //* Design (a max stack data structure) that supports the stack operations and supports finding the (stack's maximum element).
        //Implement the MaxStack class:
        //
        //+ MaxStack() Initializes the stack object.
        //+ void push(int x) Pushes element x onto the stack.
        //+ int pop() Removes the element on top of the stack and returns it.
        //+ int top() Gets the element on the top of the stack without removing it.
        //+ int peekMax() Retrieves the maximum element in the stack without removing it.
        //+ int popMax() Retrieves the maximum element in the stack and removes it. If there is more than one maximum element, only remove the top-most one.
        //- Time requirement:
        //+ peekMax() : O(1) time
        //+ Push/ pop : O(log(n)) time
        //
        //* You must come up with a solution that supports O(1) for each top call and O(logn) for each other call.
        //
        // Idea
        //1.
        //1.0,
        //- Constraint:
        //-10^7 <= x <= 10^7
        //At most 10^5 calls will be made to push, pop, top, peekMax, and popMax.
        //There will be at least one element in the stack when pop, top, peekMax, or popMax is called.
        //--> 10^5 calls : chỉ có thể take time O(n*log(n))
        //
        //- Brainstorm
        //- Design heap sao cho Max heap sẽ tốn O(1) để get max value
        //
        //Operation	Time Complexity
        //+ Insertion: O(log n) - adding a new element to a heap requires comparing it to its parent and swapping it up the tree
        // until the heap property is restored, which takes O(log n) time.
        //+ Deletion of the maximum (or minimum): O(log n) - removing the maximum (or minimum) element from a heap requires
        // replacing it with the last element in the heap, and then percolating this element down the tree until the heap property is restored,
        // which takes O(log n) time.
        //+ Finding the maximum (or minimum): O(1) - the maximum (or minimum) element is always stored at the root of the heap,
        // so finding it takes constant time.
        //
        //Push/ Pop : O(log(n))
        //- Cái này ta cần phải tìm max value và đẩy lên đầu
        //
        //- Để mất O(log(n)) tìm max thì:
        //  + Sẽ phải mất O(log(n)) để find : binary search
        //  + O(1) để remove + add lại vào peek() (last)
        //
        //- stack:
        //add : 5,1,9,2
        //+ add(5), stack: 5
        //+ add(1), stack: 1,5
        //+ add(1), stack: 1,5,9
        //+ add(1), stack: 1,2,5,9
        //==> Nếu làm như thế này thì mất 2 steps:
        //  + Find position for new value : O(log(n)) time
        //      + List phải là array thì mới có thể get value O(1) được
        //  + Insert the new value to the specific position : Sẽ cần take O(n) time
        //
        //- Priority queue thực tế follow rule:
        //+ Các phần tử trong queue khi pop/poll/remove vẫn follow order khi push vào
        //  Ex: push: 5,1,2,3 ==> poll: return 3,2,1,5 (Theo queue rule LIFO)
        //+ Nhưng peekMax() thì ta sẽ lấy được max value trong all element
        //
        //- Implementation
        //- Ta cần 1 stack để keep order
        //- 1 giá trị max để return peekMax()
        //- Khi popMax() <=> remove peekMax(): O(log(n))
        //  + Nếu dùng lib : treeSet() remove
        //  + Nếu tự implement ???
        //
        //- Khi pop() <=> remove peek()
        //  + Cái này ta sẽ pop() ở queue + pop ở treeSet
        //- peekMax(): Lấy max ở treeSet là được
        //- peek() : thì lấy ở queue
        //- Nếu push các phần tử same value --> pop()/ popMax() sẽ chỉ pop ở tree khi:
        //  + count==0
        //- Vì pair không thể thay đổi giá trị:
        //  + Việc thay đổi value của reference object làm code không clear ==> Ta sẽ dùng hashMap (val, count) để kiểm soát các value bị duplicate
        //
        //* LinkedList remove(Object) :
        //- Time complexity
        //+ new LinkedList().remove(null);
        //- Bản thân node không có pointer (next, prev) ==> Nó sẽ luôn take O(N) to remove(object)
        //=========== Code : O(N) time
//        for (LinkedList.Node<E> x = first; x != null; x = x.next) {
//            if (o.equals(x.item)) {
//                unlink(x);
//                return true;
//            }
//        }
        //- Nếu add new int[] vào hashMap thì nó có bị duplicate hay không?
        //- Với HashSet
        //+ Nếu add(new int[]{}) ==> Nó sẽ bị duplicate
        //==> Cái này sẽ không có (cache pool)
        //+ Nếu add pair thì với HashSet:
        //  + Cache lại pair ==> Nhận là cùng element
        //- Với TreeSet:
        //+ Nếu ta define comparator sort theo cái gì ==> Nó sẽ nhận key theo cái đó
        //  ==> Về cơ bản :
        //  + TreeSet check exists trên KEY ==> Còn value của Pair điền gì cũng được or even update.
        //
        HashSet<int[]> set=new HashSet<>();
        set.add(new int[]{2,3});
        System.out.println(set.contains(new int[]{2,3}));
        set.add(new int[]{2,3});
        System.out.println(set.size());
        //- Thử với Pair (HashSet)
        HashSet<Pair<Integer, Integer>> setPair=new HashSet<>();
        setPair.add(new Pair<>(1,2));
        //- Thử element giống y hệt
        System.out.printf("Thử element giống y hệt: %s\n",setPair.contains(new Pair<>(1,2)));
        //- Thử element giống key khác value
        System.out.printf("Thử element giống key khác value: %s\n",setPair.contains(new Pair<>(1,3)));
        setPair.add(new Pair<>(1,2));
        System.out.println(setPair.size());
        //- Thử với Pair (TreeSet)
        TreeSet<Pair<Integer, Integer>> setPairTree=new TreeSet<>((a, b) -> a.getKey()-b.getKey());
        setPairTree.add(new Pair<>(1,2));
        //- Thử element giống y hệt
        System.out.printf("Thử element giống y hệt (Tree): %s\n",setPairTree.contains(new Pair<>(1,2)));
        //- Thử element giống key khác value
        System.out.printf("Thử element giống key khác value (Tree): %s\n",setPairTree.contains(new Pair<>(1,3)));
        setPairTree.add(new Pair<>(1,2));
        System.out.println(setPairTree.size());
        System.out.println(setPairTree);
        //Nếu update (1,3) thì (1,2) có được update hay không
        //+ Không update => Khi add nó sẽ check trùng
        setPairTree.add(new Pair<>(1,3));
        //Thay đổi bằng tay giá trị được không
        Pair<Integer, Integer> p=setPairTree.ceiling(new Pair<>(1,3));
        //Pair không thay đổi được giá trị ==> Dùng custom class
        System.out.println(setPairTree);
        //
        System.out.println();
        System.out.println("TREE NODE: ");
        //- Thử với Node (TreeSet)
        TreeSet<Node> setNodeTree=new TreeSet<>((a, b) -> a.key-b.key);
        setNodeTree.add(new Node(1,2));
        //- Thử element giống y hệt
        System.out.printf("Thử element giống y hệt (Tree): %s\n",setNodeTree.contains(new Node(1,2)));
        //- Thử element giống key khác value
        System.out.printf("Thử element giống key khác value (Tree): %s\n",setNodeTree.contains(new Node(1,3)));
        setNodeTree.add(new Node(1,2));
        System.out.println(setNodeTree.size());
        System.out.println(setNodeTree);
        //Nếu update (1,3) thì (1,2) có được update hay không
        //+ Không update => Khi add nó sẽ check trùng
        setNodeTree.add(new Node(1,3));
        //Thay đổi bằng tay giá trị được không
        Node node=setNodeTree.ceiling(new Node(1,3));
        node.value=3;
        //Dùng custom class thay đổi giá trị
        System.out.println(setNodeTree);

        MaxStack maxStack=new MaxStack();
        maxStack.push(5);
        maxStack.push(1);
        maxStack.push(5);
        //stack : 5,1,5
        //tree: 5,1
        //hashCount: {5,2},{1,1}
        //5
        System.out.println(maxStack.top());
        //max=5
        //stack : 5,1,5
        //tree: 5,1
        //hashCount: {5,1},{1,1}, {5,2}=>{5,1}
        System.out.println(maxStack.popMax());
        System.out.println(maxStack.top());
        System.out.println(maxStack.peekMax());
        System.out.println(maxStack.pop());
        System.out.println(maxStack.top());
        //
        //- Sẽ có case mà muốn remove element ở trong hash thì take O(1) nhưng cần phải remove trong stack
        //+ Ta sẽ đẩy phần remove vào pop function.
        //Ex: stack sẽ lưu Pair(val, count)
        //  + Nếu count trong stack > count trong map ==> remove khỏi stack.
        //--> Time của top sẽ không hẳn là O(1) ==> O(const) => O(n)
        //
        //1.1, Optimization
        //
        //1.2, Complexity
        //+ void push(int x) : O(log(n))
        //+ int pop() : O(log(n))
        //+ int top() : O(const) -> O(n)
        //+ int peekMax() : O(1)
        //+ int popMax() : O(log(n))
        //
        //2, Chỉ sử dụng TreeSet không sử dụng stack
        //2.0, Idea:
        //- TreeSet khi add các value vào thì nó sẽ tự sort
        //** Tuy nhiên:
        //- TreeSet sẽ trở thành stack nếu:
        //+ TreeSet được sort theo index
        //  + Index sẽ tăng khi ta add new value
        //
        //- Idea
        //- stack: TreeSet<int[]>(index, val)
        //  + Ta có thể remove phần tử được dựa trên (unique index)
        //- treeSet: TreeSet<int[]>(val, index)
        //  + Ta có thể lấy max value
        //* Question:
        //- Vậy làm sao để có thể xoá phần tử trong stack qua count mà treeSet ở đây ta đang sort theo value
        //** key ở trong key set (nhận như thế nào):
        // TreeSet có thể thay đổi được giá trị hashSet thành cả key và value
        //- Miễn là khi declare ta cần if else trên cả 2 phần (key, value)
        //- Answer:
        //  + Ta sẽ thay đổi predicate để treeSet nhận 2 node: (same key+ khác value) ==> Duplicate được.
        //
        //2.1, Optimization
        //2.2, Complexity
        //+ void push(int x) : O(log(n))
        //+ int pop() : O(log(n))
        //+ int top() : O(1)
        //+ int peekMax() : O(1)
        //+ int popMax() : O(log(n))
        //
        //3. Heap + lazy update
        //3.0, Idea
        //- Brainstorm
        //- Stack<>(val, index)
        //- PriorityQueue<>(val, index)
        //- Remove : hash(index) (incremental index sẽ luôn unique)
        //- Khi pop(), check các element from first -> last:
        //  + Phần tử nào (exists in remove set) ==> pop()
        //  + Add index to remove set ==> Để ta có thể delete nó đi ở (priority queue nếu call peekMax()/ popMax())
        //- Removed check sẽ luôn được đặt ở mỗi method:
        //  + Để delete phần tử --> Vì ta đang sử dụng toàn bộ là queue
        //* Sử dụng cái gì --> Shorten cái đó (Chứ không update tự động)
        //==> Lazy update.
        //3.1, Optimization
        //3.2, Complexity:
        //- N is the maximum size of the heap, stack, and removed.
        //+ void push(int x) : O(log(n))
        //+ int pop() : O(log(n) -> n)
        //+ int top() : O(1 -> n)
        //+ int peekMax() : O(1 -> log(n) -> n)
        //+ int popMax() : O(1 -> log(n) -> n)
        //* Về chung chung ==> Thì time giữ nguyên do đẩy phần này qua phần kia thực hiện thôi.
        //
        //- Space : O(N) for all.
        //* Bài này nếu dùng (doubly linked list) thay cho stack
        // ==> Có thể remove trực tiếp node được.
        //
        TreeSet<Integer> treeSet1=new TreeSet<>();
        treeSet1.add(4);
        treeSet1.add(3);
        treeSet1.add(10);
        treeSet1.add(1);
        System.out.println(treeSet1);
        System.out.println("Tree Array with predicate(key)");
        TreeSet<int[]> treeSetArr=new TreeSet<>((a, b) -> (b[0]-a[0]));
        treeSetArr.add(new int[]{1,2});
        treeSetArr.add(new int[]{1,2});
        System.out.println(treeSetArr.size());
        System.out.println("Tree Array with predicate(key, value)");
        TreeSet<int[]> treeSetArr1=new TreeSet<>((a, b) -> (b[0]!=a[0]?b[0]-a[0]:b[1]-a[1]));
        treeSetArr1.add(new int[]{1,2});
        treeSetArr1.add(new int[]{1,3});
        System.out.println(treeSetArr1.size());
        //
        System.out.println("Only use treeSet");
        MaxStack maxStack1=new MaxStack();
        maxStack1.push(5);
        maxStack1.push(1);
        maxStack1.push(5);
        System.out.println(maxStack1.top());
        System.out.println(maxStack1.popMax());
        System.out.println(maxStack1.top());
        System.out.println(maxStack1.peekMax());
        System.out.println(maxStack1.pop());
        System.out.println(maxStack1.top());
        //
        //#Reference:
        //732. My Calendar III
        //364. Nested List Weight Sum II
        //2043. Simple Bank System
        //699. Falling Squares
        //2197. Replace Non-Coprime Numbers in Array
        //2751. Robot Collisions
        //2764. Is Array a Preorder of Some Binary Tree
        //2612. Minimum Reverse Operations
        //707. Design Linked List
    }
}
