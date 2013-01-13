package utility;

public final class Comunicazione {
	
	public static final String EMAIL_NON_VALIDA = 
			"L'indirizzo di posta elettronica specificato non rispetta il formato email.";
	public static final String NICKNAME_NON_LIBERO = 
			"Il nickname scelto è già in uso! Scegliere un altro nickname e ritentare.";
	public static final String EMAIL_PATTERN = "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@" +
			"(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?";
	
	private Comunicazione() {
		super();
	}

}
