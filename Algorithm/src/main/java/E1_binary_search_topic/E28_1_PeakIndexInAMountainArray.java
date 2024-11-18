package E1_binary_search_topic;

public class E28_1_PeakIndexInAMountainArray {

    public static int peakIndexInMountainArray(int[] arr) {
        int n = arr.length;
        int low=0, high=n-1;
        int rs=-1;

        while(low<=high){
            int mid = low+(high-low)/2;
            if(arr[mid]>arr[mid+1]){
                rs=mid;
                high=mid-1;
            }else{
                low=mid+1;
            }
        }
        return rs;
    }

    public static void main(String[] args) {
        //** Requirement:
        //- You are given (an integer mountain array arr) of length n where the values increase to (a peak element) and then decrease.
        //* Return (the index of the peak element).
        //- Your task is to solve it in O(log(n)) time complexity.
        //
        // Idea
        //1.
        //1.0,
        // Method-1:
        //- Constraint
        //3 <= arr.length <= 10^5
        //0 <= arr[i] <= 10^6
        //arr is guaranteed to be a mountain array.
        //
        //- Brainstorm
        //
        //Example 2:
        //Input: arr = [0,2,1,0]
        //Output: 1
        //
        //* Simply, ta chỉ cần so sánh arr[mid] và arr[mid+1]
        //==> Quyết định (peak direction)
        //- arr[mid]>arr[mid+1]:
        //  + peak in the left
        //- arr[mid]<=arr[mid+1]:
        //  + peak in the right
        //
        //1.1, Optimization
        //1.2, Complexity
        //- Space: O(1)
        //- Time: O(log(n))
        //
//        int[] arr = {0,2,1,0};
        int[] arr = {24,69,100,99,79,78,67,36,26,19};
        //rs=4
        //Expected = 2
        System.out.println(peakIndexInMountainArray(arr));
    }
}
