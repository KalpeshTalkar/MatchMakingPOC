package com.kalpesh.matchmakingpoc.models

import kotlinx.serialization.Serializable

@Serializable
data class MatchResults constructor(
    var results: ArrayList<Person>
) {
    var error: String? = null
//    override fun equals(other: Any?): Boolean {
//        if (this === other) return true
//        if (javaClass != other?.javaClass) return false
//
//        other as MatchResults
//
//        if (!results.contentEquals(other.results)) return false
//
//        return true
//    }
//
//    override fun hashCode(): Int {
//        return results.contentHashCode()
//    }
}
