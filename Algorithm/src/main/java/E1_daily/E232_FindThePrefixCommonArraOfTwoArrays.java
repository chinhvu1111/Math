package E1_daily;

public class E232_FindThePrefixCommonArraOfTwoArrays {

    public static int[] findThePrefixCommonArray(int[] A, int[] B) {
        int[] countA = new int[51];
        int[] countB = new int[51];
        int n = A.length;
        int[] rs = new int[n];

        for (int i = 0; i < n; i++) {
            countA[A[i]]++;
            countB[B[i]]++;
            int commonNum = 0;
            for (int j = 0; j < 51; j++) {
                if (countA[j] != 0 && countA[j] == countB[j]) {
                    commonNum++;
                }
            }
            rs[i] = commonNum;
        }
        return rs;
    }

    public static int[] findThePrefixCommonArrayOptimization(int[] A, int[] B) {
        int n = A.length;
        int[] rs = new int[n];
        int[] countA = new int[51];
        int[] countB = new int[51];
        int curRs=0;

        for (int i = 0; i < n; i++) {
            if(A[i]==B[i]){
                curRs++;
            }else{
                if(countA[B[i]]!=0){
                    curRs++;
                    countA[B[i]]--;
                }else{
                    countB[B[i]]++;
                }
                if(countB[A[i]]!=0){
                    curRs++;
                    countB[A[i]]--;
                }else{
                    countA[A[i]]++;
                }
            }
            rs[i]=curRs;
        }
        return rs;
    }

    public static int[] findThePrefixCommonArrayOptimizationRefer(int[] A, int[] B) {
        int n = A.length;
        int[] prefixCommonArray = new int[n];
        int[] frequency = new int[n + 1];
        int commonCount = 0;

        // Iterate through the elements of both arrays
        for (int currentIndex = 0; currentIndex < n; ++currentIndex) {
            //1,3
            //3,1
            //
            // Increment frequency of current elements in A and B
            // Check if the element in A has appeared before (common in prefix)
            frequency[A[currentIndex]] += 1;
            if (frequency[A[currentIndex]] == 2) ++commonCount;

            // Check if the element in B has appeared before (common in prefix)
            frequency[B[currentIndex]] += 1;
            if (frequency[B[currentIndex]] == 2) ++commonCount;

            // Store the count of common elements for the current prefix
            prefixCommonArray[currentIndex] = commonCount;
        }

        // Return the final array with counts of common elements in each prefix
        return prefixCommonArray;
    }

    public static void main(String[] args) {
        //** Requirement
        //- You are given (two 0-indexed integer permutations A and B) of length n.
        //- (A prefix common array) of (A and B) is (an array C) such that C[i] is equal to
        // (the count of numbers) that are present (at or before) (the index i) in (both A and B).
        //* Return (the prefix common array) of (A and B).
        //- (A sequence of n integers) is called (a permutation) if it contains (all integers) from (1 to n) ("exactly once").
        //
        // Idea
        //1.
        //1.0,
        //- Method-1:
        //+ Constraints:
        //1 <= A.length == B.length == n <= 50
        //1 <= A[i], B[i] <= n
        //It is guaranteed that A and B are both a permutation of n integers.
        //
        //- Brainstorm
        //
        //
        //1.1, Optimization
        //A = [1,3,2,4], B = [3,1,2,4]
        //1+1 = 0
        //3+3 = 0
        //A = [1,3,2,4]
        //B = [3,1,2,4]
        //- Check both of strings at the same time:
        //  + add new character
        //  ==> We can do accumulation
        //- charA
        //- charB
        //- if charA==charB:
        //  + rs++
        //  + If charB exists in the A:
        //      + countA[charB]--; rs++
        //   <> countB[charB]++
        //  + If charA exists in the B:
        //      + countB[charA]--; rs++
        //   <> countA[charA]++
        //
        //- Other way:
        //  + Each number exists only 1 time
        //  ==> We can check count[char]==2
        //
        //1.2, Complexity
        //- Space: O(n)
        //- Time: O(n^2) ==> O(n)
//        int[] A = {1, 3, 2, 4}, B = {3, 1, 2, 4};
        int[] A = {2,3,1};
        int[] B = {3,1,2};
//        int[] rs= findThePrefixCommonArray(A, B);
//        int[] rs = findThePrefixCommonArrayOptimization(A, B);
        int[] rs = findThePrefixCommonArrayOptimizationRefer(A, B);
        for (int r : rs) {
            System.out.printf("%s,", r);
        }
        //
        //#Reference:
        //1911. Maximum Alternating Subsequence Sum
        //2454. Next Greater Element IV
        //2898. Maximum Linear Stock Score
    }
}
