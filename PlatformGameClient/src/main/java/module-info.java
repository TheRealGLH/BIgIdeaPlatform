module PlatformGameClient {
    requires javafx.graphics;
    requires javafx.fxml;
    requires javafx.controls;
    requires PlatformGameShared;
    requires java.desktop;
    requires gson;
    requires javax.websocket.client.api;
    requires java.sql;
    exports gamegui;
    exports gamegui.controllers;
    exports gameclient;
    opens gamegui.controllers;
}