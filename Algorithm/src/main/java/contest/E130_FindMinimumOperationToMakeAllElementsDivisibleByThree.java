package contest;

public class E130_FindMinimumOperationToMakeAllElementsDivisibleByThree {

    public static int minimumOperations(int[] nums) {
        //1: 2
        //2: 1
        //3: 0
        int rs=0;
        for(int e: nums){
            rs+=Math.min(e%3, 3-(e%3));
        }
        return rs;
    }

    public static void main(String[] args) {

    }
}
