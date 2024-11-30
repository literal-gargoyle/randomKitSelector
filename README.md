# Random Kit Selector

A Minecraft plugin that allows OPed users to create custom kits and randomly assign them to players upon spawning, death, or first joining. The plugin is fully configurable and provides a seamless way to manage kits.

## Features
- **Custom Kit Creation**: OPed users can create kits by copying their current inventory, preserving item positions.
- **Random Kit Assignment**: Kits are randomly assigned to players on:
  - Spawn
  - Death
  - First Join
- **Easy-to-Use Commands**: All configuration is done via simple commands.
- **Lightweight and Efficient**: Designed to integrate seamlessly into your server.

## Commands

| Command           | Description                                          | Permission             |
|-------------------|------------------------------------------------------|------------------------|
| `/createkit <name>` | Creates a new kit using the OP player's inventory.  | `randomkit.createkit`  |
| `/listkits`        | Lists all available kits.                           | `randomkit.listkits`   |
| `/deletekit <name>`| Deletes an existing kit.                            | `randomkit.deletekit`  |

## Installation

1. Download the plugin JAR file from the [Releases](#) page.
2. Place the JAR file in your server's `plugins` folder.
3. Restart or reload your server.
4. Ensure you have the required permissions set up for OPed players.

## Configuration

The plugin will automatically generate a configuration file (`config.yml`) to control the behavior of the plugin. Key configuration options include:

- **Spawn Behavior**: Toggle random kit assignment on spawn, death, or first join.
- **Kit Limits**: Set limits on the number of kits that can be created.

Example `config.yml`:
```yaml
random-kits:
  on-spawn: true
  on-death: true
  on-first-join: true
