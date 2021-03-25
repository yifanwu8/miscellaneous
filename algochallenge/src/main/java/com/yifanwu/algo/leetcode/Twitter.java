package com.yifanwu.algo.leetcode;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * @author Yifan.Wu on 3/18/2021
 */
public class Twitter {
    private static int fakeTimer = 0; // mock
    private final Map<Integer, User> userMap = new HashMap<>();

    /** Initialize your data structure here. */
    public Twitter() {
    }

    /** Compose a new tweet. */
    public void postTweet(int userId, int tweetId) {
        if (!userMap.containsKey(userId)) {
            userMap.put(userId, new User(userId));
        }
        userMap.get(userId).postTweet(tweetId);
        return;
    }

    /** Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent. */
    public List<Integer> getNewsFeed(int userId) {
        // n-way merge using Priority Q
        List<Integer> res = new ArrayList<>(10);
        if (!userMap.containsKey(userId)) {
            userMap.put(userId, new User(userId));
        }
        User user = userMap.get(userId);
        Map<Integer, Iterator<Tweet>> followeesTweetsIterator = new HashMap<>();
        PriorityQueue<UserTweet> userTweetsPq = new PriorityQueue<>();
        followeesTweetsIterator.put(userId, user.tweetsIds.iterator());
        if (followeesTweetsIterator.get(userId).hasNext()) {
            userTweetsPq.add(new UserTweet(user.userId, followeesTweetsIterator.get(user.userId).next()));
        }
        for (int followeeId : user.followingUserIds) {
            if (followeesTweetsIterator.containsKey(followeeId)) continue;
            Iterator<Tweet> iterator = userMap.get(followeeId).tweetsIds.iterator();
            followeesTweetsIterator.put(followeeId, iterator);
            if (iterator.hasNext()) {  // initialize pq
                userTweetsPq.add(new UserTweet(followeeId, iterator.next()));
            }
        }
        UserTweet userTweet;
        while (res.size() < 10 && !userTweetsPq.isEmpty()) {
            userTweet = userTweetsPq.poll();
            res.add(userTweet.tweet.tweetId);
            if (followeesTweetsIterator.get(userTweet.userId).hasNext()) {
                userTweetsPq.add(new UserTweet(userTweet.userId, followeesTweetsIterator.get(userTweet.userId).next()));
            }
        }
        return res;
    }

    /** Follower follows a followee. If the operation is invalid, it should be a no-op. */
    public void follow(int followerId, int followeeId) {
        if (!userMap.containsKey(followerId)) {
            userMap.put(followerId, new User(followerId));
        }
        if (!userMap.containsKey(followeeId)) {
            userMap.put(followeeId, new User(followeeId));
        }
        userMap.get(followerId).follow(followeeId);
        return;
    }

    /** Follower unfollows a followee. If the operation is invalid, it should be a no-op. */
    public void unfollow(int followerId, int followeeId) {
        if (!userMap.containsKey(followerId)) {
            userMap.put(followerId, new User(followerId));
        }
        if (!userMap.containsKey(followeeId)) {
            userMap.put(followeeId, new User(followeeId));
        }
        userMap.get(followerId).unfollow(followeeId);
        return;
    }

    private static class User {
        private final int userId;
        private final Deque<Tweet> tweetsIds = new LinkedList<>();  // unbounded
        private final Set<Integer> followingUserIds = new HashSet<>();

        public User(int userId) {
            this.userId = userId;
        }
        void postTweet(int tweetId) {
            tweetsIds.addFirst(new Tweet(tweetId));
        }
        void follow(int followeeId) {
            followingUserIds.add(followeeId);
        }
        void unfollow(int followeeId) {
            followingUserIds.remove(followeeId);
        }
    }
    private static class Tweet implements Comparable<Tweet> {
        private final int tweetId;
        private final int fakeTimestamp;

        public Tweet(int tweetId) {
            this.tweetId = tweetId;
            this.fakeTimestamp = fakeTimer;
            fakeTimer++;
        }

        @Override
        public int compareTo(Tweet o) {
            return Integer.compare(o.fakeTimestamp, fakeTimestamp);
        }
    }
    private static class UserTweet implements Comparable<UserTweet> {
        private final int userId;
        private final Tweet tweet;

        public UserTweet(int userId, Tweet tweet) {
            this.userId = userId;
            this.tweet = tweet;
        }

        @Override
        public int compareTo(UserTweet o) {
            return tweet.compareTo(o.tweet);
        }
    }

    public static void main(String[] args) {
        Twitter twitter = new Twitter();
        twitter.postTweet(1, 5);
        twitter.follow(1, 1);
        twitter.getNewsFeed(1);

    }
}
