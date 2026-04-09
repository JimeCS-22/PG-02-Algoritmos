package model;


import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class NqueensCanvas  {

    public void paint(Canvas canvas, int[] posiciones) {

        int n = posiciones.length;
        GraphicsContext gc = canvas.getGraphicsContext2D();

        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        double margin = 35;
        double size = Math.min(canvas.getWidth(), canvas.getHeight()) - 2 * margin;
        double cell = size / n;

        // Tablero
        for (int f = 0; f < n; f++) {
            for (int c = 0; c < n; c++) {

                double x = margin + c * cell;
                double y = margin + f * cell;

                gc.setFill(((f + c) % 2 == 0) ? Color.rgb(230, 239, 255) : Color.WHITE);
                gc.fillRect(x, y, cell, cell);

                gc.setStroke(Color.TEAL);
                gc.strokeRect(x, y, cell, cell);
            }
        }

        // Reinas
        gc.setFont(Font.font("Segoe UI Symbol", cell * 0.65));

        for (int col = 0; col < n; col++) {
            int fila = posiciones[col];

            if (fila >= 0) {
                double x = margin + col * cell;
                double y = margin + fila * cell;

                gc.setFill(Color.rgb(44, 182, 182));
                gc.fillOval(x + cell * 0.09, y + cell * 0.09, cell * 0.82, cell * 0.82);

                gc.setFill(Color.DARKSLATEGRAY);
                gc.fillText("\u265B", x + cell * 0.17, y + cell * 0.78);
            }
        }

        // Borde
        gc.setStroke(Color.TEAL);
        gc.setLineWidth(3);
        gc.strokeRect(margin, margin, size, size);
    }

    public void paint(Canvas canvas, int[] posiciones, NQueenProblem.Step step) {

        GraphicsContext gc = canvas.getGraphicsContext2D();

        int n = posiciones.length;
        double cellSize = canvas.getWidth() / n;

        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

        for (int col = 0; col < n; col++) {
            for (int row = 0; row < n; row++) {

                double x = col * cellSize;
                double y = row * cellSize;

                // tablero normal
                if ((row + col) % 2 == 0) {
                    gc.setFill(Color.WHITE);
                } else {
                    gc.setFill(Color.GRAY);
                }

                // 🔴 TRY
                if (step != null &&
                        step.tipo.equals("TRY") &&
                        step.fila == row &&
                        step.col == col) {

                    gc.setFill(Color.RED);
                }

                // 🟢 PLACE
                if (step != null &&
                        step.tipo.equals("PLACE") &&
                        step.fila == row &&
                        step.col == col) {

                    gc.setFill(Color.GREEN);
                }

                gc.fillRect(x, y, cellSize, cellSize);

                // 👑 dibujar reina
                if (posiciones[col] == row) {
                    gc.setFill(Color.BLACK);
                    gc.fillText("♛", x + cellSize / 3, y + cellSize / 1.5);
                }
            }
        }
    }
}
