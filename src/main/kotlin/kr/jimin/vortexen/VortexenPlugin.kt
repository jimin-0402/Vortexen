package kr.jimin.vortexen

import dev.jorel.commandapi.CommandAPI
import dev.jorel.commandapi.CommandAPIBukkitConfig
import kr.jimin.vortexen.commands.CommandsManager
import kr.jimin.vortexen.configs.ConfigsManager
import kr.jimin.vortexen.configs.LanguagesManager
import net.kyori.adventure.platform.bukkit.BukkitAudiences
import org.bukkit.plugin.java.JavaPlugin

class VortexenPlugin : JavaPlugin() {
    private lateinit var audience: BukkitAudiences
    private lateinit var configsManager: ConfigsManager
    private lateinit var languagesManager: LanguagesManager

    companion object {
        lateinit var instance: VortexenPlugin
            private set
    }

    override fun onLoad() {
        CommandAPI.onLoad(CommandAPIBukkitConfig(this))
    }

    override fun onEnable() {
        instance = this

        audience = BukkitAudiences.create(this)
        configsManager = ConfigsManager(this) // 초기화
        languagesManager = LanguagesManager(this).apply { initialiseLanguageSettings() }

        CommandAPI.onEnable()
        configsManager.reload()
        CommandsManager(this).loadsCommands()
    }

    override fun onDisable() {
        CommandAPI.onDisable()
    }

    fun getAudience(): BukkitAudiences = audience
    fun getLanguageManager(): LanguagesManager = languagesManager
    fun getConfigsManager(): ConfigsManager = configsManager
}
