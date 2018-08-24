package run.mojo.app;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.function.Consumer;
import run.mojo.MojoError;
import run.mojo.MojoError.Code;
import run.mojo.actor.Sys;
import run.mojo.actor.Addr;
import run.mojo.http.ContentEncoding;

/**
 *
 */
public class SvrBuilder<S> {

  public String prefix;
  public String bind;
  public String systemName;
  public int backlog;
  public String hostname;
  public boolean systemExit;
  public boolean disableSignals;
  public int shutdownTimeout;
  public boolean noHttp2;

  ArrayList<Reactor<S>> reactors;


  SvrBuilder(ArrayList<Reactor<S>> reactors) {
    this.reactors = reactors;
  }

  public static <S> Route<S> defaultRoute(Resource resource) {
    Route route = new Route();
    route.resource = resource;
    return route;
  }

  /**
   *
   * @param predicates
   * @return
   */
  public static AllPredicate all(Predicate... predicates) {
    final AllPredicate pred = new AllPredicate();
    pred.vec.addAll(Arrays.asList(predicates));
    return pred;
  }

  /**
   *
   * @param predicates
   * @return
   */
  public static AnyPredicate any(Predicate... predicates) {
    final AnyPredicate pred = new AnyPredicate();
    pred.vec.addAll(Arrays.asList(predicates));
    return pred;
  }

  public static HeaderPredicate header(String name, String value) {
    return new HeaderPredicate(name, value);
  }

  public static NotPredicate not(Predicate predicate) {
    return new NotPredicate(predicate);
  }

  public static MethodPredicate options() {
    return new MethodPredicate("OPTIONS");
  }

  public static MethodPredicate GET() {
    return new MethodPredicate("GET");
  }

  public static MethodPredicate PUT() {
    return new MethodPredicate("PUT");
  }

  public static MethodPredicate POST() {
    return new MethodPredicate("POST");
  }

  public static MethodPredicate DELETE() {
    return new MethodPredicate("DELETE");
  }

  public static MethodPredicate HEAD() {
    return new MethodPredicate("HEAD");
  }

  public static MethodPredicate TRACE() {
    return new MethodPredicate("TRACE");
  }

  public static MethodPredicate CONNECT() {
    return new MethodPredicate("CONNECT");
  }

  public static MethodPredicate PATCH() {
    return new MethodPredicate("PATCH");
  }

  public static HostPredicate host(String value) {
    return new HostPredicate(value);
  }

  public Svr<S> start() {
    return new Svr<>(this);
  }

  public SvrBuilder<S> prefix(String value) {
    this.prefix = value;
    return this;
  }

  public SvrBuilder<S> bind(String value) {
    this.bind = value;
    return this;
  }

  public SvrBuilder<S> systemName(String systemName) {
    this.systemName = systemName;
    return this;
  }


  public SvrBuilder<S> backlog(int value) {
    this.backlog = value;
    return this;
  }

  public SvrBuilder<S> hostname(String value) {
    this.hostname = value;
    return this;
  }

  public SvrBuilder<S> disableSignals(boolean disable) {
    this.disableSignals = disable;
    return this;
  }

  public SvrBuilder systemExit(final boolean systemExit) {
    this.systemExit = systemExit;
    return this;
  }

  public SvrBuilder<S> shutdownTimeout(int sec) {
    this.shutdownTimeout = sec;
    return this;
  }

  public SvrBuilder<S> disableHttp2(boolean disable) {
    this.noHttp2 = disable;
    return this;
  }

  public SvrBuilder<S> resource(String path, Consumer<Resource> resource) {

    return this;
  }

  interface Predicate {

  }

  /**
   * A self-contained event-loop with it's own state, routing and middleware.
   */
  public static class Reactor<S> {

    public final SvrBuilder<S> builder;
    public final int index;
    public final S state;
    public final ArrayList<Middleware> middlewares = new ArrayList<>();
    public final ArrayList<Predicate> filters = new ArrayList<>();
    public final LinkedHashMap<String, Resource> resources = new LinkedHashMap<>();
    public final ArrayList<Scope<S>> scopes = new ArrayList<>();
    public String prefix;
    public ContentEncoding encoding;

    // Once started.
    public Addr<?> arbiter;
    public Sys system;

    Reactor(SvrBuilder<S> builder, int index, S state) {
      this.builder = builder;
      this.index = index;
      this.state = state;
    }

    public Reactor<S> prefix(String prefix) {
      this.prefix = prefix;
      return this;
    }

    public Reactor<S> resource(String path, Consumer<Resource<S>> fn) {
      final Resource<S> resource = new Resource<>(builder, this, path);
      if (resources.containsKey(path)) {
        throw new MojoError("path '" + path + "' already exists", Code.BAD_INPUT);
      }
      resources.put(path, resource);
      fn.accept(resource);
      return this;
    }
  }

  public static class Scope<S> {
    public Scope<S> nested(Consumer<Scope<S>> builder) {
      return this;
    }
  }

  /**
   * Services that are placed in the HttpRequest/HttpResponse pipeline, intercepting all within the context
   * it exists. It's common to have global middlewares such as a Logger and Session storage.
   */
  public static class Middleware<S> {

  }

  /**
   * *Resource* is an entry in route table which corresponds to requested URL.
   *
   * Resource in turn has at least one route. Route consists of an object that implements `HttpHandler`
   * trait (handler) and list of predicates (objects that implement `Predicate` trait). Route uses
   * builder-like action for configuration. During request handling, resource object iterate
   * through all routes and check all predicates for specific route, if request matches all
   * predicates route route considered matched and route handler get called.
   */
  public static class Resource<S> {

    public final SvrBuilder<S> builder;
    public final Reactor<S> reactor;
    public final String path;
    public ArrayList<Middleware> middlewares;
    public final ArrayList<Route> routes = new ArrayList<>();

    Resource(SvrBuilder<S> builder, Reactor<S> reactor, String path) {
      this.builder = builder;
      this.reactor = reactor;
      this.path = path;
    }

    public Route<S> route() {
      final Route<S> route = defaultRoute(this);
      routes.add(route);
      return route;
    }

    public Route<S> routeWithFilter(Predicate predicate) {
      final Route<S> route = defaultRoute(this);
      route.filter(predicate);
      routes.add(route);
      return route;
    }

    public Route<S> get() {
      return routeWithFilter(GET());
    }

    public Route<S> put() {
      return routeWithFilter(PUT());
    }

    public Route<S> post() {
      return routeWithFilter(POST());
    }

    public Route<S> delete() {
      return routeWithFilter(DELETE());
    }

    public Route<S> head() {
      return routeWithFilter(HEAD());
    }

    public Route<S> trace() {
      return routeWithFilter(TRACE());
    }

    public Route<S> connect() {
      return routeWithFilter(CONNECT());
    }

    public Route<S> patch() {
      return routeWithFilter(PATCH());
    }

    public void handler(Handler handler) {
      final Route route = route();
      route.handler(handler);
    }

    public Resource middleware(Middleware middleware) {
      if (middlewares == null) {
        middlewares = new ArrayList<>();
      }
      middlewares.add(middleware);
      return this;
    }


  }

  public static class Route<S> {

    public Resource resource;
    public ArrayList<Predicate> predicates;

    public Handler<S> handler;

    public void filter(Predicate predicate) {
      if (predicates == null) {
        predicates = new ArrayList<>();
      }
      this.predicates.add(predicate);
    }

    public void handler(Handler handler) {
    }
  }

  public static class Handler<S> {

  }

  /**
   * Ensure this predicate is not matched.
   */
  public static class NotPredicate implements Predicate {

    public final Predicate predicate;

    public NotPredicate(Predicate predicate) {
      this.predicate = predicate;
    }
  }

  /**
   * All predicates in the vector must be valid.
   */
  public static class AllPredicate implements Predicate {

    public final ArrayList<Predicate> vec = new ArrayList<>();
  }

  /**
   * Only a single predicate in the vector must be valid.
   */
  public static class AnyPredicate implements Predicate {

    public final ArrayList<Predicate> vec = new ArrayList<>();
  }

  /**
   * Match the HTTP method such as "GET", "POST", etc.
   */
  public static class MethodPredicate implements Predicate {

    public final String value;

    public MethodPredicate(String value) {
      this.value = value;
    }
  }

  /**
   * Ensure the request as a matching header.
   */
  public static class HeaderPredicate implements Predicate {

    public final String name;
    public final String value;

    public HeaderPredicate(String name, String value) {
      this.name = name;
      this.value = value;
    }
  }

  /**
   * Ensure host matches supplied value.
   */
  public static class HostPredicate implements Predicate {

    public final String value;

    public HostPredicate(String value) {
      this.value = value;
    }
  }
}
