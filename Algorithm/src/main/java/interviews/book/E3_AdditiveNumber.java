package interviews.book;

public class E3_AdditiveNumber {

    public static class Pair{
        int key;
        String value;
        public Pair(int key, String value){
            this.key=key;
            this.value=value;
        }

        @Override
        public String toString() {
            return key+":"+value;
        }
    }

    public static String calculateNumber(String s, String s1){
        char[] numberS=s.toCharArray();
        char[] numbers1=s1.toCharArray();
        StringBuilder rs=new StringBuilder();
        int index=numberS.length-1;
        int index1=numbers1.length-1;
        int remainNum=0;

        while (index>=0||index1>=0){
            int val1=(index>=0)?numberS[index]-'0':0;
            int val2=(index1>=0)?numbers1[index1]-'0':0;
            int newNum=(val1+val2+remainNum)%10;
            remainNum=(val1+val2+remainNum)/10;
            index--;
            index1--;
            rs.append(newNum);
        }
        if(remainNum!=0){
            rs.append(remainNum);
        }

        return rs.reverse().toString();
    }

    public static Pair canBeCreated(int index, String s, String target){
        int n=s.length();
        int j=0;
        int i;
        StringBuilder rs=new StringBuilder();

        for(i=index;i<n;i++){
            if(s.charAt(i)!=target.charAt(j)){
                return new  Pair(-1, "");
            }else{
                j++;
            }
            rs.append(s.charAt(i));
            if(j==target.length()){
                return new Pair(i+1, rs.toString());
            }
        }
        return new  Pair(-1, "");
    }

    public static boolean subAdditiveNumber(String a, String b, String s, int i){
        if(i==s.length()){
            return true;
        }
        String newNumber=calculateNumber(a, b);
//        System.out.println(newNumber);
        Pair nextElement=canBeCreated(i, s, newNumber);
        int nextIndex=nextElement.key;
        if(nextIndex==-1){
            return false;
        }
        return subAdditiveNumber(b, nextElement.value, s, nextIndex);
    }

    public static boolean isAdditiveNumber(String num) {
        int n=num.length();
        boolean rs;

        for(int i=1;i<n-1;i++){
            String num1=num.substring(0,i);
            if(num.charAt(i)=='0'){
                rs=subAdditiveNumber(num1, "0", num, i+1);
                if(rs){
                    return rs;
                }
                continue;
            }
            for(int k=i+1;k<n;k++){
                if(num.charAt(k)=='0'){
                    continue;
                }
                String num2=num.substring(i,k);
                rs=subAdditiveNumber(num1, num2, num, k);
                if(rs){
                    return rs;
                }
//                System.out.printf("%s %s \n", num1, num2);
            }
        }
        return false;
    }

    public static void main(String[] args) {
//        String s="199100199";
//        String s="101";
        String s="199001200";
        System.out.println(isAdditiveNumber(s));
        System.out.println(calculateNumber("18","12"));
        System.out.println(calculateNumber("18","92"));
        //#Reference:
        //307. Range Sum Query - Mutable
        //842. Split Array into Fibonacci Sequence
    }
}
