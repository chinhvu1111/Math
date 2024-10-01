package contest;

import java.util.Arrays;
import java.util.HashSet;

public class E195_CountOfSubstringsContainingEveryVowelAndKConsonantsII_classic {

    public static long countOfSubstrings(String word, int k) {
        int n=word.length();
//        int[][] prefixSum=new int[n+1][26];
//        int[] prefixCount=new int[26];
//
//        for(int i=0;i<n;i++){
//            prefixCount[word.charAt(i)-'a']++;
//            for(int j=0;j<26;j++){
//                prefixSum[i+1][j]=prefixCount[j];
//            }
//        }
        int[] vowels={0,'e'-'a','i'-'a','o'-'a','u'-'a'};
        long[] rightMostVowelIndex=new long[n];
        HashSet<Integer> cons=new HashSet<>();
        HashSet<Integer> vowelsSet=new HashSet<>();

        for(int c: vowels){
            vowelsSet.add(c);
        }
        for(int i=0;i<26;i++){
            if(!vowelsSet.contains(i)){
                cons.add(i);
            }
        }
        int prevIndex=-1;
        //a,b,c,e,i,c
        //2,2,2,3,-1,-1
        //
        //a,e,u,i,a,a,e,u,i
        //8,8,8,8,8,8,8,8,-1
        //  + Không để [i] = 4 được vì vướng consonants
        //- Đoạn này cũng cẩn thận chỗ if else:
        //  + Nếu if 2 operations trong 1 condition
        //  ==> Else nó sẽ là sai 1 trong 2 ==> Có thể sẽ sai condition
        //      + Ex: Ở đây nếu prevIndex==-1 ==> Ta mới update
        //          + Nhưng nếu để 2 điều kiện (là vowels + prevIndex==-1) ==> Mới update
        //          <> prevIndex==-1 ==> Có thể đúng với c là consonent nhưng prevIndex của (vowel != -1) (Tồn tại rồi)
        //          ==> Mà đi update là sai.
        for(int i=n-1;i>=0;i--){
            rightMostVowelIndex[i]=prevIndex;
            if(vowelsSet.contains(word.charAt(i)-'a')){
                if(prevIndex==-1){
                    prevIndex=i;
                }
            }else{
                prevIndex=-1;
            }
        }
        //j=0,i=n-1
        //index:0,1,2,3
        //i:  0,1,2,3,4
        //
        long rs=0;
        int left=0;
        long[] count=new long[26];
        int right=left;
        while (left<n&&right<n){
            count[word.charAt(right)-'a']++;
            boolean isInvalid=false;
            for(int h:vowels){
                if(count[h]<=0){
                    isInvalid=true;
                    break;
                }
            }
            //Không thoả mãn thì
            if(isInvalid){
                right++;
                continue;
            }
            long countCons=0;
            for(int h: cons){
                countCons+=count[h];
            }
//            for(int h=0;h<26;h++){
//                System.out.printf("%s: %s, ",(char)(h+'a'), count[h]);
//            }
//            System.out.println();
            //Thoả mãn thì sao:
            //  +
            if(countCons==k){
//                rs++;
//                System.out.println(word.substring(left, right+1));
                //ae,k,e,e,e,e
//                int tempRight=right+1;
//                while (tempRight<n){
//                    if(cons.contains(word.charAt(tempRight)-'a')){
//                        break;
//                    }
//                    //Không thoả mãn thì
//                    rs++;
//                    System.out.println(word.substring(left, tempRight+1));
//                    tempRight++;
//                }
                if(rightMostVowelIndex[right]!=-1){
                    rs+=rightMostVowelIndex[right]-right+1;
                }else{
                    rs++;
                }
                count[word.charAt(left)-'a']--;
                //Nếu tăng left ở đây ==> Không tăng right
                //  + Sẽ bị duplicate right vì right bên trên đã tăng rồi
                //      + Dưới này không thay đổi right gì ==> New loop sẽ vẫn cộng count[word[right]] ==> Bị duplicated
                //* Kinh nghiệm:
                //  + right đã cộng đầu loop count[word[right]]++
                //      ==> Đến khi chạy xong thì cần phải increased
                //      ** Còn không thì phải trừ đi cho đỡ duplicated
                //      + Nếu trừ đi:
                //          + Tức là đi xử lý lại rồi ==> TLE
                count[word.charAt(right)-'a']--;
                left++;
            }else if(countCons<k){
                right++;
            }else{
                //countCons>k
                //- Tức là số lượng consonants > k
                //  + Cần giảm số consonants đi
                while (countCons>k&&left<n){
                    if(cons.contains(word.charAt(left)-'a')){
                        countCons-=1;
                    }
                    count[word.charAt(left)-'a']--;
                    left++;
                }
                if(countCons==k){
                    isInvalid=false;
                    for(int h:vowels){
                        if(count[h]<=0){
                            isInvalid=true;
                            break;
                        }
                    }
                    //Không thoả mãn thì
                    if(!isInvalid){
//                        System.out.println(word.substring(left, right+1));
//                        rs++;
//                        int tempRight=right+1;
//                        while (tempRight<n){
//                            if(cons.contains(word.charAt(tempRight)-'a')){
//                                break;
//                            }
//                            //Không thoả mãn thì
//                            rs++;
//                            System.out.println(word.substring(left, tempRight+1));
//                            tempRight++;
//                        }
                        if(rightMostVowelIndex[right]!=-1){
                            rs+=rightMostVowelIndex[right]-right+1;
                        }else{
                            rs++;
                        }
                        count[word.charAt(right)-'a']--;
                        count[word.charAt(left)-'a']--;
                        left++;
                    }else{
                        right++;
                    }
                }else{
                    right++;
                }
            }
        }
//        System.out.printf("%s, %s\n",left, right);
        while (left<n){
            count[word.charAt(left)-'a']--;
            left++;
            boolean isInvalid=false;
            for(int h:vowels){
                if(count[h]<=0){
                    isInvalid=true;
                    break;
                }
            }
            //Không thoả mãn thì
            if(isInvalid){
                right++;
                continue;
            }
            long countCons=0;
            for(int h: cons){
                countCons+=count[h];
            }
//            for(int h=0;h<26;h++){
//                System.out.printf("%s: %s, ",(char)(h+'a'), count[h]);
//            }
//            System.out.println();
            //Thoả mãn thì sao:
            //  +
            if(countCons==k){
//                System.out.println(word.substring(left, right+1));
                rs++;
            }
        }
        return rs;
    }

    public static long countOfSubstringsRefer(String word, int k) {
        String vowels = "aeiou";
        int leftPointer = 0, midPointer = 0, distinctVowelCount = 0;
        //- Lúc count vowel:
        //  + Nên tách ra 2 size 6 và 6 thay vì để 26 ==> Tốn time.
        //aeiou
        int[] vowelCount = new int[6];
        int[] countedVowels = new int[6];
        long result = 0;

        for (int i = 0; i < word.length(); i++) {
            //- Lấy vowel index ==> trong string vowel thay vì dùng char array 26 characters như bình thường
            //- Không phải vowel:
            //  vowelIndex= -1+1 = 0
            //- Nếu là vowelIndex:
            //  + Ta sẽ tính từ 1 -> 5
            int vowelIndex = vowels.indexOf(word.charAt(i)) + 1;
            vowelCount[vowelIndex]++;
            //vowelIndex>=1:
            //  + Mới là vowel
            distinctVowelCount += (vowelCount[vowelIndex] == 1 && vowelIndex > 0) ? 1 : 0;

            //vowelCount[0]:
            //  + this is the number of consonants
            //- If the number of consonants is greater than k
            //  + vowelCount[left]--
            //  + Left++
            //** Giảm left đến khi:
            //  + The number of consonents is equal to k
            while (vowelCount[0] > k) {
                int leftVowelIndex = vowels.indexOf(word.charAt(leftPointer)) + 1;
                vowelCount[leftVowelIndex]--;
                distinctVowelCount -= (vowelCount[leftVowelIndex] == 0 && leftVowelIndex > 0) ? 1 : 0;
                leftPointer++;
            }
            //If we meet the condition:
            //- Idea:
            //  + We also need to find (the furthest point mid), so that [mid, i] also contains the needed number of (vowels) and (consonants).
            //  + With that, the number of substrings is (mid - left + 1).
            //  + When moving (mid), we use the second counter (cnt1),
            //      where we track characters between (left and mid), When left moves (past mid), we (reset cnt1).
            if (distinctVowelCount == 5 && vowelCount[0] == k) {
                if (midPointer < leftPointer) {
                    midPointer = leftPointer;
                    Arrays.fill(countedVowels, 0);
                }
                //- Đoạn này khi thoả mãn điều kiện:
                //+ Nó vẫn traverse qua right:
                //- Theo logic bình thường thì:
                //  + Gắn newPointer = right (i)
                //  + Khi gặp consonant ==> Break
                //  ==> Ntn thì sẽ bị miss case (vowel chars ở prefix)
                //      + Gắn với new pointer ==> Thiếu (Đáng nhẽ ra phải gắn vởi right smaller)
                //- Giả sử
                //  + vowelCount: Tổng count từ (left -> midPointer -> i)
                //  + (left, midPointer)
                //      + Để mà giữa (left và midPointer): toàn là vowels
                //* Main point:
                //  - Chúng ta cũng cần tìm điểm xa nhất ở giữa để [mid, i] cũng chứa đủ số nguyên âm, phụ âm cần thiết.
                //* Mốc lấy số lượng sub-string sẽ là (i):
                //  + Do (i) tăng dần liên tục (i++)
                //
                //- Vin vào (i) Map ra (midPointer):
                //  (midPointer -> i) đủ rồi
                //      + (left-> midPointer-1) là số lần kết hợp có thể nối với substring(midPointer, i]
                //          + Do ngoài loop (midPointer + thừa) => rs+= (midPointer-left+1)
                //- Mỗi lần i tăng lên:
                //  + midPointer sẽ dịch tiếp để tìm ra new midPointer:
                //      + Do thêm word[i] ( Có thể thêm vowel )
                //          ==> mid pointer increase ==> Số lượng kết hợp tăng.
                //- Vào đến đoạn này:
                //  + Thoả mãn điều kiện rồi:
                //- vowelCount: count cho đến (i)
                //- countedVowels: count trong khoảng [left, midPointer]
                //  + vowelCount[midVowelIndex] - countedVowels[midVowelIndex] == 1 (break)
                //  ==> Vừa đủ số vowels ở giữa [midPointer, i]
                //  + Có thể có nhiều thằng == 1 thì có nên break ngay hay không?
                //      Ex: (ee)[i:mid]au(i)puoee
                //** Main point2:
                //  - Xét đến (word[midPointer]==x)
                //  - Gặp bất cứ vowel char nào mà (required)(không thể thiếu) ==> break luôn:
                //      ==1 => Tức là [mid,i] có duy nhất 1 char x thôi ==> Không cut nữa.
                //          + (word[midPointer]==x): Đang == x ==> Đi tiếp là cut luôn (Thành thiếu)
                //      + [mid, i]: chỉ có 1 char x duy nhất ==> Mới mang đi kết hợp được
                //  - CT: vowelCount[midVowelIndex] - countedVowels[midVowelIndex] == 1 (break)
                //
                while (true) {
                    int midVowelIndex = vowels.indexOf(word.charAt(midPointer)) + 1;
                    //- Break khi:
                    //  + Nếu không phải vowel:
                    //      + midVowelIndex == 0
                    //  + vowelCount[midVowelIndex] - countedVowels[midVowelIndex]
                    //      + Tức là nó chỉ có 1 vowel duy nhất ==>
                    if (midVowelIndex == 0 || vowelCount[midVowelIndex] - countedVowels[midVowelIndex] == 1) break;
                    countedVowels[midVowelIndex]++;
                    midPointer++;
                }
                //- left -> midPointer -> midPonter+1(Consonants) -> i
                //  +
                result += midPointer - leftPointer + 1;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        //* Requirement
        //- You are given a string word and (a non-negative integer k).
        //* Return the total number of substrings of word that contain every vowel ('a', 'e', 'i', 'o', and 'u')
        // at least once and exactly k consonants.
        //
        // Idea
        //1.
        //1.0,
        //- Constraint:
        //- 5 <= word.length <= 2 * 105
        //word consists only of lowercase English letters.
        //0 <= k <= word.length - 5
        //  + Length of word is big ==> Time: O(n)
        //
        //** Brainstorm
        //
        //- Slice window được không?
        //  +
//        String word = "ieaouqqieaouqq";
//        int k = 1;
        //  + ieaouq
        //  + qieaou
        //  + qieaouq
        //(ieaouq)qieaouqq
        //i(eaouqq)ieaouqq
        //  ==> ieaouq(q)ieaouqq
//        String word = "iqeaouqi";
//        int k = 2;
        //iqeaouq
        //iqeaouqi
        //Rs:2
        //Expected: 3
//        String word = "aadieuoh";
//        int k = 1;
        //rs=2
        //  + aadieuo
        //  + adieuo
        //Expected : 3
        //- iqeaouqi, k=2
        //  + iqeaouq
        //  + qeaouqi
        //  + iqeaouqi
        //* Main point:
        //- aaaeeiii:
        //  + Nếu str đủ rồi + vowels sau đó ==> Vẫn có thể lấy tiếp
        //  + Nếu str đủ rồi + cut vowels ở prefix + vowels sau đó
        //      + Cũng có thể lấy tiếp
        //- Mỗi index:
        //  + Ta cần cộng nốt số cases:
        //      + Trong index đấy => Cần loop hết phần right để lấy hết số cases
        //
//        String word = "eoeaui";
//        int k = 0;
        //+ eoeaui
        //+ oeaui
//        String word = "ffcoiaaouei";
//        int k = 0;
        //  + oiaaoue
        //  + oiaaouei
        //  + iaaoue
        //  + iaaouei
        //  + aaouei
        //  + aouei
//        String word = "buoeia";
//        int k = 0;
        //
        //Ex:
        //(aeioue)eai
        //  + rs+=4
        //  + Để ta thể giảm loop chỗ này:
        //  ==> Cần tìm vowel xa nhất từ (i) bằng bao nhiêu
        //  ==> Dùng prefix index là được.
        //a(eioueea)i
        //  + rs+=2
//        String word = "ieaouqqieaouqq";
//        int k = 1;
        String word = "ieaouieauoaiaeouai";
        int k = 0;
        //rs= 16
        //Expected: 96
        //
        //- Issues:
        //- Liên quan đến việc loop nhiều lần mới count được số cases:
        //
        //Nếu tăng left ở đây ==> Không tăng right
        //  + Sẽ bị duplicate right vì right bên trên đã tăng rồi
        //      + Dưới này không thay đổi right gì ==> New loop sẽ vẫn cộng count[word[right]] ==> Bị duplicated
        //* Kinh nghiệm:
        //  + right đã cộng đầu loop count[word[right]]++
        //      ==> Đến khi chạy xong thì cần phải increased
        //      ** Còn không thì phải trừ đi cho đỡ duplicated
        //      + Nếu trừ đi:
        //          + Tức là đi xử lý lại rồi ==> TLE
        //================================
        //count[word.charAt(right)-'a']--;
        //left++;
        //================================
        //- Cần tối ưu đoạn loop cho mỗi index:
        //a,e,u,i,a,a,e,u,i
        //8,8,8,8,8,8,8,8,-1
        //  + Không để [i] = 4 được vì vướng consonants
        //- Đoạn này cũng cẩn thận chỗ if else:
        //  + Nếu if 2 operations trong 1 condition
        //  ==> Else nó sẽ là sai 1 trong 2 ==> Có thể sẽ sai condition
        //      + Ex: Ở đây nếu prevIndex==-1 ==> Ta mới update
        //          + Nhưng nếu để 2 điều kiện (là vowels + prevIndex==-1) ==> Mới update
        //          <> prevIndex==-1 ==> Có thể đúng với c là consonent nhưng prevIndex của (vowel != -1) (Tồn tại rồi)
        //          ==> Mà đi update là sai.
        //  + rightMostVowelIndex[i]: Là index của vowels xa nhất bên phải
        //      + Nếu gặp any consonant ==> rightMostVowelIndex[i] = -1
        //
        //Thay cho đoạn code này:
        //ae,k,e,e,e,e
//                int tempRight=right+1;
//                while (tempRight<n){
//                    if(cons.contains(word.charAt(tempRight)-'a')){
//                        break;
//                    }
//                    //Không thoả mãn thì
//                    rs++;
//                    System.out.println(word.substring(left, tempRight+1));
//                    tempRight++;
//                }
        //================================
        //
        //1.1, Optimization
        //#Reference:
        //https://leetcode.com/problems/count-of-substrings-containing-every-vowel-and-k-consonants-ii/solutions/5846240/sliding-window-with-2-pointer-with-exp-beats-100/
        //
        //- Đoạn này khi thoả mãn điều kiện:
        //+ Nó vẫn traverse qua right:
        //- Theo logic bình thường thì:
        //  + Gắn newPointer = right (i)
        //  + Khi gặp consonant ==> Break
        //  ==> Ntn thì sẽ bị miss case (vowel chars ở prefix)
        //      + Gắn với new pointer ==> Thiếu (Đáng nhẽ ra phải gắn vởi right smaller)
        //- Giả sử
        //  + vowelCount: Tổng count từ (left -> midPointer -> i)
        //  + (left, midPointer)
        //      + Để mà giữa (left và midPointer): toàn là vowels
        //* Main point:
        //  - Chúng ta cũng cần tìm điểm xa nhất ở giữa để [mid, i] cũng chứa đủ số nguyên âm, phụ âm cần thiết.
        //* Mốc lấy số lượng sub-string sẽ là (i):
        //  + Do (i) tăng dần liên tục (i++)
        //
        //- Vin vào (i) Map ra (midPointer):
        //  (midPointer -> i) đủ rồi
        //      + (left-> midPointer-1) là số lần kết hợp có thể nối với substring(midPointer, i]
        //          + Do ngoài loop (midPointer + thừa) => rs+= (midPointer-left+1)
        //- Mỗi lần i tăng lên:
        //  + midPointer sẽ dịch tiếp để tìm ra new midPointer:
        //      + Do thêm word[i] ( Có thể thêm vowel )
        //          ==> mid pointer increase ==> Số lượng kết hợp tăng.
        //- Vào đến đoạn này:
        //  + Thoả mãn điều kiện rồi:
        //- vowelCount: count cho đến (i)
        //- countedVowels: count trong khoảng [left, midPointer]
        //  + vowelCount[midVowelIndex] - countedVowels[midVowelIndex] == 1 (break)
        //  ==> Vừa đủ số vowels ở giữa [midPointer, i]
        //  + Có thể có nhiều thằng == 1 thì có nên break ngay hay không?
        //      Ex: (ee)[i:mid]au(i)puoee
        //** Main point2:
        //  - Xét đến (word[midPointer]==x)
        //  - Gặp bất cứ vowel char nào mà (required)(không thể thiếu) ==> break luôn:
        //      ==1 => Tức là [mid,i] có duy nhất 1 char x thôi ==> Không cut nữa.
        //          + (word[midPointer]==x): Đang == x ==> Đi tiếp là cut luôn (Thành thiếu)
        //      + [mid, i]: chỉ có 1 char x duy nhất ==> Mới mang đi kết hợp được
        //  - CT: vowelCount[midVowelIndex] - countedVowels[midVowelIndex] == 1 (break)
        //
        /*
        while (true) {
            int midVowelIndex = vowels.indexOf(word.charAt(midPointer)) + 1;
            //- Break khi:
            //  + Nếu không phải vowel:
            //      + midVowelIndex == 0
            //  + vowelCount[midVowelIndex] - countedVowels[midVowelIndex]
            //      + Tức là nó chỉ có 1 vowel duy nhất ==>
            if (midVowelIndex == 0 || vowelCount[midVowelIndex] - countedVowels[midVowelIndex] == 1) break;
            countedVowels[midVowelIndex]++;
            midPointer++;
        }
        //- left -> midPointer -> midPonter+1(Consonants) -> i
        //  +
        result += midPointer - leftPointer + 1;
        */
        //
        //1.2, Complexity
        //- Space: O(n) --> O(1)
        //- Time: O(n)
        //
        //#Reference:
        //1839. Longest Substring Of All Vowels in Order
        //2062. Count Vowel Substrings of a String
        //- Must solve:
        //https://leetcode.com/problems/subarrays-with-k-different-integers/editorial/
        //
        System.out.println(countOfSubstrings(word, k));
        System.out.println(countOfSubstringsRefer(word, k));
    }
}
