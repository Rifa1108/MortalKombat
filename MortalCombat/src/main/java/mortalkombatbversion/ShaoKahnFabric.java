/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mortalkombatbversion;

/**
 * Класс создающий Shao Kahn
 *
 * @see ShaoKahn
 */
public class ShaoKahnFabric implements EnemyFabricInterface {

    /**
     * Функция создания Shao Kahn
     *
     * @return возвращает созданного Shao Kahn
     * @see ShaoKahn
     */
    @Override
    public Fighter create() {
        Fighter enemy = new ShaoKahn(3, 100, 30, 1);
        return enemy;
    }
}
