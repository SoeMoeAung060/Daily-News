//package com.soe.dailynews.di.settingModule
//
//import android.content.Context
//import androidx.datastore.core.DataStore
//import com.soe.dailynews.data.repository.dataStroeRepository.PersonalDataStoreRepository
//import com.soe.dailynews.domain.repository.PersonalRepository
//import com.soe.dailynews.domain.usecase.GetUserDataUseCase
//import com.soe.dailynews.domain.usecase.PersonalUseCase
//import com.soe.dailynews.domain.usecase.SaveUserDataUseCase
//import dagger.Module
//import dagger.Provides
//import dagger.hilt.InstallIn
//import dagger.hilt.android.qualifiers.ApplicationContext
//import dagger.hilt.components.SingletonComponent
//import javax.inject.Singleton
//
//
//@Module
//@InstallIn(SingletonComponent::class)
//object DataStoreModule {
//
//    @Provides
//    @Singleton
//    fun providePersonalUseCase(
//        personalRepository : PersonalRepository
//    ): PersonalUseCase {
//        return PersonalUseCase(
//            saveUserDataUseCase = SaveUserDataUseCase(personalRepository),
//            getUserDataUseCase = GetUserDataUseCase(personalRepository)
//        )
//
//    }
//
//
//    @Provides
//    @Singleton
//    fun providePersonalDataStoreRepository(
//        context: Context
//    ): PersonalDataStoreRepository {
//        return PersonalDataStoreRepository(context)
//    }
//
//
//    @Provides
//    @Singleton
//    fun provideSaveUserDataUseCase(
//        repository: PersonalRepository
//    ): SaveUserDataUseCase {
//        return SaveUserDataUseCase(repository)
//    }
//
//    @Provides
//    @Singleton
//    fun provideGetUserDataUseCase(
//        repository: PersonalRepository
//    ): GetUserDataUseCase {
//        return GetUserDataUseCase(repository)
//    }
//
//}