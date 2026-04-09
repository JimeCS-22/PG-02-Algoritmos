module ucr.algoritmos.pg02algoritmos {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens ucr.algoritmos.pg02algoritmos to javafx.fxml;
    exports ucr.algoritmos.pg02algoritmos;
    exports controller;
    opens controller to javafx.fxml;

    //Necesitamos exportar el paquete de modelos para que el controlador pueda acceder a las clases de modelo
    exports model;
    opens model to javafx.fxml;
}