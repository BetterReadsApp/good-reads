package uba.fi.goodreads.domain.usecase

import uba.fi.goodreads.domain.mocks.DomainPostMocks
import uba.fi.goodreads.domain.model.Post
import javax.inject.Inject

class GetFeedUseCase @Inject constructor(
    // private val feedRepository: FeedRepository,
) {
    operator fun invoke(): List<Post> {
        // feedRepository.getFeed()
        return DomainPostMocks.getPosts()
    }
}