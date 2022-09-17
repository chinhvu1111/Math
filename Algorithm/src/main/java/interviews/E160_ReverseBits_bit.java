package interviews;

public class E160_ReverseBits_bit {

    public static int reverseBitsString(int n) {
        char[] s1=String.valueOf(n).toCharArray();
        int left=0;
        int right=s1.length-1;
        int length=s1.length;

        while (left<right){
            char temp=s1[left];

            s1[left]=s1[right];
            s1[right]=temp;
            left++;
            right--;
        }
        return 1;
    }

    public static int reverseBits(int n) {
        int left=0;
        int right=31;

        while (left<right){
            int i=(n>>left)&1;
            int j=(n>>right)&1;

            if(i!=j){
                n=n^(1<<left);
                n=n^(1<<right);
            }
            left++;
            right--;
        }
        return n;
    }

    public static void main(String[] args) {
        int s=343243;
        System.out.println(reverseBits(s));
        //Bài này tư duy như sau:
        //1, Thay vì reverse theo kiểu char[] thông thường
        //Ở đây ta sẽ reverse dựa trên bits của 1 số Integer
        //1.1, 1 số nguyên sẽ được biểu diễn dạng binary:
        //VD:
        //11010101010 : Ta cần reverse nó
        //1.2,
        //Ta cần chú ý 2 thao tác chính khi làm việc với bit:
        //*** Dịch bit sẽ bắt đầu từ vị trí [0]
        //VD: 5: 101 --> 5>>0 = 100 (4)
        //- getBit(x, k) : (x>>k)&1 (k là vị trí bits từ cuối lên)
        //- setBit(x, k) to 1 : x | ( 1<<k )
        //- setBit(x, k) to 0 : x | ( ~ ( 1<<k ) )
        //- clearBit(x,k) : x & ( ~ 1<<k )
        //VD: setBit:
        //000001 (1) << 2
        //=  000100
        //x= 001010
        // x | (1<<2)
        //=  001|1|10
        //---> Đã set bit thứ 2 từ dưới lên của (001010) ---> Thành 1.
        //
        //1,3, Như phép bên trên chỉ hỗ trọ set bit 1
        //--> Muốn flip thì ta sẽ dùng phép XOR
        //- CT: n=n(1<<k) --> Chuyển bit thứ (k) từ dưới lên (0/1) ngược với giả trị hiện tại của bit đó.
    }
}
