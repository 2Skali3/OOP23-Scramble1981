package scramble.utility;

import scramble.model.bullets.BulletType;
import scramble.model.common.impl.PairImpl;
import scramble.model.enemy.Rocket;
import scramble.model.map.util.LandUtils;
import scramble.model.tank.FuelTank;

import java.util.Map;

/** Public class that keeps all Constants toghether. */
public final class Constants {

        // Controller
        /**
         * Amount in ms between each tick of repaint of GV. Used in InputControllerImpl.
         */
        public static final int INPUT_TIMER_SEC = 30;
        /**
         * Max Stages used in some way for the checkpoints in
         * controller/mediator/LogicControllerImpl.
         */
        public static final int MAX_STAGES = 5;
        /**
         * Starting Y position for each CheckPoint. Used in
         * controller/mediator/LogicControllerImpl.
         */
        public static final int CHECKPOINT_Y_POSITION = 50;
        /** Maximum number of Lives. Used in controller/mediator/LogiControllerImpl. */
        public static final int MAX_LIVES = 2;
        /**
         * A counter used to determin repaint. Used in
         * controller/mediator/RepaintManager.
         */
        public static final int CICLE_BEFORE_BACKGROUND_REPAINT = 30;

        // Model
        /**
         * Movement speed on the X axis of the horizontal bullet type. Used in
         * model/bullets/Bullet.
         */
        public static final int XSPEED_HORIZONTAL_BULLET = 20;
        /**
         * Movement speed on the Y axis of the bomb bullet type. Used in
         * model/bullets/Bullet.
         */
        public static final int YSPEED_BOMB = 5;
        /**
         * Movement speed on the X axis of the bomb bullet type. Used in
         * model/bullets/Bullet.
         */
        public static final int XSPEED_BOMB = 5;
        /** Number of Bomb sprites. Used in model/bullets/Bullet. */
        public static final int SPRITE_NUMBER_BOMB = 5;
        /** Number of Bomb Explosion sprites. Used in model/bullets/Bullet. */
        public static final int SPRITE_NUMBER_BOMB_EXPLOSION = 4;
        /**
         * Map created to save sizes of each bullet type. Used in model/bullets/Bullet.
         */
        public static final Map<BulletType, PairImpl<Integer, Integer>> BULLETS_SIZE_MAP = Map.of(
                        BulletType.TYPE_HORIZONTAL, new PairImpl<>(5, 5),
                        BulletType.TYPE_BOMB, new PairImpl<>(42, 52));

        /** Rocket enemy speed. Used in RocketImpl.java . */
        public static final float ROCKET_SPEED = 3.5f;

        /** File path for the scores .json saved list. Used in model/scores/Scores. */
        public static final String SCORES_FILE_PATH = "/scores/scores.json";
        /**
         * This number expresses the max number of scores in the scoreBoard. Used in
         * model/scores/Scores.
         */
        public static final int MAX_N_SCORES = 9;
        /** Max amount of fuel. Used in model/spaceship/FuelBar. */
        public static final int MAX_FUEL = 100;
        /** Amount of fuel consumed every tick. Used in model/spaceship/FuelBar. */
        public static final int FUEL_DECREASE_AMOUNT = 1;
        /** Ammount of fuel refilled by {@link FuelTank}. */
        public static final int FUEL_REFILL = 15;
        /** Number of Spaceship sprites. Used in model/spaceship/SpaceShip. */
        public static final int SPRITE_SPACESHIP = 8;
        /** Number of Spaceship Explosion sprites. Used in model/spaceship/SpaceShip. */
        public static final int SPRITE_SPACESHIP_EXPLOSION = 4;
        /** Spaceship movement speed. Used in model/spaceship/SpaceShip. */
        public static final int SPACESHIP_SPEED = 2;

        /** Number of Rocket sprites. */
        public static final int SPRITE_ROCKET = 5;
        /** Number of Rocket Explosion sprites. */
        public static final int SPRITE_ROCKET_EXPLOSION = 4;
        /** Duration of a RocketExplosion before being deleted. */
        public static final int ROCKET_EXPLOSION_DURATION = 15;
        /** Number of Boss Explosion sprites. */
        public static final int SPRITE_BOSS_EXPLOSION = 4;
        /** Points for final Boss. */
        public static final int BOSS_POINTS = 1000;
        /** Spawn point for Boss. */
        public static final int BOSS_SPAWN_POINT = 26_500;

        // view
        /**
         * Number of stars generated in the background. Used in
         * view/compact/BackgroundPanel.
         */
        public static final int NUMBER_OF_STAR_IN_BACKGROUND = 100;

        /**
         * Landscape movement speed. Used in view/compact/LandscapePanel and in
         * view/compact/BulletsPanel.
         */
        public static final int LANDSCAPEX_SPEED = 4; // 4

        /**
         * Scales up the fuel bar for a fitting image on the screen. Used in
         * view/compact/FuelBarPanel.
         */
        public static final int FUELBAR_SCALE_FACTOR = 2;
        /**
         * Scales up the stage hud for a fitting image on the screen. Used in
         * view/compact/FuelBarPanel.
         */
        public static final float STAGE_HUD_SCALE_FACTOR = 1.75f;
        /** Starting X position of the spaceship. Used in view/compact/GameView. */
        public static final int SPACESHIP_STARTER_POSITION = 50;
        /** Width of the Spaceship. Used in SpaceShipPanel. */
        public static final int SPACESHIP_WIDTH = 54;
        /** Height of the Spaceship. Used in SpaceShipPanel. */
        public static final int SPACESHIP_HEIGHT = 27;

        /** Height of the Rocket. */
        public static final int ROCKET_HEIGHT = 50;
        /** Width of the Rocket. */
        public static final int ROCKET_WIDTH = 38;
        /** Points gained from destroying a rocket. */
        public static final int ROCKET_POINTS = 50;
        /** Max Delay of the rocket. */
        public static final int MAXDELAY = 3000;

        /** The original scramble font. Used in view/font/ScrambleFontUtil */
        public static final String FONT_PATH = "/font/PressStart2P-vaV7.ttf";

        // LandscapeUtils
        /** Number of sprites per stage per column. */
        public static final int SPRITE_PER_STAGE_HEIGHT = 40;
        /** Number of sprites per stage per row. */
        public static final int SPRITE_PER_STAGE_WIDTH = 350;

        // Stages
        /** Number of sprites per prestage per column. */
        public static final int SPRITE_PER_PRESTAGE_WIDTH = 70;

        /** The end of the map for the {@link Rocket} and {@link FuelTank} spawning. */
        public static final int END_OF_SPAWNING = 27_000;
        /** The end of {@link ROcket} spawn. */
        public static final int END_OF_ROCKET_SPAWN = (SPRITE_PER_STAGE_WIDTH * 4 + SPRITE_PER_PRESTAGE_WIDTH)
                        * LandUtils.PIXEL_PER_LAND_SPRITE_SIDE;

        private Constants() {
        }
}
