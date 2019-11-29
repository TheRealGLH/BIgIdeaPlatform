module PlatformGameClient {
    requires javafx.graphics;
    requires javafx.fxml;
    requires javafx.controls;
    requires PlatformGameShared;
    exports gamegui;
    exports gamegui.controllers;
    opens gamegui.controllers;
}