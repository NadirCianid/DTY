package interface_controllers;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class StartPoint extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        //Получение FXMLLoader объекта  для перехода к новой сцене и доступа к котроллеру этой сцены
        FXMLLoader fxmlLoader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("..//fxmls//groupsInfo.fxml")));

        prepareNewStage(stage, fxmlLoader);

        GroupsInfoController groupsInfoController = fxmlLoader.getController();
        groupsInfoController.init();
    }

    private static void prepareNewStage(Stage stage, FXMLLoader fxmlLoader) throws IOException {
        Parent root = fxmlLoader.load();
        stage.setScene(new Scene(root));
        stage.show();
    }

    public static void loadNewStage(ActionEvent event, FXMLLoader fxmlLoader) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        prepareNewStage(stage, fxmlLoader);
    }
}