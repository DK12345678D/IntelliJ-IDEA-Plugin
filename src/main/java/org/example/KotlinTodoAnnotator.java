package org.example;
import com.intellij.lang.annotation.*;
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiComment;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;

public class KotlinTodoAnnotator implements Annotator {
    @Override
    public void annotate(@NotNull PsiElement element, @NotNull AnnotationHolder holder) {
        if (element instanceof PsiComment) {
            String text = element.getText();
            if (text.contains("TODO")) {
                TextRange textRange = element.getTextRange();
                holder.newSilentAnnotation(HighlightSeverity.INFORMATION)
                        .range(textRange)
                        .textAttributes(DefaultLanguageHighlighterColors.LINE_COMMENT)
                        .create();

                String filePath = element.getContainingFile().getVirtualFile().getPath();
                int offset = element.getTextOffset();
                TodoService.getInstance().addTodo(filePath, text, offset);
            }
        }
    }

}
