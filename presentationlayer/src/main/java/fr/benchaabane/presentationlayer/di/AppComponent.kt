package fr.benchaabane.presentationlayer.di

interface AppComponent {
    fun fragmentsComponent(): FragmentsComponent
    fun activitiesComponent(): ActivitiesComponent
}