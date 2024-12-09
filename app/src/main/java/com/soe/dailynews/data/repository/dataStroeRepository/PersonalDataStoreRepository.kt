//package com.soe.dailynews.data.repository.dataStroeRepository
//
//import android.content.Context
//import androidx.datastore.core.DataStore
//import androidx.datastore.core.IOException
//import androidx.datastore.preferences.core.Preferences
//import androidx.datastore.preferences.core.edit
//import androidx.datastore.preferences.core.emptyPreferences
//import androidx.datastore.preferences.core.stringPreferencesKey
//import androidx.datastore.preferences.preferencesDataStore
//import com.soe.dailynews.domain.model.UserData
//import com.soe.dailynews.domain.repository.PersonalRepository
//import kotlinx.coroutines.flow.Flow
//import kotlinx.coroutines.flow.catch
//import kotlinx.coroutines.flow.map
//import javax.inject.Inject
//
//
//
//
//class PersonalDataStoreRepository @Inject constructor(
//    private val context: Context
//) : PersonalRepository{
//
//    private val Context.datastore by preferencesDataStore("user")
//
//    companion object{
//        private val NAME_KEY = stringPreferencesKey("user_name")
//        private val EMAIL_KEY = stringPreferencesKey("user_email")
//        private val DATE_OF_BIRTH = stringPreferencesKey("user_dateOfBirth")
//        private val PASSWORD = stringPreferencesKey("user_password")
//    }
//
//    override suspend fun saveUserData(
//        name: String,
//        email: String,
//        dateOfBirth: String,
//        password: String
//    ) {
//        context.datastore.edit {
//            it[NAME_KEY] = name
//            it[EMAIL_KEY] = email
//            it[DATE_OF_BIRTH] = dateOfBirth
//            it[PASSWORD] = password
//        }
//    }
//
//
//    override fun getUserData(): Flow<UserData> {
//        return context.datastore.data
//        .map {
//            val name: String = it[NAME_KEY] ?: ""
//            val email = it[EMAIL_KEY] ?: ""
//            val dateOfBirth = it[DATE_OF_BIRTH] ?: ""
//            val password = it[PASSWORD] ?: ""
//
//            UserData(name, email, dateOfBirth, password)
//        }
//    }
//
//}
//
