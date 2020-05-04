package fr.benchaabane.presentationlayer.tools.views

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import fr.benchaabane.commons.android.tools.toSparseArray
import fr.benchaabane.presentationlayer.tools.inflate


class MixedListAdapter<T : MixedListAdapter.Item>(private val items: List<T>,
                                                  vararg binders: Binder
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val viewHolderCreators = listOf(*binders).toSparseArray({ it.layoutId }, { it.createViewHolder })

    override fun onCreateViewHolder(parent: ViewGroup, layoutId: Int): RecyclerView.ViewHolder {
        return viewHolderCreators[layoutId]?.invoke(parent.inflate(layoutId))
                ?: throw IllegalArgumentException("layout $layoutId is unknown")
    }

    override fun getItemViewType(position: Int): Int {
        return items[position].layoutId
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (position != RecyclerView.NO_POSITION) (holder as ViewHolder<T>).bind(items[position])
    }

    override fun onViewRecycled(holder: RecyclerView.ViewHolder) {
        (holder as ViewHolder<T>).unbind()
    }

    interface Item {
        val layoutId: Int
    }

    class Binder(val layoutId: Int,
                 val createViewHolder: (View) -> ViewHolder<*>
    )
}

abstract class ViewHolder<in T>(view: View) : RecyclerView.ViewHolder(view) {
    open fun bind(item: T) {}
    open fun unbind() {}
}
