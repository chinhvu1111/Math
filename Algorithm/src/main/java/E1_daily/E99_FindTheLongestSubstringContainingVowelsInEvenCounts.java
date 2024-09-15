package E1_daily;

import java.util.Arrays;

public class E99_FindTheLongestSubstringContainingVowelsInEvenCounts {

    public static int findTheLongestSubstring(String s) {
        int[] mappingVal =new int[26];
        mappingVal[0] = 1;
        mappingVal['e'-'a'] = 1<<1;
        mappingVal['i'-'a'] = 1<<2;
        mappingVal['o'-'a'] = 1<<3;
        mappingVal['u'-'a'] = 1<<4;
        int n = s.length();
        int value = 0;
        int[] mapValues =new int[1<<4+1];
        Arrays.fill(mapValues, -2);
        //Out of the boundary of the array:
        //a,a
        //(index=1) --> map to (index=-1)
        //- current rs = index-previous_index (Không +1)
        //  + Previous index của (index=-1) = -1
        //  * Note:
        //      + Default value =-2 (Để khác đi)
        mapValues[0]=-1;
        int rs=0;

        for(int i=0;i<n;i++){
            //Current character is a vowel
            if(mappingVal[s.charAt(i)-'a']!=0){
                value = value^mappingVal[s.charAt(i)-'a'];
            }
            if(mapValues[value]!=-2){
                rs=Math.max(rs, i-mapValues[value]);
//                System.out.printf("Index: %s, value: %s, rs: %s\n", i, value, rs);
            }
            if(mapValues[value]==-2){
                mapValues[value]=i;
            }
        }
        return rs;
    }

    public static int findTheLongestSubstringRefer(String s) {
        int prefixXOR = 0;
        int[] characterMap = new int[26];
        characterMap[0] = 1;
        characterMap['e' - 'a'] = 2;
        characterMap['i' - 'a'] = 4;
        characterMap['o' - 'a'] = 8;
        characterMap['u' - 'a'] = 16;
        int[] mp = new int[32];
        for (int i = 0; i < 32; i++) mp[i] = -1;
        int longestSubstring = 0;
        for (int i = 0; i < s.length(); i++) {
            prefixXOR ^= characterMap[s.charAt(i) - 'a'];
            if (mp[prefixXOR] == -1 && prefixXOR != 0) mp[prefixXOR] = i;
            longestSubstring = Math.max(longestSubstring, i - mp[prefixXOR]);
        }
        return longestSubstring;
    }

    public static void main(String[] args) {
        //** Requirement
        //- Given the string s,
        //* return (the size of the longest substring) containing (each vowel) an (even) number of times.
        //That is, 'a', 'e', 'i', 'o', and 'u' must appear an even number of times.
        //
        //Example 1:
        //Input: s = "eleetminicoworoep"
        //Output: 13
        //Explanation: The longest substring is "leetminicowor" which contains two each of the vowels: e, i and o and zero of the vowels: a and u.
        //- Tức là (số lượng chars) của mỗi vowel char = "even"
        //
        // Idea
        //1.
        //1.0,
        // Method-1:
        //- Constraint
        //1 <= s.length <= 5 x 10^5
        //s contains only lowercase English letters.
        //  + Length khá lớn ==> Time: O(n) or O(n*k)
        //
        //- Brainstorm
        // 'a', 'e', 'i', 'o', 'u'
        //- Làm "Prefix sum" được không.
        //- Rule:
        //  + even number - even number = even number
        //  + odd number - odd number = even number
        //  + odd number - even number = "odd" number
        //  + even number - odd number = "odd" number
        //- Do cần (all chars) có count is ("even" number)
        //  + Nên (current index) ==> Cần gắn với ("same") (previous index)
        //
        //- Even ==> Có vẻ khá giống tính chất của (bit):
        //  + Có lý hơn rồi đấy:
        //      + Dùng hash + bit
        //- Mapping:
        //  + a => 0
        //  + e => 'e'-'a'
        //  + i => 'i'-'a'
        //  + o => 'o'-'a'
        //  + u => 'u'-'a'
        //  ==> SAI
        //  + Vì các value có thể đè bit nhau
        //Ex:
        //  e : 2 <=> 10
        //  c: 3 <=> 11
        //  ==> Khi XOR có thể ảnh hưởng lẫn nhau
        //- Ta cần (mapping) riêng giữa các (vowels):
        //  + e: bit 1st
        //  + i: bit 2nd
        //  + o: bit 3rd
        //  + u: bit 4th
        //  ==> 1111
        //
        //- a xor a = 0
        //  => xor all
        //- a có bit thứ (i)
        //- Nếu chẵn số a:
        //  + bit thứ (i) nếu xor all "even" a value = 0
        //- a xor b = c
        //  + a = c xor b
        //  + Dùng prefix sum
        //- Bài toán con:
        //  + Khoảng cách lớn nhất giữa 2 prefix xor giống nhau trong array:
        //      + Chúng (XOR với nhau == 0):
        //          + b xor a xor a xor a xor a = c
        //              + c == b
        //              ==> a số chẵn giữa b và c
        //- 0,1,2,3
        //  + MappingVal[3]=2
        //  + MappingVal[0]=2
        //  ==> rs = 3-0 = 3
        //  * Note:
        //      + Riêng XOR operation:
        //          + Rs không + 1
        //
//        String s = "eleetminicoworoep";
        String s = "leetcodeisgreat";
        //
        //1.1, Optimization
        //1.2, Complexity
        //- Space: O(constant)
        //- Time: O(n)
        //
        //#Reference:
        //1154. Day of the Year
        //2559. Count Vowel Strings in Ranges
        //2681. Power of Heroes
        //
        System.out.println(findTheLongestSubstring(s));
        System.out.println(findTheLongestSubstringRefer(s));
    }
}
