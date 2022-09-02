package interviews;

public class E120_EulerTotientFunction {

    public static int gdc(int a, int b){
        if(a==0){
            return b;
        }
        return gdc(b%a, a);
    }

    public static int phi1(int n){
        int rs=1;

        for(int i=2;i<=n;i++){
            if(gdc(i,n)==1){
                rs++;
            }
        }
        return rs;
    }

    public static int phi2(int n){
        int rs=1;


        return rs;
    }

    public static void main(String[] args) {
        System.out.println(phi1(30));
        System.out.println(phi1(1337));
        //Để bài : tìm số x nằm trong khoảng (1 --> N-1) sao cho:
        //+ Nguyên tố vs N : Ước chung lớn nhất (Greatest Common Divisor) [ gdc(i, n) ==1 ]
        //
        //Bài này tư duy như sau:
        //Cách 1:
        //1, Ta sẽ tuy duy tìm ước chung lớn nhất của 2 số gdc(a,b)
        //VD:
        //gdc(12,30)
        //+ 12%x==0 => 12 = x*k
        //+ 30%x==0 => 30 = y*k
        //step 1: 30 % 12 !=0 ==> Có 2 trường hợp xảy ra:
        //+ 30 gấp (m>2) lần 12 (Trong trường hợp m=2) ---> Dư ra 1 phần (h*k) (Do 12%k ==0)
        //( h*k < x*k (12) )
        //Câu hỏi tại sao : lấy a=b%a
        //+ Ta cần thu gọn dần a : (b%a) %k == 0 (Do a,b đều %k==0)
        //** ==> Ở đây ta sẽ thu gọn số (x, y)
        //VD :
        //12, 30
        //(x), (y)
        //-->
        //0, a (Chính là kết quả)
        //
        //+ Nếu dùng phép trừ (x, y) --> (y-x,x)
        //12, 30
        //18, 12 (chỗ này 18 >12 hơi ngược)
        //4, 8
        //0, 4
        //
        //+ Nếu dùng phép trừ (x, y) --> (y%x,x)
        //12, 30
        //6, 12
        //0, 6
        //===> có 2 cách biến đổi:
        //(x), (y) ==> (y, x-y)/ (y, x%y)
        //==> Cách dùng % sẽ triệt tiêu được nhiều hơn + không xảy ra việc (a-b>b) (Khi ta chia 2 phía) ==>
        //- gdc(x, y) = gdc(y, x%y)
        //- Ước chung lớn nhất của (a,b) = ước chung của các số (<b) + Vẫn chia hết cho k (%k==0)
        //
        //** KINH NGHIỆM :
        //- (Tư duy thu gọn) (với các số %=0) (x, y) ==> (0*k, a*k) (k là ước chung lớn nhất)
        //- có 2 cách biến đổi:
        //(x), (y) ==> (y, x-y)/ (y, x%y)
        //==> Cách dùng % sẽ triệt tiêu được nhiều hơn + không xảy ra việc (a-b>b) (Khi ta chia 2 phía) ==>
        //+ gdc(x, y) = gdc(y, x%y)
        //+ Ước chung lớn nhất của (a,b) = ước chung của các số (<b) + Vẫn chia hết cho k (%k==0)
        //
        //Complexity : n(log(n))
        //Cách 2:
        //
    }
}
