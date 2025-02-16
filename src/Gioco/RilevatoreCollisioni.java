package Gioco;

import entity.Entity;
import utils.Defines;

public class RilevatoreCollisioni {

    public RilevatoreCollisioni(){}

    public void controllaCasella(Entity entity){

        int entityDestraWorldX = entity.getWorldX() + entity.areaCollisione.x + entity.areaCollisione.width;
        int entitySinistraWorldX = entity.getWorldX() + entity.areaCollisione.x;
        int entitySuWorldY = entity.getWorldY() + entity.areaCollisione.y;
        int entityGiuWorldY = entity.getWorldY() + entity.areaCollisione.y + entity.areaCollisione.height;
        
        int entitySinistraCol = entitySinistraWorldX/Defines.GRANDEZZA_CASELLE;
        int entityDestraCol = entityDestraWorldX/Defines.GRANDEZZA_CASELLE;
        int entitySuRow = entitySuWorldY/Defines.GRANDEZZA_CASELLE;
        int entityGiuRow = entityGiuWorldY/Defines.GRANDEZZA_CASELLE;

        int tile1, tile2;
        
        if (entity.getWorldX() <= 50 || entity.getWorldX()>=Defines.GRANDEZZA_CASELLE*(Defines.MASSIMA_ALTEZZA_COL-1)) {
            switch (entity.getDirezione()) {
                case "sinistra":
                    entitySinistraCol = (entitySinistraWorldX - 37)/Defines.GRANDEZZA_CASELLE;
                    if(entitySinistraCol<0){
                        entity.inCollisione = true;
                    }
                    break;
                case "destra":
                    entityDestraCol = (entityDestraWorldX)/Defines.GRANDEZZA_CASELLE;
                    if(entityDestraCol<Defines.MASSIMA_ALTEZZA_RIG-1){
                        entity.inCollisione = true;
                    }
                    break;
                default:
                    break;
            }
            return;
        }
        if (entity.getWorldY() <= 0 || entity.getWorldY()>=Defines.GRANDEZZA_CASELLE*(Defines.MASSIMA_ALTEZZA_RIG-1)) {
            return;
        }


        switch (entity.getDirezione()) {
            case "su":
                entitySuRow = (entitySuWorldY - entity.getSpeed())/Defines.GRANDEZZA_CASELLE;
                tile1 = Defines.TILE_MANAGER.n[entitySinistraCol][entitySuRow];
                tile2 = Defines.TILE_MANAGER.n[entityDestraCol][entitySuRow];
                if(Defines.TILE_MANAGER.tile[tile1].getCollision() || Defines.TILE_MANAGER.tile[tile2].getCollision()){
                    entity.inCollisione = true;
                }
                break;
            case "giu":
                entityGiuRow = (entityGiuWorldY + entity.getSpeed())/Defines.GRANDEZZA_CASELLE;
                tile1 = Defines.TILE_MANAGER.n[entitySinistraCol][entityGiuRow];
                tile2 = Defines.TILE_MANAGER.n[entityDestraCol][entityGiuRow];
                if(Defines.TILE_MANAGER.tile[tile1].getCollision() || Defines.TILE_MANAGER.tile[tile2].getCollision()){
                    entity.inCollisione = true;
                }
                break;
            case "sinistra":
                entitySinistraCol = (entitySinistraWorldX - entity.getSpeed())/Defines.GRANDEZZA_CASELLE;
                tile1 = Defines.TILE_MANAGER.n[entitySinistraCol][entitySuRow];
                tile2 = Defines.TILE_MANAGER.n[entitySinistraCol][entityGiuRow];
                if(Defines.TILE_MANAGER.tile[tile1].getCollision() || Defines.TILE_MANAGER.tile[tile2].getCollision()){
                    entity.inCollisione = true;
                }
                break;
            case "destra":
                entityDestraCol = (entityDestraWorldX + entity.getSpeed())/Defines.GRANDEZZA_CASELLE;
                tile1 = Defines.TILE_MANAGER.n[entityDestraCol][entitySuRow];
                tile2 = Defines.TILE_MANAGER.n[entityDestraCol][entityGiuRow];
                if(Defines.TILE_MANAGER.tile[tile1].getCollision() || Defines.TILE_MANAGER.tile[tile2].getCollision()){
                    entity.inCollisione = true;
                }
                break;
            default:
                break;
        }
    }
}
