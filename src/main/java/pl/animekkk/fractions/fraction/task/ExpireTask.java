package pl.animekkk.fractions.fraction.task;

import pl.animekkk.fractions.fraction.FractionManager;

import java.util.ArrayList;

public class ExpireTask implements Runnable {

    @Override
    public void run() {
        new ArrayList<>(FractionManager.getFractions()).forEach(fraction -> {
            if(fraction.getExpireDate() < System.currentTimeMillis()) {
                FractionManager.deleteFraction(fraction);
            }
        });
    }

}
