package com.example.baehyeonbin.highthon.beans;

import com.orm.SugarRecord;

/**
 * Created by baehyeonbin on 2017. 11. 4..
 */

public class Token extends SugarRecord {
    private String token;

    public Token(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
