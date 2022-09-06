package interviews;

public class E129_CheckIfBinaryStringHasAtMostOneSegmentOfOnes {

    public static boolean checkOnesSegment(String s) {
        return !s.contains("01");
    }

    public static void main(String[] args) {
        String s="110";
    }
}
