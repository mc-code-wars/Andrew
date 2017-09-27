package me.andrew.wargames.objects;

import me.andrew.wargames.Main;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

// ------------------------------
// Copyright (c) PiggyPiglet 2017
// https://www.piggypiglet.me
// ------------------------------
public class Config {
    private FileConfiguration fileConfig;
    private Main main;

    private String name;
    private String path;
    private File file;

    public Config(String fileName){
        this(Main.getInstance().getDataFolder().getPath(), fileName);
    }

    public Config(String _path, String fileName) {
        main = Main.getInstance();
        name = fileName;
        path = _path;
        File _file = new File(_path, fileName);
        file = _file;
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            main.saveResource(fileName, false);
        }
        FileConfiguration filesConfig = new YamlConfiguration();
        try {
            filesConfig.load(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        fileConfig = filesConfig;
    }

    public boolean reloadConfig() {
        boolean status = true;
        fileConfig = new YamlConfiguration();
        try {
            fileConfig.load(file);
        } catch (Exception e) {
            status = false;
            e.printStackTrace();
        }
        saveConfig();
        return status;
    }
    public void saveConfig(){
        try {
            fileConfig.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public FileConfiguration getConfig(){
        return fileConfig;
    }
}