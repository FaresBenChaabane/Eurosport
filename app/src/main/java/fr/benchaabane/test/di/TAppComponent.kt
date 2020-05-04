package fr.benchaabane.test.di

import dagger.Component
import fr.benchaabane.presentationlayer.di.AppComponent
import fr.benchaabane.test.di.modules.*
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AppModule::class, NetworkModule::class, RepositoriesModule::class,
        UseCasesModule::class, ViewModelsModule::class]
)
interface TAppComponent : AppComponent