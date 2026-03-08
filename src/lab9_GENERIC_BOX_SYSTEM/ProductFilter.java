package lab9_GENERIC_BOX_SYSTEM;
// Name: JiepingZhou, 24250243
// Name: JOHN QUINN, 25259001
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ProductFilter<T extends Product> {
    /**
     * Filters items by name (case-insensitive).
     * {@code getName()} is available because of the bound {@code T extends Product}.
     *
     * @param items the list to filter
     * @param name  the name to match
     * @return filtered list — typed as List&lt;T&gt;, not List&lt;Product&gt;
     */
  public List<T> filterByName(List<T> items, String name) {
        return items.stream()
                .filter(item -> item.getName().equalsIgnoreCase(name)) // getName() available due to bound
                .collect(Collectors.toList());
    }

    /**
     * Filters items by a maximum price threshold.
     *
     * @param items    the list to filter
     * @param maxPrice the maximum price (inclusive)
     * @return filtered list preserving the specific type T
     */
    public List<T> filterByMaxPrice(List<T> items, double maxPrice) {
        return items.stream()
                .filter(item -> item.getPrice() <= maxPrice) // getPrice() available due to bound
                .collect(Collectors.toList());
    }

    /**
     * Filters items using a custom predicate (used in Part 4).
     *
     * @param items     the list to filter
     * @param condition the predicate each item must satisfy
     * @return filtered list preserving type T
     */
    public List<T> filterBy(List<T> items, Predicate<T> condition) {
        return items.stream()
                .filter(condition) // condition receives T, not Product
                .collect(Collectors.toList());
    }
}
