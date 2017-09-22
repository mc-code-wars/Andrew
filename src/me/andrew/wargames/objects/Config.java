package me.andrew.wargames.objects;

import me.andrew.wargames.Main;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.*;

// ------------------------------
// Copyright (c) PiggyPiglet 2017
// https://www.piggypiglet.me
// ------------------------------
public class Config {
    private FileConfiguration fileConfig;
    private Main main;

    public Config(String fileName){
        this(Main.getInstance().getDataFolder().getPath(), fileName);
    }

    public Config(String path, String fileName) {
        main = Main.getInstance();
        File file = new File(path, fileName);
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

    public FileConfiguration getConfig(){
        return fileConfig;
    }
}