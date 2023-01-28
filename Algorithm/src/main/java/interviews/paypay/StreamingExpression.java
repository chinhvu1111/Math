package interviews.paypay;

public class StreamingExpression {
    public int preValue;
    public char sign;
    public int currentNumber=0;
    public int rs=0;
    public boolean isCharBefore=false;

    public StreamingExpression() {
        sign='+';
        preValue=0;
    }

    /**
     * Adding character to current instance
     * @param ch current character to be added
     * @return the information on whether this expression is valid or not
     * @Des : Additionally, it will print the current value of the expression when meeting character question mark(?).
     */
    public boolean addCharacter(char ch)  {
        if(ch==' '){
            return true;
        }
        if(ch=='?'){
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
            System.out.println(currentRs);
            return true;
        }else if(ch>='0'&&ch<='9'){
            currentNumber=currentNumber*10+(ch-'0');
            isCharBefore=false;
            return true;
        }
        if(isCharBefore){
            System.out.println("ERROR");
            return false;
        }else{
            //Mark as pre-exsisting character
            isCharBefore=true;
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
        return true;
    }

    public int getCurrentResult(){
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

    public void resetCache(){
        preValue=0;
        sign='+';
        currentNumber=0;
        rs=0;
        isCharBefore=false;
    }

    //Testing
    public static void main(String[] args) {
        StreamingExpression expStream=new StreamingExpression();
//        String expression="2+3*3?";
//        String expression="2+3/3?";
//        String expression="2+3/3*5?";
//        String expression="2+3/3*5+4?";
//        String expression="2+22*6/2?";
//        String expression="2423 + 2+22*6/2?";
//        String expression="2423 + 2+22*6/2 + 332 / 2?";
//        String expression="11 * 21? + 3 * 7?";
//        String expression="12-3?*4?";
//        String expression="2423 + 2+22*6/2? + 332 / 2 - 23?";
//        String expression="11*2?1+3?*7?++879?";
//        String expression="11+2?";
        String expression="11*2?1+3?*7?++879?";
        boolean isGood;
        for(int i=0;i<expression.length();i++){
            isGood=expStream.addCharacter(expression.charAt(i));
            if(!isGood){
                break;
            }
        }
        //Clear cache to re-use instance
        expStream.resetCache();
        String expression1="11*2?";
        for(int i=0;i<expression1.length();i++){
            isGood=expStream.addCharacter(expression.charAt(i));
            if(!isGood){
                break;
            }
        }
    }
}
