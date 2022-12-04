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
@Table(name = "banking_accounts")
@Check(constraints = "balance >= 0")
open class Client(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "client_id", nullable = false)
    open var clientId: Int? = null,

    @JdbcTypeCode(SqlTypes.VARCHAR)
    @Column(name = "client_name", nullable = false)
    open var clientName: String,

    @JdbcTypeCode(SqlTypes.VARCHAR)
    @Column(name = "client_surname", nullable = false)
    open var clientSurname: String,

    @JdbcTypeCode(SqlTypes.INTEGER)
    @Column(name = "balance", nullable = false)
    open var balance: Int
) {
    companion object {
        fun Session.getClientById(clientId: Int) : Client =
            get(Client::class.java, clientId)
                ?: error("Client with id $clientId was not found.")
    }
}
