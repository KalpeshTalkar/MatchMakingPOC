package com.kalpesh.matchmakingpoc.models

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
open class Person : RealmObject() {
    var gender: String = ""
    var name: Name? = null
    var location: Location? = null
    @PrimaryKey
    var email: String = ""
    var dob: DOB? = null
    var phone: String = ""
    var cell: String = ""
    var picture: Picture? = null
    var nat: String = ""
    var interestStatus: String = ""
}

fun Person.getAbout(): CharSequence {
    return "${dob?.age} $gender \n" +
            "${location?.street?.number}, ${location?.street?.name} \n" +
            "${location?.city}, ${location?.state} ${location?.country}"
}
