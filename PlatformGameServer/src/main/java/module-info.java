module PlatformGameServer {
    exports gameserver;
    requires PlatformGameShared;
    requires PlatformGameModels;
    requires gson;
    requires javax.websocket.api;
    requires org.eclipse.jetty.server;
    requires org.eclipse.jetty.servlet;
    requires org.eclipse.jetty.websocket.javax.websocket.server;
    requires org.eclipse.jetty.websocket.javax.websocket;
    requires java.sql;

}