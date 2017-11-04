package com.example.baehyeonbin.highthon.beans;

import com.orm.SugarRecord;

/**
 * Created by baehyeonbin on 2017. 11. 5..
 */

public class User extends SugarRecord {
    private String username;
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUserName() {
        return username;
    }

    public void setUserName(String userName) {
        this.username = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
