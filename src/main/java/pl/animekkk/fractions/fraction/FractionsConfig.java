package pl.animekkk.fractions.fraction;

import pl.mikigal.config.Config;
import pl.mikigal.config.annotation.ConfigName;

@ConfigName("config.yml")
public interface FractionsConfig extends Config {

    /* "get", because ConfigAPI requires to start with "get"
     * Otherwise it is not working.
     */
    default boolean getSettingsEnabled() {
        return false;
    }
}
