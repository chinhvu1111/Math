package interviews;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Stack;

public class E43_NextGreaterElementI_stack {

    public static int[] nextGreaterElement(int[] nums1, int[] nums2) {
        Stack<Integer> stack=new Stack<>();
        int length=nums2.length;
        int max= Arrays.stream(nums2).max().getAsInt();
        int dp[]=new int[max+1]
                ;
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
        //Bài này tư duy như sau:
        //Nhưng bài thuộc thể loại lớn hơn:
        //- Tìm số lớn hơn gần nhất với arr[i] về bên phải : arr[j]>arr[i] , j>i, min(j)
        //Các tư tưởng chính như sau
        //1, Ta nên chạy từ (end --> start) , đến vị trí (i) sẽ tính dp[i] cùa ví trí (i)
        //Nếu là từ start --> end thì mọi chuyện sẽ rắc rối hơn.
        //1.1, Quy tắc như sau:
        //- Dùng stack, ta cần phải xác định được TOP.
        //--> Ở đây top sẽ là số min nhất của stack thỏa mãn 2 yếu tố.
        //+ Số mới nhất từ right sang
        //+ Nếu thêm vào num2[i] vào ---> Nếu nó lớn hơn bất cứ phần từ nào ở TOP ==> POP ra vì:
        //*** Các số sau nums2[i] chỉ quan tâm đến số gần nhất cùa nó MÀ NUMS2[I] lớn nhất rồi ==> Ta hoàn toàn có thể POP các giá trị NHỎ HƠN.
        //2, Bài này có thể tối ưu không gian nhớ bằng HashMap
        System.out.println(nextGreaterElement(nums1, nums2));
    }
}
