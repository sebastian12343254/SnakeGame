<div align="center">

![Java](https://img.shields.io/badge/Java%2017+-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Swing](https://img.shields.io/badge/Java%20Swing-5382A1?style=for-the-badge&logo=java&logoColor=white)
![Algorithms](https://img.shields.io/badge/Logic-Game_Loop-blue?style=for-the-badge)

<br>

<img width="572" height="577" alt="8" src="https://github.com/user-attachments/assets/3d175d1b-0c0b-400e-9da1-d055547dc3c1" />

</div>

# Classic Snake Game

---

## Descripción
Una implementación del juego **Snake Game** utilizando **Java Swing**. El proyecto se centra en la gestión de un ciclo de juego (*Game Loop*) mediante un `Timer`, la actualización de estados de una lista dinámica de objetos para representar el cuerpo de la serpiente, y el renderizado manual de gráficos en un sistema de rejilla.

---

## Mecánicas de Juego

* **Crecimiento Dinámico:** Cada vez que la serpiente consume comida (roja), su cuerpo (amarillo) crece utilizando una `ArrayList` que rastrea cada segmento.
* **Sistema de Movimiento:** Implementación de entrada por teclado (`KeyListener`) con validación de dirección para evitar giros de 180° sobre el propio cuerpo.
* **Detección de Colisiones:**
  * **Muros:** El juego termina si la cabeza sale de los límites de 590x590.
  * **Autocolisión:** El juego termina si la cabeza impacta contra cualquier segmento del cuerpo.
* **Sistema de Rejilla:** Renderizado basado en celdas de 25x25 píxeles para asegurar un movimiento preciso y retro.

---

## Detalles Técnicos

### Lógica de Seguimiento del Cuerpo
Para que el cuerpo siga a la cabeza de forma fluida, se implementó una lógica de actualización inversa:

```java
for(int i = snakeBody.size() - 1; i >= 0; i--) {
    Tile snakePart = snakeBody.get(i);
    if(i == 0) { // El primer segmento toma la posición previa de la cabeza
        snakePart.x = snakeHead.x;
        snakePart.y = snakeHead.y;
    } else { // Cada segmento sigue al anterior
        Tile prevNextPart = snakeBody.get(i - 1);
        snakePart.x = prevNextPart.x;
        snakePart.y = prevNextPart.y;
    }
}
```

---

## Estructura del Repositorio
App.java: Inicializa la ventana principal y arranca el hilo del juego.

SnakeGame.java: Contiene el motor del juego, la lógica de renderizado (paintComponent) y el manejo de eventos de teclado.

## Guía de Inicio
Clonar: git clone https://github.com/sebastian12343254/MarioGame

Compilar en tu editor favorito
