package models.classes.objects;

public interface IPlayerEventListener {

    public void onShootEvent(Player firer);

    public void onDeathEvent(Player deadPlayer);



}
