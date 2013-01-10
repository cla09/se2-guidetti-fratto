package commons;

import java.io.Serializable;

public enum EnumSesso implements Serializable {
	M,
	F;
	
	/*
	 * potrebbe essere necessaria una conversione in stringa
	 
	 @Override
	public String toString(){
		if(this.equals(M)){
			return "M";
		}
		else{
			if(this.equals(F)){
				return "F";
			}
			else{
				return "errore";
			}
		}
	}
	*/
}