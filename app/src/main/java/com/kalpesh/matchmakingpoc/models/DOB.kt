package com.kalpesh.matchmakingpoc.models

import io.realm.RealmObject
import kotlinx.serialization.Serializable

@Serializable
open class DOB constructor(
    var date: String = "",
    var age: Int = 0
) : RealmObject()
