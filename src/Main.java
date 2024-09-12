import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        // Шаг 1: Создание списка заказов
        List<Order> orders = Arrays.asList(
                new Order("Laptop", 1200.0),
                new Order("Smartphone", 800.0),
                new Order("Laptop", 1500.0),
                new Order("Tablet", 500.0),
                new Order("Smartphone", 900.0)
        );

        // Шаг 2, 3 и 4: Группировка по продуктам, подсчет общей стоимости, сортировка и выбор трех самых дорогих
        List<Map.Entry<String, Double>> topProducts = orders.stream()
                .collect(Collectors.groupingBy(
                        Order::getProduct, // Группировка по продукту
                        Collectors.summingDouble(Order::getCost) // Суммирование цен
                ))
                .entrySet()
                .stream()
                .sorted((entry1, entry2) -> Double.compare(entry2.getValue(), entry1.getValue())) // Сортировка по убыванию
                .limit(3) // Выбор трех самых дорогих
                .toList();

        // Шаг 5: Вывод результата
        System.out.println("Три самых дорогих продукта и их общая стоимость:");
        for (Map.Entry<String, Double> entry : topProducts) {
            System.out.printf("Продукт: %s, Общая стоимость: %.2f%n", entry.getKey(), entry.getValue());
        }
    }
}