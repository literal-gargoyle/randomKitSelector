# Random Kit Selector
## Please Keep in mind that this plugin is still being actively worked on.
RandomKitSelector or RKS is a simple plugin that allows kits to be given randomly from a copied inventory, that gets saved in the config.yml.

## Features (Some are WIP)
- **Custom Kit Creation**: OPed users can create kits by copying their current inventory, preserving item positions.
- **Random Kit Assignment**: Kits are randomly assigned to players on: (WIP!!! Currenty using /rks give for testing)
  - Spawn
  - Death
  - First Join
- **Easy-to-Use Commands**: All configuration is done via simple commands.
- **Lightweight and Efficient**: Designed to integrate seamlessly into your server.

## Command, and Yes, there is only ONE command!

| Command           | Description                                          | Permission             |
|-------------------|------------------------------------------------------|------------------------|
| `/RKS create <name>` | Creates a new kit using the OPed player's inventory.  | `randomkitselector.createkit`  |
| `/RKS list`        | Lists all available kits.                           | `randomkitselector.listkits`   |
| `/RKS delete <name>`| Deletes an existing kit, Based on name. Forgot the name? Simply use /RKS L to find all kits.                            | `randomkitselector.deletekit`  |
| `/rks give <player_name>` | Gives a random kit to a specified player WIP | `WIP`|

## Installation

1. Download the plugin JAR file from the [Releases](/jars) page.
2. Place the JAR file in your server's `plugins` folder.
3. Restart or reload your server.
4. Ensure you have the required permissions set up for OPed players.

## Configuration

The plugin will automatically generate a configuration file (`kits.yml`) to save the kits for the plugin. 

Example `kits.yml`:
```yaml
kits:
  MyKit1:
    items:
      - {id: DIAMOND_SWORD, amount: 1, data: 0}
      - {id: IRON_SWORD, amount: 1, data: 0}
  MyKit2:
    items:
      - {id: BOW, amount: 1, data: 0}
      - {id: ARROW, amount: 5, data: 0}


