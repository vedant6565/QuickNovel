package com.lagradost.quicknovel

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl
import com.lagradost.quicknovel.ui.search.SearchFragment
import kotlinx.android.synthetic.main.search_result_super_compact.view.*


class MainAdapter(
    context: Context,
    animeList: ArrayList<MainPageResponse>,
    resView: RecyclerView,
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var cardList = animeList
    var context: Context? = context
    var resView: RecyclerView? = resView

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val layout = R.layout.search_result_super_compact
        return MainCardViewHolder(
            LayoutInflater.from(parent.context).inflate(layout, parent, false),
            context!!,
            resView!!
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is MainCardViewHolder -> {
                holder.bind(cardList[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return cardList.size
    }

    class MainCardViewHolder
    constructor(itemView: View, _context: Context, resView: RecyclerView) : RecyclerView.ViewHolder(itemView) {
        val context = _context
        val cardView: ImageView = itemView.imageView
        val cardText: TextView = itemView.imageText
        val cardTextExtra: TextView = itemView.imageTextExtra
        val imageTextProvider: TextView = itemView.imageTextProvider
        val bg = itemView.backgroundCard
        fun bind(card: MainPageResponse) {
            cardText.text = card.name
            cardTextExtra.text = card.latestChapter ?: ""

            /*
            bg.setCardBackgroundColor(MainActivity.activity.getColor(R.color.itemBackground))
            for (d in SearchFragment.searchDowloads) {
                if (card.url == d.source) {
                    bg.setCardBackgroundColor(MainActivity.activity.getColor(R.color.colorItemSeen))
                    break
                }
            }*/
            //imageTextProvider.text = card.apiName

            val glideUrl =
                GlideUrl(card.posterUrl)
            context.let {
                Glide.with(it)
                    .load(glideUrl)
                    .into(cardView)
            }

            bg.setOnClickListener {
                MainActivity.loadResult(card.url, card.apiName)
            }
        }
    }
}
