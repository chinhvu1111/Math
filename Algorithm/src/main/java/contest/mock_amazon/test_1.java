package contest.mock_amazon;

public class test_1 {

    public static int dietPlanPerformance(int[] calories, int k, int lower, int upper) {
        int totalCalories=0;
        int n=calories.length;
        if(k>n){
            return 0;
        }
        int rs=0;

        //- k>n ==> return 0
        for(int i=0;i<k;i++){
            totalCalories+=calories[i];
        }
        if(totalCalories<lower){
            rs--;
        }else if(totalCalories>upper){
            rs++;
        }
        int low=0;

        for(int i=k;i<n;i++){
            totalCalories=totalCalories-calories[low]+calories[i];
            rs+=getPoint(totalCalories, lower, upper);
            low++;
        }
        return rs;
    }

    public static int getPoint(int calories, int lower, int upper){
        if(calories<lower){
            return -1;
        }else if(calories>upper){
            return 1;
        }
        return 0;
    }

    public static void main(String[] args) {
        //** Requirement
        //- Given calories[], calories[i] thể hiện số calories mà người đó consumer trong ngày thứ i
        //- T là tổng lượng calories mà dieter tiệu thụ trong k ngày
        //+ T < lower : Tiêu thụ kém -> Lose 1 point
        //+ T > upper : Tiêu thụ tốt -> Có thể 1 point
        //* Return lại tổng số T lượng calories tiệu thụ sau calories.length days
        //
        //** Idea
        //1.
        //1.0, Idea
        //- Constraint
        //1 <= k <= calories.length <= 10^5
        //0 <= calories[i] <= 20000
        //0 <= lower <= upper
        //20000* 10^5 ==> OK
        //
        //- Slide windows
        //- low, high và dịch dần:
        //+ low++, high++
        //+ Trừ đi thằng bên left + thằng bên right
        //
//        int[] calories = {6,5,0,0};
//        int k = 2, lower = 1, upper = 5;
        int[] calories = {3,2};
        int k = 2, lower = 0, upper = 1;
        System.out.println(dietPlanPerformance(calories, k, lower, upper));
    }
}
