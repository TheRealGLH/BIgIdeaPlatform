module PlatformGameShared {
    requires java.logging;
    exports PlatformGameShared.Points;
    exports PlatformGameShared.Enums;
    exports PlatformGameShared.Interfaces;
    opens PlatformGameShared.Messages.Client;
    opens PlatformGameShared.Messages.Response;
    opens PlatformGameShared.Points;
    opens PlatformGameShared.Enums;
    exports PlatformGameShared.Messages.Client;
    exports PlatformGameShared.Messages.Response;
    exports PlatformGameShared;
}