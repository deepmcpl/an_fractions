package pl.animekkk.fractions.fraction.setting;

import lombok.Getter;

import java.util.HashSet;
import java.util.Set;

public class FractionSettings {

    public FractionSettings(String data) {
        if(data.length() < 3) return;
        for(String setting : data.split(";")) {
            settings.add(FractionSetting.valueOf(setting));
        }
    }

    @Getter
    private final Set<FractionSetting> settings = new HashSet<FractionSetting>();

    public void addSetting(FractionSetting setting) {
        settings.add(setting);
    }

    public boolean hasSetting(FractionSetting setting) {
        return settings.contains(setting);
    }

    public void toggleSetting(FractionSetting setting) {
        if(hasSetting(setting)) settings.remove(setting);
        else settings.add(setting);
    }

    @Override
    public String toString() {
        String data = "";
        for(FractionSetting setting : settings) {
            data += ";" + setting.name();
        }
        return data.replaceFirst(";", "");
    }

}
