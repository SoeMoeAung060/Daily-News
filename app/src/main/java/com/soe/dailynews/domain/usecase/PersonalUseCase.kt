//package com.soe.dailynews.domain.usecase
//
//import com.soe.dailynews.domain.model.UserData
//import kotlinx.coroutines.flow.Flow
//
//
//
//data class PersonalUseCase(
//    val saveUserDataUseCase: SaveUserDataUseCase,
//    val getUserDataUseCase: GetUserDataUseCase
//)
//
//class SaveUserDataUseCase(
//    private val repository: PersonalRepository
//) {
//    suspend operator fun invoke(
//        name: String,
//        email: String,
//        dateOfBirth: String,
//        password: String,
//    ) {
//        repository.saveUserData(name, email, dateOfBirth, password)
//    }
//}
//
//
//class GetUserDataUseCase(
//    private val repository: PersonalRepository
//) {
//    fun invoke(): Flow<UserData> {
//        return repository.getUserData()
//    }
//}