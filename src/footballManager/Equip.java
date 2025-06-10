package footballManager;

import java.util.ArrayList;
import java.util.List;
/**
 * Clase que representa un equipo de fútbol.
 */
public class Equip {
    private String nombre;
    private int anyoFundacion;
    private String ciudad;
    private String estadio;
    private String presidente;
    private Entrenador entrenador;
    private List<Jugador> jugadores;

    public Equip(String nombre, int anyoFundacion, String ciudad, String estadio, String presidente) {
        this.nombre = nombre;
        this.anyoFundacion = anyoFundacion;
        this.ciudad = ciudad;
        this.estadio = estadio;
        this.presidente = presidente;
        jugadores = new ArrayList<>();

    }

    public Equip(String nombre, int anyoFundacion, String ciudad, String estadio, String presidente, List<Jugador> jugadores) {
        this.nombre = nombre;
        this.anyoFundacion = anyoFundacion;
        this.ciudad = ciudad;
        this.estadio = (estadio != null) ? estadio : "";
        this.presidente = (presidente != null) ? presidente : "";
        this.jugadores = jugadores;
    }

    public Equip(String nombre) {
        this.nombre = nombre;
    }

    public double calcularCalidadMedia() {

        if (jugadores == null || jugadores.isEmpty()) {
            return 0.0;
        }

        double sum = 0.0;
        for (Jugador jugador : jugadores) {
            sum += jugador.getCalidad();
        }
        return sum / jugadores.size();
    }


    public Jugador buscarJugadorPorNombre(String nombre) {
        for (Jugador jugador : jugadores) {
            if (jugador.getNombre().equalsIgnoreCase(nombre)) {
                return jugador;
            }
        }
        return null;
    }

    public Jugador buscarJugadorPorDorsal(int dorsal) {
        for (Jugador jugador : jugadores) {
            if (jugador.getDorsal() == dorsal) {
                return jugador;
            }
        }
        return null;
    }

    public void eliminarJugador(Jugador jugador) {
        if (jugadores.contains(jugador)) {
            jugadores.remove(jugador);
            System.out.println("El jugador " + jugador.getNombre() + " ha sido eliminado del equipo.");
        } else {
            System.out.println("El jugador no está en el equipo.");
        }
    }

    public void anadirJugador(Jugador jugador) {
        jugadores.add(jugador);
    }


    public boolean existeDorsal(int dorsal) {
        return jugadores.stream().anyMatch(j -> j.getDorsal() == dorsal);
    }


    public String toFileFormat() {
        StringBuilder sb = new StringBuilder();
        sb.append("EQUIPO:").append(nombre).append(";").append(anyoFundacion).append("\n");
        for (Jugador j : jugadores) {
            sb.append(j.toFileFormat()).append("\n");
        }

        if (entrenador != null) {
            sb.append(entrenador.toFileFormat()).append("\n");
        } else {
            sb.append("ENTRENADOR:NO_ASIGNADO\n"); // 或者其他你喜欢的提示信息
        }

        return sb.toString();
    }



    public void removeEntrenador() {
        this.entrenador = null;
    }



//
    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getAnyoFundacion() {
        return this.anyoFundacion;
    }

    public void setAnyoFundacion(int anyoFundacion) {
        this.anyoFundacion = anyoFundacion;
    }

    public String getCiudad() {
        return this.ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getEstadio() {
        return this.estadio;
    }

    public void setEstadio(String estadio) {
        this.estadio = estadio;
    }

    public String getPresidente() {
        return this.presidente;
    }

    public void setPresidente(String presidente) {
        this.presidente = presidente;
    }

    public Entrenador getEntrenador() {
        return this.entrenador;
    }

    public void setEntrenador(Entrenador entrenador) {
        this.entrenador = entrenador;
    }

    public List<Jugador> getJugadores() {
        return this.jugadores;
    }

    public void setJugadores(List<Jugador> jugadores) {
        this.jugadores = jugadores;
    }

    @Override
    public String toString() {
        return "Equip{" +
                "nombre='" + nombre + '\'' +
                ", anyoFundacion=" + anyoFundacion +
                ", ciudad='" + ciudad + '\'' +
                ", estadio='" + estadio + '\'' +
                ", presidente='" + presidente + '\'' +
                ", entrenador=" + entrenador +
                ", jugadores=" + jugadores +
                '}';
    }


}
