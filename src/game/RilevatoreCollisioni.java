package game;

import entity.Entity;
import utils.Defines;

public class RilevatoreCollisioni {
    
    private Entity entity;

    private int grandezzaCaselle = Defines.GRANDEZZA_CASELLE, numColonne, numRighe;

    private int speed;

    private int larghezzaMappa, altezzaMappa;
    
    private int getSpeed() {
        return speed;
    }

    private void setSpeed(int speed) {
        this.speed=speed;
    }

    private void setDefaultValues() {
        numColonne = GamePanel.getMaxWorldCol() - 1;
        numRighe = GamePanel.getMaxWorldRow() - 1;
        larghezzaMappa = grandezzaCaselle * numColonne;
        altezzaMappa = grandezzaCaselle * numRighe - 5;
    }

    //  Costruttore
    public RilevatoreCollisioni() {
        setDefaultValues();
    }

    //  Metodi
    
    public void controllaCasella(Entity entity) {
        this.entity = entity;

        int playerWorldX = Defines.PLAYER.getWorldX();
        int playerWorldY = Defines.PLAYER.getWorldY();

        setDefaultValues();

        setSpeed(entity.getSpeed());
        
        int entityDestraWorldX = playerWorldX + entity.areaCollisione.x + entity.areaCollisione.width;
        int entitySinistraWorldX = playerWorldX + entity.areaCollisione.x;
        int entitySuWorldY = playerWorldY + entity.areaCollisione.y;
        int entityGiuWorldY = playerWorldY + entity.areaCollisione.y + entity.areaCollisione.height;
    
        int entitySinistraCol = entitySinistraWorldX / grandezzaCaselle;
        int entityDestraCol = entityDestraWorldX / grandezzaCaselle;
        int entitySuRow = entitySuWorldY / grandezzaCaselle;
        int entityGiuRow = entityGiuWorldY / grandezzaCaselle;
    
    
        if (fuoriDaiBordiX(playerWorldX)) {
            switch (entity.getDirezione()) {
                case "sinistra":
                    if (playerWorldX <= 0)
                        entity.inCollisione = true;
                    break;
                case "destra":
                    if (playerWorldX >= larghezzaMappa)
                        entity.inCollisione = true;
                    break;
                default:
                    break;
            }
        }
        if (fuoriDaiBordiY(playerWorldY)) {
            switch (entity.getDirezione()) {
                case "su":
                    if (playerWorldY < 0)
                        entity.inCollisione = true;
                    break;
                case "giu":
                    if (playerWorldY > altezzaMappa)
                        entity.inCollisione = true;
                    break;
                default:
                    break;
            }
        }
    
        switch (entity.getDirezione()) {
            case "su":
                entitySuRow = (entitySuWorldY - getSpeed()) / grandezzaCaselle;
                controllaCollisioniCasella(entitySinistraCol, entitySuRow);
                controllaCollisioniCasella(entityDestraCol, entitySuRow);
                break;
            case "giu":
                entityGiuRow = (entityGiuWorldY + getSpeed()) / grandezzaCaselle;
                controllaCollisioniCasella(entitySinistraCol, entityGiuRow);
                controllaCollisioniCasella(entityDestraCol, entityGiuRow);
                break;
            case "sinistra":
                entitySinistraCol = (entitySinistraWorldX - getSpeed()) / grandezzaCaselle;
                controllaCollisioniCasella(entitySinistraCol, entitySuRow);
                controllaCollisioniCasella(entitySinistraCol, entityGiuRow);
                break;
            case "destra":
                entityDestraCol = (entityDestraWorldX + getSpeed()) / grandezzaCaselle;
                controllaCollisioniCasella(entityDestraCol, entitySuRow);
                controllaCollisioniCasella(entityDestraCol, entityGiuRow);
                break;
            default:
                break;
        }
    }
    private void controllaCollisioniCasella(int col, int row){
        int tile;
        tile = Defines.TILE_MANAGER.n[col][row];
        if (Defines.TILE_MANAGER.tile[tile].getCollision())
            entity.inCollisione = true;
    }

    private boolean fuoriDaiBordiX(int x) {
        return x <= 0 || x >= larghezzaMappa;
    }
    
    private boolean fuoriDaiBordiY(int y) {
        return y <= 0 || y >= altezzaMappa;
    }
}
