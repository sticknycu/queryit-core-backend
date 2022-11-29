package ro.nicolaemariusghergu.queryit.model

import lombok.*
import org.hibernate.Hibernate
import org.hibernate.annotations.NotFound
import org.hibernate.annotations.NotFoundAction
import java.io.Serializable
import java.util.*
import javax.persistence.*

@Getter
@Setter
@Entity
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "trucks")
class Truck : Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotFound(action = NotFoundAction.IGNORE)
    @Column(name = "truck_id", nullable = false)
    private val id: Long? = null

    @Column(name = "serial_number", nullable = false)
    private val serialNumber: String? = null
    override fun equals(o: Any?): Boolean {
        if (this === o) return true
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false
        val truck = o as Truck?
        return id != null && id == truck.id
    }

    override fun hashCode(): Int {
        return Objects.hash(id)
    }
}