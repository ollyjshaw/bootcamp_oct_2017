package service

import com.google.inject.ImplementedBy
import models.Sandwich

class RealSandwichService extends SandwichService {
  def sandwiches: List[Sandwich] = List()
}

@ImplementedBy(classOf[RealSandwichService])
trait SandwichService {
  def sandwiches: List[Sandwich]
}
