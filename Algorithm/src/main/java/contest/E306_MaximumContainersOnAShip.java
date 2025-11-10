package contest;

public class E306_MaximumContainersOnAShip {

    public static int maxContainers(int n, int w, int maxWeight) {
        //n*n
        //w*n*n = maxWeight
//        int size = (int) Math.sqrt((double) maxWeight / w);
        return Math.min(maxWeight / w, n*n);
    }

    public static void main(String[] args) {
        //- You are given a positive integer n representing an n x n cargo deck on a ship.
        // Each cell on the deck can hold one container with a weight of exactly w.
        //- However, the total weight of all containers, if loaded onto the deck, must not exceed the ship's maximum weight capacity, maxWeight.
        //* Return the maximum number of containers that can be loaded onto the ship.
        int n=2, w=3, maxWeight=3;
        System.out.println(maxContainers(n, w, maxWeight));
        n=100;
        w=4;
        maxWeight=3;
        System.out.println(maxContainers(n, w, maxWeight));
        //1
        //5
        //20
        n=1;
        w=5;
        maxWeight=20;
        System.out.println(maxContainers(n, w, maxWeight));
        //Output: 4
        //Expected: 1
        //
        //2
        //1
        //3
        n=2;
        w=1;
        maxWeight=3;
        System.out.println(maxContainers(n, w, maxWeight));
        //Output: 1
        //Expected: 3
    }
}
