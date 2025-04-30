/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mortalkombatbversion;

/**
 * Класс персонажа Sonya Blade (солдат)
 *
 * @see Fighter
 * @see SonyaBladeFabric
 */
public class SonyaBlade extends Fighter {

    /**
     * Конструктор - создание новой Sonya Blade с определёнными значениями
     *
     * @param level
     * @param health
     * @param damage
     * @param attack
     * @see Fighter#Fighter(int, int, int, int)
     * @see SonyaBlade
     */
    public SonyaBlade(int level, int health, int damage, int attack) {
        super(level, health, damage, attack);
    }

    @Override
    public String getName() {
        return "Sonya Blade";
    }
}
