package modele;

import java.util.ArrayList;
import controler.Global;
import controler.Controle;
import java.util.Hashtable;
import outils.connexion.Connection;
import javax.swing.JLabel;

/**
 * Gestion du jeu côté serveur
 *
 */
public class JeuServeur extends Jeu implements Global {

	/**
	 * Collection de murs
	 */
	private ArrayList<Mur> lesMurs = new ArrayList<Mur>() ;
	/**
	 * Collection de joueurs
	 */
	private Hashtable<Connection, Joueur> lesJoueurs = new Hashtable<Connection, Joueur>() ;
	
	/**
	 * Constructeur
	 * @param controle
	 */
	public JeuServeur(Controle controle) {
		super.controle = controle;
	}
	
	@Override
	public void connexion(Connection connection) {
		this.lesJoueurs.put(connection, new Joueur(this));
	}

	@Override
	public void reception(Connection connection, Object info) {
		String[] infos = ((String)info).split(STRINGSEPARE);
		String ordre = infos[0];
		switch (ordre) {
		case PSEUDO:
			controle.evenementJeuServeur(AJOUTPANELMURS, connection);
			String pseudo = infos[1];
			int numPerso = Integer.parseInt(infos[2]);
			this.lesJoueurs.get(connection).initPerso(pseudo, numPerso, this.lesJoueurs.values(), this.lesMurs);
			String premierMessage = "***" + pseudo + " vient de se connecter***";
			this.controle.evenementJeuServeur(AJOUTPHRASE, premierMessage);
			break;
		case TCHAT:
			String phrase = infos[1];
			phrase = this.lesJoueurs.get(connection).getPseudo()+ ">" + phrase;
			this.controle.evenementJeuServeur(AJOUTPHRASE, phrase);
			break;
		case ACTION:
			Integer action = Integer.parseInt(infos[1]);
			this.lesJoueurs.get(connection).action(action, this.lesJoueurs.values(), this.lesMurs);
			break;
		}
	}
	
	@Override
	public void deconnexion() {
	}
	/**
	 * envoie ordre d'ajouter jlabeljeu a arene
	 */
	public void ajoutJLabelJeuArene(JLabel jLabel) {
		this.controle.evenementJeuServeur(AJOUTJLABELJEU, jLabel);
	}

	/**
	 * Envoi d'une information vers tous les clients
	 * fais appel plusieurs fois à l'envoi de la classe Jeu
	 */
	public void envoi(Object info) {
		for (Connection connection : this.lesJoueurs.keySet()) {
			super.envoi(connection, info);
		}
	}
	/**
	 * envoie ordre et connexion du joueur
	 */
	public void envoiJeuATous() {
		for (Connection connection : this.lesJoueurs.keySet()) {
			this.controle.evenementJeuServeur(MODIFPANELJEU, connection);
		}
	}

	/**
	 * Génération des murs
	 */
	public void constructionMurs() {
		for (int k=0; k<NBMUR; k++) {
			this.lesMurs.add(new Mur());
			this.controle.evenementJeuServeur(AJOUTMUR, lesMurs.get(lesMurs.size()-1).getjLabel());
		}
		
	}
	
}
