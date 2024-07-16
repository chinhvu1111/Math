package E1_leetcode_medium_dynamic;

import com.sun.xml.internal.fastinfoset.util.PrefixArray;

import java.util.HashMap;
import java.util.Map;

public class E163_EncodeStringWithShortestLength {

    public static String shortestEncodedString(String s){
        int n=s.length();
        for(int l=1;l<=n;l++){
            int j=0;
            boolean isValid=true;
            for(;j+2*l-1<n;j++){
                String curStr=s.substring(j, j+l);
                String nextStr=s.substring(j+l, j+2*l);
//                System.out.printf("j: %s, curStr: %s, nextStr: %s\n", j, curStr, nextStr);
                if(!curStr.equals(nextStr)){
                    isValid=false;
                    break;
                }
            }
//            System.out.printf("j: %s, next: %s\n", j, j+2*l-1);
            if(isValid&&2*l-1<n){
                int lenNum = String.valueOf(n/l).length();
                if(lenNum+3<s.length()){
                    String pattern = s.substring(0, l);
                    return n/l+"["+pattern+"]";
                }
            }
        }
        return s;
    }
    public static String encode(String s) {
        //Time: O(n^2)
        //Space: O(n^2)
        String[][] dp = new String[s.length()][s.length()];

        //Time : O(n)
        for(int l=0;l<s.length();l++){
            //i: 0 --> n-l-1
            //Time : O(n)
            for(int i=0;i<s.length()-l;i++){
                int j=i+l;
                //Time : O(n)
                String subStr=s.substring(i, j+1);
                dp[i][j]=subStr;
                if(j-i>=4){
                    //Time : O(n)
                    //Tính toán dp[i][j] theo every
                    //+ k: (i -> j-1)
                    for(int k=i;k<j;k++){
                        if((dp[i][k]+dp[k+1][j]).length()< dp[i][j].length()){
                            dp[i][j]=dp[i][k]+dp[k+1][j];
                        }
                    }
                    for(int k=0;k<subStr.length();k++){
                        String repeatedStr=subStr.substring(0, k+1);
                        //Time: O(n)
                        if(subStr.length()%repeatedStr.length()==0 &&subStr.replaceAll(repeatedStr,"").length()==0){
//                            String encodedStr = subStr.length()/repeatedStr.length()+"["+repeatedStr+"]";
//                            System.out.println(dp[i][i+k]);
//                            System.out.println(repeatedStr);
                            //Điền đây là dp[i][i+k] vì:
                            //  + k ở đây là khoảng cách (0 --> k) trong current substring <=> (i -> j)
                            //  + Có thể:
                            //      + dp[i][i+k] == dp[i][j]
                            //  ==> dp[i][j] đã được update bên trên:
                            //  + SubString không tối ưu bằng việc tính dp[i][j] theo (dp[i][k]+dp[k+1][j])
                            //  ==> Nó sẽ 2 lần optimize ở đây
                            //      + 1 lần tính theo dp
                            //      + 1 lần repeate string.
                            String encodedStr = subStr.length()/repeatedStr.length()+"["+dp[i][i+k]+"]";
                            if(encodedStr.length()<dp[i][j].length()){
                                dp[i][j]=encodedStr;
                            }
                        }
                    }
                }
            }
        }
        return dp[0][s.length()-1];
    }

    public static String encodeWrong(String s) {
        int n=s.length();
        String[][] encodedStr=new String[n][n];

        //Loop:
        //i: 0 -> n-1
        //j: i -> n-1
        //
        //i: 0 -> n-1
        //j: 0 -> i
        //dp[i][j] = min(dp[
//        for(int i=0;i<n;i++){
//            for(int j=i;j<n;j++){
//                String curStr=s.substring(i, j+1);
//                encodedStr[i][j]=shortestEncodedString(curStr);
//            }
//        }
        for(int i=0;i<n;i++){
            encodedStr[i][i]=s.charAt(i)+"";
        }
        for(int l=2;l<=n;l++){
            for(int i=0;i+l-1<n;i++){
                int j=i+l-1;
                int minLen=Integer.MAX_VALUE;
                String curRs="";

                for(int k=i;k<=j;k++){
                    //i,k,j
//                    String encodedLeft=encodedStr[i][k]+s.charAt(j);
                    String leftStr=(k==j)?shortestEncodedString(encodedStr[i][j-1])+s.charAt(j):encodedStr[i][k];
                    String righStr=k+1>j?"":shortestEncodedString(s.substring(k+1, j+1));
                    System.out.printf("i: %s, k: %s, j:%s, left: %s, right: %s\n", i, k, j, leftStr, righStr);
                    if(minLen>leftStr.length()+righStr.length()){
                        minLen=leftStr.length()+righStr.length();
                        curRs=leftStr+righStr;
//                        System.out.printf("i: %s, k:%s, left: %s, right: %s\n", i, k, leftStr, righStr);
                    }
                    String curEncodedStr=shortestEncodedString(leftStr+righStr);
                    if(minLen>curEncodedStr.length()){
                        minLen=curEncodedStr.length();
                        curRs=curEncodedStr;
//                        System.out.printf("i: %s, k:%s, cur: %s\n", i, k, curEncodedStr);
                    }
                }
//                String subStr=s.substring(i, j+1);
//                if(minLen>subStr.length()){
//                    curRs=subStr;
//                }
//                        System.out.printf("i: %s, k:%s, cur: %s\n", i, k, curEncodedStr);
                encodedStr[i][j]=curRs;
                System.out.printf("i: %s, j:%s, curStr: %s\n", i, j, curRs);
            }
        }
//        System.out.println();
//        String[] dp=new String[n];

//        for(int i=0;i<n;i++){
//            int minLen=Integer.MAX_VALUE;
//            String curStr="";
//
//            for(int j=i;j>=0;j--){
//                int prevLen = j>0?dp[j-1].length():0;
//                String prevStr = j>0?dp[j-1]:"";
//                if(minLen>encodedStr[j][i].length()+prevLen){
//                    minLen=encodedStr[j][i].length()+prevLen;
//                    curStr=prevStr+encodedStr[j][i];
//                    System.out.printf("j: %s, i: %s, %s, curStr: %s\n", j, i, encodedStr[j][i], curStr);
//                }
//            }
//            dp[i]=curStr;
//            System.out.printf("Index: %s, str: %s\n", i, dp[i]);
//        }
        return encodedStr[0][n-1];
    }

    public static Map<String, String> map = new HashMap<String, String>();
    public static String encodeMemo(String s) {
        if (s == null || s.length() == 0) return "";
        if (s.length() <= 4) return s;
        if (map.containsKey(s)) return map.get(s);
        String ret = s;
        //
        for (int k = s.length() / 2; k < s.length(); k ++) {
            String pattern = s.substring(k);
            int times = countRepeat(s, pattern);
            if (times * pattern.length() != s.length()) continue;
            String candidate = Integer.toString(times) + "[" + encode(pattern) + "]";
            if (candidate.length() < ret.length()) ret = candidate;
        }
        for (int i = 1; i < s.length(); i ++) {
            String left = encodeMemo(s.substring(0, i));
            String right = encodeMemo(s.substring(i));
            String candidate = left + right;
            if (candidate.length() < ret.length()) ret = candidate;
        }
        map.put(s, ret);
        return ret;
    }

    private static int countRepeat(String s, String pattern) {
        int times = 0;
        while (s.length() >= pattern.length()) {
            String sub = s.substring(s.length() - pattern.length());
            if (sub.equals(pattern)) {
                times ++;
                s = s.substring(0, s.length() - pattern.length());
            } else return times;
        }
        return times;
    }

    public static void main(String[] args) {
        //* Requirement
        //- Given a string s, encode the string such that (its encoded length) is (the shortest).
        //The encoding rule is:
        //  + k[encoded_string], where the encoded_string inside the square brackets is being repeated exactly (k times).
        //  + k should be a positive integer.
        //* If an encoding process does (not make the string shorter), then (do not encode it).
        // If there are several solutions, return any of them.
        //
        // Idea
        //1.
        //1.0,
        //- Constraint:
        //1 <= s.length <= 150
        //s consists of only lowercase English letters.
        //
        //** Brainstorm
        //- Nếu encoded shorter --> Không dùng
        //- Có thể cùng 1 string có nhiều cách encode không
        //Ex:
        //abab
        //=> 2[ab]
        //  + Length > --> Không encode
        //abcabcabc
        // = 3[abc]
        //aaaabcaaaabc
        //=> 4[a]bc4[a]bc
        //=> 2[4[a]bc]
        //- Thế nên char đi qua rồi:
        //  + Ta vẫn cần xem string hiện tại có encode được không
        //
        //- Encode substring --> Không ảnh hưởng đến việc (encoded parent string)
        //- Xác định pattern ntn?
        //Ex:
        //abc|abc|abc|abc
        //abcabc|abcabc
        //==> 3[abc] better
        //- Ta có thể chứng minh là pattern có length càng nhỏ --> String càng encode shorter
        //
        //- Sẽ có 1 part of string là không follow pattern nào cả:
        //  + Ta sẽ giữ nguyên part đó.
        //
        //- Ta cần 1 mốc index để check pattern:
        //Ex:
        //abc|a(b)c|abc|abc
        //+ Đứng ở (b) thì ta có pattern nào không?
        //+ Shortest string encoded tại (b) là gì?
        //  dp[i] = shortest_string(i,j) + dp[j-1]
        //- Tìm shortest encoded string trong khoảng (i,j)
        //  + Toàn bộ có thể encode được ==> Không thừa characters nào
        //
        //- Làm cách trên khá khó khăn vì:
        //  + Đoạn tính min(i,j) ==> nó không cùng pattern với đoạn tính dp[i]
        //==> Việc tính ntn sẽ không ra kết qủa.
        //- Đã nghĩ thử tính dp[i][j]:
        //  + Nhưng vẫn chưa đúng ==> do tính dp[i][j] ==> dp[i][k]+dp[k+1][j]
        //  ==> Nhìn có vẻ đúng nhưng k work.
        //
        //- Idea có thể sát ==> Chưa implement được do:
        //  + Chưa chốt được main idea.
        //
        //* Solution:
        //  + Tính theo formula k bên trên
        //  + Nhưng sẽ tính repeated cho subString nếu (j-i>=4)
        //      + 2 lần optimizations
        //
        //* Điền đây là dp[i][i+k] vì:
        //  + k ở đây là khoảng cách (0 --> k) trong current substring <=> (i -> j)
        //  + Có thể:
        //      + dp[i][i+k] == dp[i][j]
        //  ==> dp[i][j] đã được update bên trên:
        //  + SubString không tối ưu bằng việc tính dp[i][j] theo (dp[i][k]+dp[k+1][j])
        //  ==> Nó sẽ 2 lần optimize ở đây
        //      + 1 lần tính theo dp
        //      + 1 lần repeate string.
        //===== CODE
        //String encodedStr = subStr.length()/repeatedStr.length()+"["+dp[i][i+k]+"]";
        //===== CODE
        //
        //1.1, Optimization
        //1.2, Complexity
        //- Space: O(n^2)
        //- Time: O(n^4) => O(n^5)
        //
//        String s= "aaaabcaaaabc";
//        String s= "aaaaaaaaaa";
//        String s= "abbbabbbcabbbabbbc";
        String s= "aabcabca";
        //Output: "2[abbbabbbc]"
        //Expected: "2[2[abbb]c]"
//        System.out.println(shortestEncodedString(s.substring(0, 4)));
//        System.out.println(shortestEncodedString(s));
//        System.out.println(shortestEncodedString("b"));
//        System.out.println(shortestEncodedString("aa"));
        //
        //2. Memoziation
        //2.0,
        //- Given s
        //- Ta cũng tính toán luôn pattern cho cả chuỗi s
        //  ==> Nếu có pattern --> Ta sẽ lấy pattern có (min length)
        //- Tách s thành 2 phía:
        //  - (i, k, j)
        //  ==> Calculate encode(s.subString(i,k)) + encode(s.subString(k, j)) (Recursively)
        //- Memo:
        //  + Map.put(s, minStr)
        //- return minStr.
        //
        //2.1, Optimization
        //2.2, Complexity
        //- Space: O(n^2)
        //- Time: O(n^2)
        //Time Complexity:
        //
        //The time complexity of the algorithm depends on the following factors:
        //
        //- Checking if the input string s is null or empty: O(1)
        //- Checking if the length of s is less than or equal to 4: O(1)
        //- Checking if the input string s is already in the map: O(1) on average, assuming a good hash function is used.
        //  - The outer loop that iterates through the length of s from s.length() / 2 to s.length() - 1: O(n/2), where n is the length of s.
        //  - Inside this loop, the countRepeat function is called, which has a time complexity of O(m), where m is the length of the pattern.
        //  - The length of the pattern is at most n/2, so the time complexity of the countRepeat function is O(n/2).
        //  - The inner loop that iterates through the length of s from 1 to s.length() - 1: O(n).
        //- Inside this loop, the encodeMemo function is called recursively twice, each with a substring of s.
        // The time complexity of the recursive calls is O(n/2) for each call, as the length of the substring
        // is approximately half the length of the original string.
        //- Putting it all together, the overall time complexity of the encodeMemo function is:
        //+ O(1) + O(1) + O(1) + O(n/2) * O(n/2) + O(n) * O(n/2) = O(n^2)
        //The time complexity of the algorithm is quadratic, O(n^2), where n is the length of the input string s.
        //
        //- Space Complexity:
        //  + The space complexity of the algorithm is determined by the following factors:
        //  + The use of the map to store the encoded strings:
        //      + The space complexity depends on (the number of unique substrings) in the input string s.
        //      In the worst case, where (each substring) is unique, (the space complexity) would be O(n^2), where n is the length of s.
        //  + The recursive calls to (the encodeMemo function):
        //      + The maximum depth of the recursion is O(n), as the length of the substrings decreases by approximately half
        //      in each recursive call.
        //
        System.out.println(shortestEncodedString("abbbabbbcab"));
        System.out.println(encodeWrong(s));
        System.out.println(encode(s));
        System.out.println(encodeMemo(s));
        //#Reference:
        //1883. Minimum Skips to Arrive at Meeting On Time
        //1138. Alphabet Board Path
        //2131. Longest Palindrome by Concatenating Two Letter Words
        //
    }
}
