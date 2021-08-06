package com.phi.feiyuk.di

import com.phi.feiyuk.config.Const.BASE_URL
import com.phi.feiyuk.model.api.ServiceApi
import com.phi.feiyuk.model.repository.LocalDataSource
import com.phi.feiyuk.model.repository.RemoteDataSource
import com.phi.feiyuk.viewmodel.*
import com.phi.httplib.RetrofitClient
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val commonModule = module {
    single { RetrofitClient.createApi(ServiceApi::class.java, BASE_URL) }

}

val viewModelModule = module {
    viewModel{SplashViewModel(get(),get())}
    viewModel{MainViewModel(get())}
    viewModel{HomeLiveViewModel(get())}
    viewModel{HomeVideoViewModel(get())}
    viewModel{LoginViewModel(get())}
    viewModel{MineViewModel(get(),get())}
    viewModel{UserProfileViewModel(get(),get())}

}

val repositoryModule = module {
    single { RemoteDataSource(get()) }
    single { LocalDataSource() }

}
val appModule = listOf(commonModule, viewModelModule, repositoryModule)
