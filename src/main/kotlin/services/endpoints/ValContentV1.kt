package services.endpoints

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ValContentV1 {

    /**
     * Get content optionally filtered by locale
     */
    @GET("/val/content/v1/contents")
    fun getValContent(@Query("locale") locale: String): Call<ContentDto>
}

data class ContentDto(
    val version: String,
    val characters: List<ContentItemDto>,
    val maps: List<ContentItemDto>,
    val chromas: List<ContentItemDto>,
    val skins: List<ContentItemDto>,
    val skinLevels: List<ContentItemDto>,
    val equips: List<ContentItemDto>,
    val gameModes: List<ContentItemDto>,
    val sprays: List<ContentItemDto>,
    val sprayLevels: List<ContentItemDto>,
    val charms: List<ContentItemDto>,
    val charmLevels: List<ContentItemDto>,
    val playerCards: List<ContentItemDto>,
    val playerTitles: List<ContentItemDto>
)

/**
 * @param localizedNames This field is excluded from the response when a locale is set
 */
data class ContentItemDto(
    val name: String,
    val localizedNames: LocalizedNamesDto,
    val assetName: String
)

data class LocalizedNamesDto(
    val ar_AE: String,
    val de_DE: String,
    val en_GB: String,
    val en_US: String,
    val es_ES: String,
    val es_MX: String,
    val fr_FR: String,
    val id_ID: String,
    val it_IT: String,
    val ja_JP: String,
    val ko_KR: String,
    val pl_PL: String,
    val pt_BR: String,
    val ru_RU: String,
    val th_TH: String,
    val tr_TR: String,
    val vi_VN: String,
    val zh_CN: String,
    val zh_TW: String
)