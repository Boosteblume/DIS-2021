package de.dis;

import de.dis.data.Contract;
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
}

//Estatemanagement 

public static void showEstateMenu() {
	//login needed
	//Menüoptionen
	final int NEW_Estate = 0;
	final int DEL_Estate = 1;
	final int UP_Estate = 2;
	final int BACK = 3;
	
	//Estateverwaltungsmenü
	Menu maklerMenu = new Menu("Estate-Verwaltung");
	maklerMenu.addEntry("Neues Estate", NEW_Estate);
	maklerMenu.addEntry("Löschen eines Estates", DEL_Estate);
	maklerMenu.addEntry("Updaten eines Estates", UP_Estate);
	maklerMenu.addEntry("Zurück zum Hauptmenü", BACK);
	
	//Verarbeite Eingabe
	while(true) {
		int response = EstateMenu.show();
		
		switch(response) {
			case NEW_Estate:
				newMakler();
				break;
			case DEL_Estate:
				newMakler();
				break;
			case UP_Estate:
				newMakler();
				break;
			case BACK:
				return;
		}
	}

	/**
	 * Legt ein neues Estate an, nachdem der Benutzer
	 * die entprechenden Daten eingegeben hat.
	 */
	public static void NEW_Estate() {
		Makler m = new Makler();
		
		m.setName(FormUtil.readString("Name"));
		m.setAddress(FormUtil.readString("Adresse"));
		m.setLogin(FormUtil.readString("Login"));
		m.setPassword(FormUtil.readString("Passwort"));
		m.save();
		
		System.out.println("Makler mit der ID "+m.getId()+" wurde erzeugt.");
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
		public static void sign_contract() {
			Contract c = new Contract();
			
			c.setcontract_date(FormUtil.readString("Contract Datum"));
			c.setplace(FormUtil.readString("Adresse"));
			c.save();
			
			System.out.println("Vertrag mit der Vertragsnummer "+c.getcontractno()+" wurde erzeugt.");
		}
		/**
		 * Legt ein neues Estate an, nachdem der Benutzer
		 * die entprechenden Daten eingegeben hat.
		 */
		public static void contract_holders() {
			Makler m = new Makler();
			
			m.setName(FormUtil.readString("Name"));
			m.setAddress(FormUtil.readString("Adresse"));
			m.setLogin(FormUtil.readString("Login"));
			m.setPassword(FormUtil.readString("Passwort"));
			m.save();
			
			System.out.println("Makler mit der ID "+m.getId()+" wurde erzeugt.");
		}
		/**
		 * Legt ein neues Estate an, nachdem der Benutzer
		 * die entprechenden Daten eingegeben hat.
		 */
		public static void contract_overview() {
			Makler m = new Makler();
			
			m.setName(FormUtil.readString("Name"));
			m.setAddress(FormUtil.readString("Adresse"));
			m.setLogin(FormUtil.readString("Login"));
			m.setPassword(FormUtil.readString("Passwort"));
			m.save();
			
			System.out.println("Makler mit der ID "+m.getId()+" wurde erzeugt.");
		}
}