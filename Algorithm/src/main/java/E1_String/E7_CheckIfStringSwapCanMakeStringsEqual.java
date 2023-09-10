package E1_String;

public class E7_CheckIfStringSwapCanMakeStringsEqual {

    public static boolean areAlmostEqual(String s, String goal) {
        int n=s.length();
        int m=goal.length();

        if(n!=m){
            return false;
        }
        if(s.equals(goal)){
            return true;
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
        //-
        //
        //** Idea
        //1.
        //1.0, Idea
        //- Constraint
        //
    }
}
