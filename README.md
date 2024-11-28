# BabyModel 🖥️

BabyModel est une API légère et puissante conçue pour simplifier le développement de plugins Minecraft (1.8.8). Elle sert de base pour créer des plugins évolutifs, modulaires et facilement maintenables, avec une gestion simplifiée des bases de données, des actionBar en jeu et des hologrammes.

Cette API offre plusieurs gestionnaires qui vous permettent de gérer des fonctionnalités courantes dans vos plugins Minecraft, notamment la gestion des bases de données, l'affichage de l'ActionBar et la manipulation d'hologrammes. BabyModel est conçu pour être utilisé comme modèle pour la création de plugins Minecraft de manière rapide et efficace.

---

### Maven 🔧

Ajoutez la dépendance suivante dans votre fichier `pom.xml` :

[![Maven Badge](https://img.shields.io/maven-central/v/fr.babystaff/babymodel.svg)](https://maven-badges.herokuapp.com/maven-central/fr.babystaff/babymodel)

```xml
<dependency>
    <groupId>fr.babystaff</groupId>
    <artifactId>babymodel</artifactId>
    <version>1.0.0</version>
    <scope>compile</scope>
</dependency>
```

### Gradle ⚙️

Ajoutez cette ligne dans votre fichier `build.gradle` :

[![Gradle Badge](https://img.shields.io/badge/Gradle-1.0.0-blue.svg)](https://gradle.org/)

```gradle
dependencies {
    implementation 'fr.babystaff:babymodel:1.0.0'
}
```

---


## Contribuer à BabyModel 🤝

Si vous souhaitez contribuer à BabyModel, vous êtes les bienvenus ! Si vous trouvez des bugs, souhaitez proposer des améliorations, ou souhaitez ajouter des fonctionnalités supplémentaires, n'hésitez pas à ouvrir une **issue** ou à soumettre une **pull request**.

**Consignes pour contribuer :**
- Fork ce dépôt.
- Créez une branche pour votre fonctionnalité ou correction.
- Soumettez une pull request avec une description détaillée de votre modification.

---

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
DataBaseManager dbManager = new DataBaseManager();
DataBase dataBase = new DataBase("localhost", "3306", "mydatabase", "user", "password");
dbManager.openConnectionDataBase(dataBase);
dbManager.createTable(dataBase, "players", "id INT AUTO_INCREMENT, name VARCHAR(255), PRIMARY KEY(id)");
dbManager.writeToColumn(dataBase, "players", "name", "Player1");
```

### 2. **ActionBarManager** 🏷️

L'`ActionBarManager` permet d'afficher des messages dans l'ActionBar du joueur dans Minecraft. Il est conçu pour faciliter l'affichage de notifications temporaires ou dynamiques pendant le jeu.

**Principales fonctionnalités :**
- Affichage de messages dans l'ActionBar pour un joueur spécifique.
- Personnalisation des messages avec des couleurs et des styles.
- Affichage de messages dynamiques basés sur des événements ou des actions spécifiques.

**Exemple d'utilisation :**

```java
ActionBarManager actionBar = new ActionBarManager();
actionBar.sendActionBarMessage(player, "Bienvenue sur le serveur!", 5);
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
HologramManager hologramManager = new HologramManager();

// Créer un hologramme
Hologram hologram = hologramManager.createHologram(player.getLocation().add(0, 2, 0), "Bienvenue sur le serveur!");

// Ajouter une ligne à l'hologramme
hologram.addLine("Votre rang : " + player.getRank());

// Mettre à jour l'hologramme
hologram.setLine(1, "Vous avez " + player.getCoins() + " pièces.");

// Supprimer l'hologramme
hologram.remove();
```

### 4. World 🌍
Le ``WorldManager`` permet de créer et de gérer des mondes dans Minecraft. Il facilite la création et suppression de monde.

```JAVA
 WorldManager worldManager = new WorldManager();
        
 // Créer un monde normal
 World newWorld = worldManager.createWorld("MonNouveauMonde", WorldType.NORMAL);
 if (newWorld != null) {
    System.out.println("Le monde " + newWorld.getName() + " a été créé avec succès.");
 }

 // Supprimer un monde
 boolean worldDeleted = worldManager.removeWorld("MonNouveauMonde");
 if (worldDeleted) {
    System.out.println("Le monde a été supprimé avec succès.");
    } else {
        System.out.println("La suppression du monde a échoué.");
    }
```

### 5. DiscordBot 🤖
Le ``DiscordBotManager`` permet de crée et de modifier des bot Discords pour les intégrers au fonctionnalités en jeu, des fonctionnalités de base son intégré de ``JDA``

```JAVA
BotManager botManager = new BotManager();
        
// Créer et connecter un bot
DiscordBot myBot = new DiscordBot("bot_token");
botManager.connectBot(myBot);
        
// Ajouter une commande
CommandManager commandManager = new CommandManager();
commandManager.addCommand("!hello", (message) -> {
    System.out.println("Bonjour ! Commande exécutée.");
});

// Exécuter une commande
commandManager.executeCommand("!hello");
```

### 6. Arena 🌱
Le ``ArenaManager`` permet de créer et gérer des arènes et des joueurs dans ces arènes et monde

```JAVA
ArenaManager arenaManager = new ArenaManager();

Location location = new Location(Bukkit.getWorld("world"), 0.5, 100, 0.5);
Arena arena = new Arena("Arena", Bukkit.getWorld("world"), location);
arena.addPlayerInArena(player);
```

### 7. Team 🚩
Le ``TeamManager`` est une class qui permet de crée et de géré des équipes et les joueurs que les teams contiennent, les équipes sont ranger par couleur pour permettre de gérer des ``Game``
```JAVA
// Création d'une équipe
Team redTeam = new Team(ChatColor.RED, "red", "Red Team", 5);

// Ajouter des joueurs (exemple avec des objets Player fictifs)
Player player1 = /* récupérer un joueur */;
Player player2 = /* récupérer un autre joueur */;

if (redTeam.addPlayer(player1)) {
    System.out.println(player1.getName() + " a rejoint l'équipe " + redTeam.getName());
    } else {
        System.out.println("Impossible d'ajouter " + player1.getName());
    }

    if (redTeam.addPlayer(player2)) {
        System.out.println(player2.getName() + " a rejoint l'équipe " + redTeam.getName());
    }

// Afficher les informations de l'équipe
System.out.println(redTeam);

// Supprimer un joueur
redTeam.removePlayer(player1);

// Vider l'équipe
redTeam.clearTeam();

// Vérifier l'état de l'équipe
System.out.println("L'équipe est vide ? " + redTeam.getPlayers().isEmpty());
```

### 8. Game 🎮
Le ``GameManager`` permet de crée et de gérer des parties personnalisables, inclu la gestion de joueur et d'équipe

```JAVA
// Création d'une parti
Game game = new Game("GameID", "Game Name", arena, location);

GameManager gameManager = new GameManager();
gameManager.createGame(game);

// Ajout d'un joueur
gameManager.addPlayer(player, game);

// Suppression d'un joueur
gameManager.removePlayer(player, game);
```