package E1_weekly;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class E12_GeneralizedAbbreviation {

    public static HashSet<Pair<String, Integer>> visited;

    public static void solution(int index, String word, StringBuilder curStr, int n, HashSet<String> rs, boolean isLength){
        if(visited.contains(new Pair<>(curStr.toString(), index))){
            return;
        }
        if(index>=n){
            rs.add(curStr.toString());
            return;
        }
        StringBuilder curString=new StringBuilder(curStr);
//        StringBuilder curSubString = new StringBuilder();
        StringBuilder newString = new StringBuilder(curStr);
        for(int i=index;i<n;i++){
            newString.append(word.charAt(i));
            solution(i+1, word, newString, n, rs, false);
//            curString=new StringBuilder(curStr);
            if(!isLength){
                curString.append(i-index+1);
                solution(i+1, word, curString, n, rs, true);
//                curString=new StringBuilder(curStr);
//                while (curString.length()>0&&curString.charAt(curString.length()-1)>='0'&&curString.charAt(curString.length()-1)<='9'){
//                    curString.deleteCharAt(curString.length()-1);
//                }
                curString.setLength(curStr.length());
            }
        }
        visited.add(new Pair<>(curStr.toString(), index));
    }

    public static List<String> generateAbbreviations(String word) {
        int n=word.length();
        HashSet<String> rs=new HashSet<>();
        visited=new HashSet<>();
        solution(0, word, new StringBuilder(), n, rs,false);
        return new ArrayList<>(rs);
    }

    private static void storeAbbreviations(
            List<String> abbreviations,
            String word,
            StringBuilder currWord,
            int index,
            int abbreviatedCount
    ) {
        if (index == word.length()) {
            // If the length of the last abbreviated substring is 0, add an empty string.
            if (abbreviatedCount > 0) {
                currWord.append(abbreviatedCount);
            }
            abbreviations.add(currWord.toString());
            return;
        }
        //- Mỗi index có 2 cách chọn
        //  + Depth = n
        int currWordLength = currWord.length();
        // Option 1: Keep the current character.
        if (abbreviatedCount > 0) {
            currWord.append(abbreviatedCount);
        }
        currWord.append(word.charAt(index));
        storeAbbreviations(abbreviations, word, currWord, index + 1, 0);
        currWord.setLength(currWordLength); // Backtrack
        // Option 2: Abbreviate the current character.
        storeAbbreviations(
                abbreviations,
                word,
                currWord,
                index + 1,
                abbreviatedCount + 1
        );
    }

    public static List<String> generateAbbreviationsReference(String word) {
        List<String> abbreviations = new ArrayList<>();
        storeAbbreviations(abbreviations, word, new StringBuilder(), 0, 0);
        return abbreviations;
    }

    public List<String> generateAbbreviationsBitManipulation(String word) {
        int N = word.length();
        List<String> abbreviations = new ArrayList<>();

        for (int mask = 0; mask < (1 << N); mask++) {
            StringBuilder currWord = new StringBuilder();
            int abbreviatedCount = 0;

            for (int index = 0; index < N; index++) {
                // If the bit at position index is 1, increment the abbreviated substring.
                if ((mask & (1 << index)) != 0) {
                    abbreviatedCount++;
                } else {
                    // Append the last substring and then the current character.
                    if (abbreviatedCount > 0) {
                        currWord.append(abbreviatedCount);
                        abbreviatedCount = 0;
                    }
                    currWord.append(word.charAt(index));
                }
            }

            if (abbreviatedCount > 0) {
                currWord.append(abbreviatedCount);
            }
            abbreviations.add(currWord.toString());
        }

        return abbreviations;
    }

    public static void main(String[] args) {
        //** Requirement
        //- (A word's generalized abbreviation) can be constructed by taking any number of (non-overlapping) and (non-adjacent) substrings
        // and (replacing) them with their (respective lengths).
        //- For example, "abcde" can be abbreviated into:
        //  + "a3e" ("bcd" turned into "3")
        //  + "1bcd1" ("a" and "e" both turned into "1")
        //  + "5" ("abcde" turned into "5")
        //  + "abcde" (no substrings replaced)
        //- However, these abbreviations are invalid:
        //  + "23" ("ab" turned into "2" and "cde" turned into "3") is invalid as the substrings chosen are adjacent.
        //  + "22de" ("ab" turned into "2" and "bc" turned into "2") is invalid as the substring chosen overlap.
        //* Given a string word, return a list of all the possible generalized abbreviations of word.
        // Return the answer in any order.
        //- Đại loại là replace các substring của given string bằng length của nó:
        //  + Return tất cả các string có thể
        //- Thoả mãn điều kiện:
        //  + Các substring không overlap
        //  + Các substring không cạnh nhau
        //
        //**Idea
        //1.
        //1.0,
        // Method-1:
        //- Constraint
        //1 <= word.length <= 15
        //word consists of only lowercase English letters.
        //+ Length của words không lớn ==> Time: O(n^3) được
        //
        //- Brainstorm
        //- Dùng backtracking
        //- Mỗi lần ta sẽ cộng string lên để lấy kết quả tiếp theo
        //- Mỗi vị trí có 2 lựa chọn:
        //  + Add raw substring
        //  + Add length của nó
        //- Ta dùng hashset ==> Lưu kết quả
        //- Làm như sau:
        //  + Xét từng substring
        //  + isLength (check substring trước đó)
        //      + Nếu là length thì không được xét length nữa.
        //- Time: 321 ms
        //
        //- Không dùng hashset nữa:
        //  ==> Cần caching để tăng tốc độ.
        //- Tại sao lại bị duplicated:
        //- Cùng 1 string ==> Có thể ra nhiều strings khác nhau.
        //- Chia word thành 2 nửa:
        //  + (previous string) + (tail string)
        //      + tail string được phân biệt bởi (index)
        //      + previous string ==> ta sẽ phân bởi current string
        //  - Cùng 1 current string ==> Đến cùng (index)
        //      + ==> Không đi sâu thêm (index+1) nữa.
        //  + word:
        //      + Các string còn lại là không đổi
        //- Time: 59 ms
        //==> Vẫn cần dùng hashSet ==> Không dùng thì sẽ sai
        //
        //* Kinh nghiệm:
        //- StringBuilder --> Không hash trùng được.
        //- curString.setLength(curStr.length());
        //  ==> Tác dụng y hệt cut substring cho đến (index)
        //  * Return lại StringBuilder()
        //
        //- Mấy bài này ==> Thay vì for:
        //  + Ta sẽ đi từng index or character của string.
        //  + abbreviatedCount có 2 lựa chọn:
        //      + reset = 0
        //      + Tăng lên (abbreviatedCount+1)
        //  ==> Làm ntn thì số trường hợp trùng sẽ ít hơn
        //* Kinh nghiệm (Backtracking):
        //- Vì mỗi lần for(i : index -> n-1)
        //  + Mỗi (i) sẽ có 1 case lấy (toàn bộ string) của word
        //      ==> Duplicate case vì cái nào cũng (i+1 ==> n-1)
        //  + Nếu xét từng character thì:
        //      + Xét đến index(i) sau đó (index+1 -> n-1) thì sẽ không bị duplicate lại nữa
        //
        //1.1, Optimization
        //1.2, Complexity
        //- Space: O(2^N*N+N)
        //- Time:
        //- Mỗi index có 2 cách chọn
        //  + Depth = n
        //==> Time : O(2^n*N)
        //- Explain:
        //  + add every string to list ==> each operation take O(N) times
        //  + 2^N strings ==> O(N*2^N)
        //+ Each of the N characters in the string word has two choices that we will make until we do it for all the characters.
        // Also, for each string produced,
        //  + we are appending the abbreviatedString during the process which (adds another) O(N) time.
        //  + Since we are going to generate (2^N strings), therefore the time = O(2^N)
        //
        //2.
        //2.0, Bit manipulation
        //- Dùng bit list all of string + visited
        //  ==> cộng các bit 1 cạnh nhau ==> Length
        //  ==> Word tương ứng
        //
        //* Kinh nghiệm:
        //- Dùng bit manipulation ==> Có thể list toàn bộc các string được mark theo visited
        //2.1, Optimization
        //2.2, Complexity
        //- Time: O(N*2^N)
        //- Space: O(1)
        //
        //#Reference:
        //288. Unique Word Abbreviation
        //411. Minimum Unique Word Abbreviation
        //
        String word = "word";
        System.out.println(generateAbbreviations(word));
        System.out.println(generateAbbreviationsReference(word));
    }
}
