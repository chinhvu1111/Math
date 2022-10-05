package interviews;

public class E172_24Game {

    public static double calculate(int a, double b, char c){
        double rs=0;
        if(c=='+'){
            return a+b;

        }
        if(c=='-'){
            return b-a;

        }
        if(c=='*'){
            return b*a;

        }
        if(c=='/'){
            return a!=0?b/a:0;

        }
        return 0;
    }

    public static boolean judgePoint24(int[] cards) {

        return true;
    }

    public static void main(String[] args) {
        //
        //** Đề bài:
        //-
        //
        //Time complexity:
        //Mỗi bước có O(n^2) 2 số lựa chọn lấy ra
        //Sau mỗi bước --> giảm số lượng (n-1)
        //O(N^2)^N = O(N^(2*N))
        //==> Đưa ra 1 cận trên để đánh giá.
        //- Đưa ra số quyết định + số lựa chọn của mỗi quyết định.
        //
    }
}
