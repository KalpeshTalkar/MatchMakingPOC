package com.kalpesh.matchmakingpoc.models

import io.realm.RealmObject
import kotlinx.serialization.Serializable

@Serializable
open class Name constructor(
    var title: String = "",
    var first: String = "",
    var last: String = ""
) : RealmObject()

fun Name.getName(): CharSequence {
    return "$title $first $last"
}