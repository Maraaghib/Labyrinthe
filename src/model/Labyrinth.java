/**
 *
 */
package model;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Random;
import java.util.Vector;
import java.util.ArrayList;

import model.Labyrinth.Directions;
import model.Wall.WallType;


/**
 * @author saseye
 *
 */
public class Labyrinth {

	protected static final int TOP_BORDER = 0;
	protected static final int DOWN_BORDER = 15;
	protected static final int LEFT_BORDER = 0;
	protected static final int RIGHT_BORDER = 15;

	public static final int WIDTH = 16;
	public static final int HEIGHT = 16;

	public enum Directions{
		EAST,
		WEST,
		NORTH,
		SOUTH;
	};
	
	//Liste de murs que la View devra afficher
	private Wall walls[][][] = new Wall[WIDTH][HEIGHT][2];

	private Graph graph;

	public Labyrinth() {
		graph = new Graph();
		System.out.println("Intance de la classe Labyrinth cree !");
	}

	public Labyrinth(Graph graph) {
		super();
		this.graph = graph;
	}

	public Graph getGraph() {
		return this.graph;
	}

	private void calculateManhattanDistance(Vertex source, Vertex target) throws CloneNotSupportedException {
		Queue<Vertex> fifo = new ArrayDeque<Vertex>();
		target.setNbr(1);
		fifo.add(target);
		while (!fifo.isEmpty()) {
			Vertex actual = fifo.remove();
			for (Directions dir : Directions.values()) {
				if (this.isOpened(actual, dir)) {
					Vertex next = graph.getVertexByDir(actual, dir);
					if (next.getNbr() == 0) {
						next.setNbr(actual.getNbr()+1);
						if(next != source) {
							fifo.add(next);
						}
					}
				}
			}
		}
	}

	public void launchManhattan(Vertex source, Vertex target) throws CloneNotSupportedException {
		for (Vertex vertex : graph.vertexSet()) { // vertexSet peut vouloir dire ensemble de sommets (set of vertexes)
			vertex.setNbr(0);
		}
		calculateManhattanDistance(source, target);
	}

	public void buildRandomPath(Vertex vertex) {
//		System.out.println("buildRandomPath appelï¿½e "+(++Model.cpt)+" fois !");
		// Une liste alÃ©atoire des 4 directions
		Vector<Directions> v = new Vector<Directions>();

		for (int i = 0; i < 4; ++i) {
			v.add(Directions.values()[i]);
		}

		Directions directions[] = new Directions[4];
		Random random = new Random(); // A dÃ©placer dans la boucle ?
		for (int i = 0; i < directions.length; ++i) {
			int index = random.nextInt(v.size());
			directions[i] = v.get(index);
			v.remove(index);
		}

		
		for (int i = 0; i < directions.length; i++) {
//			System.out.println(directions[i]);
		}

		// Pour chacune de ces directions, on avance en profondeur d'abord
		for (int i = 0; i < 4; ++i) {
			Directions dir = directions[i];
//			System.out.println("Direction: "+directions[i]);
//			System.out.println("vertex.inBorders(dir) = "+vertex.inBorders(dir));
//			System.out.println("this.graph.doesntExist(vertex, dir) = "+this.graph.doesntExist(vertex, dir));
			if (vertex.inBorders(dir) && this.graph.doesntExist(vertex, dir)) {
				int x = vertex.getX();
				int y = vertex.getY();
				int xt = 0, yt = 0;

				switch (dir) {
					case NORTH:
						xt = x;
						yt = y-1;
						break;
					case SOUTH:
						xt = x;
						yt = y+1;
						break;
					case EAST:
						xt = x+1;
						yt = y;
						break;
					case WEST:
						xt = x-1;
						yt = y;
						break;
				}
				Vertex next = new Vertex(xt, yt, vertex.getNbr()+1);
//				System.out.println("vertex.compareTo(next): "+ vertex.compareTo(next));
//				graph.addVertex(vertex);
//				if (graph.containsVertex(vertex)) {
					this.graph.addVertex(next);
					this.graph.addEdge(vertex, next);
//					System.out.println("vertex.toString() : "+vertex);
//					System.out.println("next.toString()   : "+ next.toString());
//					System.out.println("graph.containsEdge(vertex, next)______________________________________________: "+ graph.containsEdge(vertex, next));
//					System.out.println("Je suis lÃ  !!");
					buildRandomPath(next);
//				}
//				else {
//					System.out.println("graph.containsVertex(vertex)______________________________________________: "+ graph.containsVertex(vertex));
//					System.exit(0);
//				}
			}
		}
	}
	
	public void buildLabyrinth(){
		//On initialise walls
		for(int i = 0; i < WIDTH; i++){
			for(int j = 0; j < HEIGHT; j++){
				walls[i][j][0] = new Wall(WallType.WALL, i, j, Directions.EAST);
				walls[i][j][1] = new Wall(WallType.WALL, i, j, Directions.SOUTH);
			}
		}
		
		//On crée un tableau qui représente les cellules du labyrinthe
		int cells[][] = new int[WIDTH][HEIGHT];
		
		//Et on initialise chaque cellule à une valeur différente
		for (int i = 0; i < WIDTH; i++){
			for (int j = 0; j < HEIGHT; j++){
				cells[i][j] = j*WIDTH + i;
			}
		}
		
		//On crée une liste qui contiendra tous les noms des groupes de cellules connectées
		ArrayList<Integer> groups = new ArrayList<Integer>();
		for (int i = 0; i < WIDTH * HEIGHT; i++){
			groups.add(i);
		}
		
		//À chaque itération on détruit un mur pour fusionner deux groupes
		while(!allCellsEqualToZero(cells)){
			Random random = new Random();
			int g = groups.get(random.nextInt(groups.size()));
			
			//On fait une liste de potentiels murs à supprimer
			ArrayList<Wall> w = new ArrayList<Wall>();
			for (int i = 0; i < WIDTH; i++){
				for (int j = 0; j < HEIGHT; j++){
					if(cells[i][j] == g){
						if(isWallBreakable(i-1, j, cells, g))
							w.add(getWall(i, j, i-1, j));
						
						if(isWallBreakable(i, j-1, cells, g))
							w.add(getWall(i, j, i, j-1));
						
						if(isWallBreakable(i+1, j, cells, g))
							w.add(getWall(i, j, i+1, j));
						
						if(isWallBreakable(i, j+1, cells, g))
							w.add(getWall(i, j, i, j+1));
					}
				}
			}
			//On enlève un des murs au hasard et on met à jour les valeurs des cellules
			System.out.println(w.size());
			System.out.println(g);
			Wall wallToDestroy = w.get(random.nextInt(w.size()));
			wallToDestroy.setType(WallType.EMPTY);
			System.out.print(wallToDestroy.getX());
			System.out.print(wallToDestroy.getY());
			System.out.println(wallToDestroy.getDir());
			int cell1;
			int cell2;

			if(wallToDestroy.getDir() == Directions.EAST){
				cell1 = cells[wallToDestroy.getX()][wallToDestroy.getY()];
				cell2 = cells[wallToDestroy.getX() + 1][wallToDestroy.getY()];
			}
			else{
				cell1 = cells[wallToDestroy.getX()][wallToDestroy.getY()];
				cell2 = cells[wallToDestroy.getX()][wallToDestroy.getY() + 1];
			}

			if(cell1 < cell2){
				int cell3 = cell1;
				cell1 = cell2;
				cell2 = cell3;
			}	
			
			updateCells(cells, cell1, cell2);
			for (Integer i : groups) {
			   if (i == cell1) {
			      groups.remove(i);
			      break;
			   }
			}
			
		}
	}
	
	//Fonction auxiliare pour buildLabyrinth qui sert à savoir si toutes les cellules sont à zéro (si la construction du labyrinthe est terminée
	private boolean allCellsEqualToZero(int cells[][]){
		for(int i = 0; i < WIDTH; i++){
			for(int j = 0; j < HEIGHT; j++){
				if (cells[i][j] != 0)
					return false;
			}
		}
		return true;
	}
	
	//Fonction auxiliare pour buildLabyrinth qui sert à savoir si un mur peut-être détruit
	private boolean isWallBreakable(int x, int y, int cells[][], int g){
		if(x < 0)
			return false;
		if(x > WIDTH-1)
			return false;
		if(y < 0)
			return false;
		if(y > HEIGHT-1)
			return false;
		if(cells[x][y] == g)
			return false;
		
		return true;
	}
	
	//Fonction auxiliare pour buildLabyrinth qui donne le wall entre deux cellules
	private Wall getWall(int x1, int y1, int x2, int y2){
		if (x1 < x2)
			return walls[x1][y1][0];
		if (x1 > x2)
			return walls[x2][y2][0];
		if (y1 < y2)
			return walls[x1][y1][1];
		else
			return walls[x2][y2][1];
	}
	
	//Fonction auxiliare qui met toutes les cellules ayant pour valeur a ou b à la valeur min(a, b)
	private void updateCells(int cells[][], int a, int b){
		for (int i = 0; i < WIDTH; i++){
			for (int j = 0; j < HEIGHT; j++){
				if(cells[i][j] == a)
					cells[i][j] = b;
			}
		}
	}
	
	public WallType getWallType(int x, int y, Directions dir){
		if(dir == Directions.EAST)
			return walls[x][y][0].getType();
		return walls[x][y][1].getType();
	}

	/* Quelques prÃ©dicats pour dÃ©tecter une porte ouverte, fermÃ©e, un couloir ou un mur... */

	public boolean isWall(Vertex vertex, Directions dir) throws CloneNotSupportedException {
		Edge edge = graph.getEdge(vertex, dir);
		return (edge == null);
	}

	public boolean isClosed(Vertex vertex, Directions dir) throws CloneNotSupportedException {
		Edge edge = graph.getEdge(vertex, dir);
		return (edge == null || (edge.getType() == Edge.Type.CLOSED_DOOR));
	}

	public boolean isOpened(Vertex vertex, Directions dir) throws CloneNotSupportedException {
		Edge edge = graph.getEdge(vertex, dir);
		return (edge != null && (edge.getType() != Edge.Type.CLOSED_DOOR));
	}

	public boolean isClosedDoor(Vertex vertex, Directions dir) throws CloneNotSupportedException {
		Edge edge = graph.getEdge(vertex, dir);
		return (edge != null && (edge.getType() == Edge.Type.CLOSED_DOOR));
	}

	public boolean isOpenedDoor(Vertex vertex, Directions dir) throws CloneNotSupportedException {
		Edge edge = graph.getEdge(vertex, dir);
		return (edge != null && (edge.getType() == Edge.Type.OPENED_DOOR));
	}



}
