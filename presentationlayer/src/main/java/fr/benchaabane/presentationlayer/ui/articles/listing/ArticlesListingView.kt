package fr.benchaabane.presentationlayer.ui.articles.listing

import android.view.View
import androidx.appcompat.widget.AppCompatTextView
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.facebook.drawee.view.SimpleDraweeView
import fr.benchaabane.presentationlayer.R
import fr.benchaabane.presentationlayer.tools.*
import fr.benchaabane.presentationlayer.tools.views.MixedListAdapter
import fr.benchaabane.presentationlayer.tools.views.ViewHolder
import fr.benchaabane.presentationlayer.tools.views.errorView
import fr.benchaabane.presentationlayer.ui.articles.StoryArticleUi
import fr.benchaabane.presentationlayer.ui.articles.VideoArticleUi

class ArticlesListingView(view: View,
                          lifecycle: LifecycleOwner,
                          viewModel: IArticlesListingViewModel,
                          private val onStoryClicked: (Int) -> Unit,
                          private val onVideoClicked: (String) -> Unit) {

    private val articleRecyclerView by unsafeLazy { view.findViewById<RecyclerView>(R.id.view_articles_list) }
    private val loadingView by unsafeLazy { view.findLoadingView() }
    private val errorView by unsafeLazy { errorView(view = view, title = R.string.error_title,
        icon = R.drawable.ic_error) {viewModel.retry()} }

    init {
        viewModel.observeArticlesList().observe(lifecycle, Observer{
            showArticles(it)
        })
        viewModel.observeLoading().observe(lifecycle, Observer{ if (it) loadingView.show() else loadingView.hide() })
        viewModel.observeError().observe(lifecycle, Observer{ if (it) errorView.show() else errorView.hide() })
    }

    private fun showArticles(articles: List<MixedListAdapter.Item>) {
        articleRecyclerView.adapter = MixedListAdapter(articles,
            MixedListAdapter.Binder(
                layoutId = R.layout.item_story_holder,
                createViewHolder = { itemView ->
                    StoryArticleViewHolder(
                        view = itemView,
                        onStoryClicked = { onStoryClicked.invoke(it) })
                }),
            MixedListAdapter.Binder(
                layoutId = R.layout.item_video_holder,
                createViewHolder = { itemView ->
                    VideoArticleViewHolder(
                        view = itemView,
                        onVideoClicked = { onVideoClicked.invoke(it) })
                })
        )
    }

    class StoryArticleViewHolder(view: View, private val onStoryClicked: (Int) -> Unit):
        ViewHolder<StoryArticleUi>(view) {
        private val storyImageView by unsafeLazy { view.findViewById<SimpleDraweeView>(R.id.view_article_image) }
        private val storyTitleView by unsafeLazy { view.findViewById<AppCompatTextView>(R.id.view_article_title) }
        private val sportNameView by unsafeLazy { view.findViewById<AppCompatTextView>(R.id.view_article_sport_name) }
        private val authorNameView by unsafeLazy { view.findViewById<AppCompatTextView>(R.id.view_article_author) }
        private val timeLapsView by unsafeLazy { view.findViewById<AppCompatTextView>(R.id.view_article_time_laps) }

        override fun bind(item: StoryArticleUi) {
            super.bind(item)
            with(item) {
                storyImageView.loadUrl(imageUrl)
                storyTitleView.text = title
                sportNameView.text = sportName
                authorNameView.text = authorName
                timeLapsView.text = timeLapse
                itemView.setOnClickListener { onStoryClicked.invoke(id) }
            }
        }

        override fun unbind() {
            super.unbind()
            itemView.removeOnClickListener()
        }
    }

    class VideoArticleViewHolder(view: View, private val onVideoClicked: (String) -> Unit):
        ViewHolder<VideoArticleUi>(view) {
        private val videoThumbView by unsafeLazy { view.findViewById<SimpleDraweeView>(R.id.view_video_thumbnail) }
        private val videoTitleView by unsafeLazy { view.findViewById<AppCompatTextView>(R.id.view_video_title) }
        private val sportNameView by unsafeLazy { view.findViewById<AppCompatTextView>(R.id.view_video_sport_name) }
        private val viewsNumberView by unsafeLazy { view.findViewById<AppCompatTextView>(R.id.view_video_views) }

        override fun bind(item: VideoArticleUi) {
            super.bind(item)
            with(item) {
                videoThumbView.loadUrl(thumbnailUrl)
                videoTitleView.text = title
                sportNameView.text = sportName
                viewsNumberView.text = views
                itemView.setOnClickListener { onVideoClicked.invoke(streamUrl) }
            }
        }

        override fun unbind() {
            super.unbind()
            itemView.removeOnClickListener()
        }
    }

}