package ir.rezazarchi.hamrahorder

import android.app.Application
import ir.rezazarchi.hamrahorder.add.di.addOrderModule
import ir.rezazarchi.hamrahorder.core.maputils.mapNetworkModule
import ir.rezazarchi.hamrahorder.core.network.jsonModule
import ir.rezazarchi.hamrahorder.core.network.retrofitModule
import org.koin.android.ext.koin.androidContext
import org.koin.androix.startup.KoinStartup
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.dsl.koinConfiguration

@OptIn(KoinExperimentalAPI::class)
class MainApplication : Application(), KoinStartup {

    override fun onKoinStartup() = koinConfiguration {
        androidContext(this@MainApplication)
        modules(
            mapNetworkModule,
            addOrderModule,
            jsonModule,
            retrofitModule,
        )
    }

}