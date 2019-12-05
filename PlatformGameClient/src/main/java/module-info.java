module PlatformGameClient {
    requires javafx.graphics;
    requires javafx.fxml;
    requires javafx.controls;
    requires PlatformGameShared;
    requires java.desktop;
    requires PlatformGameServer;
    requires gson;
    requires javax.websocket.api;
    exports gamegui;
    exports gamegui.controllers;
    exports gameclient;
    opens gamegui.controllers;
}