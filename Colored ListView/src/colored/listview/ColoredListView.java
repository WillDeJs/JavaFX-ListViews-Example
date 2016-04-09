/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package colored.listview;

import javafx.scene.paint.Color;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.FlowPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 *
 * @author wilmer
 */
public class ColoredListView extends Application {
    
    @Override
    public void start(Stage primaryStage) {
       ObservableList<Color> colorList= FXCollections.observableArrayList();
        colorList.addAll(Color.RED, Color.rgb(58,57,58), Color.rgb(90, 45, 0, 0.5), Color.ALICEBLUE,Color.ANTIQUEWHITE, Color.CYAN, Color.YELLOWGREEN, Color.AQUA, Color.BISQUE, Color.BEIGE, Color.BURLYWOOD);
        ListView myList = new ListView(colorList);
        myList.setEditable(true);
       // cell factory allows to create a custom cell passed as a callback here
        myList.setCellFactory(new Callback<ListView<Color>, ListCell<Color>>() {
           @Override
           public ListCell<Color> call(ListView<Color> list) {
              return new CustomCell();
           }
       });
        
        myList.getSelectionModel().selectedItemProperty().addListener((obj, old, n)->{
            // only after first change
            if(old!=null){
               // cast objects to color objects 
                Color newColor = (Color) n;
                Color oldColor = (Color) old;
                
                // get each color separately, scale it to 0-255 value and cast it to integer
                int newRed=(int)(newColor.getRed()*255);
                int newGreen=(int)(newColor.getGreen()* 255);
                int newBlue=(int)(newColor.getBlue()*255);
                
                //define style string to change the background of list parent
                String style= String.format("-fx-background-color:rgb(%s,%s,%s)",newRed,newGreen,newBlue);

                // get the parent adn change its background
                Parent parent =(Parent) myList.getParent();
                parent.setStyle(style);
            }
        });
        
        myList.setPrefSize(300, 300);
        primaryStage.setTitle("Select a Color");
        primaryStage.setScene(new Scene(new FlowPane(myList),500,300));
        primaryStage.show();
    }
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    /*
    Class used to created each one fo the cells in the list view
    updateItem method was used to modify each sell into a circle of a determined color
    */
    class CustomCell extends ListCell<Color>{

        @Override
        protected void updateItem(Color item, boolean empty) {
            super.updateItem(item, empty);
            setGraphic(null);
            setText(null);
            if(item!=null){
                setGraphic(new Circle(20,item));
                setText("Red: "+(int)(item.getRed()*255 )+ " Green: "+(int)(item.getGreen()*255)+" Blue: "+(int)(item.getBlue()*255));
            }
                
        }
        
    }
}
