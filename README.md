# 🌱 Plants vs. Zombies — JavaFX Tower Defense Game

A Java-based **Tower Defense / Strategy Game** inspired by *Plants vs. Zombies*, developed as a university software engineering project.

The project focuses on applying **Object-Oriented Programming, MVC architecture, multithreading, clean code principles, and event-driven GUI development** using JavaFX.

---

## 📌 Project Overview

The game simulates a lane-based tower defense system in which the player must defend a house against continuous waves of zombies.

The player manages a limited resource (Sun), selects and places defensive plants, and strategically combines different plant abilities to prevent zombies from reaching the player's base.

The game includes:

* Multiple plant types with different behaviors and abilities
* Multiple zombie types with different attributes
* Resource management through Sun collection
* Wave-based enemy spawning
* Day and Night game modes
* Collision detection between plants, projectiles, and zombies
* Health and damage systems
* Game state management
* Real-time animations and movement
* Concurrent game processes using Java threads
* Interactive JavaFX-based GUI

---

## 🏗️ Architecture

The application follows the **Model–View–Controller (MVC)** architectural pattern to separate game logic, application state, and presentation.

```text
                ┌──────────────────────┐
                │       Controller     │
                │                      │
                │ User Input           │
                │ Game Events          │
                │ Game Flow            │
                └──────────┬───────────┘
                           │
                           ▼
┌─────────────────┐   ┌─────────────────┐
│      View       │◄──│      Model      │
│                 │   │                 │
│ JavaFX UI       │   │ Game State      │
│ Animations      │   │ Plants          │
│ Game Board      │   │ Zombies         │
│ Components      │   │ Resources       │
└─────────────────┘   │ Game Logic      │
                      └─────────────────┘
```

### Model

Responsible for the core game state and business logic.

Examples include:

* Plants
* Zombies
* Projectiles
* Game board
* Player resources
* Health and damage
* Game levels
* Game state

### View

Implemented using **JavaFX** and responsible for rendering the game state and providing the graphical interface.

### Controller

Handles user interactions and coordinates communication between the View and Model.

Examples include:

* Plant selection
* Plant placement
* Game actions
* Menu interactions
* Level transitions
* User input handling

---

## ⚙️ Core Technical Concepts

### Object-Oriented Programming

The project heavily utilizes OOP principles to model game entities and their behavior.

Key concepts include:

* Encapsulation
* Inheritance
* Polymorphism
* Abstraction
* Interfaces
* Composition

Game entities are modeled as independent objects with clearly defined responsibilities.

For example:

```text
GameEntity
├── Plant
│   ├── Sunflower
│   ├── Peashooter
│   └── WallNut
│
└── Zombie
    ├── NormalZombie
    ├── FastZombie
    └── TankZombie
```

This structure allows new plant and zombie types to be added without significantly modifying existing game logic.

---

## 🧵 Multithreading

The game uses multiple concurrent execution flows to handle independent real-time operations.

Examples include:

* Zombie movement
* Projectile movement
* Enemy spawning
* Plant actions
* Resource generation
* Game timers
* Animation-related tasks

Separating these operations allows the game to maintain continuous activity while remaining responsive to user interactions.

Thread synchronization and shared game state management are considered when multiple game components interact with the same resources.

---

## 🎮 Game Mechanics

### Resource Management

Players collect **Sun** resources and use them to place plants.

Each plant has:

* Resource cost
* Health
* Attack behavior
* Attack speed
* Special abilities

The player must manage resources efficiently to survive increasingly difficult waves.

### Zombie System

Zombies enter the game from the right side of the map and move toward the player's base.

Different zombie types can have different:

* Health
* Movement speed
* Damage
* Defensive capabilities
* Behaviors

### Combat System

The combat system involves interactions between:

```text
Plant
  │
  ▼
Projectile
  │
  ▼
Collision Detection
  │
  ▼
Zombie
  │
  ▼
Damage Calculation
  │
  ▼
Zombie State Update
```

---

## 🌞 Day & Night Modes

The game provides two major game modes:

### Day

The player primarily relies on Sun-producing plants and manages resources while defending against zombie waves.

### Night

The game introduces different gameplay conditions and challenges, requiring the player to adapt their strategy.

---

## 🖥️ User Interface

The graphical interface is implemented entirely using **JavaFX**.

The UI includes:

* Main menu
* Game board
* Plant selection interface
* Resource counter
* Health indicators
* Game state indicators
* Game-over screen
* Level progression
* Animations and visual feedback

---

## 🛠️ Technologies

| Technology         | Usage                                    |
| ------------------ | ---------------------------------------- |
| **Java**           | Core application and game logic          |
| **JavaFX**         | Graphical user interface                 |
| **OOP**            | Domain and game entity modeling          |
| **MVC**            | Application architecture                 |
| **Multithreading** | Concurrent game processes                |
| **Git**            | Version control                          |
| **GitHub**         | Source code management and collaboration |

---

## 📂 Project Structure

A simplified representation of the architecture:

```text
src/
├── model/
│   ├── plants/
│   ├── zombies/
│   ├── projectiles/
│   ├── game/
│   └── entities/
│
├── view/
│   ├── screens/
│   ├── components/
│   └── animations/
│
├── controller/
│   ├── game/
│   ├── menu/
│   └── input/
│
└── resources/
    ├── images/
    ├── sounds/
    └── styles/
```

---

## 🔑 Software Engineering Principles

The project was developed with emphasis on:

* **Separation of concerns**
* **Single Responsibility Principle**
* **Low coupling**
* **High cohesion**
* **Code reusability**
* **Extensibility**
* **Encapsulation**
* **Clean Code**

The architecture was designed so that new plants, zombies, levels, and game mechanics can be introduced without requiring major changes to unrelated components.



This project was originally developed as a university project.
Refer to the repository license for details regarding modification and redistribution.
