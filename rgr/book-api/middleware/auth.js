// --- Налаштування користувачів та токенів (для демонстрації) ---
const USERS = {
    'admin-token-123': { role: 'admin', name: 'Адміністратор' },
    'user-token-456': { role: 'user', name: 'Звичайний Користувач' }
  };
  const AUTH_HEADER = 'x-api-key';
  
  // --- Мідлвер для автентифікації ---
  const authenticateUser = (req, res, next) => {
    const apiKey = req.header(AUTH_HEADER);
    const user = USERS[apiKey];
  
    if (user) {
      req.user = user;
      console.log(`Автентифіковано користувача: ${user.name} (роль: ${user.role})`);
    } else {
      req.user = { role: 'guest', name: 'Гість' };
      console.log('Користувач не автентифікований (гість)');
    }
    next();
  };
  
  // --- Мідлвер для перевірки прав адміністратора ---
  const isAdmin = (req, res, next) => {
    // Перевіряємо наявність req.user (про всяк випадок) і роль
    if (req.user && req.user.role === 'admin') {
      next();
    } else {
      console.warn(`Заборонено доступ для ${req.user?.name || 'Невідомий'} (роль: ${req.user?.role || 'не визначена'}) до ресурсу адміністратора.`);
      res.status(403).json({ message: 'Доступ заборонено. Потрібні права адміністратора.' });
    }
  };
  
  module.exports = {
    authenticateUser,
    isAdmin,
  };
  