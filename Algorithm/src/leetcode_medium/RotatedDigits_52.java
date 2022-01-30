package leetcode_medium;

import java.util.HashMap;

public class RotatedDigits_52 {

    public static int rotatedDigits(int n) {
//        map.put(2,true);
//        map.put(5,true);
//        map.put(6,true);
//        map.put(9,true);
        int flag[]=new int[n+1];
        flag[0]=2;
        if(n>=1){
            flag[1]=2;
        }
        if(n>=8){
            flag[8]=2;
        }
        if(n>=2){
            flag[2]=1;
        }
        if(n>=5){
            flag[5]=1;
        }
        if(n>=6){
            flag[6]=1;
        }
        if(n>=9){
            flag[9]=1;
        }
        int rs=0;

        for(int i=1;i<=n;i++){
            int residual=i%10;
            if(flag[residual]==0){
                continue;
            }
            if((residual==1||residual==0||residual==8)&&(flag[i/10]==2)){
                flag[i]=2;
                continue;
            }
//            if(residual==i/10){
//                continue;
//            }
            if(flag[i/10]!=0){
                flag[i]=1;
                rs++;
            }
        }
        return rs;
    }

    public static void main(String[] args) {
        int n=99;

        //Bài này ta tư duy như sau:
        //1, Để xác định được số đó có (đảo có ra số mới/ Có thể đảo được không)
        //VD:
        //Số (i) đảo được khi:
        //+ (i/10) đảo được
        //+ i%10 đảo được
        //2, Ta phải loại ra các trường hợp mà:
        //+ Đứng cuối là (1,0,8)
        //+ Số đằng trước không đảo được/ Đảo được ra đúng chính nó
        //3, Ta cần đánh dấu 1 số có 3 khả năng:
        //+ Không đảo được dp[i]=0
        //+ Đảo ra số khác dp[i]=1
        //+ Đảo ra số giống dp[i]=2
        //4, Khởi tạo
        // [0,1,8]=2
        // [3,5,6,9]=1
        System.out.println(rotatedDigits(n));
    }
}
