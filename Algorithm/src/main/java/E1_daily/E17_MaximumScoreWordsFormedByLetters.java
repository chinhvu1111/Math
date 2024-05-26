package E1_daily;

import java.util.Arrays;

public class E17_MaximumScoreWordsFormedByLetters {

    public static int maxScoreWords(String[] words, char[] letters, int[] score) {
        int n=words.length;
        int[] count=new int[26];
        Arrays.fill(count, 0);
        int rs=0;

        for(char c: letters){
            count[c-'a']++;
        }
//        for(char c: letters){
//            System.out.printf("%s, count: %s\n",c, count[c-'a']);
//        }
        int size=(1<<n)-1;
        int[] scoreW=new int[n];
        Arrays.fill(scoreW, -1);

        for(int i=0;i<n;i++){
            String curWord=words[i];
            int[] curCount=new int[26];
            boolean isValid=true;
            int curScore=0;

            for(int j=0;j<curWord.length();j++){
                char c = curWord.charAt(j);
                curCount[c-'a']++;
                curScore+=score[c-'a'];
                if(curCount[c-'a']>count[c-'a']){
                    isValid=false;
                    break;
                }
            }
            if(isValid){
                scoreW[i] = curScore;
            }
        }
//        for (int i = 0; i < n; i++) {
//            System.out.printf("i=%s, val=%s\n", i, scoreW[i]);
//        }
//        System.out.println(size);
        //Time : O(2^n)
        for(int i=1;i<=size;i++){
            int curCase=i;
            int pos=0;
            int[] curCount = new int[26];
            boolean isValid=true;
            int curRs=0;

            //Time: O(n)
            while(curCase!=0){
                int curBit = curCase&1;

                if(curBit==1){
                    String curStr=words[pos];
                    if(scoreW[pos]==-1){
                        isValid=false;
                    }else{
                        //Time : O(m)
                        for(int j=0;j<curStr.length();j++){
                            char curChar = curStr.charAt(j);
                            curCount[curChar-'a']++;
                            if(curCount[curChar-'a']>count[curChar-'a']){
                                isValid=false;
                                break;
                            }
                        }
                        curRs+=scoreW[pos];
                    }
                }
                pos++;
                curCase=curCase>>1;
            }
            if(isValid){
                rs=Math.max(rs, curRs);
            }
        }
        return rs;
    }

    public static int maxScoreWordsOptimization(String[] words, char[] letters, int[] score) {
        int n=words.length;
        int[] count=new int[26];
        int rs=0;

        for(char c: letters){
            count[c-'a']++;
        }
//        for(char c: letters){
//            System.out.printf("%s, count: %s\n",c, count[c-'a']);
//        }
        int size=(1<<n)-1;
//        for (int i = 0; i < n; i++) {
//            System.out.printf("i=%s, val=%s\n", i, scoreW[i]);
//        }
//        System.out.println(size);
        //Time : O(2^n)
        for(int i=1;i<=size;i++){
            int curCase=i;
            int pos=0;
            int[] curCount = new int[26];
            boolean isValid=true;
            int curRs=0;

            //Time: O(n)
            while(curCase!=0){
                int curBit = curCase&1;

                if(curBit==1){
                    String curStr=words[pos];

                    for(int j=0;j<curStr.length();j++){
                        char curChar = curStr.charAt(j);
                        curCount[curChar-'a']++;
                        if(curCount[curChar-'a']>count[curChar-'a']){
                            isValid=false;
                            break;
                        }
                        curRs += score[curChar-'a'];
                    }
                }
                pos++;
                curCase=curCase>>1;
            }
            if(isValid){
                rs=Math.max(rs, curRs);
            }
        }
        return rs;
    }

    public static void solution(String[] words, int[] count, int[] score, int index, int curScore, int[] rs, int n){
        if(index==n){
            rs[0]=Math.max(rs[0], curScore);
            return;
        }
        rs[0]=Math.max(rs[0], curScore);
        //Time : O(n)
        for(int i=index;i<n;i++){
            String curStr=words[i];
            int[] prevCount=Arrays.copyOfRange(count, 0, 26);
            boolean isValid=true;
            int subScore=0;

            //Time : O(m+A)
            for(int j=0;j<curStr.length();j++){
                char c = curStr.charAt(j);
                count[c-'a']--;
                subScore+=score[c-'a'];

                if(count[curStr.charAt(j)-'a']<0){
                    isValid=false;
                    break;
                }
            }
            if(isValid){
//                System.out.println(subScore+curScore);
                solution(words, count, score, i+1, curScore+subScore, rs, n);
            }
            count=prevCount;
        }
    }

    public static int maxScoreWordsBackTracking(String[] words, char[] letters, int[] score) {
        int n = words.length;
        int[] count = new int[26];

        for (char c : letters) {
            count[c - 'a']++;
        }
        int[] rs=new int[1];
        solution(words, count, score, 0, 0, rs, n);
        return rs[0];
    }

    public static int maxScoreWordsReference(String[] words, char[] letters, int[] score) {
        int W = words.length;
        // Count how many times each letter occurs
        freq = new int[26];
        for (char c: letters) {
            freq[c - 'a']++;
        }
        maxScore = 0;
        check(W - 1, words, score, new int[26], 0);
        return maxScore;
    }

    // Check if adding this word exceeds the frequency of any letter
    private static boolean isValidWord(int[] subsetLetters) {
        for (int c = 0; c < 26; c++) {
            if (freq[c] < subsetLetters[c]) {
                return false;
            }
        }
        return true;
    }

    private static int maxScore;
    private static int[] freq;

    private static void check(int w, String[] words, int[] score, int[] subsetLetters, int totalScore) {
        if (w == -1) {
            // If all words have been iterated, check the score of this subset
            maxScore = Math.max(maxScore, totalScore);
            return;
        }
        // Not adding words[w] to the current subset
        check(w - 1, words, score, subsetLetters, totalScore);
        // Adding words[w] to the current subset
        int L = words[w].length();
        for (int i = 0; i < L; i++) {
            int c = words[w].charAt(i) - 'a';
            subsetLetters[c]++;
            totalScore += score[c];
        }

        if (isValidWord(subsetLetters)) {
            // Consider the next word if this subset is still valid
            check(w - 1, words, score, subsetLetters, totalScore);
        }
        // Rollback effects of this word
        for (int i = 0; i < L; i++) {
            int c = words[w].charAt(i) - 'a';
            subsetLetters[c]--;
            totalScore -= score[c];
        }
    }

    public static void main(String[] args) {
        //** Requirement
        //- Given (a list of words), (list of  single letters) (might be repeating) and (score of every character).
        //* Return (the ("maximum") score of (any valid set of words)) formed by using (the given letters) (words[i] cannot be used two or more times).
        //- It is not necessary to use (all characters) in letters and (each letter) can only be (used once).
        //  + Mỗi letter sử dụng 1 lần:
        //  E.g:
        //  Có 3 letters 'a' --> Sử dụng 'a' 3 lần.
        //- Score of letters 'a', 'b', 'c', ... ,'z' is given by score[0], score[1], ... , score[25] respectively.
        //Example 1:
        //Input:
        // words = ["dog","cat","dad","good"],
        // letters = ["a","a","c","d","d","d","g","o","o"],
        // score = [1,0,9,5,0,0,3,0,0,0,0,0,0,0,2,0,0,0,0,0,0,0,0,0,0,0] ==> All characters
        //Output: 23
        //Explanation:
        //Score  a=1, c=9, d=5, g=3, o=2
        //Given letters, we can form the words "dad" (5+1+5) and "good" (3+2+2+5) with a score of 23.
        //Words "dad" and "dog" only get a score of 21.
        //
        //
        //** Idea
        //1.
        //1.0,
        //* Method-1:
        //- Constraint
        //1 <= words.length <= 14
        //1 <= words[i].length <= 15
        //1 <= letters.length <= 100
        //letters[i].length == 1
        //score.length == 26
        //0 <= score[i] <= 10
        //words[i], letters[i] contains only lower case English letters.
        //==> Length của words và letters không quá lớn
        //  --> Có thể sẽ bruteforce được.
        //
        //- Brainstorm
        //- Mỗi words sẽ có  score riêng
        //Example 1:
        //Input:
        // words = ["dog","cat","dad","good"],
        // letters = ["a","a","c","d","d","d","g","o","o"],
        // score = [1,0,9,5,0,0,3,0,0,0,0,0,0,0,2,0,0,0,0,0,0,0,0,0,0,0]
        //Output: 23
        //Explanation:
        //Score  a=1, c=9, d=5, g=3, o=2
        //Given letters, we can form the words "
        // dad" (5+1+5) and "good" (3+2+2+5) with a score of 23.
        //Words
        // "dad" and "dog" only get a score of 21.
        //==> dog thì mất 1 o --> Không thể tạo được good nữa.
        //
        //+ count(a) = 2
        //+ count(c) = 1
        //+ count(d) = 3
        //+ count(g) = 1
        //+ count(o) = 2
        //
        //- 1 set of words có thể tạo từ letters thoả mãn:
        //  + count (every character) in (words) <= count (characters) in (letters)
        //  + Check ntn?
        //  ==> Tính count của 2 collection ==> Mỗi lần count sẽ so sánh với count letters chung là được.
        //
        //1.1, Optimization
        //- Giảm space O(N) --> O(A)
        //  + Chỉ cần bỏ cái đoạn tính score cho từng word là được.
        //
        //1.2, Complexity
        //- N is the number of words
        //- M is the max length of all of word
        //- A is the size of alphabet
        //- Space : O(n)
        //- Time : O(2^n*n*m + A)
        //
        //2.
        //2.0, Backtracking
        //- Nếu làm backtrack thì:
        //- Complexity:
        //  + Time : (Khá khó tính)
        //** Rule:
        //  + Tính vòng ngoài trước * Với vòng trong:
        //  Time : O(2^n*(m+A))
        //- Rs không cần phải đến (index==n)
        //  + Lúc nào cũng xét max là được.
        //
        //** Kinh nghiệm:
        //  + Thường mấy bài dạng này:
        //      + Backtracking sẽ faster.
        //
        //#Reference:
        //2151. Maximum Good People Based on Statements
        //
//        String[] words = {"dog","cat","dad","good"};
//        char[] letters = {'a','a','c','d','d','d','g','o','o'};
//        int[] score = {1,0,9,5,0,0,3,0,0,0,0,0,0,0,2,0,0,0,0,0,0,0,0,0,0,0};

        String[] words = {"azb","ax","awb","ayb","bpppp"};
        char[] letters = {'z','a','w','x','y','b','p','p','p'};
        int[] score = {10,1,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,3,2,3,3};
        System.out.println(maxScoreWords(words, letters, score));
        System.out.println(maxScoreWordsOptimization(words, letters, score));
        System.out.println(maxScoreWordsBackTracking(words, letters, score));
        System.out.println(maxScoreWordsReference(words, letters, score));
    }
}
