package applications.gui;

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
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
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
	private static final DateTimeFormatter TIME = DateTimeFormatter.ofPattern("h:mm a");

	@Override
	public void start(Stage stage) throws Exception {
		Label lock = new Label("🔒");
		lock.setFont(Font.font(30));
		lock.setTextFill(Color.GHOSTWHITE);

		Label clock = new Label();
		clock.setFont(Font.font("System", FontWeight.BOLD, 70));
		clock.setTextFill(Color.GHOSTWHITE);

		VBox top = new VBox();
		top.getChildren().addAll(lock, clock);
		top.setSpacing(10.0);
		top.setAlignment(Pos.BASELINE_CENTER);

		StackPane root = new StackPane(top);
		root.setBackground(
				new Background(new BackgroundFill(Color.web("#2b2b2b", 0.7), CornerRadii.EMPTY, Insets.EMPTY)));
		Scene scene = new Scene(root, WIDTH, HEIGHT);
		scene.setFill(Color.TRANSPARENT);

		clock.setText(LocalTime.now().format(TIME));
		Timeline displayClock = new Timeline(
				new KeyFrame(Duration.seconds(1), e -> clock.setText(LocalTime.now().format(TIME))));
		displayClock.setCycleCount(Animation.INDEFINITE);
		displayClock.play();

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

	private void updateClock(Label clock) {
		clock.setText(LocalTime.now().format(TIME));
	}

	public static void main(String[] args) {
		launch(args);
	}

}
