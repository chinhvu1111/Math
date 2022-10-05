package interviews;

public class E171_FibonacciNumber {

    public static class MatC{
        int a00;
        int a01;
        int a10;
        int a11;

        public MatC(int a00, int a01, int a10, int a11) {
            this.a00 = a00;
            this.a01 = a01;
            this.a10 = a10;
            this.a11 = a11;
        }
    }

    public static MatC matPow(){
        return null;
    }

    public static int fib(int n) {

        return 1;
    }

    public static int[][] multiplyMatrix(int[][] a, int[][] b){
        int[][] c=new int[2][2];

        for(int i=0;i<2;i++){
            for(int j=0;j<2;j++){
                c[i][j]=a[i][0]*b[0][j]+a[i][1]+b[1][j];
            }
        }
        return c;
    }

    public static void main(String[] args) {

    }
}
