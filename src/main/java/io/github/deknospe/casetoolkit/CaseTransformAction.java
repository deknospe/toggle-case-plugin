package io.github.deknospe.casetoolkit;

import com.intellij.openapi.actionSystem.ActionUpdateThread;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.editor.Caret;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;

import java.util.Comparator;
import java.util.List;

public abstract class CaseTransformAction extends AnAction {
    private final CaseStyle style;

    protected CaseTransformAction(CaseStyle style) {
        this.style = style;
    }

    @Override
    public void actionPerformed(@NotNull AnActionEvent event) {
        Project project = event.getProject();
        Editor editor = event.getData(CommonDataKeys.EDITOR);
        if (project == null || editor == null) {
            return;
        }

        List<Replacement> replacements = editor.getCaretModel().getAllCarets().stream()
                .filter(Caret::hasSelection)
                .map(caret -> new Replacement(
                        caret.getSelectionStart(),
                        caret.getSelectionEnd(),
                        CaseConverter.convert(caret.getSelectedText(), style)
                ))
                .sorted(Comparator.comparingInt(Replacement::start).reversed())
                .toList();

        if (replacements.isEmpty()) {
            return;
        }

        WriteCommandAction.runWriteCommandAction(project, () -> {
            Document document = editor.getDocument();
            replacements.forEach(replacement -> document.replaceString(
                    replacement.start(),
                    replacement.end(),
                    replacement.text()
            ));
        });
    }

    @Override
    public void update(@NotNull AnActionEvent event) {
        Editor editor = event.getData(CommonDataKeys.EDITOR);
        boolean hasSelection = editor != null
                && editor.getCaretModel().getAllCarets().stream().anyMatch(Caret::hasSelection);
        event.getPresentation().setEnabledAndVisible(hasSelection);
    }

    @Override
    public @NotNull ActionUpdateThread getActionUpdateThread() {
        return ActionUpdateThread.BGT;
    }

    private record Replacement(int start, int end, String text) {
    }
}
