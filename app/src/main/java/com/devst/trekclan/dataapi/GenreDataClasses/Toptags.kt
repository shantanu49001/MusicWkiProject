package com.devst.trekclan.dataapi.GenreDataClasses

data class Toptags(
    val _artist: String = "Radiohead",
    val _track: String = "Paranoid Android",
    val tag: List<Tag>
)