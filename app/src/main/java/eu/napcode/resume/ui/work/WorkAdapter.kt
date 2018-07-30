package eu.napcode.resume.ui.work

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import eu.napcode.resume.R
import eu.napcode.resume.model.Work
import kotlinx.android.synthetic.main.item_work.view.*

class WorkAdapter(private val works: List<Work>?, public val context: Context) : RecyclerView.Adapter<WorkAdapter.ViewHolder>() {

    fun ViewGroup.inflate(layoutRes: Int): View {
        return LayoutInflater.from(context).inflate(layoutRes, this, false)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, index: Int) = ViewHolder(viewGroup.inflate(R.layout.item_work))

    override fun getItemCount() = works!!.size

    override fun onBindViewHolder(holder: ViewHolder, index: Int) = holder.bind(works!![index])

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(work: Work) = with(itemView) {
            itemView.listWorkTextView.text = work.name
        }
    }

}