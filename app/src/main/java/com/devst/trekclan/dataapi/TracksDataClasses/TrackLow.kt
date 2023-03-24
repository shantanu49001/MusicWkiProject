package com.devst.trekclan.dataapi.TracksDataClasses

//classes name we can change
data class TrackLow(
    val artist: TrackArtist,
    val duration: Int,

    val mbid: String,
    val name: String,

    val url: String
)