package E1_two_pointers;

public class E17_MagicalString {

    public static int magicalStringWrong(int n) {
        if(n==0){
            return 0;
        }
        if(n==1){
            return 1;
        }
        char prevChar = '1';
        StringBuilder newStr=new StringBuilder("1");
        StringBuilder rsStr=new StringBuilder("1");
        int len=1;
        int rs=1;

        //n=6
        //1221
        //1221|12
        //  + 1221 tính rồi
        //  + 12 sau đó chưa tính
        //122112|122
        //  + 122
        //==> Tính cho 122
        while(len<n){
            StringBuilder nextNewStr=new StringBuilder();

            int initChar=(prevChar=='1')?2:1;
            int newLen=newStr.length();

            //Count for each character
            for(int i=0;i<newLen;i++){
                if(newStr.charAt(i)=='2'){
                    nextNewStr.append(initChar).append(initChar);
                    if(initChar==1){
                        rs+=2;
                    }
                }else{
                    nextNewStr.append(initChar);
                    if(initChar==1){
                        rs+=1;
                    }
                }
                if(initChar==1){
                    initChar=2;
                }else{
                    initChar=1;
                }
            }
            int m = newStr.length();
            prevChar=newStr.charAt(m-1);
            newStr=nextNewStr;
            System.out.println(nextNewStr);
            len+=newStr.length();
            rsStr.append(nextNewStr);
        }
        System.out.println(rsStr);
        return rs;
    }

    public static int magicalString(int n) {
        if (n <= 0) return 0;
        if (n <= 3) return 1;

        StringBuilder s = new StringBuilder("122");
        int i=2;

        while(s.length()<n){
            int num = s.charAt(i)-'0';
            int newChar=(s.charAt(s.length()-1))=='1'?2:1;

            if(num==1){
                s.append(newChar);
            }else{
                s.append(newChar).append(newChar);
            }
            i++;
        }
//        System.out.println(s);
        int rs=0;

        for(i=0;i<n;i++){
            if(s.charAt(i)=='1'){
                rs++;
            }
        }
        return rs;
    }

    public static void main(String[] args) {
        //** Requirement
        //- A magical string s consists of only '1' and '2' and obeys the following rules:
        //- The string s is magical because concatenating the number of contiguous occurrences of characters '1' and '2'
        // generates the string s itself.
        //The first few elements of s is s = "1221121221221121122……".
        // If we group the consecutive 1's and 2's in s, it will be "1 22 11 2 1 22 1 22 11 2 11 22 ......"
        // and the occurrences of 1's or 2's in each group are "1 2 2 1 1 2 1 2 2 1 2 2 ......".
        // You can see that the occurrence sequence is s itself.
        //Given an integer n,
        //* Return (the number of 1's in the first n number) in (the magical string s).
        //
        //** Idea
        //1.
        //1.0,
        //* Method-1:
        //- Constraint
        //1 <= n <= 10^5
        //==> Khá lớn
        //
        //- Brainstorm
        //Ex
        //1221121
        //1|22|11|2|1
        //1|2|2|1|1
        //
        //1221121221221121122
        //1 22 11 2 1 22 1 22 11 2 11 22
        //1 2 2 1 1 2 1 2 2 1 2 2
        //
        //122112
        //  + 1|22|11|2
        //1|2|2|1
        //  + 1|22|1
        //121
        //
        //- Đại loại là tìm magic string với length=n
        //- Ta thấy rằng các magic string ==> Phụ thuộc lẫn nhau
        //  + Do mỗi lần biến đổi --> reduce string
        //
        //- Ta có thể gen xuôi
        //- Ta cần init string
        //s = 1221
        //+ Nếu gen tiếp:
        //s = 1|22|1
        //s = 121
        //- Nếu gen ngược:
        //  - 2 :
        //      + 11
        //      + 22
        //  - 1:
        //      + 1
        //      + 2
        //- Nếu muốn gen ra 1221:
        //122112
        //  + 1|22|11|2
        //  = 1221
        //122112
        //1221
        //- Vì string ngắn hơn sẽ cùng prefix với string dài hơn
        //  + Ta chỉ cần suy luận ra cách thêm characters là được
        //1221
        //122112
        //122112122
        //==> Ta có thể dễ dàng generate vì:
        //  + Ta có ký tự đầu là 1 fix
        //  ==> Theo sau đó chắc chắn là 2 (Vì nếu là 1 thì đã group vào được r)
        //  + 2: ==> Chỉ có thể là 22
        //  + 1: ==> Trước là 2 nên sau là 1
        //....
        //Ta sẽ generate cho đến khi (length == n)
        //
        //- Vấn đề là length <= 10^5
        //  + Time <= O(n*k)
        //
        //- Gen liên tục có được không?
        //prev:    1221
        //current: 1221(12)
        //
        //- Khởi tạo character với length ==n trước:
        //n=6
        //1221
        //1221|12
        //  + 1221 tính rồi
        //  + 12 sau đó chưa tính
        //122112|122
        //  + 122
        //==> Tính cho 122
        //
        //1
        //1(2)
        //  + 1 tính ra 2
        //  + newStr=2
        //1(22)
        //  + prev=1 ==> 2 tính ra 22
        //  + Ta thấy đang append thêm 2
        //  + newStr=22
        //  ==> match prevString <-> curstr:
        //  + new = 2
        //* Problem:
        //- Vấn đề ở đây là việc gen mỗi step có thể khác nhau
        //Ex
        //n=6
        //* Ta sẽ tăng dần index lên vì sao?
        //  - Một step ta có thể add 1 char ==> val==1
        //  - Một step ta có thể add 2 char ==> val==2
        //      + Mỗi character sẽ được tạo ra charater khác dần dần
        //+ index=0
        //1
        //+ index=1:
        // 1(2)
        //  + Append 2 vào ==> xét luôn (2)
        //  ==> 1|22
        //+ index=2
        //12(2)
        //  + Xét 2, prev=2
        //  + 1|22|11
        //+ index=3
        //122(1)1
        //
        //1.1, Optimization
        //1.2, Complexity
        //- Space: O(n)
        //- Time : O(n)
        //
        //2. Two pointers
        //2.1,
        //
        //
        //#Reference:
        //1189. Maximum Number of Balloons
        //1896. Minimum Cost to Change the Final Value of Expression
        //3078. Match Alphanumerical Pattern in Matrix I
        //
        int n=6;
//        int n=4;
        //121121
//        System.out.println(magicalStringWrong(n));
        System.out.println(magicalString(n));
    }
}
