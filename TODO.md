# HW_L6_T1_TravelService

1. Реализовать класс GeoPosition, который хранит координаты города (широта и долгота в радианах)
   Конструктор принимает координаты в градусах, которые затем преобразовываются в конструкторе в радианы

Пример входных значений конструктора:

- 55
- 55(45'07'')
- 59(57'00'')

Реализовать getters & toString

2. Реализовать класс CityInfo, который хранит координаты города и его название. Реализовать getters & toString

// C-tor.
public CityInfo(String name, GeoPosition position)

3. Реализовать класс TravelService, позволяющий получить расстояние между городами по их координатам.
   https://www.kobzarev.com/programming/calculation-of-distances-between-cities-on-their-coordinates/
   https://stackoverflow.com/questions/13171791/how-can-i-print-the-distance-between-2-cities-given-their-latitude-and-longitud

4. Необязательно задание в классе AlgorithmPractice (выполняется по желанию и возможности)

Дополнительная информация для изучения о стримах:
а. https://habr.com/ru/company/luxoft/blog/270383/
б. https://javarush.ru/groups/posts/2203-stream-api

# Критерии приемки

!!! Запрещено использовать циклы, итераторы внутри класса TravelService. Использовать только streams и методы принимающие предикаты.
Нельзя объявлять другие поля в классе. Используйте коллекцию cities.

// do not change type
private final List<CityInfo> cities = new ArrayList<>();

1. Классы должен быть протестированы с помощью JUnit на предмет возвращаемых значений.
2. Написать тесты на альтернативные сценарии. (когда метод выбрасывает исключение)
3. Прислать PR из ветки feature/TravelServiceImpl в ветке feature/TravelService в вашем репозитории (должен быть 1 PR)
