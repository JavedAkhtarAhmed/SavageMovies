package com.javed.savagemovies.modelclasses


data class MoviesResponses(
    val score: Double,
    val show: Show
)

data class Show(
    val id: Long,
    val url: String,
    val name: String,
    val type: String,
    val language: Language,
    val genres: List<String>,
    val status: Status,
    val runtime: Long? = null,
    val averageRuntime: Long,
    val premiered: String,
    val ended: String? = null,
    val officialSite: String? = null,
    val schedule: Schedule,
    val rating: Rating,
    val weight: Long,
    val network: Network? = null,
    val webChannel: Network? = null,
    val dvdCountry: Any? = null,
    val externals: Externals,
    val image: Image,
    val summary: String,
    val updated: Long,
    val links: Links
)

data class Externals(
    val tvrage: Any? = null,
    val thetvdb: Long? = null,
    val imdb: String? = null
)

data class Image(
    val medium: String?,
    val original: String
)

enum class Language {
    English,
    Japanese,
    Korean
}

data class Links(
    val self: Nextepisode,
    val previousepisode: Nextepisode,
    val nextepisode: Nextepisode? = null
)

data class Nextepisode(
    val href: String
)

data class Network(
    val id: Long,
    val name: String,
    val country: Country
)

data class Country(
    val name: String,
    val code: String,
    val timezone: String
)

data class Rating(
    val average: Double? = null
)

data class Schedule(
    val time: String,
    val days: List<String>
)

enum class Status {
    Ended,
    Running,
    ToBeDetermined
}
