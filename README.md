# atg-api

Java API for "Automation: The Game," a deck-building card game used in COSI 103A at Brandeis University.

This is the **v1** branch (1.x releases). For 2.x releases with event observation support, see the [v2 branch](https://github.com/brandeis-cosi-103a/atg-api/tree/v2).

## Installation

**Maven:**
```xml
<dependency>
  <groupId>io.github.brandeis-cosi-103a</groupId>
  <artifactId>atg-api</artifactId>
  <version>1.5.2</version>
</dependency>
```

**Gradle:**
```groovy
implementation 'io.github.brandeis-cosi-103a:atg-api:1.5.2'
```

## Documentation

- [Javadoc (1.5.2)](https://www.javadoc.io/doc/io.github.brandeis-cosi-103a/atg-api/1.5.2/index.html)
- [Maven Central](https://central.sonatype.com/artifact/io.github.brandeis-cosi-103a/atg-api)

## Overview

The API defines contracts for building game engines and AI players:

- **`Engine`** — Orchestrates gameplay: setup, turn loop, and termination
- **`Player`** — Makes decisions when prompted by the engine
- **`GameState`** — Immutable snapshot of game state passed to players
- **`Decision`** — Player choices: buy cards, play cards, trash, gain, etc.
- **`Card`** — Card types, categories (Money, Victory, Action), costs, and effects

## v1 vs v2

v1 is the simpler API with 3 action cards (Refactor, Code Review, Evergreen Test). Use v2 if you need:
- 15 action cards for more varied gameplay
- `GameObserver` interface for tracking game events
- Event types like `GameStartEvent`, `GameEndEvent`, `GainCardEvent`, etc.
- Additional decisions: `ChooseEffectDecision`, `DiscardCardDecision`

## License

MIT
