package proz.calc;

import javafx.application.Application;
import javafx.stage.Stage;

public class Calculator extends Application {

	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage)
	{
		primaryStage.setTitle("Kalkulator");
		primaryStage.centerOnScreen();
		primaryStage.show();
	}
}
