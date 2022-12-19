package interviews;

public class E228_MinimizeResultByAddingParenthesesToExpression_dynamic {

    public static String minimizeResult(String expression) {
        //247+38
        //
        int indexPlus=0;
        int n=expression.length();

        for(int i=0;i<n;i++){
            if(expression.charAt(i)=='+'){
                indexPlus=i;
            }
        }
        int[] firstNumberLeft=new int[indexPlus];
        int[] firstNumberRight=new int[indexPlus];
        int[] secondNumberLeft=new int[n];
        int[] secondNumberRight=new int[n];
        int currentNumberLeft=0;
        int countLeft=1;
        int currentNumberRight=0;

        for(int i=indexPlus-1;i>=0;i--){
            currentNumberLeft= (expression.charAt(i)-'0')*countLeft + currentNumberLeft;
            countLeft=countLeft*10;
            firstNumberLeft[i]=currentNumberLeft;
            currentNumberRight=expression.charAt(indexPlus-i-1) -'0'+currentNumberRight*10;
            firstNumberRight[indexPlus-i-1]=currentNumberRight;
        }
        currentNumberLeft=0;
        countLeft=1;
        currentNumberRight=0;
        for(int i=indexPlus+1;i<n;i++){
            currentNumberLeft=(expression.charAt(i)-'0') + currentNumberLeft*10;
            secondNumberLeft[i]=currentNumberLeft;
            currentNumberRight=(expression.charAt(n-(i-indexPlus-1)-1)-'0')*countLeft + currentNumberRight;
            countLeft=countLeft*10;
            secondNumberRight[n-(i-indexPlus-1)-1]=currentNumberRight;
        }

        //247 ,47 ,7
        println(firstNumberLeft);
        //2 ,24 ,247 ,
        println(firstNumberRight);
        //0 ,0 ,0 ,0 ,3 ,38 ,
        println(secondNumberLeft);
        //0 ,0 ,0 ,0 ,38 ,8 ,
        println(secondNumberRight);
//        System.out.println();

        int rs=Integer.MAX_VALUE;
        int start=0;
        int end=0;

        for(int i=indexPlus-1;i>=0;i--){
            int firstLeft=0;
            int firstRight=0;

            if(i<indexPlus-1){
                //2 ,24 ,247
                firstLeft=firstNumberLeft[i+1];
            }
            //247 ,47 ,7
            firstRight=firstNumberRight[i];
            for(int j=indexPlus+1;j<n;j++){
                int secondLeft=0;
                int secondRight=0;
                int s = 0,t = 0;

                //0 ,0 ,0 ,0 ,3 ,38
                secondLeft=secondNumberLeft[j];
                if(j<n-1){
                    //0 ,0 ,0 ,0 ,38 ,8
                    secondRight=secondNumberRight[j+1];
                }
                int value=0;

                //firstRight*(firstLeft + secondLeft)*secondRight
//                if(firstLeft==0&&secondRight==0){
//                    value=firstRight+secondLeft;
//                }else if(firstLeft==0&&secondRight!=0){
//                    value=(firstRight + secondLeft)*secondRight;
//                }else if(firstLeft!=0&&secondRight==0){
//                    value=firstLeft*(firstRight + secondLeft);
//                }else if(firstLeft!=0&&secondRight!=0){
//                    value=firstLeft*(firstRight + secondLeft)*secondRight;
//                }
                //
                //firstRight*(firstLeft + secondLeft)*secondRight
                if(firstLeft==0&&secondLeft==0){
                    value=firstLeft+secondRight;
                    s=i+1;
                    t=j+1;
                }else if(firstLeft==0&&secondLeft!=0){
                    if(secondRight!=0){
                        value=(firstRight + secondLeft)*secondRight;
                    }else{
                        value=firstRight + secondLeft;
                    }
                    s=i;
                    t=j;
                }else if(firstLeft!=0&&secondLeft==0){
                    if(firstRight!=0){
                        value=firstRight*(firstLeft + secondRight);
                    }else{
                        value=firstLeft + secondRight;
                    }
                    s=i+1;
                    t=j+1;
                }else if(firstLeft!=0&&secondLeft!=0){
                    if(secondRight!=0){
                        value=firstRight*(firstLeft + secondLeft)*secondRight;
                    }else {
                        value=firstRight*(firstLeft + secondLeft);
                    }
                    s=i+1;
                    t=j;
                }
                if(s==indexPlus-1&&i==indexPlus-1){
                    s=0;
                }
                //(247+38)
                //2*(47+...)
//                System.out.printf("%s %s %s %s %s\n",firstRight, firstLeft, secondLeft, secondRight, value);

                if(value<rs){
                    start=s;
                    end=t;
                    rs=value;
                }
            }
        }

        StringBuilder rsS=new StringBuilder();

        for(int i=0;i<n;i++){
            if(i==start){
                rsS.append("(");
            }
            rsS.append(expression.charAt(i));
            if(i==end){
                rsS.append(")");
            }
        }

        return rsS.toString();
    }

    /*
    - Đơn giản là chỉ thay đổi việc lấy giá trị đầu =0 ==> =1 , quan sát lại 1 chút công thức toán
    ==> Bài toán đã trở nên đơn giản hởn rất nhiều
     */
    public static String minimizeResultRefactor(String expression) {
        StringBuilder rsStr=new StringBuilder();
        int rs=Integer.MAX_VALUE;
        int n=expression.length();
        int index=expression.indexOf('+');
        int start=0;
        int end=0;

        for (int i = 0; i < index; i++) {
            int n1=(i==0)?1 : Integer.parseInt(expression.substring(0,i));
            int n2=Integer.parseInt(expression.substring(i, index));

            for(int j=index+1;j<n;j++){
                int n3=Integer.parseInt(expression.substring(index+1, j+1));
                int n4=(j==n-1)? 1: Integer.parseInt(expression.substring(j+1, n));

                if(n1*(n2+n3)*n4<rs){
                    rs=n1*(n2+n3)*n4;
                    start=i;
                    end=j;
                }
            }
        }

        StringBuilder rsS=new StringBuilder();

        for(int i=0;i<n;i++){
            if(i==start){
                rsS.append("(");
            }
            rsS.append(expression.charAt(i));
            if(i==end){
                rsS.append(")");
            }
        }

        return rsS.toString();
    }

    public static void println(int[] arr){
        for(int i=0;i<arr.length;i++){
            System.out.printf("%s ,", arr[i]);
        }
        System.out.println();
    }

    public static void main(String[] args) {
        //2(47+38)"
        String s="247+38";
//        String s="12+34";
//        String s="999+999";
//        String s="9+9";
        System.out.println(minimizeResult(s));
        System.out.println(minimizeResultRefactor(s));
        //
        //** Đề bài
        //- Format : <number-1>+<number2>
        //- 247+38 : Đặt dấu như thế nào để số min
        //VD: 2*(47+38)
        //
        //** Bài này tư duy như sau:
        //
        //1,
        //1.1,
        //- Ta cần phải chia thành 2 số khác nhau (a) + (b)
        //- Tư duy sẽ là :
        //+ Phân chia mỗi thành 2 phần khác nhau left, right
        //VD:
        //- Số thứ 1:
        //247:
        //+ left: 247 ,47 ,7 ,
        //+ right: 2 ,24 ,247 ,
        //
        //- Số thứ 2:
        //+ left: 0 ,0 ,0 ,0 ,3 ,38 ,
        //+ right: 0 ,0 ,0 ,0 ,38 ,8 ,
        //
        //==> Mục đích là để for (O^N) tính các giá trị sẽ truy cập trực tiếp được.
        //
        //1.2, Các lưu ý như sau:
        //- Lấy
        //+ start (Index đặt '(')
        //+ end (Index đặt ')')
        //--> Như thế nào : ==> Cái này hoàn toàn phụ thuộc vào
        //CT: firstRight*(firstLeft + secondLeft)*secondRight
        //==> Phụ thuộc vào
        //+ firstLeft nhận index gì
        //+ secondLeft nhận index gì
        //
        //** Tư duy lúc này khá nhập nhằng:
        //+ Nếu firstLeft==0 ==> CT sẽ thành : (firstRight+secondLeft)*secondRight
        //==> Lúc đó sẽ tính dự trên index của firstRight
        //==> Thành ra phải xét all cases
        //** Nếu xét all case :
        //+ Chú ý chỉ cần xét các số nằm trong ngoặc (a+b) : a<>0 và b<>0
        //- Lỗi:
        //+ Vì start, end thay đổi còn tùy thuộc vào left, right nhận index tùy thuộc vào việc nó khác !=0 hay không (lúc i/i+1)
        //==> Cần phải assign start, end cho từng trường hợp.
        //
        //- Một điều nữa là liên quan đến các value left, right cần chú ý:
        //+ left : 247 ,47 ,7 ,
        //+ right : 2 ,24 ,247 ,
        //+ CT: right * (left+...)
        //==> Cần phải xác định left bắt dầu bằng số 0 / right bắt đầu bằng số 0 ==> Để có thể chọn được index cho phù hợp.
        //
        //1.3, Clean tư duy bằng cách đổi default value của left/ right
        //
        //CT: firstRight*(firstLeft + secondLeft)*secondRight
        //- firstRight = 1
        //- secondRight = 1
        //==> Lúc đó
        //+ firstLeft : 247 ,47 ,7 ,
        //+ seconfLeft : 0 ,0 ,0 ,0 ,38 ,8 ,
        //==> Khi tính ra thì sẽ không cần phải xet ==0
        //---> Index sẽ phụ thuộc hoàn toàn vào firstLeft, seconfLeft
        //
        //1.4, Refactor
        //- Ta có thể refactor 1 chút dùng substring --> More slow.
    }
}
