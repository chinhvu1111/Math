package E1_binary_search_topic;

public class E14_MissingElementInSortedArray {

    public static int missingElement(int[] nums, int k) {
        int n=nums.length;
        int[] prefixSum=new int[n];
        int missingCount=0;

        for(int i=1;i<n;i++){
            missingCount+=nums[i]-nums[i-1]-1;
            prefixSum[i]=missingCount;
//            System.out.printf("%s, ", missingCount);
        }
        int low=0, high=n-1;
        int rsIndex=0;

        if(k>prefixSum[n-1]){
            return k-prefixSum[n-1]+nums[n-1];
        }
        while(low<=high){
            int mid=low+(high-low)/2;

            if(prefixSum[mid]>=k){
                rsIndex=mid;
                high=mid-1;
            }else{
                low=mid+1;
            }
        }
        //7+(1-3)-1
        return nums[rsIndex]+k-prefixSum[rsIndex]-1;
    }

    public static int missingElementOptimization(int[] nums, int k) {
        int n=nums.length;
        int low=0, high=n-1;

        while(low<high){
            //1+(2-1)/2 = 1.5
            //2-(2-1)/2 = 1.5
            int mid=low+(high-low)/2; //= high/2 + low/2
            //int mid = high - (high - low) / 2 = high/2 + low/2
            //==> Cái này sẽ tốt hơn??
            System.out.printf("high: %s, low: %s, rs=%s\n",high, low, high-(high-low)/2);
            System.out.printf("high: %s, low: %s, rs1=%s\n",high, low, low+(high-low)/2);

            //Ex:
            //nums = [4,7,9,10], k = 3
            //mid= (0+3)/2 = 1
            //- nums[mid]-nums[0]-mid = 7-4-1=2 : Là số lượng chữ số expect
            //- nums[mid]-nums[0]-mid<k: Nó sẽ nằm bên right, low=mid
            //- nums[mid]-nums[0]-mid>=k: Nó sẽ nằm bên left ==> vì == thì tức ta sẽ lấy element có index<mid
            //  ==> high=mid-1
            //==> low cuối cùng sẽ là phần tử nums[low] : (nums[mid]-nums[0]-mid<k) ==> Tức là nó nums[low] < nums[rsIndex] nhưng gần rsIndex nhất về left.
            //
            if(nums[mid]-nums[0]-mid<k){
                low=mid;
            }else{
                high=mid-1;
            }
        }
        //7+(1-3)-1
        return nums[0]+k+low;
    }

    public static void main(String[] args) {
        // Đề bài:
        //- Given an integer array nums which is sorted in ascending order and all of its elements are unique and given also an integer k,
        //* return the kth missing number starting from the leftmost number of the array.
        //- Return lại missing number thứ k từ bên trái nhất.
        //
        // Tư duy
        //1.
        //1.1, Idea
        //- Constraint
        //1 <= nums.length <= 5 * 10^4
        //1 <= nums[i] <= 10^7
        //nums is sorted in ascending order, and all the elements are unique.
        //1 <= k <= 10^8
        //- K khá lớn: Thế nên việc chạy lần lượt +1 từ left -> right sẽ rất khó
        //
        //- Brainstorm
        //Ex:
        //nums = [4,7,9,10], k = 3
        //rs=8
        //- Start, end
        //==> Số chắc chắn sẽ nằm right của (start+k)
        //- Lúc đó k sẽ change 1 khoảng k-count
        //  + count là số lượng số đứng trước mà missing (start+k)
        //  Phần missing đứng trước ta có thể dùng prefixSum để tính.
        //
        //- Ở đây ta có thể dùng binary search để tìm:
        //mid= low+(high-low)/2
        //  + Nếu prefix[mid] >= k => high=mid
        //  + Nếu prefix[mid] < k => low=mid+1
        //- Nếu missing >= max thì sao: Cái này xét sau
        //Ex:
        //[4,7,9,10]
        //0,2,3,3
        //
        //1.1, Optimization
        //- Ở đây ta cũng apply tư duy so sánh nums[mid] và (nums[0]/ nums[n-1])
        //
        //1.2, Complexity:
        //- Space : O(n) : trước khi optimization
        //- Time : O(log(n)): trước khi optimization
        //
        //1.3,** NOTE:
        //low+(high-low)/2 : ==> Hướng về nhỏ đi
        //high-(high-low)/2 : ==> Hướng về lớn lên
        //EX:
        //1+(2-1)/2= 1 (low) ==> low=mid+1, high=mid
        //2-(2-1)/2= 2 (high) ==> low=mid, high=mid-1
        //
//        int[] nums=new int[]{4,7,9,10};
//        int k=3;
        int[] nums=new int[]{1,2,4};
        int k=3;
//        int[] nums=new int[]{4,7,9,10};
//        int k=1;
        System.out.println(missingElement(nums, k));
//        System.out.println(missingElementOptimization(nums, k));
        System.out.println(1+(2-1)/2);
        System.out.println(2-(2-1)/2);
        //#Reference:
        //2934. Minimum Operations to Maximize Last Elements in Arrays
        //378. Kth Smallest Element in a Sorted Matrix
        //1606. Find Servers That Handled Most Number of Requests
    }
}
