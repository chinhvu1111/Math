package interviews.vng;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class E1_CovidSpanProblem {
    public static String solution(String s){
        String[] numsStr = s.split(",");
        int n=numsStr.length;
        if(n<=1){
            return "-1";
        }
        int[] nums=new int[n];
        int[] indexNearest=new int[n];
        Arrays.fill(indexNearest, -1);

        for(int i=0;i<n;i++){
            nums[i]=Integer.parseInt(numsStr[i]);
        }
        int currentValue=nums[n-1];
        Integer currentIndex=n-1;

        for(int i=n-2;i>=0;i--){
            if(nums[i]>currentValue){
                indexNearest[i]= currentIndex;
            }else{
                while (nums[i]<=nums[currentIndex]){
                    currentIndex=indexNearest[currentIndex];
                    if(currentIndex==null||currentIndex==-1){
                        break;
                    }
                }
                if(currentIndex==-1||currentIndex==null){
                    indexNearest[i]=-1;
                }else indexNearest[i]= currentIndex;
            }
            currentValue=nums[i];
            currentIndex=i;
//            System.out.printf("%s %s\n",i, indexNearest[i]);
        }
        System.out.println();
        StringBuilder rs=new StringBuilder();
        for (int i = 0; i < n; i++) {
//            System.out.printf("%s %s\n",i, indexNearest[i]);
            if(indexNearest[i]!=-1){
                rs.append(indexNearest[i]-i);
            }else {
                rs.append(-1);
            }
            if(i!=n-1){
                rs.append(",");
            }
        }
        return rs.toString();
    }
    public static void main(String[] args) {
        //** Requirement
        //- Tìm số time để vị trí (i) tìm được số nhỏ hơn nó
        //
        //** Idea
        //- Constraint: Không có
        //- Có 2 tư duy
        //+ Có thể dùng O(n^2) để check với mỗi index
        //+ Dùng quy hoạch động để giảm thời gian quét
        //VD:
        //104,99,76,94,78,77,75
        //--> Chạy left --> right
        //+ Loop từ cuối lên
        //+ 77>75 --> dp[i]=1
        //+ 78>77 --> dp[i]=1
        //+ 94 >78 --> dp[i]=1
        //+ 76<94 --> jump đến so sánh với 78 luôn --> bỏ qua các node như là 100,102,103....
        //--> Lưu lại index của số gần nhất < currentValue
        //+ Nếu new value > currentValue -> lấy index hiện tại
        //+ Nếu new value < currentValue --> Lấy index map tiếp
//        String s="104,99,76,94,78,77,75";
//        String s="";
//        String s="100";
//        String s="100,100";
        Scanner sc = new Scanner(System.in);
        String s=sc.nextLine();
        System.out.println(solution(s));
    }
}
