package interviews;

public class E13_1_AddDigits_linkedList {

    public static int addDigits(int num) {
        int slow=num;

        while (slow>=10){
            slow=sumDigits(slow);
//            System.out.println(slow);
        }

        return slow;
    }

    public static int addDigitsOptimize(int num) {
        if(num==0){
            return 0;
        }
        return num%9==0?9:num%9;
    }

    public static int sumDigits(int num){
        int rs=0;

        while (num!=0){
            rs+=num%10;
            num=num/10;
        }
        return rs;
    }

    public static void main(String[] args) {
        int n=38;
        System.out.println(addDigits(n));
        //** Đề bài
        //- Cho 1 số --> return final number sau khi thực hiện 1 loạt các thao tác như bên dưới
        //- Các thao tác như sau :
        //Input: num = 38
        //Output: 2
        //Explanation: The process is
        //38 --> 3 + 8 --> 11
        //11 --> 1 + 1 --> 2
        //
        //Bài này tư duy như sau:
        //Cách 1:
        //1, Loop 1 cách bình thường
        //
        //Cách 2:
        //1, Tìm tip, trick của câu này để làm mọi thứ dễ dàng hơn chút:
        //1.1, Lấy sample mẫu để suy nghĩ quy luật
        //--------
        //1 1
        //2 2
        //3 3
        //4 4
        //5 5
        //6 6
        //7 7
        //8 8
        //9 9
        //---------
        //10 1
        //11 2
        //12 3
        //13 4
        //14 5
        //15 6
        //16 7
        //17 8
        //18 9
        //--------
        //19 1
        //20 2
        //21 3
        //22 4
        //23 5
        //24 6
        //25 7
        //26 8
        //27 9
        //-------
        //28 1
        //29 2
        //30 3
        //31 4
        //32 5
        //33 6
        //34 7
        //35 8
        //36 9
        //-------
        //37 1
        //38 2
        //39 3
        //40 4
        //41 5
        //42 6
        //43 7
        //44 8
        //45 9
        //==> Kết quả phụ thuộc vào số 9
        //- CT: return num%9==0?9:num%9;
        //+ Case đặc biệt (num==0) return 0.
        //2, #Reference
        //
        //- Count Primes
        //- Ugly Number II
        System.out.println(addDigitsOptimize(n));
    }
}
