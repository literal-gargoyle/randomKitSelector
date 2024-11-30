package me.literalgargoyle.randomkitselector

import org.bukkit.plugin.java.JavaPlugin

class RandomKitSelector : JavaPlugin() {
    lateinit var kitManager: KitManager
    lateinit var playerEventListener: PlayerEventListener

    override fun onEnable() {
        logger.info("RKS Starting")

        // Initialize the kit manager
        kitManager = KitManager(this)
        logger.info("RKS KitManager initialized")

        // Register the player event listener
        playerEventListener = PlayerEventListener(this)
        server.pluginManager.registerEvents(playerEventListener, this)
        logger.info("RKS PlayerEventListener registered")

        // Register commands
        registerCommands()
        logger.info("RKS Commands registered")

        // Save default config
        saveDefaultConfig()
        logger.info("RKS Default config saved")
    }
    private fun registerCommands() {
        logger.info("Attempting to register command RKS")
        val command = getCommand("RKS")
        if (command != null) {
            command.setExecutor(CommandHandler(this))
            logger.info("RKS Registered command RKS")
        } else {
            logger.warning("RKS Failed to register command RKS")
        }
    }

    override fun onDisable() {
        logger.info("RKS Stopping")
    }
}
