# atg-api

Java API for "Automation: The Game," a deck-building card game used in COSI 103A at Brandeis University.

This is the **v2** branch (2.x releases). For 1.x releases, see the [v1 branch](https://github.com/brandeis-cosi-103a/atg-api/tree/v1).

## Installation

**Maven:**
```xml
<dependency>
  <groupId>io.github.brandeis-cosi-103a</groupId>
  <artifactId>atg-api</artifactId>
  <version>2.5.0</version>
</dependency>
```

**Gradle:**
```groovy
implementation 'io.github.brandeis-cosi-103a:atg-api:2.5.0'
```

## Documentation

- [Javadoc (latest)](https://www.javadoc.io/doc/io.github.brandeis-cosi-103a/atg-api/latest/index.html)
- [Maven Central](https://central.sonatype.com/artifact/io.github.brandeis-cosi-103a/atg-api)

## Overview

The API defines contracts for building game engines and AI players:

- **`Engine`** — Orchestrates gameplay: setup, turn loop, and termination
- **`Player`** — Makes decisions when prompted by the engine
- **`GameObserver`** — Receives event notifications as the game progresses
- **`GameState`** — Immutable snapshot of game state passed to players
- **`Decision`** — Player choices: buy cards, play cards, trash, discard, etc.
- **`Event`** — Game events: card gains, turns ending, game start/end, etc.
- **`Card`** — Card types, categories (Money, Victory, Action), costs, and effects

## v2 vs v1

v2 expands the game significantly:
- **15 action cards** (vs 3 in v1) for more varied gameplay
- `GameObserver` interface and `Engine.setObserver()` for event observation
- Event types: `GameStartEvent`, `GameEndEvent`, `GainCardEvent`, `TrashCardEvent`, `PlayCardEvent`, `EndTurnEvent`, `DiscardCardEvent`
- Additional decisions: `ChooseEffectDecision`, `DiscardCardDecision`

Use v1 for simpler engine implementations with fewer cards.

## License

MIT
