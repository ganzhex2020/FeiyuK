package com.phi.feiyuk

import com.phi.feiyuk.model.api.ServiceApi
import com.phi.httplib.RetrofitClient
import kotlinx.coroutines.*
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain

import org.junit.After
import org.junit.Before
import org.junit.Test

class SomeTest {

    private val mainThreadSurrogate = newSingleThreadContext("UI thread")
    private val serviceApi = RetrofitClient.createApi(
        ServiceApi::class.java,
        "http://www.ppgd68gram1970.com/api/public/"
    )

    @Before
    fun setUp() {
        Dispatchers.setMain(mainThreadSurrogate)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain() // reset main dispatcher to the original Main dispatcher
        mainThreadSurrogate.close()
    }

    @Test
    fun testSomeUI() = runBlocking<Unit> {
        launch(Dispatchers.Main) {  // Will be launched in the mainThreadSurrogate dispatcher
         //   delay(2000)
            println("xxx")
           /* launch(Dispatchers.IO) {
                val result = serviceApi.userLogin("18700000001","x123456")
                println(result)
            }*/
            launch(Dispatchers.IO) {
                val result = serviceApi.getUserInfo("45245","fa8028cd2867e195604c4d57e6299921")
                println(result)
            }
        }
    }
}