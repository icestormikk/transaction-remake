package banking

class Bank {
    private val database: BankingDatabase = BankingDatabase()
    private var paymentsCount: Int = 0

    fun processPayment(clientId: Int, amount: Int) : Int {
        database.debit(clientId, amount)
        return ++paymentsCount
    }
}