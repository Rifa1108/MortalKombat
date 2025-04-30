/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mortalkombatbversion;

/**
 * Класс создающий Sonya Blade
 *
 * @see SonyaBlade
 */
public class SonyaBladeFabric implements EnemyFabricInterface {

    /**
     * Функция создания Sonya Blade
     *
     * @return возвращает созданную Sonya Blade
     * @see SonyaBlade
     */
    @Override
    public Fighter create() {
        Fighter enemy = new SonyaBlade(1, 80, 16, 1);
        return enemy;
    }

}
