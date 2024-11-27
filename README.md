# BabyModel 🖥️

BabyModel est une API légère et puissante conçue pour simplifier le développement de plugins Minecraft (1.8.8). Elle sert de base pour créer des plugins évolutifs, modulaires et facilement maintenables, avec une gestion simplifiée des bases de données, des actionBar en jeu et des hologrammes.

Cette API offre plusieurs gestionnaires qui vous permettent de gérer des fonctionnalités courantes dans vos plugins Minecraft, notamment la gestion des bases de données, l'affichage de l'ActionBar et la manipulation d'hologrammes. BabyModel est conçu pour être utilisé comme modèle pour la création de plugins Minecraft de manière rapide et efficace.


---

## Comment modifier BabyModel ⚙️

BabyModel peut être intégré dans n'importe quel plugin Minecraft en tant que bibliothèque principale ou en tant que modèle pour des projets plus complexes. Il suffit de copier les classes principales dans votre projet, puis de commencer à utiliser les gestionnaires pour faciliter le développement de vos plugins.

**Étapes d'intégration :**

1. Clonez ou téléchargez ce dépôt.
2. Ajoutez les classes de `BabyModel` à votre projet.
3. Utilisez les gestionnaires dans vos plugins comme montré dans les exemples ci-dessus.
4. Personnalisez le code pour répondre aux besoins spécifiques de votre plugin.

---

## Contribuer 🤝

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
