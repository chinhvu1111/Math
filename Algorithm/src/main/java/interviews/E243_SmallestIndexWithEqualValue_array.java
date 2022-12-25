package interviews;

public class E243_SmallestIndexWithEqualValue_array {

    public static int smallestEqual(int[] nums) {
        int n=nums.length;
        int rsIndex=Integer.MAX_VALUE;

        for(int i=0;i<n;i++){
            if(i%10==nums[i]&&rsIndex>i){
                rsIndex=i;
            }
        }
        return rsIndex==Integer.MAX_VALUE?-1: rsIndex;
    }

    public static void main(String[] args) {
        int[] arr=new int[]{4,3,2,1};
        //
        //#Reference
        //2058. Find the Minimum and Maximum Number of Nodes Between Critical Points
        //2094. Finding 3-Digit Even Numbers
        //1030. Matrix Cells in Distance Order
        //425. Word Squares
    }
}
