package E1_daily;

import java.util.*;

public class E28_HandOfStraights {

    public static boolean isNStraightHand(int[] hand, int groupSize) {
//        int n=hand.length;
        //Time : O(n*log(n))
        //Space: O(n)
        Arrays.sort(hand);
        HashMap<Integer, Integer> mapCount = new HashMap<>();

        //Time: O(n)
        for (int a : hand) {
            mapCount.put(a, mapCount.getOrDefault(a, 0) + 1);
        }
        List<Integer> distinctVals = new ArrayList<>(mapCount.keySet());
        //Time : O(n*log(n))
        Collections.sort(distinctVals);
        //Space : O(n)
        HashMap<Integer, Integer> valToIndex = new HashMap<>();

        //Time : O(n)
        for (int i = 0; i < distinctVals.size(); i++) {
            valToIndex.put(distinctVals.get(i), i);
        }
        //Time : O(n)
        //Space: O(n)
        Queue<Integer> queueCount = new LinkedList<>(distinctVals);
//        System.out.println(queueCount);
//        System.out.println(valToIndex);
        //Time : O(n) -> O(k)
        while (!queueCount.isEmpty()) {
            Integer curVal = queueCount.peek();
            Integer curIndex = valToIndex.get(curVal);

            if (!mapCount.containsKey(curVal)) {
                queueCount.poll();
                continue;
            }
//            System.out.println(curVal);
            if (mapCount.get(curVal) == 1) {
                mapCount.remove(curVal);
                queueCount.poll();
            } else {
                mapCount.put(curVal, mapCount.get(curVal) - 1);
            }
            if (curIndex + groupSize - 1 >= distinctVals.size()) {
                return false;
            }
            //Time : O(m)
            for (int i = 1; i < groupSize; i++) {
                int tempCurVal = distinctVals.get(i + curIndex);
                if (tempCurVal - 1 != distinctVals.get(i + curIndex - 1)) {
                    return false;
                }
                if (!mapCount.containsKey(tempCurVal)) {
                    return false;
                }
                if (mapCount.get(tempCurVal) == 1) {
                    mapCount.remove(tempCurVal);
                } else {
                    mapCount.put(tempCurVal, mapCount.get(tempCurVal) - 1);
                }
            }
        }
        return true;
    }

    public static boolean isNStraightHandRefer(int[] hand, int groupSize) {
        int handSize = hand.length;

        if (handSize % groupSize != 0) {
            return false;
        }
        // TreeMap to store the count of each card value
        Map<Integer, Integer> cardCount = new TreeMap<>();

        for (int i = 0; i < handSize; i++) {
            cardCount.put(hand[i], cardCount.getOrDefault(hand[i], 0) + 1);
        }
        // Process the cards until the map is empty
        while (cardCount.size() > 0) {
            // Get the smallest card value
            int current_card = cardCount.entrySet().iterator().next().getKey();
            // Check each consecutive sequence of groupSize cards
            for (int i = 0; i < groupSize; i++) {
                // If a card is missing or has exhausted its occurrences
                if (!cardCount.containsKey(current_card + i)) return false;
                cardCount.put(
                        current_card + i,
                        cardCount.get(current_card + i) - 1
                );
                // Remove the card value if its occurrences are exhausted
                if (cardCount.get(current_card + i) == 0) cardCount.remove(
                        current_card + i
                );
            }
        }
        return true;
    }

    public static boolean isNStraightHandReferOptimization(int[] hand, int groupSize) {
        //Space: O(n)
        HashMap<Integer, Integer> mapCount=new HashMap<>();

        //Time: O(n)
        for(int e: hand){
            mapCount.put(e, mapCount.getOrDefault(e, 0)+1);
        }
        //Time : O(n)
        for(int e: hand){
            int startCard=e;

            while(mapCount.containsKey(startCard-1)){
                startCard--;
            }
            //- Delete all streak ntn?
            //Ta thấy rule:
            //  + Khi xét decremental:
            //      + If không tồn tại (a-1) --> stop
            //      ==> Sẽ luôn tồn tại (startCard+1)
            //- Mỗi lần loop ta sẽ tìm cách giảm count cho đến (khi == 0) của each element
            //  + startCard --> e
            //
            //Time: Total=O(n)
            while(startCard<=e){
                while(mapCount.containsKey(startCard)){
                    //Time: O(m)
                    for(int i=startCard;i<startCard+groupSize;i++){
                        if(!mapCount.containsKey(i)){
                            return false;
                        }
                        if (mapCount.get(i)==1) {
                            mapCount.remove(i);
                        }else{
                            mapCount.put(i, mapCount.get(i)-1);
                        }
                    }
                }
                startCard++;
            }
        }
        //Remove
        return true;
    }

    public static void main(String[] args) {
        //** Requirement
        //- Alice has some number of cards and she wants to rearrange the (cards) into (groups) so that (each group) is of (size groupSize),
        // and consists of (groupSize consecutive cards).
        //- Given an integer array hand where hand[i] is the value written on (the ith card) and (an integer groupSize),
        //* Return true if (she can rearrange the cards), or false otherwise.
        //
        //** Idea
        //1.
        //1.0,
        //* Method-1:
        //- Constraint:
        //1 <= hand.length <= 10^4
        //0 <= hand[i] <= 10^9
        //1 <= groupSize <= hand.length
        //==> Max value khá nhiều >= 10^9
        //--> Nên xử lý theo (length của hand)
        //
        //- Brainstorm
        //
        //Example 1:
        //Input: hand = [1,2,3,6,2,3,4,7,8], groupSize = 3
        //Output: true
        //Explanation: Alice's hand can be rearranged as [1,2,3],[2,3,4],[6,7,8]
        //[1,2,3]
        //  [2,3,4]
        //         [6,7,8]
        //- Dùng slide window được không?
        //Ex:
        //hand = [1,2,2,3,3,4,6,7,8]
        //[1,2,3]
        //[2,3,4]
        //- Nếu qua [1,2,3] ==> Nó sẽ giảm số lần xuất hiện 2,3 đi 1 lần
        //- Ta có thể swap được không?
        //  + Ví dụ:
        //  [1,2,2,3,3,4] : swap(2,3)
        //= [1,2,3,2,3,4]
        //- Nếu count(2)>3 thì sao?
        //  E.g:
        //  [1,2,2,2,3,3,3,4,4]
        //-> [1,2,3],[2,3,4],[2,3,4]
        //  [1,2,2,2,3,3,3,4,4]
        //  [1,2,(2),2,(3),3,3,4,4] : swap(2,3)
        //      + [1,2,(3),2,(2),3,3,4,4]
        //  [1,2,3,2,(2),(3),3,4,4]: swap(2,3)
        //      + [1,2,3,2,3,2,3,4,4]
        //==> Có vẻ chưa đúng vì swap có thể dẫn đến (order tăng dần không được keep nữa).
        //- 3 pointers?
        //  E.g:
        //  [1,  2, 2,2, 3,3,3,4,4]
        //  (0),(1),2,3,(4)
        //val:   1,2,3,4
        //count: 1,3,3,2
        //==> Update 3 pointers.
        //==> Nhưng cách này chỉ work với group size hữu hạn
        //- Nếu muốn dynamic group size thì sao?
        //
        //1.1, Optimization
        //- Ta có thể không dùng queue ==> Chỉ dùng TreeMap
        //==> Mỗi turn loop để lấy first element:
        //  + Ta có thể sẽ get từ iterator ra từ element.
        //
        //1.2, Complexity
        //- K is the number of distinct numbers in the hand
        //- Space: O(n)
        //- Time: O(n*log(n) + k*m)
        //
        //2.
        //2.0,
        //* Brainstorm
        //- Idea ở đây là thay vì đi từ smallest để remove từng streak thì:
        //  + Ta có thể remove 1 số streak mà không cần sort
        //  Ex:
        //  hand = [3, 2, 1, 5, 6, 7, 7, 8, 9], k = 3
        //  5 không có 4 đằng sau ==> Ta có thể remove được streak ngay đằng trước (5,6,7)
        //- Ta có thể check được cả (start or end) streak nhưng để đơn giản:
        //  Ex:
        //  hand = [3, 2, 1, 5, 6, 7, 7, 8, 9], k = 3
        //  Có thể remove (7,8,9) vì sau 9 không có 10
        //  + Ta sẽ check 1 đầu
        //- Ta sẽ dần dần remove cho đến khi không thể remove được nữa.
        //- Để check exists + delete : Vẫn cần map count
        //- Loop ntn?
        //- Nếu loop theo each hand trong hands thì sao?
        //  + Mỗi hand có thể loop về previous element --> Lấy các element (start of the streak) có thể remove được.
        //- Nếu tìm được (starting element) của streak rồi ==> Ta có thể bắt đầu xoá từ đó được.
        //* Nếu loop với mỗi hand ntn:
        //  + Nó sẽ đảm bảo rằng tất cả các streaks trước (starting element) sẽ bị removed trước đó rồi.
        //
        //- Delete all streak ntn?
        //Ta thấy rule:
        //  + Khi xét decremental:
        //      + If không tồn tại (a-1) --> stop
        //      ==> Sẽ luôn tồn tại (startCard+1)
        //- Mỗi lần loop ta sẽ tìm cách giảm count cho đến (khi == 0) của each element
        //  + startCard --> e
        //
        //1.1, Optimization
        //1.2, Complexity
        //- N is the size of hand
        //- M is the groupSize variable
        //- Space: O(n)
        //- Time: O(n*m)
        //
//        int[] hand = {1,2,3,6,2,3,4,7,8};
//        int groupSize = 3;
//        int[] hand = {1,2,2,3,3,3,4,4,5};
//        int groupSize = 4;
        int[] hand = {53, 78, 62, 108, 83, 56, 66, 110, 49, 104, 117, 123, 86, 131, 94, 107, 84, 103, 42, 127, 100, 50, 55, 97, 81, 93, 71, 45, 63, 39, 91, 87, 129, 126, 84, 125, 73, 95, 116, 47, 106, 52, 121, 54, 38, 68, 69, 76, 89, 90, 57, 67, 86, 114, 64, 87, 79, 92, 115, 60, 51, 105, 132, 101, 59, 130, 44, 85, 80, 82, 48, 65, 128, 102, 74, 61, 40, 46, 98, 111, 109, 119, 72, 43, 112, 120, 58, 113, 77, 88, 41, 118, 75, 85, 124, 122, 96, 83, 99, 70};
        int groupSize = 50;
        System.out.println(isNStraightHand(hand, groupSize));
        System.out.println(isNStraightHandRefer(hand, groupSize));
        System.out.println(isNStraightHandReferOptimization(hand, groupSize));
        //
        //#Reference:
        //1509. Minimum Difference Between Largest and Smallest Value in Three Moves
        //1918. Kth Smallest Subarray Sum
        //2015. Average Height of Buildings in Each Segment
        //561. Array Partition
        //2335. Minimum Amount of Time to Fill Cups
        //2780. Minimum Index of a Valid Split
    }
}
