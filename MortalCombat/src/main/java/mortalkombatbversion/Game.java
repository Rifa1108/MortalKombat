/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mortalkombatbversion;

import java.io.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.openxml4j.exceptions.InvalidOperationException;
import org.apache.poi.xssf.usermodel.*;

/**
 *
 * @author Мария
 */
public class Game {

    /**
     * Экземпляр класса, который будет реализовывать логику действий персонажей
     * в текущей игре
     */
    protected CharacterAction action;
    /**
     * Экземпляр класса, который будет создавать надписи в текщей игре
     */
    protected TextChanger textChanger = new TextChanger();
    /**
     * Экземпляр класса, который будет реализовывать ход сражения в текщей игре
     */
    protected Fight fight = new Fight();
    /**
     * Текущий список рекордсменов
     */
    private ArrayList<Result> results = new ArrayList<>();

    /**
     * Функция создающая персонажа для игрока
     *
     * @param playerHealthProgressBar JProgressBar в котором отображается
     * здоровье игрока
     * @return возвращает созданного персонажа
     */
    public Player newPlayer(JProgressBar playerHealthProgressBar) {
        action = new CharacterAction();
        Player player = new Player(0, 80, 16, 1);
        action.setHealthProgressBar(player, playerHealthProgressBar);
        playerHealthProgressBar.setMaximum(player.getMaxHealth());
        return player;
    }

    /**
     * Функция добавления игрока к рекордсменам
     *
     * @param player игрок
     * @param nameForRecordTableTextField имя игрока
     * @param recordsTable таблица рекордов
     * @throws IOException
     */
    public void EndGameTop(Player player, JTextField nameForRecordTableTextField, JTable recordsTable) throws IOException {
        results.add(new Result(nameForRecordTableTextField.getText(), player.getPoints()));
        results.sort(Comparator.comparing(Result::getPoints).reversed());
        WriteToTable(recordsTable);
        WriteToExcel();
    }

    /**
     * Функция записи игрока в файл с рекордсменами
     *
     * @throws IOException
     */
    private void WriteToExcel() throws IOException {
        XSSFWorkbook recordsBook = new XSSFWorkbook();
        XSSFSheet recordsSheet = recordsBook.createSheet("Результаты ТОП 10");
        XSSFRow title = recordsSheet.createRow(0);
        title.createCell(0).setCellValue("№");
        title.createCell(1).setCellValue("Имя");
        title.createCell(2).setCellValue("Количество баллов");
        for (int i = 0; i < results.size(); i++) {
            if (i < 10) {
                XSSFRow champion = recordsSheet.createRow(i + 1);
                champion.createCell(0).setCellValue(i + 1);
                champion.createCell(1).setCellValue(results.get(i).getName());
                champion.createCell(2).setCellValue(results.get(i).getPoints());
            }
        }
        File file = new File(System.getProperty("user.dir") + "Results.xlsx");
        recordsBook.write(new FileOutputStream(file));
        recordsBook.close();
    }

    /**
     * Функция получения значения поля {@link Game#results}
     *
     * @return возвращает список рекордсменов
     */
    public ArrayList<Result> getResults() {
        return this.results;
    }

    /**
     * Функция чтения рекордсменов из файла в {@link Game#results}
     */
    public void ReadFromExcel() {
        try {
            XSSFWorkbook recordsBook = new XSSFWorkbook(System.getProperty("user.dir") + "\\Results.xlsx");
            XSSFSheet recordsSheet = recordsBook.getSheetAt(0);
            for (int i = 1; i <= recordsSheet.getLastRowNum(); i++) {
                results.add(new Result(recordsSheet.getRow(i).getCell(1).getStringCellValue(), (int) recordsSheet.getRow(i).getCell(2).getNumericCellValue()));
            }
        } catch (InvalidOperationException | IOException e) {

        }
    }

    /**
     * Функция чтения рекордсменов из {@link Game#results} в таблицу рекордов в
     * игре
     *
     * @param recordsTable Таблица рекордов, отображающаяся в игре
     */
    public void WriteToTable(JTable recordsTable) {
        DefaultTableModel model = (DefaultTableModel) recordsTable.getModel();
        for (int i = 0; i < results.size(); i++) {
            if (i < 10) {
                model.setValueAt(results.get(i).getName(), i, 0);
                model.setValueAt(results.get(i).getPoints(), i, 1);
            }
        }
    }
}
