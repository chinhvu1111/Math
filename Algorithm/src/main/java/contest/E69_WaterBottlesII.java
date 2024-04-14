package contest;

public class E69_WaterBottlesII {

    public static int maxBottlesDrunk(int numBottles, int numExchange) {
        int emptyBottles = 0;
        int bottlesDrunk = 0;

        while (numBottles != 0 || emptyBottles >= numExchange) {
            if (emptyBottles >= numExchange) {
                numBottles++;
                emptyBottles -= numExchange;
                numExchange++;
            }
            bottlesDrunk += numBottles;
            emptyBottles += numBottles;
            numBottles = 0;
//            System.out.printf("%s %s %s %s\n", numBottles, emptyBottles, numExchange, bottlesDrunk);
        }
        return bottlesDrunk;
    }

    public static void main(String[] args) {
        //* Requirement
        //- You are given two integers numBottles and numExchange.
        //- numBottles represents the number of full water bottles that you initially have.
        // In one operation, you can perform one of the following operations:
        // + Drink any number of full water bottles turning them into empty bottles.
        // + Exchange numExchange empty bottles with one full water bottle. Then, increase numExchange by one.
        //*Note that you cannot exchange multiple batches of empty bottles for the same value of numExchange.
        // For example, if numBottles == 3 and numExchange == 1, you cannot exchange 3 empty water bottles for 3 full bottles.
        //* Return the maximum number of water bottles you can drink.
        //
        // Idea
        //1.
        //1.0,
        //- Constraint:
        //1 <= numBottles <= 100
        //1 <= numExchange <= 100
        //
        //- Brainstorm
        //Ex:
        //numBottles = 13, numExchange = 6
        //Full bottles  | Empty bottles | numExchange  | Bottles Drunk
        //  13               0              6               0
        //- Ta sẽ có xu hướng drink as much as possible --> Exchange empty ==> Full sau đó drink tiếp.
        //
//        int numBottles = 10, numExchange = 3;
        int numBottles = 13, numExchange = 6;
        System.out.println(maxBottlesDrunk(numBottles, numExchange));
    }
}
