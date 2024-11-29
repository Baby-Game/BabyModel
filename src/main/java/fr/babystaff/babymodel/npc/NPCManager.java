package fr.babystaff.babymodel.npc;

import java.util.HashMap;

public class NPCManager {
    private final HashMap<String, NPC> npcHashMap = new HashMap<>();

    public boolean createNPC(NPC npc) {
        if (npcHashMap.containsKey(npc.getName())) {
            return false;
        }
        npcHashMap.put(npc.getName(), npc);
        return true;
    }

    public boolean removeNPC(String npcName) {
        NPC npc = npcHashMap.remove(npcName);
        if (npc != null) {
            npc.delete();
            return true;
        }
        return false;
    }

    public NPC getNPC(String npcName) {
        return npcHashMap.get(npcName);
    }

    public boolean npcExists(String npcName) {
        return npcHashMap.containsKey(npcName);
    }

    public int getNPCCount() {
        return npcHashMap.size();
    }
}
