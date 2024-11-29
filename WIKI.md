## Fonctionnalités 🌟

### 1. **DatabaseManager** 💾

Le `DatabaseManager` permet une gestion simplifiée des connexions à une base de données MySQL/MariaDB. Il inclut des fonctionnalités pour ouvrir et fermer des connexions à des bases de données, exécuter des requêtes SQL, et vérifier si des tables ou des colonnes existent.

**Principales fonctionnalités :**
- Connexion à une base de données via JDBC.
- Vérification de l'existence des tables et des colonnes.
- Création et suppression de tables et de colonnes.
- Exécution de requêtes SQL génériques.
- Insertion de données dans une colonne spécifique.

Exemple d'utilisation :

```java
import fr.babystaff.babymodel.dataBase.DataBase;
import fr.babystaff.babymodel.dataBase.DataBaseManager;

DataBaseManager dbManager = new DataBaseManager();
DataBase dataBase = new DataBase("localhost", "3306", "mydatabase", "user", "password");
dbManager.openConnectionDataBase(dataBase);
dbManager.createTable(dataBase, "players","id INT AUTO_INCREMENT, name VARCHAR(255), PRIMARY KEY(id)");
dbManager.writeToColumn(dataBase, "players","name","Player1");
```

### 2. **ActionBarManager** 🏷️

L'`ActionBarManager` permet d'afficher des messages dans l'ActionBar du joueur dans Minecraft. Il est conçu pour faciliter l'affichage de notifications temporaires ou dynamiques pendant le jeu.

**Principales fonctionnalités :**
- Affichage de messages dans l'ActionBar pour un joueur spécifique.
- Personnalisation des messages avec des couleurs et des styles.
- Affichage de messages dynamiques basés sur des événements ou des actions spécifiques.

**Exemple d'utilisation :**

```java
import fr.babystaff.babymodel.actionBar.ActionBarManager;

ActionBarManager actionBar = new ActionBarManager();
actionBar.sendActionBarMessage(player, "Bienvenue sur le serveur!",5);
```

### 3. **HologramManager** ✨

Le `HologramManager` permet de créer et de gérer des hologrammes dans Minecraft. Il facilite l'affichage d'informations flottantes au-dessus du sol, idéal pour des messages interactifs, des affichages de statistiques ou d'autres éléments visuels.

**Principales fonctionnalités :**
- Création d'hologrammes avec plusieurs lignes de texte.
- Mise à jour dynamique du texte d'un hologramme.
- Suppression d'hologrammes.
- Gestion des positions des hologrammes.
- Support pour les hologrammes interactifs (cliquables).

Exemple d'utilisation :

```java
import fr.babystaff.babymodel.hologram.Hologram;
import fr.babystaff.babymodel.hologram.HologramManager;

HologramManager hologramManager = new HologramManager();

// Créer un hologramme
Hologram hologram = hologramManager.createHologram(player.getLocation().add(0, 2, 0), "Bienvenue sur le serveur!");

// Ajouter une ligne à l'hologramme
hologram.

addLine("Votre rang : "+player.getRank());

// Mettre à jour l'hologramme
        hologram.

setLine(1,"Vous avez "+player.getCoins() +" pièces.");

// Supprimer l'hologramme
        hologram.

remove();
```

### 4. World 🌍
Le ``WorldManager`` permet de créer et de gérer des mondes dans Minecraft. Il facilite la création et suppression de monde.

```JAVA
import fr.babystaff.babymodel.world.World;
import fr.babystaff.babymodel.world.WorldManager;

WorldManager worldManager = new WorldManager();

// Créer un monde normal
World newWorld = worldManager.createWorld("MonNouveauMonde", WorldType.NORMAL);
 if(newWorld !=null){
        System.out.

println("Le monde "+newWorld.getName() +" a été créé avec succès.");
        }

// Supprimer un monde
boolean worldDeleted = worldManager.removeWorld("MonNouveauMonde");
 if(worldDeleted){
        System.out.

println("Le monde a été supprimé avec succès.");
    }else{
            System.out.

println("La suppression du monde a échoué.");
    }
```

### 5. DiscordBot 🤖
Le ``DiscordBotManager`` permet de crée et de modifier des bot Discords pour les intégrers au fonctionnalités en jeu, des fonctionnalités de base son intégré de ``JDA``

```JAVA
import fr.babystaff.babymodel.discord.bot.DiscordBot;
import fr.babystaff.babymodel.discord.bot.DiscordBotManager;

DiscordBotManager botManager = new BotManager();

// Créer et connecter un bot
DiscordBot myBot = new DiscordBot("bot_token");
botManager.

connectBot(myBot);

// Ajouter une commande
CommandManager commandManager = new CommandManager();
commandManager.

addCommand("!hello",(message) ->{
        System.out.

println("Bonjour ! Commande exécutée.");
});

// Exécuter une commande
        commandManager.

executeCommand("!hello");
```

### 6. Arena 🌱
Le ``ArenaManager`` permet de créer et gérer des arènes et des joueurs dans ces arènes et monde

```JAVA
import fr.babystaff.babymodel.arena.Arena;
import fr.babystaff.babymodel.arena.ArenaManager;

ArenaManager arenaManager = new ArenaManager();

Location location = new Location(Bukkit.getWorld("world"), 0.5, 100, 0.5);
Arena arena = new Arena("Arena", Bukkit.getWorld("world"), location);
arena.

addPlayerInArena(player);
```

### 7. Team 🚩
Le ``TeamManager`` est une class qui permet de crée et de géré des équipes et les joueurs que les teams contiennent, les équipes sont ranger par couleur pour permettre de gérer des ``Game``

```JAVA
import fr.babystaff.babymodel.team.Team;

// Création d'une équipe
Team redTeam = new Team(ChatColor.RED, "red", "Red Team", 5);

// Ajouter des joueurs (exemple avec des objets Player fictifs)
Player player1 = /* récupérer un joueur */;
Player player2 = /* récupérer un autre joueur */;

if(redTeam.

addPlayer(player1)){
        System.out.println(player1.getName() +" a rejoint l'équipe "+redTeam.getName());
        } else {
        System.out.println("Impossible d'ajouter " + player1.getName());
        }

        if(redTeam.addPlayer(player2)){
        System.out.println(player2.getName() +" a rejoint l'équipe "+redTeam.getName());
        }

// Afficher les informations de l'équipe
        System.out.println(redTeam);

// Supprimer un joueur
redTeam.removePlayer(player1);

// Vider l'équipe
redTeam.clearTeam();

// Vérifier l'état de l'équipe
System.out.println("L'équipe est vide ? "+redTeam.getPlayers().isEmpty());
```

### 8. Game 🎮
Le ``GameManager`` permet de crée et de gérer des parties personnalisables, inclu la gestion de joueur et d'équipe

```JAVA
import fr.babystaff.babymodel.game.Game;
import fr.babystaff.babymodel.game.GameManager;

// Création d'une parti
Game game = new Game("GameID", "Game Name", arena, location);

GameManager gameManager = new GameManager();
gameManager.

createGame(game);

// Ajout d'un joueur
gameManager.

addPlayer(player, game);

// Suppression d'un joueur
gameManager.

removePlayer(player, game);
```

### 9. NPC 🤖
Il est aussi possible de crée des NPC et ```NPCManager```, il est possible de crée modifier et gérer des NPC dans le monde et leur ajouter des pratiques.

```JAVA
import fr.babystaff.babymodel.npc.NPC;
import fr.babystaff.babymodel.npc.NPCManager;

// initialisé NPC Manager
NPCManager npcManager = new NPCManager();

// crée le npc
NPC npc = new NPC(location, "Louis_292", skinTexute, skinSigniature, hologramManager);

// le supprime
npc.delete();
```

### 10. Langue 📨
On peut égualement utiliser des messages traductions, des messages qui change en fonction de la 'langue' d'un joueur, grace à des messages garder dans des fichiers 'resources', tous ça grace à ``LanguageManager``

```JAVA
import fr.babystaff.babymodel.langue.LanguageManager;

// initialisé Language Manager
LanguageManager languageManager = new LanguageManager(getDataFolder());

// Récupère le message tradui du joueur
String translation = languageManager.translate(player, "your_key");
player.sendMessage(translation);
```