package com.android.pokeapp.models

import com.android.pokeapp.enums.EnumTags
import org.json.JSONObject


data class PokemonSprite(
    var frontDefault: String = "",
    var backDefault: String = "",
){
    constructor(json:JSONObject):this(){
        if (json.has(EnumTags.FRONT_DEFAULT.value) && !json.isNull(EnumTags.FRONT_DEFAULT.value)){
            frontDefault = json.getString(EnumTags.FRONT_DEFAULT.value)
        }
        if (json.has(EnumTags.BACK_DEFAULT.value) && !json.isNull(EnumTags.BACK_DEFAULT.value)){
            backDefault = json.getString(EnumTags.BACK_DEFAULT.value)
        }
    }
}