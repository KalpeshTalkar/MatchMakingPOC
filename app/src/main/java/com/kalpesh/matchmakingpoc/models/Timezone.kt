package com.kalpesh.matchmakingpoc.models

import io.realm.RealmObject
import kotlinx.serialization.Serializable

@Serializable
open class Timezone constructor(
    var offset: String = "",
    var description: String = ""
) : RealmObject()
