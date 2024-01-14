package interface_controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import staff.Registration;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static interface_controllers.StartPoint.loadNewStage;
import static interface_controllers.StartPoint.openSecondWindow;

public class FinalPageController {

    @FXML
    private TextField childFioTA;

    @FXML
    private TextField childPhoneNumberTA;

    @FXML
    private Label lessonsAmountLabel;

    @FXML
    private Label lessonsCostLabel;

    @FXML
    private TextField parentFioTA;

    @FXML
    private TextField parentPhoneNumberTA;

    @FXML
    void signUp(ActionEvent event) {
        if(isntFioValid(parentFioTA.getText()) || isntFioValid(childFioTA.getText())) {
            openSecondWindow("Введите корректное фио (Например: Иванов Иван Иванович).", "Ошибка ввода фио");
            return;
        }

        if(isntPhoneNumberValid(parentPhoneNumberTA.getText()) || isntPhoneNumberValid(childPhoneNumberTA.getText())) {
            openSecondWindow("Введите корректный номер телефона.", "Ошибка ввода номера телефона");
            return;
        }

        FXMLLoader fxmlLoader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("..//fxmls//postFinalPage.fxml")));

        try {
            loadNewStage(event, fxmlLoader);
        } catch (IOException e) {
            System.out.println("Ошибка загрузки FXMLLoader");
        }

        StartPoint.client.setParentFio(parentFioTA.getText());
        StartPoint.client.setChildFio(childFioTA.getText());
        StartPoint.client.setParentPhoneNumber(parentPhoneNumberTA.getText());
        StartPoint.client.setChildPhoneNumber(childPhoneNumberTA.getText());

        PostFinalPageController postFinalPageController = fxmlLoader.getController();
        postFinalPageController.init();
    }

    // Метод для проверки ФИО
    public static boolean isntFioValid(String fullName) {
        // Регулярное выражение для ФИО на русском языке
        String regex = "^[А-ЯЁ][а-яё]+(\\s[А-ЯЁ][а-яё]+){1,2}$";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(fullName);

        return !matcher.matches();
    }

    // Метод для проверки номера телефона
    public static boolean isntPhoneNumberValid(String phoneNumber) {
        // Регулярное выражение для номера телефона: 10 цифр, первая - 7
        String regex = "^7\\d{9}$";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(phoneNumber);

        return !matcher.matches();
    }

    public void init() {
        List<Registration> clientsRegistrations = StartPoint.client.getRegistrationList();
        int totalCost = 0;
        for (Registration registration: clientsRegistrations) {
            totalCost += registration.getGroup().getPriceOfSingleActivity();
        }

        lessonsAmountLabel.setText("Вы хотите записаться на " + clientsRegistrations.size() + " занятий");
        lessonsCostLabel.setText("Общая стоимость - " + totalCost  + " денег");
    }
}
