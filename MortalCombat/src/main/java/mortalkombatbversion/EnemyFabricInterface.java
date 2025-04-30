/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package mortalkombatbversion;

/**
 * Интерфейс классов создающих противников
 *
 * @see BarakaFabric
 * @see LiuKangFabric
 * @see ShaoKahnFabric
 * @see SonyaBladeFabric
 * @see SubZeroFabric
 */
public interface EnemyFabricInterface {

    /**
     * Функция создания противника
     *
     * @return возвращает созданного противника
     */
    public Fighter create();
}
