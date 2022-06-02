package br.com.jpstudent.appmessage.di

import br.com.jpstudent.appmessage.model.database.PostFirebase
import br.com.jpstudent.appmessage.model.database.UserFirebase
import br.com.jpstudent.appmessage.model.repository.PostRepository
import br.com.jpstudent.appmessage.model.repository.UserRepository
import br.com.jpstudent.appmessage.usecase.*
import br.com.jpstudent.appmessage.viewmodel.HomePostsUserViewModel
import br.com.jpstudent.appmessage.viewmodel.HomeViewModel
import br.com.jpstudent.appmessage.viewmodel.LoginViewModel
import br.com.jpstudent.appmessage.viewmodel.RegisterViewModel
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModules = module {

    //ViewModel
    viewModel { RegisterViewModel(get(), get()) }
    viewModel { LoginViewModel(get(), get()) }
    viewModel { param -> HomeViewModel(get(), get(), get(), get(), get(), param.get()) }
    viewModel { param -> HomePostsUserViewModel(get(), param.get()) }

    //Repository
    factory { UserRepository(get()) }
    factory { PostRepository(get()) }

    //UseCase
    factory { SaveUserUseCase(get(), get()) }
    factory { DoLoginUseCase(get(), get()) }
    factory { GetPostsUseCase(get()) }
    factory { AddPostUseCase(get()) }
    factory { ValidationOwnerPostUseCase(get()) }
    factory { DeletePostUseCase(get()) }
    factory { ShowPostsProfileUseCase(get()) }


    //Firebase
    single { UserFirebase(get()) }
    single { PostFirebase(get()) }
    single { Firebase.database }
}