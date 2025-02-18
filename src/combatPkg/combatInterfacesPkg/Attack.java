package combatPkg.combatInterfacesPkg;

public abstract class Attack {
    
    private String attackName;
    private int attackDamage;
    private boolean attackSpecialAction;

    public Attack(String attcakName, int attackDamage, boolean attackSpecialAction) {
        this.attackName = attcakName;
        this.attackDamage = attackDamage;
        if (attackSpecialAction)
        {
            this.attackSpecialAction = true;
        }
        else
        {
            this.attackSpecialAction = false;
        }
    }

}
