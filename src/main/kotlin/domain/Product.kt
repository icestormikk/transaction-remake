package domain

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.hibernate.Session
import org.hibernate.annotations.Check
import org.hibernate.annotations.JdbcTypeCode
import org.hibernate.type.SqlTypes

@Entity
@Table(name = "goods")
@Check(constraints = "price > 0 AND stock >= 0")
open class Product(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id", nullable = false)
    open var productId: Int? = null,

    @JdbcTypeCode(SqlTypes.VARCHAR)
    @Column(name = "name", nullable = false)
    open var name: String,

    @JdbcTypeCode(SqlTypes.INTEGER)
    @Column(name = "price", nullable = false, columnDefinition = "INT DEFAULT 0")
    open var price: Int,

    @JdbcTypeCode(SqlTypes.INTEGER)
    @Column(name = "stock", nullable = false, columnDefinition = "INT DEFAULT 0")
    open var stock: Int,
) {
    companion object {
        fun Session.getProductById(productId: Int) : Product =
            get(Product::class.java, productId)
                ?: error("Product with id $productId was not found.")
    }
}