/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mortalkombatbversion;

/**
 * Класс персонажа Shao Kahn (босс)
 *
 * @see Fighter
 * @see ShaoKahnFabric
 */
public class ShaoKahn extends Fighter {

    /**
     * Конструктор - создание нового Shao Kahn с определёнными значениями
     *
     * @param level
     * @param health
     * @param damage
     * @param attack
     * @see Fighter#Fighter(int, int, int, int)
     * @see ShaoKahn
     */
    public ShaoKahn(int level, int health, int damage, int attack) {
        super(level, health, damage, attack);
    }

    @Override
    public String getName() {
        return "Shao Kahn";
    }
}
