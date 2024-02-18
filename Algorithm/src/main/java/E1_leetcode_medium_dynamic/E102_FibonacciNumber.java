package E1_leetcode_medium_dynamic;

public class E102_FibonacciNumber {

    public static int fib(int n) {
        if(n==0){
            return 0;
        }
        int numPrevOneSteps=1;
        int numPrevTwoSteps=0;
        int numCurStep=numPrevOneSteps;
        //2 = [1] + [0]
        //3 = 2 + 1
        for(int i=2;i<=n;i++){
            numCurStep=numPrevOneSteps+numPrevTwoSteps;
            numPrevTwoSteps=numPrevOneSteps;
            numPrevOneSteps=numCurStep;
        }
        return numCurStep;
    }

    public static void main(String[] args) {
        // Đề bài:
        //- Tính fibonacci của số n
        //
        // Tư duy
        //1.
        //1.1, Idea
        //- Constraint
        //+ 0 <= n <= 30
        //
        //- Brainstorm
        //1.2, Optimization
        //- It is similar to the climbing stars problem.
        //1.3, Complexity:
        //- Space : O(1)
        //- Time : O(n)
        //#Reference:
        //842. Split Array into Fibonacci Sequence
    }
}
