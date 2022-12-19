package interviews;

import java.util.*;

public class E234_DesignTwitter_design {

    public static class Twitter {

        public static class Post{
            int time;
            int postId;

            public Post(int time, int postId) {
                this.time = time;
                this.postId = postId;
            }

            public int getTime() {
                return time;
            }

            public int getPostId() {
                return postId;
            }
        }

        public int currentTime;

        public HashMap<Integer, TreeSet<Integer>> followersHash;
        public HashMap<Integer, TreeSet<Integer>> followingUsersHash;
        public HashMap<Integer, TreeSet<Post>> hashMapPostsAll;
        public HashMap<Integer, TreeSet<Post>> hashMapRawPosts;

        public Twitter() {
            followersHash =new HashMap<>();
            hashMapPostsAll =new HashMap<>();
            followingUsersHash =new HashMap<>();
            hashMapRawPosts =new HashMap<>();
        }

        public void postTweet(int userId, int tweetId) {
            Post newPost=new Post(++currentTime, tweetId);
            TreeSet<Integer> followers = followersHash.get(userId);

            if(followers!=null){
                for(Integer id: followers){
                    TreeSet<Post> currentQueue = hashMapPostsAll.get(id);
//                    if(currentQueue.size()==10){
//                        Post post=currentQueue.last();
//                        currentQueue.remove(post);
//                    }
                    currentQueue.add(newPost);
                }
            }
            TreeSet<Post> myPosts = hashMapPostsAll.get(userId);
            TreeSet<Post> myRawPosts = hashMapRawPosts.get(userId);

            if(myPosts==null){
                myPosts=new TreeSet<>(Twitter::compare);
            }
            if(myRawPosts==null){
                myRawPosts=new TreeSet<>(Twitter::compare);
            }
//            if(myPosts.size()==10){
//                Post post=myPosts.last();
//                myPosts.remove(post);
//            }
//            if(myRawPosts.size()==10){
//                Post post=myRawPosts.last();
//                myRawPosts.remove(post);
//            }
            myPosts.add(newPost);
            myRawPosts.add(newPost);
            hashMapPostsAll.put(userId, myPosts);
            hashMapRawPosts.put(userId, myRawPosts);
        }

        public List<Integer> getNewsFeed(int userId) {
            TreeSet<Post> ownPosts = hashMapPostsAll.get(userId);

            if(ownPosts==null){
                ownPosts=new TreeSet<>(Twitter::compare);
            }
            List<Integer> rs=new LinkedList<>();
            int index=0;
            for(Post p: ownPosts){
                if(index>=10){
                    break;
                }
                rs.add(p.getPostId());
                index++;
            }
            return rs;
        }

        public void follow(int followerId, int followeeId) {
            //Adding new user who the user follows
            TreeSet<Integer> followingUsers = followingUsersHash.get(followerId);
            if(followingUsers==null){
                followingUsers=new TreeSet<>();
            }
            followingUsers.add(followeeId);
            followingUsersHash.put(followerId, followingUsers);
            //
            //Adding to the user who the current user is following
            TreeSet<Integer> followers = followersHash.get(followeeId);

            if(followers==null){
                followers=new TreeSet<>();
            }
            followers.add(followerId);
            followersHash.put(followeeId, followers);
            //Get all post from the following users
            TreeSet<Post> ownPosts = hashMapPostsAll.get(followerId);
            TreeSet<Post> followingUserPosts = hashMapRawPosts.get(followeeId);
//            HashSet<Post> hashAddedPosts=new HashSet<>();
//            HashSet<Post> hashOwnsPosts=null;
//
//            if(ownPosts!=null){
//                hashOwnsPosts=new HashSet<>(ownPosts);
//            }
            if(ownPosts==null){
                ownPosts=new TreeSet<>(Twitter::compare);
            }

//            TreeSet<Post> newPosts=new TreeSet<>(Twitter::compare);

            if(followingUserPosts==null){
                followingUserPosts=new TreeSet<>(Twitter::compare);
            }
            ownPosts.addAll(followingUserPosts);

//            Iterator<Post> interFollowingUserPosts=followingUserPosts.iterator();
//            Iterator<Post> interOwnPosts = ownPosts.iterator();
//            Post currentFollowingUserPost=null;
//            Post currentOwnPosts=null;
//
//            while (interFollowingUserPosts.hasNext()||interOwnPosts.hasNext()){
//                if(currentFollowingUserPost==null&&interFollowingUserPosts.hasNext()){
//                    currentFollowingUserPost=interFollowingUserPosts.next();
//                }
//                if(currentOwnPosts==null&&interOwnPosts.hasNext()){
//                    currentOwnPosts=interOwnPosts.next();
//                }
//                if(currentFollowingUserPost==null){
//                    newPosts.add(currentOwnPosts);
//                    hashAddedPosts.add(currentOwnPosts);
//                    currentOwnPosts=null;
//                    continue;
//                }else if(currentOwnPosts==null){
//                    newPosts.add(currentFollowingUserPost);
//                    hashAddedPosts.add(currentFollowingUserPost);
//                    currentFollowingUserPost=null;
//                    continue;
//                }
//                if(currentFollowingUserPost.time<currentOwnPosts.time){
//                    if(!hashAddedPosts.contains(currentFollowingUserPost)){
//                        newPosts.add(currentFollowingUserPost);
//                        hashAddedPosts.add(currentFollowingUserPost);
//                        currentFollowingUserPost=null;
//                    }
//                }else{
//                    if(!hashAddedPosts.contains(currentOwnPosts)){
//                        newPosts.add(currentOwnPosts);
//                        hashAddedPosts.add(currentOwnPosts);
//                        currentOwnPosts=null;
//                    }
//                }
//            }
//
//            if(currentFollowingUserPost!=null){
//                if(!hashAddedPosts.contains(currentFollowingUserPost)){
//                    newPosts.add(currentFollowingUserPost);
//                }
//            }else if(currentOwnPosts!=null){
//                if(!hashAddedPosts.contains(currentOwnPosts)){
//                    newPosts.add(currentOwnPosts);
//                }
//            }
////            if(followingUserPosts!=null){
////                for(Post post: followingUserPosts){
////                    if(hashOwnsPosts!=null&&!hashOwnsPosts.contains(post)){
////                        ownPosts.addFirst(post);
////                    }else if(hashOwnsPosts==null){
////                        ownPosts.addFirst(post);
////                    }
////                }
////            }
//            if(newPosts.size()==0){
//                for(Post p: ownPosts){
//                    newPosts.add(p);
//                }
//            }
            hashMapPostsAll.put(followerId, ownPosts);
        }

        public void unfollow(int followerId, int followeeId) {
            TreeSet<Integer> followingUsers = followingUsersHash.get(followerId);
            TreeSet<Post> followingRawUserPosts = hashMapRawPosts.get(followeeId);
            Integer object=followeeId;
            if(followingUsers!=null){
                followingUsers.remove(object);
            }

            HashSet<Post> setPosts=new HashSet<>();

            setPosts.addAll(followingRawUserPosts);
            TreeSet<Post> newPost=new TreeSet<>(Twitter::compare);
            TreeSet<Post> ownPosts = hashMapPostsAll.get(followerId);
            
            if(ownPosts!=null){
                for(Post post: ownPosts){
                    if(!setPosts.contains(post)){
                        newPost.add(post);
                    }
                }
            }
            hashMapPostsAll.put(followerId, newPost);
        }

        public static int compare(Post p1, Post p2){
            return p2.time-p1.time;
        }

    }

    public static void main(String[] args) {
//        Twitter twitter=new Twitter();
//        twitter.postTweet(1, 5);
//        twitter.getNewsFeed(1);
//        twitter.follow(1, 2);
//        twitter.postTweet(2, 6);
//        System.out.println(twitter.getNewsFeed(1));
//        twitter.unfollow(1, 2);
//        twitter.getNewsFeed(1);

//        Twitter twitter=new Twitter();
//        twitter.postTweet(2, 5);
//        twitter.follow(1, 2);
//        twitter.follow(1, 2);
//        System.out.println(twitter.getNewsFeed(1));

//        Twitter twitter=new Twitter();
//        twitter.postTweet(1, 5);
//        twitter.follow(1, 2);
//        twitter.follow(2, 1);
//        twitter.getNewsFeed(2);
//        twitter.postTweet(2, 6);
//        twitter.getNewsFeed(1);
//        twitter.getNewsFeed(2);
//        twitter.unfollow(2, 1);
//        twitter.getNewsFeed(1);
//        twitter.getNewsFeed(2);
//        twitter.unfollow(1, 2);
//        System.out.println(twitter.getNewsFeed(1));;
//        System.out.println(twitter.getNewsFeed(2));

        //Case bị không xét null sau khi continue
//        Twitter twitter=new Twitter();
//        twitter.postTweet(1, 1);
//        System.out.println(twitter.getNewsFeed(1));
//        twitter.follow(2, 1);
//        System.out.println(twitter.getNewsFeed(2));
//        twitter.unfollow(2, 1);
//        System.out.println(twitter.getNewsFeed(2));

//        Twitter twitter=new Twitter();
//        twitter.postTweet(1, 4);
//        twitter.postTweet(2, 5);
//        twitter.unfollow(1, 2);
//        twitter.follow(1, 2);
//        System.out.println(twitter.getNewsFeed(1));

        Twitter twitter=new Twitter();
        twitter.postTweet(2, 5);
        twitter.postTweet(1, 3);
        twitter.postTweet(1, 101);
        twitter.postTweet(2, 13);
        twitter.postTweet(2, 10);
        twitter.postTweet(1, 2);
        twitter.postTweet(2, 94);
        twitter.postTweet(2, 505);
        twitter.postTweet(1, 333);
        twitter.postTweet(1, 22);
        twitter.getNewsFeed(2);
        twitter.follow(2, 1);
        System.out.println(twitter.getNewsFeed(2));
        //
        //** Đề bài:
        //- Thiết kế twitter liên quan đến việc user đăng bài (post) và các followers xung quanh nó
        //Thỏa mãn các tiêu chí như:
        //+
        //
    }
}
