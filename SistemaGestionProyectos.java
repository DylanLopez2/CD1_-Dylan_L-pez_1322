import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Proyecto {
    private String nombre;
    private List<Tarea> tareas;

    public Proyecto(String nombre){
        this.nombre = nombre;
        this.tareas = new ArrayList<>();
    }

    public String getNombre(){
        return nombre;
    }

    public void agregarTarea(Tarea tarea){
        tareas.add(tarea);
    }

    public void mostrarEstado(){
        System.out.println("Estado del proyecto: "+nombre);
        if (tareas.isEmpty()) {
            System.out.println("- Sin tareas asignadas");
        } else {
            for (Tarea tarea : tareas) {
                System.out.println("- " + tarea);
            }
        }
    }
public void cambiarEstadoTareas() {
        Scanner scanner = new Scanner(System.in);
        for (Tarea tarea : tareas) {
            System.out.print("¿Desea cambiar el estado de la tarea \"" + tarea.getDescripcion() + "\" a 'Finalizado'? (s/n): ");
            String respuesta = scanner.nextLine();
            if (respuesta.equalsIgnoreCase("s")) {
                tarea.cambiarEstado("Finalizado");
                System.out.println("Tarea \"" + tarea.getDescripcion() + "\" marcada como 'Finalizado'.");
            }
        }
    }
}

class Tarea {
    private String descripcion;
    private String responsable;
    private String estado;

    public Tarea(String descripcion, String responsable){
        this.descripcion = descripcion;
        this.responsable = responsable;
        this.estado = "Pendiente";
    }

    public String getDescripcion() {
        return descripcion;
    }
    
    public void cambiarEstado(String nuevoEstado) {
        this.estado = nuevoEstado;
    }
   
    public String toString(){
        return descripcion + " (Estudiante: " + responsable + ", Estado: " + estado + ")";
    }
}

public class SistemaGestionProyectos {
    private List<Proyecto> proyectos;

    public SistemaGestionProyectos() {
        proyectos = new ArrayList<>();
    }

    public void registrarProyecto(String nombre) {
        proyectos.add(new Proyecto(nombre));
    }

    public Proyecto obtenerProyectoPorIndice(int indice) {
        if (indice>=0 && indice<proyectos.size()) {
            return proyectos.get(indice);
        }
        return null;
    }

    public void mostrarEstadosProyectos(){
        if (proyectos.isEmpty()) {
            System.out.println("No hay proyectos registrados.");
        } else {
            for (Proyecto proyecto : proyectos){
                proyecto.mostrarEstado();
                System.out.println();
            }
        }
    }

    public static void main(String[] args) {
        SistemaGestionProyectos sistema = new SistemaGestionProyectos();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el nombre del primer proyecto: ");
        String nombreProyecto1 = scanner.nextLine();
        sistema.registrarProyecto(nombreProyecto1);
        System.out.print("Ingrese el nombre del segundo proyecto: ");
        String nombreProyecto2 = scanner.nextLine();
        sistema.registrarProyecto(nombreProyecto2);
        
        for (int i=0;i<2;i++) {
            Proyecto proyecto = sistema.obtenerProyectoPorIndice(i);
            if (proyecto != null) {
                System.out.print("\n");
                System.out.print("Ingrese la descripción de la tarea para el proyecto " + proyecto.getNombre() + ": ");
                String descripcion = scanner.nextLine();
                System.out.print("Ingrese el estudiante de la tarea: ");
                String responsable = scanner.nextLine();
                proyecto.agregarTarea(new Tarea(descripcion, responsable));
                System.out.println("Tarea asignada al proyecto: " + proyecto.getNombre());
            }
        }
        System.out.print("\n");
        System.out.println(" ----Estado de los proyectos---- ");
        sistema.mostrarEstadosProyectos();
        
        for (Proyecto proyecto : sistema.proyectos) {
            proyecto.cambiarEstadoTareas();
        }
        
        System.out.print("\n");
        System.out.println(" ----Estado final de los proyectos---- ");
        sistema.mostrarEstadosProyectos();
        scanner.close();
    }
}