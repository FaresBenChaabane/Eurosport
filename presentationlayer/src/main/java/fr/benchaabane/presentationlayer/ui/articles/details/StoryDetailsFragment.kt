package fr.benchaabane.presentationlayer.ui.articles.details

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import fr.benchaabane.presentationlayer.R
import fr.benchaabane.presentationlayer.di.injector
import fr.benchaabane.presentationlayer.ui.BaseFragment
import javax.inject.Inject

class StoryDetailsFragment : BaseFragment(R.layout.fragment_article_details) {

    @Inject lateinit var viewModel: IStoryDetailsViewModel
    private var view: StoryDetailsView? = null


    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity() as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injector?.inject(this)
    }

    override fun afterViewCreated(view: View) {
        this.view = StoryDetailsView(view = view, viewModel = viewModel, lifecycle = this,
        storyId = arguments!!.getInt(ARG_ARTICLE_STORY_ID))
    }

    override fun onDetach() {
        super.onDetach()
        view = null
        (requireActivity() as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)
    }
}

const val ARG_ARTICLE_STORY_ID = ":ARG_ARTICLE_STORY_ID"