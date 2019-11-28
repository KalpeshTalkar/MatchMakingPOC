package com.kalpesh.matchmakingpoc.models

import io.realm.RealmObject
import kotlinx.serialization.Serializable

@Serializable
open class Coordinate constructor(
    var latitude: Double = 0.0,
    var longitude: Double = 0.0
) : RealmObject()
