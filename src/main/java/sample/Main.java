package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import sun.reflect.annotation.ExceptionProxy;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

public class Main extends Application {

    ListView listView;
    Pane root;
    Connection myConn;
    Statement myStmt;


    @Override
    public void start(final Stage primaryStage) {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            myConn = DriverManager.getConnection("jdbc:mysql://35.236.126.183:3306/MarchMadness","root", "password");
            myStmt = myConn.createStatement();

            int rs = myStmt.executeUpdate("UPDATE Rounds SET finalFour = 1 WHERE teamID = 15");
            //while(rs.next()) {
            //    System.out.println("ID: " + rs.getString(2));
            //}
        }catch(Exception e){System.out.println(e);}
        Label lbl = new Label("MARCH MADNESS BITCH");
        Button btn64 = new Button("First Round");
        Button btn32 = new Button("Second Round");
        Button btn16 = new Button("Sweet Sixteen");
        Button btn8 = new Button("Elite Eight");
        Button btn4 = new Button("Final Four");
        Button btn2 = new Button("National Championship");
        Button btn1 = new Button("Champions");
        listView = new ListView();

        listView.setPrefSize(320,480);
        listView.getItems().add("fuck");
        listView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                System.out.println("click fkdfs");
            }
        });
        // First Round
        btn64.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                System.out.println("please work");
                listView.getItems().clear();
                try {
                    ResultSet rs = myStmt.executeQuery("SELECT * FROM Teams");
                    while(rs.next())
                    {
                        listView.getItems().add(rs.getString(2));
                    }
                }catch (Exception e) {}

            }
        });
        btn32.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                listView.getItems().clear();
                try {
                    ResultSet rs = myStmt.executeQuery("SELECT teamName FROM Teams INNER JOIN Rounds ON Teams.id=Rounds.teamID WHERE thirtyTwo=1");
                    while(rs.next())
                    {
                        listView.getItems().add(rs.getString(1));
                    }
                }catch(Exception e){}
            }
        });
        btn16.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                listView.getItems().clear();
                try {
                    ResultSet rs = myStmt.executeQuery("SELECT teamName FROM Teams INNER JOIN Rounds ON Teams.id=Rounds.teamID WHERE sweetSixteen=1");
                    while(rs.next())
                    {
                        listView.getItems().add(rs.getString(1));
                    }
                }catch(Exception e){}
            }
        });
        btn8.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                listView.getItems().clear();
                try {
                    ResultSet rs = myStmt.executeQuery("SELECT teamName FROM Teams INNER JOIN Rounds ON Teams.id=Rounds.teamID WHERE eliteEight=1");
                    while(rs.next())
                    {
                        listView.getItems().add(rs.getString(1));
                    }
                }catch(Exception e){}
            }
        });
        btn4.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                listView.getItems().clear();
                try {
                    ResultSet rs = myStmt.executeQuery("SELECT teamName FROM Teams INNER JOIN Rounds ON Teams.id=Rounds.teamID WHERE finalFour=1");
                    while(rs.next())
                    {
                        listView.getItems().add(rs.getString(1));
                    }
                }catch(Exception e){}
            }
        });
        btn2.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                listView.getItems().clear();
                try {
                    ResultSet rs = myStmt.executeQuery("SELECT teamName FROM Teams INNER JOIN Rounds ON Teams.id=Rounds.teamID WHERE final=1");
                    while(rs.next())
                    {
                        listView.getItems().add(rs.getString(1));
                    }
                }catch(Exception e){}
            }
        });
        btn1.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                listView.getItems().clear();
                try {
                    ResultSet rs = myStmt.executeQuery("SELECT teamName FROM Teams INNER JOIN Rounds ON Teams.id=Rounds.teamID WHERE champion=1");
                    while(rs.next())
                    {
                        listView.getItems().add(rs.getString(1));
                    }
                }catch(Exception e){}
            }
        });
        root = new Pane();
        lbl.setLayoutX(330);
        lbl.setLayoutY(50);
        lbl.setStyle("-fx-font: 24 arial;");
        btn64.setLayoutX(330);
        btn64.setLayoutY(100);
        btn32.setLayoutX(330);
        btn32.setLayoutY(130);
        btn16.setLayoutX(330);
        btn16.setLayoutY(160);
        btn8.setLayoutX(330);
        btn8.setLayoutY(190);
        btn4.setLayoutX(330);
        btn4.setLayoutY(220);
        btn2.setLayoutX(330);
        btn2.setLayoutY(250);
        btn1.setLayoutX(330);
        btn1.setLayoutY(280);
        root.getChildren().addAll(btn64, btn32, btn16, btn8, btn4, btn2, btn1, lbl, listView);
        primaryStage.setScene(new Scene(root, 640, 480));
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}