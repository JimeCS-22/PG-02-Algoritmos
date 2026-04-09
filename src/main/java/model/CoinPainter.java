package model;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

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

    private Moneda[] monedas = new Moneda[]{
            new Moneda(500, 6, Color.GOLD),      // ₡500 - Amarillo
            new Moneda(100, 4, Color.LIGHTGRAY), // ₡100 - Gris
            new Moneda(25, 1, Color.SANDYBROWN), // ₡25 - Marrón claro
            new Moneda(10, 1, Color.PERU),       // ₡10 - Marrón
            new Moneda(5, 1, Color.PERU),        // ₡5 - Marrón
            new Moneda(1, 1, Color.SILVER),      // ₡1 - Gris claro
    };

    public CoinPainter() {
        super(800, 200);
        drawMonedas();
    }

    // LLama esto si necesitas cambiar las cantidades dinámicamente.
    public void setMonedas(int[] cantidades) {
        for(int i = 0; i < cantidades.length && i < monedas.length; i++) {
            monedas[i].cantidad = cantidades[i];
        }
        drawMonedas();
    }

    private void drawMonedas() {
        GraphicsContext gc = getGraphicsContext2D();
        gc.clearRect(0, 0, getWidth(), getHeight());
        double x = 60;
        double y = 80;
        double radio = 55;

        for (Moneda m : monedas) {
            // Dibuja borde
            gc.setStroke(Color.DARKGRAY);
            gc.setLineWidth(4);
            gc.strokeOval(x-55, y-55, 110, 110);

            // Dibuja círculo
            gc.setFill(m.color);
            gc.fillOval(x-50, y-50, 100, 100);

            // Dibuja valor
            gc.setFill(Color.BLACK);
            gc.setFont(Font.font(25));
            String valor = "₡" + m.valor;
            gc.fillText(valor, x-32, y+10);

            // Dibuja círculo rojo de cantidad
            gc.setFill(Color.RED);
            gc.fillOval(x+30, y-60, 35, 35);
            // Número de cantidad
            gc.setFill(Color.WHITE);
            gc.setFont(Font.font(18));
            String cantidad = "×" + m.cantidad;
            gc.fillText(cantidad, x+37, y-38);

            x += 130;
        }
    }
}
