package com.devst.trekclan.dataapi.ArtistDataClasses

data class Artist(
    //  val image: List<String>,
    val mbid: String,
    val name: String,
    val streamable: Int,
    val url: String
)