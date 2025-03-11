package E1_daily;

import java.util.*;

public class E244_MakeLexicographicallySmallestArrayBySwappingElements_unionfind_classic {

    public static int[] findParent(int node, HashMap<Integer, Integer> parent, HashMap<Integer, Integer> mapCount){
        int curNode = node;
//        System.out.println(node);
//        System.out.println(parent);
        while(parent.get(node)!=node){
            node=parent.get(node);
        }
        parent.put(curNode, node);
        return new int[]{node, mapCount.get(node)};
    }

    public static void addEdge(int u, int v, HashMap<Integer, Integer> parent, HashMap<Integer, Integer> mapCount){
        int[] parentU = findParent(u, parent, mapCount);
        int[] parentV = findParent(v, parent, mapCount);
        if(parentU[0]==parentV[0]){
            return;
        }
        //u = parent(v)
        if(parentU[1]>=parentV[1]){
            parent.put(parentV[0], parentU[0]);
            parent.put(v, parentU[0]);
            mapCount.put(parentU[0], mapCount.get(parentU[0])+1);
        }else{
            parent.put(parentU[0], parentV[0]);
            parent.put(u, parentV[0]);
            mapCount.put(parentV[0], mapCount.get(parentV[0])+1);
        }
    }

    public static int[] lexicographicallySmallestArray(int[] nums, int limit) {
        int n=nums.length;
        TreeSet<Integer> set=new TreeSet<>();
        HashMap<Integer, Integer> graph=new HashMap<>();
        HashMap<Integer, Integer> mapCount=new HashMap<>();
        int[] rs=new int[n];

        for (int i = 0; i < n; i++) {
            graph.put(nums[i], nums[i]);
            mapCount.put(nums[i], 1);
        }

        for(int i=0;i<n;i++){
            int upperVal=nums[i]-limit;
            int lowerVal = nums[i]+limit;
            Integer upperValidVal = set.ceiling(upperVal);
            Integer lowerValidVal = set.floor(lowerVal);

            if(upperValidVal!=null&&Math.abs(upperValidVal-nums[i])<=limit){
                addEdge(upperValidVal, nums[i], graph, mapCount);
            }
            if(lowerValidVal!=null&&Math.abs(lowerValidVal-nums[i])<=limit){
                addEdge(lowerValidVal, nums[i], graph, mapCount);
            }
            set.add(nums[i]);
        }
        HashMap<Integer, TreeSet<Integer>> parentToIndices=new HashMap<>();
        HashMap<Integer, List<Integer>> parentToList=new HashMap<>();
        for(int i=0;i<n;i++){
            int[] curParent = findParent(nums[i], graph, mapCount);
            TreeSet<Integer> curList=parentToIndices.getOrDefault(curParent[0], new TreeSet<>());
            List<Integer> curNodes=parentToList.getOrDefault(curParent[0], new ArrayList<>());
            curList.add(i);
            curNodes.add(nums[i]);
            parentToIndices.put(curParent[0], curList);
            parentToList.put(curParent[0], curNodes);
        }
        for(int parent: parentToIndices.keySet()){
            List<Integer> curNodes= parentToList.get(parent);
            Collections.sort(curNodes);
            TreeSet<Integer> indices = parentToIndices.get(parent);
            Iterator<Integer> iter = indices.iterator();
            int index=0;
            while (iter.hasNext()){
                Integer curIndex = iter.next();
                int curNode = curNodes.get(index);
                rs[curIndex]=curNode;
                index++;
            }
        }
        return rs;
    }

    public static void main(String[] args) {
        //** Requirement
        //- You are given a 0-indexed array of positive integers nums and a positive integer limit.
        //- In one operation, you can choose any two indices i and j and
        //  swap nums[i] and nums[j] if |nums[i] - nums[j]| <= limit.
        //* Return (the lexicographically smallest array) that can be obtained by performing the operation any number of times.
        //- An array a is lexicographically smaller than an array b if in the first position where a and b differ,
        // array a has an element that is less than the corresponding element in b.
        // For example, the array [2,10,3] is lexicographically smaller than the array [10,2,3]
        // because they differ at index 0 and 2 < 10.
        //
        //Example 1:
        //
        //Input: nums = [1,5,3,9,8], limit = 2
        //Output: [1,3,5,8,9]
        //Explanation: Apply the operation 2 times:
        //- Swap nums[1] with nums[2]. The array becomes [1,3,5,9,8]
        //- Swap nums[3] with nums[4]. The array becomes [1,3,5,8,9]
        //We cannot obtain a lexicographically smaller array by applying any more operations.
        //Note that it may be possible to get the same result by doing different operations.
        //
        //** Idea
        //1.
        //1.0,
        //- Method-1:
        //+ Constraints:
        //1 <= nums.length <= 10^5
        //1 <= nums[i] <= 10^9
        //1 <= limit <= 10^9
        //  + length<= 10^5 ==> Time: O(n*)
        //
        //- Brainstorm
        //- We need to swap to get the (smaller element) should (7appear first)
        //
        //Ex:
        //Input: nums = [1,5,3,9,8], limit = 2
        //Output: [1,3,5,8,9]
        //
        //nums = [1,5,3,9,8]
        //- a, b, c
        //  + c>a ==> we need to swap(a,c)
        //Ex:
        //5,4,1
        //5,4 => Swap(4,5)
        //4,5,1 => Wap(4,1)
        //
        //+ 1 can swap to 5
        //  + That means 1 can swap to x<5
        //  + That means 1<x<5 can swap to 5
        //Ex:
        //4,5,1
        //  + 1 can swap to 5
        //  => 1 can swap to 4
        //  => 4>1 can wap to 5
        //==> Can sort 1,4,5
        //
        //- We can sort the all of elements in range (x-limit, x)
        //
        //Ex:
        //4,9,5,10,1
        //  + 1 can swap to 5
        //  => 1 can swap to 4
        //==> 1,9,4,10,5
        //- Could we swap one by one element?
        //Ex:
        //6,9,4,10,1
        //  + 6,9
        //  + 6,9,4
        //  => swap(4,6)
        //  => 4,9,6
        //  + 4,9,6,10
        //  + 4,9,6,10,1
        //  => swap(1,4) swap left most such that |x-1|<=limit
        //  => We should not only replace
        //      + Because if we swap the all of elements
        //      ==> Time: O(n) (TLE)
        //nums = [1,5,3,9,8], limit = 2
        //  + 1,5
        //  + 1,5,3
        //  => 1,3,5
        //  + 1,3,5,9
        //  + 1,3,5,9,8
        //  => 1,3,5,8,9
        //
        //Ex:
        //6,9,4,10,3, limit=3
        //  + 6,9
        //  + 6,9,4
        //  => 4,6,9
        //  + 4,6,9,10
        //  + 4,6,9,10,3
        //  => 3,4,6,9,10
        //
        //- Give a hint related to UNION FIND:
        //  + We consider the group of elements rather than (list of pair of elements)
        //- Group have root as:
        //  + Smallest element ==> Any element
        //      + Hashmap ==> List of index
        //- Add new element x:
        //  + x can connect to y<=(x+limit)
        //  + x can connect to y>=(x-limit)
        //  ==> Add edges
        //      + (x,y1)
        //      + (x,y2)
        //
        int[] nums = {1,5,3,9,8};
        int limit = 2;
        int[] rs= lexicographicallySmallestArray(nums, limit);
        for (int i = 0; i < rs.length; i++) {
            System.out.printf("%s ", rs[i]);
        }
        System.out.println();
    }
}
