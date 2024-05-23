package E1_leetcode_medium_dynamic;

public class E141_AirplaneSeatAssignmentProbability {

    public static double nthPersonGetsNthSeat(int n) {
        return 0;
    }

    public static void main(String[] args) {
        //** Requirement:
        //- (n passengers) board an airplane with exactly (n seats).
        // The first passenger has (lost the ticket) and picks (a seat randomly).
        // But after that, the rest of the passengers will:
        // + Take their own seat if it is still available, and
        // + Pick other seats randomly when they find their seat occupied
        //* Return the probability that the (nth person) gets his own seat.
        //
        //** Tư duy
        //1.
        //1.1, Idea
        //- Constraint
        //1 <= n <= 10^5
        //
        //- Brainstorm
        //* Người thứ nth lấy seat đúng --> Không cần thiết người thứ i < n lấy seat đúng.
        //  + ==> Sai nhưng chéo nhau là được
        //Ex:
        // n=3
        //- Người 1 có 1/3 khả năng lấy seat đúng
        //  Ex:
        //  s: 1,2,3
        //  p: 1
        //  + Còn lại 2 seats đúng => cho 2 người sau
        //- Người 1 có 2/3 khả năng lấy seat sai
        //  Ex:
        //  s: 1,2,3
        //  p:   1
        //  Ex:
        //  s: 1,2,3
        //  p:     1
        //  + Còn 1 seat đúng => cho 2 người sau
        //
        //- 1 seat đúng (1 lấy sai seat):
        //  Ex:
        //  s: 1,2,3
        //  p:   1
        //==>
        //  s: 1,2,3
        //  p: 2,1
        //==> Người 2 chọn sai
        //=> Prob = 2/3*1/2
        //* CT của i khi i tính sai seat + sau đúng:
        //  + prob = (n-k-1)/(n-k) * 1/(n-k-1)
        //  + prob = (Sai)/(total) * 1/(total-1)
        //  Ex:
        //  s: 1,2,3
        //  p:     1
        //==> Ở đây chọn vào cái của người th3
        //==> Không tính cái này
        //
        //- 2 seat đúng (1 lấy đúng seat):
        //  Ex:
        //  s: 1,2,3
        //  p: 1
        //-> Người 2 có thể chọn 1/3*1/2
        //* CT của i khi i tính đúng seat:
        //  + prob = 1/(n-k) * 1/(n-k-1)
        //- Giả sử với n=4
        //- Cases:
        //- 1st
        //+ 1 sai:
        //  Ex:
        //  s: 1,2,3,4
        //  p:   1
        //  Ex:
        //  s: 1,2,3,4
        //  p:     1
        //  Ex:
        //  s: 1,2,3,4
        //  p:       1
        //==> prob = (n-1)/n
        //  + Sai thì kèm theo đằng sau ntn?
        //      + i+1 sai
        //      + i+1 đúng ==> Đều được ==> Gọi chung là prob(3)
        //      => next prob = (n-1)/n * prob(3)
        //+ 1 đúng:
        //  Ex:
        //  s: 1,2,3,4
        //  p: 1 ==> Quay lại (n=3)
        //==> prob = 1/n * prob(3)
        //-> total of prob = (n-1)/n *prob(3) + prob(3)/n = prob(3)
        //=> Prob(3) = prob(2) = 1/2
        //
        //#Reference:
        //1040. Moving Stones Until Consecutive II
        //2209. Minimum White Tiles After Covering With Carpets
        //1483. Kth Ancestor of a Tree Node
    }
}
