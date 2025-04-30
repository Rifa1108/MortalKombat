/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mortalkombatbversion;

/**
 * Класс создающий Baraka
 *
 * @see Baraka
 */
public class BarakaFabric implements EnemyFabricInterface {

    /**
     * Функция создания Baraka
     *
     * @return возвращает созданного Baraka
     * @see Baraka
     */
    @Override
    public Fighter create() {
        Fighter enemy;
        enemy = new Baraka(1, 100, 12, 1);
        return enemy;
    }
}
