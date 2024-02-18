package E2_design;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class E4_EncodeAndDecodeStrings {

    public static class Codec {

        // Encodes a list of strings to a single string.
        public String encode(List<String> strs) {
            StringBuilder encodedStr=new StringBuilder();

            for(String s: strs){
                encodedStr.append(s);
                encodedStr.append("\n");
            }
            return encodedStr.toString();
        }

        // Decodes a single string to a list of strings.
        public List<String> decode(String s) {
            int n=s.length();
            List<String> listStrs=new ArrayList<>();
            StringBuilder curRs=new StringBuilder();

            for(int i=0;i<n;i++){
                if(s.charAt(i)!='\n'){
                    curRs.append(s.charAt(i));
                }else{
                    listStrs.add(curRs.toString());
                    curRs=new StringBuilder();
                }
            }
            return listStrs;
        }
    }

    public static class CodecAllCases {

        // Encodes a list of strings to a single string.
        public String encode(List<String> strs) {
            StringBuilder encodedStr=new StringBuilder();

            for(String s: strs){
                encodedStr.append(s.replace("/", "//")).append("/:");
            }
            return encodedStr.toString();
        }

        // Decodes a single string to a list of strings.
        public List<String> decode(String s) {
            int n=s.length();
            List<String> listStrs=new ArrayList<>();
            StringBuilder curRs=new StringBuilder();

            for(int i=0;i<n;i++){
                if(i+1<n&&s.charAt(i)=='/'){
                    if(s.charAt(i+1)=='/'){
                        curRs.append("/");
                    }else if(s.charAt(i+1)==':'){
                        listStrs.add(curRs.toString());
                        curRs=new StringBuilder();
                    }
                    i++;
                }else{
                    curRs.append(s.charAt(i));
                }
            }
            return listStrs;
        }
    }

    public static class ChunkingCodecAllCases {

        // Encodes a list of strings to a single string.
        public String encode(List<String> strs) {
            StringBuilder encodedStr=new StringBuilder();

            for(String s: strs){
                encodedStr.append(s.length()).append("-").append(s);
            }
            return encodedStr.toString();
        }

        // Decodes a single string to a list of strings.
        public List<String> decode(String s) {
            int n=s.length();
            List<String> listStrs=new ArrayList<>();

            for(int i=0;i<n;i++){
                int size=0;

                while(i<n&&isDigit(s.charAt(i))){
                    size=size*10+s.charAt(i)-'0';
                    i++;
                }
                StringBuilder curRs=new StringBuilder();
                int j;

                for(j=i+1;j<=i+size;j++){
                    curRs.append(s.charAt(j));
                }
                i=j-1;
                listStrs.add(curRs.toString());
            }
            return listStrs;
        }
        public static boolean isDigit(char c){
            return c>='0'&&c<='9';
        }
    }

    public static void main(String[] args) {
        // Requirement
        //- Implement the method:
        //- Encode method: Encode list of string ==> encoded string
        //- Decode method : Decode the encoded string ==> list of the original strings
        //
        // Idea
        //1.
        //1.0,
        //- Constraint:
        //1 <= strs.length <= 200
        //0 <= strs[i].length <= 200
        //strs[i] contains any possible characters out of 256 valid ASCII characters.
        //==> Strs[i] contains ASCII --> Ta sẽ dùng character is not ASCII character as Delimiter.
        //
        //- Brainstorm
        //- Bài này liên quan đến việc list các string "" --> Lúc decode thì phải ra thông tin đầy đủ
        //==> Ta chỉ cần dùng character which is not ascii as delimiter là được.
        //1.1, Optimization
        //1.2 Complexity
        //- N is the number of character across all strings in the input list
        //- K is the number of words in the list
        //- Space: O(k) : Do không tính space thêm cho result
        //- Time : O(n)
        //
        //2.
        //2.0,
        //- Algorithm tổng quát cho all set of characters
        //- Ở đây sẽ dùng 1 kỹ thuật gọi là "ESCAPING":
        //- Ta sẽ chọn delimiter : /:
        //  + Nếu có word= "word//:" thì việc phân tách sẽ sai thì sao?
        //  --> Ta sẽ escape cho nó :
        //  + Tức là word nào có "/" -> replace -> "//"
        //--> Thực ra đây là tư duy replace những character giống với delimiter sao cho:
        //  + Replace xong lúc decode ta vẫn có thể từ đó suy ngược lại được old character.
        //- Escape "/" ==> "//" để không trùng với delimiter ="/:"
        //
        //- Câu hỏi: Tại sao lại là "/:", có thể là * mà replace các "*" trong word ==> "**" được không?
        //  + * thì nếu split về sau chuỗi sau khi replace duplicate lên thành ** ==> Split vẫn sẽ bị sai do "**" contains "*"
        //--> Ta cần delimiter là 2 character để đảm bảo các word không thể contains delimiter.
        //Ex:
        //+ Nếu : encoded_word= "a//:" ==> split như thế nào? : Ở đây "//:" là "/:" sau khi escape
        //+ Nếu : encoded_word= "a//[/:]" ==> split như thế nào? : Ở đây "/:" là DELIMITER
        //+ Ta decode bằng split trước:
        //  + Ta sẽ chỉ tìm "/:" + Đằng trước nó số lượng "/" là số chẵn (%2==0)
        //  ==> Cái này chyển về thành xử lý đôi một là được.
        //Ex:
        //encoded_word= "a//[/:]"
        //encoded_word= "//[/:]"
        //- Ta sẽ luôn xét ntn để có thể (xử lý đôi một các slash)
        // ==> Để ko có chuyện "/:" đứng 1 mình một chỗ - lẫn với "DELIMITER" sau khi "ESCAPE":
        //+ s[i]=='/' && s[i+1]=='/')
        // + i=i+2
        //+ s[i]=='/' && s[i+1]==':')
        // + New word
        //+ <> i++
        //
        //1.1, Optimization
        //1.2, Complexity
        //- N is the number of character across all strings in the input list
        //- K is the number of words in the list
        //- Space: O(k) : Do không tính space thêm cho result
        //- Time : O(n)
        //
        //3. Chunked transfer encoding
        //3.0, Idea
        //- Chunked data ==> Merge chúng lại
        //Ex:
        //For "Hello", the length is 555. So we start our encoded string with 5/:Hello.
        //- Questions:
        //+ Nếu nhiều characters + String with prefix là digits thì sao:
        //Ex:
        // Word= "13'34asdas'"
        // ==> Ta cần chọn delimiter ==> "-" cũng được.
        //1.1, Optimization
        //
        //1.2, Complexity
        //- N is the number of character across all strings in the input list
        //- K is the number of words in the list
        //- Space: O(k) : Do không tính space thêm cho result
        //- Time : O(n)
        //
        //#Reference:
        //297. Serialize and Deserialize Binary Tree
        //696. Count Binary Substrings
        CodecAllCases code=new CodecAllCases();
        List<String> input=new ArrayList<>();
        input.add("");
        input.add("");
        String output=code.encode(input);
        System.out.println(output);
        System.out.println(code.decode(output));

        ChunkingCodecAllCases chunkingCodecAllCases=new ChunkingCodecAllCases();
        input=new ArrayList<>();
        input.add("Hello");
        input.add("World");
        output=chunkingCodecAllCases.encode(input);
        System.out.println(output);
        System.out.println(chunkingCodecAllCases.decode(output));
    }
}
