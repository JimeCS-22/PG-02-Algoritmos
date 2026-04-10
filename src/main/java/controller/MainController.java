package controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.Property;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import model.*;

import java.net.URL;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @FXML
    private TabPane mainTabs;
    @FXML
    private TextField txtBinValue;
    @FXML
    private Canvas canvasBin;
    @FXML
    private Button btnBinAnimate;
    @FXML
    private Button btnBinGen;
    @FXML
    private Label lblBinSize;
    @FXML
    private Slider sliderBinSize;
    @FXML
    private Label lblBinArray;
    @FXML
    private ProgressBar progressBarBin;
    @FXML
    private Button btnBinSearch;
    @FXML
    private ListView<String> listBinSteps;
    @FXML
    private Label lblBinResult;
    @FXML
    private Label lblBinTime;
    @FXML
    private Label lblBinComplex;
    @FXML
    private Button btnBinReset;
    @FXML
    private Label lblBinComps;

    //TAB-2 MONEDAS
    @FXML
    private TextField txtCoinValue;
    @FXML
    private Slider sliderCoinAmount;
    @FXML
    private Button btnCoinChange;
    @FXML
    private ListView<String> listCoinSteps;
    @FXML
    private Button btnCoinReset;
    @FXML
    private Canvas canvasCoin;
    @FXML
    private TableView<Greedy.Coin> tableViewCoin;
    @FXML
    private TableColumn<Greedy.Coin, Integer> colMonto;
    @FXML
    private TableColumn<Greedy.Coin, Integer> colMoneda;
    @FXML
    private TableColumn<Greedy.Coin, Integer> colCantidad;
    @FXML
    private TableColumn<Greedy.Coin, Integer> colRestante;

    private static final int [] MONEDAS_CR = {500,100,50,25,10,5,1};

    //TAB 4 -MOCHILA
    @FXML
    private Canvas canvasKnapsack;
    @FXML
    private Slider sliderKnapsack;
    @FXML
    private Button btnSolveKnapsack;
    @FXML
    private Button btnResetKnapsack;
    @FXML
    private Label lblCapKnapsack;
    @FXML
    private ListView<String> listStepsKnapsack;
    @FXML
    private Label lblWeightKnapsack;
    @FXML
    private Label lblTotalValueKnapsack;
    @FXML
    private Label txtCapWValue;
    @FXML
    private ChoiceBox<String> cbPaquetes;
    KnapsackPainter knapsackPainter;
    @FXML
    private Label lblValorOptimo;
    @FXML
    private Label lblTimeKnapsack;

    //TAB-1 BINARIA-ATRIBUTOS INTERNOS DEL CONTROLLER
    private final SearchEngine searchEngine = new SearchEngine();
    private final ArrayPainter arrayPainter = new ArrayPainter();
    private Timeline animation;
    private int[] binArray;
    private SearchResult binResult;
    @FXML
    private Button btnClean;
    @FXML
    private Label lblVelocidad;
    @FXML
    private Button btnAnimar;
    @FXML
    private Button btnResolve;
    @FXML
    private Button btnStop;
    @FXML
    private ToggleButton tab8x8;
    @FXML
    private Slider sliderVelocidad;
    private CoinPainter monedasCanvas;
    @FXML
    private Label lblSolucion;
    @FXML
    private Label lblBack;
    @FXML
    private Label lblTime;
    @FXML
    private Label lblConflictos;
    @FXML
    private Label lblCalls;
    @FXML
    private ListView ListSyeps;
    @FXML
    private ProgressBar progressBar;
    @FXML
    private ToggleButton tab4x4;
    @FXML
    private Canvas canvasTab;
    private ToggleGroup grupoTablero = new ToggleGroup();
    private List<NQueenProblem.Step> queenSteps;
    private Timeline queenAnimation;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setupBinTab();
        setupCoinsTab();
        setUpNQueensTab();
        setupKnapsackTab();
        knapsackPainter = new KnapsackPainter();
    }

    private void setupBinTab() {

        configSlider(sliderBinSize, 10, 50, 20, lblBinSize);
        btnBinGen.setOnAction(e -> generateBin());
        btnBinSearch.setOnAction(e -> runSearch(false));
        btnBinAnimate.setOnAction(e -> runSearch(true));
        btnBinReset.setOnAction(e -> resetBinTab());
    }

    private void runSearch(boolean animate) {

        if (binArray == null ) {
            showError(txtBinValue, "Primero genere un arreglo");
            return;
        }

        /**
         * Tomamos el valor a buscar del textField
         * Si el usuario no indico ningun valor, asignamos uno de los que existen en el arreglo
        **/
        int value;
        try {

            value = Integer.parseInt(txtBinValue.getText());


        }catch (NumberFormatException e){
            //Elegimos un elemento al azar del arreglo
            value = binArray[new Random().nextInt(binArray.length)];
            txtBinValue.setText(String.valueOf(value));
        }
        binArray = SearchEngine.ensureContains(binArray, value);
        updateArrayLabel(lblBinArray,binArray);
        SearchResult searchResult = searchEngine.binary(binArray, value);

        //llenamos el ListView
        ObservableList<String> items = FXCollections.observableArrayList();
        for (int i = 0; i < searchResult.steps.size(); i++) {

            SearchResult.Step step = searchResult.steps.get(i);
            items.add(String.format("[%02d] %s" , i+1, step.description));

        }

        listBinSteps.setItems(items);

        //actualizar
        updateStats(lblBinResult,lblBinComps,lblBinTime, lblBinComplex,searchResult);

        //agregamos la animacion del canvas
        if (animate){

            animateSearch(searchResult, binArray,canvasBin,progressBarBin, listBinSteps);

        }else {
            //pintamos en el canvas
            boolean [] vist = buildVisited(searchResult.steps, binArray.length, searchResult.steps.size());
            SearchResult.Step last = searchResult.steps.isEmpty() ? null : searchResult.steps.getLast();
            arrayPainter.paint(canvasBin, binArray,last,vist, searchResult.foundIndex);
            progressBarBin.setProgress(1.0);

        }

    }

    private void updateStats(Label lblBinResult, Label lblBinComps, Label lblBinTime, Label lblBinComplex, SearchResult searchResult) {

        if (searchResult == null) {
            clearStats(lblBinResult, lblBinComps, lblBinTime, lblBinComplex);
            return;
        }

        if (searchResult.isFound()) {
            lblBinResult.setText("Encontrado en índice: " + searchResult.foundIndex);
            lblBinResult.setStyle("-fx-text-fill: #2ECC71; -fx-font-weight: bold;");
        } else {
            lblBinResult.setText("No encontrado");
            lblBinResult.setStyle("-fx-text-fill: #E74C3C; -fx-font-weight: bold;");
        }


        lblBinComps.setText(String.valueOf(searchResult.comparisons));
        lblBinComps.setStyle("-fx-text-fill: #3498DB;");


        double timeMs = searchResult.nanoTime / 1_000_000.0;
        lblBinTime.setText(String.format("%.4f ms", timeMs));
        lblBinTime.setStyle("-fx-text-fill: #9B59B6;");


        lblBinComplex.setText(searchResult.complexityLabel());
        lblBinComplex.setStyle("-fx-text-fill: #F39C12;");
    }

    private void animateSearch(SearchResult res, int[] arr, Canvas canvas,
                               ProgressBar pb, ListView<String> lv) {
        stopAnimation();
        if (res.steps.isEmpty()) return;

        int total = res.steps.size();
        int delay = Math.max(180, Math.min(800, 3000 / total));

        animation = new Timeline();
        for (int i = 1; i <= total; i++) {
            final int step = i;
            animation.getKeyFrames().add(new KeyFrame(Duration.millis(step * delay), e -> {
                SearchResult.Step s  = res.steps.get(step - 1);
                boolean[] vis = buildVisited(res.steps, arr.length, step - 1);
                int found = s.isHit ? s.index : (step == total ? res.foundIndex : -1);
                arrayPainter.paint(canvas, arr, s, vis, found);
                pb.setProgress((double) step / total);
                lv.scrollTo(step - 1);
                lv.getSelectionModel().select(step - 1);
            }));
        }
        animation.setOnFinished(e -> pb.setProgress(1.0));
        animation.play();
    }

    private void stopAnimation() {
        if (animation != null) { animation.stop(); animation = null; }
    }

    private void showError(TextField txt, String msg) {

        txt.setStyle("-fx-border-color:  #E74C3C");
        txt.setPromptText(msg);
        txt.setText("");
    }

    private void generateBin() {

        int size = (int) sliderBinSize.getValue();
        binArray = SearchEngine.generateSorted(size, size * 15);
        binResult = null;
        repaintBin();
        updateArrayLabel(lblBinArray, binArray);

    }

    private void repaintBin() {

        if (binResult == null) return;

        SearchResult.Step step = null;
        boolean[] visited = null;
        int found = -1;

        if (binResult != null){
            step = binResult.steps.isEmpty() ? null : binResult.steps.get(binResult.steps.size() - 1);
            visited = buildVisited(binResult.steps, binArray.length, binResult.steps.size());
        }
    }

    private boolean[] buildVisited(List<SearchResult.Step> steps, int n, int upTo) {
        boolean[] vis = new boolean[n];
        int limit = Math.min(upTo, steps.size());
        for (int i = 0; i < limit; i++) {
            int idx = steps.get(i).index;
            if (idx >= 0 && idx < n) vis[idx] = true;
        }
        return vis;
    }

    private void updateArrayLabel(Label lbl, int[] arr) {
        if (arr == null || arr.length == 0) { lbl.setText(""); return; }
        StringBuilder sb = new StringBuilder("[");
        int show = Math.min(arr.length, 20);
        for (int i = 0; i < show; i++) {
            sb.append(arr[i]);
            if (i < show - 1) sb.append(", ");
        }
        if (arr.length > 20) sb.append(", …");
        sb.append("]  (n=").append(arr.length).append(")");
        lbl.setText(sb.toString());
    }

    private void clearStats(Label ... labels){
        for (Label lbl : labels) {
            lbl.setText("-");
            lbl.setStyle("");
        }
    }

    private void configSlider(Slider s, int min, int max, int val, Label lblBinSize) {

        s.setMin(min); s.setMax(max); s.setValue(val);
        s.setMajorTickUnit(5); s.setSnapToTicks(false);
        s.valueProperty().addListener((obs, oldVal, newVal) -> {
            lblBinSize.setText(String.valueOf(newVal.intValue()));
        });

    }

    private void resetBinTab() {
        stopAnimation();
        binArray = null;
        binResult = null;

        txtBinValue.clear();
        listBinSteps.getItems().clear();

        lblBinArray.setText("");
        clearStats(lblBinResult, lblBinComps, lblBinTime, lblBinComplex);

        progressBarBin.setProgress(0);

        // Canvas vacío con texto
        arrayPainter.paintEmpty(canvasBin, "Presione 'Generar Arreglo' para iniciar");
    }

    //TAB-2 MONEDAS - METODOS

    private void setupCoinsTab() {
        //Setteamos las columnas con los nombres de los atributos de la clase Greedy.Coin
        //Greedy.Coin debe tener getters para que funcione
        colMoneda.setCellValueFactory(new PropertyValueFactory<>("coin"));
        colCantidad.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        colMonto.setCellValueFactory(new PropertyValueFactory<>("amount"));
        colRestante.setCellValueFactory(new PropertyValueFactory<>("remaining"));

        sliderCoinAmount.setMin(500);
        sliderCoinAmount.setMax(5000);
        sliderCoinAmount.setValue(787);
        sliderCoinAmount.valueProperty().addListener((observable, oldValue, newValue) -> {
            txtCoinValue.setText(String.valueOf(newValue.intValue()));
        });

        btnCoinChange.setOnAction(e -> runCoinChange());
        btnCoinReset.setOnAction(e -> clearCoinChange());

        // Encuentra el padre donde está canvasCoin (es el VBox del FXML)
        VBox vb = (VBox) canvasCoin.getParent();

      // Busca su índice dentro del VBox
        int index = vb.getChildren().indexOf(canvasCoin);

      // Crea la instancia y reemplaza<<<<<<<<<<<<<<<<<<<<
        monedasCanvas = new CoinPainter();
        monedasCanvas.setWidth(canvasCoin.getWidth());
        monedasCanvas.setHeight(canvasCoin.getHeight());
        vb.getChildren().set(index, monedasCanvas);
    }

    private void runCoinChange() {
        int monto;
        try {
            monto = Integer.parseInt(txtCoinValue.getText().trim());
        } catch (NumberFormatException ex) {
            showError(txtCoinValue, "Ingrése un monto válido");
            return;
        }

        // Se llama a greedy y se obtienen los pasos
        listCoinSteps.getItems().clear();
        List<String> coinList = Greedy.coinChange2(monto);

        ObservableList<String> items = FXCollections.observableArrayList();
        for (int i = 0; i < coinList.size(); i++) {
            items.add(String.format("[%02d] %s", i + 1, coinList.get(i)));
        }

        items.add("Monto Total: " + monto + " | Monedas: " + coinList.size());
        listCoinSteps.setItems(items);

        //Table View
        tableViewCoin.getItems().clear();
        int remaining = monto;
        for(int coin : MONEDAS_CR){
            int quantity = remaining / coin;
            if(quantity > 0){
                remaining %= coin;
                //agregamos la fila al tableview
                tableViewCoin.getItems().add(new Greedy.Coin(coin, quantity, quantity*coin, remaining));
            }
        }

        // Cantidades para el canvas
        int[] cantidades = new int[MONEDAS_CR.length];
        int remainingCanvas = monto;

        for (int i = 0; i < MONEDAS_CR.length; i++) {
            cantidades[i] = remainingCanvas / MONEDAS_CR[i];
            remainingCanvas %= MONEDAS_CR[i];
        }

        // Actualiza el canvas de monedas
        if (monedasCanvas != null) {
            monedasCanvas.setMonedas(cantidades);
        }
    }

    private void clearCoinChange() {
        txtCoinValue.clear();
        listCoinSteps.getItems().clear();
        tableViewCoin.getItems().clear();

        // “vaciar” el canvas de monedas
        if (monedasCanvas != null) {
            monedasCanvas.setMonedas(new int[MONEDAS_CR.length]); // todo en 0
            monedasCanvas.getGraphicsContext2D().clearRect(0, 0, monedasCanvas.getWidth(), monedasCanvas.getHeight());
        }
    }

    //Tab-3 N-QUEENS
    private void setUpNQueensTab(){

        tab4x4.setToggleGroup(grupoTablero);
        tab8x8.setToggleGroup(grupoTablero);

        tab4x4.setSelected(true);
        dibujarTablero(4);

        tab4x4.setOnAction(e -> dibujarTablero(4));
        tab8x8.setOnAction(e -> dibujarTablero(8));

        btnResolve.setOnAction(e -> resolverNQueens());
        btnAnimar.setOnAction(e -> animateQueens(getNSeleccionado()));

        sliderVelocidad.setMin(1);   // Turbo
        sliderVelocidad.setMax(5);   // Muy lento
        sliderVelocidad.setValue(3); // Valor por defecto
        sliderVelocidad.setMajorTickUnit(1);
        sliderVelocidad.setShowTickLabels(true);
        sliderVelocidad.setShowTickMarks(true);

        String[] etiquetas = {"Muy lento", "Lento", "Medio", "Rápido", "Turbo"};
        int[] delays = {1200, 600, 300, 120, 40};

        sliderVelocidad.valueProperty().addListener((obs, oldVal, newVal) -> {
            int idx = newVal.intValue() - 1;
            if (idx < 0) idx = 0;
            if (idx > 4) idx = 4;
            lblVelocidad.setText(etiquetas[idx]);
        });
        lblVelocidad.setText("Medio");

        btnStop.setOnAction(e -> {
            if (queenAnimation != null) {
                queenAnimation.stop();
                queenAnimation = null;
                progressBar.setProgress(0);
            }
        });

        btnClean.setOnAction(e -> {
            if (queenAnimation != null) {
                queenAnimation.stop();
                queenAnimation = null;
            }
            ListSyeps.getItems().clear();
            int n = getNSeleccionado();
            dibujarTablero(n);
            lblSolucion.setText("...");
            lblCalls.setText("...");
            lblConflictos.setText("...");
            lblBack.setText("...");
            lblTime.setText("...");
            progressBar.setProgress(0);
        });
    }

    private void resolverNQueens() {

        int n = getNSeleccionado();

        int cellSize = 60;
        double size = n * cellSize;

        canvasTab.setWidth(size);
        canvasTab.setHeight(size);

        NQueenProblem problem = new NQueenProblem();

        int[] posiciones = problem.solveNQueensPositions(n);

        queenSteps = problem.steps;

        int numSoluciones = problem.contarSoluciones(n);
        lblSolucion.setText(numSoluciones + " soluciones");

        if (posiciones == null) {
            return;
        }

        NqueensCanvas painter = new NqueensCanvas();
        painter.paint(canvasTab, posiciones);

        ObservableList<String> items = FXCollections.observableArrayList();

        for (int i = 0; i < queenSteps.size(); i++) {
            var s = queenSteps.get(i);

            String estado =
                    s.tipo.equals("PLACE") ? "✔" :
                            s.tipo.equals("REMOVE") ? "↩" : "❌";

            items.add(String.format("[%02d] (%d,%d) -> %s",
                    i + 1,
                    s.fila,
                    s.col,
                    estado));
        }

        ListSyeps.setItems(items);

        lblCalls.setText(String.valueOf(problem.llamadasRecursivas));
        lblConflictos.setText(String.valueOf(problem.conflictos));
        lblBack.setText(String.valueOf(problem.backtracks));
        lblTime.setText(String.format("%.4f ms", problem.tiempoNano / 1_000_000.0));
    }
    private void dibujarTablero(int n){

        int cellSize = 60;
        double size = n * cellSize;

        canvasTab.setWidth(size);
        canvasTab.setHeight(size);

        int[] posiciones = new int[n];
        for (int i = 0; i < n; i++) {
            posiciones[i] = -1;
        }

        NqueensCanvas painter = new NqueensCanvas();
        painter.paint(canvasTab, posiciones);
    }

    private int getNSeleccionado() {
        if (grupoTablero.getSelectedToggle() == tab4x4) {
            return 4;
        } else if (grupoTablero.getSelectedToggle() == tab8x8) {
            return 8;
        }
        return 4;
    }

    private void animateQueens(int n) {

        if (queenSteps == null || queenSteps.isEmpty()) return;

        if (queenAnimation != null) queenAnimation.stop();

        int[][] board = new int[n][n];

        int total = queenSteps.size();

        int velocidad = (int) sliderVelocidad.getValue();
        int[] delays = {1200, 600, 300, 120, 40};
        int delay = delays[velocidad - 1];
        queenAnimation = new Timeline();

        for (int i = 0; i < total; i++) {

            final int stepIndex = i;

            queenAnimation.getKeyFrames().add(
                    new KeyFrame(Duration.millis(i * delay), e -> {

                        NQueenProblem.Step step = queenSteps.get(stepIndex);

                        if (step.tipo.equals("PLACE")) {
                            board[step.fila][step.col] = 1;
                        }
                        else if (step.tipo.equals("REMOVE")) {
                            board[step.fila][step.col] = 0;
                        }

                        NqueensCanvas painter = new NqueensCanvas();
                        painter.paint(canvasTab, convertirBoard(board), step);

                        progressBar.setProgress((double) (stepIndex + 1) / total);

                        ListSyeps.scrollTo(stepIndex);
                        ListSyeps.getSelectionModel().select(stepIndex);
                    })
            );
        }

        queenAnimation.play();
    }

    private int[] convertirBoard(int[][] board) {
        int n = board.length;
        int[] posiciones = new int[n];

        for (int col = 0; col < n; col++) {
            posiciones[col] = -1;
            for (int row = 0; row < n; row++) {
                if (board[row][col] == 1) {
                    posiciones[col] = row;
                    break;
                }
            }
        }
        return posiciones;
    }

    //CONTROLLER TAB MOCHILA
    private void setupKnapsackTab() {
        configChoiceBox(cbPaquetes);//settear paquetes en choiceBox
        configSlider(sliderKnapsack, 2, 30, 20, txtCapWValue);//settear slider
        btnSolveKnapsack.setOnAction(e -> runKnapsack());
        btnResetKnapsack.setOnAction(e -> resetKnapsack());
    }

    private void resetKnapsack() {
        txtCapWValue.setText("0");
        sliderKnapsack.setValue(2);
        txtBinValue.setText("2");
        listStepsKnapsack.setItems(null);
        //RESUMEN MOCHILA
        lblCapKnapsack.setText("kg");
        lblTotalValueKnapsack.setText("");
        lblWeightKnapsack.setText("kg");
    }

    private void runKnapsack() {
        String paquete = cbPaquetes.getValue();
        Item[] items = null;
        int capacity = (int) sliderKnapsack.getValue();

        if (!paquete.isEmpty()){
            int index = cbPaquetes.getSelectionModel().getSelectedIndex();
            switch (index) {
                case 0:
                    items = Item.Package1();
                    break;
                case 1:
                    items = Item.Package2();
                    break;
                case 2:
                    items = Item.Package3();
                    break;
                case 3:
                    items = Item.Package4();
                    break;
            }
        }
        Greedy.KnapsackResult result = Greedy.knapsackSolve(items, capacity);
        lblValorOptimo.setText(( result.getMaxValue())+"" );
        lblTimeKnapsack.setText((result.getNanoTime())+" ms");

        Item[] lista = result.sortedItems;
        ObservableList<String> listaItems= FXCollections.observableArrayList();
        listaItems.add("Lista de objetos: ");
        for (Item item : lista) {
            listaItems.add(item.toString());
        }

        listaItems.add("----------------------");
        listaItems.add("Pasos del algoritmo:");

        for (Item item : lista) {
            listaItems.add(getPaso(item, result));
        }
        listStepsKnapsack.setItems(listaItems);

        //Resumen mochila
        lblCapKnapsack.setText( result.getCapacity() + " kg");
        lblWeightKnapsack.setText( result.getMaxWeight()+ " kg");
        lblTotalValueKnapsack.setText("₡ "+ result.getMaxValue());
        //dibujar la mochila
        knapsackPainter.dibujarMochila(canvasKnapsack,sliderKnapsack.getValue(),items);

    }

    private static String getPaso(Item item, Greedy.KnapsackResult result) {
        double capacidadRestante =  result.getCapacity();
        double fraccion = capacidadRestante >= item.getWeight()
                ? 1.0
                : (double) capacidadRestante / item.getWeight();

        double valorTomado = item.getValue() * fraccion;

        String paso;

        if (fraccion == 1.0) {
            paso = "✔ Tomar 100% de '" + item.getName() +
                    "' (" + item.getWeight() + "kg) → +" + item.getValue();
        } else {
            paso = "⚠ Tomar " + String.format("%.2f", fraccion * 100) + "% de '" +
                    item.getName() + "' → +" + String.format("%.2f", valorTomado);
        }
        return paso;
    }

    private void configChoiceBox(ChoiceBox cbPaquetes) {
        ObservableList<String> listPackages =  FXCollections.observableArrayList(
                "Paquete No.1 (12 items)",
                "Paquete No.2 (7 items)",
                "Paquete No.3 (4 items)", // ajusta este número
                "Paquete No.4 (4 items)"
        );
        cbPaquetes.setItems(listPackages);
        cbPaquetes.getSelectionModel().selectFirst(); // selecciona Paquete 1 por defecto
    }


}
