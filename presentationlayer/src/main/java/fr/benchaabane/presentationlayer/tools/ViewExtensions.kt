package fr.benchaabane.presentationlayer.tools

import android.content.Context
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.annotation.StringRes
import androidx.constraintlayout.widget.Group
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import fr.benchaabane.presentationlayer.R


// View

fun View.show() {
    visibility = View.VISIBLE
}

fun View.hide() {
    visibility = View.INVISIBLE
}

fun View.makeGone() {
    visibility = View.GONE
}

fun View.removeOnClickListener() {
    setOnClickListener(null)
}

fun View.findErrorView(): View = findViewById(R.id.error_view)
fun View.findLoadingView(): View = findViewById(R.id.loading_view)

// ViewGroup
fun ViewGroup.inflate(layoutId: Int): View = LayoutInflater.from(context).inflate(layoutId, this, false)



