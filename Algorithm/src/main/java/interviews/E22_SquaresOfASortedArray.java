package interviews;

import java.util.ArrayList;
import java.util.List;

public class E22_SquaresOfASortedArray {

    public static int[] sortedSquares(int[] nums) {
        List<Integer> negativeNumbers=new ArrayList<>();
        List<Integer> postitiveNumbers=new ArrayList<>();

        for(int i=0;i<nums.length;i++){
            if(nums[i]>=0){
                postitiveNumbers.add(nums[i]*nums[i]);
            }
            if(nums[nums.length-i-1]<0){
                negativeNumbers.add(nums[nums.length-i-1]*nums[nums.length-i-1]);
            }
        }
        int iLeft=0;
        int iRight=0;
        int n=postitiveNumbers.size();
        int m=negativeNumbers.size();
        int index=0;

        while (iLeft<n||iRight<m){
            if(iRight<m&&iLeft<n&&postitiveNumbers.get(iLeft)>negativeNumbers.get(iRight)){
                nums[index++]=negativeNumbers.get(iRight++);
            }else if(iLeft<n){
                nums[index++]=postitiveNumbers.get(iLeft++);
            }else{
                nums[index++]=negativeNumbers.get(iRight++);
            }
        }
        return nums;
    }

    public static int[] sortedSquaresOptimize(int[] nums) {
        int rs[]=new int[nums.length];
        int indexPositive=-1;
        int indexNegative=-1;

        for(int i=0;i<nums.length;i++){
            if(nums[i]>=0){
                indexPositive=i;
                break;
            }else{
                indexNegative=i;
            }
        }
        //Positive
        int iLeft=indexPositive;

        if(iLeft==-1){
            iLeft=rs.length;
        }
        int iRight=indexNegative;
//        if(indexPositive>0){
//            iRight=indexPositive-1;
//        }
        int length=nums.length;

        int index=0;

        while (iLeft<length||iRight>=0){
            if(iRight>=0&&iLeft<length&&nums[iLeft]*nums[iLeft]>nums[iRight]*nums[iRight]){
                rs[index++]=nums[iRight]*nums[iRight];
                iRight--;
            }else if(iLeft<length){
                rs[index++]=nums[iLeft]*nums[iLeft];
                iLeft++;
            }else{
                rs[index++]=nums[iRight]*nums[iRight];
                iRight--;
            }
        }
        return rs;
    }

    public int[] sortedSquaresOptimized(int[] nums) {
        int res[]=new int[nums.length]; //Create new Array to return from function
        int last=nums.length-1; //pointer at End
        int start=0; // pointer at start
        int dlast=res.length-1; // pointer at last for resultant array
        while(start<=last)
        {
            if(nums[start]*nums[start]<nums[last]*nums[last])
            {
                res[dlast]=nums[last]*nums[last];
                last--;
                dlast--; }
            else
            {
                res[dlast]=nums[start]*nums[start];
                start++;
                dlast--;
            }
        }
        return res;
    }

    public static void main(String[] args) {
//        int arr[]=new int[]{-4,-1,0,3,10};
        //Case 1 : Basic case khi IndexOutofBound
//        int arr[]=new int[]{-1};
        int arr[]=new int[]{1};

        //C??ch 1: Ch??a t???i ??u v?? (ArrayList copy elements + c?? th??? arrau ???????c t???o ra b??? th???a space)
        sortedSquares(arr);
        //L???i sai:
        //1, while(iLeft<n||iRight<m) : C???n th???n c???n check both indexs khi vi???t while or
        //2 list c?? th??? c?? 1 c??i c?? s??? elements ??t h??n --> C?? th??? g??y ra l???i IndexOutOfBound.

        //C??ch 2:
        //Case 1:
        //iRight (Negative) kh??ng t??m th???y
//        int arr1[]=new int[]{1};
        //Case 2:
        //iRight (Negative) kh??ng t??m th???y
//        int arr1[]=new int[]{-1};
        //L???i:
        //2,
        //C?? th??? b??? l???i li??n quan ?????n:
        //2.1, IndexOutofBound li??n quan ?????n -1 <0
        //while (iLeft<length||iRight>=0) : ??ang x??t iLeft<length --> n???u kh??ng t??m th???y th?? g??n iLeft=length lu??n
        //V?? if ??ang ch??? x??t (iLeft<length) + else (iLeft < length)
        //** C???N PH???I CHECK INDEX "M???I CH???"
        //Ta t?? duy nh?? sau:
        //2.2, V?? b??i n??y li??n quan ?????n (negative/ positive) --> Ta ch??? c???n x??t elements ch??? tr??n array th??i l?? ???????c r???i.
        //==> C??c v??? tr?? s??? c?? 2 lo???i index cho (+ / -)
        //- ??m (-) l?? t??ng d???n --> B??nh ph????ng (^2) l?? gi???m d???n ==> Ta c???n iRight--;
        //- D????ng (+) l?? t??ng d???n --> B??nh ph????ng (^2) l?? gi???m d???n ==> Ta c???n iLeft++;
        //2.3, Sau ???? ta merge 2 array ra 1 new array nh?? b??nh th?????ng.

        //C??ch 3:
        //N???u quan s??t k??? h??n th?? c??c s??? sau khi b??nh ph????ng ^2 --> c?? d???ng (Max1)...(min)....(Max2)
        //===> h???i t??? v??o gi???a --> N??n ta ch??? c???n ch???y 2 pointers 2 ?????u
        //B??i to??n chuy???n th??nh ===> Coi nh?? l?? merge 2 arrays gi???m d???n.
        //** Ch??? c???n g??n index t??? end --> start ==> Ra ???????c array (RESULT).
        int arr1[]=new int[]{-4,-1,0,3,10};
        sortedSquaresOptimize(arr1);
        System.out.println("");
    }
}
