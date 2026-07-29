# Case Toolkit

[![Build](https://github.com/deknospe/toggle-case-plugin/actions/workflows/build.yml/badge.svg)](https://github.com/deknospe/toggle-case-plugin/actions/workflows/build.yml)

Case Toolkit is an IntelliJ Platform plugin for converting selected text between
common naming conventions without leaving the editor.

## Features

- Toggle individual letters between upper and lower case.
- Convert text to `UPPER CASE`, `lower case`, or `Title Case`.
- Convert identifiers to `camelCase`, `PascalCase`, `snake_case`, or `kebab-case`.
- Recognize whitespace, punctuation, existing delimiters, camel-case boundaries,
  and acronym boundaries.
- Transform every active selection in a multi-caret editing session.
- Preserve IntelliJ IDEA's native undo and redo behavior.
- Handle Latin, Cyrillic, and other Unicode letters with locale-independent rules.

## Usage

1. Select text in an editor.
2. Open **Edit → Convert Case**.
3. Choose the required conversion.

Toggle Case is also available with <kbd>Ctrl</kbd>+<kbd>\\</kbd>. Any shortcut can
be changed under **Settings/Preferences → Keymap** by searching for “Convert Case”.

For example:

| Input | Conversion | Result |
| --- | --- | --- |
| `Hello, Мир!` | Toggle Case | `hELLO, мИР!` |
| `parseHTTPResponse-value` | snake_case | `parse_http_response_value` |
| `XML_http-request` | camelCase | `xmlHttpRequest` |
| `multiple selection support` | PascalCase | `MultipleSelectionSupport` |

## Compatibility

- IntelliJ IDEA 2024.2 or newer
- Java 17 bytecode

The implementation uses only IntelliJ Platform APIs, so it is not tied to a
particular programming language or file type.

## Build from source

The Gradle wrapper is included:

```shell
./gradlew check buildPlugin
```

The installable ZIP is written to `build/distributions/`. To try the plugin in a
sandboxed IDE instance, run:

```shell
./gradlew runIde
```

## Project structure

- `CaseConverter` contains the IDE-independent conversion engine.
- `CaseTransformAction` applies a conversion to all active editor selections.
- `src/test` contains the JUnit 5 regression suite.
- `.github/workflows/build.yml` builds and tests every pull request.

## Contributing

Bug reports and pull requests are welcome. Please keep conversion logic independent
from IntelliJ APIs where possible and add a regression test for behavior changes.

## License

Distributed under the [MIT License](LICENSE).
