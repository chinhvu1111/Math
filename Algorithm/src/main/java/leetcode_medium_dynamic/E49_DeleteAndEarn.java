package leetcode_medium_dynamic;

import java.util.*;

public class E49_DeleteAndEarn {

//    public static int deleteAndEarn(int[] nums) {
//        int max= Arrays.stream(nums).max().getAsInt();
//        int min= Arrays.stream(nums).min().getAsInt();
//        int dp[]=new int[max+1];
//        int value=max;
//        int rs=0;
//        int length=nums.length;
//
//        for(int i=0;i<length;i++){
//            dp[nums[i]]++;
//        }
//
//        while (value>=min){
//            int a=dp[value-1];
//            int b=dp[value];
//            int c=dp[value+1];
//
//            if(){
//
//            }
//        }
//        return rs;
//    }

    public static int deleteAndEarn(int[] nums) {
//        int rs=0;
//        int length=nums.length;
//        int count[]=new int[length];
//        int max=Arrays.stream(nums).max().getAsInt();
//        int dp[]=new int[max+1];
//
//        for(int i=0;i<length;i++){
//            count[nums[i]]++;
//        }
//        for(int i=1;i<length;i++){
//            int a=0;
//
//            if(count[nums[i]-1]!=0){
//                a=dp[nums[i]-1];
//            }
//        }

        int length=nums.length;
        HashMap<Integer, Integer> count=new HashMap<>();

        for(int i=0;i<length;i++){
            Integer currentCount=count.get(nums[i]);

            if(currentCount!=null){
                count.put(nums[i], currentCount+1);
            }else{
                count.put(nums[i], 1);
            }
        }
        SortedSet<Integer> keys=new TreeSet<>(count.keySet());
        int index=0;
        int dp[]=new int[keys.size()];
        int rs=0;
        int valueBefore=0;

        for(Integer key:keys){
            dp[index]=key*count.get(key);

            if(index>1&&key==valueBefore+1){
                //Tư duy thế này là tư duy cộng thêm
                dp[index]+=dp[index-2];
            }
            if(index>=1&&key!=valueBefore+1){
//                dp[index]+=dp[index-1];
                dp[index]+=dp[index-1];
            }
            if(index>=1){
                dp[index]=Math.max(dp[index], dp[index-1]);
            }
            index++;
            valueBefore=key;
        }

        return dp[keys.size()-1];
    }

    public static void main(String[] args) {
//        int arr[]=new int[]{2,2,3,3,3,4};
//        int arr[]=new int[]{3,4,2};
//        int arr[]=new int[]{3,1};
        //Thiếu trường hợp:
        //Nếu chọn số đằng trước -->
        //1,2,4,5,6
        //4 có thế kết hợp với (1,2) đều được vì đơn giản (1,4) (1,2) không ăn nhau
        //--> Nhưng chỉ chọn 1 trong 2 vì (1,2) ăn nhau
        //1, Ở đây lâu ngày mình không làm nên hơi ngáo
        //dp[] index của nó gắn với vị trí chứ không gắn với nums[i] tức là độ lớn của số
        //1,2,4,5,6
        //0,1,2,3,4
        //dp[1]=max(chọn 2, không chọn 2)
        //Đề ở đây là nếu chọn x --> xóa "toàn bộ" (x-1), (x+1) trong chuỗi chứ không phải là chỉ xóa 1 số
        //Ta tư duy như sau:
        //Cách 1:
        //Ta tư duy như sau:
        //1, Các số được chia làm 2 kiểu liên tiếp nhau 3 chữ số + (Thuộc nhiều chuỗi số rời rạc và liên tiếp nhau)
        //1.1, --> Công thức ta sẽ chọn số lớn nhất ăn trước --> Luôn luôn được kết quả tối ưu
        //VD: 1,2,3,4 --> Chọn (4) là tối ưu nhất (Do ta sẽ không phụ thuộc vào count (nums[i])
        //Nếu ta tư duy kiểu như thế này --> La do ta ĐỌC SAI ĐỀ
        //---> Ở đây là xóa toàn bộ các số (x-1), (x+1)
        //Nếu ta có: a,b,c,d
        //count = x,y,z,t
        //--> Nếu tư duy việc chọn (3 số thì không có hướng đi nào cả) --> Cho dù là có ct --> CT vẫn không suy ra được toàn bộ array
        //2,
        //Tư duy chọn số, quy hoạch động:
        //+ dp[i]: sẽ lưu kết quả lớn nhất tính đền index thứ (i) của (keys) (các key khác nhau)
        //--> Ở đây ta không chọn nums[i] vì không có phụ thuộc giữa các nums[i]
        //Phân tích bài toán
        //Vị trí (i) có các lụa chọn như sau:
        //+ Nếu key trước đó không (kém key hiện tại 1 đơn vị) : keyBefore= currentValue + 1
        //EX: 1,2
        //Ta chỉ có thể chọn (2/1)
        //dp[index]=Max (của tất cả các trường hợp có thể chọn tại vị trí index) = Max(dp[index-1], dp[index-2])
        //VD: 1,1,1,2,4,5,5,5,6
        //1,2,4,5,6
        //Nếu thiếu trường hợp này thì sẽ sai tại key=5 (index3) --> Sẽ không được tĩnh theo (2, index=1) + (2, index=1) được tính theo (1, index=0)
        //Chứ không phải lấy kết quả vị trị (2) =2 --> result=17 (Sai)
        //--> Result=18 mới đúng
        //+ Nếu key trước đó không liên quan đến key hiện tại:
        //--> Ta có thể lấy luôn key trước đó + với key*count[key]
        //dp[index]=dp[index-1] + key*count[key]

        //Cách 2:

        int arr[]=new int[]{1,1,1,2,4,5,5,5,6};
        System.out.println(deleteAndEarn(arr));
    }
}
