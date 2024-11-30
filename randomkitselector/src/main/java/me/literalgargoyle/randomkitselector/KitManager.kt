package me.literalgargoyle.randomkitselector

import org.bukkit.configuration.file.FileConfiguration
import org.bukkit.inventory.ItemStack
import kotlin.random.Random

class KitManager(private val plugin: RandomKitSelector) {

    private val config: FileConfiguration = plugin.config

    // Save a kit
    fun saveKit(kitName: String, inventory: Array<ItemStack?>, armor: Array<ItemStack?>) {
        config.set("kits.$kitName.inventory", inventory.toList())
        config.set("kits.$kitName.armor", armor.toList())
        plugin.saveConfig()
    }

    // Get a random kit
    fun getRandomKit(): Pair<Array<ItemStack?>, Array<ItemStack?>>? {
        val kitsSection = config.getConfigurationSection("kits") ?: return null
        val kitNames = kitsSection.getKeys(false)

        if (kitNames.isEmpty()) return null

        val randomKitName = kitNames.random(Random)
        val inventory = config.getList("kits.$randomKitName.inventory") as List<ItemStack?>
        val armor = config.getList("kits.$randomKitName.armor") as List<ItemStack?>

        return Pair(inventory.toTypedArray(), armor.toTypedArray())
    }
}
