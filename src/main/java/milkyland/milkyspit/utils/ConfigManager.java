package milkyland.milkyspit.utils;

import lombok.Getter;
import milkyland.milkyspit.Plugin;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class ConfigManager {

    public final static ConfigManager instance = new ConfigManager();

    private Map<String, YamlConfiguration> configs = new HashMap<>();
    @Getter
    private YamlConfiguration localeConfig;

    public void init(String... fileNames) {
        File languagesFolder = new File(Plugin.getInstance().getDataFolder(), "languages");
        if (!languagesFolder.exists()) {
            languagesFolder.mkdirs();
        }

        for (String fileName : fileNames) {
            fileName = fileName + ".yml";

            File file = new File(Plugin.getInstance().getDataFolder().getAbsolutePath() + "/" + fileName);

            if (!file.exists()) {
                Plugin.getInstance().saveResource(fileName, false);
            }

            configs.put(fileName, YamlConfiguration.loadConfiguration(file));
        }
        loadLocaleConfig();
    }

    public YamlConfiguration get(String configName) {
        return configs.get(configName + ".yml");
    }

    public void reload() {
        for (String fileName : configs.keySet()) {
            File file = new File(Plugin.getInstance().getDataFolder().getAbsolutePath() + "/" + fileName);
            if (file.exists()) {
                configs.put(fileName, YamlConfiguration.loadConfiguration(file));
            }
        }
        loadLocaleConfig();
    }

    private void loadLocaleConfig() {
        String locale = get("config").getString("locale", "en_US");
        String fileName = "languages/messages_" + locale + ".yml";
        File file = new File(Plugin.getInstance().getDataFolder().getAbsolutePath() + "/" + fileName);

        if (!file.exists()) {
            Plugin.getInstance().saveResource(fileName, false);
        }

        localeConfig = YamlConfiguration.loadConfiguration(file);
    }
}
