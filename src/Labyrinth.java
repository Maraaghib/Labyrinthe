import java.util.ArrayDeque;
import java.util.Queue;

import controller.Controller;

import javafx.application.Application;
import javafx.stage.Stage;
import model.Vertex;

/**
 * 
 */

/**
 * @author hamza
 *
 */
public class Labyrinth extends Application {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch();
	}
	
	/* (non-Javadoc)
	 * @see javafx.application.Application#start(javafx.stage.Stage)
	 */
	@Override
	public void start(Stage stage) {
		// TODO Auto-generated method stub
		Controller.makeInstance();
		Controller.start(stage);
	}
	
	@Override
	public void stop() {
		System.exit(0);
	}
	
	private void calculateManhattanDistance(Vertex source, Vertex target) {
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
	
	public void launchManhattan(Vertex source, Vertex target) {
		for (Vertex vertex : graph.vertexSet()) {
			vertex.setNbr(0);
		}
		calculateManhattanDistance(source, target);
	}
}
