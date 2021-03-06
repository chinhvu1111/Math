package interviews;

import java.util.HashMap;

public class E58_ContinuousSubarraySum {

    public static boolean checkSubarraySum(int[] nums, int k) {
        int n=nums.length;
        HashMap<Integer, Integer> hashMap=new HashMap<>();
        int sum=0;

        for(int i=0;i<n;i++){
            nums[i]=nums[i]%k;
            sum+=nums[i];
            int numberMultiple=sum/k;
            int currentSum=sum;

//            if((sum%k==0&&!hashMap.isEmpty())||hashMap.containsKey(sum)){
//                return true;
//            }
            if(hashMap.containsKey(sum)&&hashMap.get(sum)!=i-1
                    || (sum%k==0&&hashMap.size()>=1)
//                    || (sum%k==0&&hashMap.size()>1&&nums[i]==0)
            ){
                return true;
            }

            for(int j=1;j<=numberMultiple;j++){
//                int value=currentSum-j*k;
                currentSum-=k;

                if(hashMap.containsKey(currentSum)){
                    return true;
                }
            }
            if(!hashMap.containsKey(sum)){
                hashMap.put(sum, i);
            }
        }
        return false;
    }

    public static boolean checkSubarraySumOptimized1(int[] nums, int k) {
        int n=nums.length;
        HashMap<Integer, Integer> hashMap=new HashMap<>();
        int sum=0;

        for(int i=0;i<n;i++){
            nums[i]=nums[i]%k;
            sum+=nums[i];
            int numberMultiple=sum/k;
            int currentSum=sum;
            boolean isContainsSum=hashMap.containsKey(sum);

            if(isContainsSum&&hashMap.get(sum)!=i-1
                    || (sum%k==0&&hashMap.size()>=1)
//                    || (sum%k==0&&hashMap.size()>1&&nums[i]==0)
            ){
                return true;
            }

            for(int j=1;j<=numberMultiple;j++){
                currentSum-=k;

                if(hashMap.containsKey(currentSum)){
                    return true;
                }
            }
            if(!isContainsSum){
                hashMap.put(sum, i);
            }
        }
        return false;
    }

    public static boolean checkSubarraySumOptimized2(int[] nums, int k) {
        int n=nums.length;
        HashMap<Integer, Integer> hashMap=new HashMap<>();
        int sum=0;

        for(int i=0;i<n;i++){
            sum+=nums[i];
            int currentSum=sum%k;
            boolean isConstainKey=hashMap.containsKey(currentSum);

            if(isConstainKey&&hashMap.get(currentSum)!=i-1){
                return true;
            }
            if(currentSum%k==0&&hashMap.size()>=1){
                return true;
            }
            if(!isConstainKey){
                hashMap.put(currentSum, i);
            }
        }
        return false;
    }

    public static void main(String[] args) {
//        int nums[]=new int[]{23,2,6,4,7};
        //D???ng b??i n??y ta t?? duy nh?? sau :
        //C??ch 1: N???u qu??n t??nh ch???t c???a vi???c %
        //0,
        //0.1, length=10^5
        //--> D???ng n??y ch??? c?? th??? x??? l?? v???i Complexity (O(n)) ho???c O(n*k)....
        //0.2, 0 <= nums[i] <= 10^9
        //--> V???i s??? l???n nh?? th??? n??y ==> Ch??? c?? th??? d??ng hashMap stored <-> Kh??ng th??? d??ng arrays
        //0.3, Nh???ng b??i li??n quan ?????n multiple of k
        //--> Ta c?? th??? s??? d???ng % (L???y d??)
        //==> Li??n quan ?????n (a%b==0) ---> Ta c?? th??? d??ng ph????ng th???c x=x%k
        //==> ????? gi???m range cho x

        //1, B??i n??y ban ?????u ta ???? ngh?? ?????n vi???c for 2 l???n (n) --> Slow
        //1.1, ??? khi ta c?? 1 sum r???i th?? ta s??? t??nh b???i c???a sum hi???n t???i
        //VD: currentSum=23, k=7
        //0 <= multiply <= 3 (3.2)
        //--> L??c ???? ta s??? tr??? d???n cho k
        //1.2, ??? ????y ta s??? ??p d???ng t?? duy prefix_sum
        //--> L??u t???ng t???t c??? c??c ph???n t??? elements cho ?????n v??? tr?? (i)
        //+ M???i l???n currentSum -= k
        //--> Ta s??? check currentSum exists hay kh??ng? --> n???u t???n t???i r???i
        //VD: prefix[i]- prefix[j]== h * k
        //==> T???c l?? sum( elements[j+1 --> i] ) th???a m??n return true.

        //2, T???i sao kh??ng d??ng Arrays --> L???i d??ng HashMap
        //--> V?? sum l???n c?? th??? l??n d???n k*10^5
        //M?? 1 <= k <= 231 - 1
        //--> N??n ta l??u hashMap ????? gi???m kh??ng gian nh??? (max= 10^5 spaces).

        //3, ??? ????y ta t???n nums[i]=nums[i]%k
        //--> V?? mu???n gi???m s??? currentSum ==> Gi???m (currentSum/k) -- numberMultiple
        //--> S??? l???n loop s??? gi???m.

        //4, Ch?? ?? v???i case d???ng
        //VD: 5,0,0,0
        //--> sum=5 ???? xu???t hi???n r???i
        //+ Ta c???n l??u index c???a hashMap tr?????c ???? ????? check xem ???? t???n t???i hay ch??a --> Sum(elements)=0
        //+ Ta ch??? l??u index c???a ph???n t??? xu???t hi???n ?????u ti??n th??i --> ???? t???n t???i th?? kh??ng push v??o hashMap n???a --> ????? ?????m b???o index s??? lu??n l?? firstIndex.

        //4.1, C???n tr??nh c??c cases ?????c bi???t :
//        int nums[]=new int[]{23,2,4,6,6};
        // % : 2, 2, 4, 6, 6.
        //Case 1 :
        //*** Case ?????c bi???t li??n quan ?????n
        //+ HashMap.containsKey( currentSum) ==true
        //--> T???c l?? ???? included currentSum r???i.
        //+ Kh??ng tr??? k g?? c??? --> return true
        int nums[]=new int[]{5,0,0,0};
//        int nums[]=new int[]{0};
        //Case 2: Case n??y sum =1 = t???i nums[1]=0
        //-->
//        int nums[]=new int[]{1,0};
        //Output : true
        //Expected : false
        //Case 3: Case n??y ng????c v???i case [0,1]
        //V?? case [0,1]
//        int nums[]=new int[]{2,4,3};
        //Case 4: Case n??y khi sum=0 v?? hashMap.size()==1
//        int nums[]=new int[]{0,0};
        System.out.println(checkSubarraySum(nums, 7));
//        System.out.println(checkSubarraySum(nums, 6));

        //C??ch 2: T??nh ch???t c???a nh???ng b??i % nh?? th??? n??y nh?? sau:
        //1, Ta s??? ch??? l??u hash c???a (% s??? d??)
        //hashMap.put(currentSum%k, i)
        //N???u b???t k??? 1 sum n??o ???? t??nh sau % k --> ???? t???n t???i
        //--> T???ng c??c ph???n t??? ??? gi???a s??? %k==0 --> return true.
        //2, v?? c?? case:
        //[5,0,0,0] nh?? tr??n --> Ta s??? l??u index ????? check + ki???m tra tr??ng put(element) to HashMap.
        System.out.println(checkSubarraySumOptimized2(nums, 7));

    }
}
