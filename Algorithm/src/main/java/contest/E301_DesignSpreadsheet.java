package contest;

public class E301_DesignSpreadsheet {

    static class Spreadsheet {

        public int[][] sheet;
        public int rows;
        public Spreadsheet(int rows) {
            sheet=new int[1001][26];
            rows=rows;
        }

        public void setCell(String cell, int value) {
            int col = Integer.valueOf(cell.substring(1));
            // if(cell.charAt(0)-'A'>=rows){
            //     return;
            // }
            sheet[cell.charAt(0)-'A'][col-1]=value;
//            System.out.println(sheet[cell.charAt(0)-'A'][cell.charAt(1)-'0'-1]);
        }

        public void resetCell(String cell) {
            int col = Integer.valueOf(cell.substring(1));
            // if(cell.charAt(0)-'A'>=rows){
            //     return;
            // }
            sheet[cell.charAt(0)-'A'][col-1]=0;
        }

        public int getCell(String cell){
//            System.out.println(cell.charAt(1)-'0'-1);
//            System.out.println(cell.charAt(0)-'A');
//            System.out.println(cell.charAt(0)-'Z');
            if(cell.charAt(0)<'A'||cell.charAt(0)>'Z'){
                return Integer.valueOf(cell);
            }
//            System.out.println(cell.charAt(0)-'A');
            // if(cell.charAt(0)-'A'>=rows){
            //     return 0;
            // }
//            System.out.printf("%s %s\n", cell, sheet[cell.charAt(0)-'A'][cell.charAt(1)-'0'-1]);
            // if(cell.charAt(1)-'0'-1>=26){
            //     return 0;
            // }
            int col = Integer.valueOf(cell.substring(1));
            return sheet[cell.charAt(0)-'A'][col-1];
        }

        public int getValue(String formula) {
            formula=formula.substring(1);
            String[] express= formula.split("\\+");
            int rs=getCell(express[0])+ getCell(express[1]);
            return rs;
        }
    }

    public static void main(String[] args) {
        //** Requirement
        //- A spreadsheet is a grid with 26 columns (labeled from 'A' to 'Z') and a given number of rows.
        // Each cell in the spreadsheet can hold an integer value between 0 and 10^5.
        //
        //Implement the Spreadsheet class:
        //  + Spreadsheet(int rows) Initializes a spreadsheet with 26 columns (labeled 'A' to 'Z')
        // and the specified number of rows. All cells are initially set to 0.
        //  + void setCell(String cell, int value) Sets the value of the specified cell.
        //  The cell reference is provided in the format "AX" (e.g., "A1", "B10"),
        //  where the letter represents the column (from 'A' to 'Z') and the number represents a 1-indexed row.
        //  + void resetCell(String cell) Resets the specified cell to 0.
        //  + int getValue(String formula) Evaluates a formula of the form "=X+Y",
        //  where X and Y are either cell references or (non-negative integers), and returns the computed sum.
        //* Note: If getValue references a cell that has not been explicitly set using setCell,
        //  its value is considered 0.
        //
        //Example 1:
        //
        //Input:
        //["Spreadsheet", "getValue", "setCell", "getValue", "setCell", "getValue", "resetCell", "getValue"]
        //
        //[[3], ["=5+7"], ["A1", 10], ["=A1+6"], ["B2", 15], ["=A1+B2"], ["A1"], ["=A1+B2"]]
        //
        //Output:
        //[null, 12, null, 16, null, 25, null, 15]
        //
        //Explanation
        //
        //Spreadsheet spreadsheet = new Spreadsheet(3); // Initializes a spreadsheet with 3 rows and 26 columns
        //spreadsheet.getValue("=5+7"); // returns 12 (5+7)
        //spreadsheet.setCell("A1", 10); // sets A1 to 10
        //spreadsheet.getValue("=A1+6"); // returns 16 (10+6)
        //spreadsheet.setCell("B2", 15); // sets B2 to 15
        //spreadsheet.getValue("=A1+B2"); // returns 25 (10+15)
        //spreadsheet.resetCell("A1"); // resets A1 to 0
        //spreadsheet.getValue("=A1+B2"); // returns 15 (0+15)
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
        //1.1, Cases
        //["Spreadsheet","getValue"]
        //[[5],["=F1+69"]]
        //
        //["Spreadsheet","setCell","getValue"]
        //[[576],["H45",88383],["=992+H45"]]
        //Output: [null,null,992]
        //Expected: [null,null,89375]
//        Spreadsheet spreadsheet=new Spreadsheet(10);
//        System.out.println(spreadsheet.getValue("=5+7"));
//        spreadsheet.setCell("B2",15);
//        Spreadsheet spreadsheet=new Spreadsheet(5);
//        System.out.println(spreadsheet.getValue("=F1+69"));
//        Spreadsheet spreadsheet=new Spreadsheet(576);
//        spreadsheet.setCell("H45", 88383);
//        System.out.println(spreadsheet.getValue("=992+H45"));
        Spreadsheet spreadsheet=new Spreadsheet(3);
        spreadsheet.setCell("N3", 41457);
        System.out.println(spreadsheet.getValue("=992+H45"));
        //["Spreadsheet","getValue","getValue","getValue","resetCell","setCell","setCell","getValue","getValue","setCell","getValue","resetCell","resetCell","getValue"]
        //[[530],["=29483+15079"],["=A333+A135"],["=J215+P337"],["F241"],["Y104",2018],["O71",2353],["=Y104+O71"],["=73100+39834"],["Y118",58058],["=O71+Y104"],["O71"],["Y118"],["=F254+J85"]]
        //
        //- Col >= 1000
        //- The number with multiple digits
        //
    }
}
