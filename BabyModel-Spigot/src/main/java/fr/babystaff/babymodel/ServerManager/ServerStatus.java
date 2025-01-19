package fr.babystaff.babymodel.ServerManager;

public enum ServerStatus {
    STARTING("Starting"),
    START("Start"),
    STOPPING("Stopping"),
    STOP("Stop");

    private final String name;

    ServerStatus(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
