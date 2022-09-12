package interviews;

import java.util.Arrays;

public class E132_ValidTriangleNumber_two_pointers {

    public static boolean isTriangle(int a, int b, int c){
        return (a+b)>c&&(b+c)>a&&(a+c)>b;
    }

    public static int triangleNumber(int[] nums) {
        Arrays.sort(nums);
        int max=Arrays.stream(nums).max().getAsInt();
        int n=nums.length;
        int rs=0;
        int[] count=new int[max+1];

        for(int i=0;i<n;i++){
            count[nums[i]]++;
        }

        for(int i=0;i<n-1;i++){
             int currentValue=nums[i];
             int minRight=nums[i+1];
             int j;

             for(j=0;j<i;j++){
                 if(currentValue+nums[i]>minRight){
                     break;
                 }
             }
             rs+=i-j;
             System.out.printf("%s %s, ", i-j, currentValue);
        }

        return rs;
    }

    public static void main(String[] args) {
//        int arr[]=new int[]{2,2,3,4};
        int[] arr =new int[]{4,2,3,4};
        //2,(3),4,4
        //3: Có 2 giá trị thỏa mãn
        System.out.println(triangleNumber(arr));
        //Bài này tư duy như sau:
        //0, Bài này thêm kinh nghiệm + thêm 1 luồng tư duy liên quan đến Thinking idea
        //==> Thảo luận về tips của bài toán --> Xem nên:
        //- Biến đổi bài toán như thế nào --> Áp dụng 1 vài quy luật về toán
        //- Phân tích rõ tư tưởng/ problems thay vì chỉ tập trung vào code luôn. ===> (Có thể dẫn đến đi sai hướng/ Solution không tối ưu)
        //
        //1,
    }
}
