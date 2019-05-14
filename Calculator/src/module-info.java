module proz.calc {
	requires javafx.controls;
	requires javafx.fxml;
	requires jdk.jshell;
	
	requires transitive javafx.graphics;
	
	opens proz.calc to javafx.fxml;
	exports proz.calc;

}