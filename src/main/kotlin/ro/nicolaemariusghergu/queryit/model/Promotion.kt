package ro.nicolaemariusghergu.queryit.model

import javax.persistence.*
import org.hibernate.annotations.NotFound
import org.hibernate.annotations.NotFoundAction

@Entity
@Table(name = "promotions")
class Promotion (
    @Column(name = "name")
    val name: String,

    @Column(name = "description")
    val description: String,

    @Column(name = "expire_date")
    val expireDate: Long,

    @Column(name = "quantity_needed")
    val quantityNeeded: Int,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotFound(action = NotFoundAction.IGNORE)
    @Column(name = "promotion_id", nullable = false)
    val id: Long? = null
)