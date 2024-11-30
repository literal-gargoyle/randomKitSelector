package me.literalgargoyle.randomkitselector

import org.bukkit.ChatColor
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class CreateKitCommand(private val plugin: RandomKitSelector) : CommandExecutor {

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (sender !is Player) {
            sender.sendMessage("${ChatColor.RED}Only players can use this command!")
            return true
        }

        val player: Player = sender

        if (!player.isOp) {
            player.sendMessage("${ChatColor.RED}You do not have permission to use this command!")
            return true
        }

        if (args.isEmpty()) {
            player.sendMessage("${ChatColor.RED}Usage: /createkit <kitName>")
            return true
        }

        val kitName = args[0]
        plugin.kitManager.saveKit(kitName, player.inventory.contents, player.inventory.armorContents)

        player.sendMessage("${ChatColor.GREEN}Kit ${ChatColor.GOLD}$kitName${ChatColor.GREEN} has been created!")
        return true
    }
}