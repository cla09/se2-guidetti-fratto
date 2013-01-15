package entity;

import java.io.Serializable;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("admin")
public class Admin extends Profilo implements Serializable{

	private static final long serialVersionUID = 1L;
	
	public Admin(){
		super();
	}

}
