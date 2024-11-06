package uba.fi.goodreads.domain.mocks

import uba.fi.goodreads.domain.model.User

object DomainUsersMock {

    fun getUsers() = listOf(
        User(
            name = "Name",
            lastName = "Last",
            email = "",
            followers = 0,
            following = 0,
            id = 1,
            ratedBooks = emptyList(),
            shelves = emptyList()
        )
    )
}