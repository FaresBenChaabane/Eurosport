package fr.benchaabane.presentationlayer.di

import dagger.Subcomponent
import fr.benchaabane.presentationlayer.ui.articles.details.StoryDetailsFragment
import fr.benchaabane.presentationlayer.ui.articles.listing.ArticlesListingFragment

@Subcomponent
interface FragmentsComponent {
    fun inject(fragment: ArticlesListingFragment)
    fun inject(fragment: StoryDetailsFragment)
}