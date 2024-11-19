package org.prodigy_sd_01;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class Controller {
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
    public void initialize() {
        inputUnit.getItems().addAll("Celsius", "Fahrenheit", "Kelvin");
        inputUnit.setValue("Celsius");
        convertButton.setOnAction(event -> convertTemperature());
    }

    private void convertTemperature() {
        try {
            double temperature = Double.parseDouble(inputTemperature.getText());
            String unit = inputUnit.getValue();

            double celsius = 0, fahrenheit = 0, kelvin = 0;

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

            celsiusLabel.setText(String.format("°C : %.2f", celsius));
            fahrenheitLabel.setText(String.format("°F : %.2f", fahrenheit));
            kelvinLabel.setText(String.format("K : %.2f", kelvin));
        } catch (NumberFormatException e) {
            celsiusLabel.setText("°C : Invalid input");
            fahrenheitLabel.setText("°F : Invalid input");
            kelvinLabel.setText("K : Invalid input");
        }
    }
}