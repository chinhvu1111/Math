package E1_Graph;

import java.util.*;

public class E40_WebCrawler {
    interface HtmlParser {
      public default List<String> getUrls(String url) {
          return new ArrayList<>();
      }
    }

    public static boolean isValidChar(char c){
        return c=='.'||(c>='a'&&c<='z')||(c>='0'&&c<='9')||c=='-';
    }

    public static String getHostName(String url){
        int startIndex=url.indexOf("//")+1;
        StringBuilder hostName=new StringBuilder();

        for(int i=startIndex+1;i<url.length();i++){
            if(isValidChar(url.charAt(i))){
                hostName.append(url.charAt(i));
            }else{
                break;
            }
        }
        return hostName.toString();
    }

    public static List<String> crawl(String startUrl, HtmlParser htmlParser) {
        Queue<String> nodes = new LinkedList<>();
        HashSet<String> rs=new HashSet<>();
        String curHostName=getHostName(startUrl);
        rs.add(startUrl);
        nodes.add(startUrl);
        // System.out.printf("HostName: %s\n", curHostName);

        // if(curHostName.charAt(curHostName.length()-1)=='-'||curHostName.charAt(0)=='-'){
        //     return new ArrayList<>();
        // }

        while(!nodes.isEmpty()){
            String curUrl=nodes.poll();
            List<String> adjNodes=htmlParser.getUrls(curUrl);
            // System.out.println(adjNodes);

            for(String nextUrl:adjNodes){
                String curHost=getHostName(nextUrl);
                // System.out.printf("Next HostName: %s\n", curHost);

                // if(curHostName.charAt(curHostName.length()-1)=='-'||curHostName.charAt(0)=='-'){
                //     continue;
                // }
                if(curHost.equals(curHostName)&&!rs.contains(nextUrl)){
                    nodes.add(nextUrl);
                    rs.add(nextUrl);
                }
            }
        }
        return new ArrayList<>(rs);
    }

    public static void main(String[] args) {
        //** Requirement
        //- Given a url (startUrl) and an interface (HtmlParser), implement a web crawler
        // to (crawl all links) that are under the (same hostname) as (startUrl).
        //- Từ startUrl --> Các url mà ta có thể đi
        //* Return list of urls bao gồm các url có cùng host name với startUrl.
        //
        //** Idea
        //1.
        //1.0,
        //- Constraint
        //1 <= urls.length <= 1000
        //1 <= urls[i].length <= 300
        //startUrl is one of the urls.
        //- Hostname label must be from 1 to 63 characters long, including the dots, may contain only the ASCII letters from 'a' to 'z',
        // digits  from '0' to '9' and the hyphen-minus character ('-').
        //The hostname may not start or end with the hyphen-minus character ('-').
        //
        //- Brainstorm
        //- Dùng BFS + kết hợp hashSet để lưu kết quả
        //  + startUrl --> Các url khác ta traverse by using BFS
        //
        //1.1, Optimization
        //1.2, Complexity
        //- N is the number of url
        //- E is the number of connection.
        //- Space : O(N)
        //- Time : O(N+E)
        //
        System.out.println(getHostName("http://news.yahoo.com/news/topics/"));
        //#Reference:
        //1242. Web Crawler Multithreaded
    }
}
