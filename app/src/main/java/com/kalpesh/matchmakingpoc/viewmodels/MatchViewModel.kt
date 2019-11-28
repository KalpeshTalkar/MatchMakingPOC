package com.kalpesh.matchmakingpoc.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.kalpesh.matchmakingpoc.BuildConfig
import com.kalpesh.matchmakingpoc.models.MatchResults
import com.kalpesh.matchmakingpoc.models.Person
import com.kalpesh.matchmakingpoc.networking.Endpoint
import com.kalpesh.matchmakingpoc.networking.isConnectedToInternet
import com.kalpesh.matchmakingpoc.utils.GENERIC_ERROR_MESSAGE
import com.kalpesh.matchmakingpoc.utils.MEMBER_ACCEPTED_MESSAGE
import com.kalpesh.matchmakingpoc.utils.MEMBER_DECLINED_MESSAGE
import io.realm.Realm
import kotlinx.serialization.json.JSON
import org.jetbrains.anko.doAsync
import java.net.URL

class MatchViewModel(application: Application) : AndroidViewModel(application) {

    val realm: Realm by lazy {
        Realm.getDefaultInstance()
    }

    val matchResults = MutableLiveData<MatchResults>()

    fun getMatchResults() {
        if (isConnectedToInternet()) {
            doAsync {
                try {
                    val response = URL(Endpoint.MATCHES).readText()
                    val result = JSON.nonstrict.parse(MatchResults.serializer(), response)
                    matchResults.postValue(result)
                    realm.executeTransactionAsync { rlm ->
                        for (person in result.results) {
                            rlm.insertOrUpdate(person)
                        }
                    }

                } catch (e: Exception) {
                    val result = MatchResults(ArrayList())
                    result.error =
                        if (BuildConfig.DEBUG) e.localizedMessage else GENERIC_ERROR_MESSAGE
                    matchResults.postValue(result)
                }
            }
        } else {
            getMatchResultsFromDB()
        }
    }

    private fun getMatchResultsFromDB() {
        realm.executeTransactionAsync {
            val results = it.where(Person::class.java).findAll()

            val personList = ArrayList<Person>()
            personList.addAll(it.copyFromRealm(results))
            val matchResults = MatchResults(personList)

            this.matchResults.postValue(matchResults)
        }
    }

    fun updateInterestStatus(person: Person, interested: Boolean) {
        person.interestStatus = if (interested) MEMBER_ACCEPTED_MESSAGE else MEMBER_DECLINED_MESSAGE
        doAsync {
            Realm.getDefaultInstance().executeTransaction { realm ->
                person.interestStatus =
                    if (interested) MEMBER_ACCEPTED_MESSAGE else MEMBER_DECLINED_MESSAGE
                realm.insertOrUpdate(person)
            }
        }
    }

    override fun onCleared() {
        doAsync {
            realm.close()
        }
        super.onCleared()
    }

}