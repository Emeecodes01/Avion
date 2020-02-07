package com.mobigod.avin.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mobigod.avin.di.scopes.ApplicationScope
import com.mobigod.avin.ui.auth.AuthViewModel
import com.mobigod.avin.ui.flights.FlightViewModel
import com.mobigod.avin.ui.setup.SetupViewModel
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.multibindings.IntoMap
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton
import kotlin.reflect.KClass

/**Created by: Emmanuel Ozibo
//on: 04, 2020-02-04
//at: 16:43*/

@ApplicationScope
class ViewModelFactory @Inject constructor(private val viewModels: MutableMap<Class<out ViewModel>, Provider<ViewModel>>):
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T = viewModels[modelClass]?.get() as T
}

@Target(AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER)
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
@MapKey
internal annotation class ViewModelKey(val value: KClass<out ViewModel>)

@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(AuthViewModel::class)
    internal abstract fun authViewModel(viewModel: AuthViewModel): ViewModel


    @Binds
    @IntoMap
    @ViewModelKey(SetupViewModel::class)
    internal abstract fun setUpViewModel(viewModel: SetupViewModel): ViewModel


    @Binds
    @IntoMap
    @ViewModelKey(FlightViewModel::class)
    internal abstract fun flightsViewModel(viewModel: FlightViewModel): ViewModel

    //Add more ViewModels here
}