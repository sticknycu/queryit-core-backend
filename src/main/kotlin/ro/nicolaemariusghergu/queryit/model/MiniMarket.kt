package ro.nicolaemariusghergu.queryit.model

import org.hibernate.annotations.NotFound
import org.hibernate.annotations.NotFoundAction
import javax.persistence.*

@Entity
@Table(name = "minimarkets")
class MiniMarket(
        @Column(name = "name")
        val name: String,

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @NotFound(action = NotFoundAction.IGNORE)
        @Column(name = "minimarket_id", nullable = false)
        val id: Long? = null
)