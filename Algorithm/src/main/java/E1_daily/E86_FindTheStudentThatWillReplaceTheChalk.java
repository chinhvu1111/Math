package E1_daily;

public class E86_FindTheStudentThatWillReplaceTheChalk {

    public static int chalkReplacer(int[] chalk, int k) {
        int n=chalk.length;
        long sum=0;

        for(int e:chalk){
            sum+=e;
        }
        k= (int) (k%sum);
        int rs=-1;
        for(int i=0;i<n;i++){
            k=k-chalk[i];
            if(k<0){
                rs=i;
                break;
            }
        }
        return rs;
    }

    public static void main(String[] args) {
        //** Requirement
        //- There are (n students) in a class numbered from (0 to n - 1).
        //- The teacher will give each student a problem starting with (the student number 0), then (the student number 1),
        // and so on until (the teacher) reaches the student number (n - 1).
        //- After that, the teacher will (restart the process), starting with (the student number 0) again.
        //- You are given (a 0-indexed integer array) chalk and an integer k.
        //- There are initially (k pieces of chalk). When (the student number i) is given a problem to solve,
        // they will use (chalk[i] pieces of chalk) to solve that problem.
        //- However, if (the current number of chalk pieces) is (strictly less than) chalk[i],
        // then (the student number i) will be asked to replace (the chalk).
        //* Return (the index of the student) that will replace (the chalk pieces).
        //  - Ta sẽ assign problem cho từng student từ (0 -> n-1)
        //  - Tìm index của student cần (replace chalk)
        //
        //- chalk: Phấn
        //
        // Idea
        //1.
        //1.0,
        // Method-1:
        //- Constraint
        //chalk.length == n
        //1 <= n <= 10^5
        //1 <= chalk[i] <= 10^5
        //1 <= k <= 10^9
        //  + n khá lớn ==> Time: O(n) / O(n*k)
        //
        //- Brainstorm
        //- Nếu loop n students theo cycle ==> TLE
        //- Cần phải tính % trước
        //  + k%sum trước
        //- Sau đó mới loop
        //
        //#Reference:
        //228. Summary Ranges
        //2327. Number of People Aware of a Secret
        //1090. Largest Values From Labels
    }
}
