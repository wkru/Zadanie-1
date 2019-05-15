package proz.calc;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * The main class of the program responsible for starting the application and
 * loading the FXML file.
 * 
 * @author wkru
 *
 */

public class Calculator extends Application {

	/**
	 * Function which launches the JFX program.
	 * 
	 * @param args command-line arguments (not used)
	 */
	public static void main(String[] args) {
		launch(args);
	}

	/**
	 * Function responsible for calling the <code>FXMLLoader</code> on a file
	 * containing the View portion of the program and setting up the window.
	 * 
	 * @param primaryStage stage hosting the main window of an app
	 * @exception Exception generic exception that may be caused by the
	 *                      getResource(file) method
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("graphics.fxml"));
		Scene scene = new Scene(root);

		primaryStage.setTitle("Kalkulator");
		primaryStage.setScene(scene);
		primaryStage.centerOnScreen();
		primaryStage.setResizable(false);
		primaryStage.show();
	}
}
