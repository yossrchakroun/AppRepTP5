package service;

import java.util.ArrayList;
import java.util.List;

import corbaBanque.Compte;
import corbaBanque.IBanqueRemotePOA;

//servant contient la logique metier

public class BanqueImpl extends IBanqueRemotePOA {
	
	 private List <Compte> tabComptes = new ArrayList();
	 
	 //Constructeur - Initialise la structure de données
    public BanqueImpl() {
        System.out.println("Servant BanqueImpl initialisé.");
    }

	@Override
	public void creerCompte(Compte cpte) {
		// Vérifier si le compte existe déjà
        for (Compte c : tabComptes) {
            if (c.code == cpte.code) {
                System.out.println("ERREUR : Le compte " + cpte.code + " existe déjà !");
                return;
            }
        }
        
        // Ajouter le nouveau compte
        tabComptes.add(cpte);
        System.out.println("Compte créé avec succès : Code=" + cpte.code + "\n"+
                         ", Solde=" + cpte.solde + " €");
    }

	@Override
	public void verser(float mt, int code) {
		// TODO Auto-generated method stub
		Compte compteExistant = null;
		for(Compte c:tabComptes) {
			if (c.code==code)
			{
				compteExistant=c;
				break;
			}
		}
		if (compteExistant==null) {
			System.out.println("ERREUR : Le compte " + code + " n'existe pas !");
            return;
		}
		compteExistant.solde+=mt;
		System.out.println("Versement effectuer : "+ mt+ "£ sur le compte : "+ code +"\n"+ "nouvel solde : " + compteExistant.solde);
    }

	@Override
	public void retrait(float mt, int code) {
		// TODO Auto-generated method stub
		Compte compteExistant = null;
		for(Compte c:tabComptes) {
			if (c.code==code)
			{
				compteExistant=c;
				break;
			}
		}
		if (compteExistant==null) {
			System.out.println("ERREUR : Le compte " + code + " n'existe pas !");
            return;
		}
		
		if(compteExistant.solde<mt) {
			System.out.println("ERREUR : Solde insuffisant !");
            return;
		}
		
		compteExistant.solde-=mt;
		System.out.println("Retrait effectuer : "+ mt+ "£ sur le compte : "+ code +"\n"+ "nouvel solde : " + compteExistant.solde);
	}

	@Override
	public Compte getCompte(int code) {
		// TODO Auto-generated method stub
		for (Compte c : tabComptes) {
            if (c.code == code) {
                System.out.println("Consultation du compte : "+ code + " \n" + "Solde = " +c.solde + "£");
                return c;
            }
        }
		System.out.println("ERREUR : Le compte " + code + " n'existe pas !");
		return null;
	}

	@Override
	public Compte[] getComptes() {
		// TODO Auto-generated method stub
		System.out.println("Recuperation de touts les comptes");
		// Convertir l'ArrayList en tableau
        Compte[] tableauComptes = new Compte[tabComptes.size()];
        tableauComptes = tabComptes.toArray(tableauComptes);
        
        return tableauComptes;			
		}

	@Override
	public double conversion(float mt) {
		// Taux de conversion EUR -> TND (exemple : 1 EUR = 3.3 TND)
        final double TAUX_EUR_TO_TND = 3.3;
        
        double montantConverti = mt * TAUX_EUR_TO_TND;
        
        System.out.println("Conversion : " + mt + " EUR = " + 
                         montantConverti + " TND");
        
        return montantConverti;
	}

}
