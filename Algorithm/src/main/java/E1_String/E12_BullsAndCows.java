package E1_String;

import java.util.HashMap;

public class E12_BullsAndCows {

    public String getHint(String secret, String guess) {
        //Space : O(n)
        HashMap<Integer,Integer> indexToVal=new HashMap<>();
        //Space : O(10)
        int[] countS=new int[10];
        int n=secret.length();

        //Time : O(n)
        for(int i=0;i<n;i++){
            indexToVal.put(i, secret.charAt(i)-'0');
            countS[secret.charAt(i)-'0']++;
        }
        int correctNumCount=0, incorrectNumCount=0;

        //Time : O(n)
        for(int i=0;i<n;i++){
            int curNum=guess.charAt(i)-'0';

            if(indexToVal.get(i)==curNum){
                correctNumCount++;
                countS[curNum]--;
            }
        }
        //Time : O(n)
        for(int i=0;i<n;i++){
            int curNum=guess.charAt(i)-'0';
            // System.out.printf("%s %s\n", guess.charAt(i), countS[curNum]);

            if(indexToVal.get(i)==curNum){
                continue;
            }
            if(countS[curNum]<=0){
                continue;
            }
            countS[curNum]--;
            incorrectNumCount++;
        }
        return correctNumCount+"A"+incorrectNumCount+"B";
    }

    public static void main(String[] args) {
        //* Requirement
        //- Cho 1 số secret và guess là số mà người bạn kia đoán
        //* Return string format as xAyB với:
        //+ x là số lượng số đoán đúng
        //+ y là số lượng số mà có thể sắp xếp lại ==> Có thuộc chuỗi secret
        //
        //** Idea
        //1.
        //1.0, Idea
        //- Constraint
        //1 <= secret.length, guess.length <= 1000
        //secret.length == guess.length
        //secret and guess consist of digits only.
        //
        //- Brainstorm
        //Ex:
        //secret = "1807", guess = "7810"
        //1807
        //7810
        //- Hashmap cho mỗi index ==> value bằng bao nhiêu
        //- Sau đó tính số lượng chữ số đoán đúng và sai
        //Ex:
        //secret = "1123", guess = "0111"
        //1123
        //0111
        //1A1B
        //--> Mỗi value (non-bull) chỉ có thể sắp xếp lại để trở thành bull <=> 1 trong 2 số 1 ==> Có thể được sắp xếp lại vào index=0 (Ở đây cũng là value=1)
        //==> 1 trong 2 số 1 khi được sắp xếp lại sẽ thành cows ==> return 1
        //
        //- Idea
        //+ count số lượng từng ký tự của secret
        //+ Sau đó khi traverse guess thì ta sẽ:
        //  + Nếu đúng thì cộng vào correct + count[curVal]--
        //  + Nếu sai thì check với count của secret: Nếu count>0 + vào incorrect + count[curVal]-- ==0 ==> Bỏ qua.
        //Ex:
        //1122
        //1222
        //1.1, Optimization
        //-
        //1.2, Complexity:
        //- Space: O(n)
        //- Time: O(n)
        //
        //#Reference:
        //966. Vowel Spellchecker
        //488. Zuma Game
        //890. Find and Replace Pattern
        //
        //
    }
}
