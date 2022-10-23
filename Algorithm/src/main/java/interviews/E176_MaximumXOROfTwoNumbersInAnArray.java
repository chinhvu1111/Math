package interviews;

public class E176_MaximumXOROfTwoNumbersInAnArray {
    //Time complexity: O(32) , 32 là chiều cao của trie
    //==> Tìm cái khác nhất so với nums[i] : num ==> num xor nums[i] là max nhất
    ///- Phép xor thì khác nhau ==> ra 1 --> kết quả lớn nhất <>
    //- đi từ start --> end (Start sẽ được ưu tiên hơn start+i)
    //
    //- Bit : 0,1 ==> children[2]
    //- Nếu không tìm được ~ bit --> Đành phải chọn sang bit.
    //
    //Bài tập:
    //1, String match --> hash cab
    //2, trie
    //3, K divisible Elements
    //-->
    //4, Nghĩ hàm hash để kiểm tra
    //Bottle neck ==> slide window (Để tìm subarray) + tìm array distict (Hàm hash: faster)
    //5, Trả lại từ word ==> prefix nằm trong tập bàn đầu
    //prefix --> trie
    //6, Stream of characters:
    //--> trie : build ngược.
    public static void main(String[] args) {

    }
}
