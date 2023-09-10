package E1_String;

public class E6_BuddyStrings {

    public static boolean buddyStrings(String s, String goal) {
        int n=s.length();
        int m=goal.length();

        if(n!=m){
            return false;
        }
        int[] countChar=new int[26];
        //Space : O(n)
        char[] charS=s.toCharArray();
        char[] charGoal=goal.toCharArray();

        int left=-1, right=-1;
        int diffCount=0;
        boolean haveCountGreaterThanTwo=false;

        //Time : O(n)
        for(int i=0;i<n;i++){
            if(s.charAt(i)!=goal.charAt(i)&&left==-1){
                left=i;
                diffCount++;
            }else if(s.charAt(i)!=goal.charAt(i)){
                right=i;
                diffCount++;
            }
            if(diffCount>2){
                return false;
            }
            countChar[s.charAt(i)-'a']++;
            if(countChar[s.charAt(i)-'a']>1){
                haveCountGreaterThanTwo=true;
            }
        }
        if(left==-1&&right==-1&&haveCountGreaterThanTwo){
            return true;
        }
        if(left==-1){
            left=0;
        }
        if(right==-1){
            if(n>1){
                right=1;
            }else{
                return false;
            }
        }
        char temp=charS[left];
        charS[left]=charS[right];
        charS[right]=temp;
        //Time : O(n)
        String newS=String.valueOf(charS);
        String newGoal=String.valueOf(charGoal);
        // System.out.printf("%s %s\n", newS, charGoal);

        //Time : O(n)
        return newS.equals(newGoal);
    }

    public static boolean buddyStringsRefer(String s, String goal) {
        int n=s.length();
        int m=goal.length();

        if(n!=m){
            return false;
        }
        if(s.equals(goal)){
            int[] countChar=new int[26];

            for(int i=0;i<n;i++){
                countChar[s.charAt(i)-'a']++;
                if(countChar[s.charAt(i)-'a']>=2){
                    return true;
                }
            }
            return false;
        }
        int firstIndex=-1, secondIndex=-1;

        for(int i=0;i<n;i++){
            if(s.charAt(i)!=goal.charAt(i)){
                if(firstIndex==-1){
                    firstIndex=i;
                }else if(secondIndex==-1){
                    secondIndex=i;
                }else{
                    return false;
                }
            }
        }
        if(firstIndex==-1||secondIndex==-1){
            return false;
        }

        return s.charAt(firstIndex)==goal.charAt(secondIndex)&&s.charAt(secondIndex)==goal.charAt(firstIndex);
    }

    public static void main(String[] args) {
        //* Requirement
        //- Given s and goal
        //* Return true, if we can swap two letters in S so the result is equal to goal
        //
        //** Idea
        //1.
        //1.0, Idea
        //- Constraint
        //1 <= s.length, goal.length <= 2 * 10^4
        //s and goal consist of lowercase letters.
        //
        //- Brainstorm
        //- Check số lượng character khác nhau giữa 2 string
        //--> Nếu > 2 thì sẽ return false.
        //- i và j : là 2 index ta sẽ lưu + replace thử
        //--> Sau đó compare string để return result
        //
        //- Bị 2 cases:
        //ab
        //ab
        //a=a, b=b ==> return false
        //
        //aa
        //aa
        //* Tư duy trên sai ==> Vì ta đang swap bên trong array thay vì swap giữa 2 array.
        //Vì:
        //Ex:
        //abab
        //abab
        //--> Nếu ta swap 2 ký tự giống nhau thì kết quả sẽ return true
        //<> Nếu làm theo cách trên return false
        //
        //1.1, Optimization
        //- Refactor
        //- Chia ra 2 cases:
        //+ 1 là 2 chuỗi giống nhau
        //  + Nếu có char count>1 : return true
        //  + <> return false
        //+ 2 chuỗi khác nhau:
        //  + Ta sẽ dùng firstIndex và secondIndex để check
        //  + 1 trong 2 index==-1 : return false
        //  + Nếu có nhiều hơn 2 index ==> Return false
        //+ Return thì chỉ cần check việc swap 2 character thay vì check việc (combine toàn bộ string) --> Thừa
        //
        //1.2, Complexity
        //+ Space : O(n)
        //+ Time : O(n)
        //
        String s="abab";
        String t="abab";
        System.out.println(buddyStrings(s, t));
        System.out.println(buddyStringsRefer(s, t));
        //#Reference:
        //1790. Check if One String Swap Can Make Strings Equal
        //2531. Make Number of Distinct Characters Equal
        //752. Open the Lock
        //1072. Flip Columns For Maximum Number of Equal Rows
        //784. Letter Case Permutation
    }
}
