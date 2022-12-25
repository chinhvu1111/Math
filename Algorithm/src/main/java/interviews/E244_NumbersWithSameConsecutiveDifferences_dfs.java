package interviews;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class E244_NumbersWithSameConsecutiveDifferences_dfs {

    public static HashSet<Integer> distinctsNumber;

    public static void bfs(int prevDigit, int prevNumber, int currentLength,
                           int diff, int length, List<Integer> rs){
        if(currentLength==length){
            if(!distinctsNumber.contains(prevNumber)){
                rs.add(prevNumber);
                distinctsNumber.add(prevNumber);
            }
            return;
        }
        if(0<=prevDigit+diff&&prevDigit+diff<=9){
            bfs(prevDigit+diff, prevNumber*10+prevDigit+diff,
                    currentLength+1, diff, length, rs);
        }
        if(0<=prevDigit-diff&&prevDigit-diff<=9){
            bfs(prevDigit-diff, prevNumber*10+prevDigit-diff,
                    currentLength+1, diff, length, rs);
        }
    }

    public static void bfsRefactor(int prevDigit, int prevNumber, int currentLength,
                           int diff, int length, List<Integer> rs){
        if(currentLength==length){
            rs.add(prevNumber);
            return;
        }
        if(0<=prevDigit+diff&&prevDigit+diff<=9){
            bfsRefactor(prevDigit+diff, prevNumber*10+prevDigit+diff,
                    currentLength+1, diff, length, rs);
        }
        if(diff==0){
            return;
        }
        if(0<=prevDigit-diff&&prevDigit-diff<=9){
            bfsRefactor(prevDigit-diff, prevNumber*10+prevDigit-diff,
                    currentLength+1, diff, length, rs);
        }
    }

    public static int[] numsSameConsecDiff(int n, int k) {
        List<Integer> rs=new ArrayList<>();
        distinctsNumber=new HashSet<>();

        for(int i=1;i<=9;i++){
            bfsRefactor(i, i, 1, k, n, rs);
        }
        int[] rsArr=new int[rs.size()];
        for(int i=0;i<rs.size();i++){
            rsArr[i]=rs.get(i);
        }
//        System.out.println(rs);
        return rsArr;
    }

    public static void main(String[] args) {
//        int n=3, k=7;
//        int n=2, k=1;
        int n=2, k=0;
        numsSameConsecDiff(n, k);
        //
        //** Đề bài:
        //- Liệt kê các số có 2 chữ số cạnh nhau hơn kém nhau k
        //- Số đó có chiều dài n
        //
        //** Bài này tư duy như sau:
        //1,
        //1.1,
        //- Chọn 1 số 1-->9 --> traverse đến (i-diff)/(i+diff) đến khi length==n
        //--> Add thêm vào kết quả
        //- Kết quả cần distict : Ta đang dùng hashSet để phân biệt.
        //1.2, Nếu muốn bỏ trường hợp duplicated
        //--> Viết thêm if(k==0) return;
        //
        //#Reference:
        //797. All Paths From Source to Target
        //320. Generalized Abbreviation
        //127. Word Ladder
    }
}
