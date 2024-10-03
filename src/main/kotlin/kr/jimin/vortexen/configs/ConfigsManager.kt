package kr.jimin.vortexen.configs

import dev.dejvokep.boostedyaml.YamlDocument
import dev.dejvokep.boostedyaml.dvs.versioning.BasicVersioning
import dev.dejvokep.boostedyaml.settings.dumper.DumperSettings
import dev.dejvokep.boostedyaml.settings.general.GeneralSettings
import dev.dejvokep.boostedyaml.settings.loader.LoaderSettings
import dev.dejvokep.boostedyaml.settings.updater.UpdaterSettings
import kr.jimin.vortexen.VortexenPlugin
import kr.jimin.vortexen.util.log.Logs
import java.io.File

class ConfigsManager(private val plugin: VortexenPlugin) {

    var config: YamlDocument? =
        plugin.getResource("config.yml")?.let {
            YamlDocument.create(File(plugin.dataFolder, "config.yml"), it,
                GeneralSettings.DEFAULT,
                LoaderSettings.builder().setAutoUpdate(true).build(),
                DumperSettings.DEFAULT,
                UpdaterSettings.builder().setVersioning(BasicVersioning("config-version")).build()
            )

        } ?: run {
            Logs.logError("Config file not found or could not be loaded.")
            null
        }

    fun reload() {
        try {
            config?.reload()
            LanguagesManager(plugin).reloadLanguages()
        } catch (e: Exception) {
            Logs.logError("Error reloading configuration: ${e.message}")
        }
    }

    fun getConfigValue(key: String): Any? {
        return config?.get(key)
    }
}
