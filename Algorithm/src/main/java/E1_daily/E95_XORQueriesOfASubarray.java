package E1_daily;

public class E95_XORQueriesOfASubarray {

    public static int[] xorQueries(int[] arr, int[][] queries) {
        int n=arr.length;
        int[] prefixXor=new int[n+1];
        int xorVal=0;

        for(int i=0;i<n;i++){
            xorVal=xorVal^arr[i];
            prefixXor[i+1]=xorVal;
        }
        int m=queries.length;
        int[] rs=new int[m];

        for(int i=0;i<m;i++){
            rs[i]=prefixXor[queries[i][1]+1]^prefixXor[queries[i][0]];
        }
        return rs;
    }

    public static void main(String[] args) {
        //** Requirement
        //- You are (given an array arr of positive integers).
        //- You are also given the array queries where queries[i] = [lefti, righti].
        //- For each (query i) compute the XOR of elements from lefti to righti (that is, arr[lefti] XOR arr[lefti + 1] XOR ... XOR arr[righti] ).
        //* Return an array answer where answer[i] is the answer to (the ith query).
        //
        // Idea
        //1.
        //1.0,
        // Method-1:
        //- Constraint
        //1 <= arr.length, queries.length <= 3 * 10^4
        //1 <= arr[i] <= 10^9
        //queries[i].length == 2
        //0 <= lefti <= righti < arr.length
        //
        //- Brainstorm
        //- Prefix xor là được
        //
        //1.1, Optimization
        //1.2, Complexity
        //- Time: O(n+m)
        //- Space: O(n)
        //
        int[] arr = {1,3,4,8};
        int[][] queries = {{0,1},{1,2},{0,3},{3,3}};
        int[] rs= xorQueries(arr, queries);

        for (int i = 0; i < rs.length; i++) {
            System.out.printf("%s ", rs[i]);
        }
        System.out.println();
    }
}
