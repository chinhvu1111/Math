package contest;

import java.util.HashMap;

public class E2_MinimumConsecutiveCardsToPickUp {
    public static int minimumCardPickup(int[] cards) {
        HashMap<Integer, Integer> integerHashMap=new HashMap<>();
        int rs=Integer.MAX_VALUE;
        int n=cards.length;

        for(int i=0;i<n;i++){
            if(integerHashMap.containsKey(cards[i])){
                rs=Math.min(rs, i-integerHashMap.get(cards[i])+1);
            }
            integerHashMap.put(cards[i], i);
        }
        if(rs==Integer.MAX_VALUE){
            return -1;
        }
        return rs;
    }

    public static void main(String[] args) {
//        int arr[]=new int[]{3,4,2,3,4,7};
//        int arr[]=new int[]{1,0,5,3};
        int arr[]=new int[]{70,37,70,41,1,62,71,49,38,50,29,76,29,41,22,66,88,18,85,53};
        System.out.println(minimumCardPickup(arr));
    }
}
