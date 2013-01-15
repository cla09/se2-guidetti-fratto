package session;

import javax.ejb.Local;
import entity.Data;

@Local
public interface GestoreDataLocal {

	Data creaData();
}
