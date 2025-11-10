package contest;

public class E317_FindClosestPerson {

    public static int findClosest(int x, int y, int z) {
        if(Math.abs(x-z)>Math.abs(z-y)){
            return 2;
        }
        if(Math.abs(x-z)<Math.abs(z-y)){
            return 1;
        }
        return 0;
    }

    public static void main(String[] args) {

    }
}
