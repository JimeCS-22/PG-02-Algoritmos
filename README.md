<?xml version="1.0" encoding="UTF-8"?>

<?import javafx.scene.canvas.Canvas?>
<?import javafx.scene.control.Button?>
<?import javafx.scene.control.Label?>
<?import javafx.scene.control.ListView?>
<?import javafx.scene.control.ProgressBar?>
<?import javafx.scene.control.ScrollPane?>
<?import javafx.scene.control.Slider?>
<?import javafx.scene.control.SplitPane?>
<?import javafx.scene.control.Tab?>
<?import javafx.scene.control.TabPane?>
<?import javafx.scene.control.TextField?>
<?import javafx.scene.layout.BorderPane?>
<?import javafx.scene.layout.ColumnConstraints?>
<?import javafx.scene.layout.GridPane?>
<?import javafx.scene.layout.HBox?>
<?import javafx.scene.layout.RowConstraints?>
<?import javafx.scene.layout.VBox?>
<?import javafx.scene.text.Font?>

<BorderPane maxHeight="-Infinity" maxWidth="-Infinity" minHeight="-Infinity" minWidth="-Infinity" prefHeight="703.0" prefWidth="1031.0" stylesheets="@styles.css" xmlns="http://javafx.com/javafx/25" xmlns:fx="http://javafx.com/fxml/1" fx:controller="controller.MainController">
    <top>
        <VBox prefHeight="95.0" prefWidth="828.0" styleClass="header-bar" stylesheets="@styles.css" BorderPane.alignment="CENTER">
            <children>
                <HBox prefHeight="100.0" prefWidth="200.0" spacing="16.0" stylesheets="@styles.css">
                    <children>
                        <Label prefHeight="51.0" prefWidth="600.0" styleClass="header-badge" stylesheets="@styles.css" text="PG-02">
                            <font>
                                <Font size="18.0" />
                            </font>
                        </Label>
                        <VBox prefHeight="100.0" prefWidth="1013.0">
                            <children>
                                <Label prefHeight="17.0" prefWidth="389.0" styleClass="header-title" stylesheets="@styles.css" text="Recursividad Aplicada en JavaFx">
                                    <font>
                                        <Font size="24.0" />
                                    </font>
                                </Label>
                                <Label styleClass="header-sub" stylesheets="@styles.css" text="IF-3001 Algoritmos y Estructuras de datos">
                                    <font>
                                        <Font size="24.0" />
                                    </font>
                                </Label>
                            </children>
                        </VBox>
                    </children>
                </HBox>
            </children>
        </VBox>
    </top>
    <center>
        <TabPane fx:id="mainTabs" prefHeight="200.0" prefWidth="200.0" styleClass="main-tabs" stylesheets="@styles.css" tabClosingPolicy="UNAVAILABLE" BorderPane.alignment="CENTER">
            <tabs>
                <Tab text="Binaria">
                    <content>
                        <SplitPane dividerPositions="0.5615906886517944" prefHeight="200.0" prefWidth="200.0">
                            <items>
                                <VBox prefHeight="200.0" prefWidth="100.0" spacing="8.0" styleClass="panel">
                                    <children>
                                        <Label styleClass="panel-title" text="Busqueda Binaria O (log n). Soluciòn con la Técnica Divide y Vencerás">
                                 <font>
                                    <Font size="10.0" />
                                 </font></Label>
                                        <ScrollPane prefHeight="8.0" prefWidth="548.0">
                                 <content>
                                    <Label fx:id="lblBinArray" text="BinArray" />
                                 </content>
                                        </ScrollPane>
                              <Canvas fx:id="canvasBin" height="291.0" width="548.0" />
                              <ProgressBar fx:id="progressBarBin" prefHeight="17.0" prefWidth="548.0" progress="0.0" />
                              <HBox prefHeight="100.0" prefWidth="200.0">
                                 <children>
                                    <HBox prefHeight="50.0" prefWidth="129.0">
                                       <children>
                                          <Label text="■" />
                                          <Label text="Comparando" />
                                       </children>
                                    </HBox>
                                    <HBox prefHeight="100.0" prefWidth="115.0">
                                       <children>
                                          <Label text="■" />
                                          <Label text="Visitado" />
                                       </children>
                                    </HBox>
                                    <HBox prefHeight="100.0" prefWidth="131.0">
                                       <children>
                                          <Label text="■" />
                                          <Label text="Encontrado" />
                                       </children>
                                    </HBox>
                                    <HBox prefHeight="100.0" prefWidth="114.0">
                                       <children>
                                          <Label text="■" />
                                          <Label text="Sin Visitar" />
                                       </children>
                                    </HBox>
                                 </children>
                              </HBox>                                    </children>
                                </VBox>
                                <VBox prefHeight="565.0" prefWidth="470.0" spacing="10.0" styleClass="panel">
                                    <children>
                                        <Label styleClass="action-label" text="Parámetros" />
                                        <HBox prefHeight="28.0" prefWidth="316.0">
                                            <children>
                                                <Label styleClass="param-label" text="Tamaño del arreglo= " />
                                                <Label fx:id="lblBinSize" styleClass="value-badge" text="5" />
                                            </children>
                                        </HBox>
                                        <Slider fx:id="sliderBinSize" prefHeight="14.0" prefWidth="315.0" showTickLabels="true" />
                              <HBox prefHeight="32.0" prefWidth="481.0">
                                 <children>
                                    <Label text="Elemento a buscar:" />
                                    <TextField fx:id="txtBinValue" prefHeight="28.0" prefWidth="213.0" />
                                 </children>
                              </HBox>
                                        <HBox prefHeight="35.0" prefWidth="316.0" spacing="10.0">
                                            <children>
                                                <Button fx:id="btnBinGen" mnemonicParsing="false" styleClass="btn-primary" text="Generar Arreglo" />
                                    <Button fx:id="btnBinSearch" mnemonicParsing="false" styleClass="btn-primary" text="Buscar" />
                                            </children>
                                        </HBox>
                              <HBox prefHeight="51.0" prefWidth="421.0" spacing="10.0">
                                 <children>
                                    <Button fx:id="btnBinAnimate" mnemonicParsing="false" styleClass="btn-primary" text="Animar" />
                                                <Button fx:id="btnFactReset" mnemonicParsing="false" styleClass="btn-ghost" text="Limpiar" />
                                 </children>
                              </HBox>
                              <GridPane>
                                <columnConstraints>
                                  <ColumnConstraints hgrow="SOMETIMES" minWidth="10.0" prefWidth="100.0" />
                                  <ColumnConstraints hgrow="SOMETIMES" minWidth="10.0" prefWidth="100.0" />
                                </columnConstraints>
                                <rowConstraints>
                                  <RowConstraints minHeight="10.0" prefHeight="30.0" vgrow="SOMETIMES" />
                                  <RowConstraints minHeight="10.0" prefHeight="30.0" vgrow="SOMETIMES" />
                                  <RowConstraints minHeight="10.0" prefHeight="30.0" vgrow="SOMETIMES" />
                                </rowConstraints>
                                 <children>
                                    <VBox prefHeight="200.0" prefWidth="100.0">
                                       <children>
                                          <Label text="Resultado" />
                                          <Label text="---" />
                                       </children>
                                    </VBox>
                                    <VBox prefHeight="200.0" prefWidth="100.0" GridPane.rowIndex="1">
                                       <children>
                                          <Label text="Comparaciones" />
                                          <Label text="---" />
                                       </children>
                                    </VBox>
                                    <VBox prefHeight="200.0" prefWidth="100.0" GridPane.columnIndex="1" GridPane.rowIndex="1">
                                       <children>
                                          <Label text="Tiempo" />
                                          <Label text="---" />
                                       </children>
                                    </VBox>
                                    <VBox prefHeight="200.0" prefWidth="100.0" GridPane.rowIndex="2">
                                       <children>
                                          <Label text="Complejidad" />
                                          <Label text="---" />
                                       </children>
                                    </VBox>
                                 </children>
                              </GridPane>
                                        <Label styleClass="section-label" text="Pasos de ejecución" />
                                        <ListView fx:id="listSteps" prefHeight="200.0" prefWidth="200.0" styleClass="steps-list" />
                                    </children></VBox>
                            </items>
                        </SplitPane>
                    </content>
                </Tab>
            </tabs>
        </TabPane>
    </center>
</BorderPane>
