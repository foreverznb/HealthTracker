package com.example.healthtracker;

import java.util.ArrayList;

public class UserList {
    private ArrayList<User> users = new ArrayList<User>();

    public void add(User user) {
        users.add(user);
    }

    public boolean hasUser(User user) {
        return users.contains(user);
    }

    public User getUser(int index) {
        return users.get(index);
        //Tweet test = new NormalTweet("Ya");
        //return test;
    }

    public void deleteUser(User user) {
        users.remove(user);
    }
}
