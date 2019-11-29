module PlatformGameClient {
    requires javafx.graphics;
    requires javafx.fxml;
    requires javafx.controls;
    requires PlatformGameShared;
    requires java.desktop;
    exports gamegui;
    exports gamegui.controllers;
    opens gamegui.controllers;
}