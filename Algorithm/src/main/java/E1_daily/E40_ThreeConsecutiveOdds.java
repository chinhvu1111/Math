package E1_daily;

public class E40_ThreeConsecutiveOdds {

    public static boolean threeConsecutiveOdds(int[] arr) {
        int n=arr.length;
        int len=0;

        for(int i=0;i<n;i++){
            if(arr[i]%2==1){
                len++;
                if(len==3){
                    return true;
                }
            }else{
                len=0;
            }
        }
        return  false;
    }

    public static void main(String[] args) {
        //** Requirement
        //- Given an integer array arr,
        //* Return (true) if there are (three consecutive odd numbers) in the array.
        // Otherwise, return (false).
        //
        //** Idea
        //1.
        //1.0,
        //* Method-1:
        //- Constraint:
        //1 <= arr.length <= 1000
        //1 <= arr[i] <= 1000
        //
        //- Brainstorm
        //Example 2:
        //Input: arr = [1,2,34,3,4,5,7,23,12]
        //Output: true
        //Explanation: [5,7,23] are three consecutive odds.
        //- Slide window:
        //  + ==> window = 3 là được.
        //
        //1.1, Optimization
        //1.2, Complexity
        //- Time: O(n)
        //- Space: O(1)
        //
        int[] arr = {1,2,34,3,4,5,7,23,12};
        System.out.println(threeConsecutiveOdds(arr));
        //#Reference:
        //2731. Movement of Robots
        //1351. Count Negative Numbers in a Sorted Matrix
        //2158. Amount of New Area Painted Each Day
    }
}
