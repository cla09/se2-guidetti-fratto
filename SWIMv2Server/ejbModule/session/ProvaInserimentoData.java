package session;

import entity.Data;

public class ProvaInserimentoData {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GestoreData gd = new GestoreData();
		Data dataCreata;
	
		
		dataCreata = gd.creaData();
		
		System.out.println("**** la data creata e' *****\n"
				+"timestamp: " + dataCreata.getTimestamp());
	
	
		
	}

}
