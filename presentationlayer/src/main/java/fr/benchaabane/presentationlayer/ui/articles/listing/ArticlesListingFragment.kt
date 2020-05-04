package fr.benchaabane.presentationlayer.ui.articles.listing

import android.content.Context
import android.os.Bundle
import android.view.View
import fr.benchaabane.presentationlayer.R
import fr.benchaabane.presentationlayer.di.injector
import fr.benchaabane.presentationlayer.tools.cast
import fr.benchaabane.presentationlayer.ui.BaseFragment
import fr.benchaabane.presentationlayer.ui.articles.StoryArticleUi
import javax.inject.Inject

class ArticlesListingFragment : BaseFragment(R.layout.fragment_articles_listing) {

    @Inject lateinit var viewModel: IArticlesListingViewModel

    private var view: ArticlesListingView? = null
    private var delegate: Delegate? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        delegate = context.cast()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injector?.inject(this)
    }

    override fun afterViewCreated(view: View) {
        this.view = ArticlesListingView(
            view = view,
            lifecycle = this,
            viewModel = viewModel,
            onVideoClicked = { delegate?.playVideo(it) },
            onStoryClicked = { delegate?.showStoryDetails(it) })
    }

    override fun onDetach() {
        super.onDetach()
        view = null
        delegate = null
    }

    interface Delegate{
        fun playVideo(streamingUrl: String)
        fun showStoryDetails(storyId: Int)
    }
}