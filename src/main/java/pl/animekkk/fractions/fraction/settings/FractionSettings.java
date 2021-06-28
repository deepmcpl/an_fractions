package pl.animekkk.fractions.fraction.settings;

import lombok.Getter;

import java.util.HashSet;
import java.util.Set;

public class FractionSettings {

    @Getter
    private Set<FractionSetting> settings = new HashSet<FractionSetting>();

    public void addSetting(FractionSetting setting) {
        settings.add(setting);
    }

    public boolean hasSetting(FractionSetting setting) {
        return settings.contains(setting);
    }

}
