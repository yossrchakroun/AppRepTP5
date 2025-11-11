package corbaServer;

import java.util.Properties;

import javax.naming.InitialContext;

import org.omg.CORBA.ORB;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;

import corbaBanque.IBanqueRemote;
import corbaBanque.IBanqueRemoteHelper;
import service.BanqueImpl;


public class BanqueServer {
	public static void main(String[] args) {
		try {
			
			ORB orb = ORB.init(args, null); 
			 //POA poa = orb.resolve_initial_references(“RootPOA”); 
			 POA poa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
			 poa.the_POAManager().activate();
			 BanqueImpl od = new BanqueImpl (); 
			 
			// Récupérer le Naming Service CORBA
			 org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
			 NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);


			 // Enregistrer avec CORBA (pas JNDI)
			 
			 org.omg.CORBA.Object ref = poa.servant_to_reference(od);
			 IBanqueRemote banqueRef = IBanqueRemoteHelper.narrow(ref);
			 
			 String name = "BK";
			 NameComponent[] path = ncRef.to_name(name);
			 ncRef.rebind(path, banqueRef);
			 orb.run(); 
			}catch ( Exception e) 
				{e.printStackTrace();}

	}

}
