/**
 * routes/bookRoutes.js
 *
 * Обробляє всі маршрути, пов'язані з ресурсом "книги" (/api/books)
 */

const express = require('express');
const { readData, writeData } = require('../data/db'); // Імпорт функцій для роботи з файлом даних
const { isAdmin } = require('../middleware/auth'); // Імпорт мідлвера для перевірки прав адміністратора

// Створюємо новий екземпляр роутера Express
const router = express.Router();

/**
 * @swagger
 * components:
 *   schemas:
 *     Book:
 *       type: object
 *       required:
 *         - title
 *         - author
 *         - year
 *       properties:
 *         id:
 *           type: integer
 *           description: Унікальний ідентифікатор книги (генерується автоматично)
 *           readOnly: true
 *         title:
 *           type: string
 *           description: Назва книги
 *           example: "Кобзар"
 *         author:
 *           type: string
 *           description: Автор книги
 *           example: "Тарас Шевченко"
 *         year:
 *           type: integer
 *           description: Рік видання
 *           example: 1840
 *     BookInput: # Окрема схема для тіла запиту (без ID)
 *       type: object
 *       required:
 *         - title
 *         - author
 *         - year
 *       properties:
 *         title:
 *           type: string
 *           description: Назва книги
 *           example: "Енеїда"
 *         author:
 *           type: string
 *           description: Автор книги
 *           example: "Іван Котляревський"
 *         year:
 *           type: integer
 *           description: Рік видання
 *           example: 1798
 *   # Визначення securitySchemes тут необов'язкове, якщо воно є в server.js,
 *   # але може бути корисним для локальної ясності або генерації тільки з цього файлу.
 *   # securitySchemes:
 *   #   ApiKeyAuth:
 *   #     type: apiKey
 *   #     in: header
 *   #     name: X-API-Key
 */

/**
 * @swagger
 * tags:
 *   name: Books
 *   description: Керування колекцією книг
 */

/**
 * @swagger
 * /api/books:
 *   get:
 *     summary: Отримати список книг
 *     tags: [Books]
 *     description: Повертає масив всіх книг з можливістю фільтрації, пошуку та сортування.
 *     parameters:
 *       - in: query
 *         name: author
 *         schema:
 *           type: string
 *         description: Фільтр за автором (часткове співпадіння)
 *       - in: query
 *         name: year
 *         schema:
 *           type: integer
 *         description: Фільтр за роком видання
 *       - in: query
 *         name: search
 *         schema:
 *           type: string
 *         description: Пошук за назвою або автором
 *       - in: query
 *         name: sortBy
 *         schema:
 *           type: string
 *           enum: [id, title, author, year]
 *         description: Поле для сортування
 *       - in: query
 *         name: order
 *         schema:
 *           type: string
 *           enum: [asc, desc]
 *         description: Напрямок сортування (за замовчуванням 'asc')
 *     responses:
 *       '200':
 *         description: Успішний запит. Повертає масив книг.
 *         content:
 *           application/json:
 *             schema:
 *               type: array
 *               items:
 *                 $ref: '#/components/schemas/Book'
 *       '500':
 *          description: Помилка сервера
 */
router.get('/', async (req, res) => {
  try {
    let books = await readData();
    const { author, year, search, sortBy, order } = req.query;

    // --- Фільтрація та Пошук ---
    if (author) {
      books = books.filter(book =>
        book.author.toLowerCase().includes(author.toLowerCase())
      );
    }
    if (year) {
      const yearNum = parseInt(year, 10);
      if (!isNaN(yearNum)) {
        books = books.filter(book => book.year === yearNum);
      } else {
        console.warn(`Некоректний параметр 'year': ${year}. Фільтр за роком не застосовано.`);
      }
    }
    if (search) {
      const searchTerm = search.toLowerCase();
      books = books.filter(book =>
        book.title.toLowerCase().includes(searchTerm) ||
        book.author.toLowerCase().includes(searchTerm)
      );
    }
    // --- Кінець фільтрації та пошуку ---

    // --- Сортування ---
    if (sortBy) {
      const allowedSortBy = ['id', 'title', 'author', 'year'];
      if (allowedSortBy.includes(sortBy)) {
        const sortOrder = (order && order.toLowerCase() === 'desc') ? -1 : 1;
        books.sort((a, b) => {
          const valA = a[sortBy];
          const valB = b[sortBy];
          if (typeof valA === 'string' && typeof valB === 'string') {
            return valA.toLowerCase().localeCompare(valB.toLowerCase()) * sortOrder;
          } else {
            if (valA < valB) return -1 * sortOrder;
            if (valA > valB) return 1 * sortOrder;
            return 0;
          }
        });
      } else {
        console.warn(`Некоректний параметр 'sortBy': ${sortBy}. Дозволені: ${allowedSortBy.join(', ')}`);
      }
    } else {
      // Сортування за замовчуванням (за ID)
      books.sort((a, b) => a.id - b.id);
    }
    // --- Кінець сортування ---

    res.json(books);

  } catch (error) {
    console.error("Помилка в GET /:", error);
    res.status(500).json({ message: error.message || 'Помилка на сервері при отриманні списку книг' });
  }
});

/**
 * @swagger
 * /api/books/{id}:
 *   get:
 *     summary: Отримати книгу за ID
 *     tags: [Books]
 *     description: Повертає детальну інформацію про одну книгу за її унікальним ідентифікатором.
 *     parameters:
 *       - in: path
 *         name: id
 *         required: true
 *         schema:
 *           type: integer
 *         description: Унікальний ID книги
 *     responses:
 *       '200':
 *         description: Успішний запит. Повертає об'єкт книги.
 *         content:
 *           application/json:
 *             schema:
 *               $ref: '#/components/schemas/Book'
 *       '400':
 *         description: Некоректний ID
 *       '404':
 *         description: Книгу не знайдено
 *       '500':
 *         description: Помилка сервера
 */
router.get('/:id', async (req, res) => {
  try {
    const books = await readData();
    const bookId = parseInt(req.params.id, 10);
    if (isNaN(bookId)) {
      return res.status(400).json({ message: 'Некоректний ID книги. Має бути число.' });
    }
    const book = books.find(b => b.id === bookId);
    if (book) {
      res.json(book);
    } else {
      res.status(404).json({ message: 'Книгу з таким ID не знайдено' });
    }
  } catch (error) {
    console.error("Помилка в GET /:id :", error);
    res.status(500).json({ message: error.message || 'Помилка на сервері при отриманні книги' });
  }
});

/**
 * @swagger
 * /api/books:
 *   post:
 *     summary: Створити нову книгу
 *     tags: [Books]
 *     description: Додає нову книгу до колекції. Потрібні права адміністратора.
 *     security:
 *       - ApiKeyAuth: [] # Цей маршрут вимагає автентифікації за схемою ApiKeyAuth
 *     requestBody:
 *       required: true
 *       content:
 *         application/json:
 *           schema:
 *             $ref: '#/components/schemas/BookInput' # Посилання на схему для вхідних даних
 *     responses:
 *       '201':
 *         description: Книгу успішно створено. Повертає створений об'єкт книги.
 *         content:
 *           application/json:
 *             schema:
 *               $ref: '#/components/schemas/Book'
 *       '400':
 *         description: Некоректні вхідні дані
 *       '403':
 *         description: Доступ заборонено (не адмін)
 *       '500':
 *         description: Помилка сервера
 */
router.post('/', isAdmin, async (req, res) => {
  try {
    const { title, author, year } = req.body;
    if (!title || !author || typeof year !== 'number') {
      return res.status(400).json({
        message: 'Некоректні дані книги. Потрібно вказати title (рядок), author (рядок) та year (число).',
      });
    }
    const books = await readData();
    const newId = books.length > 0 ? Math.max(...books.map(b => b.id)) + 1 : 1;
    const newBook = { id: newId, title, author, year };
    books.push(newBook);
    await writeData(books);
    res.status(201).json(newBook);
  } catch (error) {
    console.error("Помилка в POST /:", error);
    res.status(500).json({ message: error.message || 'Помилка на сервері при створенні книги' });
  }
});

/**
 * @swagger
 * /api/books/{id}:
 *   put:
 *     summary: Оновити існуючу книгу
 *     tags: [Books]
 *     description: Оновлює дані існуючої книги за її ID. Потрібні права адміністратора.
 *     security:
 *       - ApiKeyAuth: []
 *     parameters:
 *       - in: path
 *         name: id
 *         required: true
 *         schema:
 *           type: integer
 *         description: ID книги, яку потрібно оновити
 *     requestBody:
 *       required: true
 *       content:
 *         application/json:
 *           schema:
 *             $ref: '#/components/schemas/BookInput' # Очікуємо повний об'єкт для оновлення
 *     responses:
 *       '200':
 *         description: Книгу успішно оновлено. Повертає оновлений об'єкт книги.
 *         content:
 *           application/json:
 *             schema:
 *               $ref: '#/components/schemas/Book'
 *       '400':
 *         description: Некоректний ID або некоректні дані в тілі запиту
 *       '403':
 *         description: Доступ заборонено (не адмін)
 *       '404':
 *         description: Книгу для оновлення не знайдено
 *       '500':
 *         description: Помилка сервера
 */
router.put('/:id', isAdmin, async (req, res) => {
  try {
    const bookId = parseInt(req.params.id, 10);
    if (isNaN(bookId)) {
      return res.status(400).json({ message: 'Некоректний ID книги. Має бути число.' });
    }
    const { title, author, year } = req.body;
    if (!title || !author || typeof year !== 'number') {
      return res.status(400).json({
        message: 'Некоректні дані книги для оновлення. Потрібно вказати title (рядок), author (рядок) та year (число).',
      });
    }
    const books = await readData();
    const bookIndex = books.findIndex(b => b.id === bookId);
    if (bookIndex === -1) {
      return res.status(404).json({ message: 'Книгу з таким ID не знайдено для оновлення' });
    }
    const updatedBook = { id: bookId, title, author, year };
    books[bookIndex] = updatedBook;
    await writeData(books);
    res.json(updatedBook);
  } catch (error) {
    console.error("Помилка в PUT /:id:", error);
    res.status(500).json({ message: error.message || 'Помилка на сервері при оновленні книги' });
  }
});

/**
 * @swagger
 * /api/books/{id}:
 *   delete:
 *     summary: Видалити книгу
 *     tags: [Books]
 *     description: Видаляє книгу за її ID. Потрібні права адміністратора.
 *     security:
 *       - ApiKeyAuth: []
 *     parameters:
 *       - in: path
 *         name: id
 *         required: true
 *         schema:
 *           type: integer
 *         description: ID книги, яку потрібно видалити
 *     responses:
 *       '204':
 *         description: Книгу успішно видалено (No Content)
 *       '400':
 *         description: Некоректний ID
 *       '403':
 *         description: Доступ заборонено (не адмін)
 *       '404':
 *         description: Книгу для видалення не знайдено
 *       '500':
 *         description: Помилка сервера
 */
router.delete('/:id', isAdmin, async (req, res) => {
  try {
    const bookId = parseInt(req.params.id, 10);
    if (isNaN(bookId)) {
      return res.status(400).json({ message: 'Некоректний ID книги. Має бути число.' });
    }
    const books = await readData();
    const bookIndex = books.findIndex(b => b.id === bookId);
    if (bookIndex === -1) {
      return res.status(404).json({ message: 'Книгу з таким ID не знайдено для видалення' });
    }
    books.splice(bookIndex, 1);
    await writeData(books);
    res.status(204).send();
  } catch (error) {
    console.error("Помилка в DELETE /:id:", error);
    res.status(500).json({ message: error.message || 'Помилка на сервері при видаленні книги' });
  }
});

// Експортуємо роутер для використання в server.js
module.exports = router;