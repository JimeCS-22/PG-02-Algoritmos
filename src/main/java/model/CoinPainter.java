package model;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.ArrayList;
import java.util.List;

public class CoinPainter extends Canvas {

    private static class Moneda {
        int valor;
        int cantidad;
        Color color;

        public Moneda(int valor, int cantidad, Color color) {
            this.valor = valor;
            this.cantidad = cantidad;
            this.color = color;
        }
    }

    private final Moneda[] monedas = new Moneda[]{
            new Moneda(500, 0, Color.GOLD),
            new Moneda(100, 0, Color.LIGHTGRAY),
            new Moneda(50, 0, Color.SILVER),
            new Moneda(25, 0, Color.SANDYBROWN),
            new Moneda(10, 0, Color.PERU),
            new Moneda(5, 0, Color.PERU),
            new Moneda(1, 0, Color.LIGHTGRAY),
    };

    public CoinPainter() {
        super(800, 200);
        drawMonedas();
    }

    public void setMonedas(int[] cantidades) {
        for (int i = 0; i < cantidades.length && i < monedas.length; i++) {
            monedas[i].cantidad = cantidades[i];
        }
        drawMonedas();
    }

    private void drawMonedas() {
        GraphicsContext gc = getGraphicsContext2D();
        gc.clearRect(0, 0, getWidth(), getHeight());

        // 1) Filtrar solo las monedas usadas
        List<Moneda> usadas = new ArrayList<>();
        for (Moneda m : monedas) {
            if (m.cantidad > 0) usadas.add(m);
        }

        if (usadas.isEmpty()) return;

        double y = 80;
        double radio = 32;          // círculo de 100x100
        double diam = radio * 2;    // 100
        double gap = 18;            // separación entre monedas
        double step = diam + gap;

        // 2) Calcular x inicial para que se vea centrado según cuántas monedas existan
        double totalWidth = usadas.size() * step - gap; // último no lleva gap
        double startX = Math.max(radio + 10, (getWidth() - totalWidth) / 2 + radio);

        double x = startX;

        for (Moneda m : usadas) {
            // Borde
            gc.setStroke(Color.DARKGRAY);
            gc.setLineWidth(4);
            gc.strokeOval(x - (radio + 5), y - (radio + 5), (radio + 5) * 2, (radio + 5) * 2);

            // Círculo
            gc.setFill(m.color);
            gc.fillOval(x - radio, y - radio, diam, diam);

            // Valor
            gc.setFill(Color.BLACK);
            gc.setFont(Font.font(18));
            String valor = "₡" + m.valor;
            gc.fillText(valor, x - 32, y + 10);

            // Badge rojo (cantidad)
            gc.setFill(Color.RED);
            gc.fillOval(x + radio - 10, y - radio - 10, 26, 26);

            gc.setFill(Color.WHITE);
            gc.setFont(Font.font(14));
            String cantidad = "×" + m.cantidad;
            gc.fillText(cantidad, x + radio - 8, y - radio + 7);

            x += step;
        }
    }
}