package mejust.frame.compiler;

import com.google.auto.service.AutoService;
import java.util.HashSet;
import java.util.Set;
import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;
import mejust.frame.annotation.TitleBar;
import mejust.frame.annotation.TitleBarMenu;

@AutoService(Processor.class)
public class ViewInjectProcessor extends AbstractProcessor {

    private Filer filer;
    private Elements elementUtils;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);
        filer = processingEnvironment.getFiler();
        elementUtils = processingEnvironment.getElementUtils();
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> set = new HashSet<>();
        set.add(TitleBar.class.getCanonicalName());
        set.add(TitleBarMenu.class.getCanonicalName());
        return set;
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        // TODO: 2018/04/12 收集相关信息，生成代码
        return false;
    }
}
