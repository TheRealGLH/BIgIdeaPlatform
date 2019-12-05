module PlatformGameShared {
    exports PlatformGameShared.Points;
    exports PlatformGameShared.Enums;
    exports PlatformGameShared.Interfaces;
    opens PlatformGameShared.Messages.Client;
    opens PlatformGameShared.Messages.Response;
    exports PlatformGameShared.Messages.Client;
    exports PlatformGameShared.Messages.Response;
}