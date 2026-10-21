package applications.gui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class HomeScreen extends Application {
	private static final int HEIGHT = 720;
	private static final int WIDTH = 360;

	public static Scene createScene() {
		// top region
		Label clock = createLabel("12:00", FontWeight.BOLD, 15);
		Label battery = createLabel("100% 🔋", FontWeight.BOLD, 15);
		Label power = createLabel("⏻", FontWeight.BOLD, 15);
		
		

		BorderPane topBox = new BorderPane();
		topBox.setCenter(clock);
		topBox.setRight(battery);
		topBox.setLeft(power);
		topBox.setPadding(new Insets(15, 20, 15, 35));
		topBox.setBackground(new Background(
				new BackgroundFill(Color.web("#000000", 0.8), CornerRadii.EMPTY, new Insets(-8, -5, 10, -5))));

		// Center region
		TilePane appBox = new TilePane();
		appBox.setAlignment(Pos.TOP_CENTER);
		appBox.setHgap(10);
		appBox.setVgap(10);
		appBox.setPrefColumns(3);
		appBox.setPadding(new Insets(50, 0, 0, 0));
		appBox.getChildren().add(createAppIcon("🔢", "Calculator"));
		appBox.getChildren().add(createAppIcon("📓", "Notes"));
		appBox.getChildren().add(createAppIcon("🎯", "Number Guesser"));

		// Bottom region

		HBox bottomBox = new HBox();
		bottomBox.getChildren().add(createAppIconNoName("📞"));
		bottomBox.getChildren().add(createAppIconNoName("💬"));
		bottomBox.getChildren().add(createAppIconNoName("⚙️"));
		bottomBox.getChildren().add(createAppIconNoName("📧"));
		bottomBox.setSpacing(15);
		bottomBox.setAlignment(Pos.CENTER);
		bottomBox.setPadding(new Insets(0, 0, 20, 0));

		bottomBox.setBackground(new Background(
				new BackgroundFill(Color.web("#000000", 0.8), new CornerRadii(20), new Insets(-8, 5, 10, 5))));

		// root setup
		BorderPane homeRoot = new BorderPane();
		homeRoot.setTop(topBox);
		homeRoot.setCenter(appBox);
		homeRoot.setBottom(bottomBox);

		homeRoot.setBackground(
				new Background(new BackgroundFill(Color.web("#2b2b2b", 0.7), new CornerRadii(5), Insets.EMPTY)));
		homeRoot.setBorder(new Border(
				new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(5), new BorderWidths(5))));

		// scene and stage setup
		Scene homeScene = new Scene(homeRoot, WIDTH, HEIGHT);
		homeScene.setFill(Color.TRANSPARENT);
		return homeScene;

	}

	@Override
	public void start(Stage stage) throws Exception {
		Scene homeScene = createScene();
		homeScene.setOnKeyPressed(e -> {
			if (e.getCode() == javafx.scene.input.KeyCode.ESCAPE) {
				stage.close();
			}
		});
		
		stage.setScene(homeScene);
		stage.setTitle("Phone");
		stage.setHeight(HEIGHT);
		stage.setWidth(WIDTH);
		stage.setResizable(false);
		stage.initStyle(StageStyle.TRANSPARENT);
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

	private static Label createLabel(String text, FontWeight weight, int fontSize) {
		Label label = new Label(text);
		label.setFont(Font.font("System", weight, fontSize));
		label.setTextFill(Color.WHITE);
		return label;
	}

	private static VBox createAppIcon(String emoji, String name) {
		Label icon = new Label(emoji);
		icon.setFont(Font.font(30));
		icon.setTextFill(Color.WHITE);

		StackPane iconBox = new StackPane(icon);
		iconBox.setPrefSize(60, 60);
		iconBox.setMaxSize(60, 60);

		iconBox.setBackground(
				new Background(new BackgroundFill((Color.web("#3b3b3b")), new CornerRadii(15), Insets.EMPTY)));
		iconBox.setBorder(new Border(new BorderStroke(Color.web("#555555"), BorderStrokeStyle.SOLID,
				new CornerRadii(15), new BorderWidths(2))));

		Label label = new Label(name);
		label.setFont(Font.font(12));
		label.setTextFill(Color.WHITE);

		VBox app = new VBox(5, iconBox, label);
		app.setAlignment(Pos.CENTER);
		app.setPrefWidth(90);
		return app;

	}

	private static VBox createAppIconNoName(String emoji) {
		Label icon = new Label(emoji);
		icon.setFont(Font.font(35));
		icon.setTextFill(Color.WHITE);

		StackPane iconBox = new StackPane(icon);
		iconBox.setPrefSize(65, 65);
		iconBox.setMaxSize(65, 65);
		

		iconBox.setBackground(
				new Background(new BackgroundFill((Color.web("#3b3b3b")), new CornerRadii(15), Insets.EMPTY)));
		iconBox.setBorder(new Border(new BorderStroke(Color.web("#555555"), BorderStrokeStyle.SOLID,
				new CornerRadii(15), new BorderWidths(2))));

		VBox app = new VBox(iconBox);
		app.setAlignment(Pos.CENTER);
		app.setPrefWidth(70);
		return app;

	}

}
