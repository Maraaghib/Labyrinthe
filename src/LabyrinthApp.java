import controller.Controller;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * 
 */

/**
 * Classe qui lance le programme en créant le controller
 * @author hamza
 *
 */
public class LabyrinthApp extends Application {

	static Controller controller ; 
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch();
	}
	
	@Override
	public void start(Stage primaryStage) {
		// TODO Auto-generated method stub
		controller = Controller.getInstance();
		controller.start(primaryStage);
	}
	
	@Override
	public void stop() {
		System.exit(0);
	}
	
	
}
