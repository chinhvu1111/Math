package interviews;

public class E118_Pow_x_n_binary_classic {

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

    public static double myPowOptimize(double x, int n){
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

    public static double myPowIterativeWrong(double x, int n){
        if(n==0){
            return 1;
        }
        int expo=n;

        //Một trong những lỗi nên cẩn thận khi làm bài này
        if(expo<0){
            if(expo!=Integer.MIN_VALUE){
                expo=expo*-1;
            }else{
                expo--;
            }
            x=1/x;
        }

        double rs=1;
        double number=x;
        int count=1;

        //Ex : expo=2
        while (count<expo){
//            if(expo>1){
//            }
//            rs*=x;
//            if(expo%2==1){
//                rs*=number;
//            }
//            expo/=2;
            count*=2;
            if(count<=expo){
                x=x*x;
            }
            System.out.printf("%s %s\n",x);
        }
        if(count/2<expo){
            x*=number;
        }
        if(n==Integer.MIN_VALUE){
            return x*number;
        }
        return x;
    }

    /*
    Tư duy liên quan đến đối xứng VD: (5,2,1) <=> không tương tự (1,2,5)
    //Nếu làm kiểu này sẽ sai case
    VD; 5(x), 2(x*x*(x)), 1 (x*x*x)*(x*x*x)
    //---> (5,2,1) không cùng chiều (1,2,5)
    //VD: 1.2.5 tư duy trên thì đúng
    //1(x), 2(x*x), 5(x*x)*(x*x)*(x) (Vì chuyển từ 2 --> 5 cần phải thêm *)
    //===> Quy luật kiểu này phải bắt đầu từ (small --> big)
     */
    public static double myPowIterativeWrong1(double x, int n){
        if(n==0){
            return 1;
        }
        int expo=n;

        //Một trong những lỗi nên cẩn thận khi làm bài này
        if(expo<0){
            if(expo!=Integer.MIN_VALUE){
                expo=expo*-1;
            }else{
                expo--;
            }
            x=1/x;
        }
        double number=x;
        double rs=1;
        double previous=expo;

        //Ex : expo=2
//        while (expo>1){
//            x=x*x;
//            System.out.printf("%s %s\n", previous, expo);
//
//            if(previous%expo==1){
//                x*=number;
//            }
//            previous=expo;
//            expo/=2;
//            rs=x;
//            System.out.printf("%s %s\n\n", rs, expo);
//        }
        expo/=2;
        while (expo!=0){
            System.out.printf("%s %s\n", previous, expo);
            x=x*x;
            if(previous!=(expo*2)){
                x*=number;
            }
            previous=expo;
            expo/=2;
            if(expo==0){
                break;
            }
            rs=x;
            System.out.printf("%s %s %s\n\n", x, rs, expo);
        }
        if(n==Integer.MIN_VALUE){
            return x*number;
        }
        return x;
    }

    public static double myPowFinal(double x, int n) {
        long exponent=n;

        if(n<0){
            exponent=-(long)n;
            x=1/x;
        }
        double rs=1;

        while(exponent!=0){
            if(exponent%2==1){
                rs*=x;
            }
            x*=x;
            exponent/=2;
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
//        System.out.println(myPow(2, -2147483648));
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
        //1.4, Nếu min=-2147483648
        //--> min= min *(-1) (Vẫn <0)
        //** max = 2147483647 && min = -2147483648
        //==> Convert như trên sẽ bị quá range.
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
        //3,
        //3.1, Dùng loop để giải quyết:
        //- Với cách này sẽ khó hơn khi tư duy theo kiểu (top - down)
        //VD; 1(x),2(x*x),5 (x*x)*(x*x)*(x)
        //---> Kiểu này không tư duy được do ta không biết chạy kiểu gì từ (1 --> đến được (5))
        //Chỉ có thể tư duy dạng (bottom - up) được:
        //VD: 5,2,1 (Chia 2 dần)
        //3.2, Các lỗi sai khi code phương pháp này:
        //Có 2 kiểu tư duy cơ bản dẫn đên sai:
        //- Coi việc thứ tự top-down <=> bottom-up
        //+ Nếu tư duy sai thì việc dùng số lẻ có thể đóng vai trò/ không đóng vai trò gì?
        //VD: 5,2,1 : (5 lẻ thì chọn 1 hay x ) ==> Ta sẽ chọn (x)
        //---> Vì nếu 10,5,2,1 thì quy định (10,5 là chẵn và lẻ) ===> Tuân theo quy định số đầu tiên của array thay vì giá trị
        //---> init =x
        //số cuối (1) --> thì sao
        //VD: 5,2,1 với case này thì :
        //+ Nếu tư duy theo kiểu số lẻ * thêm x ==> 1 phải được tính
        //5(x), 2(x*x),1(x*x)(x*x)*(x)
        //VD: 10(x),5(x*x)*(x),2,1 ==> Tuy duy theo kiểu số lẽ SAI.
        //+ Nếu tư duy theo kiểu kiểm tra số trước số sau:
        // VD: 5,2 (5!= 2*2) ==> * thêm x
        //Check:
        //VD: 10(x),5(x*x),2(x*x)*(x*x)*x,1 (5*5) ===> Cái này tính cả số 1
        //VD : 5(x),2(x*x)*x,1 ==> Case này tính không ra kết quả đúng
        //** ==> Tính theo (5,2,1) != (1,2,5)
        //
        //3.3, Dùng phương pháp binary
        //- Mục đích tính x^N:
        //---> Chia N ra thành nhiều số, có thể biểu diễn như
        //- N = 1+2,...
        //- N biểu diễn theo binary.
        //VD: N = 9 = 2^3 + 2^0 = 1001 in binary
        //Lúc đó x^N = x^(2^3) * x^(2^0) (**)
        //Biểu diễn 9 --> binary bằng cách 9%2 lần lượt (9/2)
        //+ Từ (**) : kết quả sẽ bằng [ 2^ của những (bit=1) ]
        //3.4, Code như sau:
        //- for (số lượng bit)
        //- Ta sẽ tính mọi giá trị kể cả các bit (0,1) : x=x*x
        //- Ta chỉ lấy value của các (bit==1)
        //
        //<> 10,
        //SAI DO : 5(x),2(x*x),1
//        System.out.println(myPow(2.1, 3));
//        System.out.println(myPow(2, -2147483648));
//        System.out.println(myPow(2, 5));
//        System.out.println(myPowIterative(2.1, 3));
        //
//        System.out.println(myPowIterative(2, -2147483648));
        //Case 2: Case bình thường
//        System.out.println(myPowIterative(2, 10));
        //Case 3: Case dạng 5 --> 2 --> 1
        //==> Nếu bỏ qua 1 thì có thể sẽ sai (Khi không khởi tạo giá trị ban đầu rs là x).

        //Complexity:
        //f(2n) --> f(n)
        //f(2n+1) --> f(n)
        //n,n/2,,...1
        //Đô phực tạp là số steps : log(2)(n)
        //1 --> 2 --> 4 -->8
        //Có s bước
        //2^s=n ==> s= log2(n)
        System.out.println(myPowIterativeWrong1(2, 5));
        //2.1,
        //(2.1*2.1,n/2)
        System.out.println(myPowOptimize(2, 5));
        System.out.println(myPowFinal(2, 5));
    }
}
