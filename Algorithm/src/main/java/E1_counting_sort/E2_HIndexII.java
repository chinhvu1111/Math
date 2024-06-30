package E1_counting_sort;

public class E2_HIndexII {

    public static int hIndex(int[] citations) {
        int n=citations.length;
        int low=0, high=n-1;
        //Ex:
        //citations = [0,1,3,5,6]
        //[0,1,3,(5),6]
        //value = 5, tức là:
        //  + min = 5
        //  + 5 số đằng trước
        //
        int rs=0;

        while(low<=high){
            int mid=low+(high-low)/2;
            if(citations[mid]>=n-mid){
                rs=n-mid;
                high=mid-1;
            }else{
                low=mid+1;
            }
        }
//        System.out.println(rs);
        return rs;
    }

    public static void main(String[] args) {
        //* Requirement
        //-
        //
        // Idea
        //1.
        //1.0,
        //- Constraint:
        //n == citations.length
        //1 <= n <= 10^5
        //0 <= citations[i] <= 1000
        //citations is sorted in ascending order.
        //
        //- Brainstorm
        //- Bài này search theo:
        // + element[mid]>= (n-i) : mid thoả mãn
        //  + Ta sẽ tìm qua left:
        //      + high=mid-1
        //  + <> low = mid+1
        //
        //1.1, Optimization
        //1.2, Complexity
        //- Space: O(1)
        //- Time : O(log(n))
        //
//        int[] citations = {0,1,3,5,6};
//        int[] citations = {100};
        int[] citations = {0};
        System.out.println(hIndex(citations));
        //#Reference:
        //1895. Largest Magic Square
        //2210. Count Hills and Valleys in an Array
        //2971. Find Polygon With the Largest Perimeter
    }
}
