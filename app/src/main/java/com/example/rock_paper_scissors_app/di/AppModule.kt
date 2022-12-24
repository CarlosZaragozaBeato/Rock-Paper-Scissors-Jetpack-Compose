package com.example.rock_paper_scissors_app.di

import android.app.Application
import androidx.room.Room
import com.example.rock_paper_scissors_app.ui.feature_login.data.data_source.LoginDatabase
import com.example.rock_paper_scissors_app.ui.feature_login.data.repository.LoginRepositoryImp
import com.example.rock_paper_scissors_app.ui.feature_login.domain.repository.LoginRepository
import com.example.rock_paper_scissors_app.ui.feature_login.domain.use_cases.GetLoggedUseCase
import com.example.rock_paper_scissors_app.ui.feature_login.domain.use_cases.GetUserUseCase
import com.example.rock_paper_scissors_app.ui.feature_login.domain.use_cases.InsertUseCase
import com.example.rock_paper_scissors_app.ui.feature_login.domain.use_cases.LoginUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Singleton
    @Provides
    fun provideLoginDatabase(app:Application):LoginDatabase{
        return Room.databaseBuilder(
            app,
            LoginDatabase::class.java,
            LoginDatabase.DATABASE_NAME
        ).fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideLoginRepository(db:LoginDatabase):LoginRepository{
        return LoginRepositoryImp(db.loginDao)
    }

    @Singleton
    @Provides
    fun provideLoginUseCase(repository: LoginRepository):LoginUseCases{
        return LoginUseCases(
            getLogged = GetLoggedUseCase(repository),
            getUser = GetUserUseCase(repository),
            insertUser = InsertUseCase(repository))
    }


}