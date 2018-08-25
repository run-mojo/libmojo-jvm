package run.mojo.example;

import javax.inject.Inject;
import run.mojo.SliceScope;
import run.mojo.example.actions.Actions_Package;

/**
 *
 */
@SliceScope
public class Services {

  public final Actions_Package actions;

  @Inject
  Services(Actions_Package actions) {
    this.actions = actions;
  }

  public Actions_Package actions() { return actions; }
}
