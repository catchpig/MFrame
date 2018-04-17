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
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;
import mejust.frame.annotation.TitleBar;
import mejust.frame.annotation.TitleBarMenu;
import mejust.frame.compiler.bean.TitleBarInfo;

@AutoService(Processor.class)
public class ViewInjectProcessor extends AbstractProcessor {

    private Filer filer;
    private Elements elementUtils;
    private InjectInfo injectInfo;

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
        injectInfo = new InjectInfo(processingEnv.getMessager());
        collectionTitleBarInfo(roundEnvironment);
        collectionTitleBarMenuInfo(roundEnvironment);
        injectInfo.writeFile(filer);
        return true;
    }

    private void collectionTitleBarInfo(RoundEnvironment roundEnvironment) {
        Set<? extends Element> elements = roundEnvironment.getElementsAnnotatedWith(TitleBar.class);
        for (Element element : elements) {
            TitleBar titleBar = element.getAnnotation(TitleBar.class);
            TitleBarInfo titleBarInfo = new TitleBarInfo();
            titleBarInfo.setBackgroundColor(titleBar.backgroundColor());
            titleBarInfo.setColor(titleBar.color());
            titleBarInfo.setSize(titleBar.size());
            titleBarInfo.setValue(titleBar.value());
            injectInfo.putTitleBar((TypeElement) element, titleBarInfo);
        }
    }

    private void collectionTitleBarMenuInfo(RoundEnvironment roundEnvironment) {
        Set<? extends Element> elements =
                roundEnvironment.getElementsAnnotatedWith(TitleBarMenu.class);
        for (Element element : elements) {
            TitleBarMenu titleBarMenu = element.getAnnotation(TitleBarMenu.class);
            ExecutableElement executableElement = (ExecutableElement) element;
            TitleBarInfo.TitleBarMenuInfo titleBarMenuInfo =
                    new TitleBarInfo.TitleBarMenuInfo(executableElement.getSimpleName().toString());
            titleBarMenuInfo.setIconRes(titleBarMenu.iconRes());
            titleBarMenuInfo.setLocation(titleBarMenu.location());
            titleBarMenuInfo.setText(titleBarMenu.text());
            titleBarMenuInfo.setTextColor(titleBarMenu.textColor());
            titleBarMenuInfo.setTextSize(titleBarMenu.textSize());
            injectInfo.putTitleBarMenu((TypeElement) element.getEnclosingElement(),
                    titleBarMenuInfo);
        }
    }
}
