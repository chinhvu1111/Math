package E1_daily;

public class E176_MovePiecesToObtainAString {

    public static boolean canChange(String start, String target) {
        int n=start.length();
        int i=0,j=0;

        while (i<n&&start.charAt(i)=='_'){
            i++;
        }
        while (j<n&&target.charAt(j)=='_'){
            j++;
        }

        while (i<n&&j<n){
            if(start.charAt(i)!=target.charAt(j)){
//                System.out.printf("1: %s %s\n", i, j);
                return false;
            }else if(start.charAt(i)=='L'){
                if(i<j){
//                    System.out.printf("2: %s %s\n", i, j);
                    return false;
                }
                j++;
                while (j<=i&&target.charAt(j)!='L'){
                    if(target.charAt(j)=='R'){
//                        System.out.printf("3: %s %s\n", i, j);
                        return false;
                    }
                    j++;
                }
                i++;
            }else if(start.charAt(i)=='R'){
                if(i>j){
//                    System.out.printf("4: %s %s\n", i, j);
                    return false;
                }
                i++;
                while (i<=j&&start.charAt(i)!='R'){
                    if(start.charAt(i)=='L'){
//                        System.out.printf("5: %s %s\n", i, j);
                        return false;
                    }
                    i++;
                }
                j++;
            }
            while (i<n&&start.charAt(i)=='_'){
                i++;
            }
            while (j<n&&target.charAt(j)=='_'){
                j++;
            }
        }
//        while (i<n&&start.charAt(i)=='_'){
//            i++;
//        }
//        while (j<n&&target.charAt(j)=='_'){
//            j++;
//        }
//        System.out.printf("6: %s %s\n", i, j);
        return i==j;
    }

    public static boolean canChangeRefactor(String start, String target) {
        int startLength = start.length();
        // Pointers for start string and target string
        int startIndex = 0, targetIndex = 0;

        while (startIndex < startLength || targetIndex < startLength) {
            // Skip underscores in start
            while (
                    startIndex < startLength && start.charAt(startIndex) == '_'
            ) {
                startIndex++;
            }
            // Skip underscores in target
            while (
                    targetIndex < startLength && target.charAt(targetIndex) == '_'
            ) {
                targetIndex++;
            }
            // If one string is exhausted, both should be exhausted
            if (startIndex == startLength || targetIndex == startLength) {
                return startIndex == startLength && targetIndex == startLength;
            }

            // Check if the pieces match and follow movement rules
            if (
                    start.charAt(startIndex) != target.charAt(targetIndex) ||
                            (start.charAt(startIndex) == 'L' && startIndex < targetIndex) ||
                            (start.charAt(startIndex) == 'R' && startIndex > targetIndex)
            ) return false;

            startIndex++;
            targetIndex++;
        }

        // If all conditions are satisfied, return true
        return true;
    }

    public static void main(String[] args) {
        //** Requirement
        //- You are given (two strings start and target), both of length n.
        //- Each string consists only of the characters 'L', 'R', and '_' where:
        //- The characters 'L' and 'R' represent pieces, where (a piece 'L') can move to (the left) only
        // if there is (a blank space) directly to (its left),
        //- A piece 'R' can move to (the right) only if there is (a blank space) directly to (its right).
        //- The character '_' represents (a blank space) that can be occupied by (any of the 'L' or 'R' pieces).
        //* Return true if it is possible to obtain (the string target) by moving the pieces of the string start (any number of times).
        // Otherwise, return false.
        //
        // Idea
        //1. 2 pointers
        //1.0,
        //- Method-1:
        //Constraints:
        //n == start.length == target.length
        //1 <= n <= 10^5
        //start and target consist of the characters 'L', 'R', and '_'.
        //  + n<=10^5 ==> Time: O(n)
        //
        //- Brainstorm
        //
        //Example 1:
        //
        //Input: start = "_L__R__R_", target = "L______RR"
        //Output: true
        //Explanation: We can obtain the string target from start by doing the following moves:
        //- Move the first piece one step to the left, start becomes equal to "L___R__R_".
        //- Move the last piece one step to the right, start becomes equal to "L___R___R".
        //- Move the second piece three steps to the right, start becomes equal to "L______RR".
        //Since it is possible to get the string target from start, we return true.
        //- How to move start -> target?
        //Ex:
        //___R____L___
        //- R can move to right
        //- At the same time, L can move to the left
        //==> Pattern is flexible
        //- Stack??
        //- We just check left to right of the (start and target):
        //  + If we got L != R
        //      ==> Return false
        //- It is not enough
        //- We meet R if:
        //___R____
        //_____R__
        //  + i<=j ==> We can move to right
        //      + While(i<=j) --> meet L ==> return false
        //_____R__
        //__R_____
        //  + i>j ==> We can not move to right
        //      + Return False
        //- We meet L if:
        //___L
        //_L__
        //  + i>=j ==> We can move to left
        //  + while(j<=i) --> meet R ==> return false
        //_L___
        //___L_
        //  + i<j ==> We can not move ==> return false
        //
        //- Special cases:
//        String start = "_L__R__R_", target = "L______RR";
//        String start = "_R", target = "R_";
//        String start = "__R_R_R_L", target = "____RRR_L";
        //- start: i
        //- target: j
        //  + i<=j:
        //  Ex:
        //  __R_R____
        //  _____R_R_
        //  ==> We cannot skip R
        //  i++
        //  while(i<=j&&start[i]!='R')
        //  + i++
        //- L and R have different count
//        String start = "_L__R__R_L", target = "L______RR_";
        String start = "_____LLLLL", target = "LLLLL_____";
        //==> Just bug
        //
        //1.1, Optimization
        //- We just modify a little to return false
        //  + If we combine index (i,j) from (start and target)
        //  start.charAt(startIndex) != target.charAt(targetIndex) ||
        //  (start.charAt(startIndex) == 'L' && startIndex < targetIndex) ||
        //  (start.charAt(startIndex) == 'R' && startIndex > targetIndex)
        //      + return false
        //  <> i++,j++
        //  ==> We need to skip (_) from the both of string
        //
        //1.2, Complexity
        //- Space: O(1)
        //- Time: O(n)
        //
        //2. Queue
        //2.0,
        //- We have the same logic
        //
        System.out.println(canChange(start, target));
        System.out.println(canChangeRefactor(start, target));
    }
}
