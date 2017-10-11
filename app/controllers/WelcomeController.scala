package controllers

import javax.inject.Inject

import play.api.mvc.{Action, Controller}
import service.GreetingService

class WelcomeController @Inject()(greetingService: GreetingService) extends Controller {

  def welcome = Action { implicit request =>
    val greeting = greetingService.greeting
    Ok(views.html.welcome(greeting))
  }
}
