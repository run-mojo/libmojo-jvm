package run.mojo.example;

import dagger.Component;
import javax.inject.Singleton;

/**
 *
 */
@Component(
    modules = {MojoApp_Module.class}
)
@Singleton
public interface AppComponent {

  AppComponent I = DaggerAppComponent.create();

  static AppComponent get() {
    return I;
  }

//  Set<ActorDescriptor> registry();
}
