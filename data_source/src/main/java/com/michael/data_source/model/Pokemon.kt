package com.michael.data_source.model

import com.google.gson.annotations.SerializedName

data class Ability(
    val id: Int,
    val name: String,
    @SerializedName("is_main_series") val isMainSeries: Boolean,
    val generation: NamedApiResource,
    val names: List<Name>,
    @SerializedName("effect_entries") val effectEntries: List<VerboseEffect>,
    @SerializedName("effect_changes") val effectChanges: List<AbilityEffectChange>,
    @SerializedName("flavor_text_entries") val flavorTextEntries: List<AbilityFlavorText>,
    val pokemon: List<AbilityPokemon>
)

data class AbilityEffectChange(
    val effectEntries: List<Effect>,
    val versionGroup: NamedApiResource
)

data class AbilityFlavorText(
    @SerializedName("flavor_text") val flavorText: String,
    val language: NamedApiResource,
    val versionGroup: NamedApiResource
)

data class AbilityPokemon(
    val isHidden: Boolean,
    val slot: Int,
    val pokemon: NamedApiResource
)

data class Characteristic(
    val id: Int,
    val geneModulo: Int,
    val possibleValues: List<Int>,
    val descriptions: List<Description>
)

data class EggGroup(
    val id: Int,
    val name: String,
    val names: List<Name>,
    val pokemonSpecies: List<NamedApiResource>
)

data class Gender(
    val id: Int,
    val name: String,
    val pokemonSpeciesDetails: List<PokemonSpeciesGender>,
    val requiredForEvolution: List<NamedApiResource>
)

data class PokemonSpeciesGender(
    val rate: Int,
    val pokemonSpecies: NamedApiResource
)

data class GrowthRate(
    val id: Int,
    val name: String,
    val formula: String,
    val descriptions: List<Description>,
    val levels: List<GrowthRateExperienceLevel>,
    val pokemonSpecies: List<NamedApiResource>
)

data class GrowthRateExperienceLevel(
    val level: Int,
    val experience: Int
)

data class Nature(
    val id: Int,
    val name: String,
    val decreasedStat: NamedApiResource?,
    val increasedStat: NamedApiResource?,
    val hatesFlavor: NamedApiResource?,
    val likesFlavor: NamedApiResource?,
    val pokeathlonStatChanges: List<NatureStatChange>,
    val moveBattleStylePreferences: List<MoveBattleStylePreference>,
    val names: List<Name>
)

data class NatureStatChange(
    val maxChange: Int,
    val pokeathlonStat: NamedApiResource
)

data class MoveBattleStylePreference(
    val lowHpPreference: Int,
    val highHpPreference: Int,
    val moveBattleStyle: NamedApiResource
)

data class PokeathlonStat(
    val id: Int,
    val name: String,
    val names: List<Name>,
    val affectingNatures: NaturePokeathlonStatAffectSets
)

data class NaturePokeathlonStatAffectSets(
    val increase: List<NaturePokeathlonStatAffect>,
    val decrease: List<NaturePokeathlonStatAffect>
)

data class NaturePokeathlonStatAffect(
    val maxChange: Int,
    val nature: NamedApiResource
)

data class Pokemon(
    val id: Int,
    val name: String,
    @SerializedName("base_experience") val baseExperience: Int,
    val height: Int,
    @SerializedName("is_default") val isDefault: Boolean,
    val order: Int,
    val weight: Int,
    val species: NamedApiResource,
    val abilities: List<PokemonAbility>,
    val forms: List<NamedApiResource>,
    @SerializedName("game_indices") val gameIndices: List<VersionGameIndex>,
    @SerializedName("held_items") val heldItems: List<PokemonHeldItem>,
    val moves: List<PokemonMove>,
    val stats: List<PokemonStat>,
    val types: List<PokemonType>,
    val sprites: PokemonSprites
)

data class PokemonSprites(
    @SerializedName("back_default") val backDefault: String?,
    @SerializedName("back_shiny") val backShiny: String?,
    @SerializedName("front_default") val frontDefault: String?,
    @SerializedName("front_shiny") val frontShiny: String?,
    @SerializedName("back_default_female") val backFemale: String?,
    @SerializedName("back_shiny_female") val backShinyFemale: String?,
    @SerializedName("front_default_female") val frontFemale: String?,
    @SerializedName("front_shiny_female") val frontShinyFemale: String?
)

data class PokemonAbility(
    @SerializedName("is_hidden") val isHidden: Boolean,
    val slot: Int,
    val ability: NamedApiResource
)

data class PokemonHeldItem(
    val item: NamedApiResource,
    @SerializedName("version_details") val versionDetails: List<PokemonHeldItemVersion>
)

data class PokemonHeldItemVersion(
    val version: NamedApiResource,
    val rarity: Int
)

data class PokemonMove(
    val move: NamedApiResource,
    @SerializedName("version_group_details") val versionGroupDetails: List<PokemonMoveVersion>
)

data class PokemonMoveVersion(
    @SerializedName("move_learn_method") val moveLearnMethod: NamedApiResource,
    @SerializedName("version_group") val versionGroup: NamedApiResource,
    @SerializedName("level_learned_at") val levelLearnedAt: Int
)

data class PokemonStat(
    val stat: NamedApiResource,
    val effort: Int,
    @SerializedName("base_stat") val baseStat: Int
)

data class PokemonType(
    val slot: Int,
    val type: NamedApiResource
)

data class LocationAreaEncounter(
    val locationArea: NamedApiResource,
    val versionDetails: List<VersionEncounterDetail>
)

data class PokemonColor(
    val id: Int,
    val name: String,
    val names: List<Name>,
    val pokemonSpecies: List<NamedApiResource>
)

data class PokemonForm(
    val id: Int,
    val name: String,
    val order: Int,
    val formOrder: Int,
    val isDefault: Boolean,
    val isBattleOnly: Boolean,
    val isMega: Boolean,
    val formName: String,
    val pokemon: NamedApiResource,
    val versionGroup: NamedApiResource,
    val sprites: PokemonFormSprites
)

data class PokemonFormSprites(
    val backDefault: String?,
    val backShiny: String?,
    val frontDefault: String?,
    val frontShiny: String?
)

data class PokemonHabitat(
    val id: Int,
    val name: String,
    val names: List<Name>,
    val pokemonSpecies: List<NamedApiResource>
)

data class PokemonShape(
    val id: Int,
    val name: String,
    val awesomeNames: List<AwesomeName>,
    val names: List<Name>,
    val pokemonSpecies: List<NamedApiResource>
)

data class AwesomeName(
    val awesomeName: String,
    val language: NamedApiResource
)

data class PokemonSpecies(
    val id: Int,
    val name: String,
    val order: Int,
    val genderRate: Int,
    val captureRate: Int,
    val baseHappiness: Int,
    val isBaby: Boolean,
    val hatchCounter: Int,
    val hasGenderDifferences: Boolean,
    val formsSwitchable: Boolean,
    val growthRate: NamedApiResource,
    val pokedexNumbers: List<PokemonSpeciesDexEntry>,
    val eggGroups: List<NamedApiResource>,
    val color: NamedApiResource,
    val shape: NamedApiResource,
    val evolvesFromSpecies: NamedApiResource?,
    @SerializedName("evolution_chain") val evolutionChain: ApiResource,
    val habitat: NamedApiResource?,
    val generation: NamedApiResource,
    val names: List<Name>,
    val palParkEncounters: List<PalParkEncounterArea>,
    val formDescriptions: List<Description>,
    val genera: List<Genus>,
    val varieties: List<PokemonSpeciesVariety>,
    @SerializedName("flavor_text_entries") val flavorTextEntries: List<PokemonSpeciesFlavorText>
)

data class PokemonSpeciesFlavorText(
    @SerializedName("flavor_text") val flavorText: String,
    val language: NamedApiResource,
    val version: NamedApiResource
)

data class Genus(
    val genus: String,
    val language: NamedApiResource
)

data class PokemonSpeciesDexEntry(
    val entryNumber: Int,
    val pokedex: NamedApiResource
)

data class PalParkEncounterArea(
    val baseScore: Int,
    val rate: Int,
    val area: NamedApiResource
)

data class PokemonSpeciesVariety(
    val isDefault: Boolean,
    val pokemon: NamedApiResource
)

data class Stat(
    val id: Int,
    val name: String,
    val gameIndex: Int,
    val isBattleOnly: Boolean,
    val affectingMoves: MoveStatAffectSets,
    val affectingNatures: NatureStatAffectSets,
    val characteristics: List<ApiResource>,
    val moveDamageClass: NamedApiResource?,
    val names: List<Name>
)

data class MoveStatAffectSets(
    val increase: List<MoveStatAffect>,
    val decrease: List<MoveStatAffect>
)

data class MoveStatAffect(
    val change: Int,
    val move: NamedApiResource
)

data class NatureStatAffectSets(
    val increase: List<NamedApiResource>,
    val decrease: List<NamedApiResource>
)

data class Type(
    val id: Int,
    val name: String,
    val damageRelations: TypeRelations,
    val gameIndices: List<GenerationGameIndex>,
    val generation: NamedApiResource,
    val moveDamageClass: NamedApiResource?,
    val names: List<Name>,
    val pokemon: List<TypePokemon>,
    val moves: List<NamedApiResource>
)

data class TypePokemon(
    val slot: Int,
    val pokemon: NamedApiResource
)

data class TypeRelations(
    val noDamageTo: List<NamedApiResource>,
    val halfDamageTo: List<NamedApiResource>,
    val doubleDamageTo: List<NamedApiResource>,
    val noDamageFrom: List<NamedApiResource>,
    val halfDamageFrom: List<NamedApiResource>,
    val doubleDamageFrom: List<NamedApiResource>
)

fun PokemonSpecies.generaDetail(): String {
    return genera.find {
        it.language.name == "en"
    }?.genus ?: ""
}

fun PokemonSpecies.flavorDetail(): String {
    return flavorTextEntries.find {
        it.language.name == "en"
    }?.flavorText ?: ""
}

object MockData {

    fun getNamedApiResource() = NamedApiResource("resource", "category", 1, "asdf")
    fun getApiResource() = ApiResource("resource", 1, "")

    fun getPokemon() = Pokemon(
        1,
        "charizard",
        2,
        3,
        false,
        1,
        1,
        getNamedApiResource(),
        listOf(PokemonAbility(false, 1, getNamedApiResource())),
        listOf(getNamedApiResource()),
        listOf(VersionGameIndex(1, getNamedApiResource())),
        listOf(
            PokemonHeldItem(
                getNamedApiResource(),
                listOf(PokemonHeldItemVersion(getNamedApiResource(), 1))
            )
        ),
        listOf(
            PokemonMove(
                getNamedApiResource(),
                listOf(PokemonMoveVersion(getNamedApiResource(), getNamedApiResource(), 1))
            )
        ),
        listOf(PokemonStat(getNamedApiResource(), 1, 1)),
        listOf(PokemonType(1, getNamedApiResource())),
        PokemonSprites("", "", "", "", "", "", "", "")
    )

    fun getPokemonSpecies() = PokemonSpecies(
        1,
        "string",
        1,
        1,
        1,
        1,
        false,
        1,
        false,
        false,
        getNamedApiResource(),
        listOf(PokemonSpeciesDexEntry(1, getNamedApiResource())),
        listOf(getNamedApiResource()),
        getNamedApiResource(),
        getNamedApiResource(),
        getNamedApiResource(),
        getApiResource(),
        getNamedApiResource(),
        getNamedApiResource(),
        listOf(Name("asdf", getNamedApiResource())),
        listOf(PalParkEncounterArea(1, 1, getNamedApiResource())),
        listOf(Description("asdf", getNamedApiResource())),
        listOf(Genus("asdf", getNamedApiResource())),
        listOf(PokemonSpeciesVariety(false, getNamedApiResource())),
        listOf(PokemonSpeciesFlavorText("asdf", getNamedApiResource(), getNamedApiResource()))
    )

    fun getEvolutionChain() = EvolutionChain(
        1,
        getNamedApiResource(),
        getChainLink()
    )

    fun getChainLink() = ChainLink(
        false,
        getNamedApiResource(),
        listOf(getEvolutionDetail()),
        listOf(
            ChainLink(false, getNamedApiResource(), listOf(getEvolutionDetail()), listOf())
        )
    )

    fun getEvolutionDetail() = EvolutionDetail(getNamedApiResource())

    fun getAbility() = Ability(
        1,
        "asdf",
        false,
        getNamedApiResource(),
        listOf(Name("asdf", getNamedApiResource())),
        listOf(VerboseEffect("asdf", "asdf", getNamedApiResource())),
        listOf(
            AbilityEffectChange(
                listOf(Effect("asdf", getNamedApiResource())),
                getNamedApiResource()
            )
        ),
        listOf(AbilityFlavorText("asdf", getNamedApiResource(), getNamedApiResource())),
        listOf(AbilityPokemon(false, 1, getNamedApiResource()))
    )
}