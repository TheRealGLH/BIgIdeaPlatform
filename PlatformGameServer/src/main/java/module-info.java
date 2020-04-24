module PlatformGameServer {
    exports gameserver;
    requires PlatformGameShared;
    requires PlatformGameModels;
    requires gson;
    requires javax.websocket.api;
    requires org.eclipse.jetty.server;
    requires org.eclipse.jetty.servlet;
    requires org.eclipse.jetty.servlets;
    requires org.eclipse.jetty.websocket.javax.websocket.server;
    requires org.eclipse.jetty.websocket.javax.websocket;
    requires java.sql;
    requires org.apache.httpcomponents.httpcore;
    requires org.apache.httpcomponents.httpclient;
    requires javax.servlet.api;
    requires PlatformLoginShared;
}