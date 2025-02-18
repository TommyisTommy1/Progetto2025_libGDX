package Gioco;

import entity.Entity;
import entity.Player;
import utils.Defines;

public class RilevatoreCollisioni {

    public RilevatoreCollisioni() {
    }

    public void controllaCasella(Entity entity) {

        int grandezzaCaselle = Defines.GRANDEZZA_CASELLE;
        int numColonne = GamePanel.getMaxWorldCol() - 1;
        int numRighe = GamePanel.getMaxWorldRow() - 1;
        int speed = entity.getSpeed();

        int entityDestraWorldX = Player.getWorldX() + entity.areaCollisione.x + entity.areaCollisione.width;
        int entitySinistraWorldX = Player.getWorldX() + entity.areaCollisione.x;
        int entitySuWorldY = Player.getWorldY() + entity.areaCollisione.y;
        int entityGiuWorldY = Player.getWorldY() + entity.areaCollisione.y + entity.areaCollisione.height;

        int entitySinistraCol = entitySinistraWorldX / grandezzaCaselle;
        int entityDestraCol = entityDestraWorldX / grandezzaCaselle;
        int entitySuRow = entitySuWorldY / grandezzaCaselle;
        int entityGiuRow = entityGiuWorldY / grandezzaCaselle;

        int tile1, tile2;

        int larghezzaMappa = grandezzaCaselle * numColonne;
        int altezzaMappa = grandezzaCaselle * numRighe - 5;


        if (Player.getWorldX() <= 0 || Player.getWorldX() >= larghezzaMappa) {
            switch (entity.getDirezione()) {
                case "sinistra":
                    if (Player.getWorldX() <= 0)
                        entity.inCollisione = true;

                    break;
                case "destra":
                    if (Player.getWorldX() >= larghezzaMappa)
                        entity.inCollisione = true;

                    break;
                default:
                    break;
            }
        }
        if (Player.getWorldY() < 0 || Player.getWorldY() > altezzaMappa) {
            switch (entity.getDirezione()) {
                case "su":
                    if (Player.getWorldY() < 0)
                        entity.inCollisione = true;
                    break;
                case "giu":
                    if (Player.getWorldY() > altezzaMappa)
                        entity.inCollisione = true;
                    break;
                default:
                    break;
            }
        }

        switch (entity.getDirezione()) {
            case "su":
                entitySuRow = (entitySuWorldY - speed) / grandezzaCaselle;
                tile1 = Defines.TILE_MANAGER.n[entitySinistraCol][entitySuRow];
                tile2 = Defines.TILE_MANAGER.n[entityDestraCol][entitySuRow];
                if (Defines.TILE_MANAGER.tile[tile1].getCollision()
                        || Defines.TILE_MANAGER.tile[tile2].getCollision())
                    entity.inCollisione = true;
                break;
            case "giu":
                entityGiuRow = (entityGiuWorldY + speed) / grandezzaCaselle;
                tile1 = Defines.TILE_MANAGER.n[entitySinistraCol][entityGiuRow];
                tile2 = Defines.TILE_MANAGER.n[entityDestraCol][entityGiuRow];
                if (Defines.TILE_MANAGER.tile[tile1].getCollision()
                        || Defines.TILE_MANAGER.tile[tile2].getCollision())
                    entity.inCollisione = true;
                break;
            case "sinistra":
                entitySinistraCol = (entitySinistraWorldX - speed) / grandezzaCaselle;
                tile1 = Defines.TILE_MANAGER.n[entitySinistraCol][entitySuRow];
                tile2 = Defines.TILE_MANAGER.n[entitySinistraCol][entityGiuRow];
                if (Defines.TILE_MANAGER.tile[tile1].getCollision()
                        || Defines.TILE_MANAGER.tile[tile2].getCollision())
                    entity.inCollisione = true;

                break;
            case "destra":
                entityDestraCol = (entityDestraWorldX + speed) / grandezzaCaselle;
                tile1 = Defines.TILE_MANAGER.n[entityDestraCol][entitySuRow];
                tile2 = Defines.TILE_MANAGER.n[entityDestraCol][entityGiuRow];
                if (Defines.TILE_MANAGER.tile[tile1].getCollision() || Defines.TILE_MANAGER.tile[tile2].getCollision())
                    entity.inCollisione = true;
                break;
            default:
                break;
        }
    }
}
