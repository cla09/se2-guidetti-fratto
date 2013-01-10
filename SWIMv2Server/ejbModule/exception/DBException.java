package exception;

public class DBException extends Exception {

	private static final long serialVersionUID = 1L;

	public DBException(){
		super("Errore durante il salvataggio su database");
	}
}
