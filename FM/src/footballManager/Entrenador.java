package footballManager;


import java.util.Random;

public class Entrenador extends Persona {
    private boolean esNacional;
    private int numeroTorneos;
    private static final Random random = new Random();

    public Entrenador(String nombre, String apellido, String fechaNacimiento, double sueldoAnual, int numeroTorneos, boolean esNacional) {
        super(nombre, apellido, fechaNacimiento, sueldoAnual);
        this.numeroTorneos = numeroTorneos;
        this.esNacional = esNacional;
    }

    public void incrementarSou() {
        this.sueldoAnual = this.sueldoAnual * 1.005;
    }

    @Override
    public void entrenar() {
        double base;
        if (esNacional) {
            base = 0.3;
        } else {
            base = 0.15;
        }

        double probabilitat = random.nextDouble();

        if (probabilitat < 0.5) {
            this.motivacion += base;
            System.out.println(nombre + " ha aumentado la motivación en " + base);
        } else if (probabilitat < 0.8) {
            System.out.println(nombre + " no ha cambiado su motivación.");
        } else {
            //和0比较，取较大的值
            this.motivacion = Math.max(0, motivacion - 0.8);
            System.out.println(nombre + " ha perdito 0.8 de motivación.");
        }

        motivacion = Math.min(motivacion, 10.0);
    }

    @Override
    public String toFileFormat() {
        return "E" + super.toFileFormat() + numeroTorneos + ";" + esNacional;
    }



//
    public boolean isEsNacional() {
        return this.esNacional;
    }

    public void setEsNacional(boolean esNacional) {
        this.esNacional = esNacional;
    }

    public int getNumeroTorneos() {
        return this.numeroTorneos;
    }

    public void setNumeroTorneos(int numeroTorneos) {
        this.numeroTorneos = numeroTorneos;
    }

    @Override
    public String toString() {
        return "Entrenador{" +
                "nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", fechaNacimiento='" + fechaNacimiento + '\'' +
                ", nivellMotivacion=" + motivacion +
                ", sueldoAnual=" + sueldoAnual +
                ", esNacional=" + esNacional +
                ", numeroTorneos=" + numeroTorneos +
                '}';
    }
}
