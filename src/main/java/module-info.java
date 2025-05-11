module io.github.luis01felipe.knighttour {
    requires javafx.controls;
    requires javafx.fxml;


    opens io.github.luis01felipe.knighttour to javafx.fxml;
    exports io.github.luis01felipe.knighttour;
}