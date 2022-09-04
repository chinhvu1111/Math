package interviews;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

public class E122_LongestRepeatingCharacterReplacement_slice {

    public static int characterReplacement(String s, int k) {
        //Coi như ta đi tìm (số lượng Max nhất) các số A liên tiếp là được.
        //26 chữ số
        //
        int n=s.length();
        HashSet<Character> characterHashSet=new HashSet<>();

        for(int i=0;i<n;i++){
            characterHashSet.add(s.charAt(i));
        }
        int[] arrQueue=new int[n];
        int front, rear;
        Iterator<Character> iterator = characterHashSet.iterator();
        int rs=0;

        while (iterator.hasNext()){
            Character currentChar=iterator.next();
            front=0;
            rear=0;
            int currentValue=0;

            for(int i=0;i<n;i++){
                currentValue++;

                if(s.charAt(i)!=currentChar){
                    if(k==0){
                        currentValue=0;
                        continue;
                    }
                    if(rear-front==k){
                        currentValue=i-arrQueue[front++];
                    }
                    arrQueue[rear++]=i;
                }
                rs=Math.max(currentValue, rs);
            }
        }
        return rs;
    }

    public static int characterReplacementOptimize(String s, int k) {
        //Coi như ta đi tìm (số lượng Max nhất) các số A liên tiếp là được.
        //26 chữ số
        //
        int n=s.length();
        int start=0;
        int rs=0;
        int maxCount=0;
        int[] count =new int[26];

        for(int i=0;i<n;i++){
            maxCount=Math.max(maxCount, ++count[s.charAt(i)-'A']);
            System.out.printf("%s,", maxCount);

            while (i-start+1-maxCount>k){
                count[s.charAt(start)-'A']--;
                start++;
            }
            rs=Math.max(rs, i-start+1);
        }
        return rs;
    }

    public static int characterReplacementOptimize2(String s, int k) {
        //Coi như ta đi tìm (số lượng Max nhất) các số A liên tiếp là được.
        //26 chữ số
        //
        int n=s.length();
        int start=0;
        int rs=0;
        int maxCount=0;
        int[] count =new int[26];

        for(int i=0;i<n;i++){
            maxCount=Math.max(maxCount, ++count[s.charAt(i)-'A']);
            System.out.printf("%s,", maxCount);

            if (rs-maxCount<k){
                rs++;
            }else count[s.charAt(start)-'A']--;
        }
        return rs;
    }

    public static void main(String[] args) {
//        String s="ABAB";
//        int k=2;
//        String s="AABABBA";
//        int k=1;
        String s="AABA";
        int k=0;
        System.out.println(characterReplacement(s, k));
        //Bài này tư duy như sau:
        //0, Trong việc làm cách khác các bước như sau:
        //- Đọc giải thích
        //- Tự implement
        //
        //Cách 1:
        //1, Vẫn là tư duy giống bài E121_MaximizeTheConfusionOfAnExam_binary_slide
        //--> Thay vì T,F ==> Tìm mọi ký tự có thể liên tiếp (26 ký tự)
        //1.1, Ta sẽ chia ra 26 trường hợp tìm ký tự liên tiếp + replace cho từng phần tử trong (26 ký tự)
        //Complexity : O(n*26)
        //
        //Cách 2:
        //1, Cũng là slice window nhưng là 1 kiểu tiếp cận khác
        //1.1, Không xác định ký tự mà ta muốn tìm liên tiếp luôn
        //---> Dùng 1 chút thời gian ban đầu để suy nghĩ chung chung cho all cases
        //1.2, Mỗi range (start, end) được coi là 1 khả năng khi:
        //- start-end+1 >= số lượng phần tử (start) + k(Số ký tự bị replace)
        //VD:
        //Case 1:
        //BA(ABBB), k=1, result =4
        //(BAAB)BB
        //start=0, end=3
        //+ pop B : start=1, end=3 : (3-1+1 - (maxCount=2) >= 1)
        //* Câu hỏi : tại sao lại maxCount
        //+ Thư case maxCount= 14 (Khá lơn + ngăn cách nhau bới nhiều A ==> Để tách ra thành 2 dãy khác nhau)
        //---> Kiêm tra xem maxCount còn đúng hay không.
        //VD: AAABBBBBB(AA(start)AAA)BBBBBBBBA (end), k=4
        //--> Với (start), (end) như trên, --> count['B']-- (Cũng đã được giảm)
        //*** KINH NGHIỆM:
        //- 1 số bài toán kiểu này sẽ (count['c']--) (trừ dần đi <=> (start++)[ Bỏ khỏi (start,end)]
        //- Vì so sánh count[c]++, count[c]-- liên tục nên (Ký tự ở cuối ==> Luôn bị pop.
        //VD :k=2,
        //Count của 2 ký tự A,B
        // (2,2)(ok) --> 2,3(ok)(Vì vẫn chọn replace A)--> Gặp 1 A nữa --> 3,3 --> Xóa (A/B) đều được (Ta sẽ chọn cái ngoài cùng để xóa)
        //==> (Xóa ngoài cùng/ Start++ /Tiến lên) để tạo ra liên tiếp lớn nhất (Sau khi replace 2) (***1)
        //
        //VD: (BAAA)BBB (k=2)
        //- (BAAA) --> (AAAA) : OK
        //- (BAAA)BB
        //+ Step 1: COUNT[B]++
        //+ Step 2: (COUNT[B] = COUNT[A]) --> maxCount=3
        //+ Pop B ( end-start+1 - maxCount > k) <=> (COUNT[B]--)
        //- B(AAABB) (B)
        //--> Thêm B thì COUNT[B]+1= 3 --> maxCount vẫn vậu.
        //==> Lúc này sẽ POP[A] (Vì A ĐỨNG CUỐI)
        //- BA(AABB) (B)(B)
        //Case 2:
        //ABA(ABBB), k=1, result =4
        //---> Với cách giải thích (***1) bên trên thì đã done.
        //
        //1.3, (while) ==> Có thể thay bằng (if)
        System.out.println(characterReplacementOptimize(s, k));
        //1.4, Tối ưu cách tích res tương tự bài (E121_MaximizeTheConfusionOfAnExam_binary_slide)
        System.out.println(characterReplacementOptimize2(s, k));
    }
}
