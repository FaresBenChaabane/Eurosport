package fr.benchaabane.presentationlayer.tools.views

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import fr.benchaabane.presentationlayer.R
import fr.benchaabane.presentationlayer.tools.findErrorView
import fr.benchaabane.presentationlayer.tools.hide
import fr.benchaabane.presentationlayer.tools.show
import fr.benchaabane.presentationlayer.tools.unsafeLazy


class ErrorView(private val view: View,
                title: String,
                icon: Int,
                onRefreshRequested: () -> Unit) {

    private val refreshView by unsafeLazy { view.findViewById<View>(R.id.error_refresh) }

    init {
        view.findViewById<TextView>(R.id.error_title).text = title
        view.findViewById<ImageView>(R.id.error_image).setImageResource(icon)
        refreshView.setOnClickListener {
            onRefreshRequested()
        }
    }

    fun hide() {
        view.hide()
    }

    fun show() {
        view.show()
    }
}

fun errorView(view: View,
              title: String,
              icon: Int,
              onRefreshRequested: () -> Unit) = ErrorView(view.findErrorView(), title, icon, onRefreshRequested)

fun errorView(view: View,
              title: Int,
              icon: Int,
              onRefreshRequested: () -> Unit) = with(view) { ErrorView(findErrorView(), context.getString(title), icon, onRefreshRequested) }