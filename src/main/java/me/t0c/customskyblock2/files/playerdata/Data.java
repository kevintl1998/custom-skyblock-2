package me.t0c.customskyblock2.files.playerdata;

public class Data {
    private RespawnLocation respawnLocation = new RespawnLocation();
    private Bonuses bonuses = Bonuses.newInstance(Bonuses.CreationReason.PLUGIN);
    private Stats stats = new Stats();
    private long lastNewIslandCreation = 0;
    private boolean keepItemsOnDeath = false;

    public Bonuses getBonuses() {
        return bonuses;
    }
    public RespawnLocation getRespawnLocation() {
        return respawnLocation;
    }
    public Stats getStats() {
        return stats;
    }
    public long getLastNewIslandCreation() { return lastNewIslandCreation; }
    public boolean isKeepItemsOnDeath() { return keepItemsOnDeath; }

    public void setBonuses(Bonuses bonuses) {
        this.bonuses = bonuses;
    }
    public void setRespawnLocation(RespawnLocation respawnLocation) {
        this.respawnLocation = respawnLocation;
    }
    public void setStats(Stats stats) {
        this.stats = stats;
    }
    public void setLastNewIslandCreation(long lastNewIslandCreation) { this.lastNewIslandCreation = lastNewIslandCreation; }
    public void setKeepItemsOnDeath(boolean keepItemsOnDeath) { this.keepItemsOnDeath = keepItemsOnDeath; }

    public void updateLastNewIslandCreation(long updatedLastNewIslandCreation) {
        this.lastNewIslandCreation = updatedLastNewIslandCreation;
    }

    public String toString() {
        if(bonuses != null && stats != null && respawnLocation != null) {
            return "Data { " + respawnLocation.toString() + " " + bonuses.toString() + " " + stats.toString() + " lastNewIslandCreation= " + lastNewIslandCreation + " }";
        } else {
            StringBuilder s = new StringBuilder();
            s.append("Data { ");
            if(respawnLocation == null) s.append("respawnLocation: null ");
            else s.append(respawnLocation.toString()).append(" ");

            if(bonuses == null) s.append("bonuses: null ");
            else s.append(bonuses.toString()).append(" ");

            if(stats == null) s.append("stats: null ");
            else s.append(stats.toString()).append(" ");

            s.append(" lastNewIslandCreation= ").append(lastNewIslandCreation).append(" }");

            return s.toString();
        }
    }
}
