package E1_trie_topic;

import java.util.*;

public class E2_LongestSubstringWithAtMostKDistinctCharacters_slidewindow_hashtable {

    public static int lengthOfLongestSubstringKDistinct(String s, int k) {
        int n=s.length();
        if(n==0){
            return 0;
        }
        if(k==0){
            return 0;
        }
        HashMap<Character, Integer> hashNearestCount=new HashMap<>();
        int rs=1;
        int start=0;
        int currentDistinct=1;
        hashNearestCount.put(s.charAt(0), 1);

        for(int i=1;i<n;i++){
//            System.out.printf("%s %s\n",s.charAt(i), hashNearestIndex.containsKey(s.charAt(i)));
            if(currentDistinct==k&&!hashNearestCount.containsKey(s.charAt(i))){
                if(!hashNearestCount.containsKey(s.charAt(i))||hashNearestCount.get(s.charAt(i))==0){
                    int j=start;
                    for(;j<i;j++){
                        int newValue=hashNearestCount.get(s.charAt(j))-1;
                        hashNearestCount.put(s.charAt(j), newValue);

                        if(newValue==0){
                            hashNearestCount.remove(s.charAt(j));
                            break;
                        }
                    }
                    start=j+1;
                }
            }else if(!hashNearestCount.containsKey(s.charAt(i))){
                currentDistinct++;
            }
            //Muốn get kết quả từ đây --> (end, start)==> valid<=> hash.size()==k
            rs=Math.max(rs, i-start+1);
//            System.out.printf("After %s %s %s\n", start, i, i-start+1);
            hashNearestCount.put(s.charAt(i), hashNearestCount.getOrDefault(s.charAt(i),0)+1);
        }
        return rs;
    }

    public static int lengthOfLongestSubstringKDistinctMethod2(String s, int k) {
        int n=s.length();
        if(n==0){
            return 0;
        }
        if(k==0){
            return 0;
        }
        int rs=1;
        Map<Character, Integer> rightMostPosition=new HashMap<>();
        int left=0, right=0;

        //a,b,a,c,c,c ==> Ở đây ta sẽ tìm cách remove phần tử có right most index
        //==> Tức là index lớn nhất về phía right.
        //+ Chỉ remove phần tử mới nhất được put (left --> right) thì mới có ý nghĩa.
        //+ min nhất của index chứa trong hashtable để remove
        while (right<n){
            rightMostPosition.put(s.charAt(right), right++);

            if(rightMostPosition.size()==k+1){
                int lowestIndex=Collections.min(rightMostPosition.values());
                rightMostPosition.remove(s.charAt(lowestIndex));
                left=lowestIndex+1;
            }
            rs=Math.max(rs, right-left);
        }
        return rs;
    }

    public static int lengthOfLongestSubstringKDistinctMethod3(String s, int k) {
        int n=s.length();
        if(n==0){
            return 0;
        }
        if(k==0){
            return 0;
        }
        int rs=1;
        Map<Character, Integer> rightMostPosition=new LinkedHashMap<>();
        int left=0, right=0;
        while (right<n){
            Character ch=s.charAt(right);

            rightMostPosition.remove(ch);
            rightMostPosition.put(ch, right++);

            if(rightMostPosition.size()==k+1){
                Map.Entry<Character, Integer> leftMost=rightMostPosition.entrySet().iterator().next();
                rightMostPosition.remove(leftMost.getKey());
                left=leftMost.getValue()+1;
            }
            rs=Math.max(rs, right-left);
        }

        return rs;
    }

    public static void main(String[] args) {
//        String s = "eceba";
//        int k = 2;
//        String s = "aa";
//        int k = 1;
//        String s = "abaccc";
//        int k = 2;
        String s = "aac";
        int k = 2;
        System.out.println(lengthOfLongestSubstringKDistinct(s, k));
        //** Đề bài:
        //- Tìm substring dài nhất có distinct char=2
        //
        //** Bài này tư duy như sau:
        //1.
        //1.0, Lỗi sai tư duy index nearest
        //- Không phải dùng mỗi index là xong --> Tức là tư duy giốn kiểu:
        //+ 1 hashset để remove phần tử
        //+ 1 hashIndex để lấy phần tử nearest index ==> sau đó thêm 1 phần tử mà mới ==> remove s[start] trước đó đi ==> end++ lên
        //==> Sẽ bị thiếu case sau:
        //VD: (a)b(a)ccc ==> Nếu remove (a) đi ==> result=3 sai
        //+ wrong result=3 <=> aba ==> Sai
        //+ correct result=4 <=> bccc
        //---> Khi đó ta remove (a, b) ==> chuỗi thành (accc)
        //- Mình sẽ cần remove từ left --> right + (count[s[j]]--)
        //+ Nếu count[s[j]]==0 --> Ta mới loại bỏ được 1 phần tử (DISTINCT)
        //--> current_distict--
        //==> Lúc đó ta sẽ tính được phần tử mới.
        //* a,b,a (KHÔNG PHẢI CỨ REMOVE START là remove hết các phần tử == S[start])
        //
        //1.1, Lỗi sai tư duy chọn phần tử
        //- Nếu result=max(result, [end/i]-start+1) ==> (i) phải thoả mãn điều kiện rồi (ĐƯỢC CHỌN RỒI) + start (Được changed rồi)
        //==> Xử lý trước khi lấy MAX
        //- Cần check (!hash.contains(s[i])) + current_distinct(Số phần tử distinct) ==> current_distinct++
        //- Nếu current_distinct<k + (!hash.contains(s[i]))(không contains phần tử thứ (i)) --> thêm thoải mái -> current_distinct++
        //
        //1.2, Trừ count các phần tử left --> right
        //- Loại bỏ phần tử bằng cách trừ count ==> Các phần tử xuất hiện nhiều lần.
        //--> count[i]==0 ==> Bỏ được 1 distinct ==> Ta có thể thêm (i) được rồi
        //
        //1.3, Complexity:
        //- Time complexity: O(n)
        //- Space complexity: O(n)
        //
        //- Method 2
        //2.
        //2.1, Tư duy như sau:
        //-
        //VD:
        // a,b,a,c,c,c ==> Ở đây ta sẽ tìm cách remove phần tử có right most index
        //==> Tức là index lớn nhất về phía right.
        //+ Chỉ remove phần tử mới nhất được put (left --> right) thì mới có ý nghĩa.
        //+ min nhất của index chứa trong hashtable để remove
        //
        //2.2, Complexity:
        //- Time complexity : O(n*k)
        //- Space complexity : O(k)
        //+ Nếu best cases có 2 distinct variables --> One pass tìm min chỉ mất O(1)
        //+ Nếu worst cases có k distinct variables --> Tìm min của k distinct variables ==> O(n*k)
        //+ K là số phần tử distinct elements.
        //
        //- Method 3
        //3.
        //3.1,
        //- Bài này dùng 1 structure là LinkedHashMap tận dụng:
        //+ Tính chất remove của hashMap --> O(1)
        //+ Tính chất order của linkedList --> first chính là phần tử có index sang left nhất (ch của nó update right nhất).
        //3.2, Complexity:
        //- Time complexity : O(n)
        //- Space complexity : O(k)
        System.out.println(lengthOfLongestSubstringKDistinctMethod2(s, k));
        //
        System.out.println(lengthOfLongestSubstringKDistinctMethod3(s, k));
        //#Reference
        //341. Flatten Nested List Iterator
        //159. Longest Substring with At Most Two Distinct Characters
        //992. Subarrays with K Different Integers
    }
}
