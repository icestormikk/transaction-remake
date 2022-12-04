import banking.Bank
import domain.Client
import shopping.Shop
import utilities.DatabaseUtilities

fun main() {
   try {
      val bank = Bank()
      val shop = Shop()

      with (shop) {
         buy(1, 1, bank)
         buy(3, 3, bank)
         buy(2, 3, bank)
         buy(1, 1, bank)
         buy(1, 1, bank)
      }
   } catch (ex: Exception) {
      println(ex)
   }
}