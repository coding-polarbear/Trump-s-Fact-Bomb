package com.example.baehyeonbin.highthon.beans;

import com.orm.SugarRecord;

/**
 * Created by baehyeonbin on 2017. 11. 5..
 */

public class User extends SugarRecord {
    private String userName;
    private String password;

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
