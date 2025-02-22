package game;

import entity.Entity;
import entity.Player;
import utils.Defines;

public class RilevatoreCollisioni {

    private Entity entity;

    private int grandezzaCaselle, numColonne, numRighe;

    private int speed;

    private int larghezzaMappa, altezzaMappa;

    private int getSpeed() {
        return speed;
    }

    private void setSpeed(int speed) {
        this.speed=speed;
    }

    //  Costruttore
    public RilevatoreCollisioni() {
        grandezzaCaselle = Defines.GRANDEZZA_CASELLE;
        numColonne = GamePanel.getMaxWorldCol() - 1;
        numRighe = GamePanel.getMaxWorldRow() - 1;
        larghezzaMappa = grandezzaCaselle * numColonne;
        altezzaMappa = grandezzaCaselle * numRighe - 5;
    }

    //  Metodi
    public void controllaCollisioniCasella(int col, int row){
        int tile;
        tile = Defines.TILE_MANAGER.n[col][row];
        if (Defines.TILE_MANAGER.tile[tile].getCollision())
            entity.inCollisione = true;
    }

    public void controllaCasella(Entity entity) {
        this.entity = entity;
    
        setSpeed(entity.getSpeed());
        
        int entityDestraWorldX = Player.getWorldX() + entity.areaCollisione.x + entity.areaCollisione.width;
        int entitySinistraWorldX = Player.getWorldX() + entity.areaCollisione.x;
        int entitySuWorldY = Player.getWorldY() + entity.areaCollisione.y;
        int entityGiuWorldY = Player.getWorldY() + entity.areaCollisione.y + entity.areaCollisione.height;
    
        int entitySinistraCol = entitySinistraWorldX / grandezzaCaselle;
        int entityDestraCol = entityDestraWorldX / grandezzaCaselle;
        int entitySuRow = entitySuWorldY / grandezzaCaselle;
        int entityGiuRow = entityGiuWorldY / grandezzaCaselle;
    
        
    
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
                controllaCollisioniCasella(entityDestraCol, entitySuRow);
                break;
            case "giu":
                entityGiuRow = (entityGiuWorldY + speed) / grandezzaCaselle;
                controllaCollisioniCasella(entitySinistraCol, entityGiuRow);
                controllaCollisioniCasella(entityDestraCol, entityGiuRow);
                break;
            case "sinistra":
                entitySinistraCol = (entitySinistraWorldX - speed) / grandezzaCaselle;
                controllaCollisioniCasella(entitySinistraCol, entitySuRow);
                controllaCollisioniCasella(entitySinistraCol, entityGiuRow);
                break;
            case "destra":
                entityDestraCol = (entityDestraWorldX + speed) / grandezzaCaselle;
                controllaCollisioniCasella(entityDestraCol, entitySuRow);
                controllaCollisioniCasella(entityDestraCol, entityGiuRow);
                break;
            default:
                break;
        }
    }


    
}
