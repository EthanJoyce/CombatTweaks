package org.mle.combattweaks.pvp.damagemods;

import java.util.HashMap;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.mle.combattweaks.util.NumberUtils;

public enum PvPStaticDamage {;
    
    private static HashMap<Material, Integer> meleeDamage = new HashMap<Material, Integer>();
    
    
    public static void loadMeleeMultipliers(FileConfiguration config) throws InvalidConfigurationException {
        List<String> list = config.getStringList("PvP.Static_Damage.HP.Melee");
        
        for(String str : list) {
            String[] split = str.split("\\s");
            if(split.length != 2)
                throw new InvalidConfigurationException("Invalid string \"" + str + "\" at \"PvP.Static_Damage.HP.Melee\" in config.yml");
            else if(!NumberUtils.isInt(split[0]))
                throw new InvalidConfigurationException("Invalid Integer \"" + split[0] + "\" at \"PvP.Static_Damage.HP.Melee\" in config.yml");
            else if(Material.getMaterial(Integer.parseInt(split[0])).equals(null))
                throw new InvalidConfigurationException("Invalid Item ID \"" + split[0] + "\" at \"PvP.Static_Damage.HP.Melee\" in config.yml");
            else if(!NumberUtils.isInt(split[1]))
                throw new InvalidConfigurationException("Invalid Integer \"" + split[1] + "\" at \"PvP.Static_Damage.HP.Melee\" in config.yml");
            
            addToMeleeList(Material.getMaterial(Integer.parseInt(split[0])), Integer.parseInt(split[1]));
        }
    }
    
    
    public static HashMap<Material, Integer> getMeleeList() {
        return meleeDamage;
    }
    
    public static int getDamage(Material m) {
        if(meleeDamage.containsKey(m)) return meleeDamage.get(m);
        else return -1;
    }
    
    public static void addToMeleeList(Material m, int i) {
        meleeDamage.put(m, i);
    }
    
    public static void removeFromMeleeList(Material m) {
        meleeDamage.remove(m);
    }
    
    
}
