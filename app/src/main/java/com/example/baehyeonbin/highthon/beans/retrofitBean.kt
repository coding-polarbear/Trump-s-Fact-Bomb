package com.example.baehyeonbin.highthon.beans

/**
 * Created by baehyeonbin on 2017. 11. 5..
 */
data class UserGet(val status : Result, val token : Token, val user : User)
data class Success(val status : Result)
data class PostListGet(val status : Result, val posts : List<Post>)
data class PostGet(val status : Result, val post : Post)
data class CommentListGet(val status : Result, val comments :  List<Comment>)