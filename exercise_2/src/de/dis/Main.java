package de.dis;

import de.dis.data.Contract;
import de.dis.data.Estate;
import de.dis.data.Makler;

/**
 * Hauptklasse
 */
public class Main {
	/**
	 * Startet die Anwendung
	 */
	public static void main(String[] args) {
		showMainMenu();
	}
	
	/**
	 * Zeigt das Hauptmenü
	 */
	public static void showMainMenu() {
		//Menüoptionen
		final int MENU_MAKLER = 0;
		//estatemanagement
		//contractmanagement
		final int QUIT = 1;
		
		//Erzeuge Menü
		Menu mainMenu = new Menu("Hauptmenü");
		mainMenu.addEntry("Makler-Verwaltung", MENU_MAKLER);
		mainMenu.addEntry("Beenden", QUIT);
		
		//Verarbeite Eingabe
		while(true) {
			int response = mainMenu.show();
			
			switch(response) {
				case MENU_MAKLER:
					showMaklerMenu();
					break;
				//estate
				//contract
				case QUIT:
					return;
			}
		}
	}
	
	/**
	 * Zeigt die Maklerverwaltung
	 */
	public static void showMaklerMenu() {
		//Menüoptionen
		final int NEW_MAKLER = 0;
		final int BACK = 1;
		
		//Maklerverwaltungsmenü
		Menu maklerMenu = new Menu("Makler-Verwaltung");
		maklerMenu.addEntry("Neuer Makler", NEW_MAKLER);
		maklerMenu.addEntry("Zurück zum Hauptmenü", BACK);
		
		//Verarbeite Eingabe
		while(true) {
			int response = maklerMenu.show();
			
			switch(response) {
				case NEW_MAKLER:
					newMakler();
					break;
				case BACK:
					return;
			}
		}
	}
	
	/**
	 * Legt einen neuen Makler an, nachdem der Benutzer
	 * die entprechenden Daten eingegeben hat.
	 */
	public static void newMakler() {
		Makler m = new Makler();
		
		m.setName(FormUtil.readString("Name"));
		m.setAddress(FormUtil.readString("Adresse"));
		m.setLogin(FormUtil.readString("Login"));
		m.setPassword(FormUtil.readString("Passwort"));
		m.save();
		
		System.out.println("Makler mit der ID "+m.getId()+" wurde erzeugt.");
	}
	public static void changeMakler() {
		Makler m = new Makler();
		
		m.setName(FormUtil.readString("Name"));
		m.setAddress(FormUtil.readString("Adresse"));
		m.setLogin(FormUtil.readString("Login"));
		m.setPassword(FormUtil.readString("Passwort"));
		m.save();
		
		//System.out.println("Makler mit der ID "+m.getId()+" wurde erzeugt.");
}

//Estatemanagement 

public static void showEstateMenu() {
	//login needed
	//Menüoptionen
	final int new_estate = 0;
	final int del_estate = 1;
	final int up_estate = 2;
	final int BACK = 3;
	
	//Estateverwaltungsmenü
	Menu EstateMenu = new Menu("Estate-Verwaltung");
	EstateMenu.addEntry("Neues Estate", new_estate);
	EstateMenu.addEntry("Löschen eines Estates", del_estate);
	EstateMenu.addEntry("Updaten eines Estates", up_estate);
	EstateMenu.addEntry("Zurück zum Hauptmenü", BACK);} 
	
	//Verarbeite Eingabe
	// while(true) {
	// 	int response = EstateMenu.show();
		
	// 	switch(response) {
	// 		case new_estate:
	// 			new_estate();
	// 			break;
	// 		case del_estate:
	// 			del_estate();
	// 			break;
	// 		case up_estate:
	// 			up_estate();
	// 			break;
	// 		case BACK:
	// 			return;
	// 	}
	// }

	/**
	 * Legt ein neues Estate an, nachdem der Benutzer
	 * die entprechenden Daten eingegeben hat.
	 */
	// public static void new_estate() {
	// 	Estate e = new Estate();
		
	// 	e.setCity(FormUtil.readString("City"));
	// 	e.setPostal_Code(FormUtil.readString("Postal_Code"));
	// 	e.setStreet(FormUtil.readString("Street"));
	// 	e.setStreet_Number(FormUtil.readString("Street_umber");

	// 	e.setSquare_Area(e.readFloat();
	// 	e.setAgent(FormUtil.readInt("Agent"));
	// 	e.save();
		
	// 	System.out.println("Makler mit der ID "+e.getId()+" wurde erzeugt.");
	// }


	public static void del_estate() {
		// empty
	}

	public static void up_estate() {
		// empty
	}
	//Contract management
	public static void showContractMenu() {
		//Menüoptionen
		final int contract_holders = 0;
		final int sign_contract = 1;
		final int contract_overview = 2;
		final int BACK = 3;
		
		//Vertragsmenü
		Menu ContractMenu = new Menu("Vertrags-Verwaltung");
		ContractMenu.addEntry("Neue Person", contract_holders);
		ContractMenu.addEntry("Signieren eines Vertrages", sign_contract);
		ContractMenu.addEntry("Vertragsuebersicht", contract_overview);
		ContractMenu.addEntry("Zurück zum Hauptmenü", BACK);
		
		//Verarbeite Eingabe
		while(true) {
			int response = ContractMenu.show();
			
			switch(response) {
				case contract_holders:
					newMakler();
					break;
				case sign_contract:
					newMakler();
					break;
				case contract_overview:
					newMakler();
					break;
				case BACK:
					return;
			}
		}
	
		/**
		 * Legt ein neuen Vertrag an, nachdem der Benutzer
		 * die entprechenden Daten eingegeben hat.
		 */
		// public static void sign_contract() {
		// 	Contract c = new Contract();
			
		// 	c.setcontract_date(FormUtil.readString("Contract Datum"));
		// 	c.setplace(FormUtil.readString("Adresse"));
		// 	c.save();
			
		// 	System.out.println("Vertrag mit der Vertragsnummer "+c.getcontractno()+" wurde erzeugt.");
		// }
		/**
		 * Zeigt die Vertragspartner.
		 */
// 		public static void contract_holders() {
// 			Contract c = new Contract();
			
// 			c.setName(FormUtil.readString("Name"));
// 			c.setAddress(FormUtil.readString("Adresse"));
// 			c.setLogin(FormUtil.readString("Login"));
// 			c.setPassword(FormUtil.readString("Passwort"));
// 			c.save();
			
// 			System.out.println("Makler mit der ID "+m.getId()+" wurde erzeugt.");
// 		}
// 		/**
// 		 * Zeigt eine übersicht aller Verträge an.
// 		 */
// 		public static void contract_overview() {
			
// 			Contract.getcontractno(FormUtil.readInt("Name"));
// 			Contract.getcontract_date(FormUtil.readString("Adresse"));
// 			Contract.getplace(FormUtil.readString("Login"));
			
// 			System.out.println("Makler mit der ID "+Contract.getcontractno()+" wurde erzeugt.");
// 		}
// }