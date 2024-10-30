package uba.fi.goodreads.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uba.fi.goodreads.data.auth.repositories.SessionRepository
import uba.fi.goodreads.data.auth.repositories.SessionRepositoryImpl
import uba.fi.goodreads.data.books.repositories.BooksRepositoriesImpl
import uba.fi.goodreads.data.books.repositories.BooksRepository
import uba.fi.goodreads.data.shelf.repositories.ShelvesRepository
import uba.fi.goodreads.data.shelf.repositories.ShelvesRepositoryImpl
import uba.fi.goodreads.data.users.repositories.UsersRepository
import uba.fi.goodreads.data.users.repositories.UsersRepositoryImpl
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

    @Binds
    @Singleton
    internal abstract fun bindBooksRepository(
        booksRepository: BooksRepositoriesImpl,
    ): BooksRepository

    @Binds
    @Singleton
    internal abstract fun bindUsersRepository(
        usersRepository: UsersRepositoryImpl,
    ): UsersRepository

}