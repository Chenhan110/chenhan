package footballManager;


public abstract class Persona {

    protected final String nombre;
    protected final String apellido;
    protected final String fechaNacimiento;
    protected double motivacion ;
    protected double sueldoAnual;


    public abstract void entrenar();


    public Persona(String nombre, String apellido, String fechaNacimiento, double sueldoAnual) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNacimiento = fechaNacimiento;
        this.motivacion = 5;
        this.sueldoAnual = sueldoAnual;
    }

    public Persona(String nombre, String apellido, String fechaNacimiento) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNacimiento = fechaNacimiento;
    }

    public String toFileFormat() {
        return nombre+ ";" + apellido + ";" + fechaNacimiento + ";" + motivacion + ";" + sueldoAnual ;
    }



//
    public String getNombre() {
        return this.nombre;
    }

    public String getApellido() {
        return this.apellido;
    }

    public String getFechaNacimiento() {
        return this.fechaNacimiento;
    }

    public double getMotivacion() {
        return this.motivacion;
    }

    public void setMotivacion(double nivellMotivacion) {
        this.motivacion = nivellMotivacion;
    }

    public void setSueldoAnual(double sueldoAnual) {
        this.sueldoAnual = sueldoAnual;
    }

    public double getSueldoAnual() {
        return this.sueldoAnual;
    }
}
