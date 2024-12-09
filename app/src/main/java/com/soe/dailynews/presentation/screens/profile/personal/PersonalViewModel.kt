//package com.soe.dailynews.presentation.screens.profile.personal
//
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.viewModelScope
//import com.soe.dailynews.domain.model.UserData
//import com.soe.dailynews.domain.usecase.GetUserDataUseCase
//import com.soe.dailynews.domain.usecase.PersonalUseCase
//import com.soe.dailynews.domain.usecase.SaveUserDataUseCase
//import dagger.hilt.android.lifecycle.HiltViewModel
//import kotlinx.coroutines.flow.MutableStateFlow
//import kotlinx.coroutines.flow.StateFlow
//import kotlinx.coroutines.launch
//import javax.inject.Inject
//
//
//@HiltViewModel
//class PersonalViewModel @Inject constructor(
//    private val personalUseCase: PersonalUseCase
//): ViewModel() {
//
//    private val _userData = MutableStateFlow(UserData("", "", "", ""))
//    val userData : StateFlow<UserData> = _userData
//
//
//
//    init {
//        getUserData()
//    }
//
//
//    fun saveUserData(name : String, email : String, dateOfBirth : String, password : String){
//        viewModelScope.launch {
//            personalUseCase.saveUserDataUseCase(name, email, dateOfBirth, password)
//        }
//    }
//
//    private fun getUserData(){
//        viewModelScope.launch {
//            personalUseCase.getUserDataUseCase.invoke().collect{
//                _userData.value = it
//            }
//        }
//    }
//
//}