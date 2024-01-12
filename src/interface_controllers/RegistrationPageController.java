package interface_controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;

import java.io.IOException;
import java.util.Objects;

import static interface_controllers.StartPoint.loadNewStage;

public class SignUpPageController {

    @FXML
    private Label categoryNameLabel;

    @FXML
    private Label groupNameLabel;

    @FXML
    private DatePicker lessonDatePicker;

    @FXML
    private Label priceLabel;

    @FXML
    void selectLessonDate(ActionEvent event) {

    }

    @FXML
    void selectOneMoreLesson(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("..//fxmls//signUpPage.fxml")));

        try {
            loadNewStage(event, fxmlLoader);
        } catch (IOException e) {
            System.out.println("Ошибка загрузки FXMLLoader");
        }

        SignUpPageController signUpPageController = fxmlLoader.getController();
        signUpPageController.init();
    }

    @FXML
    void signUpForLesson(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("..//fxmls//finalPage.fxml")));

        try {
            loadNewStage(event, fxmlLoader);
        } catch (IOException e) {
            System.out.println("Ошибка загрузки FXMLLoader");
        }
        
        FinalPageController finalPageController = fxmlLoader.getController();
        finalPageController.init();
    }

    public void init() {
    }
}
