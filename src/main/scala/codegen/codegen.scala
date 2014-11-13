package codegen

import java.io.StringWriter
import org.apache.velocity.{VelocityContext, Template}
import org.apache.velocity.app.VelocityEngine
import org.apache.velocity.exception.{ResourceNotFoundException, ParseErrorException, MethodInvocationException}

object VelocityFundamentalPattern extends App {
  var ve = new VelocityEngine()
  //ve.setProperty(VelocityEngine.RUNTIME_LOG_LOGSYSTEM, this)
  ve.init()

  var context = new VelocityContext
  context.put("name", new String("Velocity"))

  var template: Template = null
  try {
     template = ve getTemplate "./src/main/resources/codegen/mytemplate.vm"
  }
  catch {
    case rnfe: ResourceNotFoundException ⇒
      println("Cound not find template")
      System exit -1
    case pee: ParseErrorException ⇒
      println("Problem parsing the template")
      System exit -1
    case mie: MethodInvocationException ⇒
      println("Something invoked in the template threw an exception")
      System exit -1 
    case e: Exception ⇒
      // dunno
      System exit -1
  }
  var sw = new StringWriter
  template.merge(context, sw)
  println(sw)
}


