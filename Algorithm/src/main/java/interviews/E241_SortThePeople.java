package interviews;

public class E241_SortThePeople {

    public static String[] sortPeople(String[] names, int[] heights) {
        quickSort(heights, names, 0, names.length-1);
        return names;
    }

    public static void quickSort(int[] heights, String[] names, int low, int high){
        if(low<high){
            int pi=partition(heights, names, low, high);
            quickSort(heights, names, low, pi);
            quickSort(heights, names, pi+1, high);
        }
    }

    public static int partition(int[] heights, String[] names, int low, int high){
        int pivot=high;
        int i, j=low;

        for(i=low;i<high;i++){
            if(heights[i]>=heights[pivot]){
                int temp=heights[i];
                heights[i]=heights[j];
                heights[j]=temp;

                String tempS=names[i];
                names[i]=names[j];
                names[j]=tempS;
                j++;

            }
        }
        int temp=heights[pivot];
        heights[pivot]=heights[j];
        heights[j]=temp;

        String tempS=names[pivot];
        names[pivot]=names[j];
        names[j]=tempS;
        return j-1;
    }

    public static void println(int[] nums){
        int n=nums.length;

        for(int i=0;i<n;i++){
            System.out.printf("%s, ", nums[i]);
        }
        System.out.println();
    }

    public static void println(String[] names){
        int n=names.length;

        for(int i=0;i<n;i++){
            System.out.printf("%s, ", names[i]);
        }
        System.out.println();
    }

    public static void main(String[] args) {
        String[] names = new String[]{"Mary","John","Emma","a","b"};
        int []heights = new int[]{1,180,165,170,200};

//        String[] names = new String[]{"Mary","John","Emma"};
//        int []heights = new int[]{180,165,170};
//        String[] names = new String[]{"Alice","Bob","Bob"};
//        int []heights = new int[]{155,185,150};
        quickSort(heights, names, 0, names.length-1);
        println(heights);
        println(names);
        //
        //** Đề bài:
        //- Sắp xếp tên người (names) dựa trên chiều cao (heights) của họ.
        //
        //** Bài này tư duy như sau:
        //Cách 1:
        //1,
        //1.1, Ta dùng quick sort để sắp xếp array
        //120,90,180,165,170,140,200,150
        //- Cần sắp xếp giảm dần
        //arr[pivot]=150
        //Mục đích là partition array thành 2 parts
        //phần bên trái >= value > value
        //
        //—> Swap nếu 120 < 150
        //VD:
        //120,90,180,165,170,140,200,30,150
        //150,90,180,165,170,140,200,30,120
        //150,120,180,165,170,140,200,30,90
        //150,120,180,165,170,140,200,90,30
        //==> Swap này là (sai)
        //
        //- j=0 —> n-1
        //- Tìm i++ cho đến khi tìm được arr[i] > 150
        //Sau đó swap với j
        //+ Swap (180,120) : 180,90,120,165,170,140,200,30,150
        //+ Swap (165,190) : 180,165,120,90,170,140,200,30,150
        //+ Swap (170, 120) : 180,165,170,90,120,140,200,30,150
        //+ Swap (200, 90) : 180,165,170,200,120,140,90,30,150
        //==> swap (90,150) —> Đến cuối rồi mà vẫn không thấy
        //180,165,170,200,120,140,(150),30,90
        //
        //- Khi ta đã có pivot ở giữa rồi --> Chỉ cần sắp xếp 2 nửa left (pivot-1) và right(pivot+1) nữa là xong.
        //
        //Cách 2:
        //2.
        //2.1, Lưu object dạng (height, name) lưu thông tin về
    }
}
