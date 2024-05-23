package E1_daily;

import java.util.HashMap;

public class E6_NumberOfWonderfulSubstrings {

    public static long wonderfulSubstrings(String word) {
        int n=word.length();
        long prefixXor=0L;
        HashMap<Long, Long> countPrefix=new HashMap<>();
        countPrefix.put(0L, 1L);
        long rs=0l;

        for(int i=0;i<n;i++){
            int curChar=word.charAt(i)-'a';
            prefixXor=prefixXor^(1L<<curChar);
//            if(prefixXor==0){
//                rs+=countPrefix.get(0L);
//            }
            //Sai case này
            rs+=countPrefix.getOrDefault(prefixXor, 0L);
//            if(Long.bitCount(prefixXor)==1){
//                rs++;
//            }
            for(int j=0;j<10;j++){
                //ab => 11
                //1101 bù:
                //+ 1101 xor 11|11 = 00|10 => rs+=1
                //+ 1101 xor 11|00 = 00|01 => rs+=1
                //+ 1101 xor 11|11 = 00
                long prefix=prefixXor^(1<<j);
                if(!countPrefix.containsKey(prefix)){
                    continue;
                }
//                long prefixExpected=prefixXor^prefix;
//                if(Long.bitCount(prefixExpected)<=1){
//                    rs+=countPrefix.get(prefix);
////                    countPrefix.put(prefixExpected, countPrefix.getOrDefault(prefixExpected, 0L)+prevCount);
//                }
                rs+=countPrefix.get(prefix);
            }
            countPrefix.put(prefixXor, countPrefix.getOrDefault(prefixXor, 0L)+1L);
//            System.out.printf("index=%s, rs=%s\n", i, rs);
        }
//        for(long key: countPrefix.keySet()){
//            if(Long.bitCount(key)<=1){
//                rs+=countPrefix.get(key);
//            }
//        }
//        System.out.println(countPrefix);
        return rs;
    }

    public static void main(String[] args) {
        //** Requirement
        //- A wonderful string is a string where (at most one) letter appears an odd number of times.
        //For example, "ccjjc" and "abab" are wonderful, but "ab" is not.
        //Given a string word that consists of the first ten lowercase English letters ('a' through 'j'),
        //* Return the number of wonderful non-empty substrings in word.
        // If the same substring appears multiple times in word, then count each occurrence separately.
        //- Đếm số substring là wonderful
        //A substring is a contiguous sequence of characters in a string.
        //
        //** Idea
        //1.
        //1.0,
        //* Constraint
        //1 <= word.length <= 10^5
        //word consists of lowercase English letters from 'a' to 'j'.
        //==> Character a -> j : 10 chữ cái thôi.
        //- Length của word khá lớn
        //  ==> Khó mà recursive được.
        //
        //* Brainstorm
        //Input: word = "aba"
        //Output: 4
        //Explanation: The four wonderful substrings are underlined below:
        //- "aba" -> "a"
        //- "aba" -> "b"
        //- "aba" -> "a"
        //- "aba" -> "aba"
        //
        //- Do không quan tâm đến string xuất hiện laị
        //  ==> Cộng bừa lên được.
        //aabb
        //a : 1
        //aa : 1+2
        //aab : 1+2+3 = 6 (sau)
        //  => Thực ra là 5: a,a,aa,aab,b : Bỏ đi chuỗi ab không thoả mãn
        //- Nhiều nhất 1 char có lẻ số lần xuất hiện -> tương đương với gì?
        //- Kiểm tra 1 chuỗi thoả mãn bên trên ntn?
        //Ex:
        //abb : lẻ
        //- Ta thấy:
        //+ (1 xor 1) = 0
        //+ (1 xor 1 xor 1) = 1
        //=> Bit count của chuỗi sau khi xor ==1 ==> Thoả mãn
        //* CT:
        // a xor b = c
        // a xor c == b? true
        //- Bài này ta có thể làm prefix sum để kiểm tra các chuỗi lần lượt:
        //  + Không thể làm O(n^2) ==> Chỉ có thể lợi dụng số lượng character để thực hiện.
        //- Tại vị trí (i) check:
        //  + 0000000001 ==> 1000000000
        //  ==> shift <<1 là được
        //Ex:
        //aabb
        //i=0 :
        //  + a
        //i=1 :
        //  + a
        //  + aa
        //  + if cur_prefix == 0 : count[cur_prefix]++
        //  + if (các cases sao cho có 1 bit) :
        //  Ex:
        //  cur_prefix_sum = 0001010000
        //      count[x] = count[0000000000] + count[0000010000] + count[0001000000]
        //  + Đoạn này đơn giản là cho 0001010000 XOR với:
        //      + 0001010001, 0001010010 ... : Được tạo bởi:
        //          + Tức là 0001010000 OR (1000000000...0000000001)
        //  + current_rs[i]
        //
        //- Case này chưa đủ:
        //if(prefixXor==0){
        //  rs+=countPrefix.get(0L);
        //}
        //+ Cần phải thêm trường hợp bằng nhau ==> vì and 1<<j ==> thiếu
        //rs+=countPrefix.getOrDefault(prefixXor, 0L);
        //
        //1.1, Optimization
        //1.2, Complexity
        //- Space : O(2^10)
        //- Time : O(n*10)
        System.out.println(5^12);
        System.out.println(5^9);
        //
        //#Reference:
        //1930. Unique Length-3 Palindromic Subsequences
        //2009. Minimum Number of Operations to Make Array Continuous
        //1386. Cinema Seat Allocation
        //
//        String word = "aabb";
        //index=0, rs=1
        //  + a
        //index=1, rs=3
        //  + a
        //  + aa
        //index=2, rs=5
        //  + b
        //  + aab
        //index=3, rs=8
        //  + b
        //  + bb
        //  + abb
        //  + aabb
//        String word = "aba";
        String word = "ehaehcjjaafjdceac";
        System.out.println(wonderfulSubstrings(word));
    }
}
