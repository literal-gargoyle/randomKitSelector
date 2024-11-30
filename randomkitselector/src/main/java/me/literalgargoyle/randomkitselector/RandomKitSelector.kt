package me.literalgargoyle.randomkitselector

import org.bukkit.plugin.java.JavaPlugin

class RandomKitSelector : JavaPlugin() {

    lateinit var kitManager: KitManager

    override fun onEnable() {
        // Initialize KitManager
        kitManager = KitManager(this)

        // Register commands and event listeners
        getCommand("createkit")?.setExecutor(CreateKitCommand(this))
        server.pluginManager.registerEvents(PlayerEventListener(this), this)

        // Save default config
        saveDefaultConfig()

        logger.info("RandomKitSelector enabled!")
    }

    override fun onDisable() {
        logger.info("RandomKitSelector disabled!")
    }
}

