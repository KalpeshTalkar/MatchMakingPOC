package com.kalpesh.matchmakingpoc.holders

import android.content.Context
import android.view.View
import com.bumptech.glide.Glide
import com.kalpesh.krecyclerviewadapter.KRecyclerViewHolder
import com.kalpesh.matchmakingpoc.models.Person
import com.kalpesh.matchmakingpoc.models.getAbout
import com.kalpesh.matchmakingpoc.models.getName
import com.kalpesh.matchmakingpoc.utils.MEMBER_ACCEPTED_MESSAGE
import com.kalpesh.matchmakingpoc.utils.MEMBER_DECLINED_MESSAGE
import kotlinx.android.synthetic.main.row_match.view.*

interface MatchHolderActionListener {
    fun interestAction(person: Person, interested: Boolean)
}

class MatchHolder(itemView: View, val actionListener: MatchHolderActionListener) :
    KRecyclerViewHolder(itemView) {

    override fun setData(context: Context, itemObject: Any) {
        super.setData(context, itemObject)
        if (itemObject is Person) {
            display(itemObject)
        }
    }

    private fun display(person: Person) {
        itemView.nameLbl.text = person.name!!.getName()
        itemView.descLbl.text = person.getAbout()
        itemView.interestStatusLbl.text = person.interestStatus

        itemView.acceptBtn.setOnClickListener {
            person.interestStatus = MEMBER_ACCEPTED_MESSAGE
            updateinterestStatus(person)
            actionListener.interestAction(person, true)
        }
        itemView.declineBtn.setOnClickListener {
            person.interestStatus = MEMBER_DECLINED_MESSAGE
            updateinterestStatus(person)
            actionListener.interestAction(person, false)
        }

        updateinterestStatus(person)

        Glide.with(itemView)
            .load(person.picture?.medium)
            .centerCrop()
            .into(itemView.profileImg)
    }

    private fun updateinterestStatus(person: Person) {
        if (person.interestStatus.isEmpty()) {
            itemView.acceptBtn.visibility = View.VISIBLE
            itemView.declineBtn.visibility = View.VISIBLE
            itemView.interestStatusLbl.visibility = View.INVISIBLE

            itemView.interestStatusLbl.text = ""

        } else {
            itemView.acceptBtn.visibility = View.INVISIBLE
            itemView.declineBtn.visibility = View.INVISIBLE
            itemView.interestStatusLbl.visibility = View.VISIBLE

            itemView.interestStatusLbl.text = person.interestStatus
        }
    }

}
