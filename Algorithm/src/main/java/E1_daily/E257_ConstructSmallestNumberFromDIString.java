package E1_daily;

import java.util.Arrays;
import java.util.Stack;

public class E257_ConstructSmallestNumberFromDIString {

    public static String smallestNumber(String pattern) {
        int[] countExists=new int[10];
        int n=pattern.length();
        int max=0;
        int prev=1;
        Character[] rsChars=new Character[n+1];
        rsChars[0]='1';
        countExists[1]=1;

        //Time: O(n)
        for(int i=0;i<n;i++){
            char c = pattern.charAt(i);
            int nextVal = prev;

            if(c=='I'){
                while (countExists[nextVal]>=1){
                    nextVal++;
                }
            }else{
                if(prev>1&&countExists[prev-1]==0){
                    nextVal=prev-1;
                }else{
                    //"DD"
                    //D: 10->9
                    //DIDD
                    //5|D|4|I|6|D|3|D|2
                    //Exist:
                    //  ==> chỉ có thể tăng lên (Ưu tiên đằng trước MIN ==> Dùng hết r)
                    if(nextVal>1&&countExists[nextVal-1]==0){
                        nextVal--;
                    }else {
                        int j=i;
                        int newVal=nextVal;
                        //Time: O(n)
                        while(j>=0&&pattern.charAt(j)=='D'){
                            newVal++;
                            countExists[rsChars[j]-'0']--;
                            rsChars[j]=(char)(newVal+'0');
                            countExists[rsChars[j]-'0']++;
                            j--;
                        }
                    }
                }
            }
//            prevIndex=i;
            prev=nextVal;
            max=Math.max(max, nextVal);
            rsChars[i+1]=(char)(nextVal+'0');
            countExists[nextVal]++;
        }
        StringBuilder rs=new StringBuilder();
        for(char c: rsChars){
            rs.append(c);
        }
        return rs.toString();
    }

    public static String smallestNumberStack(String pattern) {
        int n=pattern.length();
        Stack<Integer> stack=new Stack<>();
        StringBuilder rs=new StringBuilder();

        for (int i = 0; i <= n; i++) {
            stack.add(i+1);
            if(i==n||pattern.charAt(i)=='I'){
                while(!stack.isEmpty()){
                    rs.append(stack.pop());
                }
            }
        }

        return rs.toString();
    }

    public static void main(String[] args) {
        //** Requirement
        //- You are given (a 0-indexed string pattern of length n) consisting of the characters ('I') meaning increasing and ('D') meaning decreasing.
        //- (A 0-indexed string num of length n + 1) is created using the following conditions:
        //  + num consists of the digits ('1' to '9'), where each digit is used (at most once).
        //  + If pattern[i] == 'I', then num[i] < num[i + 1].
        //  + If pattern[i] == 'D', then num[i] > num[i + 1].
        //* Return (the lexicographically (smallest possible) string num) that meets the conditions.
        //
        //Example 1:
        //
        //Input: pattern = "IIIDIDDD"
        //Output: "123549876"
        //Explanation:
        //At indices 0, 1, 2, and 4 we must have that num[i] < num[i+1].
        //At indices 3, 5, 6, and 7 we must have that num[i] > num[i+1].
        //Some possible values of num are "245639871", "135749862", and "123849765".
        //It can be proven that "123549876" is the smallest possible num that meets the conditions.
        //Note that "123414321" is not possible because the digit '1' is used more than once.
        //
        //** Idea
        //1.
        //1.0,
        //- Method-1:
        //+ Constraints:
        //1 <= pattern.length <= 8
        //pattern consists of only the letters 'I' and 'D'.
        //
        //* Brainstorm:
        //- (smallest possible) string num
        //  + The first one is the smallest digit
        //
        //Example 1:
        //Input: pattern = "IIIDIDDD"
        //- From (right to left):
        //- I mean Increasing:
        //  ==> III:
        //      + index=3: (x>=3)
        //  + We have multiple D
        //  + DDD:
        //      + index=0: (x>=3)
        //pattern = "IIIDIDDD"
        //- 123435432
        //  + 1,2,3,4,[3]:
        //      + Duplicated
        //          + Increase the nearest element with bigger value
        //          => 1,2,3,5,4
        //  + 1,2,3,5,4,[5]
        //      + ==> max+1
        //      => 1,2,3,5,4,(6)
        //  + 1,2,3,5,4,(6),5
        //      + Increase 6 => 7
        //      =>  1,2,3,5,4,7,(5)
        //
        //* Main points:
        //
        //- Prev sẽ là 1 ==> Nếu cần về sau sẽ tăng lên
        //
        //- Điểm khó ở D:
        //"DD"
        //D: 10->9
        //DIDD: Nên tách ra mỗi I/D có 2 elements
        //5|D|4|I|6|D|3|D|2
        //Nếu value exists:
        //  ==> chỉ có thể tăng lên (Ưu tiên đằng trước MIN ==> Dùng hết r)
        //- Không phải while true để tìm (Idea như thế này thì tìm cùng phải xét các case các element đằng trước xoá đi + thay vào giá trị hiện tại):
        //  ==> Vì nếu DDDD ==> Giảm dần
        //- Ta chỉ cần lấy lấy giá trị cuối:
        //  ==> Tăng các giá trị đằng trước với D lên 1 đơn vị là được.
        //
        //1.1, Special cases
        //
        //1.2, Optimization
        //- Using stack
        //
        //1.3, Complexity
        //- Time: O(n^2) => O(n)
        //- Space: O(n)
        //
        //2. Stack
        //- In this case, the core idea is to use a stack to manage the order in which numbers are appended.
        //- The stack helps handle consecutive'D'characters efficiently by (delaying their placement),
        // ensuring that numbers (in a decreasing sequence) are correctly (placed) in the smallest lexicographical order.
        //
        //- For example, given the pattern "IDID", we start by pushing 1 onto the stack because we always push the next number.
        // Since the first character is'I', we immediately pop from the stack and append 1 to the result.
        // Then we push 2 and, seeing the next 'D', we push 3 instead of immediately appending.
        // The'I'that follows tells us it's time to pop and append the numbers, so 3 and then 2 are added to the result,
        // maintaining the required decreasing order.
        // The process continues in this manner, ensuring that the number we build respects the pattern while remaining lexicographically smallest.
        //
        //- IIIDDDD
        //==> Multiple D so We need to add more value first
        //III:1,2,3
        //IIID:1,2,3,[2]
        //==> Thực ra là 3(I):
        //  => III : 1|I|2|I|3|I|X
        //  ==> Vẫn có thể add(1,2,3) nhưng ta có thể thay đổi(X)
        //  + Với D ta có thể để tăng dần 1 lượt DDD = 3,2,1 (Giảm dần)
        //
        //2.1,
        //2.2, Complexity
        //- Space: O(n)
        //- Time: O(n)
        //
//        String pattern = "DDD";
//        String pattern = "IIIDIDDD";
        String pattern = "IIIDDDD";
        System.out.println(smallestNumber(pattern));
        System.out.println(smallestNumberStack(pattern));
    }
}
