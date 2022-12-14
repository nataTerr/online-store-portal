# Портал интернет магазина (Spring MVC)

## I. Сервис интернет витрины
Отображает товары из каталога на сайте магазина.

### Обязательная часть

#### 1. Категории товаров
Все товары распределены по категориям (электроника, спортивные товары, товары для животных и т.д.). Категории товаров могут быть иерархически вложены друг в друга (компьютерная техника / мониторы / игровые мониторы).

Список категорий представляет из себя каталог, который доступен пользователю для навигации по сайту. При выборе конкретной категории открывается страница со списком товаров для выбранной категории. Помимо этого загружается список дочерних категорий, для возможности дальнейшей навигации. Список товаров для выбранной категории представляет из себя все товары из категории и всех подкатегорий.

#### 2. Список товаров
Список товаров загружается постранично по N штук. Пользователь может перемещаться по страницам, используя навигацию на сайте. Страница со списком товаров содержит краткий набор информации о каждом товаре в объеме достаточном для предварительного просмотра (название, цена, рейтинг\*, несколько фотографий в маленьком разрешении, ключевые характеристики\*).

#### 3. Карточка товара
Пользователь, выбирая товар, перемещается на страницу товара, где будет доступна вся возможная информация: все характеристики, описание, фотографии товары в большом разрешении, отзывы*

#### 4. Хлебные крошки
Для навигации по сайту удобно использовать не только каталог товаров, но и навигационную панель, называемую "хлебные крошки".\
Эта панель позволяет перейти на любой уровень вверх, относительно того товара или категории, где в данный момент находится пользователь.\
Например, находясь на странице конкретного товара, пользователь может перейти на уровень выше в ту категорию, в которой товар находится. Или же на два уровня выше. И так до корневой категории.

### Задание со звездочкой
- Авторизация и аутентификация пользователей.
- Поиск товаров на сайте.
- Реализовать систему оценки товара и возможность оставлять отзывы.

### Модель данных
[Проектирует студент, исходя из требований к задаче](https://github.com/nataTerr/online-store-portal/blob/docs/uml-diagram/docs/database-uml/UML.md)

### OpenAPI спецификация
Проектирует студент, исходя из требований к задаче

## II. Сервис работы с заказами

### II.a. Оформление заказа
#### 1. Добавление товаров в корзину
На странице карточки товара должна быть возможность сформировать корзину товаров: добавить текущий товар в корзину, изменить количество позиций, удалить товар из корзины.

На странице со списком товаров так же должен быть аналогичный функционал: добавить выбранный товар в корзину, удалить, изменить количество позиций.

#### 2. Виджет корзины товаров
На каждой странице сайта должен отображаться виджет с информацией о текущем количестве товаров в корзине. Ссылка на этом виджете ведет в корзину товаров.

#### 3. Корзина товаров
На странице отображается краткая информация о выбранных пользователем товарах, их количество, название, цена, превью фотографии.

Должна быть возможность изменить количество каждого товара или удалить его из корзины.

#### 4. Оформление заказа
Для оформления заказа пользователь указывает свои персональные данные, а так же данные о доставке товаров. При этом для доставки товара пользователь может указать как свой адрес, так и пункт выдачи товара\*

### II.b. Работа с заказами
#### 1. Обработка заказов
После того как заказ оформлен, необходимо, чтобы менеджер принял заказ в работу и начал его выполнять. Подразумевается, что в нашем интернет-магазине менеджер связывается с клиентом, подтверждает заказ по телефону и проводит по всем этапам вплоть до отправки заказа. Т.о. менеджеру необходим функционал изменения состава заказа, а так же редактирование статуса заказа. Необходимо реализовать контроль перехода из одного статуса заказа в другой.

#### 2. Сортировка заказов в работу*
Для того чтобы заказы не задерживались в работе необходимо для менеджера выдавать список всех заказов по определенному фильтру: город, категория, статус заказа, на определенные даты. А так же должна быть сортировка заказа по статусу и дате, для того чтобы можно было найти самые горячие заказы.

### Задания со звездочкой
- Выгружать из 1С и хранить информацию о пунктах выдачи товаров в каждом городе. Выгрузка файлов происходит по расписанию.
- Пользователь может просматривать историю своих заказов, видеть список заказанных ранее товаров, а так же статусы заказов.
- Сортировка заказов в работу для менеджера

### Модель данных
Проектирует студент, исходя из требований к задаче

### OpenAPI спецификация
Проектирует студент, исходя из требований к задаче

## Сервис интеграции с 1С
### Обязательная часть
Для поддержания информации на сайте в актуальном состоянии необходимо интегрироваться с системой учета товаров на предприятии, например, 1С. Для реализации этого требования со стороны 1С будут использоваться  csv-файлы. Будем считать, что эти файлы мы умеем получать, поэтому для начала разместим подготовленные заранее данные в ресурсах приложения*

Обработка файлов должна происходить по расписанию, чтобы автоматизировать актуализацию информации на сайте.

#### Загрузка списка категорий товаров
Список категорий товаров управляется в 1С и должен своевременно обновляться на сайте. Задание по обновлению категорий запускается по расписанию каждый час и выполняет синхронизацию данных с 1С. Для этого необходимо добавить новые категории и изменить существующие. Так, например, у категории может измениться название или родитель. Помимо этого категория может быть удалена, в таком случае в файле должна быть указана новая категория, в которую перенесутся все товары из удаляемой. Список удаляемых категорий хранится в отдельном файле.

#### Загрузка списка товаров
После того как загружены все категории необходимо загрузить список товаров. Товары так же могут быть добавлены, изменены или удалены.

Для удобного поиска товаров и категорий необходимо использовать идентификатор записи в 1С (foreign_id).

### Задания со звездочкой
Для загрузки файлов на сайт развернуть в докер-контейнере любой ftp сервер, примонтировать к нему локальную директорию и в приложении получать  файлы с обновлениями по ftp протоколу.
