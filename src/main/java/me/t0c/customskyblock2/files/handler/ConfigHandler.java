package me.t0c.customskyblock2.files.handler;

import org.bukkit.plugin.java.JavaPlugin;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.CustomClassLoaderConstructor;

import java.io.*;
import java.util.logging.Logger;

public class ConfigHandler {

    private final Logger logger;
    private final String prefix;

    public ConfigHandler(JavaPlugin plugin){
        logger = plugin.getLogger();
        prefix = plugin.getDataFolder().getPath() + File.separator;
    }

    /* ALIASES */

    public <T> T getConfig(Class<T> clazz){
        return getConfig(clazz, toCamelCase(clazz));
    }

    public <T> void saveConfig(T object){
        saveConfig(object, toCamelCase(object));
    }

    /* MAIN FUNCTIONS */

    public <T> T getConfig(Class<T> clazz, String path){
        try{
            return loadYaml(clazz, path);
        } catch(IOException e){
            saveDefault(clazz, path);
            return getDefault(clazz, path);
        }
    }

    public <T> void saveConfig(T object, String path){
        try{
            saveYaml(object, path);
        } catch(IOException e){
            logger.warning("Couldn't save config file");
        }
    }

    /* DEFAULTS */

    public <T> void saveDefault(Class<T> clazz) { saveDefault(clazz, toCamelCase(clazz)); }

    private <T> void saveDefault(Class<T> clazz, String path) {
        saveConfig(getDefault(clazz, path), path);
    }

    private <T> T getDefault(Class<T> clazz, String path) {
        Yaml yaml = genYaml(clazz);
        InputStream stream = ConfigHandler.class.getClassLoader().getResourceAsStream(path);
        if(stream == null) return null;
        Reader reader = new InputStreamReader(stream);
        return yaml.loadAs(reader, clazz);
    }

    /* DATA FOLDER */

    private <T> void saveYaml(T object, String path) throws IOException {
        if(object == null) return;
        File file = new File(prefix + path);
        createFile(file);
        Yaml yaml = genYaml(object.getClass());

        Writer writer = new FileWriter(file);
        writer.write(yaml.dumpAsMap(object));
        writer.close();
    }

    private <T> T loadYaml(Class<T> clazz, String path) throws FileNotFoundException {
        Reader reader = new FileReader(prefix + path);
        Yaml yaml = genYaml(clazz);
        return yaml.loadAs(reader, clazz);
    }

    /* FILE HANDLING */

    @SuppressWarnings("ResultOfMethodCallIgnored")
    private void createFile(File file){
        try{
            file.getParentFile().mkdirs();
        } catch(NullPointerException ignored) { }
        try {
            file.createNewFile();
        } catch(IOException e){
            logger.warning("Couldn't create config file");
        }
    }

    /* UTILS */

    private <T> String toCamelCase(T object){
        if(object == null) return "null";
        return toCamelCase(object.getClass());
    }

    private <T> String toCamelCase(Class<T> clazz){
        String string = clazz.getSimpleName();
        char[] charArr = string.toCharArray();
        charArr[0] = Character.toLowerCase(charArr[0]);
        return new String(charArr) + ".yml";
    }

    private <T> Yaml genYaml(Class<T> clazz){
        return new Yaml(new CustomClassLoaderConstructor(clazz.getClassLoader()));
    }
}
