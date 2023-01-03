package interviews;

import java.util.Arrays;

public class E56_PermutationInString {

    public static boolean checkInclusion(String s1, String s2) {
        if(s1.length()>s2.length()){
            return false;
        }

        int[] left =new int[26];
        int[] right =new int[26];

        for(int i=0;i<s1.length();i++){
            left[s1.charAt(i)-'a']++;
            right[s2.charAt(i)-'a']++;
        }
        int start=0;
        int end=s1.length()-1;

        if(Arrays.equals(left, right)){
            return true;
        }
        while (end+1<s2.length()){
            right[s2.charAt(start)-'a']--;
            right[s2.charAt(end+1)-'a']++;

            if(Arrays.equals(left, right)){
                return true;
            }
            start++;
            end++;
        }
        return false;
    }

    public static boolean checkInclusionHash(String s1, String s2) {
        if(s1.length()>s2.length()){
            return false;
        }

        int n=s1.length();
        int m=s2.length();

        int hashS1=0, hashS2=0;

        //Time : O(N)
        for(int i=0;i<n;i++){
            hashS1+= getHash(s1.charAt(i));
            hashS2+= getHash(s2.charAt(i));
        }
        //Time : O(N-M)
        for(int i=n;i<m;i++){
            if(hashS1==hashS2&&checkPermutation (s2.substring(i-n, i), s1)){
                return true;
            }
//            System.out.printf("%s %s\n", hashS1, hashS2);
            hashS2-=getHash(s2.charAt(i-n));
            hashS2+=getHash(s2.charAt(i));
        }
        return hashS1 == hashS2 && checkPermutation(s2.substring(m - n, m), s1);
    }

    public static int getHash(char c){
        return  1<<(c-'a');
    }

    public static boolean checkPermutation(String s1, String s2){
        int[] count1=new int[26];
        int[] count2=new int[26];

        for(int i=0;i<s1.length();i++){
            count1[s1.charAt(i)-'a']++;
            count2[s2.charAt(i)-'a']++;
        }
        for(int i=0;i<26;i++){
            if(count1[i]!=count2[i]){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
//        String s1="ab";
//        String s2="eidbaooo";
        String s1="ab";
        String s2="eidbaooo";

        System.out.println(checkInclusion(s1, s2));
        //
        //** Đề bài:
        //- Ta có 2 chuỗi s1 và s2
        //- return true nếu s2 bao gồm hoán vị(permutation) của s1
        //
        //Cách 1: Slide window
        //1,
        //1.1, Ta chỉ hướng đến việc tìm được 1 kết quả --> return true
        //VD:
        //Ta có
        //- s1 : length=n
        //- s2 : length=m
        //- m>n
        //- Test case 1:
        //s1=abcd
        //s2=eid(cdba)ooo
        //result : true
        //
        //- Test case 2:
        //s1=ab
        //s2=eidboaoo
        //result : true
        //1.2,
        //Ta tư duy như sau:
        //- Vì các chữ số đều có giới hạn từ 0--> 26
        //==> Ta có thể count số lần xuất hiện của s1 --> sau đó kiểm tra trong từng đoạn length=n trong s2
        //thì có đoạn nào xuất hiện như vậy không.
        //==> Nếu có return true luôn
        //- Các giá trị count của cả 2 lúc ban đầu sẽ tính theo length = n (Chiều dài của chuỗi nhỏ hơn)
        //- Với mỗi lần dịch sang phải --> ta sẽ trừ đi 1 count[i]-- và cộng lên count[i+n-1]++
        //==> Kết hợp với việc check giống nhau giữa 2 array left và right --> return ra kết quả.
        //VD:
        //s1: (bcd)
        //s2: h(cbd)hdc
        //
        //Cách 2:
        //2.
        //2.1,
        //- Thực hiện build 1 hash function mục đích để so sánh 1 cách chặt chẽ ==> giảm số lượng công việc đằng sau nó
        //+ Ví dụ ở đây là bỏ đi bước substring + so sánh string.
        //- Chọn hàm hash đủ cover chặt nhất các case mà condition(s1, s2)==true --> thì mới thực hiện
        //+ Ở đây nếu map (abc)--> (1,2,3) + sum(a,b,c)=7 ==> có thể có nhiều số tương tự
        //VD: 1+2+3 (abc)=2+2+2 (bbb) ===> Sẽ khiến việc so sánh bị tốn operations hơn.
        //+ Dùng hàm hash dạng : 7= 1 + 2 + 2^i +... ==> Nó là số thập phân nên có thể biểu diễn 1 cách duy nhất được.
        //VD: abc --> tính 1<< ('a'-'a')
        //+ Dùng dịch bit để tính số pow --> tăng speed
        //+ a: 2^0, b:2^1...
        //==> Lúc đó abc= 1+2+4=7 (Sẽ là duy nhất) --> Khá mạnh.
        //
        //2.2,
        //- Time complexity: O(M) , các hàm hash chỉ tính trong O(1) mà thôi
        //- Space complexity: O(1)
        //3.
        //#Reference:
        //- Bulls and Cows
        //- Minimum Area Rectangle
        System.out.println(checkInclusionHash(s1, s2));
    }
}
