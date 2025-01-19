package fr.babystaff.babymodel.ServerManager;

public class ServerManager {
    private ServerStatus serverStatus;
    private static ServerStatus currentStatus = ServerStatus.STARTING;

    public ServerManager() {
        this.serverStatus = ServerStatus.STARTING;
    }

    public void setServerStatus(ServerStatus serverStatus) {
        currentStatus = serverStatus;
        this.serverStatus = serverStatus;
    }

    public ServerStatus getServerStatus() {
        return currentStatus;
    }
}
