package com.arivan.amin.widget.cpu;

import javafx.animation.*;
import javafx.beans.property.DoubleProperty;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.util.Locale;
import java.util.logging.Logger;

public class CpuProgressBar extends VBox
{
    private final Logger logger = Logger.getLogger(getClass().getName());
    // todo cpu update lags in windows and causes the UI to freeze
    private static final int UPDATE_FREQUENCY_IN_SECONDS = 1;
    private CpuMonitor processor;
    private ProgressBar cpuBar;
    private Label cpuLabel;
    
    @SuppressWarnings ("TypeMayBeWeakened")
    private CpuProgressBar (DoubleProperty parentWidth, DoubleProperty parentHeight)
    {
        setSpacing(5);
        initializeFields();
        BorderPane cpuBorderPane = new BorderPane();
        cpuBorderPane.setLeft(new Label("CPU"));
        cpuBorderPane.setRight(cpuLabel);
        getChildren().addAll(cpuBorderPane, cpuBar);
        cpuBar.prefWidthProperty().bind(parentWidth);
        animateBar();
    }
    
    private void initializeFields ()
    {
        determineCpuMonitor();
        cpuBar = new ProgressBar();
        cpuLabel = new Label();
    }
    
    private void determineCpuMonitor ()
    {
        if (System.getProperty("os.name").toLowerCase(Locale.ENGLISH).contains("win"))
        {
            processor = WindowsCpuMonitor.newInstance();
        }
        else if (System.getProperty("os.name").toLowerCase(Locale.ENGLISH).contains("linux"))
        {
            processor = LinuxCpuMonitor.newInstance();
        }
    }
    
    /**
     * New instance cpu progress bar.
     *
     * @param parentWidth the parent width property for this box to be bound with
     * @param parentHeight the parent height property for this box to be bound with
     *
     * @return a new cpu progress bar instance
     */
    public static CpuProgressBar newInstance (DoubleProperty parentWidth,
            DoubleProperty parentHeight)
    {
        return new CpuProgressBar(parentWidth, parentHeight);
    }
    
    private void animateBar ()
    {
        Timeline cpuTimeLine = new Timeline();
        cpuTimeLine.getKeyFrames().add(new KeyFrame(Duration.seconds(UPDATE_FREQUENCY_IN_SECONDS),
                this::cpuDataUpdateHandler));
        cpuTimeLine.setCycleCount(Animation.INDEFINITE);
        cpuTimeLine.play();
    }
    
    private void cpuDataUpdateHandler (ActionEvent e)
    {
        try
        {
            double data = processor.getCpuUsage();
            cpuBar.setProgress(data);
            cpuLabel.setText((int) (data * 100) + " ");
        }
        catch (Exception ex)
        {
            logger.warning(ex.getMessage());
        }
    }
    
    @Override
    public String toString ()
    {
        return "CpuProgressBar{" + "processor=" + processor + ", cpuBar=" + cpuBar + ", cpuLabel=" +
                cpuLabel + '}';
    }
}
