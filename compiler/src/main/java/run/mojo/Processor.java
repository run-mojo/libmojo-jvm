package run.mojo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.TypeParameterElement;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.TypeKind;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;

/**
 *
 */
public class Processor extends AbstractProcessor {

  Types typeUtils;
  Elements elementUtils;
  Filer filer;
  Messager messager;

  static String packageName(String name) {
    final String[] parts = name.split("[.]");
    if (parts.length == 1) {
      return "";
    } else {
      final String lastPart = parts[parts.length - 1];
      return name.substring(0, name.length() - lastPart.length() - 1);
    }
  }

  @Override
  public synchronized void init(ProcessingEnvironment processingEnv) {
    super.init(processingEnv);
    typeUtils = processingEnv.getTypeUtils();
    elementUtils = processingEnv.getElementUtils();
    filer = processingEnv.getFiler();
    messager = processingEnv.getMessager();
  }

  /**
   * @return
   */
  @Override
  public SourceVersion getSupportedSourceVersion() {
    return SourceVersion.latestSupported();
  }

  /**
   * @return
   */
  @Override
  public Set<String> getSupportedAnnotationTypes() {
    final Set<String> annotataions = new LinkedHashSet<>();
    annotataions.add(Actor.class.getCanonicalName());
    annotataions.add(Ask.class.getCanonicalName());
    annotataions.add(Tell.class.getCanonicalName());
    return annotataions;
  }

  @Override
  public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
    final Set<? extends Element> actorElements = roundEnv
        .getElementsAnnotatedWith(Actor.class);
    final Set<? extends Element> askElements = roundEnv
        .getElementsAnnotatedWith(Ask.class);
    final Set<? extends Element> tellElements = roundEnv
        .getElementsAnnotatedWith(Tell.class);

    final HashSet<Element> elements = new HashSet<>();

    if (actorElements != null) {
      elements.addAll(actorElements);
    }
    if (askElements != null) {
      elements.addAll(askElements);
    }
    if (tellElements != null) {
      elements.addAll(tellElements);
    }

    for (Element annotatedElement : elements) {
      final Actor actor = annotatedElement
          .getAnnotation(Actor.class);
      final Ask ask = annotatedElement
          .getAnnotation(Ask.class);
      final Tell tell = annotatedElement
          .getAnnotation(Tell.class);

      if (ask != null) {

      }
    }

    return false;
  }

  private void process(
      Set<? extends TypeElement> annotations,
      RoundEnvironment roundEnv,
      Ask ask,
      Element annotatedElement) {

  }

  public class ActorHolder {
    final TypeElement element;
    final LinkedHashMap<String, AskHolder> askMap = new LinkedHashMap<>();
    final LinkedHashMap<String, TellHolder> tellMap = new LinkedHashMap<>();

    public ActorHolder(TypeElement element) {
      this.element = element;
    }
  }

  public class AskHolder {

  }

  public class TellHolder {

  }


  /**
   * Resolves a single DECLARED Type Variable.
   * <p/>
   * Searches down the superclass, interface, and superinterface paths.
   *
   * @author Clay Molocznik
   */
  public static class DeclaredTypeVar {

    private final Map<String, ResolvedTypeVar> resolvedMap = new HashMap<>();
    private DeclaredType type;
    private TypeElement element;
    private int index;
    private String varName;
    private TypeMirror varType;
    private TypeElement varTypeElement;

    public DeclaredTypeVar(DeclaredType type, TypeElement element, int index, String varName,
        TypeMirror varType) {
      this.type = type;
      this.element = element;
      this.index = index;
      this.varName = varName;
      this.varType = varType;

      if (varType instanceof DeclaredType) {
        this.varTypeElement = (TypeElement) ((DeclaredType) varType).asElement();
      }
    }

    public DeclaredType getType() {
      return type;
    }

    public TypeElement getElement() {
      return element;
    }

    public int getIndex() {
      return index;
    }

    public String getVarName() {
      return varName;
    }

    public TypeMirror getResolvedType() {
      return varType;
    }

    public TypeElement getResolvedElement() {
      return varTypeElement;
    }

    public ResolvedTypeVar getResolved(String name) {
      return resolvedMap.get(name);
    }

    public void resolve() {
      // Find 'varName' on the SuperClass or any Interfaces.
      final TypeMirror superClass = element.getSuperclass();

      if (superClass != null) {
        search(varName, superClass);
      }
    }

    private void search(String varName, TypeMirror mirror) {
      if (mirror == null || !(mirror instanceof DeclaredType)) {
        return;
      }

      final DeclaredType type = (DeclaredType) mirror;
      final TypeElement element = (TypeElement) type.asElement();
      final List<? extends TypeMirror> typeArgs = type.getTypeArguments();
      if (typeArgs == null || typeArgs.isEmpty()) {
        return;
      }

      for (int i = 0; i < typeArgs.size(); i++) {
        final TypeMirror typeArg = typeArgs.get(i);

        if (typeArg.getKind() == TypeKind.TYPEVAR
            && typeArg.toString().equals(varName)) {
          // Found it.
          // Get TypeVariable name.
          varName = element.getTypeParameters().get(i).getSimpleName().toString();

          // Add to resolved map.
          resolvedMap.put(element.toString(), new ResolvedTypeVar(type, element, i, varName));

          // Go up to the SuperClass.
          search(varName, element.getSuperclass());

          final List<? extends TypeMirror> interfaces = element.getInterfaces();
          if (interfaces != null && !interfaces.isEmpty()) {
            for (TypeMirror iface : interfaces) {
              search(varName, iface);
            }
          }
        }
      }
    }
  }

  /**
   * @author Clay Molocznik
   */
  public static class ResolvedTypeVar {

    private DeclaredType type;
    private TypeElement element;
    private int index;
    private String varName;

    public ResolvedTypeVar(DeclaredType type, TypeElement element, int index, String varName) {
      this.type = type;
      this.element = element;
      this.index = index;
      this.varName = varName;
    }

    public DeclaredType getType() {
      return type;
    }

    public TypeElement getElement() {
      return element;
    }

    public int getIndex() {
      return index;
    }

    public String getVarName() {
      return varName;
    }
  }

  /**
   * @author Clay Molocznik
   */
  public class TypeParameterResolver {

    private final TypeElement element;
    private final List<DeclaredTypeVar> vars = new ArrayList<>();
    private boolean resolved;

    public TypeParameterResolver(TypeElement element) {
      this.element = element;
    }

    void resolve() {
      if (resolved) {
        return;
      }

      resolveDeclaredTypeVars(element.getSuperclass());

      final List<? extends TypeMirror> interfaces = element.getInterfaces();
      if (interfaces != null && !interfaces.isEmpty()) {
        for (TypeMirror iface : interfaces) {
          resolveDeclaredTypeVars(iface);
        }
      }

      resolved = true;
    }

    public DeclaredTypeVar resolve(Class cls, int typeVarIndex) {
      if (!resolved) {
        resolve();
      }

      for (DeclaredTypeVar var : vars) {
        ResolvedTypeVar actionTypeVar = var.getResolved(cls.getCanonicalName());

        if (actionTypeVar == null) {
          actionTypeVar = var.getResolved(cls.getName());
        }
        if (actionTypeVar != null) {
          if (actionTypeVar.getIndex() == typeVarIndex) {
            return var;
          }
        }
      }
      return null;
    }

    private void resolveDeclaredTypeVars(TypeMirror type) {
      if (type == null) {
        return;
      }

      if (type.getKind() != TypeKind.DECLARED) {
        return;
      }

      if (type.toString().equals(Object.class.getName())) {
        return;
      }

      if (!(type instanceof DeclaredType)) {
        return;
      }

      final DeclaredType declaredType = (DeclaredType) type;
      final TypeElement element = ((TypeElement) ((DeclaredType) type).asElement());

      if (element.getQualifiedName().toString().equals(Object.class.getName())) {
        return;
      }

      final List<? extends TypeMirror> typeArgs = declaredType.getTypeArguments();
      if (typeArgs != null && !typeArgs.isEmpty()) {
        for (int i = 0; i < typeArgs.size(); i++) {
          final TypeMirror typeArg = typeArgs.get(i);

          if (typeArg.getKind() == TypeKind.DECLARED) {
            final List<? extends TypeParameterElement> typeParams =
                ((TypeElement) ((DeclaredType) type).asElement()).getTypeParameters();
            final String typeVarName = typeParams.get(i).getSimpleName().toString();

            final DeclaredTypeVar declaredTypeVar =
                new DeclaredTypeVar(declaredType, element, i, typeVarName, typeArg);
            declaredTypeVar.resolve();
            vars.add(declaredTypeVar);
          }
        }
      }

      resolveDeclaredTypeVars(element.getSuperclass());

      final List<? extends TypeMirror> interfaces = element.getInterfaces();
      if (interfaces != null && !interfaces.isEmpty()) {
        for (TypeMirror iface : interfaces) {
          resolveDeclaredTypeVars(iface);
        }
      }
    }
  }
}
