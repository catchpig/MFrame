package mejust.frame.compiler;

import com.google.auto.common.MoreElements;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.TypeKind;
import javax.lang.model.type.TypeMirror;
import javax.tools.Diagnostic;
import mejust.frame.compiler.bean.TitleBarInfo;

import static javax.lang.model.element.Modifier.PUBLIC;

/**
 * @author wangpeifeng
 * @date 2018/04/17 13:43
 */
public class InjectInfo {

    private static final ClassName TITLE_SETTING =
            ClassName.get("mejust.frame.widget.title", "TitleBarSetting");
    private static final ClassName CONTEXT = ClassName.get("android.content", "Context");
    private static final ClassName VIEW = ClassName.get("android.view", "View");
    private static final String ACTIVITY_TYPE = "android.app.Activity";
    private static final String FRAGMENT_TYPE_SUPPORT = "android.support.v4.app.Fragment";
    private static final String FRAGMENT_TYPE = "android.app.Fragment";

    private Messager messager;

    private HashMap<TypeElement, TitleBarInfo> titleBarMap = new HashMap<>();

    public InjectInfo(Messager messager) {
        this.messager = messager;
    }

    public void putTitleBar(TypeElement typeElement, TitleBarInfo titleBarInfo) {
        titleBarMap.put(typeElement, titleBarInfo);
    }

    public void putTitleBarMenu(TypeElement typeElement,
            TitleBarInfo.TitleBarMenuInfo titleBarMenuInfo) {
        TitleBarInfo titleBarInfo = titleBarMap.getOrDefault(typeElement, null);
        if (titleBarInfo == null) {
            messager.printMessage(Diagnostic.Kind.ERROR,
                    "TitleBarInfo is null,no set TitleBarMenuInfo", typeElement);
            return;
        }
        titleBarInfo.addMenuInfo(titleBarMenuInfo);
    }

    public void writeFile(Filer filer) {
        Set<Map.Entry<TypeElement, TitleBarInfo>> entrySet = titleBarMap.entrySet();
        for (Map.Entry<TypeElement, TitleBarInfo> entry : entrySet) {
            JavaFile javaFile = JavaFile.builder(getPackageClassName(entry.getKey()).packageName(),
                    getTypeSpec(entry.getKey(), entry.getValue())).build();
            try {
                javaFile.writeTo(filer);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private TypeSpec getTypeSpec(TypeElement key, TitleBarInfo value) {
        return TypeSpec.classBuilder(getPackageClassName(key).simpleName())
                .addModifiers(Modifier.PUBLIC)
                .addMethod(getMethodSpec(key, value))
                .build();
    }

    private MethodSpec getMethodSpec(TypeElement typeElement, TitleBarInfo value) {
        MethodSpec.Builder builder = MethodSpec.methodBuilder("getTitleBar")
                .addModifiers(Modifier.PUBLIC)
                .addParameter(getTargetTypeName(typeElement), "target")
                .addParameter(TITLE_SETTING, "setting")
                .returns(TITLE_SETTING);
        TypeMirror typeMirror = typeElement.asType();
        if (isSubtypeOfType(typeMirror, ACTIVITY_TYPE)) {
            builder.addStatement("$T context = target", CONTEXT);
        } else if (isSubtypeOfType(typeMirror, FRAGMENT_TYPE) || isSubtypeOfType(typeMirror,
                FRAGMENT_TYPE_SUPPORT)) {
            builder.addStatement("$T context = target.getContext()", CONTEXT);
        }
        generateStatement(builder, value);
        return builder.build();
    }

    private void generateStatement(MethodSpec.Builder builder, TitleBarInfo value) {
        if (!value.isVisible()) {
            builder.addStatement("return null");
            return;
        }
        builder.addStatement("TitleBarSetting.Builder builder = setting.newBuilder()");
        builder.addStatement("builder.setTitleTextContext($S)", value.getValue());
        if (value.getSize() != 0) {
            builder.addStatement("builder.setTitleTextSize($Lf)", value.getSize());
        }
        if (value.getColor() != 0) {
            builder.addStatement("builder.setTitleTextColorRes(context,$L)", value.getColor());
        }
        if (value.getBackgroundColor() != 0) {
            builder.addStatement("builder.setBackgroundColorRes(context,$L)",
                    value.getBackgroundColor());
        }
        for (Map.Entry<Integer, TitleBarInfo.TitleBarMenuInfo> entry : value.getMenuInfoHashMap()
                .entrySet()) {
            TitleBarInfo.TitleBarMenuInfo menuInfo = entry.getValue();
            String name = "titleMenu" + entry.getKey();
            builder.addStatement("TitleBarSetting.TitleMenu $N = new TitleBarSetting.TitleMenu($L)",
                    name, menuInfo.getLocation());
            if (menuInfo.getIconRes() == 0) {
                builder.addStatement("$N.setText($S)", name, menuInfo.getText());
                if (menuInfo.getTextColor() != 0) {
                    builder.addStatement("$N.setTextColorRes(context,$L)", name,
                            menuInfo.getTextColor());
                }
                if (menuInfo.getTextSize() != 0) {
                    builder.addStatement("$N.setTextSize($Lf)", name, menuInfo.getTextSize());
                }
            } else {
                builder.addStatement("$N.setIconDrawableRes(context,$L)", name,
                        menuInfo.getIconRes());
            }
            MethodSpec callbackMethodSpec = MethodSpec.methodBuilder("onClick")
                    .addModifiers(PUBLIC)
                    .addParameter(VIEW, "v")
                    .addStatement("target.$N(v)", menuInfo.getMethodName())
                    .build();
            TypeSpec callbackSpec = TypeSpec.anonymousClassBuilder("")
                    .superclass(ClassName.bestGuess("android.view.View.OnClickListener"))
                    .addMethod(callbackMethodSpec)
                    .build();
            builder.addStatement("$N.setClickListener($L)", name, callbackSpec);
            builder.addStatement("builder.addTitleMenu($N)", name);
        }
        builder.addStatement("return builder.build()");
    }

    private ClassName getPackageClassName(TypeElement typeElement) {
        String packageName = MoreElements.getPackage(typeElement).getQualifiedName().toString();
        String className = typeElement.getQualifiedName()
                .toString()
                .substring(packageName.length() + 1)
                .replace('.', '$');
        return ClassName.get(packageName, className + "_TitleBarInject");
    }

    private TypeName getTargetTypeName(TypeElement typeElement) {
        TypeMirror typeMirror = typeElement.asType();
        return TypeName.get(typeMirror);
    }

    private boolean isSubtypeOfType(TypeMirror typeMirror, String otherType) {
        if (isTypeEqual(typeMirror, otherType)) {
            return true;
        }
        if (typeMirror.getKind() != TypeKind.DECLARED) {
            return false;
        }
        DeclaredType declaredType = (DeclaredType) typeMirror;
        List<? extends TypeMirror> typeArguments = declaredType.getTypeArguments();
        if (typeArguments.size() > 0) {
            StringBuilder typeString = new StringBuilder(declaredType.asElement().toString());
            typeString.append('<');
            for (int i = 0; i < typeArguments.size(); i++) {
                if (i > 0) {
                    typeString.append(',');
                }
                typeString.append('?');
            }
            typeString.append('>');
            if (typeString.toString().equals(otherType)) {
                return true;
            }
        }
        Element element = declaredType.asElement();
        if (!(element instanceof TypeElement)) {
            return false;
        }
        TypeElement typeElement = (TypeElement) element;
        TypeMirror superType = typeElement.getSuperclass();
        if (isSubtypeOfType(superType, otherType)) {
            return true;
        }
        for (TypeMirror interfaceType : typeElement.getInterfaces()) {
            if (isSubtypeOfType(interfaceType, otherType)) {
                return true;
            }
        }
        return false;
    }

    private boolean isTypeEqual(TypeMirror typeMirror, String otherType) {
        return otherType.equals(typeMirror.toString());
    }
}
