package ro.nicolaemariusghergu.queryit.model

import lombok.Getter
import lombok.Setter
import lombok.ToString
import org.hibernate.annotations.NotFound
import org.hibernate.annotations.NotFoundAction
import java.io.Serializable
import java.math.BigDecimal
import java.util.*
import javax.persistence.*

@Getter
@Setter
@Entity
@ToString
@Table(name = "products")
class Product : Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id", nullable = false)
    @NotFound(action = NotFoundAction.IGNORE)
    private val id: Long? = null

    @Column(name = "name")
    private val name: String? = null

    @Column(name = "price")
    private val price: BigDecimal? = null

    @Column(name = "quantity")
    private val quantity: Int? = null

    @Column(name = "icon_path")
    private val iconPath: String? = null

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "category_id")
    @NotFound(action = NotFoundAction.IGNORE)
    private val category: Category? = null

    @ToString.Exclude
    @ManyToOne(cascade = [CascadeType.MERGE])
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "promotion_id")
    private val promotion: Promotion? = null

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "minimarket_id")
    @NotFound(action = NotFoundAction.IGNORE)
    private val miniMarket: MiniMarket? = null

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "manufacturer_id")
    @NotFound(action = NotFoundAction.IGNORE)
    private val manufacturer: Manufacturer? = null
    override fun equals(o: Any?): Boolean {
        if (this === o) return true
        if (o == null || javaClass != o.javaClass) return false
        val product = o as Product?
        return id == product.id
    }

    override fun hashCode(): Int {
        return Objects.hash(id)
    }
}