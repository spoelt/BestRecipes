package com.example.bestrecipes.network.responses

import com.example.bestrecipes.network.model.RecipeDto
import com.google.gson.annotations.SerializedName

data class RecipeSearchResponse(

    @SerializedName("count")
    var count: Int,

    // you could also add "next" and "previous" if you want to use pagination

    @SerializedName("results")
    var recipes: List<RecipeDto>,
)
