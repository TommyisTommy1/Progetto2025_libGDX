package game;

import entity.Entity;
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

    private void setDefaultValues() {
        grandezzaCaselle = Defines.GRANDEZZA_CASELLE;
        numColonne = GamePanel.getMaxWorldCol();
        numRighe = GamePanel.getMaxWorldRow();
        larghezzaMappa = grandezzaCaselle * numColonne;
        altezzaMappa = grandezzaCaselle * numRighe;
    }

    //  Costruttore
    public RilevatoreCollisioni() {
    }

    //  Metodi
    
    public void controllaCasella(Entity entity) {
        grandezzaCaselle = Defines.GRANDEZZA_CASELLE;
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
                case "sinistra" -> {
                    if (playerWorldX <= 0)
                        entity.inCollisione = true;
                }
                case "destra" -> {
                    if (playerWorldX >= larghezzaMappa)
                        entity.inCollisione = true;
                }
                default -> {
                }
            }
        }
        if (fuoriDaiBordiY(playerWorldY)) {
            switch (entity.getDirezione()) {
                case "su" -> {
                    if (playerWorldY < 0)
                        entity.inCollisione = true;
                }
                case "giu" -> {
                    if (playerWorldY > altezzaMappa)
                        entity.inCollisione = true;
                }
                default -> {
                }
            }
        }
    
        switch (entity.getDirezione()) {
            case "su" -> {
                entitySuRow = (entitySuWorldY - getSpeed()) / grandezzaCaselle;
                controllaCollisioniCasella(entitySinistraCol, entitySuRow);
                controllaCollisioniCasella(entityDestraCol, entitySuRow);
            }
            case "giu" -> {
                entityGiuRow = (entityGiuWorldY + getSpeed()) / grandezzaCaselle;
                controllaCollisioniCasella(entitySinistraCol, entityGiuRow);
                controllaCollisioniCasella(entityDestraCol, entityGiuRow);
            }
            case "sinistra" -> {
                entitySinistraCol = (entitySinistraWorldX - getSpeed()) / grandezzaCaselle;
                controllaCollisioniCasella(entitySinistraCol, entitySuRow);
                controllaCollisioniCasella(entitySinistraCol, entityGiuRow);
            }
            case "destra" -> {
                entityDestraCol = (entityDestraWorldX + getSpeed()) / grandezzaCaselle;
                controllaCollisioniCasella(entityDestraCol, entitySuRow);
                controllaCollisioniCasella(entityDestraCol, entityGiuRow);
            }
            default -> {
            }
        }
    }

    private void controllaCollisioniCasella(int col, int row) {
        if (col >= 0 && col < numColonne && row >= 0 && row < numRighe) {
            // Se dentro i limiti controllo la tile
            int tile = Defines.TILE_MANAGER.n[col][row];
            if (Defines.TILE_MANAGER.tileset.getCollision(tile)) {
                entity.inCollisione = true;
            }
        } else {
            // Se fuori dal limite collisione true
            entity.inCollisione = true;
        }
    }

    private boolean fuoriDaiBordiX(int x) {
        return x <= 0 || x >= larghezzaMappa;
    }
    
    private boolean fuoriDaiBordiY(int y) {
        return y <= 0 || y >= altezzaMappa;
    }
}
