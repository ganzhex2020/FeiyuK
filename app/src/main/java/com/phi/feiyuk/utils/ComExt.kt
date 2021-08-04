package com.phi.feiyuk.utils

import com.google.gson.reflect.TypeToken

inline fun <reified T> genType() = object: TypeToken< T >() {}.type