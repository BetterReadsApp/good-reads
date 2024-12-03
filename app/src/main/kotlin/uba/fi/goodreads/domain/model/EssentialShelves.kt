package uba.fi.goodreads.domain.model

enum class EssentialShelves(val shelfName: String) {
    ToRead("To Read"),
    CurrentlyReading("Currently Reading"),
    Read("Read");

    companion object {
        fun isEssentialShelf(name: String): Boolean {
            return entries.any { it.shelfName == name }
        }
    }

}

