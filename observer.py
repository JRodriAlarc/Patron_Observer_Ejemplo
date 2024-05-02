from abc import ABC, abstractmethod


class Sujeto(ABC):
    @abstractmethod
    def seguir(self, observador):
        pass

    @abstractmethod
    def DejarDeSeguir(self, observador):
        pass

    @abstractmethod
    def notificarObservadores(self, message):
        pass


class Observador(ABC):
    @abstractmethod
    def actualizar(self, name, message):
        pass


class Celebridad(Sujeto):
    def __init__(self, nombreDeLaCelebridad):
        self.nombreDeLaCelebridad = nombreDeLaCelebridad
        self.seguidores = []

    def seguir(self, observador):
        self.seguidores.append(observador)
        print(f"{observador} ha comenzado a seguir a {self.nombreDeLaCelebridad}")

    def DejarDeSeguir(self, observador):
        self.seguidores.remove(observador)
        print(f"{observador} ha dejado de seguir a {self.nombreDeLaCelebridad}")

    def notificarObservadores(self, tweet):
        for follower in self.seguidores:
            follower.actualizar(self.nombreDeLaCelebridad, tweet)
        print()

    def tweet(self, tweet):
        print(f"\n{self.nombreDeLaCelebridad} ha tuiteado :: {tweet}\n")
        self.notificarObservadores(tweet)


class Seguidor(Observador):
    def __init__(self, nombreDelSeguidor):
        self.nombreDelSeguidor = nombreDelSeguidor

    def actualizar(self, nombreDeLaCelebridad, tweet):
        print(f"{self.nombreDelSeguidor} ha recibido el tweet de {nombreDeLaCelebridad} :: {tweet}")

    def __str__(self):
        return self.nombreDelSeguidor


def main():
    leoMessi = Celebridad("Leo Messi")
    elonMusk = Celebridad("Elon Musk")

    juan = Seguidor("Juan")
    pedro = Seguidor("Pedro")
    pablo = Seguidor("Pablo")
    javier = Seguidor("Javier")
    carlos = Seguidor("Carlos")
    gustavo = Seguidor("Gustavo")

    leoMessi.seguir(juan)
    leoMessi.seguir(pedro)
    leoMessi.seguir(pablo)

    elonMusk.seguir(javier)
    elonMusk.seguir(carlos)
    elonMusk.seguir(gustavo)

    leoMessi.tweet("A LA FINAL!!!!! 🙌")
    elonMusk.tweet("Let’s make Twitter maximum fun! 💫🚀")

    leoMessi.DejarDeSeguir(pedro)

    leoMessi.tweet("CAMPEONES DEL MUNDO!!!!!!! 🌎🏆")


if __name__ == "__main__":
    main()
