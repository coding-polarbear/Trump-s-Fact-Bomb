package com.example.baehyeonbin.highthon.beans;

import com.google.gson.annotations.SerializedName;
import com.orm.SugarRecord;

/**
 * Created by baehyeonbin on 2017. 11. 4..
 */

public class Token extends SugarRecord {
    @SerializedName("data")
    private String token;
    public Token(){}
    public Token(String token) {
        this.token = token;
    }

    public String getToken() {
        return this.token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
