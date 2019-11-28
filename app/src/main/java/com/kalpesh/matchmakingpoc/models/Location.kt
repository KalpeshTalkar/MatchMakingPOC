package com.kalpesh.matchmakingpoc.models

import io.realm.RealmObject
import kotlinx.serialization.Serializable

@Serializable
open class Location constructor(
    var street: Street? = null,
    var city: String = "",
    var state: String = "",
    var country: String = "",
    var postcode: String = "",
    var coordinates: Coordinate? = null,
    var timezone: Timezone? = null
) : RealmObject()

