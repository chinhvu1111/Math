package interviews;

public class E144_KthLargestElementInAnArray_quicksort {

    public static int findKthLargest(int[] nums, int k) {
        int left=0, right=nums.length-1;

        int value=findKLargestPartition(nums, left, right, k);
        return value;
    }

    public static int findKLargestPartition(int[] nums, int left, int right, int k){
        int pivot=right;

        //1,
        //1.1,
        //==> Tư tưởng là chia array thành 2 phần (left)(4: pivot)(right)
        //3.2,1,5,6,[4] (pivot)
        //3,2,1,5(p),5,3,6,[4]
        //Vì (3)<(4)
        //3,2,1,5(p),5,(3),6,4
        //==>
        //3,2,1,3,|,5,5,6,4
        //==> Tư tưởng là chia array thành 2 phần (left)(4: pivot)(right)
        //==> Cần swap (4) cho index đầu tiên của (right)
        //
        //1.2, Có 1 tính chất sau đây cần nhớ
        //* Nếu tìm số lớn thứ k
        //VD : k=2
        //- Nếu thứ tự ASC:
        //0,1(result),2,3 : i = n-1-k = 4(n)- 1- (k=2) = 1
        //- Nếu thứ tự DESC:
        //-8,6,3,2 : i = k
        int currentIndexLeft=left;

        for(int i=left;i<right;i++){
            if(nums[i]<=nums[pivot]){
                swap(nums, currentIndexLeft, i);
                currentIndexLeft++;
            }
        }
        swap(nums, currentIndexLeft, pivot);

        //0,1,2(currentIndexLeft),3,4 (right=4) (k==2)
        //(right-currentIndexLeft)=(4-2)=(k=2)
        if(right-currentIndexLeft==k-1){
            return nums[currentIndexLeft];
        }
        int value;

        if(right-currentIndexLeft<k-1){
            value=findKLargestPartition(nums, left, currentIndexLeft-1, k-right+currentIndexLeft-1);
        }else{
            value=findKLargestPartition(nums,currentIndexLeft+1, right, k);
        }
        return value;
    }

    public static void swap(int[] nums, int i, int j){
        int temp=nums[i];
        nums[i]=nums[j];
        nums[j]=temp;
    }

    public static void main(String[] args) {
//        int[] arr=new int[]{3,2,1,5,6,4};
//        int k=2;
//        int[] arr=new int[]{3,2,3,1,2,4,5,5,6};
//        int k=4;
//        int[] arr=new int[]{3};
//        int k=1;
        int[] arr=new int[]{3};
        int k=2;
        System.out.println(findKthLargest(arr, k));
        //
        //** Đề bài:
        //- Tìm số lớn thứ k của array
        //
        //** Tư duy như sau:
        //
        //Cách 1:
        //Quick select
        //1,
        //1.1,
        //==> Tư tưởng là chia array thành 2 phần (left)(4: pivot)(right)
        //3.2,1,5,6,[4] (pivot)
        //3,2,1,5(p),5,3,6,[4]
        //Vì (3)<(4)
        //3,2,1,5(p),5,(3),6,4
        //==>
        //3,2,1,3,|,5,5,6,4
        //==> Tư tưởng là chia array thành 2 phần (left)(4: pivot)(right)
        //==> Cần swap (4) cho index đầu tiên của (right)
        //
        //1.2, Có 1 tính chất sau đây cần nhớ
        //* Nếu tìm số lớn thứ k
        //VD : k=2
        //- Nếu thứ tự ASC:
        //0,1(result),2,3 : i = n-1-k = 4(n)- 1- (k=2) = 1
        //- Nếu thứ tự DESC:
        //-8,6,3,2 : i = k
        //
        //1.3
        //- Complexity:
        //* Average : Luôn chia 2
        //+ N + N/2 + N/4 +... < 2*N = O(n) average complex time
        //* Nếu pivot ở 2 đầu --> N (loop)*N(height)
        //- Space: O(LogN) ???? ==> Nếu dùng randome (Còn không thì thôi)
        //Cách 2:
        //Min heap:
        //2,
        //
    }
}
