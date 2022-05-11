/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package leetcode_medium_dynamic;

import java.util.HashMap;

/**
 *
 * @author chinhvu
 */
public class IntegerReplacement_25 {

    public static int integerReplacement(int n) {
        int dp[] = new int[n + 2];

//        for(int i=2;i<=n;i++){
//            if(i%2==0){
//                dp[i]=dp[i/2]+1;
//            }else{
//                int inc=(i+1<=n)?dp[i+1]+1:Integer.MAX_VALUE;
//                int dec=(i-1<=n)?dp[i-1]+1:Integer.MAX_VALUE;
//                
//                dp[i]=Math.min(inc, dec);
//            }
//        }

        dp[2]=1;
        for (int i = 3; i<= n; i+=2) {
            dp[i-1]=dp[(i-1)/2]+1;
//            if(i+1<=n){
//                dp[i+1]=dp[(i+1)/2]+1;
//            }

            //Mình chỉ có thể lý luận là do mình thiếu case với (i=n+1)
            dp[i+1]=dp[(i+1)/2]+1;

            int inc = dp[i + 1] + 1 ;
            int dec = dp[i - 1] + 1 ;

            dp[i] = Math.min(inc, dec);
        }
        return dp[n];
    }
    static HashMap<Integer, Integer> map=new HashMap();
    
    public static int integerReplacement1(int n) {
        int rs=Integer.MAX_VALUE;
        if(n<=1){
            return 0;
        }
        if(n==2){
            return 1;
        }
        if(map.get(n)!=null){
            return map.get(n);
        }
        
        if(n%2==0){
            rs=integerReplacement1(n/2)+1;
        }else{
            int left=integerReplacement1(n-1);
            //Xảy ra tình huống số lớn quá>Integer.MAX_VALUE
            //n --> -2147483647
            //--> Cái ta cần là (giảm số đó đi) ==> (Số nhỏ hơn): Sao cho vẫn cho (kết quả tương tự)
            int right=(n<Integer.MAX_VALUE)?
                    integerReplacement1(n+1):
                    integerReplacement1((n-1)/2+1)+1;
            
            rs=Math.min(left, right)+1;
        }
        map.put(n, rs);
//        if(rs==32){
//            System.out.println(rs);
//        }
//        System.out.println(rs);
        return rs;
    }

    public static void main(String[] args) {
//        int n=8;
//        int n = 7;
//        int n = 4;
//        int n = 65535;
//        int n = 5;
//        int n = 2147483647;
        int n = 100000000;
        //Cách 1 dùng mảng từ (1 --> n)
        System.out.println(integerReplacement(n));
        //1.Xảy ra tình huống số lớn quá>Integer.MAX_VALUE
        //n --> -2147483647
        //--> Cái ta cần là (giảm số đó đi) ==> (Số nhỏ hơn): Sao cho vẫn cho (kết quả tương tự)
        
        //2.Tại sao cách bên trên lại quá bộ nhớ heap khi dùng array, mà không bị quá mảng khi dùng (HashMap)
        //Nếu dùng Array --> Ta cần lưu tất cả các giá trị từ (1 --> n)
        //Ex: 1 --> 100000000: 
        //HashMap thì không lưu hết --> Chỉ lưu khi (100000000/2) --> Để khi gặp (1)
        //KEY: --> Sau đó quay lại chỉ lưu các giá trị trung gian của việc (n/2, n+1, n-1)
        //--> Không phải lưu hết như (Array)
        //--> Khi chia ra như thế thì:
        //1. Số bước chạy ít hơn --> Có thể sử dụng lại kết quả trung gian
        //2. Dùng hashMap: Lưu ít --> Có thể sử dụng lại kết quả trung gian
        //Với tư duy này: Bài này --> Có thể chuyển thành LOOP
        System.out.println(integerReplacement1(n));
        System.out.println("");
    }
}
