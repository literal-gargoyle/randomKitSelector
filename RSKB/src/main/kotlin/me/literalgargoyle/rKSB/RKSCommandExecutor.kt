package me.literalgargoyle.rksb

import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

class RKSCommandExecutor(private val plugin: RKSB) : CommandExecutor {

    private val kitsFile = plugin.getKitsFile()

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (command.name.equals("rks", ignoreCase = true)) {
            when {
                args.isEmpty() -> {
                    // No arguments, assign kit to the sender (the player running the command)
                    if (sender is Player) {
                        assignRandomKit(sender)
                        sender.sendMessage("${ChatColor.GREEN}You have been assigned a random kit!")
                    } else {
                        sender.sendMessage("${ChatColor.RED}Only players can use this command.")
                    }
                }
                args.size == 2 && args[0].equals("give", ignoreCase = true) -> {
                    // Player specified, assign kit to the specified player
                    val playerName = args[1]
                    val player = Bukkit.getPlayer(playerName)
                    if (player != null && player.isOnline) {
                        assignRandomKit(player)
                        sender.sendMessage("${ChatColor.GREEN}Assigned a random kit to player ${player.name}.")
                    } else {
                        sender.sendMessage("${ChatColor.RED}Player '$playerName' not found or not online.")
                    }
                }
                args.size == 2 && args[0].equals("create", ignoreCase = true) -> {
                    // Create a new kit
                    if (sender is Player) {
                        createKit(sender, args[1])
                    } else {
                        sender.sendMessage("${ChatColor.RED}Only players can create kits.")
                    }
                }
                args.size == 2 && args[0].equals("delete", ignoreCase = true) -> {
                    // Delete a kit
                    deleteKit(sender, args[1])
                }
                args.size == 1 && args[0].equals("list", ignoreCase = true) -> {
                    // List all available kits
                    listKits(sender)
                }
                else -> {
                    sender.sendMessage("${ChatColor.RED}Usage: /rks (give <playername> | create <kitname> | delete <kitname> | list)")
                }
            }
            return true
        }
        return false
    }

    // Method to assign a random kit to a player
    private fun assignRandomKit(player: Player) {
        val kits = listKitsFromFile()
        if (kits.isEmpty()) {
            player.sendMessage("${ChatColor.RED}No kits available in the configuration!")
            return
        }
        val randomKitName = kits.random()
        plugin.logger.info("Assigned random kit '$randomKitName' to player '${player.name}'")
        val items = kitsFile.getStringList("kits.$randomKitName.items")
        val armor = kitsFile.getStringList("kits.$randomKitName.armor")

        player.inventory.clear()
        // Clear the armor slots individually
        player.inventory.armorContents.indices.forEach { index ->
            player.inventory.armorContents[index] = null
        }

        items.forEach { item ->
            val material = org.bukkit.Material.getMaterial(item)
            if (material != null) {
                player.inventory.addItem(org.bukkit.inventory.ItemStack(material))
            }
        }

        armor.forEachIndexed { index, item ->
            val material = org.bukkit.Material.getMaterial(item)
            if (material != null) {
                player.inventory.armorContents[index] = org.bukkit.inventory.ItemStack(material)
            }
        }
        player.sendMessage("${ChatColor.GREEN}You have been assigned the kit: $randomKitName")
    }

    // Method to create a new kit
    private fun createKit(sender: CommandSender, kitName: String) {
        val player = sender as Player
        if (kitsFile.contains("kits.$kitName")) {
            player.sendMessage("${ChatColor.RED}A kit with that name already exists!")
            return
        }

        kitsFile.createSection("kits.$kitName")
        kitsFile.set("kits.$kitName.items", listOf("STONE_SWORD", "BOW", "ARROW")) // Example items
        kitsFile.set("kits.$kitName.armor", listOf("IRON_HELMET", "IRON_CHESTPLATE", "IRON_LEGGINGS", "IRON_BOOTS")) // Example armor

        saveKitToFile(kitName, listOf("STONE_SWORD", "BOW", "ARROW").mapNotNull { org.bukkit.Material.getMaterial(it)?.let { material -> org.bukkit.inventory.ItemStack(material) } }.toTypedArray(),
            listOf("IRON_HELMET", "IRON_CHESTPLATE", "IRON_LEGGINGS", "IRON_BOOTS").mapNotNull { org.bukkit.Material.getMaterial(it)?.let { material -> org.bukkit.inventory.ItemStack(material) } }.toTypedArray())

        player.sendMessage("${ChatColor.GREEN}Kit '$kitName' has been created!")
    }

    // Method to delete an existing kit
    private fun deleteKit(sender: CommandSender, kitName: String) {
        val player = sender as Player
        if (!kitsFile.contains("kits.$kitName")) {
            player.sendMessage("${ChatColor.RED}No kit found with the name '$kitName'.")
            return
        }

        deleteKitFromFile(kitName)
        player.sendMessage("${ChatColor.GREEN}Kit '$kitName' has been deleted!")
    }

    // Method to list all available kits
    private fun listKits(sender: CommandSender) {
        val kits = listKitsFromFile()
        if (kits.isEmpty()) {
            sender.sendMessage("${ChatColor.RED}No kits available.")
            return
        }

        sender.sendMessage("${ChatColor.GREEN}Available Kits:")
        kits.forEach {
            sender.sendMessage("${ChatColor.YELLOW}- $it")
        }
    }

    // Function to save the kit to the file
    private fun saveKitToFile(kitName: String, items: Array<out ItemStack>, armor: Array<out ItemStack>) {
        plugin.logger.info("Saving kit '$kitName' to kits.yml")
        val kitPath = "kits.$kitName"
        kitsFile.set("$kitPath.items", items.map { it.type.name })
        kitsFile.set("$kitPath.armor", armor.map { it.type.name })
        plugin.saveConfig() // Save the changes to the file
    }

    // Function to delete the kit from the file
    private fun deleteKitFromFile(kitName: String) {
        plugin.logger.info("Deleting kit '$kitName' from kits.yml")
        kitsFile.set("kits.$kitName", null)
        plugin.saveConfig() // Save the changes to the file
    }

    // Function to list kits from the file
    private fun listKitsFromFile(): List<String> {
        plugin.logger.info("Listing all kits from kits.yml")
        return kitsFile.getConfigurationSection("kits")?.getKeys(false)?.toList() ?: emptyList()
    }
}
