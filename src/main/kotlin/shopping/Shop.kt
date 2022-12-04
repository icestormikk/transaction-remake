package shopping

import banking.Bank

class Shop {
    private val shoppingDatabase = ShoppingDatabase()

    fun buy(clientId: Int, goodId: Int, bank: Bank) {
        try {
            val invoiceId = bank.processPayment(clientId, shoppingDatabase.productPrice(goodId))
            with (shoppingDatabase) {
                createOrder(goodId, clientId)
                decreaseStock(goodId)
            }
        } catch (ex: Exception) {
            println("Shopping error: $ex")
        }
    }
}