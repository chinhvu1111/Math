package interviews;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class E121_MaximizeTheConfusionOfAnExam_binary {

    public static int maxConsecutiveAnswers(String answerKey, int k) {
        int rs=0;
        int numSetTrue=k;
        int currentValueTrue;
        int numSetFalse=k;
        int currentValueFalse;

        boolean isDisableTrue;
        boolean isDisableFalse;
        int n=answerKey.length();

        for(int i=0;i<n;i++){
            //True answer
            isDisableTrue=false;
            currentValueTrue=0;
            numSetTrue=k;

            //False answer
            isDisableFalse=false;
            currentValueFalse=0;
            numSetFalse=k;
            for(int j=i;j<n;j++){
                //Case 1: FF(done) TTTTT FFF
                if(answerKey.charAt(j)=='T'){
                    currentValueTrue++;
                    isDisableTrue=false;
                }else if(numSetTrue>0&&!isDisableTrue){
                    numSetTrue--;
                    currentValueTrue++;
                }else {
                    numSetTrue=k;
                    isDisableTrue=true;
                    currentValueTrue=0;
                }
//                System.out.printf("%s %s\n\n",j, currentValueTrue);
                rs=Math.max(rs, currentValueTrue);

                //False answer
                if(answerKey.charAt(j)=='F'){
                    currentValueFalse++;
                    isDisableFalse=false;
                }else if(numSetFalse>0&&!isDisableFalse){
                    numSetFalse--;
                    currentValueFalse++;
                }else {
                    numSetFalse=k;
                    isDisableFalse=true;
                    currentValueFalse=0;
                }
//                System.out.printf("%s %s\n\n",j, currentValueTrue);
                rs=Math.max(rs, currentValueFalse);
            }
        }

        return rs;
    }

    public static int maxConsecutiveAnswersOptimizeOnN(String answerKey, int k) {
        int rs=0;
        int numSetTrue;
        int currentValueTrue;
        int numSetFalse;
        int currentValueFalse;
        int n=answerKey.length();

        for(int i=0;i<n;i++){
            //True answer
            currentValueTrue=0;
            numSetTrue=k;

            //False answer
            currentValueFalse=0;
            numSetFalse=k;
            for(int j=i;j<n;j++){
                //Case 1: FF(done) TTTTT FFF
                if(answerKey.charAt(j)=='T'){
                    currentValueTrue++;
                }else if(numSetTrue>0){
                    numSetTrue--;
                    currentValueTrue++;
                }else {
                    break;
                }
//                System.out.printf("%s %s\n\n",j, currentValueTrue);
                rs=Math.max(rs, currentValueTrue);
            }
            for(int j=i;j<n;j++){
                //False answer
                if(answerKey.charAt(j)=='F'){
                    currentValueFalse++;
                }else if(numSetFalse>0){
                    numSetFalse--;
                    currentValueFalse++;
                }else {
                    break;
                }
//                System.out.printf("%s %s\n\n",j, currentValueTrue);
                rs=Math.max(rs, currentValueFalse);
            }
        }

        return rs;
    }

    public static int maxConsecutiveAnswersOptimizeONWrong(String answerKey, int k) {
        int rs=0;
        int numSetTrue=0;
        int numSetFalse=0;
        int currentValueTrue=0;
        int currentValueFalse=0;
        int n=answerKey.length();
        int leftIndexTrue=-1;
        int rightIndexTrue=0;
        int leftIndexFalse=-1;
        int rightIndexFalse=0;
        int countTrue=0;
        int countFalse=0;

        for(int i=0;i<n;i++){
            //Case 1: FF(done) TTTTT FFF
            //Case 2: FFTTFFFTTTTTTTFFFTTTTT, k=2
            if(answerKey.charAt(i)=='T'){
                currentValueTrue++;
            }else if(answerKey.charAt(i)=='F'){
                if(countTrue<k){
                    rightIndexTrue=i;
                    currentValueTrue++;
                    countTrue++;
                }else{
                    rightIndexTrue=i;
                    leftIndexTrue++;
                    if(leftIndexTrue>=0&&leftIndexTrue<n&&answerKey.charAt(leftIndexTrue)=='T'){
                        leftIndexTrue=rightIndexTrue;
                        currentValueTrue=1;
                        countTrue=1;
                    }
                }
            }
//            System.out.printf("%s %s %s %s\n",i, leftIndexTrue, rightIndexTrue, currentValueTrue);
            rs=Math.max(rs, currentValueTrue);

            //False answer
            //Case 1: FF(done) TTTTT FFF
            //Case 2: FFTTFFFTTTTTTTFFFTTTTT, k=2
            if(answerKey.charAt(i)=='F'){
                currentValueFalse++;
            }else if(answerKey.charAt(i)=='T'){
                if(countFalse<k){
                    rightIndexFalse=i;
                    currentValueFalse++;
                    countFalse++;
                }else{
                    rightIndexFalse=i;
                    leftIndexFalse++;
                    if(leftIndexFalse>=0&&leftIndexFalse<n&&answerKey.charAt(leftIndexFalse)=='F'){
                        leftIndexFalse=rightIndexFalse;
                        currentValueFalse=1;
                        countFalse=1;
                    }
                }
            }
            System.out.printf("%s %s %s %s\n",i, leftIndexTrue, rightIndexTrue, currentValueTrue);
            rs=Math.max(rs, currentValueFalse);
        }

        return rs;
    }

    public static int maxConsecutiveAnswersOptimizeON(String answerKey, int k) {
        int rs=0;
        int n=answerKey.length();
        int[] valuesTrue =new int[n];
        int[] valuesFalse =new int[n];
//        int queueArr[]=new int[k];
//        int front=-1, rear=-1;
        Deque<Integer> queueTrue=new LinkedList<>();
        int currentValueTrue=0;
        int countWindowTrue=0;
        Deque<Integer> queueFalse=new LinkedList<>();
        int currentValueFalse=0;
        int countWindowFalse=0;

        for(int i=0;i<n;i++){
            //F -> T
            currentValueTrue++;
            countWindowTrue++;
            if (answerKey.charAt(i) == 'F') {
                if(queueTrue.isEmpty()){
                    countWindowTrue=1;
                }
                if(queueTrue.size()==k){
                    currentValueTrue=countWindowTrue-valuesTrue[queueTrue.pollFirst()];
                    valuesTrue[i]=currentValueTrue;
                }else{
                    valuesTrue[i]=currentValueTrue;
                }
                queueTrue.add(i);
            }
//            System.out.printf("%s (%s), ", currentValueTrue, answerKey.charAt(i));
            rs=Math.max(rs, currentValueTrue);

            // T -> F
            currentValueFalse++;
            countWindowFalse++;
            if (answerKey.charAt(i) == 'T') {
                if(queueFalse.isEmpty()){
                    countWindowFalse=1;
                }
                if(queueFalse.size()==k){
                    currentValueFalse=countWindowFalse-valuesFalse[queueFalse.pollFirst()];
                    valuesFalse[i]=currentValueFalse;
                }else{
                    valuesFalse[i]=currentValueFalse;
                }
                queueFalse.add(i);
            }
//            System.out.printf("%s (%s), ", currentValueFalse, answerKey.charAt(i));
            rs=Math.max(rs, currentValueFalse);
        }

        return rs;
    }

    public static void main(String[] args) {
//        String answerKey="TTFF";
//        int k=2;
//        String answerKey="FFTTFFFFFFTTFFFFTTTTT";
//        int k=4;
//        String answerKey="FFTTTTTF";
//        int k=3;
//        String answerKey="FFTTFFFTTTTTTTFFFTTTTT";
//        int k=2;
//        String answerKey="FFFTTFTTFT";
//        int k=3;
        //Case liên quan đến leftIndex ==T
//        String answerKey="FFTFTTTFFF";
//        int k=1;
//        String answerKey="TTFTTFTT";
//        int k=1;
        //Case này là case phải pop đi và giá trị cũ currentValue cũng phải giữ 1 phần
        String answerKey="FFFTTFTTFTFFFFF";
        int k=3;
        //Case 1 : Phải set nhiều đoạn
        //FF(TT)FFFFFF(TT)FFFF(TTTTT)
        //k=4
        //True
        //
        //Không phải lúc nào đánh k cũng bắt đầu bằng T
        //--> Bắt đầu bằng F
        //
        //
        //1, Tuy là set nhiều đoạn nhưng vẫn cần phái chạy từ (left --> right)
        ////==> Chỉ cần O(n)
        //
        //Case 1.1
        //
        //(FF)TTTT(F)
        //k=2
        //rs=6
        //
        //Case 1.2 : Không thể đi 2 chiều
        //
        //(FF)TTTT(F)
        //k=3
        //rs=6
        //
        //Case 2 : Chọn 1 trong các đoạn
        //FF(TT)FFF(TTTTTTT)FFF(TTTTT)
        //k=2
        //
        //
        //
        //TTT(FF)TTT
        //
        //
        //Case 4:
        //"FFFTTFTTFT"
        //3
        //
        //FFFFFFFTFT
        //FFTTTTTTTT
        //
        //
        //wrong : 7
        //expect : 8
        //
        //Case 5:
        //
        //FFF(TT)F(TT)F(T)FFFFF
        //
        //Set F to T
        //FFF(TT)F(TT)F(T)FFFFF
        //123 45 5 67 7 8
        //
        //Set T to F
        //input	: FFF(TT)F(TT)F(T)FFFFF
        //k	: 123 21 1 08 9 9
        //rs	: 123 45 6 77 8
        //---> Việc push cái T ở cuối ra, currentValue cũng cần phải trừ đi đoạn sau T
        //==> Chứ không phải giữ nguyên --> Về cơ bản logic đã SAI rồi.
        //
        //Lại :
        //T -> F
        //input	: FFF(TT)F(TT)F(T)FFFFF
        //k	: 123 45 6 74 5 5 6 7 8 9 10
        //rs	: 123 45 6 78 9 10
        //
        //+ Mỗi vị trí F có giá trị riêng (Trong 1 khoảng nhất đinh) ==> Cần 1 cycle count
        //+ currentValue - values[i] --> Trong 1 cycle nhất định được thôi
        System.out.println(maxConsecutiveAnswers(answerKey, k));
        System.out.println(maxConsecutiveAnswersOptimizeOnN(answerKey, k));
        System.out.println(maxConsecutiveAnswersOptimizeON(answerKey, k));
    }
}
