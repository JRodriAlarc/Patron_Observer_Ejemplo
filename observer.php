<?php

# Esta interfaz maneja la adición, eliminación y actualización de todos los observadores
interface Sujeto {
    public function suscribirse($o);
    public function cancelarSuscripcion($o);
    public function notificarObservadores($s);
}

# El método de actualización de los Observadores se llama cuando el Sujeto cambia
interface Observador {
    public function actualizar($name, $s);
}

# Esta clase extiende la interfaz Sujeto.
class Celebridad implements Sujeto {
    private $NombreCelebridad;   // nombre de la celebridad
    private $seguidores;    // lista de seguidores

    public function __construct($NombreCelebridad) {
        $this->NombreCelebridad = $NombreCelebridad;
        $this->seguidores = array();
    }

    # Agregar seguidor a la lista de seguidores registrados de la celebridad
    public function suscribirse($o) {
        $this->seguidores[] = $o;
        echo $o . " ha comenzado a seguir a " . $this->NombreCelebridad . "\n";
    }

    # Eliminar seguidor de la lista de seguidores registrados de la celebridad
    public function cancelarSuscripcion($o) {
        $index = array_search($o, $this->seguidores);
        if ($index !== false) {
            unset($this->seguidores[$index]);
            echo $o . " ha dejado de seguir a " . $this->NombreCelebridad . "\n";
        }
    }

    # Notificar a todos los seguidores registrados
    public function notificarObservadores($tweet) {
        foreach ($this->seguidores as $seguidor) {
            $seguidor->actualizar($this->NombreCelebridad, $tweet);
        }
        echo "\n";
    }

    # Este método actualiza el tweet.
    #Internamente llamará al método notificarObservadores(tweet) después de actualizar el tweet.
    public function tweet($tweet) {
        echo "\n" . $this->NombreCelebridad . " ha tuiteado :: " . $tweet . "\n";
        $this->notificarObservadores($tweet);
    }

    # Este método devuelbe una lista con los seguidores de cada Celebridad.
    public function obtenerSeguidores() {
        return $this->seguidores;
    }
}

# Esta clase extiende la interfaz Observador.
class Seguidor implements Observador {
    private $NombreSeguidor;

    public function __construct($NombreSeguidor) {
        $this->NombreSeguidor = $NombreSeguidor;
    }

    # Este método se llamará para actualizar a todos los seguidores sobre el nuevo tweet publicado.
    public function actualizar($NombreCelebridad, $tweet) {
        echo $this->NombreSeguidor . " ha recibido el tweet de " . $NombreCelebridad . " :: " . $tweet . "\n";
    }

    public function __toString() {
        return $this->NombreSeguidor;
    }
}


$leoMessi = new Celebridad("Leo Messi");
$elonMusk = new Celebridad("Elon Musk");

$juan = new Seguidor("Juan");
$pedro = new Seguidor("Pedro");
$pablo = new Seguidor("Pablo");
$javier = new Seguidor("Javier");
$carlos = new Seguidor("Carlos");
$gustavo = new Seguidor("Gustavo");

$leoMessi->suscribirse($juan);
$leoMessi->suscribirse($pedro);
$leoMessi->suscribirse($javier);

$elonMusk->suscribirse($pablo);
$elonMusk->suscribirse($carlos);
$elonMusk->suscribirse($gustavo);

$leoMessi->tweet("A LA FINAL!!!!! 🙌");
$elonMusk->tweet("Let’s make Twitter maximum fun! 💫🚀");

$leoMessi->cancelarSuscripcion($pedro);

$leoMessi->tweet("CAMPEONES DEL MUNDO!!!!!!! 🌎🏆");

// Obtener y mostrar los seguidores
$seguidoresMessi = $leoMessi->obtenerSeguidores();
echo "Seguidores de Leo Messi: " . implode(", ", $seguidoresMessi) . "\n";

$seguidoresMusk = $elonMusk->obtenerSeguidores();
echo "Seguidores de Elon Musk: " . implode(", ", $seguidoresMusk) . "\n";
?>
