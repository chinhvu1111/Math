package contest;

public class E138_MaximumHeightOfATriangle {

    public static int cal(int x, int y){
        int num=1;
        int countF=0;
        while (x-num>=0){
            x-=num;
            countF++;
            num=2*countF+1;
        }
        num=2;
        int countS=0;
        while (y-num>=0){
            y-=num;
            countS++;
            num=(countS+1)*2;
        }
        //  1
        // 1 1
        //1 1 1
        if(countF>countS){
            return countS*2+1;
        }else if(countF<countS){
            return countF*2;
        }else{
            return countS*2;
        }
    }

    public static int maxHeightOfTriangle(int red, int blue) {
//        int x=red, y=blue;
//        int layerX=(x-1)/2+1;
//        int layerY=y/2;
//        int max=0;
//        if(layerX>layerY){
//            max=layerY*2+1;
//        }else if(layerX<layerY){
//            max=Math.max(layerX*2, max);
//        }else{
//            max=Math.max(layerX*2, max);
//        }
//
//        layerX=x/2;
//        layerY=(y-1)/2+1;
//        if(layerX>layerY){
//            max=Math.max(layerY*2, max);
//        }else if(layerX<layerY){
//            max=Math.max(layerX*2+1, max);
//        }else{
//            max=Math.max(layerX*2, max);
//        }
        //2, 4
        //
        int max = cal(red, blue);
        max=Math.max(cal(blue, red), max);
        return max;
    }

    public static void main(String[] args) {
        //    1
        //   1 1 => 2
        //  1 1 1
        // 1 1 1 1 => 4
        //1 1 1 1 1
//        int red = 2, blue = 4;
//        int red = 2, blue = 1;
//        int red = 10, blue = 1;
//        int red = 4, blue = 4;
        int red = 4, blue = 9;
        //out:3
        //Ex:4
        System.out.println(maxHeightOfTriangle(red, blue));
        //2 + 4 + 8
        //2 * (1+2+3)
    }
}
