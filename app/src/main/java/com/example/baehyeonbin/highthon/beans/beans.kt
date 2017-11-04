package com.example.baehyeonbin.highthon.beans

/**
 * Created by baehyeonbin on 2017. 11. 4..
 */
data class Result(val success : Boolean, val message : String)
data class Post(var title : String) {
        var idx : Int = 0
        var username : String = ""
        var isOpen : Boolean = false
        var content : String = ""
        var createdAt : String = ""
        var commentNum : Int = 0
}
data class Comment(var content : String) {
    var postIdx : Int = 0
    var username : String = ""
    var createdAt : String = ""
}
data class IsOpen(var idx : Int, var isOpen : Boolean)