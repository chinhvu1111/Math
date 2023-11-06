package E1_Array;

public class E13_ConfusingNumber {

    public boolean confusingNumber(int n) {
        int num=n;
        //0,1,6,8,9
        int newNum=0;
        int[]mapRotate=new int[10];
        mapRotate[1]=1;
        mapRotate[6]=9;
        mapRotate[8]=8;
        mapRotate[9]=6;

        while(n!=0){
            int curNum=n%10;
            if(curNum!=0&&curNum!=1&&curNum!=6&&curNum!=8&&curNum!=9){
                return false;
            }else{
                newNum=newNum*10;
                newNum+=mapRotate[curNum];
            }
            n=n/10;
        }
        // System.out.println(newNum);
        return newNum!=num;
    }

    public static void main(String[] args) {
        //**Requirement:
        //- Xay số 180 : 18->81, 98 -> 68
        //* return true nếu số mới khác số cũ.
        //#Reference:
        //246. Strobogrammatic Number
        //1088. Confusing Number II
    }
}
