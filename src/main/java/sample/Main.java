/*
* WE DID EVERYTHING IN MAIN LMAO
* Authors: Jayson Marshall, Majid Aziz
* Chapman University
*
* */
package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.sql.*;

public class Main extends Application {

    Button btnReport, btnDelete, btnInsert, btnUpdate, btn64, btn32, btn16, btn8, btn4, btn2, btn1;
    ListView listView, listView2;
    TextField textField3, textField2, textField1, textField;
    Connection myConn;
    Statement myStmt;
    Pane root;

    @Override
    public void start(final Stage primaryStage) {
        // Connect to database, dont forget "USE MarchMadness";
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            myConn = DriverManager.getConnection("jdbc:mysql://35.236.126.183:3306/MarchMadness","root", "password");
            myStmt = myConn.createStatement();

            //int rs = myStmt.executeUpdate("UPDATE Players SET teamID = 1 WHERE name = 'Zion Williamson'");
        }catch(Exception e){System.out.println(e);}

        Label lbl = new Label("MARCH MADNESS 2019");
        btnUpdate = new Button("Update Player");
        btnInsert = new Button("Add Player");
        btnDelete = new Button("Del Player");
        btnReport = new Button("Report");
        btn64 = new Button("First Round");
        btn32 = new Button("Second Round");
        btn16 = new Button("Sweet Sixteen");
        btn8 = new Button("Elite Eight");
        btn4 = new Button("Final Four");
        btn2 = new Button("National Championship");
        btn1 = new Button("Champions");
        textField3 = new TextField();
        textField2 = new TextField();
        textField1 = new TextField();
        textField = new TextField();
        listView2 = new ListView();
        listView = new ListView();


        listView.setPrefSize(310,460);
        listView2.setPrefSize(300, 140);

        /* Button shit till line ? //comeback to this */
        listView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                System.out.println(listView.getSelectionModel().getSelectedItem());
                String team_Name = listView.getSelectionModel().getSelectedItem().toString();
                listView2.getItems().clear();
                try {
                    PreparedStatement ps = myConn.prepareStatement("SELECT * FROM Teams WHERE teamName = ?");
                    ps.setString(1, team_Name);
                    ResultSet rs = ps.executeQuery();
                    while(rs.next())
                        listView2.getItems().add("Team: " + rs.getString(2));

                }catch(Exception e){System.out.println("E: " + e);}
                try{
                    PreparedStatement ps = myConn.prepareStatement("SELECT conferenceName FROM Conferences c JOIN Teams t ON c.id = t.confId WHERE t.teamName = ?");
                    ps.setString(1, team_Name);
                    ResultSet rs = ps.executeQuery();
                    while(rs.next())
                        listView2.getItems().add("Conference: " + rs.getString(1));
                }catch(Exception e){}
                try{
                    PreparedStatement ps = myConn.prepareStatement("SELECT name FROM Players p JOIN Teams t ON p.teamID = t.id WHERE t.teamName = ?");
                    ps.setString(1, team_Name);
                    ResultSet rs = ps.executeQuery();
                    while(rs.next())
                        listView2.getItems().add("Player: " + rs.getString(1));
                }catch(Exception e){}
            }
        });
        // First Round
        btn64.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
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
                    rs.close();
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
        //Need nodeJaayson help
        btnInsert.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                System.out.println("jayson you're my only hope");
                /*try{
                    PreparedStatement ps = myConn.preparedStatement("INSERT INTO Players ()")
                }*/
            }
        });
        //JASYON
        btnDelete.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                System.out.println("jasyon durulu");
            }
        });
        //fk this im done
        btnUpdate.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                System.out.println("im hungry but tired");
            }
        });
        //this is gonna suck
        btnReport.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {

            }
        });

        /*Lay it out //Game of Thrones season 8 is a disappointment*/
        lbl.setStyle("-fx-font: 24 arial;");
        lbl.setLayoutX(330);
        lbl.setLayoutY(20);
        textField3.setLayoutX(482);
        textField3.setLayoutY(190);
        textField3.setPromptText("current name");
        textField2.setLayoutX(482);
        textField2.setLayoutY(220);
        textField2.setPromptText("new name");
        textField1.setLayoutX(482);
        textField1.setLayoutY(130);
        textField1.setPromptText("enter team, mind capitals");
        textField.setLayoutX(482);
        textField.setLayoutY(100);
        textField.setPromptText("enter player");
        btnUpdate.setPrefWidth(148);
        btnUpdate.setLayoutX(482);
        btnUpdate.setLayoutY(250);
        btnDelete.setLayoutX(482);
        btnDelete.setLayoutY(160);
        btnInsert.setLayoutX(557);
        btnInsert.setLayoutY(160);
        btnReport.setLayoutX(578);
        btnReport.setLayoutY(280);
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
        listView2.setLayoutX(330);
        listView2.setLayoutY(330);
        listView.setLayoutX(10);
        listView.setLayoutY(10);

        /*Set the stage*/
        root = new Pane();
        root.getChildren().addAll(btnReport, btnUpdate, btnDelete, btnInsert, btn64, btn32, btn16, btn8, btn4, btn2, btn1, lbl,
                textField3, textField2, textField1, textField, listView, listView2);
        primaryStage.setScene(new Scene(root, 640, 480));
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}