package domain

import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table

@Entity
@Table(name = "orders")
open class Order(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id", nullable = false)
    open var orderId: Int? = null,

    @ManyToOne(cascade = [CascadeType.REMOVE])
    @JoinColumn(name = "product_id")
    open var product: Product,

    @ManyToOne(cascade = [CascadeType.REMOVE])
    @JoinColumn(name = "client_id")
    open var client: Client
)
