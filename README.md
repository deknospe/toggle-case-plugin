# Case Toolkit

[![Build](https://github.com/deknospe/toggle-case-plugin/actions/workflows/build.yml/badge.svg)](https://github.com/deknospe/toggle-case-plugin/actions/workflows/build.yml)

Case Toolkit — плагин для IntelliJ Platform, который позволяет преобразовывать
выделенный текст между распространёнными стилями именования, не покидая редактор.

## Возможности

- Переключение регистра каждой буквы.
- Преобразование текста в `UPPER CASE`, `lower case` или `Title Case`.
- Преобразование идентификаторов в `camelCase`, `PascalCase`, `snake_case` или `kebab-case`.
- Распознавание пробелов, знаков препинания, существующих разделителей, границ слов
  в camel case и границ аббревиатур.
- Обработка всех активных выделений при редактировании с несколькими каретками.
- Поддержка стандартных для IntelliJ IDEA операций отмены и повтора.
- Корректная работа с латиницей, кириллицей и другими символами Unicode
  независимо от системной локали.

## Использование

1. Выделите текст в редакторе.
2. Откройте меню **Edit → Convert Case**.
3. Выберите нужное преобразование.

Действие Toggle Case также доступно по сочетанию <kbd>Ctrl</kbd>+<kbd>\\</kbd>.
Любое сочетание клавиш можно изменить в разделе
**Settings/Preferences → Keymap**, выполнив поиск по запросу «Convert Case».

Примеры:

| Исходный текст | Преобразование | Результат |
| --- | --- | --- |
| `Hello, Мир!` | Toggle Case | `hELLO, мИР!` |
| `parseHTTPResponse-value` | snake_case | `parse_http_response_value` |
| `XML_http-request` | camelCase | `xmlHttpRequest` |
| `multiple selection support` | PascalCase | `MultipleSelectionSupport` |

## Совместимость

- IntelliJ IDEA 2024.2 или новее
- байт-код Java 17

Реализация использует только API IntelliJ Platform, поэтому плагин не привязан
к конкретному языку программирования или типу файлов.

## Сборка из исходников

В репозиторий включён Gradle Wrapper:

```shell
./gradlew check buildPlugin
```

Готовый для установки ZIP-архив будет создан в каталоге `build/distributions/`.
Чтобы запустить плагин в изолированном экземпляре IDE, выполните:

```shell
./gradlew runIde
```

## Структура проекта

- `CaseConverter` содержит независимое от IDE ядро преобразования текста.
- `CaseTransformAction` применяет преобразование ко всем активным выделениям в редакторе.
- `src/test` содержит набор регрессионных тестов на JUnit 5.
- `.github/workflows/build.yml` собирает и тестирует проект для каждого pull request.

## Участие в разработке

Сообщения об ошибках и pull request приветствуются. По возможности сохраняйте
логику преобразований независимой от API IntelliJ и добавляйте регрессионные тесты
для каждого изменения поведения.

## Лицензия

Проект распространяется по [лицензии MIT](LICENSE).
