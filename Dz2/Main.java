import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;


public class Main {
    public static void main(String[] args) {
        /*
      Дана json строка [{ "фамилия":"Иванов","оценка":"5","предмет":"Математика"},{"фамилия":"Петрова",
      "оценка":"4","предмет":"Информатика"},{"фамилия":"Краснов","оценка":"5","предмет":"Физика"}]
      Задача написать метод(ы), который распарсит строку и выдаст ответ вида:
      Студент Иванов получил 5 по предмету Математика.
      Студент Петрова получил 4 по предмету Информатика.
      Студент Краснов получил 5 по предмету Физика.
      Используйте StringBuilder для подготовки ответа
      Исходная json строка это просто String !!! Для работы используйте методы String, такие как replace,
      split, substring и т.д. по необходимости
      Создать метод, который запишет результат работы в файл. Обработайте исключения, и запишите ошибки в лог файл.
       */
        File file = new File("students.txt");
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.replaceAll("\"", "");
                StringBuilder res = new StringBuilder();
                String[] parts = line.split(",");
                for (String part : parts) {
                    String[] word = part.split(":");
                    if (word[0].equalsIgnoreCase("Фамилия")) {
                        res.append("Студент ")
                                .append(word[1].replaceAll(",", ""))
                                .append(" ");
                    } else if (word[0].equals("оценка")) {
                        res.append("получил ")
                                .append(word[1].replaceAll(",", ""))
                                .append(" ");
                    } else if (word[0].equals("предмет")) {
                        res.append("по предмету ")
                                .append(word[1].replaceAll(",", ""))
                                .append(".");
                    }
                }
                System.out.println(res.toString());
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}