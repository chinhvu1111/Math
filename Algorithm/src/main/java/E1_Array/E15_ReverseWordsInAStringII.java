package E1_Array;

public class E15_ReverseWordsInAStringII {

    public static void reverseWords(char[] s) {
        int n=s.length;
        int low=0, high=0;

        //1 --> O(m)
        //2 --> O(h)
        //1 + 2 +...=n
        //m+h+...=n
        //- Time = O(2*n) = O(n)
        //==> Time : O(n)
        while(high<=n){
            if(high==n||s[high]==' '){
                //Time:
                //+ Total = O(n)
                reverse(s, low, high-1);
                low=high+1;
            }
            high++;
        }
        reverse(s, 0, n-1);
    }

    public static void reverse(char[] s, int low, int high){
        while(low<high){
            char temp=s[low];
            s[low]=s[high];
            s[high]=temp;
            low++;
            high--;
        }
    }

    public static void main(String[] args) {
        //** Requirement
        //-
        //
        //** Idea
        //1.
        //1.0,
        //- Constraint
        //1 <= s.length <= 10^5
        //+ s[i] is an English letter (uppercase or lowercase), digit, or space ' '.
        //+ There is (at least one word in s).
        //+ s does (not contain leading or trailing spaces).
        //+ All the words in s are guaranteed to be (separated by a single space).
        //- n<=10^5 ==> Ta chỉ có thể xử lý được O(n)/ O(n*log(n))
        //
        //- Brainstorm
        //- Ta không được dùng extra space
        //Ex:
        //s = ['t','h','e',' ','s','k','y',' ','i','s',' ','b','l','u','e']
        //s = [ word1,' ', word2,' ', word3]
        //==> s_reverse = [ word3,' ', word2,' ', word1]
        //- Ta thử tìm cách swap các characters tương ứng:
        //+ step 1: s = ['t','h','e',' ','s','k','y',' ','i','s',' ','b','l','u','e']
        //+ Vì là swap word ==> Nó sẽ ăn theo prefix of word
        //+ step 2: s = ['t','h','e',' ','s','k','y',' ','i','s',' ','b','l','u','e']
        //==> Nếu làm kiểu này thì ta không ăn theo prefix được
        //- Liệu có cách nào khác để xử lý bài này không?
        //s = ['t','h','e',' ','s','k','y',' ','i','s',' ','b','l','u','e']
        //==>
        //s= ['b','l','u','e',' ','i','s',' ','s','k','y',' ','t','h','e']
        //+ Liệu rotate thì ta có kết quả không?
        //s = [ word1,' ', word2,' ', word3, ' ', word4]
        //=>
        //s = [ word2,' ', word3, ' ', word4, ' ', word1]
        //=>
        //s = [ word3, ' ', word4, ' ', word1,' ', word2]
        //Expected:
        //s = [ word4, ' ', word3, ' ', word2,' ', word1]
        //
        //- n is the odd number
        //a,b,c
        //b,c,a
        //c,a,b
        //Expected rs:
        //c,b,a
        //- n is the even number
        //a,b,c,d
        //b,c,d,a
        //c,d,a,b
        //d,a,b,c
        //Expected rs:
        //d,c,b,a
        //
        //- How to swap the simple example?
        //s=['i','s',' ','s','k','y']
        //=> swap partially:
        //** Mục đích reverse 1 phầ:
        //+ Để ta có thể swap 1 cách liền mạch (consecutively) từ trong ra ngoài:
        //+ Bài toán prefix liên quan đến việc swap bị thiếu pos nếu len(word1) != len(word2) ==> Sẽ khiến cho việc đặt sẽ bị
        //lệch space.
        //
        //s=['s','i',' ','s','k','y']
        //- swap từ trong ra
        //+ s <=> ' '
        //+ k <=> i
        //+ y <=> s
        //==> s=['y','k','s',' ','i','s']
        //==> reverse partially in second time in the first word
        //==> s=['s','k','y',' ','i','s']
        //+ Expected s_reverse
        //= ['s','k','y',' ','i','s']
        //
        //- Big example
        //Ex:
        //s = ['t','h','e',' ','s','k','y',' ','i','s',' ','b','l','u','e']
        //+ swap 1:
        //'s','k','y',' ','i','s'
        //==>
        //'i','s',' ','s','k','y'
        //s = ['t','h','e',' ',('i','s',' ','s','k','y'),' ','b','l','u','e']
        //+ swap partially
        //s = ['e','h','t',' ',('i','s',' ','s','k','y'),' ','b','l','u','e']
        //+ Ta thấy ở đây rằng ta không thể swap 2 arrays ở rìa ngoài mà không ==> dịch cái array mà ta đã swap ở giữa
        //==> Tư duy trên gặp vấn đề tương tự rồi
        //
        //- Gặp vấn đề liên quan đến space khi khác length giữa các arrays cần swap ==> Ta cần tư duy để xử lý vấn đề này
        //s = ['t','h','e',' ','s','k','y',' ','i','s',' ','b','l','u','e']
        //- Ta thấy rằng nếu swap 2 array ngay cạnh từ first to last
        //  + Sẽ không ảnh hướng đến length đằng sau
        //
        //s = ['t','h','e',' ','s','k','y',' ','i','s',' ','b','l','u','e']
        //==> reverse each word
        //s = ['e','h','t',' ','y','k','s',' ','s','i',' ','e','u','l','b']
        //==> swap 2 words
        //s = ['b','l','u',e,'y','k','s',' ','s','i',' ',' ','t','h','e']
        //+ Ta thấy khi swap thì ta bị swap 2 space sang
        //+ continue swap
        //s = ['b','l','u',e,' ','i','s',' ','s','k','y',' ','t','h','e']
        //==> Ta thấy rằng ta sẽ bù space sau ==> space(' ') ta cũng swap với character để:
        //  + Bù lại phần ký tự space mà ta đã remove đi lúc trước
        //
        //- Idea:
        //+ Quy về việc swap smooth ==> bằng cách reverse each word
        //+ Sau đó swap đến chết ==> Ngay cả space.
        //
        //1.1, Optimization
        //
        //1.2, Complexity:
        //- Space : O(1)
        //- Time :
        //+ 1 --> O(m)
        //+ 2 --> O(h)
        //+ 1 + 2 +...=n
        //+ m+h+...=n
        //- Time = O(2*n) = O(n)
        //==> Time : O(n)
        //
        //#Reference:
        //189. Rotate Array
//        char[] s = {'t','h','e',' ','s','k','y',' ','i','s',' ','b','l','u','e'};
        char[] s = {'t','h','e'};
        reverseWords(s);
        for(char c: s){
            System.out.printf("%s,", c);
        }
        Integer a=1;
    }
}
