import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Set<Notebook> notebooks = new HashSet<>();
        Notebook notebook1 = new Notebook("Dell", "Inspiron", 8, 1000, "Windows 10", "Black", 899.99);
        Notebook notebook2 = new Notebook("HP", "Pavilion", 16, 512, "Windows 10", "Silver", 1099.99);
        Notebook notebook3 = new Notebook("Asus", "Zenbook", 16, 512, "Windows 10", "Blue", 1299.99);
        Notebook notebook4 = new Notebook("Apple", "MacBook Air", 8, 256, "macOS", "Silver", 1199.99);
        Notebook notebook5 = new Notebook("Lenovo", "ThinkPad", 32, 1000, "Windows 10", "Black", 1799.99);
        Notebook notebook6 = new Notebook("Asus", "ROG", 16, 1000, "Windows 10", "Silver", 1399.99);
        notebooks.add(notebook1);
        notebooks.add(notebook2);
        notebooks.add(notebook3);
        notebooks.add(notebook4);
        notebooks.add(notebook5);
        notebooks.add(notebook6);

        Scanner scanner = new Scanner(System.in);
        Map<String, Object> filters = new HashMap<>();
        System.out.println("Введите критерии фильтрации:");
        System.out.println("1 - Бренд");
        System.out.println("2 - ОЗУ");
        System.out.println("3 - Объем ЖД");
        System.out.println("4 - Операционная система");
        System.out.println("5 - Цвет");
        String input = scanner.nextLine();
        while (!input.isEmpty()) {
            switch (input) {
                case "1":
                    System.out.print("Бренд: ");
                    String brand = scanner.next();
                    filters.put("brand", brand);
                    break;
                case "2":
                    System.out.print("Минимальный объем ОЗУ: ");
                    int ram = scanner.nextInt();
                    filters.put("ram", ram);
                    break;
                case "3":
                    System.out.print("Минимальный объем ЖД: ");
                    int hdd = scanner.nextInt();
                    filters.put("hdd", hdd);
                    break;
                case "4":
                    System.out.print("Операционная система: ");
                    String os = scanner.next();
                    filters.put("os", os);
                    break;
                case "5":
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

    public static List<Notebook> filterNotebooks(Set<Notebook> notebooks, Map<String, Object> filters) {
        List<Notebook> filtered = new ArrayList<>();
        for (Notebook notebook : notebooks) {
            boolean pass = true;
            for (Map.Entry<String, Object> entry : filters.entrySet()) {
                String key = entry.getKey();
                Object value = entry.getValue();
                switch (key) {
                    case "brand":
                        if (!notebook.getBrand().equals(value)) {
                            pass = false;
                        }
                        break;
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
