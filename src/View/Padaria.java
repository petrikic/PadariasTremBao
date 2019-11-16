package View;
	
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;




public class Padaria extends Application {

	@Override
	public void start(Stage primaryStage) throws IOException {
		Pane root = FXMLLoader.load(getClass().getResource("FXMLTelaInicio.fxml"));
		
		Scene scene = new Scene(root);
		primaryStage.centerOnScreen();
		primaryStage.setMaximized(true);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Padaria Trem Bão -- v1.7.4");
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		Controller.IniciaDB.iniciaDB();
		launch(args);
	}
}
