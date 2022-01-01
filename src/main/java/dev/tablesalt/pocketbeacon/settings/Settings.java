package dev.tablesalt.pocketbeacon.settings;

import org.mineacademy.fo.settings.SimpleSettings;

public class Settings extends SimpleSettings {

    @Override
    protected int getConfigVersion() {
        return 1;
    }


    public static class FuelTypes {

        public static Integer BLOCK_BURN_MULTIPLIER = getInteger("Block_Burn_Multiplier");

        public static Integer COALMULTIPLIER, IRONMULTIPLIER, GOLDMULTIPLIER, EMERALDMULTIPLIER, DIAMONDMULTIPLIER;

        public static Integer COALBURN, IRONBURN, GOLDBURN, EMERALDBURN, DIAMONDBURN;


        private static void init() {
            pathPrefix("Fuel_Types");
            COALMULTIPLIER = getInteger("Coal.Multiplier");
            IRONMULTIPLIER = getInteger("Iron.Multiplier");
            GOLDMULTIPLIER = getInteger("Gold.Multiplier");
            EMERALDMULTIPLIER = getInteger("Emerald.Multiplier");
            DIAMONDMULTIPLIER = getInteger("Diamond.Multiplier");

            COALBURN = getInteger("Coal.Burn_Time");
            IRONBURN = getInteger("Iron.Burn_Time");
            GOLDBURN = getInteger("Gold.Burn_Time");
            EMERALDBURN = getInteger("Emerald.Burn_Time");
            DIAMONDBURN = getInteger("Diamond.Burn_Time");


        }


    }


}
