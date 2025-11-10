package contest;

import java.util.HashMap;
import java.util.HashSet;

public class E321_CalculateScoreAfterPerformingInstructions {

    public static long calculateScore(String[] instructions, int[] values) {
        int n=instructions.length;
        long rs=0;
        int start=0;
        HashSet<Integer> visited=new HashSet();
        while (start<n&&start>=0&&!visited.contains(start)) {
            if(instructions[start].equals("add")){
                rs+=values[start];
                visited.add(start);
                start++;
            }else if(instructions[start].equals("jump")){ //Jump
                visited.add(start);
                start+=values[start];
            }else {
                break;
            }
//            System.out.printf("Score: %s, index: %s\n", rs, start);
        }
        return rs;
    }

    public static void main(String[] args) {
        //** Requirement
        //- You are given two arrays, instructions and values, both of size n.
        //- You need to simulate a process based on the following rules:
        //- You start at the first instruction at index i = 0 with an initial score of 0.
        //- If instructions[i] is "add":
        //  + Add values[i] to your score.
        //  + Move to the next instruction (i + 1).
        //- If instructions[i] is "jump":
        //  + Move to the instruction at index (i + values[i]) without modifying your score.
        //- The process ends when you either:
        //  + Go out of bounds (i.e., i < 0 or i >= n), or
        //  + Attempt to revisit an instruction that has been previously executed. The revisited instruction is not executed.
        //* Return (your score) at (the end of the process).
        //
        //Example 1:
        //
        //Input: instructions = ["jump","add","add","jump","add","jump"], values = [2,1,3,1,-2,-3]
        //
        //Output: 1
        //
        //Explanation:
        //
        //Simulate the process starting at instruction 0:
        //
        //At index 0: Instruction is "jump", move to index 0 + 2 = 2.
        //At index 2: Instruction is "add", add values[2] = 3 to your score and move to index 3. Your score becomes 3.
        //At index 3: Instruction is "jump", move to index 3 + 1 = 4.
        //At index 4: Instruction is "add", add values[4] = -2 to your score and move to index 5. Your score becomes 1.
        //At index 5: Instruction is "jump", move to index 5 + (-3) = 2.
        //At index 2: Already visited. The process ends.
        //
        // Idea
        //1
        //1.0,
        //- Method-1:
        //+ Constraints:
        //
        //
        //- Brainstorm
        //
        //
        String[]  instructions = {"jump","add","add","jump","add","jump"};
        //jump
        //add
        int[] values = {2,1,3,1,-2,-3};
        System.out.println(calculateScore(instructions, values));
    }
}
