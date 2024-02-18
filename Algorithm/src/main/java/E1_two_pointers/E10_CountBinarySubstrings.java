package E1_two_pointers;

public class E10_CountBinarySubstrings {

    public static int countBinarySubstrings(String s) {
        if(s.length()<=1){
            return 0;
        }
        int right=0;
        char initChar=s.charAt(0);
        int n=s.length();

        while(right<s.length()&&s.charAt(right)!=initChar){
            right++;
        }
        int rs=0;

        while(right<n){
            int left=right-1;
//            System.out.printf("Left: %s, Right: %s\n", left, right);
            char charRight = s.charAt(right);

            //1(0011111)0
            //(0000011)0
            //1(0011)0 : Cái này cần đánh dấu change
            while(left>=0&&right<n&&s.charAt(right)==charRight&&Math.abs(s.charAt(left)-s.charAt(right))==1){
                left--;
                right++;
                rs++;
            }
            while (right<n&&s.charAt(right)==charRight){
                right++;
            }
        }
        return rs;
    }

    public static int countBinarySubstringsGroup(String s) {
        if(s.length()<=1){
            return 0;
        }
        int n=s.length();
        int[] group=new int[n];
        group[0]=1;
        int t=0;

        for(int i=1;i<n;i++){
            if(s.charAt(i)!=s.charAt(i-1)){
                group[++t]=1;
            }else{
                group[t]++;
            }
        }
        int rs=0;

        for(int i=1;i<=t;i++){
            rs+=Math.min(group[i], group[i-1]);
        }
        return rs;
    }

    public static void main(String[] args) {
        // Requirement
        //- Given a binary string s,
        //* return the number of non-empty substrings that have (the same number of 0's and 1's),
        // and all the 0's and all the 1's in these substrings (are grouped consecutively).
        //Ex:
        //Input: s = "00110011"
        //Output: 6
        //Explanation: There are 6 substrings that have equal number of consecutive 1's and 0's:
        // "0011",
        // "01",
        // "1100",
        // "10",
        // "0011",
        // "01"
        //Notice that some of these substrings repeat and are counted the number of times they occur.
        //Also, "00110011" is not a valid substring because all the 0's (and 1's) are not grouped together.
        //
        // Idea
        //1. Two pointers
        //1.0,
        //- Constraint:
        //1 <= s.length <= 10^5
        //s[i] is either '0' or '1'.
        //--> Time : O(n)/ O(n*log(n))
        //
        //- Brainstorm
        //Ex:
        //000111
        //01
        //0011
        //000111
        //rs=3
        //- Bài này ta có thể xử lý đôi một được.
        //+ initChar=s[0]
        //+ index=1
        //  + While(initChar!=s[i]) i++
        //- 0001(initChar, index)11
        //==> Ta sẽ xét theo 2 phía là được.
        //
        //1.1, Optimization
        //-
        //1.2, Complexity
        //- N is the length of the string
        //- Space: O(1)
        //- Time : O(N*2)= O(N)
        //
        //2. Idea chia thành các group với size khác nhau
        //We can convert the string s into an array groups that represents the length of same-character contiguous blocks within the string.
        // For example, if s = "110001111000000", then groups = [2, 3, 4, 6] (size=2,3,4,6).
        //- Lúc đó kết quả sẽ là sum của min(group[i-1], group[i])
        //- 2 group chỉ có thể là:
        //+ group[0...0], group[1...1]
        //+ group[1...1], group[0...0]
        //==> Lấy min kiểu gì cũng đúng, miễn là:
        //+ Mỗi group sẽ bao gồm các giá trị = size()
        //+ 2 group liền kề là 2 group với char khác nhau (0!=1)/ (1!=0)
        //
        //2.1, Optimization
        //- Ta có thể reduce O(T) -> O(1) : Không cần lưu all group
        //2.2, Complexity
        //- N is the length of the string
        //- Space : O(1)
        //- Time : O(N)
        //
//        String s="000111";
//        String s="00110011";
        String s="10101";
        System.out.println(countBinarySubstrings(s));
        System.out.println(countBinarySubstringsGroup(s));
        //#Reference:
        //2489. Number of Substrings With Fixed Ratio
    }
}
