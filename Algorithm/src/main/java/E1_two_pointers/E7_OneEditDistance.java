package E1_two_pointers;

public class E7_OneEditDistance {

    public static boolean isOneEditDistance(String s, String t) {
        int n=s.length();
        int m=t.length();

        if(Math.abs(n-m)>=2){
            return false;
        }
        int countDiff=0;

        if(n==m){
            //Time : O(n)
            for(int i=0;i<n;i++){
                if(s.charAt(i)!=t.charAt(i)){
                    countDiff++;
                }
            }
            return countDiff==1;
        }

        if(n>m){
            String temp=s;
            s=t;
            t=temp;

            int tmp=n;
            n=m;
            m=tmp;
        }
        int i=0,j=0;

        //Time : O(n)
        while(i<n){
            if(j<m&&s.charAt(i)!=t.charAt(j)){
                countDiff++;
                if(countDiff>1){
                    return false;
                }
                j++;
            }else if(j<m&&s.charAt(i)==t.charAt(j)){
                i++;
                j++;
            }else{
                i++;
                countDiff++;
                if(countDiff>1){
                    return false;
                }
            }
        }
        //Có 2 trường hợp:
        //- countDiff==1 : Tức là delete or insert được 1 character
        //- Vì n chỉ có thể hơn kém nhau 1 đơn vị |n-m|==1 + nếu countDiff>1 --> return false (Ngay bên trên rồi)
        //==> chỉ có thể (countDiff==0) (Tức là không khác nhau) mà length(s) và length(t) hơn kém nhau 1
        //==> Chỉ có thể là case ("") và ("x")
        return countDiff == 1 || Math.abs(n - m) == 1;
    }

    public static void main(String[] args) {
        //** Requirement
        //- Given s and t
        //* return true:
        //- Nếu s có thể biến thành t bằng cách:
        //+ Insert chính xác 1 character
        //+ Delete chính xác 1 character
        //+ Replace chính xác 1 character
        //
        //** Idea
        //1.
        //1.0,
        //- Constraint
        //0 <= s.length, t.length <= 104
        //s and t consist of lowercase letters, uppercase letters, and digits.
        //
        //- Brainstorm
        //E.g:
        //s = "ab", t = "acb"
        //+ Delete
        //  + Nếu 2 string khác nhau ==1 ký tự : return true
        //+ Insert
        //  + Length(t) - Length(s) = 1
        //  + Insert khác gì delete ==> Insert vào s và delete cũng vào s
        //+ Replace
        //  + Length(s) = Length(t)
        //  + Chúng khác nhau cũng chỉ 1 character
        //E.g:
        //String s = "ca", t = "bc";
        //s= ca
        //t= bc
        //- Không thể xét như bên trên là làm theo kiểu xét lần lượt được.
        //+ Vì khi c!=b ta có 2 lựa chọn (i++ or j++)
        //+ Nếu length của s và t như nhau:
        //==> s[i]!=t[j] ==> i++;j++ vì nếu cộng 1 trong 2 thì kiểu gì cũng bị (j-i>=2) (Luôn false)
        //
        //- Tìm số lượng ký tự khác nhau như thế nào:
        //+ Ký tự ngắn hơn sẽ làm mốc:
        //E.g:
        //s = "ab", t = "acb"
        //i=0, j=0
        //+ a==a: i++; j++
        //+ b!=c: j++ (Vì length(t) > length(s))
        //
//        String s = "ab", t = "acb";
//        String s = "", t = "a";
        //ca
        //bc
        //+ i=0, j=0 : c!=b
//        String s = "ca", t = "bc";
        //- Special cases:
        //Có 2 trường hợp:
        //- countDiff==1 : Tức là delete or insert được 1 character
        //- Vì n chỉ có thể hơn kém nhau 1 đơn vị |n-m|==1 + nếu countDiff>1 --> return false (Ngay bên trên rồi)
        //==> chỉ có thể (countDiff==0) (Tức là không khác nhau) mà length(s) và length(t) hơn kém nhau 1
        //==> Chỉ có thể là case ("") và ("x")
        //
        //1.1, Optimization
        //- Thêm đoạn check countDiff vào bên trong khi (i++) và (j đã >=m ) ==> Lúc đó sẽ là số lượng characters khác nhau.
        //
        //1.2, Complexity:
        //- Time : O(n)
        //- Space : O(1)
        //
        String s = "a", t = "b";
        System.out.println(isOneEditDistance(s, t));
        //#Reference:
        //1781. Sum of Beauty of All Substrings
        //1880. Check if Word Equals Summation of Two Words
        //948. Bag of Tokens
        //758. Bold Words in String
        //544. Output Contest Matches
        //2262. Total Appeal of A String
    }
}
