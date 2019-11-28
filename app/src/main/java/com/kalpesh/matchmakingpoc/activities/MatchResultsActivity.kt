package com.kalpesh.matchmakingpoc.activities

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.kalpesh.krecyclerviewadapter.KRecyclerViewAdapter
import com.kalpesh.krecyclerviewadapter.KRecyclerViewHolder
import com.kalpesh.krecyclerviewadapter.KRecyclerViewHolderCallBack
import com.kalpesh.krecyclerviewadapter.KRecyclerViewItemClickListener
import com.kalpesh.matchmakingpoc.R
import com.kalpesh.matchmakingpoc.holders.MatchHolder
import com.kalpesh.matchmakingpoc.holders.MatchHolderActionListener
import com.kalpesh.matchmakingpoc.models.Person
import com.kalpesh.matchmakingpoc.utils.GENERIC_ERROR_MESSAGE
import com.kalpesh.matchmakingpoc.utils.NO_MATCHES_MESSAGE
import com.kalpesh.matchmakingpoc.viewmodels.MatchViewModel
import kotlinx.android.synthetic.main.activity_match_results.*

class MatchResultsActivity : AppCompatActivity() {

    // Variables

    private lateinit var adapter: KRecyclerViewAdapter
    private val viewModel: MatchViewModel by lazy {
        ViewModelProviders.of(this).get(MatchViewModel::class.java)
    }

    // Life cycle methods

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_match_results)
        setupUI()
        setDataObserver()
        getData()
    }

    // UI setup

    private fun setupUI() {
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        viewModel.matchResults.value?.let {
            if (it.error.isNullOrBlank()) {
                if (it.results.isNotEmpty()) {
                    adapter =
                        KRecyclerViewAdapter(
                            this,
                            it.results,
                            object : KRecyclerViewHolderCallBack {
                                override fun onCreateViewHolder(parent: ViewGroup): KRecyclerViewHolder {
                                    val view = LayoutInflater.from(parent.context)
                                        .inflate(R.layout.row_match, parent, false)
                                    return MatchHolder(view, object : MatchHolderActionListener {
                                        override fun interestAction(
                                            person: Person,
                                            interested: Boolean
                                        ) {
                                            viewModel.updateInterestStatus(person, interested)
                                        }
                                    })
                                }

                                override fun onHolderDisplayed(p0: KRecyclerViewHolder, p1: Int) {
                                }
                            },
                            KRecyclerViewItemClickListener { _, _, _ -> })
                    recyclerView.layoutManager =
                        LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
                    recyclerView.adapter = adapter

                    recyclerView.visibility = View.VISIBLE
                    titleLbl.visibility = View.VISIBLE
                    errorLbl.visibility = View.GONE

                } else {
                    showError(NO_MATCHES_MESSAGE)
                }

            } else {
                showError(it.error ?: GENERIC_ERROR_MESSAGE)
            }
        }
    }

    private fun showError(error: String) {
        recyclerView.visibility = View.INVISIBLE
        titleLbl.visibility = View.INVISIBLE
        errorLbl.visibility = View.VISIBLE
        errorLbl.text = error
    }


    private fun setDataObserver() {
        viewModel.matchResults.observe(this, Observer {
            progressBar.hide()
            setupRecyclerView()
        })
    }

    private fun getData() {
        progressBar.show()
        viewModel.getMatchResults()
    }

}
