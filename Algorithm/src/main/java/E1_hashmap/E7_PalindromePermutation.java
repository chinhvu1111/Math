package E1_hashmap;

public class E7_PalindromePermutation {

    public static boolean canPermutePalindrome(String s) {
        int[] countChar=new int[26];
        int n=s.length();
        int countExistsOdd=0;

        for(int i=0;i<n;i++){
            countChar[s.charAt(i)-'a']++;
        }
        for(int i=0;i<26;i++){
            if(countChar[i]==0){
                continue;
            }
            if(countChar[i]%2==1&&n%2==0){ // n chẵn + có 1 phẩn từ count[char] lẻ --> return false
                return false;
            }else if(countChar[i]>=1&&countChar[i]%2==1&&n%2==1){ // n lẻ + nhiều exists count character lẻ >1 --> return false
                countExistsOdd++;
                if(countExistsOdd>1){
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        //** Requirement
        //- Given a string s,
        //* return true if a (permutation) of the string could (form a palindrome)
        // and false otherwise.
        //
        //** Idea
        //1.
        //1.0,
        //- Constraint
        //1 <= s.length <= 5000
        //s consists of only lowercase English letters.
        //==> Lowcase --> [26]
        //
        //- Brainstorm
        //- Nếu chỗi chẵn:
        //+ Mỗi ký tự xuất hiện 2 lần
        //- Nếu chuỗi lẻ:
        //+ Mỗi ký tự xuất hiện 2 lần và 1 ký tự xuất hiện 1 lần
        //
        //- Ta check xem số lần xuất hiện của các ký tự:
        //+ Nếu >2 ==> return false
        //+ Nếu ==1 : 2 lần ==> return false
        //+ ==2 và (==1) ==> Tuỳ thuộc vào length ===> SAI
        //  + odd
        //  + even
        //** Tư duy trên ==1 mà return False là "SAI" vì:
        //+ Count nó cộng liên tục nên về sau nó có thể change từ 1 --> n
        //==> Quyết định luôn là "SAI".
        //
        //- Special cases:
        //+ Tất cả character=1 hết
        //Ex: aaaaa
        //Ex:
        //aabaa
//        String s="aab";
        //
        //- Idea
        //Chia ra 2 case return false như sau:
        //+ n chẵn + có 1 phẩn từ count[char] lẻ --> return false
        //+ n lẻ + nhiều exists count character lẻ >1 --> return false
        //
        //1.1, Optimization
        //- Exp : Những bài mà ta check kết quả count chung cuộc ==> Thì cần tình count hashmap xong đã
        //  + Vì nếu xét cùng lúc traverse --> kết quả nó sẽ thay đổi ==> Ta sẽ tính sai.
        //
        //1.2, Complexity
        //- Space : O(constant)
        //- Time : O(n)
        String s="abc";
        System.out.println(canPermutePalindrome(s));
        //#Reference:
        //267. Palindrome Permutation II
        //409. Longest Palindrome
    }
}
