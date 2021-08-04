package com.phi.httplib

import org.junit.Test
import java.util.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        //assertEquals(4, 2 + 2)

        val list: MutableList<Int> = ArrayList()
        for (i in 0..39) {
            list.add(i)
        }
        var count = 0
        val copys: MutableList<Int> = ArrayList()
        while (count < 20) {
            val i = (0 until list.size).random()
            if (!copys.contains(i)) {
                copys.add(i)
                count++
            }
        }
        for (i in copys.indices) {
            println(copys[i])
        }

    }
}