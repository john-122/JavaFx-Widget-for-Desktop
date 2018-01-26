package com.arivan.amin.widget.ui_boxes;

import javafx.animation.*;
import javafx.beans.property.DoubleProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.util.logging.Logger;

public class WakaTimeBox extends VBox
{
    private static final int MINUTES_BETWEEN_UPDATES = 20;
    // todo image url must be set and fetched from property file
    private static final String CODING_IMAGE_URL =
            "https://wakatime.com/share/@arivan_amin/ff435624-ae9b-453c-9599-93a63b5ce5f6.png";
    private final Logger logger = Logger.getLogger(getClass().getName());
    private final ImageView codingImageView;
    
    @SuppressWarnings ("TypeMayBeWeakened")
    private WakaTimeBox (DoubleProperty parentWidth, DoubleProperty parentHeight)
    {
        // todo implement a way to show data from wakatime, coding activity image is taking to much space
        prefWidthProperty().bind(parentWidth.multiply(0.33));
        prefHeightProperty().bind(parentHeight);
        codingImageView = new ImageView(new Image(CODING_IMAGE_URL));
        codingImageView.fitWidthProperty().bind(prefWidthProperty());
        codingImageView.fitHeightProperty().bind(prefHeightProperty());
        codingImageView.setPreserveRatio(true);
        getChildren().add(codingImageView);
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), e ->
        {
            updateImage();
        }));
        timeline.getKeyFrames().add(new KeyFrame(Duration.minutes(MINUTES_BETWEEN_UPDATES)));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }
    
    public static WakaTimeBox newInstance (DoubleProperty parentWidth, DoubleProperty parentHeight)
    {
        return new WakaTimeBox(parentWidth, parentHeight);
    }
    
    private void updateImage ()
    {
        codingImageView.setImage(new Image(CODING_IMAGE_URL));
    }
    
    @Override
    public String toString ()
    {
        return "WakaTimeBox{" + "codingImageView=" + codingImageView + '}';
    }
}
