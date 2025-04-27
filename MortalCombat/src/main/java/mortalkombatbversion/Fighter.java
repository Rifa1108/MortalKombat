/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mortalkombatbversion;

/**
 *
 * @author Мария
 */
public class Fighter {

    private int level;
    private int health;
    private int maxhealth;
    private int damage;
    private int attack;
    private int remainCursedTime;

    public Fighter(int level, int health, int damage, int attack) {
        this.level = level;
        this.health = health;
        this.damage = damage;
        this.attack = attack;
        this.maxhealth = health;
        remainCursedTime = 0;
    }

    public void levelUp() {
        this.level++;
    }

    public void addHealth(int addedHealth) {
        if (remainCursedTime > 0 && addedHealth < 0) {
            addedHealth *= 1.25;
        }
        this.health += addedHealth;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void addDamage(int addedDamage) {
        this.damage += addedDamage;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public void addMaxHealth(int addedMaxHealth) {
        this.maxhealth += addedMaxHealth;
    }

    public int getLevel() {
        return this.level;
    }

    public int getHealth() {
        return this.health;
    }

    public int getDamage() {
        if (remainCursedTime > 0) {
            return (int) (damage * 0.5);
        } else {
            return damage;
        }
    }

    public int getAttack() {
        return this.attack;
    }

    public int getMaxHealth() {
        return this.maxhealth;
    }

    public String getName() {
        return "";
    }

    public void changeCurseTime(int steps) {
        remainCursedTime = remainCursedTime + steps + 1;
    }

    public int getCurseTime() {
        return remainCursedTime;
    }

}
