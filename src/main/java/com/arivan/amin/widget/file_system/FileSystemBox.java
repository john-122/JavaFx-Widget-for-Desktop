package com.arivan.amin.widget.file_system;

import javafx.beans.property.DoubleProperty;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.util.logging.Logger;

public class FileSystemBox extends VBox
{
    private static final int BOX_SPACING = 15;
    private final Logger logger = Logger.getLogger(getClass().getName());
    private FileSystem fileSystem;
    
    private FileSystemBox (DoubleProperty parentWidth, DoubleProperty parentHeight)
    {
        determineOperatingSystem();
        bindBoxToParent(parentWidth, parentHeight);
        setSpacing(BOX_SPACING);
        createBoxesForPartitions(fileSystem.partitions());
    }
    
    @SuppressWarnings ("TypeMayBeWeakened")
    private void bindBoxToParent (DoubleProperty parentWidth, DoubleProperty parentHeight)
    {
        prefWidthProperty().bind(parentWidth);
        prefHeightProperty().bind(parentHeight);
    }
    
    private void determineOperatingSystem ()
    {
        fileSystem = LinuxFileSystem.newInstance();
    }
    
    public static FileSystemBox newInstance (DoubleProperty parentWidth,
            DoubleProperty parentHeight)
    {
        return new FileSystemBox(parentWidth, parentHeight);
    }
    
    @SuppressWarnings ("MethodParameterOfConcreteClass")
    private void createPartitionBox (FileSystemItem item)
    {
        VBox mainVBox = new VBox(10);
        BorderPane borderPane = new BorderPane();
        borderPane.setLeft(new Label(item.getName()));
        borderPane.setRight(new Label(item.getSize()));
        mainVBox.getChildren().add(borderPane);
        ProgressBar usageBar = new ProgressBar();
        usageBar.setProgress(item.getUsedPercentage());
        usageBar.prefWidthProperty().bind(prefWidthProperty());
        mainVBox.getChildren().add(usageBar);
        getChildren().add(mainVBox);
    }
    
    private void createBoxesForPartitions (Iterable<FileSystemItem> items)
    {
        items.forEach(this::createPartitionBox);
    }
    
    @Override
    public String toString ()
    {
        return "FileSystemBox{" + "fileSystem=" + fileSystem + '}';
    }
}