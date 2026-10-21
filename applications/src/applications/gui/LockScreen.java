package applications.gui;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
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
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class LockScreen extends Application {

	private static final int HEIGHT = 720;
	private static final int WIDTH = 360;
	private Timeline displayClock;
	private static final DateTimeFormatter TIME = DateTimeFormatter.ofPattern("h:mm a");
	private static final DateTimeFormatter DATE = DateTimeFormatter.ofPattern("EEEE, MMMM d");

	@Override
	public void start(Stage stage) throws Exception {

		// top region
		Label clock = createLabel("", FontWeight.BOLD, 60);
		Label date = createLabel("", FontWeight.BOLD, 20);

		VBox topBox = new VBox(10, clock, date);
		topBox.setAlignment(Pos.CENTER);
		topBox.setPadding(new Insets(80, 0, 0, 0));

		// bottom region
		Label lock = createLabel("🔒", FontWeight.BOLD, 30);
		Label unlock = createLabel("Click to unlock", FontWeight.BOLD, 20);

		VBox bottomBox = new VBox(10, lock, unlock);
		bottomBox.setAlignment(Pos.CENTER);
		bottomBox.setPadding(new Insets(0, 0, 80, 0));
		
		

		BorderPane root = new BorderPane();
		root.setTop(topBox);
		root.setBottom(bottomBox);

		root.setBackground(
				new Background(new BackgroundFill(Color.web("#2b2b2b", 0.9), new CornerRadii(5), Insets.EMPTY)));
		root.setBorder(new Border(
				new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(5), new BorderWidths(5))));
		
		startClock(clock, date);
		updateDateTime(clock, date);
		
		root.setOnMouseClicked(e -> {
			if (displayClock != null) {
				displayClock.stop();
				stage.setScene(HomeScreen.createScene());
			}
		});
		
		
		// scene and stage setup
		Scene scene = new Scene(root, WIDTH, HEIGHT);
		scene.setFill(Color.TRANSPARENT);


		
		scene.setOnKeyPressed(e -> {
			if (e.getCode() == javafx.scene.input.KeyCode.ESCAPE) {
				stage.close();
			}
		});

		stage.setScene(scene);
		stage.setTitle("Phone");
		stage.setHeight(HEIGHT);
		stage.setWidth(WIDTH);
		stage.setResizable(false);
		stage.initStyle(StageStyle.TRANSPARENT);

		stage.show();

	}

	private Label createLabel(String text, FontWeight weight, int fontSize) {
		Label label = new Label(text);
		label.setFont(Font.font("System", weight, fontSize));
		label.setTextFill(Color.GHOSTWHITE);
		return label;
	}

	private void startClock(Label clock, Label date) {
		displayClock = new Timeline(new KeyFrame(Duration.seconds(1), e -> updateDateTime(clock, date)));
		displayClock.setCycleCount(Animation.INDEFINITE);
		displayClock.play();
	}

	private void updateDateTime(Label clock, Label date) {
		clock.setText(LocalTime.now().format(TIME));
		date.setText(LocalDate.now().format(DATE));
	}

	public static void main(String[] args) {
		launch(args);
	}

}
