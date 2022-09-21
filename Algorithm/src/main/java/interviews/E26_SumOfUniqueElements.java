package interviews;

public class E26_SumOfUniqueElements {

    public static int sumOfUnique(int[] nums) {
        int[] count =new int[101];
        int rs=0;
        int subTraction=0;

        for(int i=0;i<nums.length;i++){
            rs+=nums[i];
            count[nums[i]]++;
        }
        for(int i=0;i<nums.length;i++){
            if(count[nums[i]]>1){
                subTraction+=nums[i];
            }
        }
        return rs-subTraction;
    }

    public static void main(String[] args) {
        int arr[]=new int[]{1,2,3,2};
        System.out.println(sumOfUnique(arr));
        //** Đề bài
        //- Tổng các phần tử khác nhau:
        //
        //** Bài này tư duy như sau:
        //- sum - (sum các phần tử có lần xuất hiện >1)
    }
}
