# Random Kit Selector
## Please Keep in mind that this plugin is still being actively worked on.
RandomKitSelector or RKS is a simple plugin that allows kits to be given randomly from a copied inventory, that gets saved in the config.yml.

## Features
- **Custom Kit Creation**: OPed users can create kits by copying their current inventory, preserving item positions.
- **Random Kit Assignment**: Kits are randomly assigned to players on:
  - Spawn
  - Death
  - First Join
- **Easy-to-Use Commands**: All configuration is done via simple commands.
- **Lightweight and Efficient**: Designed to integrate seamlessly into your server.

## Command, and Yes, there is only ONE command!

| Command           | Description                                          | Permission             |
|-------------------|------------------------------------------------------|------------------------|
| `/RKS C <name>` | Creates a new kit using the OPed player's inventory.  | `randomkitselector.createkit`  |
| `/RKS L`        | Lists all available kits.                           | `randomkitselector.listkits`   |
| `/RKS D <name>`| Deletes an existing kit, Based on name. Forgot the name? Simply use /RKS L to find all kits.                            | `randomkitselector.deletekit`  |

## Installation

1. Download the plugin JAR file from the [Releases](#) page.
2. Place the JAR file in your server's `plugins` folder.
3. Restart or reload your server.
4. Ensure you have the required permissions set up for OPed players.

## Configuration

The plugin will automatically generate a configuration file (`config.yml`) to save the kits for the plugin. 

Example `config.yml`:
```yaml
kits:
  # Example Kit
  StarterKit:
    items:
      - 'DIAMOND_SWORD'
      - 'APPLE'
    armor:
      - 'DIAMOND_HELMET'
      - 'DIAMOND_CHESTPLATE'
      - 'DIAMOND_LEGGINGS'
      - 'DIAMOND_BOOTS'

