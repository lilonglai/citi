package com.lex.webservice;

import java.util.HashMap;
import java.util.Map;

public class UserRepository {
    private static Map<String, User> users;

    static{
        initData();
    }

    public static void initData() {
        users = new HashMap<>();
        User bella = new User();
        bella.setName("Bella");
        bella.setAge(30);
        bella.setGender(Gender.FEMALE);
        bella.setAddress("hello");

        User kevin = new User();
        kevin.setName("Kevin");
        kevin.setAge(32);
        kevin.setGender(Gender.MALE);
        kevin.setAddress("hello");

        User arthur = new User();
        arthur.setName("Arthur");
        arthur.setAge(4);
        arthur.setGender(Gender.MALE);
        arthur.setAddress("hello");

        users.put(bella.getName(), bella);
        users.put(kevin.getName(), kevin);
        users.put(arthur.getName(), arthur);
    }

    public User findUser(String name) {
        return users.get(name);
    }
}
