package proz.calc;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class Controller {
	@FXML
	private Button zero;

	@FXML
	private Button tripleZero;

	@FXML
	private Button decimalSign;

	@FXML
	private Button equals;

	@FXML
	private Button add;

	@FXML
	private Button subtract;

	@FXML
	private Button multiply;

	@FXML
	private Button divide;

	@FXML
	private Button one;

	@FXML
	private Button two;

	@FXML
	private Button three;

	@FXML
	private Button four;

	@FXML
	private Button five;

	@FXML
	private Button six;

	@FXML
	private Button seven;

	@FXML
	private Button eight;

	@FXML
	private Button nine;

	@FXML
	private Button allCancel;

	@FXML
	private Button backspace;

	@FXML
	private Button invertSign;

	@FXML
	private Button sqrt;

	@FXML
	private Button power;

	@FXML
	private Button factorial;

	@FXML
	private Button combinations;

	@FXML
	private TextFlow display;

	private Text displayText = new Text("0");
	private String calcInput = "";
	private Model model = new Model();

	public Controller() {
		displayText.setFill(Color.BLACK);
		displayText.setFont(Font.font("Segoe UI Bold", 20));
		display.getChildren().add(displayText);
	}

	private void digitAppend(String digit) {
		if(displayText.getText().equals("0"))
			if(!(digit.equals("0") || digit.equals("000")))
			displayText.setText(digit);
		else
			displayText.setText(displayText.getText() + digit);
	}
	
	private void click() {
		zero.setOnAction((event) -> {
			System.out.println("zero clicked");
			digitAppend("0");
		});
		
		tripleZero.setOnAction(e -> {
			digitAppend("000");
		});
		
		one.setOnAction(e -> {
			digitAppend("1");
		});
		
		two.setOnAction(e -> {
			digitAppend("2");
		});
		
		three.setOnAction(e -> {
			digitAppend("3");
		});
		
		four.setOnAction(e -> {
			digitAppend("4");
		});
		
		five.setOnAction(e -> {
			digitAppend("5");
		});
		
		six.setOnAction(e -> {
			digitAppend("6");
		});
		
		seven.setOnAction(e -> {
			digitAppend("7");
		});
		
		eight.setOnAction(e -> {
			digitAppend("8");
		});
		
		nine.setOnAction(e -> {
			digitAppend("9");
		});
		
		
	}
}
