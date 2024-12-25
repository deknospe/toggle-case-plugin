# Toggle Case Plugin

### ITMO IS'28 ISRPO Plugin
Gromozdin Alexandr Vladimirovich M3109

## Description

**Toggle Case Plugin** is an IntelliJ IDEA plugin that inverts the case of any selected text: uppercase letters become lowercase, and lowercase letters become uppercase. It also supports a simple configuration to toggle the functionality on or off.

---

## Features

### 1. Toggle Case

- Converts all selected **UPPERCASE** letters to **lowercase**.
- Converts all selected **lowercase** letters to **UPPERCASE**.
- Non-alphabetic characters remain unchanged.

### 2. Simple Undo/Redo

- The plugin supports IntelliJ’s native undo/redo functionality.
- All changes can be reverted with standard shortcuts (e.g., `Ctrl+Z` or `Cmd+Z`).

---

## Usage

1. **Select** the text in an open file within IntelliJ IDEA.
2. Press the assigned keyboard shortcut (e.g., `Ctrl+\ + Ctrl+T`).
3. The selected text will automatically have its case inverted.

---

## Configuration

Currently, the plugin does not require additional configuration.  
If you wish to change the shortcut:

1. Go to **File > Settings > Keymap** (or **Preferences > Keymap** on macOS).
2. Search for **Toggle Case**.
3. Assign a new keyboard shortcut as desired.

---

## Supported Languages

Since this plugin operates on raw text, **any language** or file type open in the IntelliJ editor is supported.

---

## Installation

1. **Build** or **download** the `.zip` distribution of the plugin.
2. Open **IntelliJ IDEA**.
3. Go to **File > Settings > Plugins** (or **Preferences > Plugins** on macOS).
4. Click on the gear icon (⚙) and select **Install Plugin from Disk...**.
5. Choose the downloaded `.zip` file.
6. Restart the IDE if prompted.

---

## Known Issues

- Characters that are not letters (e.g., numbers, punctuation) remain unchanged.
- Very large selections may lead to minor performance delays, but typically it’s fast.

---

## Contributing

Contributions are welcome! Feel free to submit a pull request or open an issue on GitHub if you have any suggestions or encounter any bugs.

---

## Release Notes

### 0.0.2
- Added undo/redo support.

### 0.0.1
- Initial release with case toggling functionality.

---

## License

MIT
