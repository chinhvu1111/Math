package interviews;

public class E69_FindFirstAndLastPositionOfElementInSortedArray {

    public static int searchElement(int arr[], int target, int low, int high){
        if(low>high){
            return -1;
        }
        int mid=(low+high)/2;
        int index;

        if(arr[mid]<target){
            index=searchElement(arr, target, mid+1, high);
        }else if(arr[mid]>target){
            index=searchElement(arr, target, low, mid-1);
        }else{
            return mid;
        }
        return index;
    }

    public static int[] searchRange(int[] nums, int target) {
        int rs[]=new int[]{-1, -1};
        int index=searchElement(nums, target, 0, nums.length-1);
        if(index!=-1){
            rs[0]=index;
        }
        int start=-1;
        int end=-1;

        for(int i=index;index!=-1&&i>=0;i--){
            if(nums[i]==nums[index]){
                start=i;
            }else{
                break;
            }
        }
        for(int i=index;index!=-1&&i<nums.length;i++){
            if(nums[i]==nums[index]){
                end=i;
            }else{
                break;
            }
        }
        if(start!=-1){
            rs[0]=start;
        }
        if(end!=-1){
            rs[1]=end;
        }
        return rs;
    }

    public static int searchSubRangeOptimize(int[] nums, int target, boolean isStartIndex){
        int low=0;
        int high=nums.length-1;
        int index=-1;

        while (low<=high){
            int mid=(low+high)/2;

            if(target<nums[mid]){
                high=mid-1;
            }else if(target>nums[mid]){
                low=mid+1;
            }else{
                index=mid;

                if(isStartIndex){
                    high=mid-1;
                }else{
                    low=mid+1;
                }
            }
        }
        return index;
    }

    public static int[] searchRangeOptimize(int[] nums, int target) {
        int start=searchSubRangeOptimize(nums, target, true);
        int end=searchSubRangeOptimize(nums, target, false);

        return new int[]{start, end};
    }

    public static void main(String[] args) {
//        int arr[]=new int[]{5,7,7,8,8,10};
        int arr[]=new int[]{5,7,7,8,8,10};
        int rs[]=searchRange(arr, 8);
//        int rs[]=searchRange(arr, 6);
        System.out.println();
        int rs1[]=searchRangeOptimize(arr, 8);
        System.out.println();
        //B??i n??y t?? duy nh?? sau:
        //??? ????y ta c?? 2 c??ch ????? l??m b??i n??y:
        //C??ch 1:
        //1, Ta s??? t??m 1 index b???t k??? th???a m??n arr[index]=target
        //--> Index ???? c?? th??? n???m ??? ?????u / gi???a <-> arr[index]=target
        //2, Ta s??? loop (left, right) c???a index ????? t??m (start, end)
        //C??ch 2:
        //1, Ta d??ng t??nh ch???t c???a binary search nh?? b??nh th?????ng, ch??? l??:
        //+ T??m (start, end) s??? kh??c nhau ??? b?????c cu???i c??ng
        //+ Khi t??m ???????c index m?? arr[index] = target.
        //--> v???i start --> low=mid-1
        //--> v???i end --> high=mid+1
        //* V?? nh?? th??? th?? ta s??? next d???n gi?? tr??? arr[i] sang left, right gi???ng v???i for b??n tr??n.
    }
}
