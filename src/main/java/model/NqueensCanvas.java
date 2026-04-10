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

        //
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

        int n = posiciones.length;
        GraphicsContext gc = canvas.getGraphicsContext2D();

        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        double margin = 35;
        double size = Math.min(canvas.getWidth(), canvas.getHeight()) - 2 * margin;
        double cell = size / n;

        for (int f = 0; f < n; f++) {
            for (int c = 0; c < n; c++) {

                double x = margin + c * cell;
                double y = margin + f * cell;

                // Fondo igual "bonito" que el inicial
                Color casillaBase = ((f + c) % 2 == 0) ? Color.rgb(230, 239, 255) : Color.WHITE;

                // Colorea según el paso de animación SOLO la casilla actual
                if (step != null && step.fila == f && step.col == c) {
                    if (step.tipo.equals("TRY")) {
                        gc.setFill(Color.RED);
                    } else if (step.tipo.equals("PLACE")) {
                        gc.setFill(Color.LIMEGREEN);
                    } else if (step.tipo.equals("REMOVE")) {
                        gc.setFill(Color.ORANGE);
                    } else {
                        gc.setFill(casillaBase);
                    }
                } else {
                    gc.setFill(casillaBase);
                }

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
}