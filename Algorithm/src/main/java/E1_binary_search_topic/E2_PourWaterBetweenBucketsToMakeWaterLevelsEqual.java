package E1_binary_search_topic;

public class E2_PourWaterBetweenBucketsToMakeWaterLevelsEqual {

    //** Đề bài:
    //- Có array buckets[] ={2,4,6}
    //- Lost=50
    //+ Tức là nếu rót từ cố i --> j sẽ mất 50%
    //- Muốn lượng nước mỗi buckets là giống nhau --> rót để = nhau ==> return lại lượng nước = nhau đó
    //
    //** Bài này tư duy như sau:
    //1.
    //1.1, Idea
    //- Dữ kiện:
    //- 0<= buckets[i] <=10^5
    //- 1 <= bucket.length <=10^5
    //VD:
    //+ buckets[] ={1,2,7}
    //+ loss=80
    //Giả sử kết quả = 2, thì ta cần bù như sau:
    //+ buckets[]= {1*(1-0.8), 0, -5} ===> tổng = 0 ==> Thoả mãn.
    //--> Điều kiện sum=0 ==> Thoả mãn.
    //- Dữ kiên bài này --> Dùng binary search
    //+ Range quantity of water:
    // min <= r <=max.
    //
    //1.2, Bài này tư duy như sau:
    //- Limit min <= r <=max
    //- Với mỗi mid = low (high-low)/2
    //+ Để riêng sum==0 --> return r
    //+ Tính sum <0 --> Lượng bù không đủ --> low=mid+1
    //+ Sum >0 --> Lượng bù có thể đủ --> high=mid.
    //
    public static double equalizeWater(int[] buckets, int loss) {
        if(buckets.length==1){
            return buckets[0];
        }
        int min=Integer.MAX_VALUE;
        int max=Integer.MIN_VALUE;
        int n=buckets.length;
        double e=1e-5;

        for (int bucket : buckets) {
            min = Math.min(min, bucket);
            max = Math.max(max, bucket);
        }
        double low=min;
        double high=max;

        while (low<=high){
            double mid=low+(high-low)/2;

            double currentV=sumAlls(buckets, mid, loss);
//            System.out.printf("%s %s\n", mid, currentV);

            //Để ntn vẫn đúng
//            if(0<=currentV&&currentV<e){
//                return mid;
//            }else
            if(currentV<0){
                high=mid-e;
            }else{
                low=mid+e;
            }
        }
        return low;
    }

    public static double sumAlls(int[] buckets, double value, double loss){
        int n=buckets.length;
        double sum=0;

        for (int bucket : buckets) {
            double currentVal=0;

            if(bucket - value<=0){
                currentVal=(bucket - value) /( (100 - loss) / 100);
            }else{
                currentVal=(bucket - value);
            }
            sum += currentVal;
//            System.out.println(currentVal);
        }
        return sum;
    }

    public static void main(String[] args) {
//        int[] buckets = {1,2,7};
//        int loss = 80;
        int[] buckets = {2,4,6};
        int loss = 50;
//        int[] buckets = {0};
//        int loss = 0;
//        System.out.println(sumAlls(buckets, 2, loss));
        System.out.println(equalizeWater(buckets, loss));
    }
}
