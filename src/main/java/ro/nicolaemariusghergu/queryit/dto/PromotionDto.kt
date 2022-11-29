package ro.nicolaemariusghergu.queryit.dto
data class PromotionDto(val id: Long,
                        val name: String,
                        val description: String? = null,
                        val expireDate: Long,
                        val quantityNeeded: Int
)