package leetcode_medium_greedy;

public class E5_ContainerWithMostWater {

    public static int maxArea(int[] height) {
        int n=height.length;
        int start=0;
        int end=n-1;
        int rs=0;

        while(start<=end){
            if(height[start]>height[end]){
                rs=Math.max(rs, height[end]*(end-start));
                end--;
            }else{
                rs=Math.max(rs, height[start]*(end-start));
                start++;
            }
        }
        return rs;
    }

    public static void main(String[] args) {
//        int height[]=new int[]{1,8,6,2,5,4,8,3,7};
        int height[]=new int[]{1,1};
        //Bài này là 1 dạng bài cũ
        //Ta tư duy như sau:
        //- Step 1: Nếu ta tư duy theo so sánh (height[i] vs height[j])
        //--> Thì 2 pointer gần như tường tự nhau --> Nếu bỏ qua cách tính bình thg
        //VD : + thêm phần nhỏ hơn --> Việc so sánh là vô nghia và giống hệt nhau
        //--> Ta sẽ thay đổi tư duy về cách tính <--> Hoặc chí ít là (Tính ngược hoàn toàn nhau)
        //**, Cho dù thế nếu tính (i, j) độc lập --> Vẫn không thể dẫn đến cách làm đúng
        //---> Không có quy luật nào ở đây.
        //L1 :
        //
        System.out.println(maxArea(height));
    }
}
