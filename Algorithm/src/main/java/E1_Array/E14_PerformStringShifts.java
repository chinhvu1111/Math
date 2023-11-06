package E1_Array;

public class E14_PerformStringShifts{
    public static String stringShift(String s, int[][] shifts) {
        int m=s.length();

        if(m==0){
            return "";
        }
        int numShift=0;

        for(int[] shift:shifts){
            if(shift[0]==0){
                numShift+=shift[1];
            }else{
                numShift-=shift[1];
            }
        }
        if(numShift>0){
            numShift=numShift%m;
            return s.substring(numShift)+s.substring(0, numShift);
        }
        numShift=numShift*-1;
        numShift=numShift%m;
//        System.out.println(n-numShift);
//        System.out.println(s.substring(n-numShift));
//        System.out.println(s.substring(0, n-numShift));
        return s.substring(m-numShift)+s.substring(0, m-numShift);
    }

    public static String stringShiftOptimization(String s, int[][] shift) {

        // Count the number of left shifts. A right shift is a negative left shift.
        int leftShifts = 0;
        for (int[] move : shift) {
            if (move[0] == 1) {
                move[1] = - move[1];
            }
            leftShifts += move[1];
        }

        // Convert back to a positive, do left shifts, and return.
        leftShifts = Math.floorMod(leftShifts, s.length());
        s = s.substring(leftShifts) + s.substring(0, leftShifts);
        return s;
    }

    public static void main(String[] args) {
//        String  s = "abc";
//        String  s = "a";
        String  s = "ab";
//        int[][] shift = {{0,1},{1,2}};
        int[][] shift = {{0,1},{1,5}};
        System.out.println(stringShift(s, shift));
        //- Ở đây nó chuyển việc shift left --> shift right (Shift k left <0 <=> Shift (n-abs(k)))
        //Ex: abcd
        //Shift 2 left : abcd => cdab
        //<=> shift (4-2) right : abcd => cdab
        //
        //+ Số lượng shift lúc này sẽ là > 0
        System.out.println(stringShiftOptimization(s, shift));
        //#Reference:
        //1540. Can Convert String in K Moves
        //2464. Minimum Subarrays in a Valid Split
        //2278. Percentage of Letter in String
    }
}
