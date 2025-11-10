package E1_daily;

public class E320_NumberOfWaysToSelectBuildings {

    public static long numberOfWays(String s) {
        int n=s.length();
        int[] prefixSum=new int[n];
        int[] suffixSum=new int[n];
        int sumOnePrefix=0;
        int sumOneSuffix=0;

        for(int i=0;i<n;i++){
            if(s.charAt(i)=='1'){
                sumOnePrefix++;
            }
            if(s.charAt(n-1-i)=='1'){
                sumOneSuffix++;
            }
            prefixSum[i]=sumOnePrefix;
            suffixSum[n-i-1]=sumOneSuffix;
        }
        long rs=0;

        for (int i = 1; i < n-1; i++) {
            long numCase=0;
            if(s.charAt(i)=='1'){
                //0,1,2
                if(i-prefixSum[i-1]>0&&n-i-1-suffixSum[i+1]>0){
                    numCase=(long)(i-prefixSum[i-1])*(n-i-1-suffixSum[i+1]);
                }
            }else if(prefixSum[i-1]>0&&suffixSum[i+1]>0){
                numCase=(long)prefixSum[i-1]*suffixSum[i+1];
            }
//            System.out.printf("Index:%s, numCase: %s\n", i, numCase);
            rs+=numCase;
        }

        return rs;
    }

    public static long numberOfWaysRefer(String s) {
        long one = 0, zero = 0, oneZero = 0, zeroOne = 0, ways = 0;
        for (int i = 0; i < s.length(); ++i) {
            if (s.charAt(i) == '0') {
                ++zero;
                oneZero += one; // Count in '10'.
                ways += zeroOne; // Count in '010'.
            }else {
                ++one;
                zeroOne += zero; // Count in '01'.
                ways += oneZero; // Count in '101'.
            }
        }
        return ways;
    }

    public static void main(String[] args) {
        //** Requirement
        //- You are given a 0-indexed binary string s which represents the types of buildings along a street where:
        //  + s[i] = '0' denotes that the ith building is (an office) and
        //  + s[i] = '1' denotes that the ith building is (a restaurant).
        //- As a city official, you would like to select 3 buildings for random inspection.
        //- However, to ensure variety, no (two consecutive buildings) out of the selected buildings can be of (the ("same") type).
        //+ For example, given s = "001101", we cannot select the 1st, 3rd,
        // and 5th buildings as that would form "011"
        // which is not allowed due to having two consecutive buildings of the same type.
        //* Return (the number of valid ways) to select 3 buildings.
        //
        //** Idea
        //1.
        //1.0,
        //- Method-1:
        //+ Constraints:
        //3 <= s.length <= 10^5
        //s[i] is either '0' or '1'.
        //
        //* Brainstorm:
        //
        //Example 1:
        //
        //Input: s = "001101"
        //Output: 6
        //Explanation:
        //The following sets of indices selected are valid:
        //- [0,2,4] from "001101" forms "010"
        //- [0,3,4] from "001101" forms "010"
        //- [1,2,4] from "001101" forms "010"
        //- [1,3,4] from "001101" forms "010"
        //- [2,4,5] from "001101" forms "101"
        //- [3,4,5] from "001101" forms "101"
        //No other selection is valid. Thus, there are 6 total ways.
        //
        //- (Prefix/Suffix) sum for each index
        //- For each index:
        //  + rs+= left*right
        //- current char (Index=i) = '1':
        //  + (i-left[i-1]) * (n-i-1-right[i+1])
        //- current char (Index=i) = '0':
        //  + left[i-1] * right[i+1]
        //
        //1.1, Case
        //
        //
        //1.2, Optimization
        //- 100[1]1100
        //- current char == '1':
        //  + rs+= count(10)
        //- current char == '0':
        //  + rs+= count(01)
        //
        //1.3, Complexity
        //- Space: O(n)
        //- Time: O(n)
        //
        String s = "001101";
        System.out.println(numberOfWays(s));
        System.out.println(numberOfWaysRefer(s));
        //
        //#Reference:
        //1900. The Earliest and Latest Rounds Where Players Compete
        //3015. Count the Number of Houses at a Certain Distance I
        //1392. Longest Happy Prefix
        //
        //1955. Count Number of Special Subsequences - HARD
        //312. Burst Balloons - HARD
        //1830. Minimum Number of Operations to Make String Sorted - HARD
    }
}
