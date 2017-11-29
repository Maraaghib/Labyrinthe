import controller.Controller;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * 
 */

/**
 * @author hamza
 *
 */
public class LabyrinthApp extends Application {

	static Controller controller ; 
	
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
