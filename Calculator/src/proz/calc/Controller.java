package proz.calc;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Alert.AlertType;
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

	@FXML
	private Text displayText;

	private String calcInput = "";
	private String pendingOperation = "";
	private boolean finalResultShown = false;
	private Model model = new Model();

	private void showErrorWindow(String header, String content) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("B³¹d dzia³ania programu");
		alert.setHeaderText(header);
		alert.setContentText(content);
		alert.showAndWait();
	}

	private void showWarningWindow(String header, String content) {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Uwaga");
		alert.setHeaderText(header);
		alert.setContentText(content);
		alert.showAndWait();
	}

	private void calculate() {
		String result = model.calculate(calcInput);
		displayText.setText(result);
		calcInput = "";
	}

	private void digitAppend(String digit) {
		if (finalResultShown) {
			displayText.setText("0");
			finalResultShown = false;
		}
		if (!pendingOperation.isBlank()) {
			if(pendingOperation.equals("/")) {
				if(!displayText.getText().contains("."))
					calcInput = displayText.getText() + "." + pendingOperation;
				else
					calcInput = displayText.getText() + pendingOperation;
			}
			else	
				calcInput = displayText.getText() + pendingOperation;
			pendingOperation = "";
			displayText.setText(digit);
			return;
		}
		if (displayText.getText().length() + digit.length() > 14) {
			showWarningWindow("Przekroczona liczba znaków", "Maksymalna dozwolona liczba znaków to 14.");
			return;
		}
		if (displayText.getText().equals("0")) {
			if (!(digit.equals("0") || digit.equals("000")))
				displayText.setText(digit);
		} else
			displayText.setText(displayText.getText() + digit);
	}

	public void initialize() {
		zero.setOnAction(e -> {
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

		backspace.setOnAction(e -> {
			if (displayText.getText().equals("0"))
				return;

			if (finalResultShown)
				finalResultShown = false;
				
			if (displayText.getText().length() == 1) {
				displayText.setText("0");
				return;
			}

			displayText.setText(displayText.getText().substring(0, displayText.getText().length() - 1));
		});

		allCancel.setOnAction(e -> {
			displayText.setText("0");
			finalResultShown = false;
			pendingOperation = "";
			calcInput = "";
		});

		invertSign.setOnAction(e -> {
			if (displayText.getText().equals("0"))
				return;
			if (displayText.getText().length() + 1 > 14) {
				showWarningWindow("Przekroczona liczba znaków", "Maksymalna dozwolona liczba znaków to 14.");
				return;
			}
			if (displayText.getText().startsWith("-"))
				displayText.setText(displayText.getText().substring(1, displayText.getText().length()));
			else
				displayText.setText("-" + displayText.getText());
		});

		decimalSign.setOnAction(e -> {
			if (displayText.getText().length() + 1 > 14) {
				showWarningWindow("Przekroczona liczba znaków", "Maksymalna dozwolona liczba znaków to 14.");
				return;
			}
			if (!displayText.getText().contains("."))
				displayText.setText(displayText.getText() + ".");
		});

		add.setOnAction(e -> {
			calcInput = calcInput.concat(displayText.getText());
			calculate();
			pendingOperation = "+";
		});
		
		subtract.setOnAction(e -> {
			calcInput = calcInput.concat(displayText.getText());
			calculate();
			pendingOperation = "-";
		});

		multiply.setOnAction(e -> {
			calcInput = calcInput.concat(displayText.getText());
			calculate();
			pendingOperation = "*";
		});

		divide.setOnAction(e -> {
			calcInput = calcInput.concat(displayText.getText());
			
			if(!displayText.getText().contains("."))
				calcInput = calcInput.concat(".");
			
			calculate();
			pendingOperation = "/";
		});
		
		equals.setOnAction(e -> {
			calcInput = calcInput.concat(displayText.getText());
			calculate();
			finalResultShown = true;
		});
	}
}
