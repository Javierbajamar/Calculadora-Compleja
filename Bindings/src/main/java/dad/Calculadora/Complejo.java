package dad.Calculadora;


import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;


public class Complejo {

    private DoubleProperty real;
    private DoubleProperty imaginario;

    public Complejo() {
        this.real = new SimpleDoubleProperty();
        this.imaginario = new SimpleDoubleProperty();
    }

    public double getReal() {
        return real.get();
    }

    public DoubleProperty realProperty() {
        return real;
    }

    public void setReal(double real) {
        this.real.set(real);
    }

    public double getImaginario() {
        return imaginario.get();
    }

    public DoubleProperty imaginarioProperty() {
        return imaginario;
    }

    public void setImaginario(double imaginario) {
        this.imaginario.set(imaginario);
    }


}
