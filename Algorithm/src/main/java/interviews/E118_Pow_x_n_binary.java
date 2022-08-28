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

    public static double myPow(double x, int n) {
        int space=Math.abs(n);
        dp=new double[space+1];
        dp[0]=1;
        if(space>=1){
            dp[1]=x;
        }
        return calculate(x, n);
    }

    public static void main(String[] args) {
//        System.out.println(myPow(2, 10));
//        System.out.println(myPow(2.1, 3));
//        System.out.println(myPow(2, -2));
//        System.out.println(myPow(0.44528, 0));
//        System.out.println(myPow(8.95371, -1));
        System.out.println(myPow(0.00001, 2147483646));
    }
}
