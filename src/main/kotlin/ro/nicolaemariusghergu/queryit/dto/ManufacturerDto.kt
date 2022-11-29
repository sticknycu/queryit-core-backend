package ro.nicolaemariusghergu.queryit.dto

import lombok.*
import java.util.*

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
class ManufacturerDto {
    private val id: Long? = null
    private val name: String? = null
    override fun equals(o: Any?): Boolean {
        if (this === o) return true
        if (o == null || javaClass != o.javaClass) return false
        val that = o as ManufacturerDto?
        return id == that.id
    }

    override fun hashCode(): Int {
        return Objects.hash(id)
    }
}