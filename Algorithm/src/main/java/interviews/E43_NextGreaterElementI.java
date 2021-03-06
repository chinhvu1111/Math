package interviews;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Stack;

public class E43_NextGreaterElementI {

    public static int[] nextGreaterElement(int[] nums1, int[] nums2) {
        Stack<Integer> stack=new Stack<>();
        int length=nums2.length;
        int max= Arrays.stream(nums2).max().getAsInt();
        int dp[]=new int[max+1];
        Arrays.fill(dp, -1);

        for(int i=length-1;i>=0;i--){
            while (!stack.isEmpty()&&stack.peek()<nums2[i]){
                stack.pop();
            }
            if(!stack.isEmpty()){
                dp[nums2[i]]=stack.peek();
            }
            stack.push(nums2[i]);
        }
        int rs[]=new int[nums1.length];

        for(int i=0;i<nums1.length;i++){
            rs[i]=dp[nums1[i]];
        }
        return rs;
    }

    public static int[] nextGreaterElementOptimizedMemSpeed(int[] nums1, int[] nums2) {
        Stack<Integer> stack=new Stack<>();
        int length=nums2.length;
        HashMap<Integer, Integer> dp=new HashMap();

        for(int i=length-1;i>=0;i--){
            while (!stack.isEmpty()&&stack.peek()<nums2[i]){
                stack.pop();
            }
            if(!stack.isEmpty()){
                dp.put(nums2[i], stack.peek());
            }
            stack.push(nums2[i]);
        }
        int rs[]=new int[nums1.length];

        for(int i=0;i<nums1.length;i++){
            Integer value=dp.get(nums1[i]);

            if(value==null){
                rs[i]=-1;
            }else{
                rs[i]=value;
            }
        }
        return rs;
    }

    public int[] nextGreaterElementOptimized(int[] nums1, int[] nums2) {
        int ans[]= new int[nums1.length];
        HashMap<Integer,Integer> map= new HashMap<>();
        genz(map,nums2);
        for(int i=0;i<nums1.length;i++)
        {
            ans[i]=map.get(nums1[i]);
        }
        return ans;
    }

    public void genz(HashMap<Integer,Integer> map, int arr[])
    {
        Stack<Integer> stack= new Stack<>();
        int n=arr.length;
        for(int i=n-1;i>=0;i--)
        {
            if(i==n-1)
            {
                map.put(arr[i],-1);
                stack.push(arr[i]);
            }
            else{
                while(!stack.isEmpty() && stack.peek()<=arr[i])
                {
                    stack.pop();
                    map.put(arr[i],-1);;
                }
                if(stack.isEmpty())
                {
                    map.put(arr[i],-1);;
                }
                else
                    map.put(arr[i],stack.peek());
                stack.push(arr[i]);
            }
        }

    }

    public static void main(String[] args) {
//        int nums1[]=new int[]{4,1,2};
//        int nums2[]=new int[]{1,3,4,2};
//        int nums1[]=new int[]{2,4};
//        int nums2[]=new int[]{1,2,3,4};
        int nums1[]=new int[]{1};
        int nums2[]=new int[]{1};
        //B??i n??y t?? duy nh?? sau:
        //Nh??ng b??i thu???c th??? lo???i l???n h??n:
        //- T??m s??? l???n h??n g???n nh???t v???i arr[i] v??? b??n ph???i : arr[j]>arr[i] , j>i, min(j)
        //C??c t?? t?????ng ch??nh nh?? sau
        //1, Ta n??n ch???y t??? (end --> start) , ?????n v??? tr?? (i) s??? t??nh dp[i] c??a v?? tr?? (i)
        //N???u l?? t??? start --> end th?? m???i chuy???n s??? r???c r???i h??n.
        //1.1, Quy t???c nh?? sau:
        //- D??ng stack, ta c???n ph???i x??c ?????nh ???????c TOP.
        //--> ??? ????y top s??? l?? s??? min nh???t c???a stack th???a m??n 2 y???u t???.
        //+ S??? m???i nh???t t??? right sang
        //+ N???u th??m v??o num2[i] v??o ---> N???u n?? l???n h??n b???t c??? ph???n t??? n??o ??? TOP ==> POP ra v??:
        //*** C??c s??? sau nums2[i] ch??? quan t??m ?????n s??? g???n nh???t c??a n?? M?? NUMS2[I] l???n nh???t r???i ==> Ta ho??n to??n c?? th??? POP c??c gi?? tr??? NH??? H??N.
        //2, B??i n??y c?? th??? t???i ??u kh??ng gian nh??? b???ng HashMap
        System.out.println(nextGreaterElement(nums1, nums2));
    }
}
