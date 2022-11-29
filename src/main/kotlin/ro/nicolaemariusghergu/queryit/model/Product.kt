package ro.nicolaemariusghergu.queryit.model

import javax.persistence.*
import org.hibernate.annotations.NotFound
import org.hibernate.annotations.NotFoundAction
import java.math.BigDecimal

@Entity
@Table(name = "products")
class Product(
        @Column(name = "name")
        val name: String,

        @Column(name = "price")
        val price: BigDecimal,

        @Column(name = "quantity")
        var quantity: Int,

        @Column(name = "icon_path")
        val iconPath: String,

        @ManyToOne
        @JoinColumn(name = "category_id")
        @NotFound(action = NotFoundAction.IGNORE)
        val category: Category,

        @ManyToOne(cascade = [CascadeType.MERGE])
        @NotFound(action = NotFoundAction.IGNORE)
        @JoinColumn(name = "promotion_id")
        val promotion: Promotion? = null,

        @ManyToOne
        @JoinColumn(name = "minimarket_id")
        @NotFound(action = NotFoundAction.IGNORE)
        val miniMarket: MiniMarket,

        @ManyToOne
        @JoinColumn(name = "manufacturer_id")
        @NotFound(action = NotFoundAction.IGNORE)
        val manufacturer: Manufacturer,

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "product_id", nullable = false)
        @NotFound(action = NotFoundAction.IGNORE)
        val id: Long? = null
)