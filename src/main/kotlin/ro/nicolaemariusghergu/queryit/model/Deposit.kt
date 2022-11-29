package ro.nicolaemariusghergu.queryit.model

import org.hibernate.annotations.NotFound
import org.hibernate.annotations.NotFoundAction
import javax.persistence.*

@Entity
@Table(name = "deposits")
class Deposit(
        @Column(name = "name")
        val name: String,

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @NotFound(action = NotFoundAction.IGNORE)
        @Column(name = "deposit_id", nullable = false)
        val id: Long? = null
)