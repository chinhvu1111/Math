package E1_daily;

import javafx.util.Pair;

import java.util.Comparator;
import java.util.Map;
import java.util.PriorityQueue;

public class E132_LongestHappyString {

    public static String longestDiverseString(int a, int b, int c) {
        PriorityQueue<Pair<Character, Integer>> freqChar=new PriorityQueue<>(new Comparator<Pair<Character, Integer>>() {
            @Override
            public int compare(Pair<Character, Integer> o1, Pair<Character, Integer> o2) {
                return o2.getValue()-o1.getValue();
            }
        });
        if(a>0){
            freqChar.add(new Pair<>('a', a));
        }
        if(b>0){
            freqChar.add(new Pair<>('b', b));
        }
        if(c>0){
            freqChar.add(new Pair<>('c', c));
        }
        int maxLen=a+b+c;
        StringBuilder rs=new StringBuilder();

        for(int i=0;i<maxLen&&!freqChar.isEmpty();i++){
            Pair<Character, Integer> curChar=freqChar.poll();
            int j=rs.length()-1;
            while (j>=0&&rs.charAt(j)==curChar.getKey()){
                j--;
            }
            //
            int countPrev = rs.length()-1-j;
            //(cc) có sẵn r
            if(countPrev>=1&&!freqChar.isEmpty()){
                Pair<Character, Integer> nextChar = freqChar.poll();
                freqChar.add(curChar);
                curChar=nextChar;
                //Chỉ cần chuyển cái này từ countPrev=0 => countPrev=1 là được, vì:
                //+ Khi mà chọn 1 character có count nhỏ hơn
                //  ==> Ta chỉ nên chọn 1 char thôi
                //  ==> Nếu gán countPrev=1
                //      + countAddingChar == 1
                //      ==> Ta đạt được mục đích gán 1 char vào thôi.
                countPrev=0;
            }
            int countAddingChar = 2 - countPrev;
            countAddingChar= Math.min(countAddingChar, curChar.getValue());
            if(curChar.getValue()!=countAddingChar){
                freqChar.add(new Pair<>(curChar.getKey(), curChar.getValue()-countAddingChar));
            }
            while (countAddingChar>0){
                rs.append(curChar.getKey());
                countAddingChar--;
            }
//            if(countPrev>=1){
//                break;
//            }
//            System.out.println(countAddingChar);
//            System.out.println(rs);
        }
        return rs.toString();
    }

    public static void main(String[] args) {
        //** Requirement
        //- A string s is called happy if it satisfies the following conditions:
        //  + s only contains the letters 'a', 'b', and 'c'.
        //  + s does not contain any of "aaa", "bbb", or "ccc" as a substring.
        //  + s contains at most a occurrences of the letter 'a'.
        //  + s contains at most b occurrences of the letter 'b'.
        //  + s contains at most c occurrences of the letter 'c'.
        //* Given three integers a, b, and c, return the ("longest" possible happy string).
        // If there are multiple longest happy strings, return any of them.
        // If there is no such string, return the empty string "".
        //A substring is a contiguous sequence of characters within a string.
        //
        // Idea
        //1.
        //1.0,
        // Method-1:
        //- Constraint
        //0 <= a, b, c <= 100
        //a + b + c > 0
        //  + a,b,c không lớn, Time: O(n^3)
        //
        //- Brainstorm
        //- S không bao gồm:
        //  + aaa
        //  + bbb
        //  + ccc
        //- Backtrack được không?
        //  + Được nhưng vấn đề là longest
        //- Cần tìm công thức để lấy longest string
        //Ex:
        //a=2,b=3,c=0
        //+ aabbb -> Điền aa cạnh nhau luôn cũng ko được
        //+ bab(aaa) -> Điền so le b,a luôn cũng ko được
        //  + aababa
        //
        //- Tức là ta sẽ chọn (a,b,c) dựa trên số lượng của chúng:
        //- Có case nào điền aa mà gây ra kết quả về sau không cân bằng được các số còn lại không?
        //Ex:
        //a=2,b=3
        //+ aabbb -> Điền aa cạnh nhau luôn cũng ko được
        //  ==> Nếu lấy thứ tự ntn thì lấy b trươc
        //a=2,b=2
        //+ aabb
        //a=3,b=3
        //+ aabbab
        //  ==> Vẫn được
        //- Thêm c vào thì càng tốt hơn
        //  ==> Tăng tính xen kẽ hơn
        //- Khi nào thì không thoả mãn:
        //Ex:
        //a=10, b=2
        //aabaabaa
        //len = 8
        //  + Cần biết đằng trước có mấy char rồi
        //  + Cache word
        //  + Check cùng char thì mới tính break
        //      + While cũng được.
        //  ==> Idea này chưa chuẩn lắm
        //- Đoạn này check chars trước đấy:
        //  + count số lượng char c==curChar.key()
        //- Nếu đằng trước điền đủ 2 chars của curChar
        //  ==> Sẽ điền thằng tiếp:
        //      + Ở đây ta sẽ chỉ điền thằng tiếp 1 char thôi, nếu không sẽ bị case dưới đây:
        //Ex
        //int a = 0, b = 8, c = 11;
        //ccbbccbbccbbccbbcc
        //ccbccbbccbbccbbccbc
        //- Bị case chọn 2 char liên tiếp sẽ dính bug
        //      ==> Chỉ chọn 1 lần
        //  - Đằng trước đã có curChar có thể:
        //      + curChar áp đảo về số lượng count
        //      + Hoặc là đến cuối rồi
        //  - Khi đó thì:
        //      + Ta sẽ chọn nextChar
        //      ==> Chỉ chọn 1 char bằng cách gán:
        //          + countPrev = 1
        //          ==> countAddingChar = 2-countPrev = 1
        //      + Do add next char:
        //          + queue.add(curChar) : Không tính curChar nữa
        //          + curChar = nextChar
        //  - Loop countAddingChar để add(curChar)
        //
        //- a==0/b==0/c==0
        //  + Không được add vào ban đầu.
        //
        //1.1, Optimization
        //1.2, Complexity
        //+ n= a+b+c
        //- Space: O(1)
        //- Time: O(n)
        //
//        int a = 1, b = 1, c = 7;
//        int a = 7, b = 1, c = 0;
        int a = 0, b = 8, c = 11;
        //ccbbccbbccbbccbbcc
        //ccbccbbccbbccbbccbc
        //- Bị case chọn 2 char liên tiếp sẽ dính bug
        System.out.println(longestDiverseString(a, b, c));
    }
}
