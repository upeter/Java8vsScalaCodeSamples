package org.misc;


public class FunctionsExampleOldJ {

	
	
	
	class AuthService {
		public void authLDAP(String credentials) throws RuntimeException {
			// auth
		}

		public void authDB(String credentials) throws RuntimeException {
			// auth
		}
	}

	//=======================================
	//Imperative
	//=======================================

	class ControllerImperative {
		
		private AuthService authService = new AuthService();

		public String handleFlowWithDBAuth(String credentials, String input) {
			if (validInput(input)) {
				// request data from a backend system
				String backendData = "...";
				if (needsAdditionalAuthorization(backendData)) {
					authService.authDB(credentials);
				}
				// more logic here like requesting more data from backend
				// systems
				return "result";
			}
			return "invalid input";
		}
		
		public String handleFlowWithLDAPAuth(String credentials, String input) {
			if (validInput(input)) {
				// request data from a backend system
				String backendData = "...";
				if (needsAdditionalAuthorization(backendData)) {
					authService.authLDAP(credentials);
				}
				// more logic here like requesting more data from backend
				// systems
				return "result";
			}
			return "invalid input";
		}
		
		private boolean needsAdditionalAuthorization(String input) {
			return true;
		}

		private boolean validInput(String input) {
			return true;
		}
	}


	//=======================================
	//Functional
	//=======================================

	interface Authenticator {
		public void authenticate();
	}
	
	class ControllerFunctional {
		private AuthService authService = new AuthService();
		
		public String handleFlowWithLDAPAuth(final String credentials,
				String input) {
			return performGenericFlow(new Authenticator() {
				public void authenticate() {
					authService.authLDAP(credentials);
				}
			}, input);
		}

		public String handleFlowWithDBAuth(final String credentials,
				String input) {
			return performGenericFlow(new Authenticator() {
				public void authenticate() {
					authService.authDB(credentials);
				}
			}, input);
		}

		private String performGenericFlow(Authenticator authenticator,
				String input) {
			if (validInput(input)) {
				// request data from a backend system
				String backendData = "...";
				if (needsAdditionalAuthorization(backendData)) {
					authenticator.authenticate();
				}
				// more logic here like requesting more data from backend
				// systems
				return "result";
			}
			return "invalid input";
		}

		private boolean needsAdditionalAuthorization(String input) {
			return true;
		}

		private boolean validInput(String input) {
			return true;
		}
	}



}
