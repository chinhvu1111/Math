package interviews;

public class E9_CanPlaceFlowers {

    public static boolean canPlaceFlowers(int[] flowerbed, int n) {
        int rs=0;
        int length=flowerbed.length;
        int start=0;

        while (start<length){
            if(flowerbed[start]==1){
                start++;
            }else if(flowerbed[start]==0&&(start-1<0||flowerbed[start-1]==0)&&(start+1>length-1||flowerbed[start+1]==0)){
                rs++;
                flowerbed[start]=1;
                start+=2;
            }else {
                start++;
            }
            if(rs>=n){
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int arr[]=new int[]{1,0,0,0,0,1};
        //Đề bài:
        //Input: flowerbed = [1,0,0,0,1], n = 2
        //Output: false
        //- Không được trồng cây cạnh nhau (1 planted, 0 blank)
        //- Trả lại kết quả liệu có trồng được không (true/ false)
        //Bài này tư duy như sau:
        //1, Bài này plant từ đầu đến cuối rs++, if (rs> n) return false,
        //<> out of loop : return true.
        System.out.println(canPlaceFlowers(arr, 2));
    }
}
