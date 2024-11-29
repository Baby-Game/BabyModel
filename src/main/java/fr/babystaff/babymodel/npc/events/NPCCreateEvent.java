package fr.babystaff.babymodel.npc.events;

import fr.babystaff.babymodel.npc.NPC;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class NPCCreateEvent extends Event {
    private NPC npc;

    public NPCCreateEvent(NPC npc) {
        this.npc = npc;
    }

    public NPC getNpc() {
        return npc;
    }

    @Override
    public HandlerList getHandlers() {
        return null;
    }
}
