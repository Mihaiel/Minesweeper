module minesweeper {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires com.almasb.fxgl.all;
    requires java.desktop;

    opens minesweeper to javafx.fxml;
    exports minesweeper;
    exports minesweeper.UI;
    opens minesweeper.UI to javafx.fxml;
}