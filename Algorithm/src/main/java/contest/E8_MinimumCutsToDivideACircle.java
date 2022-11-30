package contest;

public class E8_MinimumCutsToDivideACircle {

    public static int numberOfCuts(int n) {
        if(n%2==1){
            return n;
        }
        return n/2;
    }

    public static void main(String[] args) {
        int n=3;
    }
}
