package org.prodigy_sd_01;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Controller {
    @FXML
    public Button exitButton;
    @FXML
    private TextField inputTemperature;
    @FXML
    private ComboBox<String> inputUnit;
    @FXML
    private Label celsiusLabel;
    @FXML
    private Label fahrenheitLabel;
    @FXML
    private Label kelvinLabel;
    @FXML
    private Button convertButton;
    @FXML
    private Button clearButton;

    @FXML
    public void initialize() {

        inputUnit.getItems().addAll("Celsius", "Fahrenheit", "Kelvin");
        inputUnit.setValue("Celsius");
        convertButton.setOnAction(event -> convertTemperature());
        exitButton.setOnAction(event -> exitApplication());
        clearButton.setOnAction(event -> clearFields());

        // Add a listener to the inputTemperature TextField to validate input
        inputTemperature.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!isNumeric(newValue)) {
                inputTemperature.setText(oldValue);
            }
        });
    }

    private void convertTemperature() {
        // Clear previous output
        clearOutputLabels();

        // Validate input
        String input = inputTemperature.getText();
        if (input.isEmpty()) {
            showError("Please enter a temperature.");
            return;
        }

        try {
            double temperature = Double.parseDouble(input);
            String unit = inputUnit.getValue();

            double celsius = 0, fahrenheit = 0, kelvin = 0;

            // Convert temperature based on the selected unit
            switch (unit) {
                case "Celsius":
                    celsius = temperature;
                    fahrenheit = (temperature * 9 / 5) + 32;
                    kelvin = temperature + 273.15;
                    break;
                case "Fahrenheit":
                    fahrenheit = temperature;
                    celsius = (temperature - 32) * 5 / 9;
                    kelvin = celsius + 273.15;
                    break;
                case "Kelvin":
                    kelvin = temperature;
                    celsius = temperature - 273.15;
                    fahrenheit = (celsius * 9 / 5) + 32;
                    break;
            }

            // Display the results
            celsiusLabel.setText(String.format("°C : %.2f", celsius));
            fahrenheitLabel.setText(String.format("°F : %.2f", fahrenheit));
            kelvinLabel.setText(String.format("K : %.2f", kelvin));
        } catch (NumberFormatException e) {
            // Handle invalid number format
            showError("Invalid input. Please enter a valid number.");
        }
    }

    public void clearFields() {
        inputTemperature.clear();
        clearOutputLabels();
        inputUnit.setValue("Celsius");
        inputTemperature.requestFocus();
    }
    private void clearInputPutTextField() {
        inputTemperature.setText("");
    }
    private void clearOutputLabels() {
        celsiusLabel.setText("°C : ");
        fahrenheitLabel.setText("°F : ");
        kelvinLabel.setText("  K : ");
    }

    private void exitApplication() {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }

    private boolean isNumeric(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Input Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}