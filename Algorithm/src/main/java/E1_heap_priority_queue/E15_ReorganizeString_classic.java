package E1_heap_priority_queue;

import javafx.util.Pair;

import java.util.*;

public class E15_ReorganizeString_classic {

    public static String reorganizeStringWrong(String s) {
        HashMap<Character, Integer> mapCount=new HashMap<>();
        int n=s.length();

        for(int i=0;i<n;i++){
            mapCount.put(s.charAt(i), mapCount.getOrDefault(s.charAt(i), 0)+1);
        }
        char[] rs=new char[n];
        Arrays.fill(rs, '-');
        PriorityQueue<Pair<Character, Integer>> queue=new PriorityQueue<>(new Comparator<Pair<Character, Integer>>() {
            @Override
            public int compare(Pair<Character, Integer> o1, Pair<Character, Integer> o2) {
                return o2.getValue()- o1.getValue();
            }
        });
        for(Map.Entry<Character, Integer> e: mapCount.entrySet()){
            queue.add(new Pair(e.getKey(), e.getValue()));
        }

//        while (!queue.isEmpty()){
//            Pair<Character, Integer> e=queue.poll();
//            int i=0;
//            while(i<n&&rs[i]!='-'){
//                i++;
//            }
//            int count = e.getValue();
//            while (count>0){
//                if(i>=n){
//                    return "";
//                }
//                if(rs[i]==e.getKey()){
//                    return "";
//                }else if(rs[i]=='-'){
//                    rs[i]=e.getKey();
//                }
//                i=(i+2);
//                count--;
//            }
//            System.out.println(String.valueOf(rs));
//        }
        while (!queue.isEmpty()){
            Pair<Character, Integer> e=queue.poll();
            int i=0;
            while(i<n&&rs[i]!='-'){
                i++;
            }
            int count = e.getValue();
            while (count>0){
                if(i>=n){
                    return "";
                }
                if(rs[i]==e.getKey()){
                    return "";
                }else if(rs[i]=='-'){
                    rs[i]=e.getKey();
                }
                i=(i+2);
                count--;
            }
            System.out.println(String.valueOf(rs));
        }
        return String.valueOf(rs);
    }

    public static String reorganizeString(String s) {
        //Space: O(26)
        HashMap<Character, Integer> mapCount=new HashMap<>();
        int n=s.length();

        for(int i=0;i<n;i++){
            mapCount.put(s.charAt(i), mapCount.getOrDefault(s.charAt(i), 0)+1);
        }
        //Space: O(n)
        char[] rs=new char[n];
        Arrays.fill(rs, '-');
        //Space: O(26)
        PriorityQueue<Pair<Character, Integer>> queue=new PriorityQueue<>(new Comparator<Pair<Character, Integer>>() {
            @Override
            public int compare(Pair<Character, Integer> o1, Pair<Character, Integer> o2) {
                return o2.getValue()- o1.getValue();
            }
        });
        for(Map.Entry<Character, Integer> e: mapCount.entrySet()){
            queue.add(new Pair(e.getKey(), e.getValue()));
        }
        int i=0;

        while (!queue.isEmpty()){
            Pair<Character, Integer> e=queue.poll();
            int curCount = e.getValue();
            int countCycle=0;
            //Làm sao để biết nó stuck
            //Time: O(n)
            while (i<n&&curCount>0&&countCycle<=1){
                if(rs[i]=='-'&&((i==0||rs[i-1]!=e.getKey())&&(i==n-1||rs[i+1]!=e.getKey()))){
                    rs[i]=e.getKey();
                    curCount--;
                }
                if(i==n-1){
                    countCycle++;
                }
                i=(i+1)%n;
            }
            if(curCount>=1){
                return "";
            }
            System.out.println(String.valueOf(rs));
        }
        return String.valueOf(rs);
    }

    public static String reorganizeStringRefer(String s) {
        int[] charCounts=new int[26];

        for(char c: s.toCharArray()){
            charCounts[c-'a']++;
        }
        int maxCount=0, letter=0;
        for (int i = 0; i < charCounts.length; i++) {
            if(charCounts[i]>maxCount){
                maxCount=charCounts[i];
                letter=i;
            }
        }
        if(maxCount>(s.length()+1)/2){
            return "";
        }
        char[] rs=new char[s.length()];
        int index=0;

        while (charCounts[letter]!=0){
            rs[index] = (char)(letter+'a');
            index+=2;
            charCounts[letter]--;
        }
        for(int i=0;i<charCounts.length;i++){
            while (charCounts[i]>0){
                if(index>=s.length()){
                    index=1;
                }
                rs[index]=(char)(i+'a');
                index+=2;
                charCounts[i]--;
            }
        }
        return String.valueOf(rs);
    }

    public static String reorganizeStringReferPriorityQueue(String s) {
        int[] charCounts = new int[26];
        for (char c : s.toCharArray()) {
            charCounts[c - 'a']++;
        }

        // Max heap ordered by character counts
        PriorityQueue<int[]> pq = new PriorityQueue<int[]>((a, b) -> Integer.compare(b[1], a[1]));
        for (int i = 0; i < 26; i++) {
            if (charCounts[i] > 0) {
                pq.offer(new int[] {i + 'a', charCounts[i]});
            }
        }
        StringBuilder sb=new StringBuilder();
        while (!pq.isEmpty()){
            int[] first=pq.poll();
            if(sb.length()==0 || first[0] != sb.charAt(sb.length()-1)){
                sb.append((char)first[0]);
                if(--first[1]>0){
                    pq.offer(first);
                }
            }else{
                if(pq.isEmpty()){
                    return "";
                }
                int[] second = pq.poll();
                sb.append((char)second[0]);
                if(--second[1]>0){
                    pq.offer(second);
                }
                pq.offer(first);
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        //** Requirement
        //- Given a string s, rearrange (the characters of s) so that (any two adjacent characters) are not the same.
        //* Return (any possible rearrangement of s) or return "" if not possible.
        //
        // Idea
        //1.
        //1.0,
        // Method-1:
        //- Constraint
        //1 <= s.length <= 500
        //s consists of lowercase English letters.
        //
        //- Brainstorm
        //
        //Example 2:
        //Input: s = "aaab"
        //Output: ""
        //
        //Ex:
        //a,,a,
        //
//        String s = "aaab";
//        String s = "aab";
//        String s = "sfffp";
        String s = "aabbcc";
//        String s = "aaab";
        //a,,a
        //- Điền hết chuỗi trước
        //- Sau đó mới điền phần cần fill
        //Ex:
        //s = aabbcc
        //t = abacbc
        //Nếu điền ntn:
        //a,b,a,b,c,c
        //  + 2 chữ c cạnh nhau
        //  ==> Sai.
        //- Chứng minh:
        //a,c,a,b,c,b
        //+ Khi điền xong a
        //  ==> Sang new char ta có thể điền sát luôn cũng được
        //a,b,a,b
        //- Điền lần lượt vào:
        //  + For toàn bộ array điền rs[i] = x, khi:
        //      + rs[i-1]!=x, rs[i+1]!=x
        //  + Số lần điền sẽ bằng count của char đó.
        //- Ta cũng cần back lại first:
        //  + i=(i+1)%n
        //
        //1.1, Optimization
        //- Không dùng priority queue
        //- Ta có thể pick cho thằng có count max nhất
        //  + i=0 -> i=2 -> i=4 ...
        //- Khi điền xong max, ta sẽ điền cho các char còn lại:
        //  + Vì i=0 đã fill max rồi:
        //      ==> i+=2 sẽ luôn đúng
        //      + i+1>=n: i=1
        //          + i==0: Điền rồi
        //* Kinh nghiệm:
        //- Dạng bài điền vị trí này ==> Ưu tiên count của thằng max nhất
        //- Nếu i==0 đã điền rồi ==> i=1 (start)
        //
        //- Priority queue:
        //- Dùng tư duy tương tự bài E132_LongestHappyString
        //- Ưu tiên theo count của char
        //  + Nếu số đằng trước đã điền rồi + Mà count vẫn max thì lấy next char ngay sau.
        //
        //1.2, Complexity
        //- Space: O(k)
        //- Time: O(n)
        //
        //#Reference:
        //3078. Match Alphanumerical Pattern in Matrix I
        //2842. Count K-Subsequences of a String With Maximum Beauty
        //916. Word Subsets
        //1585. Check If String Is Transformable With Substring Sort Operations
        //2982. Find Longest Special Substring That Occurs Thrice II
        //
//        System.out.println(reorganizeStringWrong(s));
        System.out.println(reorganizeString(s));
        System.out.println(reorganizeStringRefer(s));
        System.out.println(reorganizeStringReferPriorityQueue(s));
    }
}
