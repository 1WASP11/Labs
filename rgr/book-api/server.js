// 1. Підключення модулів
const express = require('express');
const swaggerUi = require('swagger-ui-express'); // Swagger UI для відображення документації
const swaggerJsdoc = require('swagger-jsdoc');    // Для генерації специфікації з JSDoc
const { authenticateUser } = require('./middleware/auth'); // Мідлвер автентифікації
const bookRoutes = require('./routes/bookRoutes'); // Роутер для книг

// 2. Створення екземпляру додатку Express
const app = express();

// 3. Визначення порту
const PORT = process.env.PORT || 3000;

// Налаштування Swagger
const swaggerDefinition = {
  openapi: '3.0.0', // Версія специфікації OpenAPI
  info: {
    title: 'API Колекції Книг (РГР)', // Назва API
    version: '1.0.0', // Версія API
    description: 'Документація REST API для керування колекцією книг, створеного в рамках РГР.',
  },
  servers: [ // Масив серверів API
    {
      url: `http://localhost:${PORT}`, // Базовий URL
      description: 'Локальний сервер для розробки',
    },
  ],
  components: { // Перевикористовувані компоненти
    securitySchemes: { // Схеми безпеки
      ApiKeyAuth: { // Назва схеми (для нашого X-API-Key)
        type: 'apiKey',
        in: 'header',
        name: 'X-API-Key' // Назва заголовка, що містить ключ
      }
    }
  },
};

const options = {
  swaggerDefinition,
  // Шлях до файлів, де знаходяться JSDoc анотації для API
  apis: ['./routes/*.js'], // Шукати у всіх .js файлах папки routes
};

// Генеруємо специфікацію Swagger/OpenAPI на основі JSDoc
const swaggerSpec = swaggerJsdoc(options);
// Кінець налаштування Swagger


// 4. Глобальні мідлвери
app.use(express.json()); // Для парсингу JSON тіла запитів
app.use(authenticateUser); // Для визначення користувача за токеном (для всіх маршрутів нижче)

// Маршрути Swagger
// Обслуговуємо Swagger UI за шляхом /api-docs
app.use('/api-docs', swaggerUi.serve, swaggerUi.setup(swaggerSpec));
// Надаємо доступ до згенерованої специфікації у форматі JSON (необов'язково)
app.get('/api-docs.json', (req, res) => {
    res.setHeader('Content-Type', 'application/json');
    res.send(swaggerSpec);
});

// 5. Підключення (монтування) маршрутів API
// Всі запити на '/api/books' будуть оброблятися роутером bookRoutes
app.use('/api/books', bookRoutes);

// 6. Кореневий маршрут
app.get('/', (req, res) => {
  res.json({
    message: `Ласкаво просимо до API колекції книг!`,
    user: req.user, // Показуємо інформацію про поточного користувача
    apiDocs: '/api-docs' // Посилання на документацію Swagger UI
  });
});

// 7. Запуск серверу
app.listen(PORT, () => {
  console.log(`Сервер успішно запущено і слухає порт ${PORT}`);
  console.log(`API доступне за адресою http://localhost:${PORT}`);
  // Посилання на Swagger UI в консолі
  console.log(`Swagger UI доступний за адресою http://localhost:${PORT}/api-docs`);
});