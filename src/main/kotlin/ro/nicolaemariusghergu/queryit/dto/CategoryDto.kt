package ro.nicolaemariusghergu.queryit.dto

import lombok.*

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
class CategoryDto {
    private val id: Long? = null
    private val name: String? = null
    override fun equals(o: Any?): Boolean {
        if (this === o) return true
        if (o == null || javaClass != o.javaClass) return false
        val that = o as CategoryDto?
        return id == that.id
    }

    override fun hashCode(): Int {
        return id?.hashCode() ?: 0
    }
}