package com.example.pokeapi.mapper

import com.example.pokeapi.network.model.*
import com.example.pokeapi.ui.model.*

object PokeMapper {

    fun apiToUIModel(apiModel: PokemonApi) = Pokemon(
        name = apiModel.name,
        baseExperience = apiModel.baseExperience,
        height = apiModel.height,
        isDefault = apiModel.isDefault,
        order = apiModel.order,
        weight = apiModel.weight,
        sprites = apiSpritesToUIModel(apiModel.sprites),
        types = apiModel.types.map { apiPokemonTypesToUIModel(it.type) },
        abilities = apiModel.abilities.map { apiPokemonAbilitiesToUIModel(it) }
    )

    fun apiPokemonAbilitiesToUIModel(apiModel: PokemonAbilitiesApi) = Abilities(
        isHidden = apiModel.isHidden,
        slot = apiModel.slot,
        ability = apiPokemonAbilityToUIModel(apiModel.ability),
    )

    fun apiPokemonAbilityToUIModel(apiModel: PokemonAbilityApi) = Ability(
        name = apiModel.name,
    )

    fun apiSpritesToUIModel(apiModel: SpritesApi) = Sprites(
        backFemale = apiModel.backFemale,
        backShinyFemale = apiModel.backShinyFemale,
        backDefault = apiModel.backDefault,
        frontFemale = apiModel.frontFemale,
        frontShinyFemale = apiModel.frontShinyFemale,
        backShiny = apiModel.backShiny,
        frontDefault = apiModel.frontDefault,
        frontShiny = apiModel.frontShiny,
    )

    fun apiPokemonTypesToUIModel(apiModel: TypeApi) = PokemonTypes(
        name = apiModel.name,
    )

    fun apiPokemonTypeToUIModel(apiModel: PokemonTypeApi) = PokemonType(
        name = apiModel.name,
        damageRelations = apiTypeRelationToUIModel(apiModel.damageRelations)
    )

    fun apiTypeRelationToUIModel(apiModel: TypeRelationsApi) = TypeRelations(
        noDamageTo = apiModel.noDamageTo.map { apiNamedResourceToUIModel(it) },
        halfDamageTo = apiModel.halfDamageTo.map { apiNamedResourceToUIModel(it) },
        doubleDamageTo = apiModel.doubleDamageTo.map { apiNamedResourceToUIModel(it) },
        noDamageFrom = apiModel.noDamageFrom.map { apiNamedResourceToUIModel(it) },
        halfDamageFrom = apiModel.halfDamageFrom.map { apiNamedResourceToUIModel(it) },
        doubleDamageFrom = apiModel.doubleDamageFrom.map { apiNamedResourceToUIModel(it) },
    )

    fun apiNamedResourceToUIModel(apiModel: NamedApiResource) = NamedResource(
        name = apiModel.name,
        url = apiModel.url,
    )

}