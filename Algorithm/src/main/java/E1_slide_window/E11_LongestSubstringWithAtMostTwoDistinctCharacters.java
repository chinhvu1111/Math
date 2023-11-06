package E1_slide_window;

import java.util.*;

public class E11_LongestSubstringWithAtMostTwoDistinctCharacters {

    public static int lengthOfLongestSubstringTwoDistinct(String s) {
        int[] rightMost=new int[82];
        Arrays.fill(rightMost, -1);
        int n=s.length();
        int countDistinct=0;
        int rs=0;
        int low=0, i;

        for(i=0;i<n;i++){
            int c=s.charAt(i)-'A';

            if(rightMost[c]==-1){
                if(countDistinct==2){
                    rs=Math.max(rs, i-low);
//                    System.out.printf("low: %s, high: %s\n", low, i);
//                    System.out.printf("low: %s, right most: %s\n", low, rightMost[s.charAt(low)-'a']);
                    int removeChar=-1;
                    int minIndex=Integer.MAX_VALUE;

                    for(int h=0;h<82;h++){
                        if(rightMost[h]!=-1&&rightMost[h]<minIndex){
                            minIndex=rightMost[h];
                            removeChar=h;
                        }
                    }
                    low=rightMost[removeChar]+1;
                    // System.out.printf("low: %s, right most: %s\n", low, rightMost[removeChar]);
                    rightMost[removeChar]=-1;
                }else{
                    rs=Math.max(rs, i-low+1);
                    countDistinct++;
                }
                rightMost[c]=i;
            }else{
                //Ex
                //aabab
                rs=Math.max(rs, i-low+1);
                rightMost[c]=i;
            }
        }
        return rs;
    }

    public static int lengthOfLongestSubstringTwoDistinctOptimization(String s) {
        int n=s.length();
        if(n==0){
            return 0;
        }
        Map<Character, Integer> rightMost=new HashMap<>();
        int left=0, right=0;
        int k=2;
        int rs=0;

        while(right<n){
            rightMost.put(s.charAt(right), right++);

            if(rightMost.size()==k+1){
                int lowestIndex= Collections.min(rightMost.values());
                rightMost.remove(s.charAt(lowestIndex));
                left=lowestIndex+1;
            }
            rs=Math.max(rs, right-left);
        }
        return rs;
    }

    public static int lengthOfLongestSubstringTwoDistinctOptimization1(String s) {
        int n=s.length();
        if(n==0){
            return 0;
        }
        Map<Character, Integer> rightMost=new LinkedHashMap<>();
        int left=0, right=0;
        int k=2;
        int rs=0;

        while(right<n){
            rightMost.remove(s.charAt(right));
            rightMost.put(s.charAt(right), right++);

            if(rightMost.size()==k+1){
                Map.Entry<Character, Integer> leftMostElement=rightMost.entrySet().iterator().next();
                left=leftMostElement.getValue()+1;
                rightMost.remove(leftMostElement.getKey());
            }
            rs=Math.max(rs, right-left);
        }
        return rs;
    }

    public static void main(String[] args) {
        //** Requirement
        //* Return string longest mà có nhiều nhất 2 character
        //
        //** Idea
        //1.
        //1.0,
        //- Constraint
        //1 <= s.length <= 10^5
        //s consists of English letters.
        //==> 26 characters : hashmap cũng không lớn lắm
        //
        //- Brainstorm
        //Ex:
        //Input: s = "eceba"
        //Output: 3
        //Explanation: The substring is "ece" which its length is 3.
        //
        //- Bài này sẽ dùng slide window để xử lý
        //Ex:
        //s= ccaacbbb
        //+ Khi ta traverse đến vị trị character=b ==> ta thấy rằng count(distinct character)=2
        //  + Ta cần remove 1 character bên right most ra
        //  Nhưng vấn đề là ta sẽ remove 'a' thay vì 'c' vì a xuất hiện right most là index=3 (Là lớn nhất)
        //  ==> ta sẽ lấy index của right most mà character đó xuất hiện
        //  + Nhưng the character đó phải là character xuất hiện min(right most index) trong all character.
        //
        //s= ccaacbbb
        //right_most[c]=4
        //right_most[a]=3
        //+ count_distinct==2:
        //  + Để count distinct==2:
        //      + Nếu rightMost[newNode]==-1: Tức là ta gặp new node
        //      --> Ta sẽ tính giá trị của các node đi trước
        //
        //
//        String s= "ccaacbbb";
//        String s= "ccaabbb";
//        String s= "eceba";
//        String s= "e";
        String s= "abaccc";
        //- Ta lại gặp lại lỗi cũ liên quan đến slide window
        //  + Khi ở 'c' ta có thể remove (a or b)
        //  + Ở đây mình sẽ remove 'ab' ==> Tức là vẫn còn 'a' (Chỉ remove b)
        //==>Ta sẽ remove character xuất hiện right most min nhất (Lần cuối)
        //
        //- Ở đây cùng lắm mình for all (characters) để mình tìm ra (MIN right most) của all characters
        //** Chú ý:
        //- Ở đây không quy định "lower case" ==> Bao gồm cả upper case
        //  + rightMost[82]
        //
        //1.1, Optimization
        //- Bài này ta có 3 cách:
        //+ Là low, high + loop all (lower case or upper case characters để có thể lấy min right_most_index)
        //+ Là dùng hashmap ==> Update right most index của mỗi character ==> Sau đó ta cũng tìm min values[index] của all chacracters
        //- Tối ưu hơn 1 chút:
        //- Ta sẽ dùng tính chất order của linkedList và remove character (fast) của hashmap:
        //+ Ta sẽ only keep thể right most index of each character
        //+ Và ta cũng cần keep thứ tự vì nếu:
        //  + keep order element ta có thể map ra phần tử nào có min (right most index)
        //* Tính chất:
        //  + Vì element exist in (ListHashMap) <=> Nó sẽ có right most index
        //  + Element order càng sang left ==> Update càng lâu về trước ==> Right most Index càng nhỏ [ Đây là cái ta cần ]
        //  ==> Ta sẽ remove from left to right
        //
        //1.2, Complexity
        //+ N is the length of s
        //- Space : O(82)
        //- Time : O(n)
        //
        System.out.println(lengthOfLongestSubstringTwoDistinct(s));
        System.out.println(lengthOfLongestSubstringTwoDistinctOptimization(s));
    }
}
