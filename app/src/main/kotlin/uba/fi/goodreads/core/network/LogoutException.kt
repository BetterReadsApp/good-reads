package uba.fi.goodreads.core.network

import java.io.IOException

object LogoutException : IOException() {
    private fun readResolve(): Any = LogoutException
}