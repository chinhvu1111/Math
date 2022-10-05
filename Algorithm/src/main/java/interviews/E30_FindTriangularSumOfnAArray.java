package interviews;

public class E30_FindTriangularSumOfnAArray {

    public static int triangularSum(int[] nums) {
        int length=nums.length;
//        int temp[]=new int[nums.length];

        for(int i=length-1;i>=0;i--){
            for(int j=0;j<i;j++){
//                int secondElement=0;
//
//                if(j+1<length){
//                    secondElement=nums[j+1];
//                }
                nums[j]=(nums[j]+nums[j+1])%10;
            }
//            System.out.println(i);
//            for(int j=0;j<i;j++){
//                nums[j]=temp[j];
////                System.out.print(nums[j]+" ");
//            }
//            System.out.println();
        }
        return nums[0];
    }

    public static int triangularSumOptimized(int[] nums) {
        for (int n = nums.length; n > 1; n--) {
            for (int i = 0; i < n - 1; i++) {
                nums[i] = (nums[i] + nums[i + 1]) % 10;
            }
        }
        return nums[0];
    }

    public static void main(String[] args) {
        int arr[]=new int[]{1,2,3,4,5};
        //** Đề bài
        //- Cho 1 array {1,2,3,4,5} phần tử xác định phần tử ở đỉnh tam giác
        //- ===> Ta dùng tam giác ngược
        //
        //
        //Chú ý bài này:
        //Ta không cần create 2 array --> Chỉ cần 1 array assign trực tiếp là ok.
        //1, Bài này giúp ta có 1 cách để assign value cho array:
        //1.1, Luôn tạo large array + assign lại thay vì cố create new array for each loop.
        System.out.println(triangularSum(arr));
    }
}
