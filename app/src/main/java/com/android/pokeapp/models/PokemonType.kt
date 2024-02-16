package com.android.pokeapp.models

import com.android.pokeapp.enums.EnumTags
import org.json.JSONObject


data class PokemonType(
    var name: String = "",
    var url: String = "",
){
    constructor(json:JSONObject):this(){
        if (json.has(EnumTags.NAME.value) && !json.isNull(EnumTags.NAME.value)){
            name = json.getString(EnumTags.NAME.value)
        }
        if (json.has(EnumTags.URL.value) && !json.isNull(EnumTags.URL.value)){
            url = json.getString(EnumTags.URL.value)
        }
    }
}