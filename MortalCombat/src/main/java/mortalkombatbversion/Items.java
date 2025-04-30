/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mortalkombatbversion;

/**
 * Класс предмета из инвентаря игрока
 */
public class Items {

    /**
     * Название предмета
     */
    private String name;
    /**
     * Количество предмета
     */
    private int count;

    /**
     * Конструктор - создание нового предмета с определёнными значениями
     *
     * @param name {@link Items#name}
     * @param count {@link Items#count}
     */
    public Items(String name, int count) {
        this.name = name;
        this.count = count;
    }

    /**
     * Добавление к текущему количеству предмета переданное значение
     *
     * @param addedQuantity добавленное количество
     * @see Items#count
     */
    public void addElixir(int addedQuantity) {
        this.count += addedQuantity;
    }

    /**
     * Функция получения значения поля {@link Items#name}
     *
     * @return возвращает название предмета
     */
    public String getName() {
        return this.name;
    }

    /**
     * Функция получения значения поля {@link Items#count}
     *
     * @return возвращает количество предмета
     */
    public int getCount() {
        return this.count;
    }
}
