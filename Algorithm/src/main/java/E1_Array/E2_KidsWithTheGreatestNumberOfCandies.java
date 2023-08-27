package E1_Array;

public class E2_KidsWithTheGreatestNumberOfCandies {
    public static void main(String[] args) {
        //** Requirement
        //- Có n childrens, children ith has candies[i] candies
        //- Extra candies is the number of candies you want to give them
        //* return res[i]:
        //+ True if : candies[i] + extra > the number of candies that remaining of each children have
        //+ False : otherwise
        //
        //** Idea
        //1.
        //1.0,
        //- Constraint
        //n == candies.length
        //2 <= n <= 100
        //1 <= candies[i] <= 100
        //1 <= extraCandies <= 50
        //
        //- Brainstorm
        //- Sort array and compare to Max value of array when changing.
        //
        //1.1, Optimization
        //- Có thể optimization xuống O(N)
        //https://leetcode.com/problems/maximum-or/solutions/3520489/o-1-space-keep-track-of-bits-super-simple-to-understand-and-detailed-explanation/
        //
        //
        //1.2, Complexity:
        //- Space : O(N)
        //- Time : O(NLog(N))
        //
        //2.0,
        //2.1, How
        //- Find max value --> compare
        //
        //#Reference:
        //605. Can Place Flowers
        //2680. Maximum OR
        //2656. Maximum Sum With Exactly K Elements
        //573. Squirrel Simulation
        //302. Smallest Rectangle Enclosing Black Pixels
        //782. Transform to Chessboard
        //1452. People Whose List of Favorite Companies Is Not a Subset of Another List
        //1457. Pseudo-Palindromic Paths in a Binary Tree
        //902. Numbers At Most N Given Digit Set
        //994. Rotting Oranges
    }
}
