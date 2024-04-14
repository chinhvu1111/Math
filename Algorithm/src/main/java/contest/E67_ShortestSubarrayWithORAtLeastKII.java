package contest;

public class E67_ShortestSubarrayWithORAtLeastKII {
    static int MAX = 100000;
    static int bitscount = 32;

    // Array to store bit-wise
// prefix count
    static int [][]prefix_count;

    // Function to find the prefix sum
    static void findPrefixCount(int arr[], int n)
    {

        // Loop for each bit
        for (int i = 0; i < bitscount; i++)
        {
            // Loop to find prefix count
            prefix_count[i][0] = ((arr[0] >> i) & 1);
            for (int j = 1; j < n; j++)
            {
                prefix_count[i][j] =((arr[j] >> i) & 1);
                prefix_count[i][j] += prefix_count[i][j - 1];
            }
        }
    }

    // Function to answer query
    static int rangeOr(int l, int r)
    {

        // To store the answer
        int ans = 0;

        // Loop for each bit
        for (int i = 0; i < bitscount; i++)
        {
            // To store the number of variables
            // with ith bit set
            int x;
            if (l == 0)
                x = prefix_count[i][r];
            else
                x = prefix_count[i][r] - prefix_count[i][l - 1];

            // Condition for ith bit
            // of answer to be set
            if (x != 0)
                ans = (ans | (1 << i));
        }

        return ans;
    }

    public static int minimumSubarrayLength(int[] nums, int k) {
        prefix_count = new int [bitscount][MAX];
        int n=nums.length;
        findPrefixCount(nums, n);

        for(int i=1;i<=n;i++){
            for(int j=0;j+i-1<n;j++){
                int l = i+j-1;
                if(rangeOr(j, l)>=k){
                    return i;
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        //* Requirement
        //- You are given an array nums of non-negative integers and an integer k.
        //An array is called special if the bitwise OR of all of its elements is at least k.
        //* Return the length of the shortest special non-empty subarray of nums, or return -1 if no special subarray exists.
        //
        // Idea
        //1.
        //1.0,
        //- Constraint:
        //1 <= nums.length <= 2 * 10^5
        //0 <= nums[i] <= 10^9
        //0 <= k <= 10^9
        //
        //- Brainstorm
        //- Tìm subarray có all or >=k
        //
        //
        int x = 1 | 2 | 5 | 12 | 3;
        System.out.println(x);
        int y = 1 | 2 ;
        System.out.println(x^y);
        System.out.println(5 | 12 | 3);
        //1101
        //1010
        //1111
        //1 or 0 = 1
        //+ 1 xor 1 = 0
        //0 or 1 = 1
        //+ 0 xor 1 = 1
        //1 or 1 = 1
        //+ 1 xor 1 = 0 ==> Wrong
        int[] nums = {2,1,8};
        int k=10;
        System.out.println(minimumSubarrayLength(nums, k));
    }
}
