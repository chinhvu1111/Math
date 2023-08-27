package E1_two_pointers;

import java.util.Arrays;

public class E5_MaximumMatchingOfPlayersWithTrainers {

    public static int findTrainer(int[] trainers, int ability, int low, int high){
        if(low>=high-1){
            if(low<trainers.length&&trainers[low]>=ability){
                return low;
            }
            if(high<trainers.length&&trainers[high]>=ability){
                return high;
            }
            return -1;
        }
        int mid=low+(high-low)/2;
        //Finding the trainer has its capacity >= ability but It also has minimum capacity
        int tempRs=0;
        if(trainers[mid]<ability){
            low=mid+1;
        }else{
            tempRs=mid;
            high=mid-1;
        }
        int nextRs=findTrainer(trainers, ability, low, high);
        if(nextRs==-1){
            return tempRs;
        }
        return nextRs;
    }

    public static int matchPlayersAndTrainers(int[] players, int[] trainers) {
        int n=players.length;
        int m=trainers.length;

        Arrays.sort(players);
        Arrays.sort(trainers);

        int low=0, high=m-1;
        int rs=0;

        //Time : O(n)
        for(int i=0;i<n;i++){
            int posValidTrainer;

            if(low<=high){
                //n
                //n/2
                //n/4
                //n/2^(h-1)=1
                //==> 2^(h-1) = n
                //=> h-1= log(n) +1
                //Space : O(log(n))
                //Time : log(n)
                posValidTrainer=findTrainer(trainers, players[i], low, high);
            }else{
                continue;
            }

            if(posValidTrainer!=-1){
                rs++;
                // System.out.printf("Low: %s, high: %s\n", low, high);
                low=posValidTrainer+1;
            }
        }
        return rs;
    }

    public static int matchPlayersAndTrainersTwoPointers(int[] p, int[] t) {
        Arrays.sort(p);
        Arrays.sort(t);
        int i=0,j=0,c=0;

        while(i<p.length&&j<t.length){
            if(p[i]<=t[j]){
                i++;
                j++;
                c++;
            }else{
                j++;
            }
        }
        return c;
    }

    public static void main(String[] args) {
        //** Requirement
        //- Given players, players[i] represents the ability of the ith player.
        //- You are also given a 0-indexed integer array trainers,
        // where trainers[j] represents the (training capacity) of the (jth trainer).
        //- The (ith player) can match with the (jth trainer) if
        // the (player's ability) is less than or equal to the (trainer's training capacity).
        //- Mỗi player chỉ được match tối đa 1 trainer
        //- Mỗi trainer chỉ được match đối đa 1 player
        //* Return max (number of matching) giữa players và trainers <-> Sao cho thoả mãn điều kiện trên.
        //- Số lượng matching
        //
        //** Idea
        //1.
        //1.0,
        //- Constraint
        //1 <= players.length, trainers.length <= 10^5
        //1 <= players[i], trainers[j] <= 10^9
        //- Count nên kết quả không có gì đặc biệt.
        //
        //- Brainstorm
        //- Sort ability of players
        //- Sort training capacity of trainer
        //--> Tìm theo dần dần.
        //- Tìm theo low và high ==> Sẽ được update dần dần.
        //Ex:
        //players = [4,7,9], trainers = [8,2,5,8]
        //Sort players = {4,7,9}
        //Sort trainers = {2,5,8,8}
        //+ i=0, ability=4, (low=0, high=3) ==> j=1 (Do min capacity=5)
        //+ Do không được trùng trainer
        //+ i=1, ability=7, (low=1, high=3) ==> j=2 (Do min capacity=8)
        //--. Update tiếp tục ...
        //
        //1.1, Optimization
        //-
        //1.2, Complexity :
        //- Space : O(log(n))
        //
        //* Method 2:
        //- Bài này ta chỉ cần 2 pointers là xử lý được không cần phải binary search
        //
        //#Reference:
        //925. Long Pressed Name
        //1754. Largest Merge Of Two Strings
        //2071. Maximum Number of Tasks You Can Assign
        int[] players = {4,7,9}, trainers = {8,2,5,8};
        System.out.println(matchPlayersAndTrainersTwoPointers(players, trainers));
    }
}
