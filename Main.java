import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        List<Notebook> notebooks = new ArrayList<>();
        notebooks.add(new Notebook("Dell", "Inspiron", 8, 1000, "Windows 10", "Black", 899.99));
        notebooks.add(new Notebook("HP", "Pavilion", 16, 512, "Windows 10", "Silver", 1099.99));
        notebooks.add(new Notebook("Asus", "Zenbook", 16, 512, "Windows 10", "Blue", 1299.99));
        notebooks.add(new Notebook("Apple", "MacBook Air", 8, 256, "macOS", "Silver", 1199.99));
        notebooks.add(new Notebook("Lenovo", "ThinkPad", 32, 1000, "Windows 10", "Black", 1799.99));

        Scanner scanner = new Scanner(System.in);
        Map<String, Object> filters = new HashMap<>();
        System.out.println("Введите критерии фильтрации:");
        System.out.println("1 - ОЗУ");
        System.out.println("2 - Объем ЖД");
        System.out.println("3 - Операционная система");
        System.out.println("4 - Цвет");
        String input = scanner.nextLine();
        while (!input.isEmpty()) {
            switch (input) {
                case "1":
                    System.out.print("Минимальный объем ОЗУ: ");
                    int ram = scanner.nextInt();
                    filters.put("ram", ram);
                    break;
                case "2":
                    System.out.print("Минимальный объем ЖД: ");
                    int hdd = scanner.nextInt();
                    filters.put("hdd", hdd);
                    break;
                case "3":
                    System.out.print("Операционная система: ");
                    String os = scanner.next();
                    filters.put("os", os);
                    break;
                case "4":
                    System.out.print("Цвет: ");
                    String color = scanner.next();
                    filters.put("color", color);
                    break;
            }
            input = scanner.nextLine();
        }

        List<Notebook> filtered = filterNotebooks(notebooks, filters);
        System.out.println("Результаты фильтрации:");
        for (Notebook notebook : filtered) {
            System.out.println(notebook);
        }
    }

    public static List<Notebook> filterNotebooks(List<Notebook> notebooks, Map<String, Object> filters) {
        List<Notebook> filtered = new ArrayList<>();
        for (Notebook notebook : notebooks) {
            boolean pass = true;
            for (Map.Entry<String, Object> entry : filters.entrySet()) {
                String key = entry.getKey();
                Object value = entry.getValue();
                switch (key) {
                    case "ram":
                        if (notebook.getRam() < (int) value) {
                            pass = false;
                        }
                        break;
                    case "hdd":
                        if (notebook.getHdd() < (int) value) {
                            pass = false;
                        }
                        break;
                    case "os":
                        if (!notebook.getOs().equals(value)) {
                            pass = false;
                        }
                        break;
                    case "color":
                        if (!notebook.getColor().equals(value)) {
                            pass = false;
                        }
                        break;
                }
                if (!pass) {
                    break;
                }
            }
            if (pass) {
                filtered.add(notebook);
            }
        }
        return filtered;
    }
}
