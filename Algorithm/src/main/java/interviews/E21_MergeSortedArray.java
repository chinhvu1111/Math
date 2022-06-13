package interviews;

public class E21_MergeSortedArray {

    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        int iLeft=0;
        int iRight=0;
        int newArr[]=new int[nums1.length];

        for(int i=0;i<newArr.length;i++){
            newArr[i]=nums1[i];
        }

        int index=0;
        while (iLeft<m||iRight<n){
//            if(newArr[iLeft]==0&&nums2[iRight]==0){
//                break;
//            }
//            if(newArr[iLeft]==0){
//                nums1[index++]=nums2[iRight++];
//            }else if(iRight<n&&nums2[iRight]==0){
//                nums1[index++]=newArr[iLeft++];
//            }
            if(iRight<n&&newArr[iLeft]>nums2[iRight]){
                nums1[index++]=nums2[iRight++];
            }else if(iLeft<m){
                nums1[index++]=newArr[iLeft++];
            }else if(iRight<n){
                nums1[index++]=nums2[iRight++];
            }
        }
    }

    public static void main(String[] args) {
//        int nums1[]=new int[]{1,2,3,0,0,0};
//        int nums2[]=new int[]{2,5,6};
        //Case 1:
        //Chú ý case này có thể dẫn đến việc không thể break ra khỏi loop
        //Chú ý trường hợp (không increase / decrease)
//        merge(nums1,3, nums2, 3);
        //Expected : 1,2,2,3,5,6
//        int nums1[]=new int[]{0};
//        int nums2[]=new int[]{1};
//        merge(nums1,0, nums2, 1);

        //Case 3:
        //Sai liên quan đến gán các case nums[i]==0 / nums2[i]==0
//        int nums1[]=new int[]{2, 0};
//        int nums2[]=new int[]{1};
//        merge(nums1,1, nums2, 1);

        int nums1[]=new int[]{-1,0,0,3,3,3,0,0,0};
        int nums2[]=new int[]{1,2,2};
        merge(nums1,6, nums2, 3);
        //Output :   [-1,1,0,2,0,2,3,3,3]
        //Expected : [-1,0,0,1,2,2,3,3,3]
        //
        //Với những bài merge về cũng 1 array cần thận như sau:
        //1, Liên quan đến việc có thể break ra khỏi loop được hay không.
        //Cần thận với các đk increase, decrease
        //Với dạng no decrease này thì ---> Chú ý : CHỈ IGNORE 0 ZERO Ở CUỐI <-> Chỉ khi (iLeft >= m-1);
        //thường sẽ while(iLeft<m||iRight<n)
        //Các điều kiện liên quan index ---> BẮT BUỘC.
        //1.1, Với các trường hợp:
        //nums1[i]=0 < nums2[i] --> Không cần điều kiên riếng
        //==> Else cho nó là được.
        //1.2, Với nums[i]=0 ở cuối --> i>=m-1 --> Bỏ qua không tính!
        //1.3,
        //- Không cần xét case both==0
        //- Không cần xét từng case nums1[i]==0 / nums2[i]==0
        //- Lấy 1 trong 2 khi thỏa mãn : (index <m/n), và có (đk >=)
        System.out.println("");
    }
}
