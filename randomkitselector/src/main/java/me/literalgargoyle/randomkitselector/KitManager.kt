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
            val items = config.getList("kits.$kitName.items") as? List<ItemStack> ?: continue
            val armor = config.getList("kits.$kitName.armor") as? List<ItemStack> ?: continue
            kits[kitName] = Kit(items.toTypedArray(), armor.toTypedArray())
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
        config.set("kits.$kitName.items", items)
        config.set("kits.$kitName.armor", armor)
        plugin.saveConfig()
    }

    fun getRandomKit(): Kit? {
        return kits.values.randomOrNull()
    }
}
