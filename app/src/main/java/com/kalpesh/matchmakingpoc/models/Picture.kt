package com.kalpesh.matchmakingpoc.models

import io.realm.RealmObject
import kotlinx.serialization.Serializable

@Serializable
open class Picture constructor(
    var large: String = "",
    var medium: String = "",
    var thumbnail: String = ""
) : RealmObject()
