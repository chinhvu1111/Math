package interviews;

public class E118_Pow_x_n_binary {

    public static double dp[];

    public static double calculate(double x, int expo){
        int currentExpo=1;
        int newExpo=Math.abs(expo);

        while(currentExpo<=newExpo){
            int maxExp=newExpo-currentExpo;
//            System.out.printf("%s %s\n", currentExpo, maxExp);

            for(int i=1;i<=maxExp;i++){
                if(dp[i+currentExpo]==0){
                    dp[i+currentExpo]=dp[i]*dp[currentExpo];
                }
            }
            if(dp[newExpo]!=0){
                break;
            }
            currentExpo++;
        }
        if(expo<0){
            return 1/dp[newExpo];
        }
        return dp[expo];
    }

    public static double myPowWrong(double x, int n) {
        int space=Math.abs(n);
        dp=new double[space+1];
        dp[0]=1;
        if(space>=1){
            dp[1]=x;
        }
        return calculate(x, n);
    }

    public static double myPow(double x, int n){
        if(n==0){
            return 1;
        }
//        if(n<0){
//            return 1/pow(x, -n);
//        }
//        return pow(x, n);
        if(n<0){
            return pow(1/x, -n);
        }
        return pow(x, n);
    }

    public static double pow(double x, int n) {
        if(n==0){
            return 1;
        }
        if(n==1){
            return x;
        }
//        System.out.printf("%s %s\n", x, n);
        return (n%2==0)? pow(x*x, n/2):x*pow(x*x, n/2);
    }

    public static double myPowIterative(double x, int n){
        if(n==0){
            return 1;
        }
        int expo=n;
        double rs=x;
        double number=x;

        while (expo>0){
            x=x*x;
            rs*=x;
            System.out.printf("%s %s\n",x, rs);
            if(expo%2==1){
                rs*=number;
            }
            expo/=2;
        }
        if(n<0){
            return 1/rs;
        }
        return rs;
    }

    public static void main(String[] args) {
//        System.out.println(myPow(2, 10));
//        System.out.println(myPow(2.1, 3));
//        System.out.println(myPow(2, -2));
//        System.out.println(myPow(0.44528, 0));
//        System.out.println(myPow(8.95371, -1));
        //Case 1: Nếu n exceed space
//        System.out.println(myPow(0.00001, 2147483647));
        System.out.println(myPow(2, -2147483648));
        //Bài này tư duy như sau:
        //Cách 1:
        //1,
        //1.1, Dùng quy hoạch động để giảm thời gian chạy xuống
        //VD: 1,(2)
        //--> Ta có thể tính kết quả 3, 4
        //1,2,(3),4 --> Ta có thể tính được các refered : 1,2,3, (5, 6=(3+3)
        //1,2,3,4,5,6 ---> Ta có thể tính được các refered : 1,2,3,4,5,6,7,8,9,10,11 (5, 6=(3+3)
        //1.2, Quy luật là từ độ phức tạp O(n^2):
        //--> Ta có thể SCAN all được các phần tử
        //1.3, Chú ý các cases có thể sai:
        //- n<0
        //+ MAX_INTEGER --> (MAX_INTEGER + 1) <0
        //+ MAX_INTEGER --> Quá size array.
        //
        //Cách 2:
        //2,
        //2.1, Dùng cách chia đều 2 phần ---> Để giảm độ phức tạp log(n)
        //- If (n%2==0) pow(x,n) = pow(x*x, n/2)
        //- If (n%2==1) pow(x,n) =( x * pow(x*x, n/2) )
        //2.2, Chú ý:
        //- if(n==0) return 1
        //- if(n==1) return x
        //
        //Cách 3:
        //
        System.out.println(myPow(2.1, 3));
        System.out.println(myPowIterative(2.1, 3));
    }
}
