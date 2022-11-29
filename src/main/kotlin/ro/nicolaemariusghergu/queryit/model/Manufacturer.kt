package ro.nicolaemariusghergu.queryit.model

import org.hibernate.annotations.NotFound
import org.hibernate.annotations.NotFoundAction
import javax.persistence.*

@Entity
@Table(name = "manufacturers")
class Manufacturer(
        @ManyToOne
        @JoinColumn(name = "deposit_id")
        @NotFound(action = NotFoundAction.IGNORE)
        val deposit: Deposit? = null,

        @ManyToOne
        @JoinColumn(name = "truck_id")
        @NotFound(action = NotFoundAction.IGNORE)
        val truck: Truck ? = null,

        @Column(name = "name")
        val name: String,

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @NotFound(action = NotFoundAction.IGNORE)
        @Column(name = "manufacturer_id", nullable = false)
        val id: Long ? = null
)