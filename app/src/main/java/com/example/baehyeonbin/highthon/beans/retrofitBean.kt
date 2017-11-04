package com.example.baehyeonbin.highthon.beans

/**
 * Created by baehyeonbin on 2017. 11. 5..
 */
data class UserGet(val status : Result, val token : Token, val user : User)
data class Success(val status : Result);