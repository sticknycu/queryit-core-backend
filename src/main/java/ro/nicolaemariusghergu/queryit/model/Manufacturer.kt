package ro.nicolaemariusghergu.queryit.model

import lombok.*
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
@Table(name = "manufacturers")
class Manufacturer : Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotFound(action = NotFoundAction.IGNORE)
    @Column(name = "manufacturer_id", nullable = false)
    private val id: Long? = null

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "deposit_id")
    @NotFound(action = NotFoundAction.IGNORE)
    private val deposit: Deposit? = null

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "truck_id")
    @NotFound(action = NotFoundAction.IGNORE)
    private val truck: Truck? = null

    @Column(name = "name")
    private val name: String? = null
    override fun equals(o: Any?): Boolean {
        if (this === o) return true
        if (o == null || javaClass != o.javaClass) return false
        val that = o as Manufacturer?
        return id == that.id
    }

    override fun hashCode(): Int {
        return Objects.hash(id)
    }
}