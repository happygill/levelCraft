# LevelCraft

**LevelCraft** is a Minecraft mod that adds a unique progression system to crafting, where unlocking recipes requires *
*experience points (XP)**. With a new **Research Table**, players can unlock crafting recipes by spending their XP,
making crafting more strategic and rewarding.

## Features

- **Unlock Recipes with XP**:
    - Spend your XP to unlock important recipes, from basic tools to advanced items.
    - Example: Unlock shears at 10 levels, an anvil at 20 levels, or modded items at 30 levels.

- **Customizable Progression**:
    - Modpack creators can adjust which recipes require XP to unlock and set specific level requirements.

- **Research Table**:
    - Access the **Research Table** to view and unlock locked recipes as you level up.

- **Dynamic Crafting System**:
    - Adds an element of strategy to crafting, where progress is tied to experience.

## Installation

1. Download the latest release of **LevelCraft** from the [Releases](https://github.com/happygill/levelcraft/releases)
   page.
2. Install Minecraft Forge (or the appropriate mod loader for your version of Minecraft).
3. Place the downloaded `.jar` file in your Minecraft `mods` folder.
4. Launch Minecraft and enjoy a more strategic crafting experience!

## Configuration

For modpack authors, the configuration file allows you to customize the XP levels required to unlock specific recipes.
Simply adjust the values in the config file to suit your modpack's progression needs.

## Example Configuration

```json
{
  "recipes": {
    "minecraft:shears": 10,
    "minecraft:anvil": 20,
    "modded:craftingstation": 30
  }
}
```

## How to Use

- Gather XP by defeating mobs, mining, or completing tasks.
- Interact with the Research Table to view your locked recipes.
- Spend XP to unlock new crafting recipes and progress through the game!

## Contributing

Contributions are welcome! If you'd like to improve the mod or fix any bugs, feel free to fork the repository and submit
a pull request.

## License

This mod is licensed under the MIT License.
