package E1_String;

public class E9_MinimumSwapsToMakeStringsEqual {

    public static int minimumSwap(String s1, String s2) {
        int n=s1.length();
        int m=s2.length();

        if(n!=m){
            return -1;
        }
        int xmis=0;
        int ymis=0;

        //+ xmis:
        //x
        //y
        //+ ymis:
        //y
        //x
        //
        //- Ta có biểu thức như sau:
        //xx
        //yy
        //- return 1
        //
        //yy
        //xx
        //- return 1
        //
        //- Vì các cặp xmis và ymis ta sẽ ưu tiên ghép với nhau vì mỗi cặp chỉ mất 1 swap operation
        //+ rs= xmis/2 + ymis/2
        //- Nên còn lại là các cặp phải mất 2 swap operation:
        //- Dư ra 1
        //+ rs+=2 (nếu xmis%2==1)
        //yx
        //xy
        //or
        //xy
        //yx
        //
        for(int i=0;i<n;i++){
            if(s1.charAt(i)=='x'&&s2.charAt(i)=='y'){
                xmis++;
            }else if(s1.charAt(i)=='y'&&s2.charAt(i)=='x'){
                ymis++;
            }
        }
        if(xmis%2!=ymis%2){
            return -1;
        }
        int rs=xmis/2 + ymis/2;
        if(xmis%2==1){
            rs+=2;
        }
        return rs;
    }

    public static void main(String[] args) {
        //* Requirement
        //- We can execute swap operation:
        //+ Swap word1[i] by word2[j]
        //* Return the minimum the number of swaps required to make s1 equal to s2.
        //
        //** Idea
        //1.
        //1.0, Idea
        //- Constraint
        //1 <= s1.length, s2.length <= 1000
        //s1.length == s2.length
        //s1, s2 only contain 'x' or 'y'.
        //
        //- Brainstorm
        //- Để swap 2 string giống nhau --> Ta cần swap sao cho count của 2 string giống nhau trước
        //Ex:
        //s1 = "xy"
        //s2 = "yx"
        //-> x <-> y
        //s1 = "yy"
        //s2 = "xx"
        //-> y <-> x
        //s1 = "xy"
        //s2 = "xy"
        //- return 2
        //
        //s1=abc
        //s2=def
        //->
        //s1=fbc
        //s2=dea
        //->
        //s1=dbc
        //s2=fea
        //
        //- Ta có thể swap như sau:
        //s1=abc
        //s2=def
        //d <-> f
        //->
        //s1=afc
        //s2=deb
        //->
        //s1=adc
        //s2=feb
        //->
        //s1=abc
        //s2=fed
        //- return 3 steps
        //
        //Ex:
        //s=abcccd
        //t=decced
        //->
        //s=abc
        //t=dee
        //- Có trường hợp nào mà ta replace cả những character giống nhau hay không?
        //Ex:
        //s=accd
        //t=acbc
        //->
        //s=accc
        //t=acbd
        //->
        //s=accb
        //t=accd
        //
        //+ Làm sao để biết biểu thức dưới này sẽ hết bao nhiêu step?
        //Ex:
        //s1 = "xy"
        //s2 = "yx"
        //
        //
        //* NOTE:
        //- Cần nhớ rõ đề bài --> Bài này chỉ gồm 2 character x và y thôi
        //
        //- Idea
        //+ xmis:
        //x
        //y
        //+ ymis:
        //y
        //x
        //
        //- Ta có biểu thức như sau:
        //xx
        //yy
        //- return 1
        //
        //yy
        //xx
        //- return 1
        //
        //- Vì các cặp xmis và ymis ta sẽ ưu tiên ghép với nhau vì mỗi cặp chỉ mất 1 swap operation
        //+ rs= xmis/2 + ymis/2
        //- Nên còn lại là các cặp phải mất 2 swap operation:
        //- Dư ra 1
        //+ rs+=2 (nếu xmis%2==1)
        //yx
        //xy
        //or
        //xy
        //yx
        //
        //1.1, Optimization
        //1.2, Complexity
        //- Space : O(1)
        //- Time : O(n)
        //
        //Ex:
        //s=xxyyyxx
        //t=yxyyyxx
        //- Số lượng chữ số x và y phải chẵn
        //+ 1 case nhỏ
        //s1 = "xy"
        //s2 = "yx"
        //-> x <-> y
        //s1 = "yy"
        //s2 = "xx"
        //-> y <-> x
        //s1 = "xy"
        //s2 = "xy"
        //- return 2
        //
        //s1 = "xx"
        //s2 = "yy"
        //->
        //s1 = "xy"
        //s2 = "xy"
        //-> return 1
        //
        //s1 = "yx"
        //s2 = "xy"
        //-> return 2 ==> vì x,y đối xứng
        //
        //s1 = "xx"
        //s2 = "yx"
        //-> return -1
        //
        //Ex:
        //s=xxyyyxx
        //t=yxyyyxx
        //->
        //Ex:
        //s=xxyyyxy
        //t=xxyyyxx
        //
        //- Tóm lại ta có các cases có thể như sau
        //xx
        //yy
        //-> return 1
        //
        //xy
        //yx
        //-> return 2
        //
        //#Reference:
        //2194. Cells in a Range on an Excel Sheet
        //484. Find Permutation
        //758. Bold Words in String
        //172. Factorial Trailing Zeroes
        //1520. Maximum Number of Non-Overlapping Substrings
        //2381. Shifting Letters II
        System.out.println(minimumSwap("xy", "yx"));
    }
}
