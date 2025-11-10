package E1_daily;

public class E364_LargestMergeOfTwoStrings {

    public static String largestMerge(String word1, String word2) {
        int n=word1.length();
        int m=word2.length();
        int i=0, j=0;
        StringBuilder rs=new StringBuilder();

        while(i<n||j<m){
//            System.out.println(word1.substring(i));
//            System.out.println(word2.substring(j));
//            System.out.println(rs);
//            System.out.println();
            if(i<n&&j<m){
                if(word1.charAt(i)>word2.charAt(j)){
                    rs.append(word1.charAt(i));
                    i++;
                }else if(word1.charAt(i)<word2.charAt(j)){
                    rs.append(word2.charAt(j));
                    j++;
                }else{
                    //* Làm ntn sẽ sai
//                    int k=i,h=j;
////                    System.out.printf("Prev: %s %s\n", i, j);
//                    //ddddab
//                    //dddd
//                    //==> nên chọn (ddddab)
//                    //s = dddd|dddd|ab
//                    //- So sánh từng prefix string s1,s2
//                    //
//                    while(i<n&&j<m&&word1.charAt(i)==word2.charAt(j)){
////                        addStr.append(word1.charAt(i));
//                        i++;
//                        j++;
//                    }
////                    rs.append(addStr);
////                    System.out.printf("%s %s\n", i, j);
//                    if(i<n&&j<m&&word1.charAt(i)>word2.charAt(j)){
//                        rs.append(word1.charAt(k));
////                        i++;
//                        i=k+1;
//                        j=h;
//                    }else if(i<n&&j<m&&word1.charAt(i)<word2.charAt(j)){
//                        rs.append(word2.charAt(h));
////                        j++;
//                        j=h+1;
//                        i=k;
//                    }else if(k<n){
////                        rs.append(addStr);
//                        rs.append(word1.charAt(k));
//                        i=k+1;
//                        j=h;
//                    }else if(h<m){
//                        rs.append(word2.charAt(h));
//                        j=h+1;
//                        i=k;
//                    }
                    String s1 = word1.substring(i);
                    String s2 = word2.substring(j);
                    if(s1.compareTo(s2)>0){
                        rs.append(word1.charAt(i));
                        i++;
                    }else{
                        rs.append(word2.charAt(j));
                        j++;
                    }
                }
            }else if(i<n){
                rs.append(word1.charAt(i));
                i++;
            }else {
                rs.append(word2.charAt(j));
                j++;
            }
        }
        return rs.toString();
    }

    public static void main(String[] args) {
        //** Requirement
        //- You are given two strings word1 and word2.
        //- You want to construct a string merge in the following way:
        //  + while either word1 or word2 are non-empty, choose one of the following options:
        //  + If word1 is non-empty, append the first character in word1 to merge and delete it from word1.
        //      + For example, if word1 = "abc" and merge = "dv", then after choosing this operation,
        //      word1 = "bc" and merge = "dva".
        //  + If word2 is non-empty, append the first character in word2 to merge and delete it from word2.
        //      + For example, if word2 = "abc" and merge = "", then after choosing this operation,
        //      word2 = "bc" and merge = "a".
        //* Return the lexicographically (largest) merge you can construct.
        //
        //- A string a is lexicographically larger than a string b (of the same length)
        // if in the first position where a and b differ,
        // a has a character strictly larger than the corresponding character in b.
        // For example, "abcd" is lexicographically larger than "abcc" because the first position
        // they differ is at the fourth character, and d is greater than c.
        //
        //Example 1:
        //
        //Input: word1 = "cabaa", word2 = "bcaaa"
        //Output: "cbcabaaaaa"
        //Explanation: One way to get the lexicographically largest merge is:
        //- Take from word1: merge = "c", word1 = "abaa", word2 = "bcaaa"
        //- Take from word2: merge = "cb", word1 = "abaa", word2 = "caaa"
        //- Take from word2: merge = "cbc", word1 = "abaa", word2 = "aaa"
        //- Take from word1: merge = "cbca", word1 = "baa", word2 = "aaa"
        //- Take from word1: merge = "cbcab", word1 = "aa", word2 = "aaa"
        //- Append the remaining 5 a's from word1 and word2 at the end of merge.
        //
        //** Idea
        //1.
        //1.0,
        //- Method-1:
        //+ Constraints:
        //1 <= word1.length, word2.length <= 3000
        //word1 and word2 consist only of lowercase English letters.
        //  + word1.length, word2.length <= 3000 ==> Time: O(n^2)
        //
        //* Brainstorm:
        //Example 1:
        //
        //Input: word1 = "cabaa", word2 = "bcaaa"
        //  + c,b
        //
        //1.1, Case
        //
        //Ex:
        //s1 = bbbb
        //s2 = bbbbcc
        //- Nếu chọn s1:
        //=> merge = bbbb|bbbbcc
        //- Nếu đầu tiên chọn s2:
        //=> merge = bbbbcc|bbbb
        //* Main point:
        //- Nếu 2 s1,s2, cùng prefix mà length(s1)< length(s2)
        //  + Ưu tiên chọn longer string ==> Vì có thể (suffix string) của nó lớn hơn (prefix string) còn lại
        //  ==> Tiềm năng cao hơn
        //
        String word1 = "cabaa", word2 = "bcaaa";
//        String word1 = "abcabc", word2 = "abdcaba";
//        String word1 = "guguuuuuuuuuuuuuuguguuuuguug", word2 = "gguggggggguuggguugggggg";
        //w1 = guguuuuuuuuuuuuuuguguuuuguug
        //w1 = gguggggggguuggguugggggg
        //Output:   guguuuuuuuuuuuuuuguguuuuguugg(g)uggggggguuggguugggggg
        //Expected: guguuuuuuuuuuuuuuguguuuuguugg(u)ggggggguuggguuggggggg
        //abd
        //abc
        //==> Nên lấy abd vì abc < abd
        //Ex:
        //hhhhab(c)
        //ab(d)
        //
        //1.2, Optimization
        //- Thay vì reverse thì ta dùng công thức ntn:
        //  + num = (num << 1) | head.next.val;
        //  + 100 | 1 => thêm 1 vào cuối
        //
        //1.3, Complexity
        //- Time: O(n*log(n))
        //- Space: O(n)
        //
        //# Reference:
        System.out.println('g'-'u');
        System.out.println(largestMerge(word1, word2));
    }
}
