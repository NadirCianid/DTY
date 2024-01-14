package interface_controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

public class PostFinalPageController {

    @FXML
    private ListView<String> registeredActivitiesLV;

    public void init() {
        ObservableList<String> registeredActivitiesList = FXCollections.observableArrayList();
        StartPoint.client.getRegistrationList().forEach(registration -> registeredActivitiesList.add(registration.toString()));

        registeredActivitiesLV.getItems().addAll(registeredActivitiesList);
    }

}
