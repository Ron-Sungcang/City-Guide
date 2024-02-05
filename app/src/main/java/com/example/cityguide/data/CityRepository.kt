package com.example.cityguide.data

import com.example.cityguide.R
import com.example.cityguide.model.City
import com.example.cityguide.model.Recommendation

object CityRepository {
    val defaultCity = getCategories()[0]
    val defaultRecommendationList = getRecommendationData()[0]
    val defaultRecommendation = getRecommendationData()[0][0]

    fun getCategories(): List<City> {
        return listOf(
            City(
                id = 0,
                nameRes = R.string.restaurant,
                descriptionRes = R.string.restaurantdesc,
                imageRes = R.drawable.image
            ),
            City(
                id = 1,
                nameRes = R.string.gym,
                descriptionRes = R.string.gymdesc,
                imageRes = R.drawable.gym_structure_1080x675
            ),
            City(
                id = 2,
                nameRes = R.string.cafe,
                descriptionRes = R.string.cafedesc,
                imageRes = R.drawable.coffee
            ),
            City(
                id = 3,
                nameRes = R.string.kid,
                descriptionRes = R.string.kiddesc,
                imageRes = R.drawable.kids_playing
            ),
            City(
                id = 4,
                nameRes = R.string.park,
                descriptionRes = R.string.parkdesc,
                imageRes = R.drawable.parks
            ),
            City(id = 5,
                nameRes = R.string.shop,
                descriptionRes = R.string.shopdesc,
                imageRes = R.drawable.mall
            )
        )
    }
    fun getRecommendationData(): List<List<Recommendation>>{
        return listOf(
            listOf(
                Recommendation(
                    id = 1,
                    nameRes = R.string.craven,
                    addressRes = R.string.cravenadd,
                    descriptionRes = R.string.cravendesc,
                    imageRes = R.drawable.craven
                ),
                Recommendation(
                    id = 2,
                    nameRes = R.string.ymca,
                    addressRes = R.string.ymcaadd,
                    descriptionRes = R.string.ymcadesc,
                    imageRes = R.drawable.ymca_logo_250_x_250
                ),
                Recommendation(
                    id = 3,
                    nameRes = R.string.iron,
                    addressRes = R.string.ironadd,
                    descriptionRes = R.string.irondesc,
                    imageRes = R.drawable.iron_works
                ),
                Recommendation(
                    id = 4,
                    nameRes = R.string.focus,
                    addressRes = R.string.focusadd,
                    descriptionRes = R.string.focusdesc,
                    imageRes = R.drawable.fitness_focus
                ),
                Recommendation(
                    id = 5,
                    nameRes = R.string.motion,
                    addressRes = R.string.motionadd,
                    descriptionRes = R.string.motiondesc,
                    imageRes = R.drawable.motion_fitness
                )
            ),
            listOf(
                Recommendation(
                    id = 6,
                    nameRes = R.string.gofor,
                    addressRes = R.string.goforadd,
                    descriptionRes = R.string.gofordesc,
                    imageRes = R.drawable.goforsushi
                ),
                Recommendation(
                    id = 7,
                    nameRes = R.string.thirteen,
                    addressRes = R.string.thirteenadd,
                    descriptionRes = R.string.thirteendesc,
                    imageRes = R.drawable.thirteenpies
                ),
                Recommendation(
                    id = 8,
                    nameRes = R.string.fuh,
                    addressRes = R.string.fuhadd,
                    descriptionRes = R.string.fuhdesc,
                    imageRes = R.drawable.fuh
                ),
                Recommendation(
                    id = 9,
                    nameRes = R.string.cut,
                    addressRes = R.string.cutadd,
                    descriptionRes = R.string.cutdesc,
                    imageRes = R.drawable.cit
                ),
                Recommendation(
                    id = 10,
                    nameRes = R.string.laspalapas,
                    addressRes = R.string.laspalapasadd,
                    descriptionRes = R.string.laspalapasdesc,
                    imageRes = R.drawable.las_palapas
                )
            ),
            listOf(
                Recommendation(
                    id = 11,
                    nameRes = R.string.city_perks,
                    addressRes = R.string.city_perksadd,
                    descriptionRes = R.string.city_perksdesc,
                    imageRes = R.drawable.city_perks
                ),
                Recommendation(
                    id = 12,
                    nameRes = R.string.sparrow,
                    addressRes = R.string.sparrowadd,
                    descriptionRes = R.string.sparrowdesc,
                    imageRes = R.drawable.sparrow
                ),
                Recommendation(
                    id = 13,
                    nameRes = R.string.christies,
                    addressRes = R.string.christiesadd,
                    descriptionRes = R.string.christiesdesc,
                    imageRes = R.drawable.christies
                ),
                Recommendation(
                    id = 14,
                    nameRes = R.string.museo,
                    addressRes = R.string.museoadd,
                    descriptionRes = R.string.museodesc,
                    imageRes = R.drawable.museo_logo
                ),
                Recommendation(
                    id = 15,
                    nameRes = R.string.drift,
                    addressRes = R.string.driftadd,
                    descriptionRes = R.string.driftdesc,
                    imageRes = R.drawable.driftsidewalk
                )
            ),
            listOf(
                Recommendation(
                    id = 16,
                    nameRes = R.string.kiwanis,
                    addressRes = R.string.kiwanisadd,
                    descriptionRes = R.string.kiwanisdesc,
                    imageRes = R.drawable.kiwanis_memorial_park__spadina_cres_e__saskatoon__505733___25896195710_
                ),
                Recommendation(
                    id = 17,
                    nameRes = R.string.meewasin,
                    addressRes = R.string.meewasinadd,
                    descriptionRes = R.string.meewasindesc,
                    imageRes = R.drawable.meewasin
                ),
                Recommendation(
                    id = 18,
                    nameRes = R.string.victoria,
                    addressRes = R.string.victoriaadd,
                    descriptionRes = R.string.victoriadesc,
                    imageRes = R.drawable.victoria
                ),
                Recommendation(
                    id = 19,
                    nameRes = R.string.silverwood,
                    addressRes = R.string.silverwoodadd,
                    descriptionRes = R.string.silverwooddesc,
                    imageRes = R.drawable.silverwood
                ),
                Recommendation(
                    id = 20,
                    nameRes = R.string.zoofarm,
                    addressRes = R.string.zoofarmadd,
                    descriptionRes = R.string.zoofarmdesc,
                    imageRes = R.drawable.zoofarmpark
                )
            ),
            listOf(
                Recommendation(
                    id = 21,
                    nameRes = R.string.apex,
                    addressRes = R.string.apexadd,
                    descriptionRes = R.string.apexdesc,
                    imageRes = R.drawable.apex_saskatoon
                ),
                Recommendation(
                    id = 22,
                    nameRes = R.string.playland,
                    addressRes = R.string.playlandadd,
                    descriptionRes = R.string.playlanddesc,
                    imageRes = R.drawable.playland
                ),
                Recommendation(
                    id = 23,
                    nameRes = R.string.lily,
                    addressRes = R.string.lilyadd,
                    descriptionRes = R.string.lilydesc,
                    imageRes = R.drawable.prairie_lily
                ),
                Recommendation(
                    id = 24,
                    nameRes = R.string.zoofarm,
                    addressRes = R.string.zoofarmadd,
                    descriptionRes = R.string.zoofarmdesc,
                    imageRes = R.drawable.zoofarmpark
                ),
                Recommendation(
                    id = 25,
                    nameRes = R.string.western,
                    addressRes = R.string.westernadd,
                    descriptionRes = R.string.westerndesc,
                    imageRes = R.drawable.western_development
                )
            ),
            listOf(
                Recommendation(
                    id = 26,
                    nameRes = R.string.midtown,
                    addressRes = R.string.midtownadd,
                    descriptionRes = R.string.midtowndesc,
                    imageRes = R.drawable.midtown_plaza_saskatoon
                ),
                Recommendation(
                    id = 27,
                    nameRes = R.string.centre,
                    addressRes = R.string.centreadd,
                    descriptionRes = R.string.centredesc,
                    imageRes = R.drawable.centre_mall
                ),
                Recommendation(
                    id = 28,
                    nameRes = R.string.lawson,
                    addressRes = R.string.lawsonadd,
                    descriptionRes = R.string.lawsondesc,
                    imageRes = R.drawable.lawsonheights
                ),
                Recommendation(
                    id = 29,
                    nameRes = R.string.preston,
                    addressRes = R.string.prestonadd,
                    descriptionRes = R.string.prestondesc,
                    imageRes = R.drawable.preston
                ),
                Recommendation(
                    id = 30,
                    nameRes = R.string.farmer,
                    addressRes = R.string.farmeradd,
                    descriptionRes = R.string.farmerdesc,
                    imageRes = R.drawable.farmers
                )
            )
        )
    }
}