package me.literalgargoyle.randomkitselector

import org.bukkit.inventory.ItemStack
import org.bukkit.configuration.file.FileConfiguration

class KitManager(private val plugin: RandomKitSelector) {
    private val kits: MutableMap<String, Kit> = mutableMapOf()

    init {
        loadKits()
    }

    private fun loadKits() {
        val config: FileConfiguration = plugin.config
        val kitNames = config.getConfigurationSection("kits")?.getKeys(false) ?: return
        for (kitName in kitNames) {
            plugin.logger.info("Loading kit: $kitName")
            val items = config.getList("kits.$kitName.items")?.filterIsInstance<ItemStack>()?.toTypedArray()
            if (items == null) {
                plugin.logger.warning("Items for kit $kitName are not properly configured.")
                continue
            }
            val armor = config.getList("kits.$kitName.armor")?.filterIsInstance<ItemStack>()?.toTypedArray()
            if (armor == null) {
                plugin.logger.warning("Armor for kit $kitName is not properly configured.")
                continue
            }
            kits[kitName] = Kit(items, armor)
            plugin.logger.info("Kit $kitName loaded successfully.")
        }
    }

    fun createKit(kitName: String, items: Array<ItemStack>, armor: Array<ItemStack>) {
        kits[kitName] = Kit(items, armor)
        saveKit(kitName, items, armor)
    }

    fun deleteKit(kitName: String) {
        kits.remove(kitName)
        plugin.config.set("kits.$kitName", null)  // Remove from config
        plugin.saveConfig()
    }

    fun listKits(): List<String> {
        return kits.keys.toList()
    }

    private fun saveKit(kitName: String, items: Array<ItemStack>, armor: Array<ItemStack>) {
        val config = plugin.config
        config.set("kits.$kitName.items", items.toList())
        config.set("kits.$kitName.armor", armor.toList())
        plugin.saveConfig()
    }

    fun getRandomKit(): Kit? {
        return kits.values.randomOrNull()
    }
}
