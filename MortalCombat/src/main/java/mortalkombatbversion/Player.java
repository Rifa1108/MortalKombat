/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mortalkombatbversion;

/**
 *
 * @author Мария
 */
public class Player extends Fighter {

    private int points;
    private int experience;
    private int win;
    private int nextexperience;

    public Player(int level, int health, int damage, int attack) {
        super(level, health, damage, attack);
        this.points = 0;
        this.experience = 0;
        this.nextexperience = 40;
        this.win = 0;
    }

    public int getPoints() {
        return this.points;
    }

    public int getExperience() {
        return this.experience;
    }

    public int getNextExperience() {
        return this.nextexperience;
    }

    public int getWin() {
        return this.win;
    }

    public void setPoints(int p) {
        this.points += p;
    }

    public void addExperience(int expirienceQuantity) {
        this.experience += expirienceQuantity;
    }

    public void setNextExperianceGoal(int experianceGoal) {
        this.nextexperience = experianceGoal;
    }

    public void addWin() {
        this.win++;
    }

    @Override
    public String getName() {
        return "You";
    }

}
