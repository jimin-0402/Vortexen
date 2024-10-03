package kr.jimin.vortexen.configs

import dev.dejvokep.boostedyaml.YamlDocument
import dev.dejvokep.boostedyaml.dvs.versioning.BasicVersioning
import dev.dejvokep.boostedyaml.libs.org.snakeyaml.engine.v2.common.ScalarStyle
import dev.dejvokep.boostedyaml.libs.org.snakeyaml.engine.v2.nodes.Tag
import dev.dejvokep.boostedyaml.settings.dumper.DumperSettings
import dev.dejvokep.boostedyaml.settings.general.GeneralSettings
import dev.dejvokep.boostedyaml.settings.loader.LoaderSettings
import dev.dejvokep.boostedyaml.settings.updater.UpdaterSettings
import dev.dejvokep.boostedyaml.spigot.SpigotSerializer
import dev.dejvokep.boostedyaml.utils.format.NodeRole
import kr.jimin.vortexen.VortexenPlugin
import kr.jimin.vortexen.configs.util.Config
import kr.jimin.vortexen.configs.util.LanguageType
import java.io.File
import java.util.*

class LanguagesManager(private val plugin: VortexenPlugin) {
    private val config = ConfigsManager(plugin).config
    private val languagesFolder = "${plugin.dataFolder}/languages/"

    private val selectedLanguage: LanguageType
        get() {
            val langValue = Config.LANGUAGE.getConfigValue() as? String
            return LanguageType.checkLang(langValue?.lowercase(Locale.getDefault()) ?: "en") ?: LanguageType.ENGLISH
        }

    private val languageFile: YamlDocument?
        get() = plugin.getResource("languages/${selectedLanguage.lang}.yml")?.let {
            YamlDocument.create(
                File(languagesFolder, "${selectedLanguage.lang}.yml"),
                it,
                GeneralSettings.builder().setSerializer(SpigotSerializer.getInstance()).build(),
                LoaderSettings.builder().setAutoUpdate(true).build(),
                DumperSettings.builder().setScalarFormatter { tag, value, role, def ->
                    if (tag != Tag.STR) return@setScalarFormatter def
                    return@setScalarFormatter if (role == NodeRole.KEY) ScalarStyle.PLAIN else ScalarStyle.DOUBLE_QUOTED
                }.build(),
                UpdaterSettings.builder().setVersioning(BasicVersioning("config-version")).build()
            )
        }

    fun initialiseLanguageSettings() {
        languageFile?.save()
        if (languageFile?.file?.exists() == true) {
            plugin.reloadConfig()
            reloadLanguages()
        }
    }

    fun getLangString(key: String): String = languageFile?.getString(key) ?: ""

    fun reloadLanguages(): Boolean {
        languageFile?.reload()
        return languageFile?.file?.exists() == true
    }
}
