package com.phi.feiyuk

import com.phi.feiyuk.model.entity.LiveclasEntity
import com.phi.feiyuk.utils.DeviceUtils
import org.junit.Test

import org.junit.Assert.*
import java.nio.Buffer

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        //assertEquals(4, 2 + 2)
        val list = mutableListOf<LiveclasEntity>()//listOf<Int>(1,2,3,4,5)
        /*for (element in list){
            println(element)
        }*/
//        list  .add(LiveclasEntity("","0","全部","","",false))
//        list  .add(LiveclasEntity("","0","全部","","",true))
//        list  .add(LiveclasEntity("","0","全部","","",false))
//        list  .add(LiveclasEntity("","0","全部","","",false))
//        println(list.indexOfFirst { it.select })

        //    println(DeviceUtils.getCNM(20,5))
//unicode ->String
        /*fun decode(encodeText: String): String {
            fun decode1(unicode: String) = unicode.toInt(16).toChar()
            val unicodes = encodeText.split("\\u")
                .map { if (it.isNotBlank()) decode1(it) else null }.filterNotNull()
            return String(unicodes.toCharArray())
        }


        val str =
            "{\"ret\":200,\"data\":{\"code\":700,\"msg\":\"\\u60a8\\u7684\\u767b\\u9646\\u72b6\\u6001\\u5931\\u6548\\uff0c\\u8bf7\\u91cd\\u65b0\\u767b\\u9646\\uff01\",\"info\":[]},\"msg\":\"\"}"
        println(decode(str))*/

    }
}