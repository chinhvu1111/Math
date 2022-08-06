package leetcode_medium_dynamic;

public class E70_LongestTurbulentSubarray {

    public static int maxTurbulenceSize(int[] arr) {
        int n=arr.length;
        int rs=0;
        int flag=0;
        int temp=1;
        if(n>=1){
            rs=1;
        }

        for(int i=1;i<n;i++){
            boolean gr=arr[i]>arr[i-1];
            boolean lt=arr[i]<arr[i-1];

            if((gr&&(flag==-1||flag==0))){
                temp++;
                flag=1;
            }else if((lt&&(flag==1||flag==0))){
              temp++;
              flag=-1;
            }else if(gr){
                flag=1;
                temp=2;
            }else if(lt){
                flag=-1;
                temp=2;
            }else{
                flag=0;
                temp=1;
            }
            rs=Math.max(rs, temp);
        }

        return rs;
    }

    public static int maxTurbulenceSizeOptimized(int[] arr) {
        if(arr.length == 1 || (arr.length == 2 && arr[0] == arr[1])) return 1;
        if(arr.length == 2) return 2;
        int max = arr[0] != arr[1] ? 2 : 1; // to tackle edge cases for similar arr[i] elements
        int current = max;
        for(int i = 1; i < arr.length - 1; i++)
        {
            if(arr[i-1] > arr[i] && arr[i+1] > arr[i])
                current++;
            else if(arr[i-1] < arr[i] && arr[i+1] < arr[i])
                current++;
            else
            {
                max = Math.max(max,current);
                current = arr[i-1] != arr[i] || arr[i] != arr[i+1] ? 2 : 1; // to make a check if all array elements are same or not
            }
        }
        max = Math.max(max,current);
        return max;
    }

    public static void main(String[] args) {
        int arr[]=new int[]{9,4,2,10,7,8,8,1,9};
//        int arr[]=new int[]{4,8,12,16};
//        int arr[]=new int[]{100};
//        int arr[]=new int[]{9,9};
        System.out.println(maxTurbulenceSize(arr));
        //Kinh nghiệm:
        //Với những bài dạng range 3 --> 4 số như thế này
        //--> Tốt nhất xét điều kiện cả 3/ 4 số --> Thậm chí tốc độ xét nhanh hơn so với việc
        //For flag + if else nhiều lần
        //VD: arr[i-1] > arr[i] && arr[i+1] > arr[i]
        //--> Sẽ nhanh hơn việc chỉ xét điều kiện đôi (arr[i]> arr[i-1]+ flog=1/-1)
        //--> Xét như thế này sẽ chậm hơn vì phải ( (temp++) + check if else nhiều)
    }
}
