package Modele;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

import Vue.AffichageJeu;


public class Route extends Observable{
	
	/****************CONSTANTES****************/
	public final static int POSITIONXMOTO = 100;
	public final static int POSITIONHORIZON = 180;
	
	public final static int LARGEURMINROUTE = 50;
	public final static int LARGEURMAXROUTE = 150;
	
	public final static int HAUTEURMINROUTE = 100;
	public final static int HAUTEURMAXROUTE = 150;
	
	public final static int bordureMinX = 150;
	public final static int plageLargeurRoute = LARGEURMAXROUTE - LARGEURMINROUTE;
	
	public final static int departXDroit = Moto.POSITIONXMOTO + LARGEURMAXROUTE/2;
	public final static int departXGauche = Moto.POSITIONXMOTO - LARGEURMAXROUTE/2;
	
	public final static int TAILLEAVANCEE = 10;
	public final static int ACCELERATION = 5;
	
	public final static int VITESSEMAX = 300;
	
	
	/****************ATTRIBUTS****************/
	private ArrayList<Point> listePointsG;
	private ArrayList<Point> listePointsD;
	private Moto moto;
	private int horizon;
	//score = nombre de kilometre parcourus
	private int kilometre;
	
	private ArrayList<Point> listeObstacles;
	private ArrayList<Point> listeCheckpoints;
	
	
	/****************CONSTRUCTEUR****************/
	public Route(Moto m) {
		this.kilometre = 0;
		this.horizon = POSITIONHORIZON;
		this.moto = m;
		this.listePointsG = new ArrayList<Point>();
		this.listePointsD = new ArrayList<Point>();
		
		Point departG = new Point(departXGauche, Vue.AffichageJeu.HAUTAFFICHAGE);
		this.listePointsG.add(departG);
		Point departD = new Point(departXDroit, Vue.AffichageJeu.HAUTAFFICHAGE);
		this.listePointsD.add(departD);
		
		
		///////////////////////////INITIALISATION DES POINTS DE LA LIGNE COTE DROIT/////////////////////////////////
		int xG = (departG.x);
		int yG = (departG.y);
		Random r = new Random();
		
		//int profondeur = 0;
		
		while(yG > -30) {
			xG = r.nextInt(plageLargeurRoute) + bordureMinX;
			//a chaque creation d'ordonnee, on decremente
			yG = yG - r.nextInt(HAUTEURMAXROUTE) - HAUTEURMINROUTE;
			Point newPoint = new Point(xG, yG);
			listePointsG.add(newPoint);
		}
		
		
		/*
		if(this.getListePointsG().get(this.getListePointsG().size()-1).getY() < POSITIONHORIZON) {
			listePointsG.remove(this.getListePointsG().size()-1);
		}
		//ajout en dernier du point sur l'horizon
		listePointsG.add(new Point(r.nextInt(plageLargeurRoute) + bordureMinX, POSITIONHORIZON));
		*/
		
		
		///////////////////////////INITIALISATION DES POINTS DE LA LIGNE COTE DROIT/////////////////////////////////
		int xD = 0;
		int yD = 0;
		
		for(int i = 0; i < this.getListePointsG().size(); i++) {
			xD = (this.getListePointsG().get(i).x + LARGEURMAXROUTE + 80);
			yD = this.getListePointsG().get(i).y;
			Point newPoint = new Point(xD, yD);
			this.getListePointsD().add(newPoint);
		}
		
		this.affiche_listePoints();
		
	///////////////////////////INITIALISATION DES OBSTACLES/////////////////////////////////
	this.listeObstacles = new ArrayList<Point>();
	Point newObstacle;
	for (int i = 0; i < 6; i++) {
		newObstacle = new Point(r.nextInt(AffichageJeu.LARGAFFICHAGE), r.nextInt(AffichageJeu.HAUTAFFICHAGE));
	}
		
	///////////////////////////INITIALISATION DES CHECKPOINTS/////////////////////////////////
	this.listeCheckpoints = new ArrayList<Point>();
	Point newCheckpoints;
	}
	
	

	/****************METHODES****************/
	public ArrayList<Point> getListePointsG() { return listePointsG; }
	public ArrayList<Point> getListePointsD() { return listePointsD; }
	public int getKilometre() { return kilometre; }
	public int getHorizon() { return horizon; }
	public Moto getMoto() { return moto; }
	
	/**
	 * methode getPoint() : renvoie le Point situe a l'indice i de l'ArrayList listePoints
	 * @param i un indice int
	 * @return Point
	 */
	public Point getPointG(int i) { return this.listePointsG.get(i); }
	
	public void setListePointsG(ArrayList<Point> listePoints) { this.listePointsG = listePoints; }
	public void setListePointsD(ArrayList<Point> listePointsParallele) { 
		this.listePointsD = listePointsParallele; 
	}
	public void setKilometre(int kilometre) { this.kilometre = kilometre; }
	public void setHorizon(int horizon) { this.horizon = horizon; }
	public void setMoto(Moto moto) { this.moto = moto; }

	public ArrayList<Point> getListeObstacles() {
		return listeObstacles;
	}
	public void setListeObstacles(ArrayList<Point> listeObstacles) {
		this.listeObstacles = listeObstacles;
	}
	public ArrayList<Point> getListeCheckpoints() {
		return listeCheckpoints;
	}
	public void setListeCheckpoints(ArrayList<Point> listeCheckPoints) {
		this.listeCheckpoints = listeCheckPoints;
	}
	
	/**
	 * methode setPointG() : modifie le Point situe a l'indice i de l'ArrayList listePointsG
	 * @param i un indice int
	 * @param pt le Point a substituer
	 */
	public void setPointAtG(int i, Point pt) {
		this.listePointsG.set(i, pt);
	}
	
	/**
	 * methode setPointD() : modifie le Point situe a l'indice i de l'ArrayList listePointsD
	 * @param i un indice int
	 * @param pt le Point a substituer
	 */
	public void setPointAtD(int i, Point pt) {
		this.listePointsD.set(i, pt);
	}
	
	
	/**
	 * methode affiche_listePoints() : affichage d'une liste de Points
	 */
	public void affiche_listePoints() {
		for (int i = 0; i < this.getListePointsG().size(); i++) {
			System.out.printf("Point %d = (%d, %d)\n", i, this.getListePointsG().get(i).x, 
															this.getListePointsG().get(i).y);
		}
	}
	
	public void affiche_liste(ArrayList<Point> list) {
		for (int i = 0; i < this.getListePointsG().size(); i++) {
			System.out.printf("Point %d = (%d, %d)\n", i, this.getListePointsG().get(i).x, 
															this.getListePointsG().get(i).y);
		}
	}
	
	
	/**
	 * methode avanceRoute() :
	 * modifie les listes listesPointsG et listesPointsG en incrementant leur ordonnee
	 */
	public void avanceRoute() {
		int x;
		int y;
		
		//deplacement des points de la ligne gauche
		ArrayList<Point> newListeG = new ArrayList<Point>();
		for(int i = 0; i < this.getListePointsG().size(); i++) {
			x = (int) (this.getListePointsG().get(i).x);
			y = (int) (this.getListePointsG().get(i).y + TAILLEAVANCEE);
			newListeG.add(new Point(x, y));
		}
		this.setListePointsG(newListeG);
		
		//deplacement des points de la ligne droite
		ArrayList<Point> newListeD = new ArrayList<Point>();
		for(int i = 0; i < this.getListePointsD().size(); i++) {
			x = (int) (this.getListePointsD().get(i).x);
			y = (int) (this.getListePointsD().get(i).y + TAILLEAVANCEE);
			newListeD.add(new Point(x, y));
		}
		this.setListePointsD(newListeD);
		
		this.setKilometre(this.getKilometre() + 1);
		
		this.notifyObservers();
	}
	
	
	/**
	 * methode removePointInvisible() : modifie la listePoints en supprimant le premier Point 
	 * situe en dehors de la fenetre d'affichage
	 */
	public void removePointInvisible() {
		//si le premier Point et le deuxieme Point sont en dehors de l'affichage
		if(this.getListePointsG().get(0).y > Vue.AffichageJeu.HAUTAFFICHAGE && 
				this.getListePointsG().get(1).y > Vue.AffichageJeu.HAUTAFFICHAGE) {
			//on retire le premier Point de la listePoints
			this.getListePointsG().remove(0);
			this.getListePointsD().remove(0);
			this.notifyObservers();
		}
	}
	
	
	/**
	 * methode addPointInvisible() : modifie la listePoints en ajoutant un Point de coordonnee aleatoire
	 * a la fin, si le dernier Point a une ordonnee superieure a l'horizon
	 */
	public void addPointInvisible() {
		
		//si l'ordonnee du dernier Point de listePointsG est superieure a -30
		if(this.getListePointsG().get(this.getListePointsG().size()-1).y > -30) {
			
			Random r = new Random();
			int xG = r.nextInt(plageLargeurRoute) + bordureMinX;
			
			//a chaque creation d'ordonnee, on decremente
			//int yG = (int) this.getListePointsG().get(this.getListePointsG().size()-1).getY();
			int yG = this.getPointG(this.getListePointsG().size()-1).y;
			yG = yG - r.nextInt(HAUTEURMAXROUTE) - HAUTEURMINROUTE;
			System.out.printf("ordonnee cree : %d\n", yG);
			this.listePointsG.add(new Point(xG, yG));
			
			//affiche_listePoints();
			
			int xD = (this.getListePointsG().get(this.getListePointsG().size()-1).x + LARGEURMAXROUTE + 80);
			int yD = this.getListePointsG().get(this.getListePointsG().size()-1).y;
			this.listePointsD.add(new Point(xD, yD));

			/*
			int notLast = this.getListePointsG().size()-2;
			System.out.printf("Avant dernier point = (%f, %f)\n", this.getListePointsG().get(notLast).getX(), 
					this.getListePointsG().get(notLast).getY());
			
			int last = this.getListePointsG().size()-1;
			System.out.printf("Dernier point = (%f, %f)\n", this.getListePointsG().get(last).getX(), 
					this.getListePointsG().get(last).getY());
			this.notifyObservers();
			*/
		}
	}
	
	
	
	/**
	 * methode addPointInvisible() : modifie la listePoints en ajoutant un Point de coordonnee aleatoire
	 * a la fin, si le dernier Point a une ordonnee superieure a l'horizon
	 */
	public void addPointInvisible2() {

		//si l'ordonnee du dernier Point de listePointsG est superieure a -30
		if(this.getListePointsG().get(this.getListePointsG().size()-1).y > POSITIONHORIZON) {
			System.out.printf("y : %d\n", this.getListePointsG().get(this.getListePointsG().size()-1).y);

			
			Random r = new Random();
			int xG = r.nextInt(plageLargeurRoute) + bordureMinX;
			
			//a chaque creation d'ordonnee, on decremente
			//int yG = (int) this.getListePointsG().get(this.getListePointsG().size()-1).getY();
			int yG = this.getPointG(this.getListePointsG().size()-1).y;
			//yG = yG - r.nextInt(HAUTEURMAXROUTE) - HAUTEURMINROUTE;
			yG =  1;
			//System.out.printf("ordonnee before : %d\n", this.getListePointsG().size());

			this.listePointsG.add(new Point(xG, yG));
			//System.out.printf("ordonnee after : %d\n", this.getListePointsG().size());

			//affiche_listePoints();
			
			int yD = yG;
			//this.getListePointsG().get(this.getListePointsG().size()-1).y;
			this.listePointsD.add(new Point(xG+200, yD));
			//System.out.printf("ordonnee cree : %d\n", this.getListePointsG().size());
			this.notifyObservers();
		}
	}
	
	
	
	
	
	
//////////////////////////////////// GESTION VITESSE MOTO /////////////////////////////////////////	
	
	
	/**
	 * methode iBetween() :
	 * @param p1 un Point
	 * @param p2 un Point
	 * @return true si l'ovale se trouve entre les abscisses des deux points p1 (inclus) et p2 (exclus), 
	 * 			false sinon
	 */
	public boolean isBetween(Point p1, Point p2) {
        if(( p1.getX() <= this.getMoto().getPositionX())
        		&& this.getMoto().getPositionX() < p2.x) {
        	return true;
        }
        return false;
	}
	
	
	/**
	 * methode abscisseNiveauMotoG() :
	 * @return l'abscisse du Point qui se trouve sur le segment gauche de la route 
	 * 			(au niveau de la moto) par rapport a l'ordonnee de la moto	
	 */
	public float abscisseNiveauMotoG() {
		int i = 0;
		int indice = 0;
		
		while(this.getListePointsG().get(i).y < this.getMoto().getPositionY()) {
			indice = i; //indice du Point dont l'ordonnee est inferieure a la moto et le plus proche
			i++;
		}
		
		/* recuperation des Points les plus proches de la moto
		 * p1 le Point en dessous, p2 le Point au dessus
		 */
		Point p1 = new Point(this.getListePointsG().get(indice));
		Point p2 = new Point(this.getListePointsG().get(indice+1));
		
		/*coefficient du segment au niveau de la moto*/
		float coef = (float) ( (p2.y - p1.y)
					/ (float) (p2.x - (float) p1.x) );
		
		//abscisse situee sur le segmentG et dont l'ordonnee correspond a la positionY de la moto
		float result = (this.getMoto().getPositionY() - Moto.POSITIONXMOTO) / coef;
		return result;
	}
	
	
	/**
	 * methode abscisseNiveauMotoG() :
	 * @return l'abscisse du Point qui se trouve sur le segment droit de la route 
	 * 			(au niveau de la moto) par rapport a l'ordonnee de la moto	
	 */
	public float abscisseNiveauMotoD() {
		int i = 0;
		int indice = 0;
		while(this.getListePointsD().get(i).y < this.getMoto().getPositionY()) {
			indice = i; //indice du Point dont l'ordonnee est inferieure a la moto et le plus proche
			i++;
		}
		
		/* recuperation des Points les plus proches de la moto
		 * p1 le Point en dessous, p2 le Point au dessus
		 */
		Point p1 = new Point(this.getListePointsD().get(indice));
		Point p2 = new Point(this.getListePointsD().get(indice+1));
		
		/*coefficient du segment au niveau de la moto*/
		float coef = (float) ( (p2.y - p1.y)
					/ (float) (p2.x - (float) p1.x) );
		
		//abscisse situee sur le segmentD et dont l'ordonnee correspond a la positionY de la moto
		float result = (this.getMoto().getPositionY() - Moto.POSITIONXMOTO) / coef;
		
		return result;
	}
	
	/**
	 * methode estDansRoute():
	 * verifie que l'abscisse de la moto est entre les traces de la route
	 * @return renvoie true si la moto est bien sur la route,
	 * 			false sinon
	 */
	public boolean estDansRoute() {
		if(this.abscisseNiveauMotoG() < this.getMoto().getPositionX() 
				&& this.getMoto().getPositionX() < this.abscisseNiveauMotoD()) {
			return true;
		} else if(this.abscisseNiveauMotoG() > this.getMoto().getPositionX()
					|| this.getMoto().getPositionX() > this.abscisseNiveauMotoD()) {
				return false;
		}
		return false;
	}
	
	/**
	 * methode updateVitesseMoto():
	 * incremente la vitesse de la moto de ACCELERATION si elle est sur la route,
	 * decremente sinon
	 */
	public void updateVitesseMoto() {
		if(this.estDansRoute()) {
			if(this.getMoto().getVitesse() < VITESSEMAX) { //tant que la vitesse max n'est pas atteint
				this.getMoto().setVitesse(this.getMoto().getVitesse() + ACCELERATION);
				this.notifyObservers();
				System.out.printf("acceleration : %f\n", this.getMoto().getVitesse());
			}
		}else{
			if(this.getMoto().getVitesse() > 0) {
				this.getMoto().setVitesse(this.getMoto().getVitesse() - ACCELERATION);
				this.notifyObservers();
				System.out.printf("deceleration : %f\n", this.getMoto().getVitesse());
			}
		}
	}


	

}
