package me.literalgargoyle.randomkitselector

import org.bukkit.plugin.java.JavaPlugin

class RandomKitSelector : JavaPlugin() {
    lateinit var kitManager: KitManager
    lateinit var playerEventListener: PlayerEventListener

    override fun onEnable() {

        logger.info("RKS Starting")
        // Initialize the kit manager
        kitManager = KitManager(this)

        // Register the player event listener
        playerEventListener = PlayerEventListener(this)
        server.pluginManager.registerEvents(playerEventListener, this)

        // Register commands
        registerCommands()

        // Save default config
        saveDefaultConfig()
    }

    private fun registerCommands() {
        getCommand("RKS")?.setExecutor(CommandHandler(this))
        logger.info("RKS Registered command RKS")
    }

    override fun onDisable() {
        logger.info("RKS Stopping")
    }
}
