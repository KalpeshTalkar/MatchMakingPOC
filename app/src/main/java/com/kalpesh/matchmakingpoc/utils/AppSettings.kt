package com.kalpesh.matchmakingpoc.utils

class AppSettings {

    companion object {

        private const val KEY_MATCH_PREFERENCE = "match_preference"
        const val MATCH_PREFERENCE_MALE = "male"
        const val MATCH_PREFERENCE_FEMALE = "female"

        /**
         * Set match preference. (Male or Female)
         */
        fun setMatchPreference(preference: String) {
            LocalStorage.putValue(preference, KEY_MATCH_PREFERENCE)
        }

        /**
         * Get match preference. (Male or Female)
         */
        fun getMatchPreferences(): String {
            return LocalStorage.getValue(
                KEY_MATCH_PREFERENCE,
                MATCH_PREFERENCE_FEMALE,
                String::class.java
            )
        }

    }

}