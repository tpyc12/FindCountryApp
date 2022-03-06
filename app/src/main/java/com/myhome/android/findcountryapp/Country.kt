package com.myhome.android.findcountryapp

data class Country(
    val name: String,
    val capital: String,
    val population: Long,
    val area: Long,
    val languages: List<Language>,
    val flag: String
)

data class Language(
    val name: String
)