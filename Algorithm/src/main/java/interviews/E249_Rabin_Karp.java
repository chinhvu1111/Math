package interviews;

import java.util.ArrayList;
import java.util.List;

public class E249_Rabin_Karp {

    public static List<Integer> rabinKarp(String t, String p){
        List<Integer> result=new ArrayList<>();
        int m=t.length();
        int n=p.length();

        if(m<n){
            return result;
        }
        //CT
        //hash[i...i+N-1] = T[i]*K^(N-1) + T[i+1]*K^(N-2) + ... + T[i-N-1]*K^0
        //hash( T[i+1...i+N] ) = K * (hash(T[i...i+N-1]) - T[i] * K^(N-1) ) + T[i+N]*K^0

        //1  ví dụ thực tế với k=2
        //CT:
        //1.1,hash(p)=P[0]*2^(n-1) + P[1]*2^(n-2)+ ... + P[N-1]*2^0
        //1.2, Lần lượt tính các substring có độ dài N của T
        //+ Tức là tính theo index thứ (i) [T[i], T[i+N] và (N-1) vị trí (length) của s/t.
        //CT: hash(T[i+1...i+N]) = 2 * ( hash(T[i...i+N-1) - T [i]*2^(N-1) ) + T[i+N]
        //1.3, Để tránh tràn số thì nên mod giá trị hash với một số nguyên tố
        //** Tất cả các phép hash mục đích là hỗ trợ cho việc check nhanh hơn trong việc
        //+ Move giữ các window, ví dụ như việc substring(i, j) --> move đến kết quả của substring(i+1, j+1) mất O(1)
        //--> Thay vì đi tính lại việc tính substring.

        int pHash=0;
        int tHash=0;
        int power=1;
        int mod=1_000_000_007;

        for(int i=0;i<n;i++){
            //Ta chọn k =2 cho đơn giản.
            //Cộng ký tự ở cuối mục đích giống với T[i+N]
            //
             pHash=(pHash*2 + p.charAt(i))%mod;
             tHash=(tHash*2 + t.charAt(i))%mod;
             //1.3
            //- Nếu xảy ra tràn số thì ta cần phải mod số đó với số nguyên tố lớn
            //VD: mod= 10^9+7
             if(i!=0 ){
                 power=(power*2)%mod;
             }
        }
        for(int i=n;i<m;i++){
            if(tHash==pHash&&t.substring(i-n,i).equals(p)){
                result.add(i-n);
            }
            //1,
            //1.1,Dịch window sang bên phải 1 đơn vị của t vì length(t)> length(p)
            //+ Hash(p) được tính cố định
            //+ Ta chỉ dịch dựa trên (t) mà thôi
            //1.2,
            //VD: a-=b
            //==>
            //a-=(b%mod)
            //- Kết quả sẽ không còn đúng vì có thể xảy ra trường hợp:
            //- a%mod==0
            //- b%mod==2
            //--> Thành ra kết quả sẽ có thể bị âm
            //- Nếu ta tính bình thường sẽ không sao --> Chỉ bị tràn số (int32) mà thôi.
            //a+= mod : Để đám bảo a luôn >0.
//            tHash= (tHash- power*t.charAt(i-n))*2  + t.charAt(i);
            //Tách ra, mục đích là để mode
            tHash-=(power*t.charAt(i-n))%mod;
            if(tHash<0){
                tHash+=mod;
            }
            tHash=(tHash*2+t.charAt(i))%mod;

//            pHash= (pHash- power*p.charAt(i-n))*2 + p.charAt(i);
        }
        if(pHash==tHash&&t.substring(m-n, m).equals(p)){
            result.add(n-m);
        }
        return result;
    }

    public static void main(String[] args) {
        String t="AABAABAARAC";
        String p="ABAA";
        System.out.println(rabinKarp(t, p));
        //
        //** Đề bài:
        //- Tìm các index (vị trí bắt đầu) mà string s1 bên trong s2
        //
        //** Bài này tư duy như sau:
        //CT
        //hash[i...i+N-1] = T[i]*K^(N-1) + T[i+1]*K^(N-2) + ... + T[i-N-1]*K^0
        //hash( T[i+1...i+N] ) = K * (hash(T[i...i+N-1]) - T[i] * K^(N-1) ) + T[i+N]*K^0

        //1  ví dụ thực tế với k=2
        //CT:
        //1.1,hash(p)=P[0]*2^(n-1) + P[1]*2^(n-2)+ ... + P[N-1]*2^0
        //1.2, Lần lượt tính các substring có độ dài N của T
        //+ Tức là tính theo index thứ (i) [T[i], T[i+N] và (N-1) vị trí (length) của s/t.
        //CT: hash(T[i+1...i+N]) = 2 * ( hash(T[i...i+N-1) - T [i]*2^(N-1) ) + T[i+N]
        //1.3, Để tránh tràn số thì nên mod giá trị hash với một số nguyên tố
        //** Tất cả các phép hash mục đích là hỗ trợ cho việc check nhanh hơn trong việc
        //+ Move giữ các window, ví dụ như việc substring(i, j) --> move đến kết quả của substring(i+1, j+1) mất O(1)
        //--> Thay vì đi tính lại việc tính substring.
        //
        //1.4,Dịch window sang bên phải 1 đơn vị của t vì length(t)> length(p)
        //+ Hash(p) được tính cố định
        //+ Ta chỉ dịch dựa trên (t) mà thôi
        //1.5,
        //VD: a-=b
        //==>
        //a-=(b%mod)
        //- Kết quả sẽ không còn đúng vì có thể xảy ra trường hợp:
        //- a%mod==0
        //- b%mod==2
        //--> Thành ra kết quả sẽ có thể bị âm
        //- Nếu ta tính bình thường sẽ không sao --> Chỉ bị tràn số (int32) mà thôi.
        //a+= mod : Để đám bảo a luôn >0.
        //tHash= (tHash- power*t.charAt(i-n))*2  + t.charAt(i);
        //Tách ra, mục đích là để mode
        //
        //1.6,
        //- Nếu xảy ra tràn số thì ta cần phải mod số đó với số nguyên tố lớn
        //VD: mod= 10^9+7
    }
}
