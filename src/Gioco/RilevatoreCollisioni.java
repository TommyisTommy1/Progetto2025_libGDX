package Gioco;

import entity.Entity;
import utils.Defines;

public class RilevatoreCollisioni {

    public RilevatoreCollisioni() {
    }

    public void controllaCasella(Entity entity) {

        int grandezzaCaselle = Defines.GRANDEZZA_CASELLE;
        int numColonne = Defines.NUM_COLONNE - 1;
        int numRighe = Defines.NUM_RIGHE - 1;
        int speed = Defines.PLAYER.getSpeed();

        int entityDestraWorldX = entity.getWorldX() + entity.areaCollisione.x + entity.areaCollisione.width;
        int entitySinistraWorldX = entity.getWorldX() + entity.areaCollisione.x;
        int entitySuWorldY = entity.getWorldY() + entity.areaCollisione.y;
        int entityGiuWorldY = entity.getWorldY() + entity.areaCollisione.y + entity.areaCollisione.height;

        int entitySinistraCol = entitySinistraWorldX / grandezzaCaselle;
        int entityDestraCol = entityDestraWorldX / grandezzaCaselle;
        int entitySuRow = entitySuWorldY / grandezzaCaselle;
        int entityGiuRow = entityGiuWorldY / grandezzaCaselle;

        int tile1, tile2;

        int larghezzaMappa = grandezzaCaselle * numColonne;
        int altezzaMappa = grandezzaCaselle * numRighe - 5;
        System.out.println(grandezzaCaselle);
        System.out.println(numRighe);
        System.out.println(altezzaMappa);
        System.out.println(entity.getWorldY());


        if (entity.getWorldX() <= 0 || entity.getWorldX() >= larghezzaMappa) {
            switch (entity.getDirezione()) {
                case "sinistra":
                    if (entity.getWorldX() <= 0)
                        entity.inCollisione = true;

                    break;
                case "destra":
                    if (entity.getWorldX() >= larghezzaMappa)
                        entity.inCollisione = true;

                    break;
                default:
                    break;
            }
        }
        if (entity.getWorldY() < 0 || entity.getWorldY() > altezzaMappa) {
            switch (entity.getDirezione()) {
                case "su":
                    if (entity.getWorldY() < 0)
                        entity.inCollisione = true;
                    break;
                case "giu":
                    if (entity.getWorldY() > altezzaMappa)
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
