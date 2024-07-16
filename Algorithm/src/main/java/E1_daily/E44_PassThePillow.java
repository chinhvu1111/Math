package E1_daily;

public class E44_PassThePillow {

    public static int passThePillow(int n, int time) {
        if(time+1<n){
            return time+1;
        }
        int k = (time/(n-1))%2;
        if(k==1){
            //left
            return n-(time-(time/(n-1))*(n-1));
        }
        //right
        return 1+time%(n-1);
    }

    public static void main(String[] args) {
        //** Requirement
        //- There are n people standing in a line labeled from (1 to n).
        //- The first person in the line is holding a pillow initially.
        //- Every second, the person holding (the pillow) passes it to (the next person) standing in the line.
        //- Once the pillow reaches (the end of the line), the direction changes,
        //  + and people continue passing the pillow in (the opposite direction).
        //- For example, once
        //  + the pillow reaches the (nth person) they pass it to the (n - 1th person),
        //  then to the (n - 2th person) and so on.
        //- Given the two positive integers n and time,
        //* Return the index of the person holding the pillow after time seconds.
        //
        //** Idea
        //1.
        //1.0,
        //* Method-1:
        //- Constraint
        //2 <= n <= 1000
        //1 <= time <= 1000
        //
        //- Brainstorm
        //Example 1:
        //
        //Input: n = 4, time = 5
        //Output: 2
        //Explanation: People pass the pillow in the following way: 1 -> 2 -> 3 -> 4 -> 3 -> 2.
        //After five seconds, the 2nd person is holding the pillow.
        //1 -> 2 -> 3 -> 4 (time=3)
        // -> 3 -> 2.
        // = (n-1)+(n-1)+x
        // = (n-1)*k + x
        //+ k%2==0:
        //  + direction = right
        //+ k%2==1:
        //  + direction = left
        //
        //
//        int n = 4, time = 5;
//        int n = 3, time = 2;
//        int n = 5, time = 17;
        //n=5
        //1,2,3,4,5
        //1 -> 5 : 4
        //5 -> 1 : 4
        //17/4 = 4
        //  + 17%4 == 1
        //time = 17
        int n = 2, time = 341;
        //1 -> 2
        //2 -> 1
        //
        System.out.println(passThePillow(n, time));
        //#Reference:
        //1894. Find the Student that Will Replace the Chalk
    }
}
