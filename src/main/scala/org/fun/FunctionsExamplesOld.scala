package org.forloops
import java.io.File
import org.intro.Photo

object FunctionsExamplesOld {
  class AuthService {
    def authLDAP(credentials: String) = ()
    def authDB(credentials: String) = ()
  }

  class Controller {
    val authService = new AuthService

    private def performGenericFlow(auth: => Unit, input: String): String = {
      if (validInput(input)) {
        //request data from a backend system
        val backendData = "...";
        if (needsAdditionalAuthorization(backendData)) auth
        //more logic here like requesting more data from backend systems
        "result"
      }
      "invalid input";
    }

    def handleFlowWithDBAuth(credentials: String, input: String) {
      performGenericFlow(authService.authDB(credentials), input)
    }
    def handleFlowWithLDAPAuth(credentials: String, input: String) {
      performGenericFlow(authService.authLDAP(credentials), input)
    }

  }
  def validInput(input: String) = true;
  def needsAdditionalAuthorization(backendData: String) = true;

}