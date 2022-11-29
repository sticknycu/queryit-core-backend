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
@Table(name = "minimarkets")
class MiniMarket : Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotFound(action = NotFoundAction.IGNORE)
    @Column(name = "minimarket_id", nullable = false)
    private val id: Long? = null

    @Column(name = "name")
    private val name: String? = null
    override fun equals(o: Any?): Boolean {
        if (this === o) return true
        if (o == null || javaClass != o.javaClass) return false
        val shelf = o as MiniMarket?
        return id == shelf.id
    }

    override fun hashCode(): Int {
        return Objects.hash(id)
    }
}