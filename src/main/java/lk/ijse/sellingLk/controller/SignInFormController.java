package lk.ijse.sellingLk.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import lk.ijse.sellingLk.bo.BOFactory;
import lk.ijse.sellingLk.bo.custom.SignInBO;
import lk.ijse.sellingLk.util.Navigation;

import java.sql.SQLException;

public class SignInFormController {
    @FXML
    private Pane usernamePane;

    @FXML
    private JFXTextField txtUsername;

    @FXML
    private Pane passwordPane;

    @FXML
    private JFXPasswordField txtPassword;

    @FXML
    private ImageView imgEye;
    @FXML
    private Label lblwarning;
    @FXML
    private JFXTextField txtUnHidePassword;
    @FXML
    private JFXButton btnSignIn;
    private boolean hide = true;

    public static String uname;
    public static String pword;

    private SignInBO signInBO = (SignInBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.SIGNIN);

    @FXML
    void btnSendOtpOnAction(ActionEvent event) {

    }

    @FXML
    void btnSignInOnAction(ActionEvent event) {
        String username = txtUsername.getText();
        String password=null;
        if(txtPassword.isVisible()) password = txtPassword.getText();
        else password = txtUnHidePassword.getText();



        try {
            boolean isIn = signInBO.searchUser(username, password);
            if (isIn){
                try {
                    this.uname=username;
                    this.pword=username;

                    LoginFormController.t.stop(); //to stop thread using carousal

                    Navigation.switchNavigation("dashboard-form.fxml",event);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }else{
                usernamePane.setStyle("-fx-border-color: #FA5252");
                passwordPane.setStyle("-fx-border-color: #FA5252");
                lblwarning.setVisible(true);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void imgPasswordEye(MouseEvent event) {
        if (hide) {
            imgEye.setImage(new Image("/assets/icons/openEye.png"));
            txtUnHidePassword.setText(txtPassword.getText());
            txtPassword.setVisible(false);
            txtUnHidePassword.setVisible(true);
            hide = false;
            return;
        }
        else{
            imgEye.setImage(new Image("/assets/icons/closedEye.png"));
            txtPassword.setText(txtUnHidePassword.getText());
            txtUnHidePassword.setVisible(false);
            txtPassword.setVisible(true);
            hide = true;
            return;
        }
    }

}
