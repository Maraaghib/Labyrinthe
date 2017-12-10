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

	public Labyrinth() {
		System.out.println("Intance de la classe Labyrinth créée !");
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
			Wall wallToDestroy = w.get(random.nextInt(w.size()));
			wallToDestroy.setType(WallType.EMPTY);
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
}
