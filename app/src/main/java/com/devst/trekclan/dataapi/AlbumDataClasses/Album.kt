package com.devst.trekclan.dataapi.AlbumDataClasses

data class Album(//4-->we don;t need to parse this on our own ; just map the required to the list that is holding reuntdant data
    val artist: Artist,
    //  val image: List<String>,   //dirty json data from api-->eror in parsing
    val mbid: String,
    val name: String,
    val url: String
)