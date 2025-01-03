# Автоматизація тестування з використанням штучного інтелекту

---

У цьому проекті ми вирішуємо поширену проблему ручного оновлення локаторів (таких як XPath, селектори CSS тощо) в автоматизованих тестах інтерфейсу користувача шляхом інтеграції штучного інтелекту через інтерфейс API. Для цього доказу концепції ChatGPT використовується для динамічного відновлення зламаних локаторів, спрощуючи процес і підтверджуючи гіпотезу.

Примітка. Для майбутніх або робочих проектів розгляньте можливість використання більш безпечних рішень AI або внутрішніх інструментів, щоб забезпечити кращий захист даних і надійність.

---

## 📋 Вміст
- [🛠️ Встановлення](#-Встановлення)
- [🚀 Використання](#-Використання)
- [⚙️ Конфігурація](#-Конфігурація)
- [🛠️ Використані технології](#-Використані-технології)
- [📁 Структура проекту](#-Структура-проекту)
- [📞 Контактна інформація](#-Контактна інформація)


---

## 🛠️ Встановлення

### Передумови
1. Переконайтеся, що Git встановлено у вашій системі.
2. Переконайтеся, що Maven встановлено та JAVA_HOME налаштовано.

### Кроки для налаштування
1. Клонуйте репозиторій:

```баш
git клон https://github.com/ArtemTykhonenko/ui_test_with_ai_integration.git
```

2. Встановіть залежності проекту за допомогою Maven:

mvn чиста інсталяція

3. Завантажте необхідні драйвери за допомогою WebDriverManager.

---

## 🚀 Використання

### Виконання тестів
Щоб запустити автоматичні тести, скористайтеся такою командою Maven:

```баш
чистий тест mvn -Dtest=TestRunner "-Dcucumber.filter.tags=@C2" -Ddataproviderthreadcount=1 -Dholdbrowseropen=true
```
### Пояснення команди:

- `mvn clean test`: ця команда очищає проект і запускає тести за допомогою Maven.
- `-Dtest=TestRunner`: визначає, який клас запуску TestNG потрібно виконати (`TestRunner` у цьому випадку).
- `"-Dcucumber.filter.tags=@C2"`: фільтрує та запускає лише тести з тегом `@C2`. Ви можете змінити цей тег у файлах функцій, щоб запустити певні тести.
- `-Ddataproviderthreadcount=1`: встановлює кількість потоків для постачальника даних. Значення "1" гарантує, що тести виконуються послідовно.
- `-Dholdbrowseropen=true`: тримає браузер відкритим після виконання тесту. Це корисно для цілей налагодження.


### Створення звітів Allure

Щоб створити та відкрити звіт Allure після виконання тестів, скористайтеся такою командою:

```bush
mvn allure: служити
```

Ця команда автоматично створить звіт і розмістить його на http://localhost:8080.

---

## ⚙️ Конфігурація

### Плагіни Maven
У проекті використовуються такі плагіни Maven:

- Плагін Maven Surefire: для виконання тестів.
- Плагін Allure Maven: для створення звітів про тестування.
- Плагін AspectJ Maven: для обробки аспектно-орієнтованого програмування (AOP).

### Залежності
Включено такі залежності:

— Selenium WebDriver: для автоматизації браузера.
- WebDriverManager: для автоматичного керування драйверами браузера.
- TestNG: для організації та структурування тестів.
- Logback & SLF4J: Для реєстрації.
- Огірок: для тестування в стилі BDD.
- Allure: для створення звітів про тестування.
- OkHttp & JSON: для взаємодії з API AI та обробки відповідей JSON.
- AspectJ: для перехоплення викликів методів і відновлення локаторів.

---

## 🛠️ Використані технології
- Java 11
- Мейвен
- Selenium WebDriver
- TestNG
- Огірок
- Алюр
- Аспект Дж
- Логбек
- OkHttp
- Бібліотеки Gson і JSON

---

## 📁 Структура проекту
```
src
├── головний
│ ├── java
│ │ ├── автоматизація
│ │ │ ├── api # Інтеграція ШІ для ремонту локаторів
│ │ │ ├── базове # керування WebDriver
│ │ │ ├── дані # Сутності тестових даних (продукт, інформація про користувача)
│ │ │ ├── сторінок # Класи об’єктної моделі сторінки (LoginPage, HomePage, CartPage)
│ │ │ └── utils # Допоміжні класи (Logger, Constants, PageTools)
│ └── ресурси
├── тест
│ ├── java
│ │ ├── BDD # Визначення кроків і хуки для тестів Cucumber
│ └── ресурси
│ └── особливості # файли функцій огірка
└── pom.xml # Конфігурація проекту Maven
```
---

## 📄 Приклади функцій

### Вхід і вихід користувача

@C1 @Smoke @Login
Функція: вхід і вихід користувача

Сценарій: успішний вхід і вихід з дійсними обліковими даними
Відкрийте сторінку входу «Swag Labs».
Під час входу в «Swag Labs» як стандартний користувач
Потім відкриється домашня сторінка «Swag Labs».
Коли натисніть у бічному меню
І натисніть посилання «Вийти».
Потім відобразиться сторінка входу «Swag Labs».

### Додайте продукт до кошика

@C2 @Дим @AddProduct
Функція: додайте продукт у кошик

Сценарій: додавання товару в кошик
Відкрийте сторінку входу «Swag Labs».
Увійшов у «Swag Labs» як стандартний користувач
Потім відкриється домашня сторінка «Swag Labs».
Коли запам'ятати інформацію про 1 продукт
Натисніть кнопку «Додати до кошика» для 1 продукту
Потім кнопка «Видалити» відображається для 1 продукту
Коли натисніть посилання «Кошик».
Тоді значення елемента 1 рівні

---

## 📞 Контактна інформація

Не соромтеся звертатися, якщо у вас виникли запитання або потрібна допомога:

– Електронна пошта: artem.tykhonenko.v@gmail.com
– Telegram: https://t.me/Artem_Tykhonenko

---

## 📢 Майбутні вдосконалення
- запровадити додаткові моделі AI для покращеної стабільності локатора.
- Дослідити використання внутрішніх рішень AI для безпеки даних.
— Додати підтримку паралельного виконання тестів у контейнерах Docker.