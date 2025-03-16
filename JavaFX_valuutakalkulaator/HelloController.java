package com.example.demo7;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.util.Map;

public class HelloController {

    private Map<String, Double> exchangeRates;

    @FXML
    private TextField euroField;

    @FXML
    private ComboBox<String> currencyDropdown;

    @FXML
    private Label convertedValue;

    @FXML
    private Canvas canvas;

    public void initialize() {
        drawLogo();
    }

    private void drawLogo() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        double width = canvas.getWidth();
        double height = canvas.getHeight();

        gc.setFill(Color.LIGHTGRAY);
        gc.fillRect(0, 0, width, height);

        double circleSize = Math.min(width, height) * 0.5;
        gc.setFill(Color.RED);
        gc.fillOval((width - circleSize) / 2, (height - circleSize) / 2, circleSize, circleSize);

        double rectWidth = width * 0.8;
        double rectHeight = height * 0.2;
        gc.setFill(Color.BLUE);
        gc.fillRect((width - rectWidth) / 2, height - rectHeight, rectWidth, rectHeight);

        gc.setStroke(Color.BLACK);
        gc.setLineWidth(width * 0.05);
        gc.strokeLine(width * 0.1, height * 0.1, width * 0.9, height * 0.9);
    }


    public void setExchangeRates(Map<String, Double> exchangeRates) {
        this.exchangeRates = exchangeRates;
        currencyDropdown.getItems().addAll(exchangeRates.keySet());

        currencyDropdown.valueProperty().addListener((observable, oldValue, newValue) -> exchange());
        euroField.textProperty().addListener((observable, oldValue, newValue) -> exchange());
    }

    public void exchange() {
        String currency = currencyDropdown.getValue();

        String euro = euroField.getText().trim();
        double rate = exchangeRates.get(currency);

        if (euro.isEmpty()) {
            convertedValue.setText("0.00");
            return;
        }

        double euroDouble = Double.parseDouble(euro);
        double newRate = rate * euroDouble;

        convertedValue.setText(String.format("%.2f %s", newRate,currency));

    }

    public void onClearButtonClick() {
        euroField.clear();
        convertedValue.setText("0.00");
    }
}