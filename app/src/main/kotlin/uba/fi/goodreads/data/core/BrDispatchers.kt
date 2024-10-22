package uba.fi.goodreads.data.core

import javax.inject.Qualifier
import kotlin.annotation.AnnotationRetention.RUNTIME

@Qualifier
@Retention(RUNTIME)
annotation class Dispatcher(val brDispatcher: BrDispatchers)

enum class BrDispatchers {
    Default,
    IO,
}