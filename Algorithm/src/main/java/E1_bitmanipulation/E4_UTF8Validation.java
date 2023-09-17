package E1_bitmanipulation;

public class E4_UTF8Validation {

    public static boolean validUtf8(int[] data) {
        int n=data.length;

        if(n==0){
            return true;
        }
        int index=7;

        while(((data[0]>>index)&1)==1){
            // System.out.printf("Bit>>: %s\n", (data[0]>>index)&1);
            index--;
        }
        if(((data[0]>>index)&1)!=0){
            return false;
        }
        int numBytes=7-index;
        // System.out.printf("%s\n", numBytes);

        for(int i=1;i<numBytes;i++){
            int currentNum=data[i];

            if(((currentNum>>6)&1)!=0){
                return false;
            }
            if(((currentNum>>7)&1)!=1){
                return false;
            }
        }
        //11
        // System.out.printf("%s\n", 3>>2);
        return true;
    }

    public static void main(String[] args) {
        //** Requirement
        //A character in UTF8 can be from 1 to 4 bytes long, subjected to the following rules:
        //+ For a 1-byte character, the first bit is a 0, followed by its Unicode code.
        //+ For an n-bytes character, the first n bits are all one's, the n + 1 bit is 0, followed by n - 1 bytes with the most significant 2 bits being 10.
        //Ex:
        //Input: data = [197,130,1]
        //Output: true
        //Explanation: data represents the octet sequence: 11000101 10000010 00000001.
        //
        //** Idea
        //*
        //1.
        //1.0,
        //- Constraint
        //1 <= data.length <= 2 * 104
        //0 <= data[i] <= 255
        //
        //- Brainstorm
        //- Bài này sẽ làm những việc:
        //+ Kiếm tra độ dài loại unicode:
        //+ k bytes ==> Sẽ không liên quan quá nhiều ==> Chỉ cần xét riêng với byte=1 là được
        //+ Kiểm tra xem byte đằng sau có start with 10 hay không
//        int[] arr=new int[]{235,140,4};
        //{10, 1}
        int[] arr=new int[]{2, 1};
        System.out.println(validUtf8(arr));
        //#Reference:
        //1125. Smallest Sufficient Team
        //2018. Check if Word Can Be Placed In Crossword
        //1575. Count All Possible Routes
    }
}
