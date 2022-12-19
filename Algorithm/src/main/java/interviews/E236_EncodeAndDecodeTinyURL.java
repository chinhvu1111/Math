package interviews;

import java.util.HashMap;
import java.util.Map;

public class E236_EncodeAndDecodeTinyURL {

    public class Codec {
        Map<String, String> codeDB = new HashMap<>(), urlDB = new HashMap<>();
        static final String chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

        // Encodes a URL to a shortened URL.
        public String encode(String longUrl) {
            if (urlDB.containsKey(longUrl)) return urlDB.get(longUrl);
            String code = getCode();
            while (codeDB.containsKey(code)) code = getCode();
            codeDB.put(code, longUrl);
            urlDB.put(longUrl, code);
            return code;
        }

        private String getCode() {
            char[] code = new char[6];
            for (int i = 0; i < 6; i++)
                code[i] = chars.charAt((int)Math.random() * 62);
            return "http://tinyurl.com/" + String.valueOf(code);
        }

        // Decodes a shortened URL to its original URL.
        public String decode(String shortUrl) {
            return codeDB.get(shortUrl);
        }
    }

    public static void main(String[] args) {

    }
}
