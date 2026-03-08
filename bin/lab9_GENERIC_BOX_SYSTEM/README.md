Generic Box System
**Name:** JiepingZhou, 24250243
**Name:** JOHN QUINN, 25259001

## Project Files

### Core Classes

1. **Box.java** - Unbounded generic container class `Box<T>`
2. **Product.java** - Base class representing a generic product
3. **Book.java** - Specific product type with author and page count
4. **Fruit.java** - Specific product type with weight and harvest date
5. **ProductFilter.java** - Bounded generic class `ProductFilter<T extends Product>`

### Demonstration Programs

6. **GenericsDemo01.java** - Basic generic Box usage and type safety
7. **GenericsDemo02_TypeHierarchy.java** - Type hierarchy, upcasting/downcasting
8. **GenericsDemo03_Predicates.java** - Generics with Predicates and lambdas

## Generics Usage

### 1. Unbounded Generics: `Box<T>`

**What it is:**

- A generic container that can hold **any type** of item
- Type parameter `T` has no restrictions
- Works with `String`, `Integer`, `Product`, or any other type

**Why we use it:**

- **Type Safety**: Prevents storing wrong types at compile time
- **No Casting**: Retrieve items without casting
- **Reusability**: One class works for all types

### 2. Bounded Generics: `ProductFilter<T extends Product>`

**What it is:**

- A generic class where type parameter `T` **must extend Product**
- Restricts what types can be used
- Allows calling `Product` methods on type `T`

**Why we use it:**

- **Type Preservation**: `List<Book>` stays `List<Book>`, not `List<Product>`
- **Method Access**: Can call `getName()`, `getPrice()` because T extends Product
- **Specific Operations**: Work with Product-specific behavior while preserving subclass types

**Why the Bound Matters:**

- Without `extends Product`, compiler only knows `T` is `Object`
- Can't call `getName()` or `getPrice()` on plain `Object`
- Bound gives access to Product methods while preserving specific type

### 3. Generics with Predicates and Lambdas

**What it is:**

- Combining generics with Java's functional programming features
- `Predicate<T>` is a functional interface: `boolean test(T t)`
- Lambda expressions provide concise filtering logic

**Why we use it:**

- **Flexible Filtering**: Custom conditions without writing new methods
- **Type Safety**: Lambda parameter type matches generic type
- **Clean Code**: One-line filters instead of loops

### 4. Type Hierarchy and Generics

**Understanding the Problem:**
When you upcast to a parent type, you lose access to subclass-specific methods.

**What it shows:**

- Creating boxes for different types
- Type safety at compile time
- Accessing items without casting
- Runtime type information with `printItemType()`

---

## Architecture Diagram

```

                         Product (Base Class)
                         - name: String
                         - price: double
                              |
                 _____________|_____________
                |                           |
            Book (Subclass)             Fruit (Subclass)
            - author: String            - weight: int
            - pages: int                - date: String
                |                           |
                |                           |
         Box<Book>                    Box<Fruit>
         (Generic Container)          (Generic Container)
                |                           |
                |___________________________|
                            |
                    ProductFilter<T extends Product>
                    (Bounded Generic Class)
                            |
                 ___________|___________
                |                       |
         filterByName()          filterBy(Predicate<T>)
         filterByMaxPrice()      (Lambda expressions)
---
## Summary
This project demonstrates that **Java Generics** provide:
✓ **Type Safety**: Compile-time checking prevents type errors
✓ **Code Reusability**: One generic class works for many types
✓ **No Casting**: Direct retrieval without explicit casts
✓ **Functional Programming**: Seamless integration with lambdas and streams
---
**End of README**
```
