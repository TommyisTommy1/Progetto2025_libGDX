package game;

import entity.Entity;
import entity.Player;
import utils.Defines;

public class RilevatoreCollisioni {
    Entity entity;
    public RilevatoreCollisioni() {
    }

    public void controllaCasella(Entity entity) {
        this.entity=entity;

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
                controllaCollisioniCasella(entitySinistraCol, entitySuRow);
                break;
            case "giu":
                entityGiuRow = (entityGiuWorldY + speed) / grandezzaCaselle;
                controllaCollisioniCasella(entitySinistraCol, entityGiuRow);
                break;
            case "sinistra":
                entitySinistraCol = (entitySinistraWorldX - speed) / grandezzaCaselle;
                controllaCollisioniCasella(entitySinistraCol, entitySuRow);
                break;
            case "destra":
                entityDestraCol = (entityDestraWorldX + speed) / grandezzaCaselle;
                controllaCollisioniCasella(entityDestraCol, entitySuRow);
                break;
            default:
                break;
        }
    }
    public void controllaCollisioniCasella(int col, int row){
        int tile[] = new int[2];
        tile[0] = Defines.TILE_MANAGER.n[col][row];
        tile[1] = Defines.TILE_MANAGER.n[col][row];
        if (Defines.TILE_MANAGER.tile[tile[0]].getCollision() || Defines.TILE_MANAGER.tile[tile[1]].getCollision())
            entity.inCollisione = true;
    }
}
