package footballManager;

import java.util.Objects;
import java.util.Random;

public class Jugador extends Persona {
    private int dorsal;
    public static final String[] POSICIONS = {"POR", "DEF", "MIG", "DAV"};

    private int calidad;
    private String posicion;
    private static final Random random = new Random();

    public Jugador(String nombre, String apellido, String fechaNacimiento, double sueldoAnual, int dorsal, String posicion) {
        super(nombre, apellido, fechaNacimiento, sueldoAnual);
        this.dorsal = dorsal;

        this.calidad =  30 + new Random().nextInt(71);

        this.posicion = posicion;
    }

    public Jugador(String nombre, String apellido, String fechaNacimiento, double sueldoAnual, int dorsal, String posicion, int calidad) {
        super(nombre, apellido, fechaNacimiento, sueldoAnual);
        this.dorsal = dorsal;
        this.posicion = posicion;
        this.calidad = calidad;
    }

    public void cambioPosicio(){

        if (random.nextInt(100) < 5) {
            this.posicion = POSICIONS[random.nextInt(POSICIONS.length)];
            System.out.println("Posición cambiado");
            this.calidad += 1;

        }

    }

    @Override
    public void entrenar() {
        double probabilidad = random.nextDouble();

        if (probabilidad < 0.7) {
            this.motivacion += 0.3;
        } else if (probabilidad < 0.9) { // 20%概率
            this.motivacion = Math.max(0, motivacion - 1.5);
        } else {
            this.motivacion += 1.2;
            System.out.println("motivacion +1.2 ");
        }

        motivacion = Math.min(motivacion, 10.0);
    }


    @Override
    public String toFileFormat() {
        return "J" + super.toFileFormat() + ";" + dorsal + ";" + posicion + ";" + calidad;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, dorsal);
    }




    //getter and setter
    public int getDorsal() {
        return this.dorsal;
    }

    public void setDorsal(int dorsal) {
        this.dorsal = dorsal;
    }


    public int getCalidad() {
        return this.calidad;
    }

    public void setCalidad(int calidad) {
        this.calidad = calidad;
    }

    public String getPosicion() {
        return this.posicion;
    }

    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }


    @Override
    public String toString() {
        return "Jugador{" +
                "nombre='" + nombre +
                ", apellido='" + apellido +
                ", fechaNacimiento='" + fechaNacimiento +
                ", nivellMotivacion=" + motivacion +
                ", sueldoAnual=" + sueldoAnual +
                ", dorsal=" + dorsal +
                ", calidad=" + calidad +
                ", posicion='" + posicion +
                '}';
    }
}
