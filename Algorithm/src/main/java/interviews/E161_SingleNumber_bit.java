package interviews;

public class E161_SingleNumber_bit {

    public static int singleNumber(int[] nums) {
        int n=nums.length;
        int rs=0;

        for(int i=0;i<n;i++){
            rs=rs^nums[i];
        }
        return rs;
    }

    public static void main(String[] args) {
        int[] arr=new int[]{4,1,2,1,2};
        System.out.println(singleNumber(arr));
        //Đề bài:
        //- Cho các số xuất hiện 1 lần (Chỉ 1 số)/ 2 lần
        //- Tìm số xuât hiện 1 lần
        //Bài này tư duy như sau:
        //1, Quy luật:
        //(x,y,z,y,z) --> xor all = x
        //1.1, Quy luật phép xor
        //- x ^ 0s = x
        //- x ^ 1s = ~x
        //- x ^ x = 0
        //1.2, Chỉ cần XOR all elements là xong.
    }
}
