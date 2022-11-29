package ro.nicolaemariusghergu.queryit.model

import javax.persistence.*
import org.hibernate.annotations.NotFound
import org.hibernate.annotations.NotFoundAction

@Entity
@Table(name = "trucks")
class Truck(
    @Column(name = "serial_number", nullable = false)
    val serialNumber: String,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotFound(action = NotFoundAction.IGNORE)
    @Column(name = "truck_id", nullable = false)
    val id: Long? = null
)