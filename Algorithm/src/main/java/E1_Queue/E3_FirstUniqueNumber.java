package E1_Queue;

import java.util.*;

public class E3_FirstUniqueNumber {
    public Deque<Integer> uniqueVals;
    public HashMap<Integer, Integer> setVals;
    public HashSet<Integer> hashSetVals;

    public LinkedHashSet<Integer> uniqueSet;

//    public E3_FirstUniqueNumber(int[] nums) {
//        setVals=new HashMap<>();
//        uniqueVals=new LinkedList<>();
//        for(int e: nums){
//            if(setVals.containsKey(e)){
//                setVals.put(e, setVals.get(e)+1);
//                uniqueVals.remove((Integer)e);
//            }else{
//                setVals.put(e, 1);
//                uniqueVals.addLast(e);
//            }
//            //Remove trong linked list nếu không có reference:
//            //+ Search: O(n)
//            //+ Remove: O(1)
//        }
//    }

    public E3_FirstUniqueNumber(int[] nums) {
        uniqueSet=new LinkedHashSet<>();
        hashSetVals=new HashSet<>();
        for(int e: nums){
            if(hashSetVals.contains(e)){
                uniqueSet.remove(e);
            }else{
                uniqueSet.add(e);
            }
            hashSetVals.add(e);
            //Remove trong linked list nếu không có reference:
            //+ Search: O(n)
            //+ Remove: O(1)
        }
    }

    public int showFirstUnique() {
        while(!uniqueVals.isEmpty()&&setVals.get(uniqueVals.peekFirst())>1){
            uniqueVals.pollFirst();
        }
        if(setVals.size()==0||uniqueVals.size()==0){
            return -1;
        }
        return uniqueVals.peekFirst();
    }

    public void add(int value) {
        if(setVals.containsKey(value)){
            setVals.put(value, setVals.get(value)+1);
        }else{
            setVals.put(value, 1);
            uniqueVals.addLast(value);
        }
    }

    public int showFirstUniqueRefactor() {
        if(uniqueSet.size()==0){
            return -1;
        }
        return uniqueSet.stream().iterator().next();
    }

    public void addRefactor(int value) {
        if(hashSetVals.contains(value)){
            uniqueSet.remove(value);
        }else{
            hashSetVals.add(value);
            uniqueSet.add(value);
        }
    }

    public static void main(String[] args) {
        //* Requirement
        //- Given the queue of integer
        //* We need to build the class to retrieve the (first unique integer) in the queue
        //====
        //Implement the FirstUnique class:
        //
        //FirstUnique(int[] nums) Initializes the object with the numbers in the queue.
        //+ int showFirstUnique()
        // returns the value of the first unique integer of the queue, and returns -1 if there is no such integer.
        //+ void add(int value) insert value to the queue.
        //
        //** Idea
        //* Method 1:
        //1.
        //1.0,
        //- Constraint
        //1 <= nums.length <= 10^5
        //1 <= nums[i] <= 10^8
        //1 <= value <= 10^8
        //At most 50000 calls will be made to showFirstUnique and add.
        //- (Số khá lớn 10^8)
        //==> Số lượng distinct k ảnh hg
        //
        //- Brainstorm
        //Ex:
        //1,2,3,1,4
        //show : 2
        //- 1 số sẽ có 2 states:
        //+ Unique
        //+ Không còn unique
        //
        //** NOTE:
        //- LINKED LIST:
        //Remove trong linked list nếu không có reference:
        //+ Search: O(n)
        //+ Remove: O(1)
        //- Bài này dùng LinkedList remove --> Là nó sẽ loop search
        //  + TIMEOUT
        //- Nên sẽ dùng queue + hashMap count value -->
        // + Lúc show thì mới poll first những element có count>1 ra.
        // + Lúc add thì ta tăng value của count lên.
        //
        //2. Dùng linkedHashSet
        //- Brainstorm
        //
        //- Ta vẫn sẽ cần phải dùng 2 set
        //  + LinkedHashSet để quy định order
        //  + HashSet để check trùng value khi add:
        //      + Ta không thể lấy ordered list unique value của array nums ban đầu nếu chỉ dùng 1 set.
        //      + Sau khi 1 value remove từ linkedhashMap do trùng (Add duplicate) thì value đó cũng cần cache lại để không add được nữa.
        //          Nếu chỉ dùng linkedhashMap --> remove e --> add(e) sẽ thêm được (SAI)
        //* KINH NGHIỆM:
        //- Nếu list không có get(index=0)
        //  - Ta sẽ dùng iterator().next(): Để lấy value đầu tiên fast.
        //
        //2.1, Optimization
        //2.2, Complexity:
        //- Space : O(N)
        //- Time :
        //  + Add : O(1)
        //  + Show : O(1)
        //
        //
        //#Reference:
        //1402. Reducing Dishes
        //2892. Minimizing Array After Replacing Pairs With Their Product
        //622. Design Circular Queue
        //2780. Minimum Index of a Valid Split
        //1713. Minimum Operations to Make a Subsequence
        //1202. Smallest String With Swaps
    }
}
