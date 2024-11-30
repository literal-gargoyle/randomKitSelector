@file:Suppress("DEPRECATION")

package me.literalgargoyle.randomkitselector

import org.bukkit.ChatColor
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class CommandHandler(private val plugin: RandomKitSelector) : CommandExecutor {

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        plugin.logger.info("RKS Command received: ${command.name}, Args: ${args.joinToString()}")

        if (args.isEmpty()) {
            sender.sendMessage("${ChatColor.RED}Usage: /RKS <create|delete|list>")
            return false
        }

        when (args[0].uppercase()) {
            "C" -> {
                plugin.logger.info("RKS Command 'C' executed")

                if (args.size < 2 || sender !is Player) {
                    sender.sendMessage("${ChatColor.RED}Usage: /RKS C <kitName>")
                    return false
                }

                val player = sender
                val kitName = args[1]

                // Create a kit with the player's current inventory and armor
                val items = player.inventory.contents.filterNotNull().toTypedArray()
                val armor = player.inventory.armorContents.filterNotNull().toTypedArray()

                plugin.kitManager.createKit(kitName, items, armor)
                sender.sendMessage("${ChatColor.GREEN}Kit '$kitName' created successfully!")
            }

            "D" -> {
                plugin.logger.info("RKS Command 'D' executed")

                if (args.size < 2) {
                    sender.sendMessage("${ChatColor.RED}Usage: /RKS D <kitName>")
                    return false
                }

                val kitName = args[1]
                plugin.kitManager.deleteKit(kitName)
                sender.sendMessage("${ChatColor.GREEN}Kit '$kitName' deleted successfully!")
            }

            "L" -> {
                plugin.logger.info("RKS Command 'L' executed")

                val kits = plugin.kitManager.listKits()
                if (kits.isEmpty()) {
                    sender.sendMessage("${ChatColor.RED}No kits available!")
                } else {
                    sender.sendMessage("${ChatColor.GREEN}Available Kits:")
                    kits.forEach { sender.sendMessage("${ChatColor.GOLD}$it") }
                }
            }

            else -> return false
        }

        return true
    }
}
