package lk.ijse.controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.util.Duration;
import lk.ijse.bo.custom.BOFactory;
import lk.ijse.bo.custom.ProgramBO;
import lk.ijse.bo.custom.RegistrationBO;
import lk.ijse.bo.custom.StudentBO;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DashboardFormController {

    @FXML
    private AnchorPane centerNode;

    @FXML
    private AnchorPane countSection;

    @FXML
    private Text count1;

    @FXML
    private Text count2;

    @FXML
    private Text count3;

    @FXML
    private Button courseBtn;

    @FXML
    private Button dashboardBtn;

    @FXML
    private AnchorPane dashboardForm;

    @FXML
    private AnchorPane graphSection;

    @FXML
    private Button paymentBtn;

    @FXML
    private AnchorPane sidebar;

    @FXML
    private Button studentBtn;

    @FXML
    private TextField txtUserType;

    @FXML
    private TextField txtUsername;

    @FXML
    private Button userBtn;

    @FXML
    private Button registrationBtn;

    @FXML
    private Pane datePane;

    @FXML
    private Pane timePane;

    @FXML
    private Label lblAdmin;

    @FXML
    private Label lblCourseCount;

    @FXML
    private Label lblDate;

    @FXML
    private Label lblRegisterCount;

    @FXML
    private Label lblStudentCount;

    @FXML
    private Label lblTime;

    @FXML
    private Button logoutBtn;

    private int studentCount;
    private int courseCount;
    private int registerCount;

    StudentBO studentBO = (StudentBO) BOFactory.getBoFactory().getBOTypes(BOFactory.BOTypes.STUDENT);
    ProgramBO programBO = (ProgramBO) BOFactory.getBoFactory().getBOTypes(BOFactory.BOTypes.PROGRAM);
    RegistrationBO registrationBO = (RegistrationBO) BOFactory.getBoFactory().getBOTypes(BOFactory.BOTypes.REGISTRATION);

    public void initialize(){
        getCount();
        setDate();
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(1), event -> updateClock())
        );
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
        updateClock();

        lblAdmin.setText(LoginFormController.loggedUsername);
    }

    private void getCount(){
        try {

            studentCount =studentBO.getStudentCount();
            courseCount = programBO.getProgramCount();
            registerCount = registrationBO.getRegisterCount();

        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage());
        }
        setCount(studentCount,courseCount,registerCount);

    }
    private void setCount(int studentCount,int courseCount,int registerCount) {

        lblStudentCount.setText(String.valueOf(studentCount));
        lblCourseCount.setText(String.valueOf(courseCount));
        lblRegisterCount.setText(String.valueOf(registerCount));

    }

    private void updateClock() {
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        String formattedTime = dateFormat.format(now);
        lblTime.setText(formattedTime);
        lblTime.setFont(Font.font("Arial", FontWeight.BOLD, 18));
    }
    private void setDate(){
        lblDate.setText("" + java.time.LocalDate.now());
        lblDate.setFont(Font.font("Arial", FontWeight.BOLD, 18));
    }
    @FXML
    void BtnCourseOnAction(ActionEvent event) throws IOException {
        try {
            AnchorPane coursesPane = FXMLLoader.load(getClass().getResource("/view/courses_form.fxml"));
            centerNode.getChildren().clear();
            centerNode.getChildren().add(coursesPane);
        } catch (IOException e) {
            e.printStackTrace();
            // Log or handle the IOException
        }
        if (courseBtn.isDisable()){
            return;
        }
    }


    public void disableButtons(String userType){

        if ("Admission coordinator".equalsIgnoreCase(userType)){
            courseBtn.setDisable(true);
            paymentBtn.setDisable(true);

        }
    }
    @FXML
    void BtnPaymentOnAction(ActionEvent event) throws IOException {
        AnchorPane paymentPane = FXMLLoader.load(this.getClass().getResource("/view/payment_form.fxml"));
        centerNode.getChildren().clear();
        centerNode.getChildren().add(paymentPane);

        if (paymentBtn.isDisable()){
            return;
        }
    }

    @FXML
    void btnDashboardOnAction(ActionEvent event) throws IOException {
        navigateToTheDashboard();
    }

    @FXML
    void btnStudentOAction(ActionEvent event) throws IOException {
        AnchorPane studentPane = FXMLLoader.load(this.getClass().getResource("/view/student_form.fxml"));
        centerNode.getChildren().clear();
        centerNode.getChildren().add(studentPane);
    }

    @FXML
    void btnUserOnAction(ActionEvent event) throws IOException {
        try {
            AnchorPane userPane = FXMLLoader.load(getClass().getResource("/view/user_form.fxml"));
            centerNode.getChildren().clear();
            centerNode.getChildren().add(userPane);
        } catch (IOException e) {
            e.printStackTrace();
            // Log or handle the IOException
        }
    }

    @FXML
    void registrationBtnOnAction(ActionEvent event) throws IOException {
        AnchorPane registrationPane = FXMLLoader.load(this.getClass().getResource("/view/registration_form.fxml"));
        centerNode.getChildren().clear();
        centerNode.getChildren().add(registrationPane);
    }

    @FXML
    void btnLogoutOnAction(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/view/login_form.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.show();
        dashboardForm.getScene().getWindow().hide();
    }
    public  void navigateToTheDashboard() throws IOException {
        AnchorPane rootNode = FXMLLoader.load(this.getClass().getResource("/view/dashboard_form.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage =(Stage) this.dashboardForm.getScene().getWindow();
        stage.setScene(scene);

        stage.setTitle("Dashboard Form");
        stage.centerOnScreen();
    }
    @FXML
    void studentViewBtnOnAction(ActionEvent event) {

    }
}


