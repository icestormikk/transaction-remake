package banking

import domain.Client
import domain.Client.Companion.getClientById
import utilities.DatabaseUtilities

class BankingDatabase {
    private val databaseSession = DatabaseUtilities.openSession()

    init {
        fillSimpleData(
            Client(clientName = "John", clientSurname = "Dowe", balance = 100),
            Client(clientName = "Jane", clientSurname = "Down", balance = 200),
            Client(clientName = "Michael", clientSurname = "Johnes", balance = 10)
        )
    }

    fun debit(clientId: Int, amount: Int) {
        if (isMoneyEnough(clientId, amount)) {
            val client = databaseSession.getClientById(clientId)
            client.balance -= amount
            databaseSession.merge(client)
        }
    }

    private fun isMoneyEnough(clientId: Int, amount: Int) : Boolean {
        val client = databaseSession.getClientById(clientId)
        return client.balance - amount >= 0
    }

    private fun fillSimpleData(vararg clients: Client) =
        with (databaseSession) {
            transaction.begin()
            clients.forEach {
                if (it.balance < 0)
                    throw IllegalArgumentException("The client's initial balance cannot be negative!")
                persist(it)
            }
            transaction.commit()
        }
}