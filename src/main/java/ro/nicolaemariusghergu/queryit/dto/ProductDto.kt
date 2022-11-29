package ro.nicolaemariusghergu.queryit.dto

import lombok.*
import java.math.BigDecimal

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
class ProductDto {
    private val id: Long? = null
    private val name: String? = null
    private val price: BigDecimal? = null
    private val quantity: Int? = null
    private val iconPath: String? = null
    private val category: CategoryDto? = null
    private val promotion: PromotionDto? = null
    override fun equals(o: Any?): Boolean {
        if (this === o) return true
        if (o == null || javaClass != o.javaClass) return false
        val that = o as ProductDto?
        return id == that.id
    }

    override fun hashCode(): Int {
        return id?.hashCode() ?: 0
    }
}