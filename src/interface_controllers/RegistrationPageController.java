package interface_controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.util.Callback;
import staff.Group;
import staff.Registration;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import static interface_controllers.StartPoint.loadNewStage;

public class RegistrationPageController {

    @FXML
    private Label categoryNameLabel;

    @FXML
    private Label groupNameLabel;

    @FXML
    private DatePicker activityDatePicker;

    @FXML
    private Label priceLabel;

    private Registration currentRegistration;

    @FXML
    void selectLessonDate(ActionEvent event) {
        currentRegistration = new Registration(activityDatePicker.getValue(), GroupsInfoController.selectedGroup);
    }

    @FXML
    void selectOneMoreLesson(ActionEvent event) {
        StartPoint.client.registerNewActivity(currentRegistration);

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
    void signUpForLesson(ActionEvent event) {
        StartPoint.client.registerNewActivity(currentRegistration);
        currentRegistration.getGroup().registerNewChild();
        //TODO: удалять свободную дату из таковых объекта группы

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
        groupNameLabel.setText("Вы хотите записаться в группу " + GroupsInfoController.selectedGroup);
        categoryNameLabel.setText("На направление \"" + GroupsInfoController.selectedCategory.name() + "\"");

        createCustomDatePicker(activityDatePicker, GroupsInfoController.selectedGroup.getActivityDates());

        priceLabel.setText("Стоимость посещения данного факультатива: " + GroupsInfoController.selectedGroup.getPriceOfSingleActivity()+ " денег (1 занятие)");
    }

    private void createCustomDatePicker(DatePicker datePicker, List<LocalDate> availableDates) {
        datePicker.setDayCellFactory(new Callback<DatePicker, DateCell>() {
            @Override
            public DateCell call(DatePicker param) {
                return new DateCell() {
                    @Override
                    public void updateItem(LocalDate item, boolean empty) {
                        super.updateItem(item, empty);

                        if (empty || item == null) {
                            setDisable(true);
                            setStyle("-fx-background-color: #d3d3d3;"); // Серый цвет для пустых ячеек
                        } else {
                            // Отключение недоступных дат и установка цвета для доступных
                            setDisable(!availableDates.contains(item));
                            if (availableDates.contains(item)) {
                                setStyle("-fx-background-color: #90ee90;"); // Светло-зеленый цвет для доступных дат
                            }
                        }
                    }
                };
            }
        });
    }
}
