package fr.benchaabane.presentationlayer.ui.articles.details

import android.view.View
import androidx.appcompat.widget.AppCompatTextView
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.facebook.drawee.view.SimpleDraweeView
import fr.benchaabane.presentationlayer.R
import fr.benchaabane.presentationlayer.tools.*
import fr.benchaabane.presentationlayer.tools.views.errorView

class StoryDetailsView(view: View,
                       storyId: Int,
                       viewModel: IStoryDetailsViewModel,
                       lifecycle: LifecycleOwner) {

    private val storyImageView by unsafeLazy { view.findViewById<SimpleDraweeView>(R.id.view_story_image) }
    private val storyTitleView by unsafeLazy { view.findViewById<AppCompatTextView>(R.id.view_story_title) }
    private val sportNameView by unsafeLazy { view.findViewById<AppCompatTextView>(R.id.view_story_sport_name) }
    private val authorNameView by unsafeLazy { view.findViewById<AppCompatTextView>(R.id.view_story_author) }
    private val timeLapsView by unsafeLazy { view.findViewById<AppCompatTextView>(R.id.view_story_time_laps) }
    private val teaserView by unsafeLazy { view.findViewById<AppCompatTextView>(R.id.view_story_teaser) }
    private val loadingView by unsafeLazy { view.findLoadingView() }
    private val errorView by unsafeLazy { errorView(view = view, title = R.string.error_title,
        icon = R.drawable.ic_error) {viewModel.retry()} }

    init {
        viewModel.observeLoading().observe(lifecycle, Observer{ if (it) loadingView.show() else loadingView.hide() })
        viewModel.observeError().observe(lifecycle, Observer{ if (it) errorView.show() else errorView.hide() })
        viewModel.observeStoryDetails().observe(lifecycle, Observer {
            with(it) {
                storyImageView.loadUrl(imageUrl)
                storyTitleView.text = title
                sportNameView.text = sportName
                authorNameView.text = authorName
                timeLapsView.text = timeLapse
                teaserView.text = teaser
            }
        })
        viewModel.fetchStoryDetails(storyId)
    }
}