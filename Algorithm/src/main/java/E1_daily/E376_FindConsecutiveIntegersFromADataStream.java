package E1_daily;

public class E376_FindConsecutiveIntegersFromADataStream {

    static class DataStream {

        public DataStream(int value, int k) {

        }

        public boolean consec(int num) {
            return true;
        }
    }

    public static void main(String[] args) {
        //** Requirement
        //- For a stream of integers, implement a data structure that checks if (the last k integers) parsed
        // in the stream are equal to (value).
        //- Implement the DataStream class:
        //  + DataStream(int value, int k) Initializes the object with an empty integer stream
        // and the two integers value and k.
        //  + boolean consec(int num) Adds num to the stream of integers.
        //* Returns true if (the last k integers) are equal to value, and false otherwise.
        // If there are less than k integers, the condition does not hold true, so returns false.
        //
        //** Idea
        //1.
        //1.0,
        //- Method-1:
        //+ Constraints:
        //1 <= value, num <= 10^9
        //1 <= k <= 10^5
        //At most 10^5 calls will be made to consec.
        //  + Time: O(n) call
        //
        //* Brainstorm:
        //
        //
        //1.1, Case
        //
        //1.2, Optimization
        //
        //
        //1.3, Complexity
        //- Time: O(n)
        //- Space: O(1)
        //
    }
}
