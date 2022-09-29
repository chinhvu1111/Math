package interviews;

/*
Đây là bài mở rộng cùng dạng với rùa và thỏ
 */
public class E14_1_FindTheDuplicateNumber_linkedList {

    public static int findDuplicate(int[] nums) {
        //increase
        int left=0, right=nums.length-1;

        while (left<right){
            int mid=left+(right-left)/2;
            int count=0;

            for(int i=0;i<nums.length;i++){
                if(nums[i]<=mid){
                    count++;
                }
            }
            if(mid<count){
                right=mid;
            }else{
                left=mid+1;
            }
        }
//        System.out.printf("%s %s,", left, right);
        return left;
    }

    public static void main(String[] args) {
//        int arr[]=new int[]{1,3,4,2,2};
//        int arr[]=new int[]{3,1,3,4,2};
//        int arr[]=new int[]{1,2};
//        int arr[]=new int[]{1,1};
        //Case 1: này nếu 2 thì sẽ tìm được 3 số <=2 : 1,1,2
        //--> Nếu mình chỉ xét 2<count : return 2 thì sẽ bị sai case này
        //VD: 4,5,6,3,2,1,1 --> Như thế này thì số >=1 ==> Đều có thể là kết quả
        //- Ta cần giảm số đó đi để xét tiếp:
        //+ < --> Sẽ bị thiếu case
        //Có 2 cases cần xét:
        //+ 1,2(mid),3,3 --> 1 <= (count=2)
        //+ 2,2(mid),3,4 --> 1 <= (count=2)
        //
        int arr[]=new int[]{1,3,4,2,1};
//        System.out.println(2^2^2);

        //https://leetcode.com/problems/find-the-duplicate-number/discuss/1892921/Java-9-Approaches-Count-%2B-Hash-%2B-Sort-%2B-Binary-Search-%2B-Bit-%2B-Fast-Slow-Pointers
        System.out.println(findDuplicate(arr));
    }
}
