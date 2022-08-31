package interviews;

public class E119_SuperPow_binary {

    public static int superPow(int a, int[] b) {
//        int m=1;
//        int currentValue=a;
//
//        while(currentValue<=1337){
//            currentValue*=a;
//            m++;
//        }
//        int residual=currentValue%1337;
//
//        int exponentNeeded=m;
        int n=b.length;
        double mod=1;
        int length=0;

//        for(int i=n-1;i>=0;i--){
//            if(b[i]<=exponentNeeded){
//                exponentNeeded-=b[i];
//                b[i]=0;
//                mod*=residual;
//            }else{
//                b[i]-=exponentNeeded;
//                mod*=residual;
//                exponentNeeded=m;
//                i++;
//            }
//            length++;
//        }

        for(int i=n-1;i>=0;i--){
            double currentMod=1;
            for(int j=0;j<length&&b[i]!=0;j++){
                currentMod=(currentMod*Math.pow(a, 10))%1337;
            }
            mod=mod*currentMod%1337;
            System.out.println(Math.pow(a, 10)+" "+mod);
            if(b[i]!=1&&b[i]!=0){
                mod=(mod*Math.pow(mod, b[i]))%1337;
            }
            System.out.printf("%s\n\n",mod);
            length++;
        }

        return (int) (mod % 1337);
    }

    public static void main(String[] args) {
//        int[] arr=new int[]{4,3};
//        int a=8;
//        int[] arr=new int[]{1,0};
//        int a=2;
//        int[] arr=new int[]{3};
//        int a=2;
        int[] arr=new int[]{2,0,0};
        int a=2147483647;

//        System.out.println(64%14);
//        System.out.println(56%14);
//        System.out.println(Math.pow(8,14)%14);

        System.out.println(superPow(a, arr));
    }
}
