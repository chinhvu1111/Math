package contest;

public class E116_MinimumNumberOfChairsInAWaitingRoom {

    public static int minimumChairs(String s) {
        int rs=0;
        int numE=0;
        int n=s.length();

        for(int i=0;i<n;i++){
            if(s.charAt(i)=='E'){
                numE++;
            }else{
                numE--;
            }
            rs=Math.max(rs, numE);
        }
        return rs;
    }

    public static void main(String[] args) {
        //* Requirement
        //- You are given a string s. Simulate events at (each second i):
        //  + If s[i] == 'E', a person (enters) the waiting room and takes one of the chairs in it.
        //  + If s[i] == 'L', a person (leaves) the waiting room, freeing up a chair.
        //* Return (the minimum number of chairs) needed so that a chair is available
        // for (every person) who enters (the waiting room) given that (it is initially empty).
        //
        // Idea
        //1.
        //1.0,
        //- Constraint:
        //
        //
        //- Brainstorm
    }
}
