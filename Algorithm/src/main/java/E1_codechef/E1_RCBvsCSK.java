package E1_codechef;

import java.io.*;

public class E1_RCBvsCSK {

    public static String solution(int rcb, int csk){
        if(rcb-csk>=8){
            return "RCB";
        }
        return "CSK";
    }

    public static void main(String[] args) throws IOException {
        //* Requirement
        //- RCB vs CSK
        //In the recent RCB vs CSK match, RCB batted first and scored X runs while CSK batted second and scored Y runs.
        //It is known that RCB qualifies to the playoffs if they win by at least 18 runs, otherwise CSK qualify.
        // Knowing the final scores of both teams, find out who qualified to the playoffs.
        //
        // Idea
        //1.
        //1.0,
        //- Constraint:
        //150≤X≤250
        //150<=Y<=X+6
        //
        //- Brainstorm
        //
        //
//        System.setIn(new FileInputStream("input"));
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String input = bufferedReader.readLine();
        int rcb = Integer.parseInt(input.split(" ")[0]);
        int csk = Integer.parseInt(input.split(" ")[1]);
        System.out.println(solution(rcb, csk));
    }
}
