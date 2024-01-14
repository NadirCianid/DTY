package interface_controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import staff.Client;
import staff.FacultyCategory;
import staff.Group;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;

import static interface_controllers.StartPoint.loadNewStage;
import static interface_controllers.StartPoint.openSecondWindow;

public class GroupsInfoController {

    @FXML
    private DatePicker birthDatePicker;

    @FXML
    private ComboBox<FacultyCategory> facultyCategoryCB;

    @FXML
    private ComboBox<Group> groupCB;

    @FXML
    private Button nextPageButton;

    @FXML
    private Button selectionButton;

    public static FacultyCategory selectedCategory;
    public static Group selectedGroup;
    private int clientAgeInYears;

    @FXML
    void enterBirthday(ActionEvent event) {
        LocalDate birthDate = birthDatePicker.getValue();

        LocalDate currentDate = LocalDate.now();
        Period age = Period.between(birthDate, currentDate);
        clientAgeInYears = age.getYears();

        // Минимальный допустимый возраст - 6 лет
        int minimumAge = 6;

        if(clientAgeInYears < minimumAge) {
            openSecondWindow("Минимальный возраст для вступления в любую группу - 6 лет.", "Ошибка ввода даты рождения");
            return;
        }

        StartPoint.client = new Client(birthDate);
    }

    @FXML
    void goNextPage(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("..//fxmls//registrationPage.fxml")));

        try {
            loadNewStage(event, fxmlLoader);
        } catch (IOException e) {
            System.out.println("Ошибка загрузки FXMLLoader");
        }

        RegistrationPageController registrationPageController = fxmlLoader.getController();
        registrationPageController.init();
    }

    @FXML
    void selectCategory(ActionEvent event) {
        groupCB.getItems().clear();

        selectedCategory = facultyCategoryCB.getValue();

        ObservableList<Group> groupObservableList = FXCollections.observableArrayList();

        groupObservableList.addAll(Group.selectGroupsByCategory(selectedCategory));

        if(groupObservableList.size() == 0) {
            openSecondWindow("Для данной категории пока нет свободных групп", "Нет групп");
            return;
        }

        groupCB.setItems(groupObservableList);
    }

    @FXML
    void selectGroup(ActionEvent event) {
        selectedGroup = groupCB.getValue();
    }

    @FXML
    void selectParameter(ActionEvent event) {
        if(selectedGroup.getCapacity() - (selectedGroup.getRegisteredAmount() + 1) < 0) {
            openSecondWindow("Вы не можете записаться в эту группу, \nтак как свободные места закончились.", "Ошибка выбора группы");
            return;
        }

        if(selectedGroup.getMinAge() > clientAgeInYears) {
            openSecondWindow("Вы не подходите для данной группы по минимальному возрасту. \n" +
                    "Минимальный возраст для данной группы - " + selectedGroup.getMinAge() + ".", "Ошибка выбора группы");
            return;
        }

        nextPageButton.setDisable(false);
    }

    public void init() {
        nextPageButton.setDisable(true);

        ObservableList<FacultyCategory> facultyCategories = FXCollections.observableArrayList();
        facultyCategories.addAll(FacultyCategory.values());

        facultyCategoryCB.setItems(facultyCategories);

    }
}
