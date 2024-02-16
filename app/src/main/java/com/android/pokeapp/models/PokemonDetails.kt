package com.android.pokeapp.models

import com.android.pokeapp.enums.EnumTags
import com.android.pokeapp.utils.JsonParser
import org.json.JSONObject


data class PokemonDetails(
    var name: String = "",
    var url: String = "",
    var order: Int = -1,
    var weight: Int = -1,
    var abilities: List<PokemonAbility> = listOf(),
    var moves: List<PokemonMove> = listOf(),
    var sprites: PokemonSprite = PokemonSprite(),
    var types: List<PokemonType> = listOf(),
) {
    constructor(json: JSONObject) : this() {
        if (json.has(EnumTags.NAME.value) && !json.isNull(EnumTags.NAME.value)) {
            name = json.getString(EnumTags.NAME.value)
        }
        if (json.has(EnumTags.URL.value) && !json.isNull(EnumTags.URL.value)) {
            url = json.getString(EnumTags.URL.value)
        }
        if (json.has(EnumTags.ORDER.value) && !json.isNull(EnumTags.ORDER.value)) {
            order = json.getInt(EnumTags.ORDER.value)
        }
        if (json.has(EnumTags.WEIGHT.value) && !json.isNull(EnumTags.WEIGHT.value)) {
            weight = json.getInt(EnumTags.WEIGHT.value)
        }
        if (json.has(EnumTags.ABILITIES.value) && !json.isNull(EnumTags.ABILITIES.value)) {
            abilities = JsonParser.getAbilities(json.getJSONArray(EnumTags.ABILITIES.value))
        }
        if (json.has(EnumTags.MOVES.value) && !json.isNull(EnumTags.MOVES.value)) {
            moves = JsonParser.getMoves(json.getJSONArray(EnumTags.MOVES.value))
        }
        if (json.has(EnumTags.SPRITES.value) && !json.isNull(EnumTags.SPRITES.value)) {
            sprites = PokemonSprite(json.getJSONObject(EnumTags.SPRITES.value))
        }
        if (json.has(EnumTags.TYPES.value) && !json.isNull(EnumTags.TYPES.value)) {
            types = JsonParser.getTypes(json.getJSONArray(EnumTags.TYPES.value))
        }
    }
}