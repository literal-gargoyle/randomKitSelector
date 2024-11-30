package me.literalgargoyle.randomkitselector

import org.bukkit.ChatColor
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerRespawnEvent

class PlayerEventListener(private val plugin: RandomKitSelector) : Listener {

    @EventHandler
    fun onFirstJoin(event: PlayerJoinEvent) {
        val player = event.player
        if (!player.hasPlayedBefore()) {
            assignRandomKit(player)
            player.sendMessage("${ChatColor.GREEN}You have been given a random kit!")
        }
    }

    @EventHandler
    fun onRespawn(event: PlayerRespawnEvent) {
        assignRandomKit(event.player)
        event.player.sendMessage("${ChatColor.GREEN}You have been given a random kit!")
    }

    private fun assignRandomKit(player: Player) {
        val kit = plugin.kitManager.getRandomKit()
        if (kit == null) {
            player.sendMessage("${ChatColor.RED}No kits available!")
            return
        }

        player.inventory.clear()
        player.inventory.contents = kit.items
        player.inventory.armorContents = kit.armor
    }
}
