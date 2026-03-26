package controller;

import javafx.animation.Timeline;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import model.ArrayPainter;
import model.SearchEngine;
import model.SearchResult;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @javafx.fxml.FXML
    private TabPane mainTabs;
    @javafx.fxml.FXML
    private TextField txtBinValue;
    @javafx.fxml.FXML
    private Canvas canvasBin;
    @javafx.fxml.FXML
    private Button btnBinAnimate;
    @javafx.fxml.FXML
    private Button btnBinGen;
    @javafx.fxml.FXML
    private Label lblBinSize;
    @javafx.fxml.FXML
    private Slider sliderBinSize;
    @javafx.fxml.FXML
    private Label lblBinArray;
    @javafx.fxml.FXML
    private ProgressBar progressBarBin;
    @javafx.fxml.FXML
    private Button btnBinSearch;
    @javafx.fxml.FXML
    private ListView<String> listBinSteps;
    @javafx.fxml.FXML
    private Label lblBinResult;
    @javafx.fxml.FXML
    private Label lblBinTime;
    @javafx.fxml.FXML
    private Label lblBinComplex;
    @javafx.fxml.FXML
    private Button btnBinReset;
    @javafx.fxml.FXML
    private Label lblBinComps;

    //TAB-1 BINARIA-ATRIBUTOS INTERNOS DEL CONTROLLER
    private final SearchEngine searchEngine = new SearchEngine();
    private final ArrayPainter arrayPainter = new ArrayPainter();
    private Timeline animation;
    private int[] binArray;
    private SearchResult binResult;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        
        setupBinTab();



    }

    private void setupBinTab() {

        configSlider(sliderBinSize, 10, 50, 20, lblBinSize);
        btnBinGen.setOnAction(e -> generateBin());
    }

    private void generateBin() {

        int size = (int) sliderBinSize.getValue();
        binArray = SearchEngine.generateSorted(size, size * 15);
        binResult = null;
        repaintBin();

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

    private void configSlider(Slider s, int min, int max, int val, Label lblBinSize) {

        s.setMin(min); s.setMax(max); s.setValue(val);
        s.setMajorTickUnit(5); s.setSnapToTicks(false);
        s.valueProperty().addListener((obs, oldVal, newVal) -> {
            lblBinSize.setText(String.valueOf(newVal.intValue()));
        });

    }




}
