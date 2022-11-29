package ro.nicolaemariusghergu.queryit.model

import lombok.*
import org.hibernate.annotations.NotFound
import org.hibernate.annotations.NotFoundAction
import java.io.Serializable
import javax.persistence.*

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "promotions")
class Promotion : Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotFound(action = NotFoundAction.IGNORE)
    @Column(name = "promotion_id", nullable = false)
    private val id: Long? = null

    @Column(name = "name")
    private val name: String? = null

    @Column(name = "description")
    private val description: String? = null

    @Column(name = "expire_date")
    private val expireDate: Long? = null

    @Column(name = "quantity_needed")
    private val quantityNeeded: Int? = null
    override fun equals(o: Any?): Boolean {
        if (this === o) return true
        if (o == null || javaClass != o.javaClass) return false
        val promotion = o as Promotion?
        return id == promotion.id
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }
}