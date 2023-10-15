package dad.Calculadora;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CalculadoraComplejos extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Calculadora de Números Complejos");

        Complejo numero1 = new Complejo();
        Complejo numero2 = new Complejo();
        Complejo resultado = new Complejo();


        Label num1Label = new Label("Número 1:");
        TextField num1RealField = new TextField();
        TextField num1ImagField = new TextField();

        Label num2Label = new Label("Número 2:");
        TextField num2RealField = new TextField();
        TextField num2ImagField = new TextField();

        Label resultLabel = new Label("Resultado:");
        TextField resultRealField = new TextField();
        TextField resultImagField = new TextField();

        ComboBox<String> operacionComboBox = new ComboBox<>();
        operacionComboBox.getItems().addAll("Sumar", "Restar", "Multiplicar", "Dividir");
        operacionComboBox.setValue("Sumar"); // Opción predeterminada

        operacionComboBox.setOnAction(e -> {
            String operacion = operacionComboBox.getValue();
            realizarOperacion(operacion, numero1, numero2, resultado);
        });

        Button igualButton = new Button("=");
        igualButton.setOnAction(e -> {
            String operacion = operacionComboBox.getValue();
            realizarOperacion(operacion, numero1, numero2, resultado);
        });

        // Vincular propiedades de Complejo con los campos de texto
        numero1.realProperty().bind(Bindings.createDoubleBinding(() -> parseDouble(num1RealField.getText()), num1RealField.textProperty()));
        numero1.imaginarioProperty().bind(Bindings.createDoubleBinding(() -> parseDouble(num1ImagField.getText()), num1ImagField.textProperty()));
        numero2.realProperty().bind(Bindings.createDoubleBinding(() -> parseDouble(num2RealField.getText()), num2RealField.textProperty()));
        numero2.imaginarioProperty().bind(Bindings.createDoubleBinding(() -> parseDouble(num2ImagField.getText()), num2ImagField.textProperty()));

        // Vincular propiedades de resultado con campos de texto de resultado
        resultRealField.textProperty().bind(Bindings.convert(resultado.realProperty()));
        resultImagField.textProperty().bind(Bindings.convert(resultado.imaginarioProperty()));

        // Crear HBox para los campos del primer número
        HBox num1HBox = new HBox();
        num1HBox.getChildren().addAll(num1RealField, new Label("+"), num1ImagField);

        // Crear HBox para los campos del segundo número
        HBox num2HBox = new HBox();
        num2HBox.getChildren().addAll(num2RealField, new Label("+"), num2ImagField);

        // Crear VBox para los campos del primer número
        VBox num1VBox = new VBox();
        num1VBox.getChildren().addAll(num1Label, num1HBox);

        // Crear VBox para los campos del segundo número
        VBox num2VBox = new VBox();
        num2VBox.getChildren().addAll(num2Label, num2HBox);

        // Crear VBox para el resultado
        HBox resultVBox = new HBox();
        resultVBox.getChildren().addAll(resultLabel, resultRealField, resultImagField);

        // Crear VBox para el ComboBox de operaciones
        VBox operacionesVBox = new VBox();
        operacionesVBox.getChildren().addAll(operacionComboBox);

        // Crear HBox para los botones y el símbolo "="
        VBox botonesHBox = new VBox();
        botonesHBox.getChildren().addAll(igualButton);

        // Crear Separator
        Separator separator = new Separator();
        separator.setOrientation(Orientation.VERTICAL);


        // Crear el contenedor principal VBox

        VBox mainVBox = new VBox();
        mainVBox.getChildren().addAll(num1VBox, num2VBox, separator, resultVBox);
        mainVBox.setAlignment(Pos.CENTER);

        VBox SumaVBox = new VBox();
        SumaVBox.getChildren().addAll(operacionesVBox);
        SumaVBox.setAlignment(Pos.CENTER_LEFT);
        VBox IgualVBox = new VBox();
        IgualVBox.getChildren().addAll(botonesHBox);
        IgualVBox.setAlignment(Pos.CENTER_RIGHT);

        HBox mainHBox = new HBox();
        mainHBox.getChildren().addAll(SumaVBox, mainVBox, IgualVBox);
        mainHBox.setAlignment(Pos.CENTER);

        Scene scene = new Scene(mainHBox, 500, 250); // Ajusta el ancho y alto de la ventana
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private double parseDouble(String text) {
        try {
            return Double.parseDouble(text);
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }

    private void realizarOperacion(String operacion, Complejo num1, Complejo num2, Complejo resultado) {
        switch (operacion) {
            case "Sumar":
                resultado.setReal(num1.getReal() + num2.getReal());
                resultado.setImaginario(num1.getImaginario() + num2.getImaginario());
                break;
            case "Restar":
                resultado.setReal(num1.getReal() - num2.getReal());
                resultado.setImaginario(num1.getImaginario() - num2.getImaginario());
                break;
            case "Multiplicar":
                double real = num1.getReal() * num2.getReal() - num1.getImaginario() * num2.getImaginario();
                double imag = num1.getReal() * num2.getImaginario() + num1.getImaginario() * num2.getReal();
                resultado.setReal(real);
                resultado.setImaginario(imag);
                break;
            case "Dividir":
                double denominator = num2.getReal() * num2.getReal() + num2.getImaginario() * num2.getImaginario();
                double realPart = (num1.getReal() * num2.getReal() + num1.getImaginario() * num2.getImaginario()) / denominator;
                double imagPart = (num1.getImaginario() * num2.getReal() - num1.getReal() * num2.getImaginario()) / denominator;
                resultado.setReal(realPart);
                resultado.setImaginario(imagPart);
                break;
        }
    }
}
