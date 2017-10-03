
/**
*La classe GererCP nous permet de gérer un affichage de code postaux et de noms de ville
*Avec un argument primaire obligatoire et un argument secondaire dépendant du primaire 
*
*Quatres arguments primaires (donc quatre commandes) sont possibles dans ce programme
* et correspondent à   :
*<ul>
*<li>C pour afficher(créer) une ligne supplémentaire CP + nomville </li>
*<li>R pour afficher la correspondance nomVille qui concerne le CP saisi</li>
*<li>U pour modifier le nomVille a l'affichage de la liste pour le CP concerné</li>
*<li>D pour retirer un CP + nomVille du second affichage de liste.</li>
*</ul>
*
*
*@author pierrot97434
*@version 1.0
*
*/

import java.util.ArrayList;

public class GererCP {
	

	public static void main(String [] args){
		/**
		*
		*@param tableauCP 
		*stocke les codes postaux 
		*
		*@param tableauNomVilles
		*stocke les villes correspondants au CP
		*
		*/
		tableauCP = new ArrayList <String>();
		tableauNomVilles = new ArrayList <String>();
		tableauCP.add("97410");tableauCP.add("97420");tableauCP.add("97433");tableauCP.add("97413");tableauCP.add("97414");
		tableauNomVilles.add("St-Pierre");tableauNomVilles.add("Port");tableauNomVilles.add("Salazie");tableauNomVilles.add("Cilaos");tableauNomVilles.add("Entre-Deux");
		boolean verifier = false ;
		int position = 0 ;
		int compteur = 0 ;
		
		verifparametres(args);
		
		verifier = verif(args,tableauCP) ;
		
		position = avoirPosition( args,tableauCP);
		
		//compteur nous permet de savoir combien d'élément comporte le tableau pour pouvoir en ajouter sans recalculer le nombre d'éléments.
		
		compteur = affichListe(tableauCP,tableauNomVilles,position);
		
		affichEtoile();
		
		procCRUD(args,tableauCP,tableauNomVilles,verifier,position,compteur);
		

	}
	

	/**
	*
	*@param tableauCP
	*	recup les CP
	*@param tableauNomVilles
	*	recup les nomVilles
	*@return le nombre d'éléments présents dans le tableauCP
	*
	*/
	//Ici on doit gérer l'affichage des deux tableaux, et faire cette procédure de telle sorte qu'elle aura un argument nous permettant de selectionner en particulier
	//un de ses éléments.
	public static int affichListe(String tableauCP[],String tableauNomVilles[],int position ){
		
		int compteur = 0 ;
		
			for(int ind = 0 ; ind < tableauCP.size() ; ind++)
			{
					System.out.println ( tableauCP.get(ind) + " " + tableauNomVilles.get(ind));
					compteur ++;
			
			
			
				
			}
		
		return compteur;
	}
	
	/**
	* @param args 
	* @param tableauCP
	*
	* @return la position du Code Postal dans le tableau
	*/
	//Ici, dans le cas où le CP est correct , on marque la position de ce dernier dans le tableau de CP pour pouvoir y acceder facilement avec procCRUD() par la suite
	public static int avoirPosition(String args[],String tableauCP[]){
		
		int ind = 0 ;
		int positionCP = -1;
		
		while (ind < tableauCP.size() ){
			if (args[1].equals(tableauCP.get(ind)))
			{
				positionCP = ind ;
				ind = tableauCP.size();
				
			}
			ind++;
			
		}
		
		return positionCP ;
	}
	
	
	/**
	* @return un booléen qui nous dit si le CP saisi en deuxieme parametre est present dans le tableau
	*
	*
	*
	* @param args
	* @param tableauCP
	*/
	//Verifie que le parametre args[1] est bien present dans le tableau cp. renvoie un résultat booléen
	public static boolean verif(String args[] ,String tableauCP[] ){
		
		
		int ind = 0 ;
		boolean verifCP = false;
		
		ind = tableauCP.indexOf(args[1]);
		
		if (ind > -1 ){
			verifCP = true;
		}
		
		/*while (ind < tableauCP.size())
		{
			
			if (args[1].equals(tableauCP.get(ind)))
			{
				verifCP = true;
				ind = tableauCP.size();
			}
			
			
			ind++;
			
		}*/
		
		return verifCP ;
		
		
		
	}
	
	
	/**

	* @param verifier
	*	est le contenant de la methode verif
	* @param compteur
	* 	compteur nous permet de connaitre le nombre d'éléments présents dans le tableauCP 	
	*/
	//dans cette proc, nous gérons les quatres commandes possibles pour le programme GererCp, a savoir la creation, la lecture, la MAJ et l effacement
	//On gere egalement les cas de doublons pour creation, les cas d'absence pour delete, etc.
	//Puis on réaffiche la liste suivant les modifications effectuées.
	public static void procCRUD(String args[],String tableauCP[],String tableauNomVilles[],boolean verifier,int position,int compteur){
		
		
		
		switch (args[0])
		{
			case "R" :
				if (!verifier){
					System.out.println("Ce code postal n'a pas encore été créé ! : " + args[1]);
				}
				else{
					System.out.println(args[1] + " correspond à " + tableauNomVilles.get(position);
				}
				affichEtoile();
				break;
			
			case "C" :
				verifVille(args);
				if(verifier){
					System.out.println("Ce code postal existe déja : " + tableauCP.get(position));
				}
				else{
					System.out.println("creation " + args[1]);
					affichEtoile();
					tableauCP.add (args[1]);
					tableauNomVilles.add(args[2]);
 					affichListe(tableauCP,tableauNomVilles,position);
				   
					
				}
				affichEtoile();
				
				break;
			
			case "U" :
				
				verifVille(args);
				if(verifier){
					System.out.println("modification " + tableauCP.get(position));
					affichEtoile();
					tableauNomVilles.set(position, args[2]) ;
					affichListe(tableauCP,tableauNomVilles,position);
				}
				else {
					System.out.println(args[1] +" n'existe pas dans nos données ! ");
				}
				
				affichEtoile();
				break;
				
			case "D" :
				
				if (verifier){
					
					System.out.println("effacement de " + tableauCP.get(position));
					affichEtoile();
					
					tableauCP.remove(position);
					tableauNomVilles.remove(position) ;
					
					affichListe(tableauCP,tableauNomVilles,position);
					
				}
				
				else {
					
					System.out.println("Ce code postal n'existe pas et ne peut pas être effacé!");
				}
				
				break;
			
			default :
			    
				System.out.println("Erreur de saisie de commande, recommencez !");
				
			
		}
	}
	/**
	* 
	* @since v1
	*/
	//Ici, on procure juste l'affichage souhaité par le client!
	public static void affichEtoile(){
		
		System.out.println("*****");
		
	}
	
	//La procédure suivante permet de fermer le programme si jamais les paramètres obligatoires n'ont pas été saisis !
	/**
	*/
	public static void verifparametres(String args[]){
		if (args.length == 0){
			System.out.println("Vous avez oublié les paramètres! lisez la doc ! ");
			System.exit(0);
			
		}
		else if(args.length == 1){
			System.out.println("Vous avez oublié le paramètre secondaire");
			System.exit(0);
		}
	}
	
	//La proc suivante vérifie que l'utilisateur a bien saisi le nom de la ville a creer / modifier ! Sinon , il quitte le programme !
	public static void verifVille(String args[]){
		if(args.length < 3 ){
					System.out.println("Vous avez oublié le nom de la ville !!");
					System.exit(0);
				}
	}
}

	
