package com.example.baehyeonbin.highthon.beans;

import com.orm.SugarRecord;

/**
 * Created by baehyeonbin on 2017. 11. 4..
 */

public class Token extends SugarRecord {
    private String accessToken;

    public Token(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
