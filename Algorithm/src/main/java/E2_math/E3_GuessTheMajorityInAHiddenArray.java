package E2_math;

public class E3_GuessTheMajorityInAHiddenArray {

    interface ArrayReader {
        public int query(int a, int b, int c, int d);
 
        // Returns the length of the array
        public int length();
    };
    
    public static int guessMajority(ArrayReader reader) {
        int countEqual=0;
        int n=reader.length();
        int cal0123=reader.query(0,1,2,3);
        int indexNotEqual=-1;

        for(int i=4;i<n;i++){
            if(reader.query(0,1,2,i)==cal0123){
                countEqual++;
            }else if(indexNotEqual==-1){
                indexNotEqual=i;
            }
        }
        int call0124=reader.query(0,1,2,4);

        if(call0124==reader.query(1,2,3,4)){
            countEqual++;
        }else{
            indexNotEqual=0;
        }
        if(call0124==reader.query(0,2,3,4)){
            countEqual++;
        }else{
            indexNotEqual=1;
        }
        if(call0124==reader.query(0,1,3,4)){
            countEqual++;
        }else{
            indexNotEqual=2;
        }
        // System.out.printf("%s\n", countEqual+1);
        if((countEqual+1)*2==n){
            return -1;
        }
        return (countEqual+1)*2>n?3:indexNotEqual;
    }

    public static void main(String[] args) {
        // Requirement
        //- We have an integer array nums, where all the integers in nums are 0 or 1.
        // You will not be given direct access to the array, instead, you will have an API ArrayReader which have the following functions:
        //+ int query(int a, int b, int c, int d): where 0 <= a < b < c < d < ArrayReader.length().
        // The function returns the distribution of the value of the 4 elements and returns:
        //+ 4 : if the values of the 4 elements are the same (0 or 1).
        //  + 1=1=1=1 or 0=0=0=0
        //+ 2 : if three elements have a value equal to 0 and one element has value equal to 1 or vice versa.
        //  + 3(1) and 1(0), 3(0), 1(1)
        //+ 0 : if two element have a value equal to 0 and two elements have a value equal to 1.
        //  + 2(0), 2(1)
        //+ int length(): Returns the size of the array.
        //- You are allowed to call query() 2 * n times at most where n is equal to ArrayReader.length().
        //* Return (any index of the most frequent value) in nums, in case of tie, return -1.
        //Ex:
        //Input: nums = [0,0,1,0,1,1,1,1]
        //Output: 5
        //+ reader.query(0,1,2,3)
        //returns 2 this is a query that compares the elements (nums[0], nums[1], nums[2], nums[3])
        //-> 3(1) and 1(0) or 3(0) and 1(1)
        //+ reader.query(4,5,6,7)
        //returns 4 because nums[4], nums[5], nums[6], nums[7] have the same value.
        //-> 1=1=1=1 or 0=0=0=0
        //=> Index 2, 4, 6, 7 is also a correct answer.
        //
        // Idea
        //1.
        //1.0,
        //- Constraint:
        //
        //- Brainstorm
        //Ex:
        //Input: nums = [0,0,1,0,1,1,1,1]
        //Output: 5
        //- call(4,5,6,7) : Các giá trị cuối chỉ có thể là 0/1
        //- call(0,1,2,3) : Thì sẽ có 1 giá trị (=0/1)
        //- Giả sử kết quả không nằm trong (4,5,6,7) và =0
        //  + Tệ nhất là trong 4 cái đầu có 3(0) và 1(1) or tốt nhất là 3(1) và 1(0)
        //  -> return index=4/5/6/7
        //- Giả sử kết quả nằm trong (4,5,6,7) : return index=4/5/6/7
        //- Tức là nếu call mà return ==2 :
        //  + Ta có thể check được xem là ta có thể return được result hay chưa <=> Tệ nhất
        //- Nếu call==0:
        //  + Ta gần như không quyết định thêm được gì
        //- Ta có thể call lần lượt được không?
        //Ex:
        // 0 <-> 2
        //1,0,1,0,[1,1,1,0]
        //or
        //1,0,1,0,[1,1,0,1]
        //or
        //1,0,1,0,[1,0,1,1]
        //or
        //1,0,1,0,[0,1,1,1]
        //- call(0,1,2,3) == 0: không có tác dụng
        //- call (4,5,6,7) == 2: Tức là có 1 value chiếm đa số
        //- call(2,3,4,5)
        //Chỉ có thể:
        //  + ==2 : return 4/5
        //  + ==0 : return 6,7
        //
        // 0 <-> 4
        //1,0,1,0,[1,1,1,1]
        //0,0,1,1,[1,1,1,1]
        //- call(2,3,4,5):
        //Chỉ có thể:
        //  + ==2: return 4 or 5
        //  + ==4: return 4 or 5
        //
        // 2 <-> 4
        //+ return 4
        //1,1,1,0,[1,1,1,1]
        //1,1,0,1,[1,1,1,1]
        //1,0,1,1,[1,1,1,1]
        //0,1,1,1,[1,1,1,1]
        //-> return 4/5/6/7
        //
        //- 0 <-> 2 <-> 4 = 2 <-> 4
        //- 2 <-> 2 <-> 4
        //[1,1,1,0],[1,0,0,0],[1,1,1,1] :
        //+ 2 triệt tiêu nhau nếu ngược
        //or
        //[1,1,1,0],[1,1,1,0],[1,1,1,1]
        //  + return 4,5,6,7
        //or
        //[1,1,1,0],[1,1,1,0],[0,0,0,0]
        //  + Vì count(0)==count(1)==6
        //  + return 4,5,6,7
        //- Khi kết hợp các phần tử trong array thì khá khó đoán:
        //+ Ta thử tư duy theo kiểu call lần lượt thì sẽ ntn:
        //Ex:
        //nums = [0,0,1,0,1,1,1,1]
        //+ call(0,1,2,3) = 2
        //+ call(1,2,3,4) = 0 : 2 - 0
        //  (0),0,1,0,(1) : Cái sau =0 khi 0101 (Thiếu 1 số 0), 1100 (Thiếu số 1)
        //  ==> 0!=4
        //+ call(2,3,4,5) = 2
        //  0 -> 2: 1!=5
        //[0,1,0,1],1
        //+ call(3,4,5,6) = 2
        //+ call(4,5,6,7) = 2
        //
        //- Idea reference:
        //- Ta sẽ so sánh (0,1,2,3) với (1,2,3,4)
        //+ == nhau --> 3==4
        //+ != nhau --> 3<>4
        //- Tương tự ta sẽ xét (0,1,2,3) với (0,1,2,i) : i (4,5,...,n-1)
        //- Key idea ở đây là để group phần tử thứ i thành 2 groups:
        //  + (ith!=3rd) or (ith==3rd)
        //- Và về sau ta cũng sẽ so sánh 0,1,2 với 3
        //  + Để so sánh 3 với 0, ta so sánh (0),1,2,4 vs 1,2,(3),4
        //  + Để so sánh 3 với 1, ta so sánh 0,(1),2,4 vs 0,2,(3),4
        //  + Để so sánh 3 với 2, ta so sánh 0,(1),2,4 vs 0,1,(3),4
        //* KEY ở đây là mỗi giá trị chỉ có thể là 0/1:
        //  + Nên ta có thể dùng giá trị đó để đếm xem có bao nhiêu giá trị như nó
        //- count>n/2: return index của giá trị đó
        //<> index của value khác nó.
        //
        //1.1, Optimization
        //1.2, Complexity
        //- N is length of the array
        //- Space : O(1)
        //- Time : O(N)
    }
}
