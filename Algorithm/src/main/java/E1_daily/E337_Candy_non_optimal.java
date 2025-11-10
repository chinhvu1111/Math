package E1_daily;

public class E337_Candy_non_optimal {

    public static int candy(int[] ratings) {
        int n=ratings.length;
        int[] candies=new int[n];
//        int prevVal=1;
        int i=1;

        //Ex:
        //1,3,6,(10),5,2,1
        //
        //1,2,3,pointer
        //1,2,3,i=3,(i=4)
        //
        //Ex:
        //4,1,3,6,(10),5,2,1
        //
        //[1,0,2]
        candies[0]=1;
        while(i<n){
            if(ratings[i]>ratings[i-1]){
//                int count=1;
                while (i<n&&ratings[i]>ratings[i-1]){
                    candies[i]=candies[i-1]+1;
//                    System.out.printf("%s, ", count);
//                    count++;
                    i++;
                }
//                System.out.println();
            }else {
                int count=0;
//                List<Integer> values=new ArrayList<>();
                while(i<n&&ratings[i]<=ratings[i-1]){
                    count++;
//                    values.add(count);
                    i++;
//                    System.out.printf("%s, ", count);
                }
//                System.out.println();
//                System.out.println(count);
//                System.out.println(i);
                int j=i;
                int c=1;
                int value=1;
                int min=Integer.MAX_VALUE;
                while(c<=count){
                    candies[j-1]=value;
                    if(ratings[j-1]<ratings[j-2]){
                        value++;
                    }else{
                        value=1;
                    }
                    min=Math.min(min, value);
                    c++;
                    j--;
                }
                candies[j-1]=value;
                int plus;
                if(value<=0){
                    plus=-1*value+1;
                    int t=j;
                    for (int k = 0; k < count; k++) {
                        candies[t-1]+=plus;
                        t++;
                    }
                }
                if(j>=2){
                    if(ratings[j-1]>ratings[j-2]){
                        candies[j-1]=Math.max(candies[j-2]+1, candies[j-1]);
                    }else{
                        candies[j-1]=Math.max(candies[j-2]-1, candies[j-1]);
                    }
                }
            }
        }
        int rs=0;
        for (int j = 0; j < n; j++) {
            rs+=candies[j];
            System.out.printf("%s, ", candies[j]);
        }
        System.out.println();
        return rs;
    }

    public static void main(String[] args) {
        //** Requirement
        //- There are (n children) standing in a line.
        //- Each child is assigned (a rating value) given in (the integer array ratings).
        //- You are (giving candies) to these children subjected to the following requirements:
        //  + Each child must have (at least) (one candy).
        //  + Children with (a higher rating) get (more candies) than their neighbors.
        //* Return (the minimum number of candies) you need to have to distribute the candies to the children.
        //
        //Example 1:
        //
        //Input: ratings = [1,0,2]
        //Output: 5
        //Explanation: You can allocate to the first, second and third child with 2, 1, 2 candies respectively.
        //
        //** Idea
        //1.
        //1.0,
        //- Method-1:
        //+ Constraints:
        //n == ratings.length
        //1 <= n <= 2 * 10^4
        //0 <= ratings[i] <= 2 * 10^4
        //  + Time: O(n*k)
        //
        //* Brainstorm:
        //Ex:
        //ratings = [1,0,4,3,2,3]
        //score = [2,1,3,2,1,2]
        //
        //score = [1,0,2]
        //- (Increase and decrease) rule
        //Ex:
        //ratings = [1,0,4,3,2,3]
        //- (Increase and decrease)
        //  + We don't know (how many unit) we need to decrease
        //  If we have (decrease ==> increase)
        //      + Ex: 5,3,2,3,4,6 | 5,4,3,2,1
        //      ==> (5,3,2) out of scope ==> as small as possible
        //      ==> division = [(3,2,1) ==> (3,4,6) We choose score base on the (ratings) [5,4,3,2,1]
        //- Focus on (the decrease -> increase)
        //  + ==> Get the candy
        //- 2 types:
        //  + 1,4,2
        //  + 5,3,8
        //
        //- Order: increase/decrease
        //Ex: 5,3,2,3,4,6 | 5,4,3,2,1
        //3,2,1|2,3|(6,5,4,3,2,1
        //  + We will mark increase from 1 -> 6
        //  + max(3,5)
        //
        //- Order: decrease/increase
        //
        //
        //1.1, Case
        //
        //
//        int[] ratings = {1,3,6,10,5,2,1};
//        int[] ratings = {1,2,2};
        //1,2,3,4,3,2,1
        //6+10
        //2,1,2
//        int[] ratings = {1,0,2};
        //2,1,2
//        int[] ratings = {1,3,2,2,1};
        //[1, 4, 3, 2, 1]
        //1,0,1
        //1,2,1
        //1,2,3
        //3,2,1
        //==> 1,2,1,2,1
        //
        //[3,2,2,1]
        //[1,0,1]
        //
        //* Special cases:
        int[] ratings = {1,2,87,87,87,2,1};
        //1,2,3,(1),3,2,1
        //- 87,87,87,87
        //  + In the middle, we always fill in 1 value
        //
        //
        //1.2, Optimization
        //
        //
        //1.3, Complexity
        //- Time: O(n)
        //- Space: O(1)
        System.out.println(candy(ratings));
    }
}
