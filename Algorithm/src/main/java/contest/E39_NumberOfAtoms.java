package contest;

import java.util.*;

public class E39_NumberOfAtoms {
    public static String countOfAtoms(String formula) {
        Stack<String> stack=new Stack<>();
        int n=formula.length();

        for(int i=0;i<n;i++){
            char currentC=formula.charAt(i);
//            System.out.printf("%s %s %s\n", currentC, currentC>='A'&&currentC<='Z', currentC=='O');

            if(currentC>='A'&&currentC<='Z'){
                StringBuilder currentAtom=new StringBuilder();
                currentAtom.append(currentC);
                int j=i+1;
                while(j<n&&formula.charAt(j)>='a'&&formula.charAt(j)<='z'){
                    currentAtom.append(formula.charAt(j));
                    j++;
                }
                stack.add(currentAtom.toString());

                StringBuilder number=new StringBuilder();
                while (j<n&&formula.charAt(j)>='0'&&formula.charAt(j)<='9'){
                    number.append(formula.charAt(j));
                    j++;
                }
                if(number.length()!=0){
                    stack.add(number.toString());
                }
//                System.out.printf("Atom: %s, number: %s\n",currentAtom, number);
                i=j-1;
            }else if(currentC=='('){
                stack.add("(");
            }else if(currentC==')'){
                int number=0;
                int j=i+1;
                while (j<n&&formula.charAt(j)>='0'&&formula.charAt(j)<='9'){
                    number=number*10+formula.charAt(j)-'0';
                    j++;
                }
                if(number==0){
                    number=1;
                }
//                System.out.println(stack);
                //"K4(ON(SO3)2)2"
                //(K4(ONS2O6)2)
                List<String> newList=new ArrayList<>();
                while(!stack.isEmpty()&&!stack.peek().equals("(")){
                    String currentElement=stack.pop();
                    if(isNumeric(currentElement)){
                        int newNumber=Integer.parseInt(currentElement);
                        newNumber=newNumber*number;
                        newList.add(String.valueOf(newNumber));
                        if(!stack.isEmpty()){
                            newList.add(stack.pop());
                        }
                    }else{
                        newList.add(String.valueOf(number));
                        newList.add(currentElement);
                    }
                }
                if(!stack.isEmpty()){
                    stack.pop();
                }
                Collections.reverse(newList);
//                System.out.println(newList);
                stack.addAll(newList);
            }
        }
//        System.out.println(stack);
        TreeMap<String, Integer> charToCount=new TreeMap<>();

        while (!stack.isEmpty()){
            String currentElement=stack.pop();
            if(isNumeric(currentElement)){
                String atom=stack.pop();
                charToCount.put(atom, charToCount.getOrDefault(atom, 0)+Integer.parseInt(currentElement));
            }else{
                charToCount.put(currentElement, charToCount.getOrDefault(currentElement, 0)+1);
            }
        }
        StringBuilder rs=new StringBuilder();

        for(String key: charToCount.keySet()){
            rs.append(key);
            int count=charToCount.get(key);
            if(count>1){
                rs.append(count);
            }
        }
//        System.out.println(stack);
        return rs.toString();
    }
    public static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }

    public static void main(String[] args) {
        //* Requirement
        //- Cho 1 biểu diễn của công thức hoá học
        //- Tên của tp bắt đầu bởi upper case và theo sau đó có thể là lower case
        //- Sau đó có thể là 1 số thể hiện số lương >2
        //+ Nếu count=1 thì không tính
        //For example, "H2O" and "H2O2" are possible, but "H1O2" is impossible.
        //Two formulas are concatenated together to produce another formula.
        //
        //For example, "H2O2He3Mg4" is also a formula.
        //
        //A formula placed in parentheses, and a count (optionally added) is also a formula.
        //For example, "(H2O2)" and "(H2O2)3" are formulas.
        //* Return the count of all elements as a string in the following form:
        // the first name (in sorted order), followed by its count (if that count is more than 1),
        // followed by the second name (in sorted order), followed by its count (if that count is more than 1), and so on.
        //Ex:
        //formula = "Mg(OH)2"
        //Output: "H2MgO2"
        //Explanation: The count of elements are {'H': 2, 'Mg': 1, 'O': 2}.
        //
        //Ex:
        //formula = "K4(ON(SO3)2)2"
        //Output: "K4N2O14S4"
        //count('O') = 6*2+2
        //Explanation: The count of elements are {'K': 4, 'N': 2, 'O': 14, 'S': 4}.
        //
        //** Idea
        //1.
        //1.0, Idea
        //- Constraint
        //1 <= formula.length <= 1000
        //formula consists of English letters, digits, '(', and ')'.
        //formula is always valid.
        //
        //- Brainstorm
        //- Dùng stack
        //- Cần lấy được tên nguyên tố --> Khi gặp upper case thì coi như đã lấy được 1 atom
        //Ex:
        //formula = "(ON(SO3))2"
        //(ON(SO3))2
        //+ Khi xét đến 2 ==> Ta phải xét đến () trước đó 1 phần
        //(ONSO3)2 ==> O2N2S2O6
        //==> Mình sẽ giải dần biếu thức ==> Nhân dần biểu thức ra
        //Ex:
        //formula = "K4(ON(SO3)2)2"
        //
        //- Special cases:
        //- H50: Có digit=0
        //
        //- H11He49NO35B7N46Li20
        //+ Liên quan đến đoạn hashmap những atom đứng 1 mình không phải put 1 là xong cũng cần phải lấy giá trị cũ + 1 nữa
        //
        //#Reference
        //471. Encode String with Shortest Length
        //736. Parse Lisp Expression
//        String formula = "K4(ON(SO3)2)2";
//        String formula = "Mg(OH)2";
//        String formula = "H2O";
//        String formula = "H50";
        String formula = "H11He49NO35B7N46Li20";
        System.out.println(countOfAtoms(formula));
    }
}
