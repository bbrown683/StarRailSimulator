package hsr.core;

public class BaseStats {
    private int health;
    private int attack;
    private int defense;
    private int speed;
    public int getHealth() {
        return health;
    }
    public void setHealth(int health) {
        this.health = health;
    }
    public int getAttack() {
        return attack;
    }
    public void setAttack(int attack) {
        this.attack = attack;
    }
    public int getDefense() {
        return defense;
    }
    public void setDefense(int defense) {
        this.defense = defense;
    }
    public int getSpeed() {
        return speed;
    }
    public void setSpeed(int speed) {
        this.speed = speed;
    }
    @Override
    public String toString() {
        return "BaseStats [health=" + health + ", attack=" + attack + ", defense=" + defense + ", speed=" + speed + "]";
    } 
}
