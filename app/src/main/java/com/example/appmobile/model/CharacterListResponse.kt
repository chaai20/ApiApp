package com.example.appmobile.model


import com.google.gson.annotations.SerializedName

data class CharacterListResponse(
    @SerializedName("info")
    var info: Info?,
    @SerializedName("results")
    var results: List<CharacterModel>
)