package E2_design;

public class E2_DesignCompressedStringIterator {

    public static class StringIterator {
        Character curChar;
        int curIndex;
        int count=0;
        String compressedStr;
        int n;

        public StringIterator(String compressedString) {
            curIndex=0;
            this.compressedStr=compressedString;
            this.n=compressedString.length();
        }

        public static boolean isDigit(char curChar){
            return curChar>='0'&&curChar<='9';
        }

        public char next() {
//            System.out.printf("Count: %s\n", count);
            if(count>1){
                count--;
                return curChar;
            }else{
                count=0;
            }
            if(!hasNext()){
                return ' ';
            }
            curChar=compressedStr.charAt(curIndex++);
            while(curIndex<n&&isDigit(compressedStr.charAt(curIndex))){
                count=count*10+compressedStr.charAt(curIndex)-'0';
                curIndex++;
            }
            return curChar;
        }

        public boolean hasNext() {
            return count>1||curIndex<n;
        }
    }

    public static void main(String[] args) {
        // Requirement
        //- Given compressed string
        //==> Implement the data structure for support traverse all character of "uncompressed" string
        //Ex:
        //compressed string: L1e2t1C1o1d1e1
        //==> Uncompressed string : LeetCode
        //
        // Idea
        //1.
        //1.0,
        //- Constraint:
        //
        //
        //- Brainstorm
        //
        //#Reference:
        //1491. Average Salary Excluding the Minimum and Maximum Salary
        //1231. Divide Chocolate
        //1676. Lowest Common Ancestor of a Binary Tree IV
    }
}
