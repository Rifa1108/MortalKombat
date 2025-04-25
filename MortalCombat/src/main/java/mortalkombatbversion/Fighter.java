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
    
    public Fighter(int level, int health, int damage, int attack){
        this.level=level;
        this.health=health;
        this.damage=damage;
        this.attack=attack;
        this.maxhealth=health;
    }
   
    public void levelUp(){
        this.level++;
    }
    public void addHealth(int addedHealth){
        this.health+=addedHealth;
    }
    public void setHealth(int health){
        this.health=health;
    }
    public void addDamage(int addedDamage){
        this.damage+=addedDamage;
    }
    public void setAttack(int attack){
        this.attack=attack;
    }
    public void addMaxHealth(int addedMaxHealth){
        this.maxhealth+=addedMaxHealth;
    }
    
    public int getLevel(){
        return this.level;
    }
    public int getHealth(){
        return this.health;
    }
    public int getDamage(){
        return this.damage;
    }
    public int getAttack(){
        return this.attack;
    }
    public int getMaxHealth(){
        return this.maxhealth;
    }
    
    public String getName(){
        return "";
    }
    
}
