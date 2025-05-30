package footballManager;


import java.util.*;

public class Lliga {
    private String nombre;
    private List<Equip> equipos;
    private List<Partit> partidos;
    private Map<Equip, int[]> clasificacion;


    public Lliga(String nombre) {
        this.nombre = nombre;
        this.equipos = new ArrayList<>();
        this.partidos = new ArrayList<>();
        this.clasificacion = new HashMap<>();
    }


    public void anadirEquipo(Equip equipo) {
        if (equipos.contains(equipo)) {
            System.out.println("El equipo ya existe");
        }else {
            equipos.add(equipo);
            clasificacion.put(equipo, new int[4]);
        }
    }


    public void programarPartido(Partit partido) {
        partidos.add(partido);
    }

    public void actualizarClasificacion() {
        for (Partit partido : partidos) {
            Equip local = partido.getEquipLocal();
            Equip visitante = partido.getEquipVisitant();

            if (!clasificacion.containsKey(local) || !clasificacion.containsKey(visitante)) {
                throw new IllegalStateException("El equipo no está registrado: "
                        + local.getNombre() + " vs " + visitante.getNombre());
            }

            int[] statsLocal = clasificacion.get(local);
            int[] statsVisitante = clasificacion.get(visitante);

            statsLocal[1]++;    // 主队参赛次数+1
            statsVisitante[1]++; // 客队参赛次数+1

            statsLocal[2] += partido.getGolsLocal();      // 主队进球
            statsLocal[3] += partido.getGolsVisitant();   // 主队失球
            statsVisitante[2] += partido.getGolsVisitant(); // 客队进球
            statsVisitante[3] += partido.getGolsLocal();    // 客队失球


            if (partido.getGolsLocal() > partido.getGolsVisitant()) {
                statsLocal[0] += 3; // 主队胜
            } else if (partido.getGolsLocal() < partido.getGolsVisitant()) {
                statsVisitante[0] += 3; // 客队胜
            } else {
                statsLocal[0] += 1;     // 平局
                statsVisitante[0] += 1;
            }
        }
    }


    public void mostrarClasificacion() {
        if (equipos.isEmpty()) {
            System.out.println("No hay equipos");
            return;
        }
        actualizarClasificacion();
        List<Equip> equiposOrdenados = new ArrayList<>(equipos);
        equiposOrdenados.sort((e1, e2) -> {
            int[] stats1 = clasificacion.get(e1);
            int[] stats2 = clasificacion.get(e2);

            // 按积分排序
            int diffPuntos = stats2[0] - stats1[0];
            if (diffPuntos != 0) return diffPuntos;

            // 积分相同则按净胜球排序
            return (stats2[2] - stats2[3]) - (stats1[2] - stats1[3]);
        });

        System.out.printf("%-15s %-10s %-10s %-15s %-15s%n", "Equipo", "Puntos", "Partidos", "Goles A Favor", "Goles En Contra");
        for (Equip equipo : equiposOrdenados) {
            int[] stats = clasificacion.get(equipo);
            System.out.printf("%-15s %-10d %-10d %-15d %-15d%n",
                    equipo.getNombre(),
                    stats[0],
                    stats[1],
                    stats[2],
                    stats[3]);
        }
    }





//
    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Partit> getPartidos() {
        return this.partidos;
    }

    public void setPartidos(List<Partit> partidos) {
        this.partidos = partidos;
    }

    public List<Equip> getEquipos() {
        return this.equipos;
    }

    public void setEquipos(List<Equip> equipos) {
        this.equipos = equipos;
    }

    public Map<Equip, int[]> getClasificacion() {
        return clasificacion;
    }

    public void setClasificacion(Map<Equip, int[]> clasificacion) {
        this.clasificacion = clasificacion;
    }

}
