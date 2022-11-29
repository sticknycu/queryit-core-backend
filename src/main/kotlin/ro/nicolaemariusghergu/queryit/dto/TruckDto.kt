package ro.nicolaemariusghergu.queryit.dto

import lombok.*
import org.hibernate.Hibernate
import java.util.*

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
class TruckDto {
    private val id: Long? = null
    private val serialNumber: String? = null
    override fun equals(o: Any?): Boolean {
        if (this === o) return true
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false
        val truck = o as TruckDto?
        return id != null && id == truck.id
    }

    override fun hashCode(): Int {
        return Objects.hash(id)
    }
}