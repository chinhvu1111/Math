package contest;

import javafx.util.Pair;

import java.util.*;

public class E57_ReplaceQuestionMarksInStringToMinimizeItsValue {

    public static String minimizeStringValue(String s) {
        int[] freq = new int[26];
        //Space: O(const)
        //Time : O(k) (k= 26*log(26)
        PriorityQueue<Pair<Integer, Character>> pq = new PriorityQueue<>((a, b)->{
            if(Objects.equals(a.getKey(), b.getKey())){
                return a.getValue()-b.getValue();
            }
            return a.getKey()-b.getKey();
        });

        //Time: O(n)
        for(int i = 0; i < s.length(); i++) {
            if(s.charAt(i) != '?') {
                freq[s.charAt(i) - 'a']++;
            }
        }
        //Time: O(const)
        for(int i=0;i<26;i++){
            pq.add(new Pair<>(freq[i], (char)('a'+i)));
        }
        //Space: O(N)
        int n=s.length();
        List<Character> list=new ArrayList<>();

        for(int i=0;i<n;i++){
            if(s.charAt(i)=='?'){
                Pair<Integer, Character> prefNode= pq.poll();
                int curFreq = prefNode.getKey();
                list.add(prefNode.getValue());
                pq.add(new Pair<>(curFreq+1, prefNode.getValue()));
            }
        }
        //Time: n*O(log(n))
        Collections.sort(list);
        int index=0;
        StringBuilder rs=new StringBuilder(s);
        for(int i=0;i<n;i++){
            if(s.charAt(i)=='?'){
                rs.setCharAt(i, list.get(index++));
            }
        }
        return rs.toString();
    }

    public static String minimizeStringValueWrong(String s) {
        TreeSet<Pair<Integer, Integer>> set= new TreeSet<>((a, b) ->{
            if(!Objects.equals(a.getValue(), b.getValue())){
                return a.getValue()-b.getValue();
            }
            return a.getKey()-b.getKey();
        });
        boolean[] visited=new boolean[200];
        int[] count=new int[200];
        int n=s.length();
        StringBuilder rs=new StringBuilder();

        for(int i=0;i<n;i++){
            System.out.println(set);
            Pair<Integer, Integer> curPair=new Pair<>((int)s.charAt(i), count[s.charAt(i)]);
            if(s.charAt(i)!='?'){
                rs.append(s.charAt(i));
                set.remove(curPair);
                count[s.charAt(i)]++;
                curPair=new Pair<>((int)s.charAt(i), count[s.charAt(i)]);
                set.add(curPair);
                visited[(int)s.charAt(i)]=true;
                continue;
            }
            boolean isNotVisited=false;
            for(int j='a';j<='z';j++){
                if(!visited[j]){
                    visited[j]=true;
                    set.add(new Pair<>(j, 1));
                    count[j]=1;
                    isNotVisited=true;
                    rs.append((char)j);
                    break;
                }
            }
            if(!isNotVisited&&!set.isEmpty()){
                char chooseChar = (char) set.pollFirst().getKey().intValue();
                count[(int)chooseChar]++;
                curPair=new Pair<>((int)chooseChar, count[(int)chooseChar]);
                set.add(curPair);
                rs.append(chooseChar);
            }
        }
        return rs.toString();
    }

    public static void main(String[] args) {
        //* Requirement
        //- You are given a string s. s[i] is either a lowercase English letter or '?'.
        //For a string t having length m containing only lowercase English letters,
        // we define the function cost(i) for an index i as the number of characters equal to t[i]
        // that appeared before it, i.e. in the range [0, i - 1].
        //The value of t is the sum of cost(i) for all indices i.
        //For example, for the string t = "aab":
        //- cost(0) = 0
        //- cost(1) = 1
        //- cost(2) = 0
        //Hence, the value of "aab" is 0 + 1 + 0 = 1.
        //- Your task is to replace all occurrences of '?' in s with any lowercase English letter
        // so that the value of s is minimized.
        //
        //* Return a string denoting the modified string with replaced occurrences of '?'.
        // If there are multiple strings resulting in the minimum value,
        // Return the lexicographically smallest one.
        //
        // Idea
        //1.
        //1.0,
        //- Constraint:
        //1 <= s.length <= 10^5
        //s[i] is either a lowercase English letter or '?'.
        //--> Khá lớn ==> O(n), O(n*log(n))
        //- Brainstorm
        //- Cost[i] là số lượng character giống s[i] trước đó
        //==> Tìm string sao cho result minimized
        //
        //Ex;
        //aba?bb
        //abaabb = 0+0+1+2+1+2 = 6
        //ababbb = 0+0+1+1+2+3 = 7
        //s=aba?bb
        //+ i=0:
        // ['a':1]
        //+ i=1
        // ['a':1, 'b':1]
        //+ i=2
        // ['a':2, 'b':1]
        //+ i=3
        // ['a':2, 'b':1]
        //
        //- Nếu ta tư duy theo kiểu:
        //  + Ưu tiên chọn char chưa xuất hiện
        //  + Nếu không có char chưa xuất hiện ==> Chọn min count char xuất hiện rồi
        //- Special cases:
        //Ex:
        //s = abcdefghijklmnopqrstuvwxy??
        //+ Current rs = "abcdefghijklmnopqrstuvwxy(za)"
        //+ Expected rs = "abcdefghijklmnopqrstuvwxy(az)"
        //  + Ở đây z chưa được điền --> ưu tiên z (a sẽ được điền sau)
        //  + Sau đó ta điền a
        //--> Vấn đề là result chưa là lexicographically smallest
        //- Liệu ta có thể re-optimize string sau khi xử lý?
        //+ abcdefghijklmnopqrstuvwxy(za) => abcdefghijklmnopqrstuvwxy(az)
        //  + cost[z]=0, cost[a]=1
        //  Ex: ***z**a
        //  + Khi đổi chỗ z và a thì trong khoảng (z-a) có thể có z
        //  + cost[z]=0, cost[a]=1
        //- Tư duy rộng ra --> Có thể xét đến các char sau không?
        //+
        //
//        String s = "a?a?";
        String s = "abcdefghijklmnopqrstuvwxy??";
//        String s = "abc?dde?";
        //abc?dde?
        //- Cách trên return:
        //+ result:   abcdddef
        //+ Expected: abcfddeg
        //- Nếu frequency cộng dần ==> Nó sẽ bị trường hợp swap
        //==> Frequency cần tính count + sort for the whole array
        //Ex:
        //s = abc?dde?
        //index = 3 : Ta có thể điền (d/e/f/...) ==> Nhưng ta nên ưu tiên điền
        //  + Phần tử mà không ảnh hưởng đến (prev count, after count) ==> Vì nó có thể ảnh hưởng cộng dồn
        //--> Ta sẽ tính cho whole array
        //  + Ngoài ra sẽ dễ dàng cho việc swap char --> Lấy smallest string
        //
        //1.1, Optimization
        //1.2 Complexity
        //- Space: O(n+k)
        //- Time: n*O(log(n))
        //
        //#Reference:
        //2734. Lexicographically Smallest String After Substring Operation
        //
        System.out.println(minimizeStringValueWrong(s));
        System.out.println(minimizeStringValue(s));
    }
}
