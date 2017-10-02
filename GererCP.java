
/**
*La classe GererCP nous permet de g�rer un affichage de code postaux et de noms de ville
*Avec un argument primaire obligatoire et un argument secondaire d�pendant du primaire 
*
*Quatres arguments primaires (donc quatre commandes) sont possibles dans ce programme
* et correspondent �   :
*<ul>
*<li>C pour afficher(cr�er) une ligne suppl�mentaire CP + nomville </li>
*<li>R pour afficher la correspondance nomVille qui concerne le CP saisi</li>
*<li>U pour modifier le nomVille a l'affichage de la liste pour le CP concern�</li>
*<li>D pour retirer un CP + nomVille du second affichage de liste.</li>
*</ul>
*
*
*@author pierrot97434
*@version 1.0
*
*/


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
		String tableauCP[] = {"97410","97420","97433","97413","97414","","","","",""};
		String tableauNomVilles[] = {"St-Pierre","Port","Salazie","Cilaos","Entre-Deux","","","","",""};
		boolean verifier = false ;
		int position = 0 ;
		int compteur = 0 ;
		
		verifparametres(args);
		
		verifier = verif(args,tableauCP) ;
		
		position = avoirPosition( args,tableauCP);
		
		//compteur nous permet de savoir combien d'�l�ment comporte le tableau pour pouvoir en ajouter sans recalculer le nombre d'�l�ments.
		
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
	*@return le nombre d'�l�ments pr�sents dans le tableauCP
	*
	*/
	//Ici on doit g�rer l'affichage des deux tableaux, et faire cette proc�dure de telle sorte qu'elle aura un argument nous permettant de selectionner en particulier
	//un de ses �l�ments.
	public static int affichListe(String tableauCP[],String tableauNomVilles[],int position ){
		
		int compteur = 0 ;
		
			for(int ind = 0 ; ind < tableauCP.length ; ind++)
			{
				if(!tableauCP[ind].equals("")){
					System.out.println ( tableauCP[ind] + " " + tableauNomVilles[ind]);
					compteur ++;
				}
			}
		
		return compteur;
	}
	
	/**
	* @param args 
	* @param tableauCP
	*
	* @return la position du Code Postal dans le tableau
	*/
	//Ici, dans le cas o� le CP est correct , on marque la position de ce dernier dans le tableau de CP pour pouvoir y acceder facilement avec procCRUD() par la suite
	public static int avoirPosition(String args[],String tableauCP[]){
		
		int ind = 0 ;
		int positionCP = -1;
		
		while (ind < tableauCP.length ){
			if (args[1].equals(tableauCP[ind]))
			{
				positionCP = ind ;
				ind = tableauCP.length;
				
			}
			ind++;
			
		}
		
		return positionCP ;
	}
	
	
	/**
	* @return un bool�en qui nous dit si le CP saisi en deuxieme parametre est present dans le tableau
	*
	*
	*
	* @param args
	* @param tableauCP
	*/
	//Verifie que le parametre args[1] est bien present dans le tableau cp. renvoie un r�sultat bool�en
	public static boolean verif(String args[] ,String tableauCP[] ){
		
		
		int ind = 0 ;
		boolean verifCP = false;
		
		while (ind < tableauCP.length)
		{
			
			if (args[1].equals(tableauCP[ind]))
			{
				verifCP = true;
				ind = tableauCP.length;
			}
			
			
			ind++;
			
		}
		
		return verifCP ;
		
		
		
	}
	
	
	/**

	* @param verifier
	*	est le contenant de la methode verif
	* @param compteur
	* 	compteur nous permet de connaitre le nombre d'�l�ments pr�sents dans le tableauCP 	
	*/
	//dans cette proc, nous g�rons les quatres commandes possibles pour le programme GererCp, a savoir la creation, la lecture, la MAJ et l effacement
	//On gere egalement les cas de doublons pour creation, les cas d'absence pour delete, etc.
	//Puis on r�affiche la liste suivant les modifications effectu�es.
	public static void procCRUD(String args[],String tableauCP[],String tableauNomVilles[],boolean verifier,int position,int compteur){
		
		
		
		switch (args[0])
		{
			case "R" :
				if (!verifier){
					System.out.println("Ce code postal n'a pas encore �t� cr�� ! : " + args[1]);
				}
				else{
					System.out.println(args[1] + " correspond � " + tableauNomVilles[position]);
				}
				affichEtoile();
				break;
			
			case "C" :
				verifVille(args);
				if(verifier){
					System.out.println("Ce code postal existe d�ja : " + tableauCP[position]);
				}
				else{
					System.out.println("creation " + args[1]);
					affichEtoile();
					tableauCP[compteur] = args[1];
					tableauNomVilles[compteur] = args[2];
 					affichListe(tableauCP,tableauNomVilles,position);
				   
					
				}
				affichEtoile();
				
				break;
			
			case "U" :
				
				verifVille(args);
				if(verifier){
					System.out.println("modification " + tableauCP[position]);
					affichEtoile();
					tableauNomVilles[position] = args[2] ;
					affichListe(tableauCP,tableauNomVilles,position);
				}
				else {
					System.out.println(args[1] +" n'existe pas dans nos donn�es ! ");
				}
				
				affichEtoile();
				break;
				
			case "D" :
				
				if (verifier){
					
					System.out.println("effacement de " + tableauCP[position]);
					affichEtoile();
					tableauCP[position] = "";
					tableauNomVilles[position] = "";
					affichListe(tableauCP,tableauNomVilles,position);
					
				}
				
				else {
					
					System.out.println("Ce code postal n'existe pas et ne peut pas �tre effac�!");
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
	//Ici, on procure juste l'affichage souhait� par le client!
	public static void affichEtoile(){
		
		System.out.println("*****");
		
	}
	
	//La proc�dure suivante permet de fermer le programme si jamais les param�tres obligatoires n'ont pas �t� saisis !
	/**
	*
	*/
	public static void verifparametres(String args[]){
		if (args.length == 0){
			System.out.println("Vous avez oubli� les param�tres! lisez la doc ! ");
			System.exit(0);
			
		}
		else if(args.length == 1){
			System.out.println("Vous avez oubli� le param�tre secondaire");
			System.exit(0);
		}
	}
	
	//La proc suivante v�rifie que l'utilisateur a bien saisi le nom de la ville a creer / modifier ! Sinon , il quitte le programme !
	public static void verifVille(String args[]){
		if(args.length < 3 ){
					System.out.println("Vous avez oubli� le nom de la ville !!");
					System.exit(0);
				}
	}
}

	
