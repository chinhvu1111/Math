package interviews;

public class E253_MaximumXOROfTwoNumbersInAnArray_radix_search_tree {

    public static class RSTNode{
        public int value;
        RSTNode[] children;

        public RSTNode() {
            children=new RSTNode[2];
        }

        public int getValue() {
            return value;
        }

        public RSTNode[] getChildren() {
            return children;
        }
    }

    public static int findMaximumXOR(int[] nums) {
        RSTNode root=new RSTNode();

        for(int value:nums){
            insert(root, value);
        }
        int result=0;

        for(int num:nums){
//            System.out.println(num);
            result=Math.max(result, num^getXor(root, num));
        }
        return result;
    }

    public static void insert(RSTNode root, int value){
        RSTNode node=root;

        //Adding new node to Trie
        //- Vẫn có value được gán vào giá trị cuối cùng.
        //- Chỉ có path được gán chính là binary của 1 số nào đó.
        //(x) --1--> (x)
        for(int i=31;i>=0;i--){
            int bit=(value>>i)&1;
            RSTNode children=node.getChildren()[bit];

            //Nếu ở vị tri (bit) không có (value)
            if(children==null){
                children=new RSTNode();
                node.children[bit]=children;
            }
            node=children;
//            System.out.println(bit);
//            System.out.printf("%s %s\n", i, bit);
        }
        node.value=value;
    }

    public static int getXor(RSTNode root, int value){
        RSTNode node=root;

        //Phép xor có ct như sau:
        //- 1 xor 1=0
        //- 1 xor 0=1
        //- 0 xor 0=0
        //* ==> Chỉ có những bit khác nhau thì mới làm thay đổi giá trị của 1 bit.
        //VD:
        //5 = 101 ==> Sẽ chọn số mà có giá trị càng ngược càng tốt để tăng lên được giá trị lên MAX
        //--> 010 ==> 2 hoặc nhiều số 1 nhất có thể.
        for(int i=31;i>=0;i--){
            int bit=(value>>i)&1;
//            System.out.printf("%s %s\n", i, bit);
//            System.out.println(node);
            RSTNode children=node.getChildren()[1-bit];

            if(children==null){
                children=node.getChildren()[bit];
            }
            node=children;
        }
        return node.value;
    }
    public static void main(String[] args) {
        int[] arr=new int[]{3,10,5,25,2,8};
        System.out.println(findMaximumXOR(arr));
        //
        //** Đề bài:
        //- Tính max nhất của phép xor có giá trị lớn nhất trong 2 số
        //
        //** Bài này tư duy như sau:
        //1,
        //1.1,
        //- Tìm max nhất bằng cách tìm bit tương ứng sao cho giá trị của nó max nhất
        //VD:
        //value =3 : 011
        //Ta sẽ tìm x sao cho value ^ x MAX nhất
        //- Càng bit ở xa (từ right --> left) thì cần phải tìm bit thoả mãn điều kiện (value xor x) max nhất
        //+ 011 --> Cần tìm số có bit đầu tiên là 1 --> sau đó tìm tiếp các bit ở đằng sau sao cho thoả mãn điều kiện.
        //1.2,
        //- Radix search tree mục đích của nó để biểu diễn all số dưới dạng nhị phân + leaf node chính là value của số
        //tương ứng.
        //- Dùng radix search tree ta có thể tìm được bit thoả mãn
        //- Phép xor có ct như sau:
        //+ 1 xor 1=0
        //+ 1 xor 0=1
        //+ 0 xor 0=0
        //* ==> Chỉ có những bit khác nhau thì mới làm thay đổi giá trị của 1 bit.
        //VD:
        //5 = 101 ==> Sẽ chọn số mà có giá trị càng ngược càng tốt để tăng lên được giá trị lên MAX
        //--> 010 ==> 2 hoặc nhiều số 1 nhất có thể.
        //
        //- Đơn giản ta sẽ lưu 2 children[2] : tương ứng với bit 0/1.
        //- Insert operation
        //+ Ta sẽ biểu diễn số hiện tại theo bit 01 --> for (31) lần coi như số đó là số tự nhiên int32
        //- Sear operation
        //+ Ta sẽ luôn tìm bit ngược với bit hiện tại
        //+ Nếu không tồn tại số có bit ngược thì lấy bit hiện tại (Cùng lắm là lấy số hiện tại)
        //
        //1.3, Với mỗi số thì ta sẽ lấy số đó value ^ getXor(value, root)
        //
        //1.4,
        //- Time complexity:
        //- Space complexity:
        //
        //1.5, Ưu điểm:
        //- Fix độ cao của trie.
        //
        //1.6, Bài toán có thể áp dụng:
        //- Tìm số lớn nhất < a, số nhỏ nhất >a
        //
    }
}
