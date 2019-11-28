package com.kalpesh.matchmakingpoc.models

import io.realm.RealmObject
import kotlinx.serialization.Serializable

@Serializable
open class Street constructor(
    var number: Int = 0,
    var name: String = ""
) : RealmObject()
