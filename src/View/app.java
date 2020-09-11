package View;

import Controller.Controller;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 *
 * @author Yixuan
 */
public class app extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		try {
			final String viewAdress = "Formulaire.fxml";
			final Parent root = FXMLLoader.load(getClass().getResource(viewAdress));
			// Controller controller = new Controller(root);

			// final FXMLLoader loader = new FXMLLoader();
			// loader.setLocation(getClass().getResource(viewAdress));
			// final Parent root = (Parent) loader.load();
			// final Controller controller = loader.getController();
			// controller.primaryStage = primaryStage;

			Scene scene = new Scene(root, 600, 400);
			primaryStage.setTitle("Functions");
			primaryStage.setScene(scene);

			// set Stage boundaries to visible bounds of the main screen
			Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
			primaryStage.setX(screenBounds.getMinX());
			primaryStage.setY(screenBounds.getMinY());
			primaryStage.setWidth(screenBounds.getWidth());
			primaryStage.setHeight(screenBounds.getHeight());

			System.out.println("stage show ");
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
