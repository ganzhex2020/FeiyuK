package com.phi.feiyuk.utils

import com.phi.feiyuk.R
import com.phi.feiyuk.model.entity.LevelEntity
import com.phi.feiyuk.model.entity.LevelanchorEntity

object ComUtils {
    @JvmStatic
    fun getSexIcon(key:String):Int{
        return if (key == "1"){
            R.drawable.ic_sex_male_1
        }else{
            R.drawable.ic_sex_female_1
        }
    }

    @JvmStatic
    fun getLevelAnchor(level:String,list:List<LevelanchorEntity>):LevelanchorEntity?{
        for (item in list){
            if (item.levelid == level){
                return item
            }
        }
        return null
    }

    @JvmStatic
    fun getLevel(level:String,list:List<LevelEntity>):LevelEntity?{
        for (item in list){
            if (item.levelid == level){
                return item
            }
        }
        return null
    }
}