package controllers

import javax.inject.Inject

import models.Sandwich
import play.api.mvc.{Action, Controller}
import service.SandwichService

class SandwichController @Inject()(sandwichService: SandwichService) extends Controller {
  def sandwiches() = Action {
    val sarnies:List[Sandwich] = sandwichService.sandwiches
    Ok(views.html.sandwiches(sarnies) )
  }
}