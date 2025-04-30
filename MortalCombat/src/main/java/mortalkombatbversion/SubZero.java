/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mortalkombatbversion;

/**
 * Класс персонажа Sub-Zero (маг)
 *
 * @see Fighter
 * @see SubZeroFabric
 */
public class SubZero extends Fighter {

    /**
     * Конструктор - создание нового Sub-Zero с определёнными значениями
     *
     * @param level
     * @param health
     * @param damage
     * @param attack
     * @see Fighter#Fighter(int, int, int, int)
     * @see SubZero
     */
    public SubZero(int level, int health, int damage, int attack) {
        super(level, health, damage, attack);
    }

    @Override
    public String getName() {
        return "Sub-Zero";
    }
}
