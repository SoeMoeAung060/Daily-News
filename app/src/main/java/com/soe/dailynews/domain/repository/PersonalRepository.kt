//package com.soe.dailynews.domain.repository
//
//import com.soe.dailynews.domain.model.UserData
//import kotlinx.coroutines.flow.Flow
//
//interface PersonalRepository {
//
//    suspend fun saveUserData(
//        name : String,
//        email: String,
//        dateOfBirth: String,
//        password:String
//    )
//
//    fun getUserData() : Flow<UserData>
//}