package com.android.pokeapp.utils


import com.android.pokeapp.enums.EnumTags
import com.android.pokeapp.models.PokemonAbility
import com.android.pokeapp.models.PokemonMove
import com.android.pokeapp.models.PokemonDetails
import com.android.pokeapp.models.PokemonType
import org.json.JSONArray

object JsonParser {

    fun getPokemonList(json: JSONArray): List<PokemonDetails> {
        val listOfPokemon = mutableListOf<PokemonDetails>()
        for (i in 0 until json.length()) {
            listOfPokemon.add(PokemonDetails(json.getJSONObject(i)))
        }
        return listOfPokemon
    }

    fun getAbilities(json: JSONArray): List<PokemonAbility> {
        val listOfAbilities = mutableListOf<PokemonAbility>()
        for (i in 0 until json.length()) {
            if (json.getJSONObject(i).has(EnumTags.ABILITY.value)) {
                val ability = json.getJSONObject(i).getJSONObject(EnumTags.ABILITY.value)
                listOfAbilities.add(PokemonAbility(ability))
            }
        }
        return listOfAbilities
    }

    fun getMoves(json: JSONArray): List<PokemonMove> {
        val listOfMoves = mutableListOf<PokemonMove>()
        for (i in 0 until json.length()) {
            if (json.getJSONObject(i).has(EnumTags.MOVE.value)) {
                val move = json.getJSONObject(i).getJSONObject(EnumTags.MOVE.value)
                listOfMoves.add(PokemonMove(move))
            }
        }
        return listOfMoves
    }

    fun getTypes(json: JSONArray): List<PokemonType> {
        val listOfTypes = mutableListOf<PokemonType>()
        for (i in 0 until json.length()) {
            if (json.getJSONObject(i).has(EnumTags.TYPE.value)) {
                val ability = json.getJSONObject(i).getJSONObject(EnumTags.TYPE.value)
                listOfTypes.add(PokemonType(ability))
            }
        }
        return listOfTypes
    }


}

