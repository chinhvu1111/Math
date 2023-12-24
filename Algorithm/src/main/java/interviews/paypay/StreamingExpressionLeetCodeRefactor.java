package interviews.paypay;

public class StreamingExpressionLeetCodeRefactor {

    //Giá trị của số trước đó/ của toàn bộ expression trước đó
    public int preValue;
    public char sign;
    public int currentNumber=0;
    public int rs=0;

    public StreamingExpressionLeetCodeRefactor() {
        sign='+';
        preValue=0;
    }

    public int getCurrentValue(){
        int currentRs=0;
        //3*4+5?
        if(sign=='+'){
            currentRs=rs+preValue+currentNumber;
        }else if(sign=='-'){
            currentRs=rs+preValue-currentNumber;
        }else if(sign=='*'){
            currentRs=rs+preValue*currentNumber;
        }else if(sign=='/'){
            currentRs=rs+preValue/currentNumber;
        }
        return currentRs;
    }

    public void addCharacter(char ch){
        if(ch==' '){
            return;
        }
        if(ch>='0'&&ch<='9'){
            currentNumber=currentNumber*10+(ch-'0');
            return;
        }
        if(sign=='+'){
            rs+=preValue;
            preValue=currentNumber;
        }else if(sign=='-'){
            rs+=preValue;
            preValue=-currentNumber;
        }else if(sign=='*'){
            preValue*=currentNumber;
        }else if(sign=='/'){
            preValue/=currentNumber;
        }
        currentNumber=0;
        sign=ch;
        //2423 + ( (2+22*6/2) + 332 ) / 2 - 23
    }

    public static int calculate(String expression){
        StreamingExpressionLeetCodeRefactor expStream=new StreamingExpressionLeetCodeRefactor();
        for(int i=0;i<expression.length();i++){
            expStream.addCharacter(expression.charAt(i));
        }
        return expStream.getCurrentValue();
    }

    public static void main(String[] args) {
        //2+3*3?
        //- Không có 2 ký tự *,/ được dùng liên tiếp nhau
        //- Không có 2 ký tự +/- được dùng liên tiếp nhau
        //
        //+ 2+3*3 (Đang điền dở) --> Cần in
        //+ 2+3/3 (Đang điền dở) --> Cần in
        //+ 2+4? (Đang điền dở) --> Cần in
        //2+3+?
//        String s="1*2-3/4+5*6-7*8+9/10";
//        String s=" 3/2 ";
//        String s=" 3+5 / 2 ";
        //- Idea
        //- Bài này idea dạng cache sign và preVal
        //+ sign: dấu trước đó
        //+ preVal : val trước đó
        //  + Nếu +/- thì:
        //      + Sign='+' : rs=+preValue
        //      + Sign='-' : rs=-preValue
        //  + Nếu */ / thì:
        //      + sign="*" preValue*=current_num
        //      + sign="/" preValue/=current_num
        String s=" 3+5";
        System.out.println(calculate(s));
    }
}
