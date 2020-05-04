package fr.benchaabane.test.app

import android.app.Application
import android.content.ComponentCallbacks2
import android.content.Context
import com.facebook.drawee.backends.pipeline.Fresco
import com.jakewharton.threetenabp.AndroidThreeTen
import fr.benchaabane.commons.android.tools.TSchedulers
import fr.benchaabane.commons.tools.Logger
import fr.benchaabane.presentationlayer.di.AppComponent
import fr.benchaabane.test.BuildConfig
import fr.benchaabane.test.di.DaggerTAppComponent
import fr.benchaabane.test.di.modules.AppModule
import fr.benchaabane.test.tools.LoggerDelegate
import timber.log.Timber

class TApplication : Application(), AppComponent {

    private lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        initLogger()
        initThreeTen()
        initSchedulers()
        initFresco()
        initDaggerGraph()
    }

    override fun onTrimMemory(level: Int) {
        super.onTrimMemory(level)
        if (level != ComponentCallbacks2.TRIM_MEMORY_UI_HIDDEN) {
            val imagePipeline = Fresco.getImagePipeline()
            imagePipeline.clearCaches()
        }
    }

    /* ------------------------------------- */
    /*             AppComponent              */
    /* ------------------------------------- */

    override fun fragmentsComponent() = appComponent.fragmentsComponent()
    override fun activitiesComponent() = appComponent.activitiesComponent()


    private fun initDaggerGraph() {
        appComponent = DaggerTAppComponent.builder().appModule(AppModule(this)).build()
    }

    private fun initLogger() {
        if (BuildConfig.LOG_ENABLE) {
            Timber.plant(Timber.DebugTree())
        }
        Logger.use(LoggerDelegate())
    }

    private fun initFresco() {
        Fresco.initialize(this)
    }

    private fun initSchedulers() {
        TSchedulers.init()
    }

    private fun initThreeTen() {
        AndroidThreeTen.init(this)
    }
}