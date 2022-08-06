package leetcode_medium_dynamic;

public class E59_PushDominoes {

    public static String pushDominoes(String dominoes) {
        int n=dominoes.length();
        int left[]=new int[n];
        int right[]=new int[n];
        int l=1,r=1;
        boolean inRangeLeft=false;
        boolean inRangeRight=false;

        for(int i=0;i<n;i++){
            if(inRangeLeft&&dominoes.charAt(n-1-i)!='L'){
                l++;
            }
            if(inRangeRight&&dominoes.charAt(i)!='R'){
                r++;
            }
            if(dominoes.charAt(i)=='R'||(inRangeRight && dominoes.charAt(i)!='L')){
                if(i>0&&dominoes.charAt(i-1)=='.'&&dominoes.charAt(i)=='R'){
                    r=1;
                }
                inRangeRight=true;
                right[i]=r;
            }else{
                r=1;
                inRangeRight=false;
            }
            if(dominoes.charAt(n-1-i)=='L'||(inRangeLeft && dominoes.charAt(n-1-i)!='R')){
                if(n-1-i>0&&dominoes.charAt(n-2-i)=='.'&&dominoes.charAt(n-1-i)=='L'){
                    l=1;
                }
                inRangeLeft=true;
                left[n-1-i]=l;
            }else{
                l=1;
                inRangeLeft=false;
            }
        }
        StringBuilder rs=new StringBuilder();

        for(int i=0;i<n;i++){
            if(left[i]<right[i]){
                if(left[i]!=0){
                    rs.append("L");
                }else{
                    rs.append("R");
                }
            }else if(left[i]>right[i]){
                if(right[i]!=0){
                    rs.append("R");
                }else{
                    rs.append("L");
                }
            }else{
                rs.append(".");
            }
        }
        return rs.toString();
    }

    public static String pushDominoes1(String dominoes) {
        int n=dominoes.length();
        int[] left =new int[n];
        int[] right =new int[n];
        int l=0,r=0;
        boolean inRangeLeft=false;
        boolean inRangeRight=false;

        for(int i=0;i<n;i++){
            if(inRangeLeft){
                l++;
            }
            if(inRangeRight){
                r++;
            }
            if(dominoes.charAt(i)=='R') {
                r = 1;
                if(i>0&&dominoes.charAt(i-1)!='L'){
                    right[i]=1;
                }
                right[i]=r;
                inRangeRight=true;
            }
            if(dominoes.charAt(i)=='L'){
                r=1;
                inRangeRight=false;
            }
            if(dominoes.charAt(n - i -1)=='L'){
                l = 1;
                if(n - i<n&&dominoes.charAt(n - i) != 'R'){
                    left[n-1-i]=1;
                    continue;
                }
                left[n-1-i]=l;
                inRangeLeft=true;
                continue;
            }
            if(dominoes.charAt(n - i -1)=='R'){
                l=1;
                inRangeLeft=false;
            }
            if(dominoes.charAt(i)=='.'&&inRangeRight){
                right[i]=r;
            }
            if(dominoes.charAt(n-1-i)=='.'&&inRangeLeft){
                left[n-1-i]=l;
            }
        }
        StringBuilder rs=new StringBuilder();

        for(int i=0;i<n;i++){
            if(left[i]<right[i]){
                if(left[i]!=0){
                    rs.append("L");
                }else{
                    rs.append("R");
                }
            }else if(left[i]>right[i]){
                if(right[i]!=0){
                    rs.append("R");
                }else{
                    rs.append("L");
                }
            }else{
                rs.append(".");
            }
        }
        return rs.toString();
    }

    public static void main(String[] args) {
        String s=".L.R...LR..L..";
//        String s="RR.L";
//        String s="R.R.L";
        //Đề bài ở đây cần clear vài cái --> Dẫn đến sai cách làm ngay;

        System.out.println(pushDominoes(s));
        System.out.println(pushDominoes1(s));
    }
}
