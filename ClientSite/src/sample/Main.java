package sample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;



import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;


import java.util.ArrayList;
import java.util.Iterator;

import static javafx.scene.text.FontWeight.BOLD;

public class Main extends Application {
    int client;
    Stage window;
    Voter voter;
    Admin adminClass;
    Candidate candidate;
    Socket socket;
    ComboBox<String> setPostComboBox;
    private String name;
    private String id;
    private String post;
    TableView<Voter> voterListTable;
    ArrayList<Voter> voterListArray = new ArrayList<Voter>();
    TableView<Candidate> candidateListTable;
    ArrayList<Candidate>candidateArray=new ArrayList<Candidate>();
    ArrayList<Result>resultList=new ArrayList<Result>();
    ComboBox<String> comboBoxPresident;
    ComboBox<String> comboBoxVicePresident;
    TableView<Result> resultTable;


    public static void main(String[] args) {
        launch(args);
    }



    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            socket = new Socket("127.0.0.1", 6000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Done");
        window = primaryStage;
        window.setTitle("ONLINE VOTING SYSTEM ");


        //GridPane with 10px padding around edge
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(1);
        grid.setHgap(1);

        //set background
        FileInputStream input = new FileInputStream("vote.gif");
        Image image = new Image(input, 800, 800, false, false);

        BackgroundImage backgroundimage = new BackgroundImage(image,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT);

        Background background = new Background(backgroundimage);

        //vote finished scene start
        GridPane voteStoppedGrid = new GridPane();
        voteStoppedGrid.setPadding(new Insets(10, 10, 10, 10));
        voteStoppedGrid.setVgap(1);
        voteStoppedGrid.setHgap(1);

        Text voteStoppedText = new Text("Vote is Over!!!  :(");
        voteStoppedText.setFont(Font.font("", 50));
        voteStoppedText.setFill(Color.BLACK);
        voteStoppedText.setStroke(Color.BLANCHEDALMOND);
        GridPane.setConstraints(voteStoppedText, 150, 300);


        Button voteStoppedBackButton = new Button("Back");
        GridPane.setConstraints(voteStoppedBackButton,0,0);
        voteStoppedGrid.getChildren().addAll(voteStoppedText,voteStoppedBackButton);

        Scene voteStoppedScene = new Scene(voteStoppedGrid, 800, 800);

        //**********************************************ends****************************


        //vote running  page starts**********************************************
        GridPane voteRunningGrid = new GridPane();
        voteRunningGrid.setPadding(new Insets(10, 10, 10, 10));
        voteRunningGrid.setVgap(1);
        voteRunningGrid.setHgap(1);

        Text voteRunningText = new Text("Vote is still running");
        voteRunningText.setFont(Font.font("", 50));
        voteRunningText.setFill(Color.BLACK);
        voteRunningText.setStroke(Color.BLANCHEDALMOND);
        GridPane.setConstraints(voteRunningText, 100, 200);



        Button voteRunningBackButton = new Button("Back");

        //voteRunningBackButton.setFont(Font.font("",20));
        GridPane.setConstraints(voteRunningBackButton,100,250);

        voteRunningGrid.getChildren().addAll(voteRunningBackButton,voteRunningText);

        Scene voteRunningScene = new Scene(voteRunningGrid, 800, 800);


        //vote running grid end******************************

        //vote hasnot started page starts*****************************************

        GridPane voteNotStartedGrid = new GridPane();
        voteNotStartedGrid.setPadding(new Insets(10, 10, 10, 10));
        voteNotStartedGrid.setVgap(1);
        voteNotStartedGrid.setHgap(1);

        Text voteNotStartedText = new Text("Vote has not started yet .. :/ ");
        voteNotStartedText.setFont(Font.font("", 40));
        voteNotStartedText.setFill(Color.BLACK);
        voteNotStartedText.setStroke(Color.BLANCHEDALMOND);
        GridPane.setConstraints(voteNotStartedText, 110, 300);


        Button voteNotStartedBackButton = new Button("Back");
        GridPane.setConstraints(voteNotStartedBackButton,0,500);
        voteNotStartedGrid.getChildren().addAll(voteNotStartedText,voteRunningBackButton);

        Scene voteNotStartedScene = new Scene(voteNotStartedGrid, 800, 800);



        //vote hasnot started page ends*****************************************


        //Admin Button
        Button adminButton = new Button("Admin");
        adminButton.setFont(Font.font("", BOLD, 30));
        GridPane.setConstraints(adminButton, 120, 380);

        //Voter Button
        Button voterButton = new Button("Voter");
        voterButton.setFont(Font.font("", BOLD, 30));
        GridPane.setConstraints(voterButton, 190, 440);

        //candidate Button
        Button candidateButton = new Button("Candidate");
        candidateButton.setFont(Font.font("", BOLD, 30));
        GridPane.setConstraints(candidateButton, 250, 490);


        //Add everything to grid
        grid.setBackground(background);

        //Add everything to grid
        grid.getChildren().addAll(adminButton, voterButton, candidateButton);
        grid.setBackground(background);
        Scene homeScene = new Scene(grid, 800, 800);

        //admin login
        GridPane adminGridpane = new GridPane();
        adminGridpane.setPadding(new Insets(10, 10, 10, 10));
        adminGridpane.setVgap(1);
        adminGridpane.setHgap(1);

        //set background
        FileInputStream admin = new FileInputStream("admin2.jpg");
        Image adminImage = new Image(admin, 800, 800, false, false);

        BackgroundImage adminBackgroundimage = new BackgroundImage(adminImage,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT);

        Background adminBackground = new Background(adminBackgroundimage);

        //View Details Label
        Label adminPassword = new Label("Admin Password");
        adminPassword.setFont(Font.font("", BOLD, 22));
        adminPassword.setTextFill(Color.web("#003300"));
        GridPane.setConstraints(adminPassword, 210, 200);

        //taking name input  text field
        PasswordField inAdminPassword = new PasswordField();
        GridPane.setConstraints(inAdminPassword, 230, 200);
        inAdminPassword.setPrefHeight(40);
        inAdminPassword.setPrefWidth(200);

        //admin log in  button
        Button adminLogin = new Button("LOG IN");
        adminLogin.setFont(Font.font("", 22));
        adminLogin.setTextFill(Color.web("#3366cc"));
        GridPane.setConstraints(adminLogin, 230, 240);

        //button to go back to home
        Button adminBackButtonToHome = new Button("Back");
        adminBackButtonToHome.setFont(Font.font("", 22));
        adminBackButtonToHome.setTextFill(Color.web("#3366cc"));
        GridPane.setConstraints(adminBackButtonToHome, 250, 600);

        //Add everything to grid
        adminGridpane.getChildren().addAll(adminPassword, inAdminPassword, adminLogin, adminBackButtonToHome);
        adminGridpane.setBackground(adminBackground);
        adminBackButtonToHome.setOnAction(e -> window.setScene(homeScene));//to switch  to that page where admin will log in

        //declaring admin login scene
        Scene adminLoginScene = new Scene(adminGridpane, 800, 800);
        //admin login end*******************************************************************


        //page for voter to login->start********************************************************


        //GridPane with 10px padding around edge
        GridPane voterLoginGrid = new GridPane();
        voterLoginGrid.setPadding(new Insets(10, 10, 10, 10));
        voterLoginGrid.setVgap(1);
        voterLoginGrid.setHgap(1);
        //set background
        FileInputStream voterLoginInput = new FileInputStream("vote1.jpg");
        Image voterLoginImage = new Image(voterLoginInput, 800, 800, false, false);

        BackgroundImage voterLoginBackgroundImage = new BackgroundImage(voterLoginImage,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT);

        Background voterLoginBackground = new Background(voterLoginBackgroundImage);

        //Name Label - constrains use (child, column, row)
        //voter login page
        Label voterNameLabel = new Label("ID:");
        voterNameLabel.setFont(Font.font("", BOLD, 30));
        GridPane.setConstraints(voterNameLabel, 50, 90);

        //voter Name Input
        TextField voterNameInput = new TextField(" ");
        GridPane.setConstraints(voterNameInput, 60, 90);

        //voter Password Label
        Label voterPasswordLabel = new Label("Password:");
        voterPasswordLabel.setFont(Font.font("", BOLD, 30));
        GridPane.setConstraints(voterPasswordLabel, 50, 100);

        //voter Password Input
        PasswordField voterPasswordInput = new PasswordField();
        voterPasswordInput.setPromptText("password");
        GridPane.setConstraints(voterPasswordInput, 60, 100);

        //voter Login
        Button voterLoginButton = new Button("Log In");
        voterLoginButton.setFont(Font.font("", BOLD, 25));
        GridPane.setConstraints(voterLoginButton, 60, 110);

        //button voter going back  to home
        Button voterBackButtonToHome = new Button("Back");
        voterBackButtonToHome.setFont(Font.font("", 22));
        voterBackButtonToHome.setTextFill(Color.web("#3366cc"));
        GridPane.setConstraints(voterBackButtonToHome, 61, 110);

        //back button action handle
        voterBackButtonToHome.setOnAction(e -> window.setScene(homeScene));//to switch  to that page where admin will log in

        //Add everything to grid
        voterLoginGrid.getChildren().addAll(voterNameLabel, voterNameInput, voterPasswordLabel, voterPasswordInput, voterLoginButton, voterBackButtonToHome);
        voterLoginGrid.setBackground(voterLoginBackground);

        Scene voterLoginScene = new Scene(voterLoginGrid, 800, 800);
        //voter login page ends******************************************************************

        //candidate login scene starts**********************************************

        //GridPane with 10px padding around edge
        GridPane candidateLoginGrid = new GridPane();
        candidateLoginGrid.setPadding(new Insets(10, 10, 10, 10));
        candidateLoginGrid.setVgap(1);
        candidateLoginGrid.setHgap(1);
        //set background
        FileInputStream candidateLoginInput = new FileInputStream("candi.jpeg");
        Image candidateLoginImage = new Image(candidateLoginInput, 800, 800, false, false);

        BackgroundImage candidateLoginBackgroundImage = new BackgroundImage(candidateLoginImage,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT);

        Background candidateLoginbackground = new Background(candidateLoginBackgroundImage);

        //Name Label - constrains use (child, column, row)
        //Candidate login page

        Label candidateNameLabel = new Label("ID:");
        candidateNameLabel.setFont(Font.font("", BOLD, 30));
        GridPane.setConstraints(candidateNameLabel, 50, 90);

        //candidate Name Input
        TextField candidateNameInput = new TextField(" ");
        GridPane.setConstraints(candidateNameInput, 60, 90);

        //candidate Password Label
        Label candidatePasswordLabel = new Label("Password:");
        candidatePasswordLabel.setFont(Font.font("", BOLD, 30));
        GridPane.setConstraints(candidatePasswordLabel, 50, 100);

        //candidate Password Input
        PasswordField candidatePasswordInput = new PasswordField();
        candidatePasswordInput.setPromptText("password");
        GridPane.setConstraints(candidatePasswordInput, 60, 100);

        //candidate Login
        Button candidateLoginButton = new Button("Log In");
        candidateLoginButton.setFont(Font.font("", BOLD, 25));
        GridPane.setConstraints(candidateLoginButton, 60, 110);


        //button candidate going back  to home
        Button candidateBackButtonToHome = new Button("Back");
        candidateBackButtonToHome.setFont(Font.font("", 22));
        candidateBackButtonToHome.setTextFill(Color.web("#3366cc"));
        GridPane.setConstraints(candidateBackButtonToHome, 250, 600);
        //back button action handle
        candidateBackButtonToHome.setOnAction(e -> window.setScene(homeScene));//to switch  to that page where admin will log in


        //Add everything to grid
        candidateLoginGrid.getChildren().addAll(candidateNameLabel, candidateNameInput, candidatePasswordLabel, candidatePasswordInput, candidateLoginButton, candidateBackButtonToHome);
        candidateLoginGrid.setBackground(candidateLoginbackground);

        Scene candidateLoginScene = new Scene(candidateLoginGrid, 800, 800);

        //candidate login ends**********************************

        //admin loggedin scene starts*************************


        //admin logged in
        //GridPane with 10px padding around edge
        GridPane adminLoggedinGrid = new GridPane();
        adminLoggedinGrid.setPadding(new Insets(10, 10, 10, 10));
        adminLoggedinGrid.setVgap(1);
        adminLoggedinGrid.setHgap(1);

        //set background at the page where voter is logged in
        FileInputStream adminloggedInInput = new FileInputStream("admin2.jpg");
        Image adminLoggedInImage = new Image(adminloggedInInput, 800, 800, false, false);

        BackgroundImage adminLoggedInBackgroundImage = new BackgroundImage(adminLoggedInImage,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT);

        Background adminLoggedinBackground = new Background(adminLoggedInBackgroundImage);
        adminLoggedinGrid.setBackground(adminLoggedinBackground);


        //setting buttons
        Button logOutAdmin = new Button("Log Out");
        logOutAdmin.setFont(Font.font("", BOLD, 22));
        logOutAdmin.setTextFill(Color.web("#3366cc"));
        GridPane.setConstraints(logOutAdmin, 440, 350);

        Button seeVoteResult = new Button("Result");
        seeVoteResult.setFont(Font.font("", BOLD, 22));
        seeVoteResult.setTextFill(Color.web("#3366cc"));
        GridPane.setConstraints(seeVoteResult, 440, 355);


        Button setPollTime = new Button("Poll time");
        setPollTime.setFont(Font.font("", BOLD, 22));
        setPollTime.setTextFill(Color.web("#3366cc"));
        GridPane.setConstraints(setPollTime, 440, 365);

        Button editVoterInfo = new Button("Update voter");
        editVoterInfo.setFont(Font.font("", BOLD, 22));
        editVoterInfo.setTextFill(Color.web("#3366cc"));
        GridPane.setConstraints(editVoterInfo, 440, 375);

        Button editCandidateInfo = new Button("Update Candidate");
        editCandidateInfo.setFont(Font.font("", BOLD, 22));
        editCandidateInfo.setTextFill(Color.web("#3366cc"));
        GridPane.setConstraints(editCandidateInfo, 440, 380);

        Button viewVoterDetails = new Button("Voters");
        viewVoterDetails.setFont(Font.font("", BOLD, 22));
        viewVoterDetails.setTextFill(Color.web("#3366cc"));
        GridPane.setConstraints(viewVoterDetails, 440, 360);

        Button viewCandidateDetails = new Button("Candidates");
        viewCandidateDetails.setFont(Font.font("", BOLD, 22));
        viewCandidateDetails.setTextFill(Color.web("#3366cc"));
        GridPane.setConstraints(viewCandidateDetails, 440, 370);

        adminLoggedinGrid.getChildren().addAll(setPollTime, editVoterInfo, logOutAdmin, seeVoteResult, editCandidateInfo, viewCandidateDetails, viewVoterDetails);
        Scene adminLoggedInScene = new Scene(adminLoggedinGrid, 800, 800);
        adminLogin.setOnAction(e -> {
            client=1;
            String str=inAdminPassword.getText();
            adminClass=new Admin(socket);
            boolean login = false;
            try {
                login = adminClass.login(str);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            if(login)
            window.setScene(adminLoggedInScene);
        });
        //admin loggedin scene ends***************************
        logOutAdmin.setOnAction(e -> {
            window.setScene(homeScene);
            inAdminPassword.clear();
        });
        //setPOll time page starts **************************
        //GridPane with 10px padding around edge
        GridPane pollPage = new GridPane();
        pollPage.setPadding(new Insets(10, 10, 10, 10));
        pollPage.setVgap(1);
        pollPage.setHgap(1);

        //set background at the page where voter is logged in

        pollPage.setBackground(adminBackground);


        //setting buttons
        Label finishPoll = new Label("End");
        finishPoll.setFont(Font.font("", BOLD, 22));
        finishPoll.setTextFill(Color.web("#003300"));
        GridPane.setConstraints(finishPoll, 220, 280);

        //finish poll text field
        TextField pollFinish = new TextField();
        GridPane.setConstraints(pollFinish, 220, 290);
        pollFinish.setPrefHeight(40);
        pollFinish.setPrefWidth(200);
        //-----startpoll button


        Label startPoll = new Label("Start");
        startPoll.setFont(Font.font("", BOLD, 22));
        startPoll.setTextFill(Color.web("#003300"));
        GridPane.setConstraints(startPoll, 220, 240);
        //startpoll text field
        TextField pollStart = new TextField();
        GridPane.setConstraints(pollStart, 220, 250);
        pollStart.setPrefHeight(40);
        pollStart.setPrefWidth(200);
        //save button
        Button save = new Button("Save");
        save.setFont(Font.font("", 22));
        save.setTextFill(Color.web("#003300"));
        GridPane.setConstraints(save, 220, 310);

        Button goBackAdmin = new Button("Back");
        goBackAdmin.setFont(Font.font("", 22));
        goBackAdmin.setTextFill(Color.web("#003300"));
        GridPane.setConstraints(goBackAdmin, 220, 312);

        pollPage.getChildren().addAll(finishPoll, startPoll, pollStart, pollFinish, save, goBackAdmin);
        Scene setPollTimeScene = new Scene(pollPage, 800, 800);

        setPollTime.setOnAction(e -> window.setScene(setPollTimeScene));
        goBackAdmin.setOnAction(e -> window.setScene(adminLoggedInScene));
        save.setOnAction(e-> {
            String start=pollStart.getText();
            String end=pollFinish.getText();
            boolean flag=true;
            try{
                Integer.parseInt(start);
                Integer.parseInt(end);
                adminClass.setPollTime(start,end);
            }catch(Exception ex){
                System.out.println("Ok lol");
                flag=false;
            }
            if(flag)
                window.setScene(adminLoggedInScene);
            else {
                pollStart.setText("Enter hour in 24 hour format");
                pollFinish.setText("Enter hour in 24 hour format");
            }
        }
        );

        //setPoll time page ends******************************

        //editVoterInfopage starts ***************************
        //admin logged in
        //GridPane with 10px padding around edge
        GridPane editVoterDetailsGrid = new GridPane();
        editVoterDetailsGrid.setPadding(new Insets(10, 10, 10, 10));
        editVoterDetailsGrid.setVgap(1);
        editVoterDetailsGrid.setHgap(1);


        editVoterDetailsGrid.setBackground(adminBackground);


        //setting buttons
        // //
        // admin page-> edit Voter details->thid page
        /*Button delete = new Button ("DELETE ");
        delete.setFont(Font.font("", BOLD , 30));
        delete.setTextFill(Color.web("#663300"));
        */

        Button add = new Button("ADD ");
        add.setFont(Font.font("", BOLD, 30));
        add.setTextFill(Color.web("#663300"));

        Button edit = new Button("EDIT");
        edit.setFont(Font.font("", BOLD, 30));
        edit.setTextFill(Color.web("#663300"));

        GridPane.setConstraints(edit, 0, 340);
        GridPane.setConstraints(add, 0, 370);
        //GridPane.setConstraints(delete,0,400);


        Button toGoBackAdmin = new Button("Back");
        toGoBackAdmin.setFont(Font.font("", BOLD, 22));
        toGoBackAdmin.setTextFill(Color.web("#663300"));
        GridPane.setConstraints(toGoBackAdmin, 0, 430);

        editVoterDetailsGrid.getChildren().addAll(edit, add, toGoBackAdmin);
        Scene editVoterDetailsScene = new Scene(editVoterDetailsGrid, 800, 800);

        //editVoterInfopage ends*****************************
        editVoterInfo.setOnAction(e -> window.setScene(editVoterDetailsScene));
        toGoBackAdmin.setOnAction(e -> window.setScene(adminLoggedInScene));
        //addvoter page starts********************************************

        //GridPane with 10px padding around edge
        GridPane addVoterGrid = new GridPane();
        addVoterGrid.setPadding(new Insets(10, 10, 10, 10));
        addVoterGrid.setVgap(1);
        addVoterGrid.setHgap(1);


        addVoterGrid.setBackground(adminBackground);


        //setting buttons


        //-----adding name
        //add voter name label
        Label addVoterName = new Label("Set Name:");
        addVoterName.setFont(Font.font("", BOLD, 22));
        addVoterName.setTextFill(Color.web("#3366cc"));
        GridPane.setConstraints(addVoterName, 220, 270);
        //taking name input  text field
        TextField inVoterName = new TextField();
        GridPane.setConstraints(inVoterName, 230, 270);
        inVoterName.setPrefHeight(40);
        inVoterName.setPrefWidth(200);
        //password label
        Label addVoterpass = new Label("Set Password:");
        addVoterpass.setFont(Font.font("", BOLD, 22));
        addVoterpass.setTextFill(Color.web("#3366cc"));
        GridPane.setConstraints(addVoterpass, 220, 290);

        //taking input  voter password
        PasswordField inVoterPass = new PasswordField();
        GridPane.setConstraints(inVoterPass, 230, 290);
        inVoterPass.setPrefHeight(40);
        inVoterPass.setPrefWidth(200);
        //save button
        Button saveVoter = new Button("Save");
        saveVoter.setFont(Font.font("", 22));
        saveVoter.setTextFill(Color.web("#3366cc"));
        GridPane.setConstraints(saveVoter, 230, 300);

        //goback button

        Button backToEditVoterdetails = new Button("Back");
        backToEditVoterdetails.setFont(Font.font("", 22));
        backToEditVoterdetails.setTextFill(Color.web("#3366cc"));
        GridPane.setConstraints(backToEditVoterdetails, 0, 600);

//add Text
        Text addNewvoterText = new Text("ADD NEW VOTER ");
        addNewvoterText.setFont(Font.font("", 20));
        addNewvoterText.setFill(Color.WHITE);
        addNewvoterText.setStroke(Color.BLANCHEDALMOND);
        GridPane.setConstraints(addNewvoterText, 230, 0);

        addVoterGrid.getChildren().addAll(inVoterName, inVoterPass, addVoterName, addVoterpass, saveVoter, backToEditVoterdetails, addNewvoterText);
        Scene addNewVoterScene = new Scene(addVoterGrid, 800, 800);


        //addVoter page ends**********************************************
        add.setOnAction(e -> window.setScene(addNewVoterScene));
       saveVoter.setOnAction(e->{
            String name=inVoterName.getText();
            String password=inVoterPass.getText();
            adminClass.addVoter(name,password);
            window.setScene(editVoterDetailsScene);


       });
        backToEditVoterdetails.setOnAction(e -> window.setScene(editVoterDetailsScene));

        //voter loggedin page starts here********************************************


        //GridPane with 10px padding around edge
        GridPane voterLoggedinGrid = new GridPane();
        voterLoggedinGrid.setPadding(new Insets(10, 10, 10, 10));
        voterLoggedinGrid.setVgap(1);
        voterLoggedinGrid.setHgap(1);

        //set background
        FileInputStream voterLoggedinInput = new FileInputStream("voter.jpg");
        Image voterLoggedinImage = new Image(voterLoggedinInput, 800, 800, false, false);

        BackgroundImage voterLoggedinBackgroundimage = new BackgroundImage(voterLoggedinImage,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT);

        Background voterLoggedinBackground = new Background(voterLoggedinBackgroundimage);


        Button voterViewDetailsButton = new Button("Details");
        voterViewDetailsButton.setFont(Font.font("", 20));
        GridPane.setConstraints(voterViewDetailsButton, 60, 440);
        //Change Password Label
        Button voterChangePasswordButton = new Button("Change Password");
        voterChangePasswordButton.setFont(Font.font("", 20));
        GridPane.setConstraints(voterChangePasswordButton, 60, 500);


        //Logout
        Button voterLogOutButton = new Button("Log Out");
        voterLogOutButton.setFont(Font.font("", 20));
        GridPane.setConstraints(voterLogOutButton, 60, 520);

        //Give Vote
        Button giveVoteButton = new Button("Vote");
        giveVoteButton.setFont(Font.font("", 20));
        GridPane.setConstraints(giveVoteButton, 60, 460);

        //See Result
        Button seeResultButtonVoter = new Button("See Result");
        seeResultButtonVoter.setFont(Font.font("", 20));
        GridPane.setConstraints(seeResultButtonVoter, 60, 480);


        //Add everything to grid
        voterLoggedinGrid.getChildren().addAll(voterChangePasswordButton, voterLogOutButton, giveVoteButton, seeResultButtonVoter, voterViewDetailsButton);
        voterLoggedinGrid.setBackground(voterLoggedinBackground);

        Scene voterLoggedinScene = new Scene(voterLoggedinGrid, 800, 800);

        //voter loggedin page ends***********************************

        //voter changed password starts************************************

        //GridPane with 10px padding around edge
        GridPane voterChangedPasswordGrid = new GridPane();
        voterChangedPasswordGrid.setPadding(new Insets(10, 10, 10, 10));
        voterChangedPasswordGrid.setVgap(1);
        voterChangedPasswordGrid.setHgap(1);
        //set background
        FileInputStream voterChangedPasswordInput = new FileInputStream("vote1.jpg");
        Image voterChangedPasswordImage = new Image(voterChangedPasswordInput, 800, 800, false, false);

        BackgroundImage voterChangedPasswordBackgroundImage = new BackgroundImage(voterChangedPasswordImage,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT);

        Background voterChangedPasswordBackground = new Background(voterChangedPasswordBackgroundImage);

        //changed password change page

        //old Password Label
        Label oldPasswordLabel = new Label("Old Password  :");
        oldPasswordLabel.setFont(Font.font("", BOLD, 30));
        GridPane.setConstraints(oldPasswordLabel, 80, 90);

        //old Password Input
        PasswordField oldPasswordInput = new PasswordField();
        oldPasswordInput.setPromptText("password");
        GridPane.setConstraints(oldPasswordInput, 90, 90);


        //New Password Label
        Label newPasswordLabel = new Label("New Password:");
        newPasswordLabel.setFont(Font.font("", BOLD, 30));
        GridPane.setConstraints(newPasswordLabel, 80, 100);

        //New Password Input
        PasswordField newPasswordInput = new PasswordField();
        newPasswordInput.setPromptText("password");
        GridPane.setConstraints(newPasswordInput, 90, 100);


        //confirm  Password Label
        Label confirmPasswordLabel = new Label("Confirm Password:");
        confirmPasswordLabel.setFont(Font.font("", BOLD, 30));
        GridPane.setConstraints(confirmPasswordLabel, 80, 110);

        //confirm Password Input
        PasswordField confirmPasswordInput = new PasswordField();
        confirmPasswordInput.setPromptText("password");
        GridPane.setConstraints(confirmPasswordInput, 90, 110);


        //Save  chANGED PASSWORD Button
        Button saveChangedPasswordVoterButton = new Button("Save");
        saveChangedPasswordVoterButton.setFont(Font.font("", BOLD, 25));
        saveChangedPasswordVoterButton.setTextFill(Color.web("#3366cc"));

        GridPane.setConstraints(saveChangedPasswordVoterButton, 100, 500);

        Button backToVoterLoggedIn = new Button("Back");
        backToVoterLoggedIn.setFont(Font.font("", BOLD, 22));
        backToVoterLoggedIn.setTextFill(Color.web("#3366cc"));
        GridPane.setConstraints(backToVoterLoggedIn, 100, 510);

        backToVoterLoggedIn.setOnAction(e -> window.setScene(voterLoggedinScene));//switch to vthat page where voter logged in first
        voterLogOutButton.setOnAction(e -> window.setScene(homeScene));//switch to home
        saveChangedPasswordVoterButton.setOnAction(e -> {
            String oldpass = oldPasswordInput.getText();
            String newpass1 = newPasswordInput.getText();
            String newpass2 = confirmPasswordInput.getText();
            boolean flag = false;
            if (newpass1.equals(newpass2)) {
                try {
                    flag = voter.changePassword(oldpass, newpass1);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
            System.out.println(flag);
            if (flag)
                window.setScene(voterLoggedinScene);

        });////switch to vthat page where voter logged in first

        //Add everything to grid
        voterChangedPasswordGrid.getChildren().addAll(oldPasswordLabel, backToVoterLoggedIn, oldPasswordInput, newPasswordLabel, newPasswordInput, confirmPasswordLabel, confirmPasswordInput, saveChangedPasswordVoterButton);
        voterChangedPasswordGrid.setBackground(voterChangedPasswordBackground);

        Scene voterChangedPasswordScene = new Scene(voterChangedPasswordGrid, 800, 800);

        ////voter changed password ends************************************

        voterChangePasswordButton.setOnAction(e -> window.setScene(voterChangedPasswordScene));//to switch  to that page where voter  logged in


        voterLoginButton.setOnAction(e -> {
            client=2;
            System.out.println("OK um ok now");
            boolean flag = false;
            try {
                String str = voterNameInput.getText();
                int id = Integer.parseInt(str);
                String password = voterPasswordInput.getText();
                voter = new Voter(socket);
                flag = voter.login(id, password);
            } catch (Exception ex) {
            }
            if (flag) {
                System.out.println("Here");
                window.setScene(voterLoggedinScene);
                voterPasswordInput.clear();
                voterNameInput.clear();
            }
            else {
                voterNameInput.setText("Wrong Password or ID");
                voterPasswordInput.clear();
            }
        });

        //to switch  to that page where voter  logged in
        editVoterInfo.setOnAction(e -> window.setScene(editVoterDetailsScene));
        candidateButton.setOnAction(e -> window.setScene(candidateLoginScene));
        adminButton.setOnAction(e -> window.setScene(adminLoginScene));//to switch  to that page where admin will log in
        voterButton.setOnAction(e -> window.setScene(voterLoginScene));//to switch  to that page where voter will log in
//page after candidate logged in started***************************************************

        GridPane candiateLoggedInGrid = new GridPane();
        candiateLoggedInGrid.setPadding(new Insets(10, 10, 10, 10));
        candiateLoggedInGrid.setVgap(1);
        candiateLoggedInGrid.setHgap(1);
        //set background


        //View Details Label
        Button viewCandidateDetailButton = new Button("View Details");
        viewCandidateDetailButton.setFont(Font.font("", BOLD, 20));
        GridPane.setConstraints(viewCandidateDetailButton, 300, 480);

        Button candidateLogOutButton = new Button("Log Out");
        candidateLogOutButton.setFont(Font.font("", BOLD, 20));
        GridPane.setConstraints(candidateLogOutButton, 300, 500);

        candidateLogOutButton.setOnAction(e -> window.setScene(homeScene));
        //Add everything to grid
        candiateLoggedInGrid.getChildren().addAll(viewCandidateDetailButton, candidateLogOutButton);
        candiateLoggedInGrid.setBackground(candidateLoginbackground);

        Scene candidateLoggedInScene = new Scene(candiateLoggedInGrid, 800, 800);

        ////page after candidate logged in ended***************************************************
        candidateLoginButton.setOnAction(e -> {
            String str = candidateNameInput.getText();
            int id = Integer.parseInt(str);
            String password = candidatePasswordInput.getText();
            candidate = new Candidate(socket);
            boolean flag = false;
            try {
                flag = candidate.login(id, password);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            if (flag) {
                System.out.println("Here");
                window.setScene(candidateLoggedInScene);
                candidatePasswordInput.clear();
            }
        });
        //page of edit candidate details starts***************************************
        GridPane editCandidateDetailsGrid = new GridPane();
        editCandidateDetailsGrid.setPadding(new Insets(10, 10, 10, 10));
        editCandidateDetailsGrid.setVgap(1);
        editCandidateDetailsGrid.setHgap(1);

        //set background at the page where voter is logged in
        editCandidateDetailsGrid.setBackground(candidateLoginbackground);

        // admin page-> edit Voter details
        Button editCandidateButton = new Button("EDIT");
        editCandidateButton.setFont(Font.font("", BOLD, 30));
        editCandidateButton.setTextFill(Color.web("#663300"));


        Button addCandidate = new Button("ADD");
        addCandidate.setFont(Font.font("", BOLD, 30));
        addCandidate.setTextFill(Color.web("#663300"));


        Button deleteCandidate = new Button("DELETE");
        deleteCandidate.setFont(Font.font("", BOLD, 30));
        deleteCandidate.setTextFill(Color.web("#663300"));


        GridPane.setConstraints(editCandidateButton, 0, 340);
        GridPane.setConstraints(addCandidate, 0, 400);
        GridPane.setConstraints(deleteCandidate, 0, 370);

        Button goBackToEditCandidate = new Button("Back");
        goBackToEditCandidate.setFont(Font.font("", BOLD, 22));
        goBackToEditCandidate.setTextFill(Color.web("#663300"));
        GridPane.setConstraints(goBackToEditCandidate, 0, 430);

        editCandidateDetailsGrid.getChildren().addAll(editCandidateButton, addCandidate, deleteCandidate, goBackToEditCandidate);
        Scene editCandidateDetailsScene = new Scene(editCandidateDetailsGrid, 800, 800);


        //page of edit candidate details ends*******************************************
        editCandidateInfo.setOnAction(e -> window.setScene(editCandidateDetailsScene));
        goBackToEditCandidate.setOnAction(e -> window.setScene(adminLoggedInScene));
        //add candidate page starts*************************************************

        GridPane addCandidateGrid = new GridPane();
        addCandidateGrid.setPadding(new Insets(10, 10, 10, 10));
        addCandidateGrid.setVgap(1);
        addCandidateGrid.setHgap(1);

        addCandidateGrid.setBackground(adminBackground);


        //add candidate name label
        Label addCandidateName = new Label("Set Name:");
        addCandidateName.setFont(Font.font("", BOLD, 22));

        addCandidateName.setTextFill(Color.web("#161515"));
        GridPane.setConstraints(addCandidateName, 200, 270);

        TextField inAddCandidateName = new TextField();
        String text = inAddCandidateName.getText();
        GridPane.setConstraints(inAddCandidateName, 201, 270);
        inAddCandidateName.setPrefHeight(40);
        inAddCandidateName.setPrefWidth(200);

        //add candidate password label
        Label addCandidatePass = new Label("Set Password:");
        addCandidatePass.setFont(Font.font("", BOLD, 22));
        addCandidatePass.setTextFill(Color.web("#161515"));
        GridPane.setConstraints(addCandidatePass, 200, 290);


        PasswordField inAddCandidatePass = new PasswordField();
        GridPane.setConstraints(inAddCandidatePass, 201, 290);
        inAddCandidatePass.setPrefHeight(40);
        inAddCandidatePass.setPrefWidth(200);

        //add candidate post label
        Label addCandidatePost = new Label("Set Post:");
        addCandidatePost.setFont(Font.font("", BOLD, 22));
        addCandidatePost.setTextFill(Color.web("#161515"));
        GridPane.setConstraints(addCandidatePost, 200, 310);

        //post text field
        TextField inAddCandidatePost = new TextField();
        GridPane.setConstraints(inAddCandidatePost, 201, 310);
        inAddCandidatePost.setPrefHeight(40);
        inAddCandidatePost.setPrefWidth(200);

        Button addCandidateSave = new Button("Save");
        addCandidateSave.setFont(Font.font("", 22));
        addCandidateSave.setTextFill(Color.web("#3366cc"));
        GridPane.setConstraints(addCandidateSave, 201, 330);

        addCandidateSave.setOnAction(e->{
            String name=inAddCandidateName.getText();
            String password=inAddCandidatePass.getText();
            String post=setPostComboBox.getValue();
            System.out.println(post);
            inAddCandidateName.clear();
            inAddCandidatePass.clear();
            setPostComboBox.setPromptText("Select Candidate Post");
            adminClass.addCandidate(name,password,post);
            window.setScene(editCandidateDetailsScene);
        });

        Button addCandidateBack = new Button("Back");
        addCandidateBack.setFont(Font.font("", 22));
        addCandidateBack.setTextFill(Color.web("#3366cc"));
        GridPane.setConstraints(addCandidateBack, 0, 500);
        addCandidateBack.setOnAction(e -> window.setScene(editCandidateDetailsScene));

        Text addCandidatetext = new Text("ADD NEW CANDIDATE ");
        addCandidatetext.setFont(Font.font("", 20));
        addCandidatetext.setFill(Color.BLACK);
        GridPane.setConstraints(addCandidatetext, 200, 0);

        GridPane.setConstraints(addCandidatetext, 200, 0);

        //adding comboBox
        setPostComboBox = new ComboBox<>();
        setPostComboBox.getItems().addAll("President", "Vice President");
        setPostComboBox.setPromptText("Select Candidate Post");
        GridPane.setConstraints(setPostComboBox, 201, 310);


        addCandidateGrid.getChildren().addAll(setPostComboBox, addCandidateName, inAddCandidateName, addCandidatePass, inAddCandidatePass, addCandidatePost, addCandidateSave, addCandidateBack, addCandidatetext);

        Scene addcandidateScene = new Scene(addCandidateGrid, 800, 800);
        addCandidate.setOnAction(e -> window.setScene(addcandidateScene));
        //add candidate page ends***************************************************
        //delete Candidate starts**************************************************

        //admin logged in
        //GridPane with 10px padding around edge
        GridPane deleteCandidateGrid = new GridPane();
        deleteCandidateGrid.setPadding(new Insets(10, 10, 10, 10));
        deleteCandidateGrid.setVgap(1);
        deleteCandidateGrid.setHgap(1);

        ///set background at the page where voter is logged in

        deleteCandidateGrid.setBackground(adminBackground);


        //delete candidate name label
        Label deleteCandidateID = new Label("ID : ");
        deleteCandidateID.setFont(Font.font("", 22));
        deleteCandidateID.setTextFill(Color.web("#3366cc"));
        GridPane.setConstraints(deleteCandidateID, 220, 270);


        //taking name input  text field
        TextField inDeleteCandidateID = new TextField();
        GridPane.setConstraints(inDeleteCandidateID, 230, 270);
        inDeleteCandidateID.setPrefHeight(40);
        inDeleteCandidateID.setPrefWidth(200);

        //save button
        Button deleteCandidateButton = new Button("Delete");
        deleteCandidateButton.setFont(Font.font("", 22));
        deleteCandidateButton.setTextFill(Color.web("#3366cc"));
        GridPane.setConstraints(deleteCandidateButton, 230, 300);

        //goback button

        Button backEditCandidateDetails = new Button("Back");
        backEditCandidateDetails.setFont(Font.font("", 22));
        backEditCandidateDetails.setTextFill(Color.web("#3366cc"));
        GridPane.setConstraints(backEditCandidateDetails, 0, 580);

        //add Text
        Text deleteCandidateText = new Text("INFORMATION OF CANDIDATE");
        deleteCandidateText.setFont(Font.font("", 20));
        deleteCandidateText.setFill(Color.BLACK);
        deleteCandidateText.setStroke(Color.BLANCHEDALMOND);
        GridPane.setConstraints(deleteCandidateText, 230, 0);

        deleteCandidateGrid.getChildren().addAll(inDeleteCandidateID, deleteCandidateID, deleteCandidateButton, backEditCandidateDetails, deleteCandidateText);
        Scene deleteCandidateScene = new Scene(deleteCandidateGrid, 800, 800);

        //delete candidate ends****************************************************
        deleteCandidate.setOnAction(e -> window.setScene(deleteCandidateScene));
        deleteCandidateButton.setOnAction(e-> {
            String id= inDeleteCandidateID.getText();
            boolean flag=adminClass.deleteCandidate(id);
            System.out.println(flag);
            inDeleteCandidateID.clear();
            if(flag)
                window.setScene(adminLoggedInScene);
            else{
                inDeleteCandidateID.setText("Invalid ID");
            }
        });

        backEditCandidateDetails.setOnAction(e -> window.setScene(adminLoggedInScene));

        //editVoterScene starts********************************************
        GridPane editVoter = new GridPane();
        editVoter.setPadding(new Insets(10, 10, 10, 10));
        editVoter.setVgap(1);
        editVoter.setHgap(1);

        editVoter.setBackground(adminBackground);

        //-----adding name
        Label editVoterName = new Label("ID:");
        editVoterName.setFont(Font.font("", BOLD, 22));
        editVoterName.setTextFill(Color.web("#161515"));
        GridPane.setConstraints(editVoterName, 220, 270);

        //taking name input  text field
        TextField inEditVoterId = new TextField();
        GridPane.setConstraints(inEditVoterId, 230, 270);
        inEditVoterId.setPrefHeight(40);
        inEditVoterId.setPrefWidth(200);

        //password button
        Label editVoterPass = new Label("Set Name:");
        editVoterPass.setFont(Font.font("", BOLD, 22));
        editVoterPass.setTextFill(Color.web("#161515"));
        GridPane.setConstraints(editVoterPass, 220, 290);

        //taking input  voter password
        TextField inEditVoterName = new TextField();
        GridPane.setConstraints(inEditVoterName, 230, 290);
        inEditVoterName.setPrefHeight(40);
        inEditVoterName.setPrefWidth(200);

        //save button
        Button saveEditVoter = new Button("Save");
        saveEditVoter.setFont(Font.font("", 22));
        saveEditVoter.setTextFill(Color.web("#3366cc"));
        GridPane.setConstraints(saveEditVoter, 230, 300);

        //goback button
        Button editVoterBack = new Button("Back");
        editVoterBack.setFont(Font.font("", 22));
        editVoterBack.setTextFill(Color.web("#3366cc"));
        GridPane.setConstraints(editVoterBack, 230, 310);

        //add Text
        Text editVoterText = new Text("EDIT VOTER ");
        editVoterText.setFont(Font.font("", 20));
        editVoterText.setFill(Color.BLACK);

        GridPane.setConstraints(editVoterText, 300, 0);

        editVoter.getChildren().addAll(inEditVoterName, inEditVoterId, editVoterName, editVoterPass, saveEditVoter, editVoterBack, editVoterText);
        Scene editVoterScene = new Scene(editVoter, 800, 800);

        editVoterBack.setOnAction(e -> window.setScene(editVoterDetailsScene));

        saveEditVoter.setOnAction(e->{
            boolean flag=false;
            String id=inEditVoterId.getText();
            String name=inEditVoterName.getText();
            try{
                Integer.parseInt(id);
                flag=adminClass.editVoter(id,name);
            }catch(Exception ex){
                inEditVoterId.setText("Enter velid ID no.");
            }
            if(flag)
                window.setScene(editVoterDetailsScene);
        });

        edit.setOnAction(e -> window.setScene(editVoterScene));
        //editVoterScene ends***********************************************

        //editCandidate SceneStarts******************************************
        //GridPane with 10px padding around edge
        GridPane editCandidateGrid = new GridPane();
        editCandidateGrid.setPadding(new Insets(10, 10, 10, 10));
        editCandidateGrid.setVgap(1);
        editCandidateGrid.setHgap(1);

        editCandidateGrid.setBackground(adminBackground);


        //setting buttons


        //-----adding name


        Label editCandidateId = new Label(" ID:");
        editCandidateId.setFont(Font.font("", BOLD, 22));
        editCandidateId.setTextFill(Color.web("#3366cc"));
        GridPane.setConstraints(editCandidateId, 220, 270);

        //taking name input  text field
        TextField inEditCandidateId = new TextField();
        GridPane.setConstraints(inEditCandidateId, 250, 270);
        inEditCandidateId.setPrefHeight(40);
        inEditCandidateId.setPrefWidth(200);

        //password button
        Label editCandidateName = new Label("Name:");
        editCandidateName.setFont(Font.font("", BOLD, 22));
        editCandidateName.setTextFill(Color.web("#3366cc"));
        GridPane.setConstraints(editCandidateName, 220, 290);

        //taking input  voter password
        TextField inEditCandidateName = new TextField();
        GridPane.setConstraints(inEditCandidateName, 250, 290);
        inEditCandidateName.setPrefHeight(40);
        inEditCandidateName.setPrefWidth(200);
        //save button
        Button saveEditcandidate = new Button("Save");
        saveEditcandidate.setFont(Font.font("", 22));
        saveEditcandidate.setTextFill(Color.web("#3366cc"));

        GridPane.setConstraints(saveEditcandidate, 250, 300);

        //goback button

        Button editCandidateBack = new Button("Back");
        editCandidateBack.setFont(Font.font("", 22));
        editCandidateBack.setTextFill(Color.web("#3366cc"));
        GridPane.setConstraints(editCandidateBack, 250, 310);

        //add Text
        Text editCandidateText = new Text("EDIT CANDIDATE");
        editCandidateText.setFont(Font.font("", 20));
        editCandidateText.setFill(Color.BLACK);

        GridPane.setConstraints(editCandidateText, 300, 0);

        editCandidateGrid.getChildren().addAll(inEditCandidateId, inEditCandidateName, editCandidateId, editCandidateName, saveEditcandidate, editCandidateBack, editCandidateText);
        Scene editCandidateScene = new Scene(editCandidateGrid, 800, 800);
        editCandidateBack.setOnAction(e -> window.setScene(editCandidateDetailsScene));
        saveEditcandidate.setOnAction(e->{
            String id=inEditCandidateId.getText();
            String name=inEditCandidateName.getText();
            boolean flag=false;
            try{
                Integer.parseInt(id);
                flag=adminClass.editCandidate(id,name);
            }catch(Exception ex){
                inEditCandidateId.setText("Enter valid Id no.");
                inEditCandidateName.clear();
                flag=false;
            }
            if(flag) {
                inEditCandidateId.clear();
                inEditCandidateName.clear();
                window.setScene(editCandidateDetailsScene);
            }
            else {
                inEditCandidateId.setText("Enter valid Id no.");
                inEditCandidateName.clear();
            }
        });
        editCandidateButton.setOnAction(e -> window.setScene(editCandidateScene));
        //editCandidate Scene ends*******************************************

        // view candidate details page starts**************************************
        //  candidate name label
        Label viewCandidateName = new Label("Name : " + name);
        viewCandidateName.setFont(Font.font("", BOLD, 30));
        GridPane.setConstraints(viewCandidateName, 80, 90);

        /*/candidate name Input
        TextField viewCandidateNameInput = new TextField();
        GridPane.setConstraints(viewCandidateNameInput, 90, 90);

         */


        //candidate id Label
        Label viewCandidateId = new Label("ID :" + id);
        viewCandidateId.setFont(Font.font("", BOLD, 30));
        GridPane.setConstraints(viewCandidateId, 80, 100);

        //confirm  Password Label
        Label viewCandidatePost = new Label("Post :" + post);
        viewCandidatePost.setFont(Font.font("", BOLD, 30));
        GridPane.setConstraints(viewCandidatePost, 80, 110);

        //Back button
        Button viewCandidateBack = new Button("Back");
        viewCandidateBack.setFont(Font.font("", BOLD, 22));
        viewCandidateBack.setTextFill(Color.web("#3366cc"));
        GridPane.setConstraints(viewCandidateBack, 80, 120);

        GridPane viewCandidateGrid = new GridPane();
        viewCandidateGrid.setPadding(new Insets(10, 10, 10, 10));
        viewCandidateGrid.setVgap(1);
        viewCandidateGrid.setHgap(1);

        viewCandidateGrid.setBackground(candidateLoginbackground);
        viewCandidateGrid.getChildren().addAll(viewCandidateName, viewCandidateId, viewCandidatePost, viewCandidateBack);
        viewCandidateBack.setOnAction(e -> window.setScene(candidateLoggedInScene));
        Scene viewCandidateDetailsScene = new Scene(viewCandidateGrid, 800, 800);
        viewCandidateDetailButton.setOnAction(e-> {
            Candidate temp=candidate.viewDetails();
            System.out.println(temp);
            name=temp.getName();
            id= String.valueOf(temp.getId());
            post=temp.post;
            viewCandidateName.setText("Name : "+temp.getName());
            viewCandidateId.setText("Ballot No : "+temp.getId());
            viewCandidatePost.setText("Post : "+temp.post);
            window.setScene(viewCandidateDetailsScene);
        });

        //voter details scene starts8********************************************************************************
        // voter name label
        Label viewVoterName = new Label("Name: ");
        viewVoterName.setFont(Font.font("", BOLD, 30));
        GridPane.setConstraints(viewVoterName, 80, 90);

        //candidate name Input
        TextField viewVoterNameInput = new TextField();
        GridPane.setConstraints(viewVoterNameInput, 90, 90);


        //candidate id Label
        Label viewVoterId = new Label("ID :");
        viewVoterId.setFont(Font.font("", BOLD, 30));
        GridPane.setConstraints(viewVoterId, 80, 100);

        //New ID Input
        TextField viewVoterIdInput = new TextField();
        GridPane.setConstraints(viewVoterIdInput, 90, 100);


        //back buttton

        Button viewVoterBack = new Button("Back");
        viewVoterBack.setFont(Font.font("", BOLD, 22));
        viewVoterBack.setTextFill(Color.web("#3366cc"));
        GridPane.setConstraints(viewVoterBack, 90, 110);

        GridPane viewVoterGrid = new GridPane();
        viewVoterGrid.setPadding(new Insets(10, 10, 10, 10));
        viewVoterGrid.setVgap(1);
        viewVoterGrid.setHgap(1);

        viewVoterGrid.setBackground(voterLoginBackground);
        viewVoterGrid.getChildren().addAll(viewVoterBack, viewVoterId, viewVoterName);
        viewVoterBack.setOnAction(e -> window.setScene(voterLoggedinScene));
        Scene viewVoterDetailsScene = new Scene(viewVoterGrid, 800, 800);
        voterViewDetailsButton.setOnAction(e -> {
            Voter temp = null;
            temp=voter.showDetails();
            viewVoterName.setText("Name : " + temp.getName());
            viewVoterId.setText("Id : " + temp.getId());
            window.setScene(viewVoterDetailsScene);
        });
        //voter details scene ends************************************************************************************
        //voter details showing page starts***********************************************************************************

        TableColumn<Voter, String> voterListNameColumn = new TableColumn<>("Name");
        voterListNameColumn.setMinWidth(400);
        voterListNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        //ballotNo column
        TableColumn<Voter, String> voterListIdColumn = new TableColumn<>("ID");
        voterListIdColumn.setMinWidth(400);
        voterListIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));


        voterListTable = new TableView<>();
        voterListTable.getColumns().addAll(voterListIdColumn, voterListNameColumn);

        VBox voterListVBox = new VBox();
        voterListVBox.getChildren().addAll(voterListTable);

        GridPane voterListGrid = new GridPane();
        voterListGrid.setPadding(new Insets(10, 10, 10, 10));
        voterListGrid.setVgap(1);
        voterListGrid.setHgap(1);
        GridPane.setConstraints(voterListVBox,0,30);

        Button BackbuttonVoterList = new Button("Back");
        GridPane.setConstraints(BackbuttonVoterList,0,300);

        voterListGrid.getChildren().addAll(voterListVBox,BackbuttonVoterList);
        Scene voterListScene = new Scene(voterListGrid, 800, 800);
        //voter details page ends*************************************************************************************

        //candidate details page starts*****************************************************************************
        GridPane candidateListGrid = new GridPane();
        candidateListGrid.setPadding(new Insets(10, 10, 10, 10));
        candidateListGrid.setVgap(1);
        candidateListGrid.setHgap(1);

        TableColumn<Candidate, String> candidateListNameColumn = new TableColumn<>("Name");
        candidateListNameColumn.setMinWidth(300);
        candidateListNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        //post column
        TableColumn<Candidate, String> candidatePostColumn = new TableColumn<>("Post");
        candidatePostColumn.setMinWidth(250);
        candidatePostColumn.setCellValueFactory(new PropertyValueFactory<>("post"));

        //ballotNo column
        TableColumn<Candidate, String> candidateBallotNoColumn = new TableColumn<>("BallotNo");
        candidateBallotNoColumn.setMinWidth(250);
        candidateBallotNoColumn.setCellValueFactory(new PropertyValueFactory<>("ballotNo"));



        candidateListTable = new TableView<>();
        candidateListTable.setItems(candidateTableElement());
        candidateListTable.getColumns().addAll(candidateBallotNoColumn,candidateListNameColumn,candidatePostColumn);
        candidateListTable.prefHeight(600);

        VBox candidateListVBox = new VBox();
        candidateListVBox.getChildren().addAll(candidateListTable);

        Button backbuttonViewCandidate = new Button("Back");
        GridPane.setConstraints(backEditCandidateDetails,0,0);

        GridPane.setConstraints(candidateListVBox,0,0);
        candidateListGrid.getChildren().addAll(candidateListVBox,backbuttonViewCandidate);

        Scene candidateListScene = new Scene(candidateListGrid,800,800);

        //candidate details page ed8*********************************************************************************
        viewVoterDetails.setOnAction(e-> {
            voterListArray=adminClass.viewVoterList();
            voterListTable.setItems(voterTableEliment());
            for(int i=0;i<voterListArray.size();i++){
                System.out.println(voterListArray.get(i).getName());
            }
            window.setScene(voterListScene);
        });
        BackbuttonVoterList.setOnAction(e-> window.setScene(adminLoggedInScene));
        viewCandidateDetails.setOnAction(e-> {
            candidateArray=adminClass.candidateList();
            candidateListTable.setItems(candidateTableElement());
            window.setScene(candidateListScene);
        });
        backbuttonViewCandidate.setOnAction(e-> window.setScene(adminLoggedInScene));


        //give vote page starts************************
        GridPane giveVoteGrid = new GridPane();
        giveVoteGrid.setPadding(new Insets(10, 10, 10, 10));
        giveVoteGrid.setVgap(1);
        giveVoteGrid.setHgap(1);
        comboBoxPresident = new ComboBox<>();

        comboBoxVicePresident = new ComboBox<>();

        comboBoxPresident.setPromptText("President");
        comboBoxPresident.setPrefHeight(50);
        comboBoxPresident.setPrefWidth(200);


        comboBoxVicePresident.setPromptText("Vice President");
        comboBoxVicePresident.setPrefHeight(50);
        comboBoxVicePresident.setPrefWidth(200);

        Button giveVote = new Button("Submit");
        giveVote.setFont(Font.font("", BOLD, 22));
        GridPane.setConstraints(giveVote,150,330);

        GridPane.setConstraints(comboBoxPresident,130,270);
        GridPane.setConstraints(comboBoxVicePresident,180,270);

        giveVoteGrid.setBackground(voterLoginBackground);


        giveVoteGrid.getChildren().addAll(comboBoxPresident,comboBoxVicePresident,giveVote);
        Scene  giveVoteScene = new Scene(giveVoteGrid, 800, 800);
        giveVoteButton.setOnAction(e-> {
            boolean flag=voter.validVoter();
            int voteTime=voter.checkVoteTime();
            if(voteTime==2){
                window.setScene(voteStoppedScene);
            }
            else if(voteTime==0)
                window.setScene(voteNotStartedScene);
            else if(flag) {
                ArrayList<Candidate> candidateObject = new ArrayList<Candidate>();
                candidateObject = voter.candidateList();
                for (int i = 0; i < candidateObject.size(); i++) {
                    if (candidateObject.get(i).getPost().equals("President")) {
                        comboBoxPresident.getItems().add(candidateObject.get(i).getBallotNo() + " " + candidateObject.get(i).getName());
                    }
                    if (candidateObject.get(i).getPost().equals("Vice President")) {
                        comboBoxVicePresident.getItems().add(candidateObject.get(i).getBallotNo() + " " + candidateObject.get(i).getName());
                    }
                }
                window.setScene(giveVoteScene);
            }
            else System.out.println("Vote already given");
                });

        giveVote.setOnAction(e-> {
                String vicePresident = comboBoxVicePresident.getValue();
                String president = comboBoxPresident.getValue();
                int presidentId = Integer.parseInt(String.valueOf(president.charAt(0)));
                int vicePresidentId = Integer.parseInt(String.valueOf(vicePresident.charAt(0)));
                voter.giveVote(presidentId, vicePresidentId);
                window.setScene(voterLoggedinScene);

        });
        voteStoppedBackButton.setOnAction(e->{
            if(client==2)
                window.setScene(voterLoggedinScene);
        });

        voteNotStartedBackButton.setOnAction(e->{
            if(client==1)
                window.setScene(adminLoggedInScene);
            else if(client==2)
                window.setScene(voterLoggedinScene);
        });
        //give vote ends((((((((((((((((((((((((((((((

        //result page starts************************

        GridPane resultGrid = new GridPane();
        resultGrid.setPadding(new Insets(10, 10, 10, 10));
        resultGrid.setVgap(1);
        resultGrid.setHgap(1);

        //Name column
        TableColumn<Result, String> nameColumnCandidate = new TableColumn<>("Name");
        nameColumnCandidate.setMinWidth(200);
        nameColumnCandidate.setCellValueFactory(new PropertyValueFactory<>("name"));

        //ballotNo column
        TableColumn<Result, String> idColumnCandidate = new TableColumn<>("BallotNo");
        idColumnCandidate.setMinWidth(200);
        idColumnCandidate.setCellValueFactory(new PropertyValueFactory<>("ballotNo"));

        //post column
        TableColumn<Result, String> postColumnCandidate = new TableColumn<>("Post");
        postColumnCandidate.setMinWidth(200);
        postColumnCandidate.setCellValueFactory(new PropertyValueFactory<>("post"));

        //count vote column
        TableColumn<Result, String> countedVoteColumnCandidate = new TableColumn<>("Vote count");
        countedVoteColumnCandidate.setMinWidth(200);
        countedVoteColumnCandidate.setCellValueFactory(new PropertyValueFactory<>("voteCount"));



        resultTable = new TableView<>();
        resultTable.setItems(resultEliments());
        resultTable.getColumns().addAll(nameColumnCandidate, idColumnCandidate,postColumnCandidate,countedVoteColumnCandidate);

        VBox resultVBox = new VBox();
        resultVBox.getChildren().addAll(resultTable);

        Button backFromResult = new Button("Back");
        GridPane.setConstraints(resultVBox,0,0);
        GridPane.setConstraints(backFromResult,0,300);
        resultGrid.getChildren().addAll(backFromResult,resultVBox);


        Scene resultScene = new Scene(resultGrid,800,800);
        backFromResult.setOnAction(e-> {
            if(client==2)
                window.setScene(voterLoggedinScene);
            else if(client==1){
                window.setScene(adminLoggedInScene);
            }
        });
        seeVoteResult.setOnAction(e->{
                int flag=adminClass.checkVoteTime();
                if(flag==2) {
                    resultList = adminClass.finalResult();
                    resultTable.setItems(resultEliments());
                    window.setScene(resultScene);
                }
                else if(flag==1){
                    System.out.println("Vote is running");
                    window.setScene(voteRunningScene);
                }
                else {
                    window.setScene(voteNotStartedScene);
                }
        });
        seeResultButtonVoter.setOnAction(e->{
            int flag=voter.checkVoteTime();
            if(flag==2) {
                resultList = voter.finalResult();
                resultTable.setItems(resultEliments());
                window.setScene(resultScene);
            }
            else if(flag==1){
                System.out.println("Vote is running");
                window.setScene(voteRunningScene);
            }
            else {
                window.setScene(voteNotStartedScene);
            }
        });
        voteRunningBackButton.setOnAction(e->{
            if(client==1)
                window.setScene(adminLoggedInScene);
            else if(client==2)
                window.setScene(voterLoggedinScene);
        });
        window.setScene(homeScene);

        window.show();
    }

    public ObservableList<Voter> voterTableEliment(){
        ObservableList<Voter> voters = FXCollections.observableArrayList();
        Voter object;
        Iterator<Voter> it = voterListArray.iterator();
        for (int i = 0; i < voterListArray.size(); i++) {
            object = it.next();
            voters.add(new Voter(object.getName(), object.getId()));
        }

        return voters;

    }
    public ObservableList<Candidate> candidateTableElement(){
        ObservableList<Candidate> candidates = FXCollections.observableArrayList();
        for(int i=0;i<candidateArray.size();i++){
            candidates.add(new Candidate(candidateArray.get(i).getName() ,candidateArray.get(i).getBallotNo(),candidateArray.get(i).post));
        }

        return candidates;
    }

    public ObservableList<Result> resultEliments() {
        ObservableList<Result> finalResult = FXCollections.observableArrayList();
        for(int i=0;i<resultList.size();i++){
            finalResult.add(new Result(resultList.get(i).getName(),resultList.get(i).getBallotNo(),resultList.get(i).getPost(),resultList.get(i).getVoteCount()));
        }

        return finalResult;
    }

}