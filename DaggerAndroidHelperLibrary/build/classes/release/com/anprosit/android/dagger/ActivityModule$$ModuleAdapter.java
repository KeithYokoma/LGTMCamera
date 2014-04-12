// Code generated by dagger-compiler.  Do not edit.
package com.anprosit.android.dagger;

import dagger.internal.BindingsGroup;
import dagger.internal.ModuleAdapter;
import dagger.internal.ProvidesBinding;
import javax.inject.Provider;

/**
 * A manager of modules and provides adapters allowing for proper linking and
 * instance provision of types served by {@code @Provides} methods.
 */
public final class ActivityModule$$ModuleAdapter extends ModuleAdapter<ActivityModule> {
  private static final String[] INJECTS = { };
  private static final Class<?>[] STATIC_INJECTIONS = { };
  private static final Class<?>[] INCLUDES = { };

  public ActivityModule$$ModuleAdapter() {
    super(com.anprosit.android.dagger.ActivityModule.class, INJECTS, STATIC_INJECTIONS, false /*overrides*/, INCLUDES, false /*complete*/, true /*library*/);
  }

  /**
   * Used internally obtain dependency information, such as for cyclical
   * graph detection.
   */
  @Override
  public void getBindings(BindingsGroup bindings, ActivityModule module) {
    bindings.contributeProvidesBinding("dagger.ObjectGraph", new ProvideActivityGraphProvidesAdapter(module));
    bindings.contributeProvidesBinding("@com.anprosit.android.dagger.annotation.ForActivity()/android.content.Context", new ProvideActivityContextProvidesAdapter(module));
    bindings.contributeProvidesBinding("android.app.Activity", new ProvideActivityProvidesAdapter(module));
  }

  /**
   * A {@code Binding<dagger.ObjectGraph>} implementation which satisfies
   * Dagger's infrastructure requirements including:
   *
   * Being a {@code Provider<dagger.ObjectGraph>} and handling creation and
   * preparation of object instances.
   */
  public static final class ProvideActivityGraphProvidesAdapter extends ProvidesBinding<dagger.ObjectGraph>
      implements Provider<dagger.ObjectGraph> {
    private final ActivityModule module;

    public ProvideActivityGraphProvidesAdapter(ActivityModule module) {
      super("dagger.ObjectGraph", IS_SINGLETON, "com.anprosit.android.dagger.ActivityModule", "provideActivityGraph");
      this.module = module;
      setLibrary(true);
    }

    /**
     * Returns the fully provisioned instance satisfying the contract for
     * {@code Provider<dagger.ObjectGraph>}.
     */
    @Override
    public dagger.ObjectGraph get() {
      return module.provideActivityGraph();
    }
  }

  /**
   * A {@code Binding<android.content.Context>} implementation which satisfies
   * Dagger's infrastructure requirements including:
   *
   * Being a {@code Provider<android.content.Context>} and handling creation and
   * preparation of object instances.
   */
  public static final class ProvideActivityContextProvidesAdapter extends ProvidesBinding<android.content.Context>
      implements Provider<android.content.Context> {
    private final ActivityModule module;

    public ProvideActivityContextProvidesAdapter(ActivityModule module) {
      super("@com.anprosit.android.dagger.annotation.ForActivity()/android.content.Context", IS_SINGLETON, "com.anprosit.android.dagger.ActivityModule", "provideActivityContext");
      this.module = module;
      setLibrary(true);
    }

    /**
     * Returns the fully provisioned instance satisfying the contract for
     * {@code Provider<android.content.Context>}.
     */
    @Override
    public android.content.Context get() {
      return module.provideActivityContext();
    }
  }

  /**
   * A {@code Binding<android.app.Activity>} implementation which satisfies
   * Dagger's infrastructure requirements including:
   *
   * Being a {@code Provider<android.app.Activity>} and handling creation and
   * preparation of object instances.
   */
  public static final class ProvideActivityProvidesAdapter extends ProvidesBinding<android.app.Activity>
      implements Provider<android.app.Activity> {
    private final ActivityModule module;

    public ProvideActivityProvidesAdapter(ActivityModule module) {
      super("android.app.Activity", IS_SINGLETON, "com.anprosit.android.dagger.ActivityModule", "provideActivity");
      this.module = module;
      setLibrary(true);
    }

    /**
     * Returns the fully provisioned instance satisfying the contract for
     * {@code Provider<android.app.Activity>}.
     */
    @Override
    public android.app.Activity get() {
      return module.provideActivity();
    }
  }
}