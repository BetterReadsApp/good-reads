package uba.fi.goodreads.domain.mocks

import uba.fi.goodreads.domain.model.Post

object DomainPostMocks {

    fun getPosts() = listOf(
        Post(
            userName = "Paulo",
            content = "He terminado este libro"
        ),
        Post(
            userName = "Camila",
            content = "Por la pagina 100 de este libro, y avanzando!"
        ),
    )
}