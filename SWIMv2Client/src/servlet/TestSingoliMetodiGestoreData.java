package servlet;

import java.io.IOException;
import java.util.GregorianCalendar;
import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Data;

import session.GestoreAbilitaRemote;
import session.GestoreDataRemote;

/**
 * Servlet implementation class TestSingoliMetodiGestoreData
 */
public class TestSingoliMetodiGestoreData extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestSingoliMetodiGestoreData() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			Context ctx = getInitialContext();

			GestoreDataRemote gestoreDataRemoto = (GestoreDataRemote) ctx.lookup("GestoreDataJNDI");

			System.out.println("****** sono nella servlet--- collegamento stabilito **********");
			
			GregorianCalendar c = gestoreDataRemoto.generaTimestamp();
			long a = c.getTimeInMillis();
			System.out.println("*** milli: "+ a);
			
			if(gestoreDataRemoto.controllaEsistenzaTimestamp(a)){
				System.out.println("********c'è******");
			}
			else{
				System.out.println("********non c'è******");
			}
			
			System.out.println("\n\n####### creo una data #####");
			Data d = gestoreDataRemoto.creaData();
			
			System.out.println("timestamp: "+ d.getTimestamp());
			

		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
	
	static public Context getInitialContext() throws Exception{

		Hashtable<String,String> env = new Hashtable<String,String>();

		env.put(Context.INITIAL_CONTEXT_FACTORY, "org.jnp.interfaces.NamingContextFactory");
		env.put(Context.PROVIDER_URL, "localhost:1099");

		return new InitialContext(env);

	}

}