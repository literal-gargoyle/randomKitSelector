package me.literalgargoyle.rksb

import org.bukkit.configuration.file.FileConfiguration
import org.bukkit.configuration.file.YamlConfiguration
import org.bukkit.plugin.java.JavaPlugin
import java.io.File

class RKSB : JavaPlugin() {
    private lateinit var kitsFile: YamlConfiguration

    override fun onEnable() {
        // Debugging: Log plugin enable
        logger.info("RKSB Plugin Enabled")

        // Create the kits.yml file if it doesn't exist
        val kitsFileFile = File(dataFolder, "kits.yml")
        if (!kitsFileFile.exists()) {
            saveResource("kits.yml", false)
        }

        // Load the kits.yml configuration file
        kitsFile = YamlConfiguration.loadConfiguration(kitsFileFile)
        logger.info("Loaded kits.yml successfully.")

        // Register commands
        getCommand("RKS")?.setExecutor(RKSCommandExecutor(this))
    }

    // Method to get kitsFile
    fun getKitsFile(): FileConfiguration {
        return kitsFile
    }
}
