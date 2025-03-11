package contest;

public class E286_MinimumSwapsToGroupAll1Together {

    public static int minSwaps(int[] data) {
        int n=data.length;
        int size=0;

        for (int datum : data) {
            if (datum == 1) {
                size++;
            }
        }
        int end=size-1;
        int curCountOne=0;
        int i=0;
        int rs=Integer.MAX_VALUE;

        for(;i<=end;i++){
            if(data[i]==1){
                curCountOne++;
            }
            rs=Math.min(rs, size-curCountOne);
        }
        int start=0;

        for(int j=end+1;j<n;j++){
            if(data[start]==1&&data[j]==0){
                curCountOne--;
            }else if(data[start]==0&&data[j]==1){
                curCountOne++;
            }
            start++;
            rs=Math.min(rs, size-curCountOne);
        }
        return rs;
    }

    public static void main(String[] args) {
        //** Requirement
        //- Given a binary array data,
        //* return the minimum number of swaps required to group (all 1’s present) in the array together in any place in the array.
        //
        //Example 1:
        //
        //Input: data = [1,0,1,0,1]
        //Output: 1
        //Explanation: There are 3 ways to group all 1's together:
        //[1,1,1,0,0] using 1 swap.
        //[0,1,1,1,0] using 2 swaps.
        //[0,0,1,1,1] using 1 swap.
        //The minimum is 1.
        //
        // Idea
        //1
        //1.0,
        //- Method-1:
        //+ Constraints:
        //1 <= data.length <= 10^5
        //data[i] is either 0 or 1.
        //  + Length<= 10^5 ==> Time: O(n*k)
        //
        //- Brainstorm
        //Ex:
        //
        //Input: data = [1,0,1,0,1]
        //Output: 1
        //- Swap any pair of indices
        //Ex:
        //Input: data = [1,0,1,0,0,0,1]
        //- Can we solve using slide window?
        //
        //data = [1,0,1,0,1]
        //- Index=0:
        //data = [1,0,1,0,1]
        //mask = [1,1,1,0,0]
        //+ rs = 1
        //- count of one number, size = Count of one number
        //
        //1.1, Cases
        //
//        int[] data = {1,0,1,0,1};
        int[] data = {0,0,0,1,0};
        System.out.println(minSwaps(data));
        //1.2, Optimization
        //1.3, Complexity
        //- Space: O(1)
        //- Time: O(n)
        //
        //#Reference:
        //1703. Minimum Adjacent Swaps for K Consecutive Ones
        //2380. Time Needed to Rearrange a Binary String
        //3086. Minimum Moves to Pick K Ones
    }
}
