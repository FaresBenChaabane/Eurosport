package fr.benchaabane.test.di.modules

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import fr.benchaabane.presentationlayer.ui.AndroidResources
import fr.benchaabane.presentationlayer.ui.Resources
import fr.benchaabane.test.app.TApplication
import javax.inject.Singleton


@Module
class AppModule(private val application: TApplication) {

    @Provides
    @Singleton
    fun provideContext(): Context {
        return application
    }

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return GsonBuilder().create()
    }

    @Provides
    @Singleton
    fun provideResources(context: Context): Resources {
        return AndroidResources(context.resources)
    }
}
