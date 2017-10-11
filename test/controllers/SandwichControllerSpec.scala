package controllers

import org.scalatestplus.play.PlaySpec
import org.scalatestplus.play.guice.GuiceOneAppPerTest
import play.api.test.FakeRequest
import play.api.test.Helpers.{status, _}

class SandwichControllerSpec extends PlaySpec with GuiceOneAppPerTest {
  "SandwichController" should {
    "inform the user we're sold out when there are no sandwiches" in {
      // Need to specify Host header to get through AllowedHostsFilter
      val request = FakeRequest(GET, "/sandwiches").withHeaders("Host" -> "localhost")
      val home = route(app, request).get

      //sanitation
      status(home) mustBe OK
      contentType(home) mustBe Some("text/html")
      contentAsString(home) must include("<title>Sandwiches</title>")
      contentAsString(home) must include("<h1>Have a look at today's sandwiches</h1>")
    }

    "give a helpful message when sold out" in {
      val controller = new SandwichController(FakeNoSandwichService)
      val result = controller.sandwiches().apply(FakeRequest())
      contentAsString(result) must include("<p>Sorry, we're sold out</p>")
    }

    "show a single sandwich when only one is available" in {
      val controller = new SandwichController(FakeSingleSandwichService)
      val result = controller.sandwiches().apply(FakeRequest())

      contentAsString(result) must not include("<p>Sorry, we're sold out</p>")
      contentAsString(result) must include ("Please choose a sandwich")
      contentAsString(result) must include ("Ham")
      contentAsString(result) must include ("Very tasty")
      contentAsString(result) must include ("£1.55")
    }


    "show multiple sandwiches when more than one is available" in {
      val controller = new SandwichController(FakeMultiSandwichService)
      val result = controller.sandwiches().apply(FakeRequest())

      contentAsString(result) must not include("<p>Sorry, we're sold out</p>")
      contentAsString(result) must include ("Ham")
      contentAsString(result) must include ("Very tasty")
      contentAsString(result) must include ("£1.55")
      contentAsString(result) must include ("Cheese")
      contentAsString(result) must include ("Cheese tastic")
      contentAsString(result) must include ("£2.55")
      contentAsString(result) must include ("Egg")
      contentAsString(result) must include ("Fresh")
      contentAsString(result) must include ("£1.15")
    }
  }
}