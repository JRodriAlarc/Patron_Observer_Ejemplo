import java.util.ArrayList;
 
/**
 * Esta interfaz maneja la adición, eliminación y actualización de todos los observadores
 */
interface Sujeto {
    public void seguir(Observador o);
    public void dejarDeSeguir(Observador o);
    public void notificarATodosLosObservadores(String s);
}
 
/**
 * El método de actualización de los Observadores se llama cuando el Sujeto cambia
 */
interface Observador {
    public void actualizar(String nombre, String s);
}
 
/**
 * Esta clase extiende la interfaz Sujeto.
 */
class Celebridad implements Sujeto {
 
    private String nombreCelebridad;  // nombre de la celebridad
    private ArrayList<Observador> seguidores;  // lista de seguidores
     
    public Celebridad(String nombreCelebridad) {
        this.nombreCelebridad = nombreCelebridad;
        seguidores = new ArrayList<Observador>();
    }
 
    /**
     * agregar seguidor a la lista de seguidores registrados de la celebridad
     */
    @Override
    public void seguir(Observador o) {
        seguidores.add(o);
        System.out.println(o + " ha comenzado a seguir a " + nombreCelebridad);
    }
 
    /**
     * eliminar seguidor de la lista de seguidores registrados de la celebridad
     */
    @Override
    public void dejarDeSeguir(Observador o) {
        seguidores.remove(o);
        System.out.println(o + " ha dejado de seguir a " + nombreCelebridad);
    }
     
    /**
     * Notificar a todos los seguidores registrados
     */
    @Override
    public void notificarATodosLosObservadores(String tweet) {
        for(Observador seguidor : seguidores)
        {
            seguidor.actualizar(nombreCelebridad, tweet);
        }
        System.out.println();
    }
     
    /**
     * Este método actualiza el tweet.
     * Internamente llamará al método notificarATodosLosObservadores(tweet)
     * después de actualizar el tweet.
     */
    public void twittear(String tweet)
    {
         
        System.out.println("\n" + nombreCelebridad + " ha twitteado :: " + tweet + "\n");
         
        notificarATodosLosObservadores(tweet);
    }
     
}
 
/**
 * Esta clase extiende la interfaz Observador.
 */
class Seguidor implements Observador {
 
    private String nombreSeguidor;
     
    public Seguidor(String nombreSeguidor) {
        this.nombreSeguidor = nombreSeguidor;
    }
 
    /**
     * Este método se llamará para actualizar a todos los seguidores sobre el
     * nuevo tweet publicado por la celebridad.
     */
    @Override
    public void actualizar(String nombreCelebridad, String tweet) {
        System.out.println(nombreSeguidor + " ha recibido el tweet de "+ nombreCelebridad + " :: "+  tweet);
         
    }
 
    @Override
    public String toString() {
        return nombreSeguidor;
    }
     
     
}
 
public class PatronDiseñoObservador {
 
    public static void main(String[] args) {
        Celebridad leoMessi = new Celebridad("Leo Messi");
        Celebridad elonMusk = new Celebridad("Selena Gomez");
         
        Seguidor juan = new Seguidor("Juan");
        Seguidor pedro = new Seguidor("Pedro");
        Seguidor pablo = new Seguidor("Pablo");
        Seguidor javier =  new Seguidor("Javier");
        Seguidor carlos = new Seguidor("Carlos");
        Seguidor gustavo =  new Seguidor("Gustavo");
         
        leoMessi.seguir(juan);
        leoMessi.seguir(pedro);
        leoMessi.seguir(pablo);
         
        elonMusk.seguir(javier);
        elonMusk.seguir(carlos);
        elonMusk.seguir(gustavo);
         
        leoMessi.twittear("A LA FINAL!!!!! 🙌");
        elonMusk.twittear("Let’s make Twitter maximum fun! 💫🚀");
         
        leoMessi.dejarDeSeguir(pedro);
         
        leoMessi.twittear("CAMPEONES DEL MUNDO!!!!!!! 🌎🏆");
         
    }
 
}
