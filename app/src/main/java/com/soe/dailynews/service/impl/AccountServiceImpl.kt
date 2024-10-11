package com.soe.dailynews.service.impl

import android.util.Log
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthEmailException
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.auth
import com.google.firebase.auth.userProfileChangeRequest
import com.soe.dailynews.service.User
import com.soe.dailynews.service.AccountService
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AccountServiceImpl @Inject constructor(): AccountService {


    override val currentUser: Flow<User?>
        get() = callbackFlow {
            val listener = FirebaseAuth.AuthStateListener { auth ->
                this.trySend(auth.currentUser.toNewsUser())
            }
            Firebase.auth.addAuthStateListener(listener)
            awaitClose {
                Firebase.auth.removeAuthStateListener(listener)
            }
        }

    override val currentUserId: String
        get() = Firebase.auth.currentUser?.uid.orEmpty()

    override fun hasUser(): Boolean {
        return Firebase.auth.currentUser != null
    }

    override fun getUserProfile(): User {
        return Firebase.auth.currentUser.toNewsUser()
    }

    override suspend fun forgotPassword(email: String) {
        try {
            Firebase.auth.sendPasswordResetEmail(email).await()
            Log.d("ForgotPassword", "Email sent.")
        } catch (e: Exception) {
            Log.w("ForgotPassword", "Email failed.", e)
        }
    }

    override suspend fun createAnonymousAccount() {
        Firebase.auth.signInAnonymously().await()
    }


    override suspend fun updateUserDisplayName(newDisplayName: String) {
        val profileUpdate = userProfileChangeRequest {
            displayName = newDisplayName
        }
        Firebase.auth.currentUser!!.updateProfile(profileUpdate).await()

    }

    override suspend fun signInWithEmailAndPassword(email: String, password: String) {
        try {
        Firebase.auth.signInWithEmailAndPassword(email, password).await()
        }catch (e: FirebaseAuthException){
            throw Exception("Invalid email or password")
        }catch (e: Exception){
            throw Exception("An unknown error occurred while signing in ${e.message}")
        }
    }

    override suspend fun signUp(email: String, password: String) {
        try {
        Firebase.auth.createUserWithEmailAndPassword(email, password).await()
        }catch (e : FirebaseAuthException){
            throw Exception("Sign up failed. Invalid email or password ${e.message}")
        }catch (e: Exception){
            throw Exception("An unknown error occurred while signing up ${e.message}")
        }
    }

    override suspend fun signOut() {
        Firebase.auth.signOut()
    }

    private fun FirebaseUser?.toNewsUser(): User {
        return if(this == null) User() else User(
            id = this.uid,
            email = this.email ?: "",
            displayName = this.displayName ?: ""

        )
    }
}

