package eu.napcode.resume.ui.work

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import eu.napcode.resume.R
import eu.napcode.resume.model.Work
import eu.napcode.resume.utils.getFullDatesSpannable
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
            Glide.with(context)
                    .load(work.logo)
                    .apply(RequestOptions()
                            .placeholder(R.drawable.ic_work_placeholder)
                            .dontAnimate())
                    .into(itemView.listWorkImageView)

            itemView.listWorkRoleTextView.text = work.role
            itemView.listWorkCompanyTextView.text = context.getString(R.string.work_at, work.name)
            itemView.listWorkDateTextView.text = getFullDatesSpannable(work.startMonth, work.startYear, work.endMonth, work.endYear)
        }
    }

}