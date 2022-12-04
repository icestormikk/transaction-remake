package utilities

import domain.Client
import domain.Order
import domain.Product
import org.hibernate.Session
import org.hibernate.SessionFactory
import org.hibernate.boot.registry.StandardServiceRegistryBuilder
import org.hibernate.cfg.Configuration

object DatabaseUtilities {
    private lateinit var sessionFactory: SessionFactory

    fun openSession() : Session {
        if (!this::sessionFactory.isInitialized) {
            with(Configuration()) {
                addAnnotatedClass(Client::class.java)
                addAnnotatedClass(Product::class.java)
                addAnnotatedClass(Order::class.java)
                val builder = StandardServiceRegistryBuilder().applySettings(properties)
                sessionFactory = buildSessionFactory(builder.build())
            }
        }

        return sessionFactory.openSession()
    }
}