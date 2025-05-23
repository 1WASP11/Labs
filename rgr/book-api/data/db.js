const fs = require('fs').promises;
const path = require('path');

// Визначаємо шлях до файлу "бази даних" відносно кореня проекту
// path.resolve забезпечує отримання абсолютного шляху
const dbPath = path.resolve(__dirname, '..', 'db.json'); // '..' - піднятись на рівень вище з папки 'data'

// Функція для читання даних з файлу
async function readData() {
  try {
    const rawData = await fs.readFile(dbPath, 'utf8');
    const jsonData = JSON.parse(rawData);
    return jsonData;
  } catch (error) {
    if (error.code === 'ENOENT') { // Якщо файл не знайдено, повертаємо порожній масив
        console.log("Файл db.json не знайдено, буде створено новий при першому записі.");
        return [];
    }
    console.error("Помилка читання файлу db.json:", error);
    // Краще кидати помилку, щоб головний додаток знав про проблему
    throw new Error('Не вдалося прочитати файл даних');
  }
}

// Функція для запису даних у файл
async function writeData(data) {
  try {
    const jsonString = JSON.stringify(data, null, 2);
    await fs.writeFile(dbPath, jsonString, 'utf8');
  } catch (error) {
    console.error("Помилка запису у файл db.json:", error);
    throw new Error('Не вдалося записати у файл даних');
  }
}

// Експортуємо функції, щоб їх можна було використовувати в інших файлах
module.exports = {
  readData,
  writeData,
};
