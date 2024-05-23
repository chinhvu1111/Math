package E1_leetcode_medium_dynamic;

import java.util.Arrays;

public class E145_GreatestSumDivisibleByThree {

    public static int maxSumDivThree(int[] nums) {
        int[] dp=new int[3];
        int n=nums.length;

        for(int i=0;i<n;i++){
            int oldDpZero=dp[0];
            int oldDpOne=dp[1];
            if(nums[i] % 3 == 0 || dp[(3 - nums[i] % 3) % 3] != 0){
                dp[0]=Math.max(dp[0], dp[(3-nums[i]%3)%3]+nums[i]);
            }
            if(nums[i]%3==1){
                dp[1]=Math.max(dp[1], oldDpZero+nums[i]);
            }else if(nums[i]%3==0&&dp[1]!=0){
                dp[1]=Math.max(dp[1], dp[1]+nums[i]);
            }
            if(nums[i]%3==2){
                dp[2]=Math.max(dp[2], oldDpZero+nums[i]);
            }else if(nums[i]%3==0&&dp[2]!=0){
                dp[2]=Math.max(dp[2], dp[2]+nums[i]);
            }else if(nums[i]%3==1&&oldDpOne!=0){
                dp[2]=Math.max(dp[2], oldDpOne+nums[i]);
            }
            System.out.printf("Index= %s, val= %s: ", i, nums[i]);
            for(int j=0;j<3;j++){
                System.out.printf("%s,", dp[j]);
            }
            System.out.println();
        }
        return dp[0];
    }

    public static int maxSumDivThreeRefer(int[] nums) {
        int[] dp = new int[3];
        for(int e: nums){
            int[] cp=Arrays.copyOf(dp, 3);
            for(int sum: cp){
                dp[(sum+e)%3]= Math.max(dp[(sum+e)%3], sum+e);
            }
        }
        return dp[0];
    }

    public static void main(String[] args) {
        //** Requirement
        //- Given an integer array nums,
        //* Return (the maximum possible sum of elements) of the array such that (it is divisible by three).
        //
        //** Idea
        //1.
        //1.0,
        //* Method-1:
        //- Constraint
        //1 <= nums.length <= 4 * 10^4
        //1 <= nums[i] <= 10^4
        //==> Length khá lớn nên có thể làm trong O(n*k)
        //
        //- Brainstorm
        //Ex:
        //Input: nums = [3,6,5,1,8]
        //Output: 18
        //Explanation: Pick numbers 3, 6, 1 and 8 their sum is 18 (maximum sum divisible by 3).
        //- Sum %3==0:
        //  + Không nhất thiết all elements %3==0
        //  Ex:
        //  5+5+8 = 18%3 ==0
        //- List all sum ==> Khá lớn
        //
        //- Liệu ta có thể tính sum all ==> Liệt kê những (sum %3==0)
        //  + Mỗi sum -> check xem có tạo từ array được không?
        //  ==> Chỉ có thể dùng recursively (Với n lớn) / bit (với n nhỏ) : Khá khó để check
        //
        //- Dù ta có thể có tính chất:
        //  + sum1%3 =mod
        //  + sum2%3 =mod
        //  => (sum2-sum1)%3==0
        //- Câu hỏi là làm sao để có thể lấy all sum từ (0 --> i)
        //Ex:
        //Input: nums = [3,6,5,1,8]
        //- Length=1
        //  + n sum : 3,6,5,1,8
        //- Length=2:
        //  + 10 sum = 5!/2!*(5-2)! = 1*2*3*4*5/2/6 = 10.
        //==> Khá lớn để xét all sums 1 cách bình thường.
        //
        //* Hint:
        //- dp[pos][mod] : Là max sum %3 = mod cho đến vị tri index=pos
        //
        //- Giả sử x%3 chỉ có thể là 0,1,2
        //- Nếu xét thêm nums[i+1] so với (0 -> i) như cũ.
        //  ==> Việc xét thêm này chưa thấy có tính chất gì đặc biệt cả?
        //- Questions:
        //- Ta cần tận dụng lại tính chất hiệu 2 sum có module bằng nhau
        //* Tính chất bù module:
        //  + Không phải chỉ mỗi hiệu --> ta có thể bù module
        //* CT:
        //- a%b=x
        //- c%b=y
        // ==> (a+c)%(x+y)==0
        //  + Nhưng số lượng 1 và 2 cần phải controlled đúng
        //  Ex:
        //  1 + 1 + 2 = 4 %3 = 1 (Không chia hết cho 3)
        //- Again:
        //- Nếu xét thêm nums[i+1] so với (0 -> i) như cũ.
        //  + if nums[i+1]%3:
        //   + ==0: ==> Gắn với pos[j][0]
        //   + ==1: ==> Gắn với pos[j][2]
        //   + ==2: ==> Gắn với pos[j][1]
        //==> Chỉ cần array 1 chiều là được.
        //Ex:
        //nums = [3,6,5,1,8]
        //+ i=0:
        //  nums[i]=3%3==0
        //  + dp[0]+=3
        //+ i=1:
        //  nums[i]=6%3==0
        //  + dp[0]+=6
        //+ i=2:
        //  nums[i]=5%3==2
        //  + dp[2]+=5
        //  + dp[0]+=dp[1]+2 ==> Ở đây ta không tính dp[0] luôn do dp[1], dp[2] có thể cộng dồn.
        //+ i=3:
        //  nums[i]=1%3==1
        //  + dp[1]+=1
        //+ i=4:
        //  nums[i]=8%3==2
        //  + dp[2]+=8
        //  -> dp[2]=13
        //- Nếu để đến cuối tính : dp[0] = dp[1]+dp[2]
        //===> WRONG IDEA
        //- Dp[0] : chỉ max sum %3 ==0
        //- Dp[1] : chỉ max sum %3 ==1
        //- Dp[2] : chỉ max sum %3 ==2
        //  ==> Cộng thêm là sai.
        //- Mỗi lần ta sẽ cần update max chứ không phải cộng thêm.
        //Ex:
        //nums = [3,6,5,1,8]
        //- i=0:
        //  + dp[0] = 3
        //  + dp[1] = 0
        //  + dp[2] = 0
        //
        //- i=1:
        //  if e=6%3==0:
        //      dp[0] = max(dp[0], dp[0]+e)
        //  + dp[0] = 3 :
        //  + dp[1] = 0
        //  + dp[2] = 0
        //
        //- i=2:
        //  + dp[0] = 9
        //  if e=5%3==2:
        //      dp[0] = max(dp[0], dp[1]+5)
        //  + dp[1] = 0
        //  if e=5%3==2:
        //      dp[2] = max(dp[2], dp[0]+5)
        //==> code
        //for j: 1 -> 3:
        //  dp[j] = max(dp[j], dp[3-num[i]%3]+e)
        //  ==> Cần phải % tiếp bên ngoài:
        //  dp[3-num[i]%3] => dp[(3-num[i]%3)%3] vì : dp[3] sẽ lỗi.
        //  ** Thêm phần tử mới thì mới update 0 dựa trên max 1 và 2
        //** WRONG IDEA:
        //  - Thiếu case: dp[2] = dp[1]+ [phần tử mới %2==1]
        //** NOTE:
        //- (Sum 0,1,2) vẫn đang được tính riêng rẽ không cộng dồn:
        //  + Cộng dồn --> Duplicated sai.
        //Ex:
        //int[] nums = {5,2,2,2};
        //5+2+2 = 9 --> Nhưng không capture được
        //  + Do 0 tạo từ 2 và 1 không xuất hiện do không lấy new sum %3
        //- Gía trị cũ của [0,1,2] là [x,y,z] => Kết hợp với new element là x
        //  ==> Tính new values cho [0,1,2]  1 trong x+a,y+a,z+a.
        //--> dp[(oldSum+a)%3] = max(dp[(oldSum+a)%3], oldSum+a)
//        int[] nums = {3,6,5,1,8};
//        int[] nums = {1,2,3,4,4};
        //
        //** Khá khó + hại não
        //
        //1.1, Optimization
        //1.2, Complexity
        //- Space: O(const)
        //- Time : O(n)
        //
        //#Reference:
        //1851. Minimum Interval to Include Each Query
        //2604. Minimum Time to Eat All Grains
        //923. 3Sum With Multiplicity
        //
        int[] nums = {5,2,2,2};
        System.out.println(maxSumDivThree(nums));
        System.out.println(maxSumDivThreeRefer(nums));
    }
}
