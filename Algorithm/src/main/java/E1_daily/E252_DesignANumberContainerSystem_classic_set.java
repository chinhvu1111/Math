package E1_daily;

import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.TreeSet;

public class E252_DesignANumberContainerSystem_classic_set {

    static class NumberContainers {
        public HashMap<Integer, Integer> boxMap;
        public HashMap<Integer, TreeSet<Integer>> valToIndices;

        public NumberContainers() {
            boxMap = new HashMap<>();
            valToIndices = new HashMap<>();
        }

        public void change(int index, int number) {
            if (boxMap.containsKey(index)) {
                int prevVal = boxMap.get(index);
                TreeSet<Integer> curSet = valToIndices.getOrDefault(prevVal, new TreeSet<>());
                //Time: O(log(n))
                curSet.remove(index);
            }
            TreeSet<Integer> curSet = valToIndices.getOrDefault(number, new TreeSet<>());
            //Time: O(log(n))
            boxMap.put(index, number);
            curSet.add(index);
            valToIndices.put(number, curSet);
        }

        public int find(int number) {
            if (!valToIndices.containsKey(number) || valToIndices.get(number).isEmpty()) {
                return -1;
            }
            //Time: O(1)
            return valToIndices.get(number).first();
        }
    }

    static class NumberContainers1 {
        public HashMap<Integer, Integer> boxMap;
        public HashMap<Integer, PriorityQueue<Integer>> valToIndices;

        public NumberContainers1() {
            boxMap = new HashMap<>();
            valToIndices = new HashMap<>();
        }

        public void change(int index, int number) {
            PriorityQueue<Integer> curSet = valToIndices.getOrDefault(number, new PriorityQueue<>());
            //Time: O(log(n))
            boxMap.put(index, number);
            curSet.add(index);
            valToIndices.put(number, curSet);
        }

        public int find(int number) {
            if (!valToIndices.containsKey(number) || valToIndices.get(number).isEmpty()) {
                return -1;
            }
            PriorityQueue<Integer> curIndices = valToIndices.get(number);
            //Time: O(1)
            while (!curIndices.isEmpty()) {
                int curMinIndex = curIndices.peek();
                if (boxMap.get(curMinIndex) == number) {
                    return curMinIndex;
                }
                curIndices.poll();
            }
            return -1;
        }
    }

    public static void main(String[] args) {
        //** Requirement
        //- Design (a number container system) that can do the following:
        //  + Insert or Replace a number at the given index in the system.
        //  + Return (the smallest index) for (the given number) in the system.
        //- Implement the NumberContainers class:
        //  + NumberContainers() Initializes the number container system.
        //  + void change(int index, int number)
        //      + Fills the container at (index) with (the number).
        //      If there is already a number at that index, (replace it).
        //  + int find(int number)
        //      + Returns (the smallest index) for the given number,
        //      + or -1 if there is no index that is filled by number in the system.
        //
        //Example 1:
        //
        //Input
        //["NumberContainers", "find", "change", "change", "change", "change", "find", "change", "find"]
        //[[], [10], [2, 10], [1, 10], [3, 10], [5, 10], [10], [1, 20], [10]]
        //Output
        //[null, -1, null, null, null, null, 1, null, 2]
        //
        //Explanation
        //NumberContainers nc = new NumberContainers();
        //nc.find(10); // There is no index that is filled with number 10. Therefore, we return -1.
        //nc.change(2, 10); // Your container at index 2 will be filled with number 10.
        //nc.change(1, 10); // Your container at index 1 will be filled with number 10.
        //nc.change(3, 10); // Your container at index 3 will be filled with number 10.
        //nc.change(5, 10); // Your container at index 5 will be filled with number 10.
        //nc.find(10); // Number 10 is at the indices 1, 2, 3, and 5. Since the smallest index that is filled with 10 is 1, we return 1.
        //nc.change(1, 20); // Your container at index 1 will be filled with number 20. Note that index 1 was filled with 10 and then replaced with 20.
        //nc.find(10); // Number 10 is at the indices 2, 3, and 5. The smallest index that is filled with 10 is 2. Therefore, we return 2.
        //
        //** Idea
        //1.
        //1.0,
        //- Method-1:
        //+ Constraints:
        //1 <= index, number <= 10^9
        //  + index<=10^9 ==> HashMap
        //At most 10^5 calls will be made in total to change and find.
        //  + Complexity of the find method: O(1)
        //
        //- Brainstorm
        //- arr = [3,2,3,10]
        //
        //
        //1.1, Special cases
        //
        //1.2, Optimization (Priority queue)
        //- We can use the priority queue rather than the TreeSet
        //* Main points:
        //  + For (each index) that has (only 1) value
        //  + For (each value) can put into (multiple boxes)
        //
        //- Change method:
        //  + We need to remove the (previous value) out of the set of (the index)
        //- For find method:
        //  + For each index in MIN HEAP:
        //      + We return it when arr[min_index] = current value
        //      <> pop()
        //* Main points:
        //  + For each index ==> 1 value
        //  ==> We only return index <=>
        //      (current value == index) : Means this value is (still in the list) of (the index)
        //
        //
        //1.3, Complexity
        //- Space: O(n)
        //- Time:
        //  + Change: O(log(n))
        //  + find: O(1)
        //
        NumberContainers nc = new NumberContainers();
        System.out.println(nc.find(10)); // There is no index that is filled with number 10. Therefore, we return -1.
        nc.change(2, 10); // Your container at index 2 will be filled with number 10.
        nc.change(1, 10); // Your container at index 1 will be filled with number 10.
        nc.change(3, 10); // Your container at index 3 will be filled with number 10.
        nc.change(5, 10); // Your container at index 5 will be filled with number 10.
        System.out.println(nc.find(10)); // Number 10 is at the indices 1, 2, 3, and 5. Since the smallest index that is filled with 10 is 1, we return 1.
        nc.change(1, 20); // Your container at index 1 will be filled with number 20. Note that index 1 was filled with 10 and then replaced with 20.
        System.out.println(nc.find(10)); // Number 10 is at the indices 2, 3, and 5. The smallest index that is filled with 10 is 2. Therefore, we return 2.
        //
        //- What is sorted set in java?
        //  + Which is usually implemented using some kind of balanced BST (e.g. in C++ a red-black tree), finding the minimum element is O(logn)
        //  + In a balanced BST like C++'s set (typically a red-black tree),
        //  finding the minimum would take ( O(\log n) ) by traversing down the leftmost path.
        //  However, set.begin() provides direct access to the smallest element in ( O(1) ) because the tree internally maintains a pointer to it.
        //  The real ( O(\log n) ) cost happens during insertions and deletions, not when retrieving the minimum.
        //
        NumberContainers1 nc1 = new NumberContainers1();
        System.out.println(nc1.find(10)); // There is no index that is filled with number 10. Therefore, we return -1.
        nc1.change(2, 10); // Your container at index 2 will be filled with number 10.
        nc1.change(1, 10); // Your container at index 1 will be filled with number 10.
        nc1.change(3, 10); // Your container at index 3 will be filled with number 10.
        nc1.change(5, 10); // Your container at index 5 will be filled with number 10.
        System.out.println(nc1.find(10)); // Number 10 is at the indices 1, 2, 3, and 5. Since the smallest index that is filled with 10 is 1, we return 1.
        nc1.change(1, 20); // Your container at index 1 will be filled with number 20. Note that index 1 was filled with 10 and then replaced with 20.
        System.out.println(nc1.find(10)); // Number 10 is at the indices 2, 3, and 5. The smallest index that is filled with 10 is 2. Therefore, we return 2.
        //#Reference:
        //1845. Seat Reservation Manager
        //2353. Design a Food Rating System
    }
}
