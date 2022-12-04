package shopping

import domain.Client
import domain.Client.Companion.getClientById
import domain.Order
import domain.Product
import domain.Product.Companion.getProductById
import utilities.DatabaseUtilities

class ShoppingDatabase {
    private val databaseSession = DatabaseUtilities.openSession()

    init {
        fillWithSimpleData(
            Product(name = "Bread", price = 20, stock = 2),
            Product(name = "Salt", price = 50, stock = 5),
            Product(name = "Truffle", price = 150, stock = 1)
        )
    }

    fun productPrice(productId: Int) : Int {
        val product = databaseSession.getProductById(productId)
        return product.price
    }

    fun createOrder(productId: Int, clientId: Int) =
        with (databaseSession) {
            transaction.begin()

            val product = getProductById(productId)
            val client = getClientById(clientId)

            persist(Order(product = product, client = client))
            transaction.commit()
        }

    fun decreaseStock(goodId: Int) =
        with (databaseSession) {
            if (isTheProductInStock(goodId)) {
                transaction.begin()

                val product = getProductById(goodId)
                product.stock -= 1
                merge(product)

                transaction.commit()
            } else {
                println("The product with id $goodId is out of stock")
            }
        }

    private fun isTheProductInStock(productId: Int) : Boolean {
        val product = databaseSession.getProductById(productId)
        return product.stock > 0
    }


    private fun fillWithSimpleData(vararg goods: Product) =
        with (databaseSession) {
            transaction.begin()
            goods.forEach {
                if (it.price <= 0)
                    throw IllegalArgumentException(
                        "The initial price of the product cannot be negative or zero!"
                    )
                if (it.stock < 0)
                    throw IllegalArgumentException(
                        "The initial quantity of goods in stock cannot be non-positive!"
                    )
                persist(it)
            }
            transaction.commit()
        }
}