package com.phi.feiyuk.di

import com.phi.feiyuk.config.Const.BASE_URL
import com.phi.feiyuk.model.api.ServiceApi
import com.phi.feiyuk.model.repository.RemoteDataSource
import com.phi.feiyuk.viewmodel.*
import com.phi.httplib.RetrofitClient
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val commonModule = module {
    single { RetrofitClient.createApi(ServiceApi::class.java, BASE_URL) }

}

val viewModelModule = module {
    viewModel{SplashViewModel(get())}
    viewModel{MainViewModel(get())}
    viewModel{HomeLiveViewModel(get())}
    viewModel{LoginViewModel(get())}
    viewModel{MineViewModel(get())}

}

val repositoryModule = module {
    single { RemoteDataSource(get()) }

}
val appModule = listOf(commonModule, viewModelModule, repositoryModule)
