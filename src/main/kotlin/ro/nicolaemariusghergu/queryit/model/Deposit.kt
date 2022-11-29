package ro.nicolaemariusghergu.queryit.model

import javax.persistence.*
import org.hibernate.annotations.NotFound
import org.hibernate.annotations.NotFoundAction

@Entity
@Table(name = "deposits")
class Deposit(
        @Column(name = "name")
        var name: String,

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @NotFound(action = NotFoundAction.IGNORE)
        @Column(name = "deposit_id", nullable = false)
        val id: Long? = null
)