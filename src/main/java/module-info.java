module ucr.algoritmos.pg02algoritmos {
    requires javafx.controls;
    requires javafx.fxml;


    opens ucr.algoritmos.pg02algoritmos to javafx.fxml;
    exports ucr.algoritmos.pg02algoritmos;
    exports controller;
    opens controller to javafx.fxml;
}