package pl.animekkk.fractions.fraction.task;

import pl.animekkk.fractions.Fractions;

import java.util.logging.Level;

public class SaveTask implements Runnable {

    @Override
    public void run() {
        Fractions.getInstance().saveUsers();
        Fractions.getInstance().saveFractions();
        Fractions.getInstance().getLogger().log(Level.INFO, "SaveTask - saved!");
    }

}
