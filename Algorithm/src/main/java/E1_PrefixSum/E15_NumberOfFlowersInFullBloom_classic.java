package E1_PrefixSum;

import java.util.*;

public class E15_NumberOfFlowersInFullBloom_classic {

    public static int[] fullBloomFlowers(int[][] flowers, int[] people) {
        Arrays.sort(flowers, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0]-o2[0];
            }
        });
//        Arrays.sort(people);
        int m = people.length;
        //The size of flowers: n
        //The size of people: m
        //Space: O(n)
        TreeMap<Integer, Integer> setFlowers=new TreeMap<>();
        TreeSet<Integer> flowerPoints = new TreeSet<>();

        //Time: O(n)
        for(int[] f: flowers){
            if(!setFlowers.containsKey(f[0])){
                //Time: O(log(n))
                setFlowers.put(f[0], 1);
            }else{
                setFlowers.put(f[0], setFlowers.get(f[0])+1);
            }
            if(!setFlowers.containsKey(f[1]+1)){
                setFlowers.put(f[1]+1, -1);
            }else{
                setFlowers.put(f[1]+1, setFlowers.get(f[1]+1)-1);
            }
            flowerPoints.add(f[0]);
            flowerPoints.add(f[1]+1);
        }
        //Space: O(n)
        HashMap<Integer, Integer> prefixSum=new HashMap<>();
        Iterator<Integer> iter = flowerPoints.iterator();
        int sum=0;
//        System.out.println(setFlowers);
//        System.out.println(flowerPoints);

        //Time: O(m)
        while (iter.hasNext()){
            int curVal = iter.next();
            sum=sum+setFlowers.get(curVal);
            prefixSum.put(curVal, sum);
        }
//        System.out.println(prefixSum);
        //Space: O(m)
        int[] rs=new int[m];
        for(int i=0;i<m;i++){
            Integer nearFlower = setFlowers.floorKey(people[i]);
            if(nearFlower!=null){
                rs[i]=prefixSum.get(nearFlower);
            }
        }
        return rs;
    }

    public static int[] fullBloomFlowersPriorityQueue(int[][] flowers, int[] people) {
        //The size of flowers: n
        //The size of people: m
        //
        //Space: O(m)
        int[] sortedPeople = Arrays.copyOf(people, people.length);
        //Time: O(m*log(m))
        Arrays.sort(sortedPeople);

        //Time: O(n*log(n))
        Arrays.sort(flowers, Comparator.comparingInt(a -> a[0]));
        Map<Integer, Integer> personToFlowerNum = new HashMap();
        PriorityQueue<Integer> heap = new PriorityQueue();

        int i=0;
        //Time: O(m)
        for(int p: sortedPeople){
            while(i<flowers.length&&flowers[i][0]<=p){
                //Time: O(log(n))
                heap.add(flowers[i][1]);
                i++;
            }
            while (!heap.isEmpty()&&heap.peek()<p){
                heap.poll();
            }
            personToFlowerNum.put(p, heap.size());
        }

        //Space: O(m)
        int[] ans = new int[people.length];
        for (int j = 0; j < people.length; j++) {
            ans[j] = personToFlowerNum.get(people[j]);
        }

        return ans;
    }

    public static int[] fullBloomFlowersBinarySearch(int[][] flowers, int[] people) {
        TreeMap<Integer, Integer> difference = new TreeMap<>();
        difference.put(0, 0);

        for(int[] f: flowers){
            int start=f[0];
            int end=f[1]+1;
            difference.put(start, difference.getOrDefault(start, 0));
            difference.put(end, difference.getOrDefault(end, 0)-1);
        }
        List<Integer> pos=new ArrayList<>();
        List<Integer> prefix=new ArrayList<>();
        int curr=0;

        for(int key: difference.keySet()){
            //Auto increase
            pos.add(key);
            curr+=difference.get(key);
            //The current value for the current key corresponding to the index of the flowers
            prefix.add(curr);
        }
        int[] rs=new int[people.length];

        for(int i=0;i<people.length;i++){
            int j=binarySearch(pos, people[i])-1;
            rs[j]=prefix.get(j);
        }
        return rs;
    }

    public static int binarySearch(List<Integer> arr, int target) {
        int left = 0;
        int right = arr.size();
        while (left < right) {
            int mid = (left + right) / 2;
            if (target < arr.get(mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }

    public static void main(String[] args) {
        //** Requirement
        //- You are given a 0-indexed 2D integer array flowers, where flowers[i] = [start-i, end-i]
        // means (the ith flower) will be in full bloom from (start-i to end-i) (inclusive).
        //- You are also given (a 0-indexed integer array) people of size n,
        // where people[i] is the time that (the ith person) will (arrive) to see the flowers.
        //* Return (an integer array) answer of size n, where answer[i] is (the number of flowers)
        // that are in full bloom when (the ith person) arrives.
        //
        //** Idea
        //1.
        //1.0,
        //* Method-1:
        //- Constraint
        //1 <= flowers.length <= 5 * 10^4
        //flowers[i].length == 2
        //1 <= start-i <= end-i <= 10^9
        //1 <= people.length <= 5 * 10^4
        //1 <= people[i] <= 10^9
        //  + start-i, end-i <= 10^9:
        //      + Nếu ntn tức là ta khó có thể loop toàn bộ theo length được
        //
        //- Brainstorm
        //- Bài này idea tương tự bài E14
        //- Dùng prefix sum để giải:
        //- Ta sẽ sort people để có thể tính incremental
        //===> WRONG idea (Vì theo tư tưởng chưa rõ ràng ban đầu thì sẽ làm ntn)
        //  + Ta muốn tính cho mỗi person nhưng keep order cũ.
        //- Flowers ta cũng sẽ sort [a,b] theo a:
        //  + Để tính prefix sum tăng dần
        //Ex:
        //Input: flowers = [[1,6],[3,7],[9,12],[4,13]], people = [2,3,7,11]
        //Output: [1,2,2,2]
        //flowers = [[1,6],[3,7],[4,13],[9,12]]
        //  + prefix[1]+=1
        //  + prefix[6]-=1
        //people = [2,3,7,11]
        //- If we go the people[0] = 2, we want to know how many flower we get
        //  + We need to calculate the all of flower intervals with start-i is (less than or equal) to 2
        //Ex:
        //Input: flowers = [[1,6],[2,4],[3,7],[9,12],[4,13]], people = [3,7,11]
        //people[0]=3:
        //  + Calculate: [1,6],[2,4],[3,7]
        //      + [1]+[2]+[3]
        //  ==>
        //  [1],[2],[3],[4],...,[13]
        //  ==> We will split the flowers into the 1 dimension  array
        //  + Calculate the prefix sum for the array
        //- Calculate for the flower point incrementally
        //- Add the value to the treeset:
        //  + Loop people + search (lower value):
        //      + We will get the number of flowers for each people
        //
        //1.1, Optimization
        //- We still use the idea above collaborating with
        // (the binary search approach) + (using (the size of heap))
        //  + Prefix approach still will be used
        //* Solution:
        //- We use the prefix sum idea but we will store value as the prefix list
        //  + The index of this prefix corresponds to the index of the flowers after they are sorted
        //- After that, we traverse all of people + binary search with the sorted flowers to:
        //  + Find the index with flowers[j][1]<people[j]d
        //  ==> Using j var, current resul = prefix[j]
        //
        //1.2, Complexity
        //- Space: O(n+m+log(n))
        //- Time: Time: O(n*log(n)+m*log(n)) = O((n+m)*log(n))
        //
        int[][] flowers = {{1,6},{3,7},{9,12},{4,13}};
        int[] people = {2,3,7,11};
//        int[] rs = fullBloomFlowers(flowers, people);
//        int[] rs = fullBloomFlowersPriorityQueue(flowers, people);
        int[] rs = fullBloomFlowersBinarySearch(flowers, people);
        for (int i = 0; i < rs.length; i++) {
            System.out.printf("%s, ", rs[i]);
        }
        //
        //2. MAX heap
        //2.0,
        //flowers[i] = [a,b]
        //people[i] = [p]
        //- For each people:
        //  + We find the number of flowers with a is less than p
        //Ex:
        //p = 3
        //+ Ta add 1,2,3 vào heap
        //p = 4
        //- Ta sẽ add thêm >= 4 vào
        //- Vấn đề sẽ có case:
        //Ex:
        //p=4
        //f = [1,3]
        //  + 3 cần pop ra ngoài vì (3<4)
        //
        //* Solution:
        //- For each people, add all of corresponding flowers:
        //  + [a,b]: a<=p
        //- Because we always travers from left to right so we need to remove the left elements that:
        //  + [a,b]: b<p
        //      + p is out of range betweet [a,b]
        //- We only add the (right value) to heap to remove
        //- We use the size of heap as the number of flower that person get.
        //
        //2.1, Optimization
        //2.2, Complexity
        //- Space: O(n+m)
        //- Time: O(n*log(n) + m*(log(n)+log(m))
        //
        //3. Binary search
        //3.0,
        //-
        //
        //#Reference:
        //1851. Minimum Interval to Include Each Query
    }
}
