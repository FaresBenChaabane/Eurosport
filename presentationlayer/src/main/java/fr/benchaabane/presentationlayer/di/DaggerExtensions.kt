package fr.benchaabane.presentationlayer.di

import android.app.Activity
import androidx.fragment.app.Fragment


val Activity.injector: ActivitiesComponent?
    get() = (applicationContext as? AppComponent)?.activitiesComponent()

val Fragment.injector: FragmentsComponent?
    get() = (context?.applicationContext as? AppComponent)?.fragmentsComponent()