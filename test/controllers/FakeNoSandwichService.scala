package controllers

import models.Sandwich
import service.SandwichService

object FakeNoSandwichService extends SandwichService{
  def sandwiches = List()
}

object FakeSingleSandwichService extends SandwichService {
   val hamSarnie = Sandwich("Ham", "Very tasty", 1.55)
   def sandwiches = List(hamSarnie)
}

object FakeMultiSandwichService extends SandwichService {
  val ham = Sandwich("Ham", "Very tasty", 1.55)
  val cheese = Sandwich("Cheese", "Cheese tastic", 2.55)
  val egg = Sandwich("Egg", "Fresh", 1.15)
  def sandwiches = List(ham, cheese, egg)
}
