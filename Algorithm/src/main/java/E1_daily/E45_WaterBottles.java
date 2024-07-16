package E1_daily;

public class E45_WaterBottles {

    public static int numWaterBottles(int numBottles, int numExchange) {
        int rs=0;
        int empty=0;

        while (numBottles>0||empty>=numExchange){
            //Drink
            rs+=numBottles;
            //Empty increase
            empty+=numBottles;
            //Exchange the empty to get the numBottles
            numBottles=empty/numExchange;
            empty=empty%numExchange;
            System.out.printf("empty: %s, numBottles: %s\n", numBottles, empty);
        }
        return rs;
    }

    public static void main(String[] args) {
        //** Requirement
        //- There are (numBottles) (water bottles) that are initially (full of water).
        //- You can exchange (numExchange) empty water bottles from the market with (one full) water bottle.
        //- The operation of "drinking" (a full water) bottle turns it into (an empty bottle).
        //- Given the two integers "numBottles" and "numExchange",
        //* Return the maximum number of water bottles you can drink.
        //
        //** Idea
        //1.
        //1.0,
        //* Method-1:
        //- Constraint
        //1 <= numBottles <= 100
        //2 <= numExchange <= 100
        //
        //- Brainstorm
        //
        //1.1, Optimization
        //1.2, Complexity
        //- Space: O(1)
        //- Time : O(Log(m)(n))???
        //==> m^time = n
        //Ex:
        //log(4)16 = 2
        //  + 4^2 - 16
        //  + numBottles = x
        //  + numBottles = x
        //      + empty+=numBottles = x
        //      + numBottles = empty/numExchange = x/numExchange
        //      + empty = x%numExchange < x = y
        //  + numBottles = x1
        //      + empty+=numBottles = y + x1
        //      + numBottles = empty/numExchange = (y + x1)/numExchange
        //      + empty = (y + x1)%numExchange = n
        //
        //- numBottles = (numBottles+empty/numExchange)
        //
        int numBottles = 15, numExchange = 4;
        System.out.println(numWaterBottles(numBottles, numExchange));
        //
        //#Reference:
        //2189. Number of Ways to Build House of Cards
        //2960. Count Tested Devices After Test Operations
        //1562. Find Latest Group of Size M
    }
}
