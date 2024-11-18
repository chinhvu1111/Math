package E1_daily;

import java.util.*;

public class E154_MostBeautifulItemForEachQuery_classic {

    public static int[] maximumBeauty(int[][] items, int[] queries) {
        int n=items.length;
        Arrays.sort(items, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0]-o2[0];
            }
        });
        int maxPrice=0;
        for(int i=0;i<n;i++){
            maxPrice=Math.max(items[i][1], maxPrice);
            items[i][1]=maxPrice;
        }
        int m=queries.length;
        int[] rs=new int[m];

        for(int i=0;i<m;i++){
            int[] searchElement = searchElement(queries[i], items);
            rs[i]=searchElement[1];
        }
        return rs;
    }

    public static int[] searchElement(int queryVal, int[][] sortArr){
        int[] searchElement=new int[]{0, 0};
        int low=0, high=sortArr.length-1;

        while(low<=high){
            int mid=low+(high-low)/2;
            if(sortArr[mid][0]>queryVal){
                high=mid-1;
            }else{
                searchElement=sortArr[mid];
                low=mid+1;
            }
        }
        return searchElement;
    }

    public static int[] maximumBeautySortingQueue(int[][] items, int[] queries) {
        int[] ans = new int[queries.length];

        // sort both items and queries in ascending order
        Arrays.sort(items, (a, b) -> a[0] - b[0]);

        int[][] queriesWithIndices = new int[queries.length][2];
        for (int i = 0; i < queries.length; i++) {
            queriesWithIndices[i][0] = queries[i];
            queriesWithIndices[i][1] = i;
        }

        //- Sort queries by price
        //- Attach to the index
        Arrays.sort(queriesWithIndices, (a, b) -> a[0] - b[0]);

        int itemIndex = 0;
        int maxBeauty = 0;

        for (int i = 0; i < queries.length; i++) {
            int query = queriesWithIndices[i][0];
            int originalIndex = queriesWithIndices[i][1];

            //After sorting the query
            //==> We only use the incremental index of the items array
            //  + To get the max beauty with increase beauty
            while (itemIndex < items.length && items[itemIndex][0] <= query) {
                maxBeauty = Math.max(maxBeauty, items[itemIndex][1]);
                itemIndex++;
            }
            ans[originalIndex] = maxBeauty;
        }

        return ans;
    }

    public static void main(String[] args) {
        //** Requirement:
        //- You are given a 2D integer array items where items[i] = [price-i, beauty-i] denotes (the price and beauty of an item) respectively.
        //- You are also given (a 0-indexed integer array queries).
        //  + For each queries[j], you want to determine (the maximum beauty of an item) whose (price is less than or equal) to (queries[j]).
        //- If (no such item exists), then the answer to (this query is 0).
        //* Return (an array answer of the same length) as queries where answer[j] is the answer to (the jth query).
        //
        //Example 1:
        //
        //Input: items = [[1,2],[3,2],[2,4],[5,6],[3,5]], queries = [1,2,3,4,5,6]
        //Output: [2,4,5,5,6,6]
        //Explanation:
        //- For queries[0]=1, [1,2] is the only item which has price <= 1. Hence, the answer for this query is 2.
        //- For queries[1]=2, the items which can be considered are [1,2] and [2,4].
        //  The maximum beauty among them is 4.
        //- For queries[2]=3 and queries[3]=4, the items which can be considered are [1,2], [3,2], [2,4], and [3,5].
        //  The maximum beauty among them is 5.
        //- For queries[4]=5 and queries[5]=6, all items can be considered.
        //  Hence, the answer for them is the maximum beauty of all items, i.e., 6.
        //
        // Idea
        //1.
        //1.0,
        // Method-1:
        //- Constraint
        //1 <= items.length, queries.length <= 10^5
        //items[i].length == 2
        //1 <= price-i, beauty-i, queries[j] <= 10^9
        //
        //- Brainstorm
        //- We can use treeSet to store the information that will be used to search
        //
        //1.1, Optimization
        //- We will sort the items array directly rather than creating new array from items
        //- After sorting, we use the prefix max to mark the max beautiful metric of the sorted array
        //- For the queries to search.
        //
        //1.2, Complexity
        //- Space: O(log(n))
        //- Time: O(m*log(n)+n*log(n))
        //
        //2. Sort queries
        //2.0,
        //
        //- Sort queries by price
        //- Attach to the index
        //
        //After sorting the query
        //==> We only use the incremental index of the items array
        //  + To get the max beauty with increase beauty
        //
        //- The space complexity is determined by the space needed by our sorting algorithm to sort both items and queries.
        //  This space complexity (S) depends on the language of implementation. Given input size M:
        //- In Java, Arrays.sort() is implemented using a variant of the (Quick Sort algorithm) which has a space complexity of O(logM).
        //- In C++, the sort() function is implemented as a hybrid of (Quick Sort, Heap Sort, and Insertion Sort),
        // with (a worst-case) space complexity of O(logM).
        //- In Python, the sort() method sorts a list using the (Timsort algorithm) which is a combination of (Merge Sort and Insertion Sort)
        //  and has a space complexity of O(M).
        //
        //Because we run this algorithm on both items and queries, the total space complexity would be O(SM + SN)
        //
        //2.1, Optimization
        //
        //2.2, Complexity
        //- Space: O(log(m))
        //- Time: O(m*log(m)+n*log(n))
        //
        //#Reference:
        //1847. Closest Room
        //2736. Maximum Sum Queries
        int[][] items = {{1,2},{3,2},{2,4},{5,6},{3,5}};
        int[] queries = {1,2,3,4,5,6};
//        int[] rs= maximumBeauty(items, queries);
        int[] rs= maximumBeautySortingQueue(items, queries);

        for (int i = 0; i < rs.length; i++) {
            System.out.printf("%s,", rs[i]);
        }
    }
}
