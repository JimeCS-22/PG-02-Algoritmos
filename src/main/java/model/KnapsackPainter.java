package model;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.ArcType;


import java.util.List;

public class KnapsackPainter extends Canvas {
    //Definimos las dimensiones  y posición de la mochila en el canvas
    private final double MOCHILA_X = 100;
    private final double MOCHILA_Y= 50;
    private final double MOCHILA_ANCHO = 200;
    private final double MOCHILA_ALTO_MAX = 300;

    public void dibujarMochila(Canvas canvas, double capacidadMaxima, Item[]itemsSelected){
        //GraphicsContext gc = graphicsContext;
        GraphicsContext gc = canvas.getGraphicsContext2D();
        //1.Limpiar el canvas
        gc.clearRect(0,0,canvas.getWidth(),canvas.getHeight());

        //2.Activar suavizado para bordes lindos
        gc.setImageSmoothing(true);

        //3.Dibujar la estructura de la mochila

        // Fondo mochila
        gc.setFill(Color.web("#F5DEB3")); // beige
        gc.fillRoundRect(
                MOCHILA_X,
                MOCHILA_Y,
                MOCHILA_ANCHO,
                MOCHILA_ALTO_MAX,
                20,
                20
        );

        // Borde
        gc.setStroke(Color.web("#8B4513")); // café
        gc.setLineWidth(4);
        gc.strokeRoundRect(
                MOCHILA_X,
                MOCHILA_Y,
                MOCHILA_ANCHO,
                MOCHILA_ALTO_MAX,
                20,
                20
        );

        // =========================
        // 4. Dibujar manija
        // =========================
        gc.setLineWidth(6);
        gc.strokeArc(
                MOCHILA_X + MOCHILA_ANCHO / 4,
                MOCHILA_Y - 40,
                MOCHILA_ANCHO / 2,
                60,
                0,
                180,
                ArcType.OPEN
        );

        // =========================
        // 5. Dibujar items
        // =========================

        double alturaActual = 0;

        for (Item item : itemsSelected) {

            // Altura proporcional al peso
            double alturaItem = (item.getWeight() / capacidadMaxima) * MOCHILA_ALTO_MAX;

            // Color del item
            gc.setFill(item.getColor());

            // Dibujar bloque
            double y = MOCHILA_Y + MOCHILA_ALTO_MAX - alturaActual - alturaItem;

            gc.fillRoundRect(
                    MOCHILA_X + 5,
                    y,
                    MOCHILA_ANCHO - 10,
                    alturaItem,
                    10,
                    10
            );

            // Borde del item
            gc.setStroke(Color.BLACK);
            gc.setLineWidth(1);
            gc.strokeRoundRect(
                    MOCHILA_X + 5,
                    y,
                    MOCHILA_ANCHO - 10,
                    alturaItem,
                    10,
                    10
            );

            // Texto
            gc.setFill(Color.BLACK);
            gc.fillText(
                    item.getName() + " (" + item.getWeight() + "kg)",
                    MOCHILA_X + 10,
                    y + alturaItem / 2
            );

            alturaActual += alturaItem;
        }
    }

}

