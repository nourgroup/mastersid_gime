package fr.mastersid.oummadi.stackoverflow

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.internal.managers.ApplicationComponentManager
import dagger.hilt.android.scopes.ActivityScoped
import fr.mastersid.oummadi.stackoverflow.backend.ApiService
import fr.mastersid.oummadi.stackoverflow.repository.DataRepository
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(ActivityComponent::class)
object MyModule {

    @ActivityScoped
    @Provides
    @Named("ApiService")
    fun getDataFromService() = ApiService()

    @ActivityScoped
    @Provides
    fun getDataFromDB(@Named("ApiService") a : ApiService) = DataRepository(a)
}