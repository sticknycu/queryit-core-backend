package ro.nicolaemariusghergu.queryit.dto

import java.math.BigDecimal

data class ProductDto (
    val id: Long,
    val name: String,
    val price: BigDecimal,
    val quantity: Int,
    val iconPath: String,
    val category: CategoryDto,
    val promotion: PromotionDto
)