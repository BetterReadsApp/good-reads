package uba.fi.goodreads.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uba.fi.goodreads.data.auth.repositories.SessionRepository
import uba.fi.goodreads.data.auth.repositories.SessionRepositoryImpl
import uba.fi.goodreads.data.shelf.repositories.ShelvesRepository
import uba.fi.goodreads.data.shelf.repositories.ShelvesRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Binds
    @Singleton
    internal abstract fun bindsSessionRepository(
        sessionRepository: SessionRepositoryImpl,
    ): SessionRepository

    @Binds
    @Singleton
    internal abstract fun bindShelvesRepository(
        shelvesRepository: ShelvesRepositoryImpl,
    ): ShelvesRepository

}