package com.soe.dailynews.di.authenticationModule

import kotlinx.coroutines.flow.Flow

interface AccountService {

    val currentUser : Flow<User?>
    val currentUserId : String
    fun hasUser() : Boolean
    fun getUserProfile() : User

    suspend fun forgotPassword(email : String)
    suspend fun createAnonymousAccount()
    suspend fun updateUserDisplayName(newDisplayName: String)
    suspend fun signInWithEmailAndPassword(email : String, password : String)
    suspend fun signUp(email : String, password : String)
    suspend fun signOut()

}