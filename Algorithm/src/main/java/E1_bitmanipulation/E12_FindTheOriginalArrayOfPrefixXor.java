package E1_bitmanipulation;

public class E12_FindTheOriginalArrayOfPrefixXor {

    public static int[] findArray(int[] pref) {
        int n =pref.length;
        int[] arr=new int[n];

        for(int i=0;i<n;i++){
            if(i==0){
                arr[i]=pref[i];
            }else{
                arr[i]=pref[i]^pref[i-1];
            }
        }
        return arr;
    }

    public static int[] findArrayOptimization(int[] pref) {
        int n =pref.length;
        int prev=pref[0];

        for(int i=1;i<n;i++){
            int newVal=pref[i]^prev;
            prev=pref[i];
            pref[i]=newVal;
        }
        return pref;
    }

    public static void main(String[] args) {
        //* Requirement
        //- You are given (an integer array pref of size n).
        // Find and return the array arr of size n that satisfies:
        //  + pref[i] = arr[0] ^ arr[1] ^ ... ^ arr[i].
        //- Note that ^ denotes the bitwise-xor operation.
        //It can be proven that the answer is unique.
        //
        //- Given pref:
        //==> Tìm array
        //
        // Idea
        //1.
        //1.0,
        //- Constraint:
        //1 <= pref.length <= 10^5
        //0 <= pref[i] <= 10^6
        //
        //- Brainstorm
        //-
        //1.1, Optimization
        //- Có thể optimize space -> O(1)
        //
        //1.2, Complexity
        //- Space:
        //- Time:
        //
        int[] pref = new int[]{5,2,0,3,1};
        findArray(pref);
        findArrayOptimization(pref);
    }
}
